package test.model.detectors;

import java.util.List;

import org.junit.Test;

import model.listeners.ContactListener;

public class FauxContactDetector{

	private FauxTouchSensor sensor;
	private List<ContactListener> listeners;
	
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
