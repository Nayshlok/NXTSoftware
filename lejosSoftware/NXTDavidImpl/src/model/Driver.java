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


public class Driver implements DistanceListener, LineListener, Runnable {

	private RegulatedMotor leftWheel, rightWheel;
	private List<DriverListener> driveListeners;
	private List<FinishedMovementListener> finishListeners;
	private DriveState driveState;
	private MotorState motorState;
	private final int NUMBER_OF_CANS = 3;
	private int cansPushed = 0;
	
	public enum DriveState{
		SEARCHING, REMOVING, RETURNING, FINISHED;
	}
	public enum MotorState{
		FORWARD, BACKWARD, TURNING, STOP;
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
		motorState = MotorState.FORWARD;
		leftWheel.forward();
		rightWheel.forward();
	}
	
	public void forward(int numberOfRotation){
		motorState = MotorState.FORWARD;
		leftWheel.rotate(360 * numberOfRotation, true);
		rightWheel.rotate(360 * numberOfRotation);
	}
	
	public void backward(){
		motorState = MotorState.BACKWARD;
		leftWheel.backward();
		rightWheel.backward();
		this.notifyMovingBackwards();
	}
	
	public void backward(int numberOfRotation){
		motorState = MotorState.BACKWARD;
		this.notifyMovingBackwards();
		leftWheel.rotate(-360 * numberOfRotation, true);
		rightWheel.rotate(-360 * numberOfRotation);
	}
	
	public void stop(){
		motorState = MotorState.STOP;
		leftWheel.stop(true);
		rightWheel.stop();
		this.notifyStop();
	}
	
	public void turnClockwise(){
		motorState = MotorState.TURNING;
		leftWheel.forward();
		rightWheel.backward();
	}
	
	public void turnClockwise(int degreeTurn){
		motorState = MotorState.TURNING;
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
		RConsole.println("received light event");
		switch (driveState) {
		case SEARCHING:
			RConsole.println("recieved line in search");
			break;
		case REMOVING:
			RConsole.println("recieved line in remove");
			this.forward(2);
			cansPushed++;
			RConsole.println("number of cans pushed: " + cansPushed);
				
				if(cansPushed > NUMBER_OF_CANS){
					RConsole.println("Finished with cans");
					driveState = DriveState.FINISHED;
					this.forward(4);
					notifyFinish();
				}
				else{
					this.backward(3);
					driveState = DriveState.SEARCHING;
					this.turnClockwise();
				}
			break;
		case RETURNING:
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

//	@Override
//	public void reachedFullCircle() {
//		RConsole.println(driveState.toString());
//		if(driveState == DriveState.SEARCHING){
//			RConsole.println("Finished Movement");
//			driveState = DriveState.FINISHED;
//			this.forward();
//		}
//		else{
//			this.resetTacho();
//		}
//	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			Thread.yield();
		}
	}

	public void resetTacho(){
		leftWheel.resetTachoCount();
		rightWheel.resetTachoCount();
		resumeAction();
	}

	private void resumeAction() {
		switch(motorState){
		case FORWARD:
			this.forward();
			break;
		case TURNING:
			this.turnClockwise();
			break;
		case STOP:
			this.stop();
			break;
		}
	}
	
}
