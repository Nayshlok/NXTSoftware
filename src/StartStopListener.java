import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;


public class StartStopListener implements SensorPortListener {

	private boolean wheelRunning = true;

	@Override
	public void stateChanged(SensorPort port, int oldArg, int newArg) {
		if(newArg > 1000){
			if(wheelRunning){
				MotorPort.A.controlMotor(60, MotorPort.FORWARD);
				wheelRunning = false;
			}
			else{
				MotorPort.A.controlMotor(0, MotorPort.STOP);;
				wheelRunning = true;
			}
		}
	}

}
