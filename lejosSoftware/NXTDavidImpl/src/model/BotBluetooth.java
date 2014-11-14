package model;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class BotBluetooth implements Runnable{

	private NXTConnection connection;
	private OutputStream os;
	private Queue<String> pendingMessages = new ArrayDeque<>();
	
	public void sendMessage(String message){
		if(os == null){
			pendingMessages.add(message);
		}
		else {
			try {
				os.write(message.getBytes());
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void nonThreadRun(){
		connection = Bluetooth.waitForConnection();
		os = connection.openOutputStream();
		this.sendMessage("hi");
	}
	
	@Override
	public void run() {
		connection = Bluetooth.waitForConnection();
		os = connection.openOutputStream();
		this.sendMessage("hi");
		
		while(true){
			while(!pendingMessages.isEmpty() && os != null){
				sendMessage(pendingMessages.poll().toString());
			}
			Thread.yield();
		}
	}
}
