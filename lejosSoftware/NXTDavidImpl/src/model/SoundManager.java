package model;
import lejos.nxt.Sound;
import model.listeners.ContactListener;
import model.listeners.DriverListener;


public class SoundManager implements ContactListener, DriverListener, Runnable{

	private boolean inContact = false;
	private boolean backingUp = false;
	
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
	
	public void playContact(){
		Sound.beepSequenceUp();
	}
	
	public void stopContact(){
	}
	
	public void playBackup(){
		Sound.beep();
	}
	

	@Override
	public void buttonPressed() {
		inContact = true;
		playContact();
	}

	@Override
	public void buttonReleased() {
		inContact = false;
		
	}

	@Override
	public void movingBackward() {
		playBackup();
	}

	@Override
	public void stopBackward() {
		backingUp = false;
		
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			Thread.yield();
		}
	}
	
	
	
}
