package test;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;

public class Testing {

	public static void main(String[] args){
		Testing world = new Testing();
		world.run();
	}
	
	private boolean wheelRunning = true;
	
	public void run(){
//		SensorPort.S1.addSensorPortListener(new StartStopListener());
//		SensorPort.S1.setListenerTolerance(500);
		RegulatedMotor A = Motor.A;
		A.setAcceleration(300);
		A.rotate(720);
		System.out.println("Finish rotation");
		Button.waitForAnyPress();
	}
}
