import java.util.List;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class DistanceDetector {

	public DistanceDetector(){
		
	}
	
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S3);
	private List<DistanceListener> listeners;
	
	public void registerListener(DistanceListener listener){
		/*
		 * add listener to listeners
		 */
	}
	
	public void scan(){
		/*
		 Loop forever
		 	Sensor ping and store values
		 	Check stored values for one that can close enough to be a can
		 	if distance is close
		 		Notify listeners that something was found
		 */
	}
	
	public void register(DistanceListener listener){
		/*
		 * Add listener to list
		 */
	}
	
	public void unregister(DistanceListener listener){
		/*
		 * remove listener from list if they are on it.
		 */
	}
	
	public void notifyAllListener(){
		/*
		 * Go through list of listeners and send the distance to nearby object.
		 */
	}
	
	
}
