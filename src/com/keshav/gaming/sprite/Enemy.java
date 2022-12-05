package com.keshav.gaming.sprite;

import javax.imageio.ImageIO;

public class Enemy extends Sprite{
	int c;
	public boolean isAlive ;
	public Enemy(int x, int y) throws Exception
	{
		this.x=x;
		c=-5;
		this.y=y;
		w=40;
		h=40;
		speed=1;
		isAlive = true;
		bi=ImageIO.read(Enemy.class.getResource("enemy1.png"));
	}
	
	public boolean getisAlive() {
    	return isAlive;
    }
	
    public void setisAlive(boolean isAlive) {
    	this.isAlive=isAlive;
    	if(isAlive==false) {
    		speed=0;
    	}
    }
    
	public void moveVertical() {
		y=y+h;
	}

	@Override
	public void limitMotion() {
		// TODO Auto-generated method stub
		if(this.c==0)
		{
			speed=5;
			
		}
		else if(c==5)
		{
			speed=-5;
			c=-5;
		}
	}
	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		x=x+speed;
		c++;
	}
	
}
