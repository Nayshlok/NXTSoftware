package blueTooth;

import java.io.IOException;
import java.io.OutputStream;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class NXTSideBluetooth {

	public static void main(String[] args){
		NXTSideBluetooth transmit = new NXTSideBluetooth();
		transmit.run();
	}
	
	private NXTConnection connection;
	private OutputStream os;

	public void run(){
		System.out.println("Start Connection");
		Button.waitForAnyPress();
		connection = Bluetooth.waitForConnection();
		Button.waitForAnyPress();
		System.out.println("Connection");
		os = connection.openOutputStream();
		SensorPort.S1.addSensorPortListener(new TouchSensorListener());
		System.out.println("Waiting for input.");
		Button.waitForAnyPress();
	}
	
	private class TouchSensorListener implements SensorPortListener{

		@Override
		public void stateChanged(SensorPort arg0, int arg1, int arg2) {
			try {
				String message = ("Old Arg: " + arg1 + ", New Arg: " + arg2 + "\r\n\r\n");
				os.write(message.getBytes());
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
