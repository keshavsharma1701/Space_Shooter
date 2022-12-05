package com.keshav.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.keshav.gaming.sprite.Enemy;
import com.keshav.gaming.sprite.Missile;
import com.keshav.gaming.sprite.Player;
import com.keshav.gaming.sprite.PlayerLine;
import com.keshav.gaming.sprite.Sprite;

public class Board extends JPanel implements Constants{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int countTimer;
	BufferedImage bi;
	Player player;
	PlayerLine playerLine;
	Enemy enemies[][];
	int count,c;
	String gameMessage="Game Start";
	public Board() throws Exception{
		countTimer=0;
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		bi = ImageIO.read(Board.class.getResource("game-bg.jpg"));
		player = new Player();
		count=0;
		playerLine = new PlayerLine();
		enemies = new Enemy[MAX_ROWS][MAX_ENEMIES];
		loadEnemies();
		setFocusable(true);
		bindEvents();
		gameLoop();
	}
	
	boolean isCollide(Sprite one, Sprite two) {
		int xDistance = Math.abs(one.getX()- two.getX());
		int yDistance = Math.abs(one.getY()- two.getY());
		int w=Math.max(one.getW(), two.getW());
		int h=Math.max(one.getH(),two.getH());
		return (xDistance<=w && yDistance<=h);
	}
	
	void checkCollision() {
		for(int i=0;i<MAX_ROWS;i++) {
		for(int j=0;j<MAX_ENEMIES;j++) {
			if(isCollide(playerLine , enemies[i][j])) {
				gameMessage = "Game Over";
				timer.stop();
			}
		}
		}
		for(Missile missile : player.getMissiles()) {
			for(int i=0;i<MAX_ROWS;i++) {
			for(int j=0;j<MAX_ENEMIES;j++) {
				if(enemies[i][j].isAlive) {
				if(isCollide(enemies[i][j], missile)) {
					enemies[i][j].setisAlive(false);
					missile.setUsed(true);
				}
			}
			}	
		}
		}
	}
	
	void isGameWin() {
		c=0;
		for(int i=0;i<MAX_ROWS;i++) {
			for(int j=0;j<MAX_ENEMIES;j++) {
		       if(!enemies[i][j].getisAlive()) {
		    	   c++;
		       }
			}
		}
		if(c==MAX_ENEMIES*MAX_ROWS) {
			gameMessage = "Game Win";
			timer.stop();
		}
	}
	void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				///TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {;
				    player.moveRight();
				    player.limitMotion();
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {;
					player.moveLeft();
					player.limitMotion();
				}
				else if(e.getKeyCode()== KeyEvent.VK_UP) {
					try {
						player.addMissile(player.getX() + (player.getW()/2), player.getY());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	Timer timer;
	void gameLoop()
	{
		
		//delay
		timer=new Timer(100,new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Timer call..."):
				repaint();
				countTimer++;
				checkCollision();
				isGameWin();
				count++;
				if(count==10) {
					gameMessage="";
				}
			}
		});
		timer.start();
	}
	
	void loadEnemies() throws Exception
	{
		int x;
		int GAP;
		for(int i=0;i<MAX_ROWS;i++) {			
		x=50;
		GAP =50;	
		for(int j=0;j<MAX_ENEMIES;j++)
		{
			enemies[i][j]=new Enemy(x,(i+1)*30);
			x=x+GAP;
		}
		}
	}
	
	void printEnemies(Graphics pen)
	{
		int moveEnemiesDown=0;
		if(countTimer==25) {
			countTimer=0;
			moveEnemiesDown=1;
		}
		for(int i=0;i<MAX_ROWS;i++) {
		for(int j=0;j<MAX_ENEMIES;j++)
		{
			if(!enemies[i][j].getisAlive()) {
			continue;
			}
			else {
				if(moveEnemiesDown==1) {
					enemies[i][j].moveVertical();
				}
				enemies[i][j].draw(pen);
				enemies[i][j].limitMotion();
				enemies[i][j].move();
			}
		}
		}
	}
	
	void printMissiles(Graphics pen) {
		ArrayList<Missile> missiles = player.getMissiles();
		for(int i = 0; i<missiles.size(); i++) {
			Missile currentMissile = missiles.get(i);
			if(!currentMissile.getUsed()) {
			currentMissile.draw(pen);
			currentMissile.move();
			}
			else {
				missiles.remove(i);
			}
			if(currentMissile.outOfScreen()) {
				missiles.remove(i);
				//System.out.println(missiles.size());
			}
		  }
		}
	
	void printMessage(Graphics pen) {
		// TODO Auto-generated method stub
		pen.setColor(Color.RED);
		pen.setFont(new Font("times", Font.BOLD, BOARD_WIDTH/30));
		pen.drawString(gameMessage, BOARD_WIDTH/3, BOARD_HEIGHT/2);
	}
	
	@Override
   public void paintComponent(Graphics pen) {
		
	pen.drawImage(bi, 0, 0,BOARD_WIDTH,BOARD_HEIGHT, null);
	player.draw(pen);
	playerLine.draw(pen);
	printEnemies(pen);
	printMissiles(pen);
	if(gameMessage.length()>0){
		printMessage(pen);
		}
   }
}
