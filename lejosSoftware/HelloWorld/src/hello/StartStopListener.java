package hello;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;


public class StartStopListener implements SensorPortListener {

	private boolean wheelRunning = true;

	@Override
	public void stateChanged(SensorPort port, int oldArg, int newArg) {
		Motor.A.setAcceleration(3);
		//Motor.A.rotate(360);
	}

}
