package blueTooth;

import java.io.IOException;
import java.io.OutputStream;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class BotBluetooth implements Runnable{

	private NXTConnection connection;
	private OutputStream os;
	
	public void sendMessage(String message){
		try {
			os.write(message.getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		connection = Bluetooth.waitForConnection();
		os = connection.openOutputStream();
	}
}
