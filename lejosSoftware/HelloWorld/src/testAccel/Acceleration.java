package testAccel;

import hello.StartStopListener;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;

public class Acceleration {

	public static void main(String[] args){
		Acceleration world = new Acceleration();
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
