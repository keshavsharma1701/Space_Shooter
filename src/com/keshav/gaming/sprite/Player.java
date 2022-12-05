package com.keshav.gaming.sprite;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.keshav.gaming.Constants;

public class Player extends Sprite implements Constants{
	private ArrayList<Missile> missiles = new ArrayList<>();
	public Player() throws Exception{
		x=50;
		y=BOARD_HEIGHT-100;
		w=40;
		h=60;
		speed=10;
		bi=ImageIO.read(Player.class.getResource("aircraft.gif"));
	}
	
	public ArrayList<Missile> getMissiles(){
		return missiles;
	}
	
	public void addMissile(int x, int y) throws IOException {
		missiles.add(new Missile(x,y));
	}
	
	@Override
	public void limitMotion() {
		if(x<50)
		{
			x=50;
		}
		if(x>BOARD_WIDTH-100)
		{
			x=BOARD_WIDTH-100;
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		//x=x+speed;
		if(speed>0) {
		for(int i=1;i<=speed;i++) {
			x=x+i;
		}
		}
		else {
			for(int i=speed;i<=-1;i++) {
				x=x+i;
			}
		}
	}
	public void moveRight() {
		speed=10;
		this.move();
	}
	public void moveLeft() {
		speed=-10;
		this.move();
	}
}
