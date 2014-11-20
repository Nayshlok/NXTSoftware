package test;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import model.BotBluetooth;
import model.Driver;
import model.listeners.LineListener;

public class LightTest implements LineListener{

	private LightSensor sensor = new LightSensor(SensorPort.S2, true);
	
	public static void main(String[] args) {
		RConsole.open();
		LightTest test = new LightTest();
		test.run();
	}
	
	public void run(){
		sensor.calibrateLow();
		while(true){
			int reading = sensor.getLightValue();
			RConsole.println("Light Level: " + reading);
			System.out.println(reading);
			Delay.msDelay(10);
		}
	}

	@Override
	public void lineDetected() {
		// TODO Auto-generated method stub
		
	}
	
	
}
