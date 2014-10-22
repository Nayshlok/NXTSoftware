import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.I2CPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.OpticalDistanceSensor;


public class SonarTester {

	public static void main(String[] args) {
		SonarTester tester = new SonarTester();
		tester.run();
	}
	
	private boolean stopRunning = false;
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S1);
	
	public SonarTester(){
		sensor.setMode(UltrasonicSensor.MODE_PING);
	}
	
	public void run(){
		System.out.println("Begin test.");
		Button enter = Button.ENTER;
		enter.addButtonListener(new PingButton());
		Button escape = Button.ESCAPE;
		escape.addButtonListener(new EscapeButton());
		
		Button.LEFT.waitForPress();
	}

	private class EscapeButton implements ButtonListener{

		@Override
		public void buttonPressed(Button arg0) {
			stopRunning = true;			
		}

		@Override
		public void buttonReleased(Button arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class PingButton implements ButtonListener{

		@Override
		public void buttonPressed(Button arg0) {
			
		}

		@Override
		public void buttonReleased(Button arg0) {
			sensor.ping();
			System.out.println(sensor.getDistance() + ", in " + sensor.getUnits());
		}
		
	}
	
}
