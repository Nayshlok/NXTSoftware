package test;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import model.BotBluetooth;
import model.Driver;

public class DistanceTest {

	private BotBluetooth bluetooth = new BotBluetooth();
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S3);
	private Driver driver = new Driver();
	
	public static void main(String[] args) {
		DistanceTest test = new DistanceTest();
		test.run();
	}
	
	public void run(){
		
		try{
			bluetooth.nonThreadRun();
			
			System.out.println(sensor.getUnits());
			bluetooth.sendMessage("measuring in " + sensor.getUnits() + "\r\n\r\n");

			for(int i = 0; i < 20; i++){
				int[] distances = new int[8];
				sensor.ping();
				int readIn = sensor.getDistances(distances);
				String distString = "[";
				for(int j : distances){
					distString += j + ", ";
				}
				distString += "]";
				bluetooth.sendMessage("Read in: " + readIn + ", Distance reading: " + distString + "\r\n\r\n");
				System.out.println(distances[0]);
				Delay.msDelay(1500);
				driver.turnClockwise(30);
			}
		} catch(Exception e){
			bluetooth.sendMessage(e.getMessage());
		}
	}
	
}
