package com.keshav.gaming.sprite;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile extends Sprite{
	boolean used;
	public Missile(int x, int y) throws IOException {
		this.x=x;
		this.y=y;
		w=10;
		h=30;
		speed=-50;
		used=false;
		try {
			bi=ImageIO.read(Missile.class.getResource("missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean outOfScreen(){
		if(y<0) {
			return true;
		}
		return false;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		y=y+speed;
	}

	
	@Override
	public void limitMotion() {
		// TODO Auto-generated method stub
		
	}
	public void setUsed(boolean used) {
		// TODO Auto-generated method stub
		this.used=used;
		
	}
	public boolean getUsed() {
		return used;
	}

}
