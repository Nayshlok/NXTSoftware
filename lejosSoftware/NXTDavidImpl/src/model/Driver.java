package model;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;
import model.detectors.RotationDetector;
import model.listeners.DistanceListener;
import model.listeners.DriverListener;
import model.listeners.FinishedMovementListener;
import model.listeners.LineListener;
import model.listeners.RotationListener;


public class Driver implements DistanceListener, LineListener, RotationListener, Runnable {

	private RegulatedMotor leftWheel, rightWheel;
	private List<DriverListener> driveListeners;
	private List<FinishedMovementListener> finishListeners;
	private DriveState driveState;
	
	public enum DriveState{
		SEARCHING, REMOVING, RETURNING, FINISHED;
	}
	
	public Driver(){
		leftWheel = Motor.B;
		rightWheel = Motor.C;
		driveListeners = new ArrayList<DriverListener>();
		finishListeners = new ArrayList<FinishedMovementListener>();
		driveState = DriveState.SEARCHING;
	}
	
	public void registerDriverListener(DriverListener driveListen){
		driveListeners.add(driveListen);
	}
	
	public void registerFinishedListener(FinishedMovementListener finishListen){
		finishListeners.add(finishListen);
	}
	
	public void forward(){
		System.out.println("forward");
		leftWheel.forward();
		rightWheel.forward();
	}
	
	public void forward(int numberOfRotation){
		leftWheel.rotate(360 * numberOfRotation, true);
		rightWheel.rotate(360 * numberOfRotation);
	}
	
	public void backward(){
		leftWheel.backward();
		rightWheel.backward();
		this.notifyMovingBackwards();
	}
	
	public void backward(int numberOfRotation){
		this.notifyMovingBackwards();
		leftWheel.rotate(-360 * numberOfRotation, true);
		rightWheel.rotate(-360 * numberOfRotation);
	}
	
	public void stop(){
		leftWheel.stop();
		rightWheel.stop();
		this.notifyStop();
	}
	
	public void turnClockwise(){
		leftWheel.forward();
		rightWheel.backward();
	}
	
	public void turnClockwise(int degreeTurn){
		int totalAngle = RotationDetector.DEGREE_FOR_REVOLUTION;
    	int angle = (totalAngle/360) * degreeTurn;
    	
        leftWheel.rotate(angle, true);
        rightWheel.rotate(-angle);
	}

	public void notifyMovingBackwards(){
		for(DriverListener l : driveListeners){
			l.movingBackward();
		}
	}
	
	public void notifyStop(){
		for(DriverListener l : driveListeners){
			l.stopBackward();
		}
	}
	
	public void notifyFinish(){
		for(FinishedMovementListener l : finishListeners){
			l.finishedMovement();
		}
	}
	
	@Override
	public void objectDetected(int distance) {
		
		if(driveState == DriveState.SEARCHING){
			driveState = DriveState.REMOVING;
			this.stop();
			this.forward();
		}
	}

	@Override
	public void lineDetected() {
		switch (driveState) {
		case SEARCHING:
			RConsole.println("recieved line in search");
			break;
		case REMOVING:
			RConsole.println("recieved line in remove");
			System.out.println("locked turn");
			this.turnClockwise(180);
			this.driveState = DriveState.RETURNING;
			this.forward();
			break;
		case RETURNING:
			RConsole.println("recieved line in return");
			//stop();
			driveState = DriveState.SEARCHING;
			this.turnClockwise();
			break;
		case FINISHED:
			RConsole.println("recieved line in finish");

			this.forward(4);
			this.notifyFinish();
			break;
		default:
			break;
		}
	}

	@Override
	public void reachedFullCircle() {
		if(driveState == DriveState.SEARCHING){
			RConsole.println("Finished Movement");
			driveState = DriveState.FINISHED;
			this.forward();
		}
		else{
			leftWheel.resetTachoCount();
			rightWheel.resetTachoCount();
			RConsole.println("done reseting tacho");
		}
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			Thread.yield();
		}
	}

	
}
