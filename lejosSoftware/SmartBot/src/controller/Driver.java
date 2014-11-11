package controller;

public class Driver {

	public static void main(String[] args){
		BotBluetooth botBluetooth = new BotBluetooth();
		Thread bluetoothThread = new Thread(botBluetooth, "bluetoothThread");
		bluetoothThread.run();
		Rover remover = new Rover(botBluetooth);
		remover.run();
	}
}
