package controller;

import java.util.List;

import controller.Driver.Direction;
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
	private Weapon weapon;
	
	public enum BattleState{
		SEARCHING, FLEEING, RETURNING
	}
	
	public BattleBot(){
		weapon = new Weapon();
		driver = new Driver();
		DistanceDetector distance = new DistanceDetector();
		LineDetector line = new LineDetector();
		RotationDetector rotation = new RotationDetector();
		
		distance.registerListener(this);
		line.registerListener(this);
		rotation.registerListener(this);
		
		threadList.add(new Thread(line, "LineThread"));
		threadList.add(new Thread(rotation, "RotationThread"));
	}
	
	public void start() {
		for(Thread t : threadList){
			t.start();
		}
		
		driver.turnRight();
		
		while(true){
			Thread.yield();
		}
	}

	@Override
	public void reachedFullCircle() {
		if(state == BattleState.RETURNING){
			driver.turnRight();
			state = BattleState.SEARCHING;
		}
		
	}

	@Override
	public void objectDetected(int distance) {
		if(distance < 20 && state != BattleState.FLEEING){
			state = BattleState.FLEEING;
			driver.turnLeft();
			Delay.msDelay(200);
			driver.forward();
		}
		else if(distance < 30 && state == BattleState.SEARCHING){
			weapon.beginAttack();
		}
		else{
			driver.forward();
		}
		
	}

	@Override
	public void lineDetected() {
		switch (driver.getMotorState()){
		case BACKWARD:
			driver.turnRight();
			Delay.msDelay(200);
			state = BattleState.RETURNING;
			driver.forward();
			break;
		case FORWARD:
			driver.turnRight();
			Delay.msDelay(200);
			state = BattleState.RETURNING;
			driver.backward();
			break;
		case LEFT:
			driver.turnRight();
			Delay.msDelay(200);
			state = BattleState.RETURNING;
			driver.forward();
			break;
		case RIGHT:
			driver.turnLeft();
			Delay.msDelay(200);
			state = BattleState.RETURNING;
			driver.forward();
			break;
		default:
			break;
		
		}
		
	}

}
