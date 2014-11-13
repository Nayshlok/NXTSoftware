package model;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import model.detectors.ContactDetector;
import model.detectors.DistanceDetector;
import model.detectors.LineDetector;
import model.detectors.RotationDetector;
import model.detectors.RoverTimer;
import model.listeners.FinishedMovementListener;
import model.listeners.TimerListener;


public class Rover implements TimerListener, FinishedMovementListener{

//	public static BotBluetooth bluetoothConnection;
	private Driver driver;
	private RoverTimer timer;
	private SoundManager soundManager;
	private List<Thread> threadList = new ArrayList<Thread>();
	
	public Rover(){
		
		driver = new Driver();
		timer = new RoverTimer();
		soundManager = new SoundManager();
//		bluetoothConnection = new BotBluetooth();
		LineDetector lineDetector = new LineDetector();
		DistanceDetector distanceDetector = new DistanceDetector();
		ContactDetector contactDetector = new ContactDetector();
		RotationDetector rotationDetector = new RotationDetector();		
		
		driver.registerFinishedListener(this);
		driver.registerDriverListener(soundManager);
		timer.registerListener(this);
		lineDetector.registerListener(driver);
		distanceDetector.registerListener(driver);
		contactDetector.registerListener(soundManager);
		rotationDetector.registerListener(driver);
		
//		threadList.add(new Thread(bluetoothConnection, "bluetooth"));
		threadList.add(new Thread(driver, "driver"));
		threadList.add(new Thread(timer, "timer"));
		threadList.add(new Thread(lineDetector, "line"));
		threadList.add(new Thread(distanceDetector, "distance"));
		threadList.add(new Thread(contactDetector, "contact"));
		threadList.add(new Thread(rotationDetector, "rotation"));
	}
	
	public void start(){
		
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
		
		for(Thread t : threadList){
			t.start();
		}
		
		driver.turnClockwise();
		
		while(true){
			Thread.yield();
		}
	}
	
	@Override
	public void timeUp() {
		RConsole.println("Stopping for time");
		soundManager.playFailure();
		for(Thread t : threadList){
			t.interrupt();
		}
		System.exit(0);
	}

	@Override
	public void finishedMovement() {
		System.out.println("Finished in: " + timer.getHumanReadableTime());
		soundManager.playVictory();
		for(Thread t : threadList){
			t.interrupt();
		}
		System.exit(0);
	}
	
}
