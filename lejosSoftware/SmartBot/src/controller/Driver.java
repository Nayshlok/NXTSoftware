package controller;

public class Driver {

	public static void main(String[] args){
		BotBluetooth botBluetooth = new BotBluetooth();
		Thread bluetoothThread = new Thread(botBluetooth, "bluetoothThread");
		bluetoothThread.run();
		CanRemover remover = new CanRemover(botBluetooth);
		remover.run();
	}
}
