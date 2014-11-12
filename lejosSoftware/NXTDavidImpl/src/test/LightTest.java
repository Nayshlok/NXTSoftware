package test;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;
import model.BotBluetooth;
import model.listeners.LineListener;

public class LightTest implements LineListener{

	private BotBluetooth bluetooth = new BotBluetooth();
	private LightSensor sensor = new LightSensor(SensorPort.S2, true);
	
	public static void main(String[] args) {
		LightTest test = new LightTest();
		test.run();
	}
	
	public void run(){
		bluetooth.nonThreadRun();
		
		for(int i = 0; i < 20; i++){
			int reading = sensor.getLightValue();
			bluetooth.sendMessage("Light reading: " + sensor.getLightValue());
			bluetooth.sendMessage("Normalized Value: " + sensor.getNormalizedLightValue() + "\r\n");
			System.out.println(reading);
			Delay.msDelay(1000);
		}
	}

	@Override
	public void lineDetected() {
		// TODO Auto-generated method stub
		
	}
	
	
}
