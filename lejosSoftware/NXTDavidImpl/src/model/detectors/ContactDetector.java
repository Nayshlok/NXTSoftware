package model.detectors;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import model.listeners.ContactListener;


public class ContactDetector implements Runnable{

	private TouchSensor sensor;
	private List<ContactListener> listeners;
	
	public ContactDetector() {
		sensor = new TouchSensor(SensorPort.S1);
		listeners = new ArrayList<ContactListener>();
	}
	
	@Override
	public void run(){
		while(!Thread.interrupted()){
			try{
				while(!sensor.isPressed()){
					Thread.sleep(20);
				}
				this.notifyButtonPress();
				while(sensor.isPressed()){
					Thread.sleep(20);
				}
				this.notifyButtonRelease();			
			} catch(InterruptedException te){
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void registerListener(ContactListener listener){
		listeners.add(listener);
	}
	
	public void notifyButtonPress(){
		for(ContactListener l : listeners){
			l.buttonPressed();
		}
	}
	
	public void notifyButtonRelease(){
		for(ContactListener l : listeners){
			l.buttonReleased();
		}
	}
}
