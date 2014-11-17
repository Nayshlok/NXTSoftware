package model;
import lejos.nxt.Sound;
import model.listeners.ContactListener;
import model.listeners.DriverListener;


public class SoundManager implements ContactListener, DriverListener, Runnable{

	private boolean inContact = false;
	private boolean backingUp = false;
	private Thread backingUpThread = new Thread();
	private Thread buttonThread = new Thread();
	
	public SoundManager() {
		Sound.setVolume(90);
	}
	
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
		buttonThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!Thread.interrupted())
					Sound.beepSequenceUp();
			}
		});
		buttonThread.start();
	}
	
	public void stopContact(){
	}
	
	public void playBackup(){
		backingUpThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!Thread.interrupted())
					Sound.beep();
			}
		});
		backingUpThread.start();
	}
	

	@Override
	public void buttonPressed() {
		inContact = true;
		playContact();
	}

	@Override
	public void buttonReleased() {
		buttonThread.interrupt();
	}

	@Override
	public void movingBackward() {
		playBackup();
	}

	@Override
	public void stopBackward() {
		backingUpThread.interrupt();
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			Thread.yield();
		}
	}
	
	
	
}
