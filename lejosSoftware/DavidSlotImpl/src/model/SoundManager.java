package model;

import lejos.nxt.Sound;

public class SoundManager {

	public void playVictory(){
		Sound.playNote(Sound.FLUTE, 1200, 250);
		Sound.playNote(Sound.FLUTE, 1400, 250);
		Sound.playNote(Sound.FLUTE, 1600, 250);
		Sound.playNote(Sound.FLUTE, 1000, 250);
	}
	
	public void playFailure(){
		Sound.playNote(Sound.PIANO, 1200, 250);
		Sound.playNote(Sound.PIANO, 1000, 250);
		Sound.playNote(Sound.PIANO, 800, 250);
		Sound.playNote(Sound.PIANO, 500, 250);
	}
	
	public void playStart(){
		Sound.beepSequenceUp();
	}
	
	public void playReturn(){
		Sound.buzz();
	}
	
}
