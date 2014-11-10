package blueTooth;

import java.io.IOException;
import java.io.OutputStream;

import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;

public class BluetoothConnect implements Runnable, MyMotorListener{

	
	private NXTConnection connection;
	private OutputStream os;
	private boolean motorRunning = true;
	private BluetoothListener listener;
	
	@Override
	public void run(){
		System.out.println("Start Connect");
		connection = Bluetooth.waitForConnection();
		System.out.println("Connection");
		os = connection.openOutputStream();
		
		listener.bluetoothConnection();
		while(motorRunning){
			if(Motor.B.getTachoCount() >= 720){
				listener.reachedTwoRotations();
			}
		}
		sendMessage("Tacho Count: " + Motor.B.getTachoCount());
	}

	public void register(BluetoothListener listener){
		this.listener = listener;
	}
	
	public void sendMessage(String message){
		System.out.println("Sending message");
		try {
			os.write(message.getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void testEvent() {
		System.out.println("Recieved Event");
		sendMessage("Tacho count: " + Motor.B.getTachoCount());
	}

	@Override
	public void stoppedMotor() {
		motorRunning = false;
		
	}
}
