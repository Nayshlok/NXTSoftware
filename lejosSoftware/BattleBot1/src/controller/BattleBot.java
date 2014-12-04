package controller;

import java.util.ArrayList;
import java.util.List;

import controller.Driver.Direction;
import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import listener.DistanceListener;
import listener.LineListener;
import listener.RotationListener;
import detector.ContactDetector;
import detector.DistanceDetector;
import detector.LineDetector;
import detector.RotationDetector;

public class BattleBot implements LineListener, DistanceListener, RotationListener{

	private Driver driver;
	private List<Thread> threadList;
	private BattleState state;
	private RotationDetector rotation;
	private Weapon weapon;
	private boolean attacking;
	
	public enum BattleState{
		SEARCHING, FLEEING, RETURNING
	}
	
	public BattleBot(){
		weapon = new Weapon();
		driver = new Driver();
		DistanceDetector distance = new DistanceDetector();
		LineDetector line = new LineDetector();
		rotation = new RotationDetector();
		
		distance.registerListener(this);
		line.registerListener(this);
		rotation.registerListener(this);
		
		threadList = new ArrayList<Thread>();
		threadList.add(new Thread(line, "LineThread"));
		threadList.add(new Thread(rotation, "RotationThread"));
		threadList.add(new Thread(distance, "DistanceThread"));
	}
	
	public void start() {
		for(Thread t : threadList){
			t.start();
		}
		RConsole.println("Set to search from beginning");
		state = BattleState.SEARCHING;
		driver.turnLeft();
		
		while(true){
			Thread.yield();
		}
	}

	@Override
	public void reachedFullCircle() {
		if(state == BattleState.RETURNING || state == BattleState.FLEEING){
			RConsole.println("rotation event, return to search");
			state = BattleState.SEARCHING;
			driver.turnRight();
		}
		
	}

	@Override
	public void objectDetected(int distance) {
		if(distance < 30 && state == BattleState.SEARCHING ){
//			state = BattleState.FLEEING;
//			RConsole.println("enemy too close. State: " + state);
//			rotation.resetRotation();
//			driver.backward();
		}
		else if(state == BattleState.SEARCHING){
			driver.forward();
		}
		
		if(distance < 40){
			attacking = true;
			weapon.beginAttack();
		}
//		else if(distance > 60 && attacking && state == BattleState.FLEEING){
//			RConsole.println("state changed to searching in chase part of object detected.");
//			state = BattleState.SEARCHING;
//			attacking = false;
//			driver.turnLeft(40);
//			driver.turnRight();
//		}
		
	}

	@Override
	public void lineDetected() {
		RConsole.println("Line detected, current State: " + state);
		if(state != BattleState.RETURNING){
			RConsole.println("Drive state " + driver.getMotorState());
			rotation.resetRotation();
			state = BattleState.RETURNING;
			RConsole.println("line detected state is now: " + state);
			switch (driver.getMotorState()){
			case BACKWARD:
				driver.forward();
				break;
			case FORWARD:
				driver.backward();
				break;
			case LEFT:
				driver.turnRight(90);
				driver.forward();
				break;
			case RIGHT:
				driver.turnLeft(90);
				driver.forward();
				break;
			default:
				break;
			
			}
		}
	}

}
