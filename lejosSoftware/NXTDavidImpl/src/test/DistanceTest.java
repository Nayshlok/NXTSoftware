package test;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import model.BotBluetooth;

public class DistanceTest {

	private BotBluetooth bluetooth = new BotBluetooth();
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S2);
	
	public static void main(String[] args) {
		DistanceTest test = new DistanceTest();
		test.run();
	}
	
	public void run(){
		bluetooth.nonThreadRun();
		
		for(int i = 0; i < 20; i++){
			sensor.ping();
			int[] distances = new int[8];
			sensor.getDistances(distances);
			int distance = distances[0];
			bluetooth.sendMessage("Distance reading: " + distance + "\r\n\r\n");
			System.out.println(distance);
			Delay.msDelay(1500);
		}
	}
	
}
