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
		
		threadList = new ArrayList<Thread>();
		threadList.add(new Thread(line, "LineThread"));
		threadList.add(new Thread(rotation, "RotationThread"));
		threadList.add(new Thread(distance, "DistanceThread"));
	}
	
	public void start() {
		for(Thread t : threadList){
			t.start();
		}
		state = BattleState.SEARCHING;
		driver.turnLeft();
		
		while(true){
			Thread.yield();
		}
	}

	@Override
	public void reachedFullCircle() {
		RConsole.println("Rotation event");
		if(state == BattleState.RETURNING || state == BattleState.FLEEING){
			state = BattleState.SEARCHING;
			driver.turnRight();
		}
		
	}

	@Override
	public void objectDetected(int distance) {
		if(distance < 30 && state != BattleState.FLEEING){
			state = BattleState.FLEEING;
			weapon.beginAttack();
			driver.backward();
		}
		else if(distance < 40 && state == BattleState.SEARCHING){
			weapon.beginAttack();
		}
		else if(state != BattleState.RETURNING){
			driver.forward();
		}
		
	}

	@Override
	public void lineDetected() {
		if(state != BattleState.RETURNING){
			Motor.B.resetTachoCount();
			Motor.C.resetTachoCount();
			switch (driver.getMotorState()){
			case BACKWARD:
				state = BattleState.RETURNING;
				driver.turnLeft(60);
				driver.forward(1);
				break;
			case FORWARD:
				state = BattleState.RETURNING;
				driver.backward(2);
				driver.turnLeft(90);
				break;
			case LEFT:
				driver.turnRight(90);
				state = BattleState.RETURNING;
				driver.forward();
				break;
			case RIGHT:
				driver.turnLeft(90);
				state = BattleState.RETURNING;
				break;
			default:
				break;
			
			}
			state = BattleState.SEARCHING;
			driver.turnRight();
		}
	}

}
