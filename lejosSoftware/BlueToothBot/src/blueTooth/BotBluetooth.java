package blueTooth;

import java.io.IOException;
import java.io.OutputStream;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class BotBluetooth {

	private NXTConnection connection;
	private OutputStream os;
	
	public void instantiateConnection(){
		connection = Bluetooth.waitForConnection();
		os = connection.openOutputStream();
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
}
