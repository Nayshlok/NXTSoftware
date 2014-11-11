package model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class BotBluetooth implements Runnable{

	private NXTConnection connection;
	private OutputStream os;
	private Queue<String> pendingMessages = new Queue<String>();
	
	public void sendMessage(String message){
		if(os == null){
			pendingMessages.addElement(message);
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

	@Override
	public void run() {
		connection = Bluetooth.waitForConnection();
		os = connection.openOutputStream();
		this.sendMessage("hi");
		
		while(true){
			while(!pendingMessages.empty() && os != null){
				sendMessage(pendingMessages.pop().toString());
			}
			Thread.yield();
		}
	}
}
