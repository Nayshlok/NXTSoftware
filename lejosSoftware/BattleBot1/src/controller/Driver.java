package controller;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import listener.DriverListener;
import detector.RotationDetector;


public class Driver {

	private RegulatedMotor leftWheel, rightWheel;
	private List<DriverListener> driveListeners;
	private MotorState motorState;
	private boolean finishedRotation;
	private final int DEGREE_FOR_REVOLUTION = 1090;
	
	public enum Direction{
		FORWARD, BACKWARD, LEFT, RIGHT;
	}
	
	public enum MotorState{
		FORWARD, BACKWARD, LEFT, RIGHT, STOP;
	}
	
	public Driver(){
		leftWheel = Motor.B;
		rightWheel = Motor.C;
		leftWheel.setSpeed((int) (360));
		rightWheel.setSpeed((int) (360));
		driveListeners = new ArrayList<DriverListener>();
	}
	
	public void registerDriverListener(DriverListener driveListen){
		driveListeners.add(driveListen);
	}
	
	public void forward(){
		System.out.println("forward");
		motorState = MotorState.FORWARD;
		leftWheel.forward();
		rightWheel.forward();
		this.notifyStop();
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
	
	public void turnRight(){
		leftWheel.setSpeed(360);
		rightWheel.setSpeed(360);
		motorState = MotorState.RIGHT;
		leftWheel.forward();
		rightWheel.backward();
		notifyStop();
	}
	
	public void turnRight(int degreeTurn){
		motorState = MotorState.RIGHT;
		int totalAngle = DEGREE_FOR_REVOLUTION;
    	int angle = (totalAngle/360) * degreeTurn;
    	
        leftWheel.rotate(angle, true);
        rightWheel.rotate(-angle);
	}

	public void turnLeft(){
		leftWheel.setSpeed(360);
		rightWheel.setSpeed(360);
		motorState = MotorState.LEFT;
		leftWheel.backward();
		rightWheel.forward();
		notifyStop();
	}

	public void turnLeft(int degreeTurn){
		motorState = MotorState.LEFT;
		int totalAngle = DEGREE_FOR_REVOLUTION;
    	int angle = (totalAngle/360) * degreeTurn;
    	
        leftWheel.rotate(-angle, true);
        rightWheel.rotate(angle);
	}
	
	public MotorState getMotorState(){
		return motorState;
	}
	
	public void move(Direction direction){
		switch(direction){
		case BACKWARD:
			backward();
			break;
		case FORWARD:
			forward();
			break;
		case LEFT:
			turnLeft();
			break;
		case RIGHT:
			turnRight();
			break;
		default:
			stop();
			break;
		
		}
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
	
}
