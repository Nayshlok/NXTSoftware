package hello;

import lejos.nxt.ADSensorPort;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXT;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;

public class DavidWorld {

	public static void main(String[] args){
		DavidWorld world = new DavidWorld();
		world.run();
	}
	
	private boolean wheelRunning = true;
	
	public void run(){
		SensorPort.S1.addSensorPortListener(new StartStopListener());
		SensorPort.S1.setListenerTolerance(500);
		System.out.println("Hello World");
		MotorPort.A.controlMotor(50, 1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MotorPort.A.controlMotor(0, 3);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		MotorPort.A.controlMotor(50, 2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MotorPort.A.controlMotor(0, 3);
		
		Button.waitForAnyPress();
	}
	
}
