package com.keshav.gaming.sprite;

import javax.imageio.ImageIO;

import com.keshav.gaming.Constants;

public class PlayerLine extends Sprite implements Constants{
	public PlayerLine() throws Exception{
	x=0;
	y=BOARD_HEIGHT-180;
	w=BOARD_WIDTH;
	h=20;
	bi= ImageIO.read(PlayerLine.class.getResource("playerline.gif"));
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limitMotion() {
		// TODO Auto-generated method stub
		
	}
	
}
