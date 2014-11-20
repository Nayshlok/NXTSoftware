package model;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import model.listeners.LineListener;


public class LightDetector implements Runnable{

	private LightSensor sensor;
	private List<LineListener> listeners;
	//Good board: 150, bad board: 200
	private int coinMinimum = 31;
	
	public LightDetector() {
		sensor = new LightSensor(SensorPort.S2);
		sensor.calibrateLow();
		listeners = new ArrayList<LineListener>();
	}

	@Override
	public void run() {
		sensor.setFloodlight(true);
		RConsole.println("Coin minimum: " + coinMinimum);
		while(!Thread.interrupted()){
			//RConsole.println("Light value: " + sensor.getLightValue());
			if(sensor.getLightValue() < coinMinimum){
				RConsole.println("about to notify light listeners");
				this.notifyLineDetected();
				while(sensor.getLightValue() < coinMinimum){
					Thread.yield();
				}
			}
		}
	}
	
	public void registerListener(LineListener listener){
		listeners.add(listener);
	}
	
	public void notifyLineDetected(){
		for(LineListener l : listeners){
			l.lineDetected();
		}
	}
	
}
