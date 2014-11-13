package test;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;
import model.BotBluetooth;
import model.Driver;
import model.listeners.LineListener;

public class LightTest implements LineListener{

	private BotBluetooth bluetooth = new BotBluetooth();
	private LightSensor sensor = new LightSensor(SensorPort.S2, true);
	private Driver driver = new Driver();
	
	public static void main(String[] args) {
		LightTest test = new LightTest();
		test.run();
	}
	
	public void run(){
		bluetooth.nonThreadRun();

		driver.forward();
		
		for(int i = 0; i < 50; i++){
			int reading = sensor.getLightValue();
			bluetooth.sendMessage("Light reading: " + sensor.getLightValue() + ", Normalized Value: " + sensor.getNormalizedLightValue() + "\r\n\r\n");
			System.out.println(reading);
			Delay.msDelay(10);
		}
		driver.stop();
		Button.waitForAnyPress();
	}

	@Override
	public void lineDetected() {
		// TODO Auto-generated method stub
		
	}
	
	
}
