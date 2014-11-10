package blueTooth;

import java.io.IOException;
import java.io.OutputStream;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.RegulatedMotor;

public class NXTSideBluetooth {

	public static void main(String[] args){
		NXTSideBluetooth transmit = new NXTSideBluetooth();
		transmit.run();
	}
	
	private NXTConnection connection;
	private OutputStream os;

	public void run(){
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
		
		System.out.println("Start Connection");
		connection = Bluetooth.waitForConnection();
		System.out.println("Connection");
		os = connection.openOutputStream();
//		rotationCounter();
		
		lightTest();
		
//		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S1);
//		for(int i = 0; i < 10; i++){
//		sendDistances(sensor);
//		}
		Button.waitForAnyPress();
	}
	
	public void rotationCounter(){
		int cCount = Motor.C.getTachoCount();
		int bCount = Motor.B.getTachoCount();
		
		String message = "Motor B: " + bCount + ", Motor C: " + cCount;
		sendMessage(message);
	}
	
	public void sendMessage(String message){
		try {
			os.write(message.getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lightTest(){
		SensorPort.S2.addSensorPortListener(new LightListener());
		SensorPort.S2.setListenerTolerance(500);
		LightSensor sensor = new LightSensor(SensorPort.S2);
		sensor.setFloodlight(true);
	}
	
	private class LightListener implements SensorPortListener{

		@Override
		public void stateChanged(SensorPort aSource, int aOldValue,
				int aNewValue) {
			sendMessage("Old Light: " + aOldValue + ", New light: " + aNewValue);
		}
		
	}
	
	public void sendDistances(UltrasonicSensor sensor){
		
		sensor.ping();
		int[] distances = new int[8];
		sensor.getDistances(distances);
		String distanceString = "[";
		for(int i : distances){
			distanceString += i + ", ";
		}
		distanceString += "]";

		sendMessage(distanceString);
	}
	
	private class TouchSensorListener implements SensorPortListener{

		@Override
		public void stateChanged(SensorPort arg0, int arg1, int arg2) {
			String message = ("Old Arg: " + arg1 + ", New Arg: " + arg2);
			sendMessage(message);
			
		}
	}
	
}
