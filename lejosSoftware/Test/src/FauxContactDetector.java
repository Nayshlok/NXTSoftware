
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FauxContactDetector implements Runnable{

	private FauxTouchSensor sensor = new FauxTouchSensor();
	private List<ContactListener> listeners = new ArrayList<ContactListener>();
	
	public FauxContactDetector(){
		
	}
	
	public FauxContactDetector(FauxTouchSensor sensor){
		this.sensor = sensor;
	}

	public void run() {
		while(!Thread.interrupted()){
			try{
				while(!sensor.isPressed()){
					Thread.sleep(20);
				}
				this.notifyButtonPress();
				System.out.println("Can hit");
				while(sensor.isPressed()){
					Thread.sleep(20);
				}
				this.notifyButtonRelease();			
			} catch(InterruptedException te){
				System.out.println("Caught interrupt and rethrowing");
				Thread.currentThread().interrupt();
			}
		}
	}

	public void registerListener(ContactListener listener) {
		listeners.add(listener);	
	}

	public void notifyButtonPress() {
		for(ContactListener l : listeners){
			l.buttonPressed();
		}
	}

	public void notifyButtonRelease() {
		for(ContactListener l : listeners){
			l.buttonReleased();
		}
	}


}
