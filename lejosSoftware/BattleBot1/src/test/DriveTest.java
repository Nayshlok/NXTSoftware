package test;

import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import listener.RotationListener;
import controller.Driver;
import controller.Driver.Direction;
import detector.RotationDetector;

public class DriveTest implements RotationListener{

	public static void main(String[] args){
		DriveTest test = new DriveTest();
		test.run();
	}

	private Driver driver;
	private RotationDetector rotation;
	
	public DriveTest(){
		driver = new Driver();
		rotation = new RotationDetector();
		rotation.registerListener(this);
	}
	
	public void run(){
		Motor.A.setAcceleration(12000);
		Motor.A.setSpeed(Motor.A.getMaxSpeed());
		Motor.A.rotate(-65);
		Delay.msDelay(1000);
		Motor.A.setAcceleration(6000);
		Motor.A.rotate(125);
	}

	@Override
	public void reachedFullCircle() {
		// TODO Auto-generated method stub
		
	}

}
