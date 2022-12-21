package com.keshav.gaming;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Sound {
	MP3Player mp1, mp2;
	String checksound;
	public void setChecksound(String checksound) {
		this.checksound = checksound;
	}
	public Sound() throws Exception{
		mp1 = new MP3Player(new File("src\\com\\keshav\\gaming\\space-sound.mp3"));
		mp1.play();
	}
	public void soundplay() {
        if(checksound=="siren") {
			mp2 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\attack-siren.mp3"));
		}
		else if(checksound=="enemydie") {
			mp2 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\shipblast.mp3"));
		}
		else if(checksound=="weapon") {
			mp2 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\missile.mp3"));
		}
		mp2.play();
	}
}
