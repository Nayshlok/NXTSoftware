package blueTooth;

public class Starter {

	public static void main(String[] args){
		BluetoothConnect connector = new BluetoothConnect();
		Driver driver = new Driver();
		connector.register(driver);
		driver.register(connector);
		Thread bluetoothThread = new Thread(connector, "bluetooth");
		Thread driverThread = new Thread(driver, "Driver");
		
		bluetoothThread.start();
		driverThread.start();
		
		while(true){
			Thread.yield();
		}
	}
}
