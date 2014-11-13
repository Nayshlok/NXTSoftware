package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import model.Rover;
import model.listeners.LineListener;


public class LineDetector implements Runnable{

	private LightSensor sensor;
	private List<LineListener> listeners;
	
	public LineDetector() {
		sensor = new LightSensor(SensorPort.S2, true);
		sensor.calibrateHigh();
		listeners = new ArrayList<LineListener>();
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			if(sensor.getLightValue() < 80){
				this.notifyLineDetected();
				while(sensor.getLightValue() < 80){
					Thread.yield();
				}
			}
//			while(sensor.getLightValue() > 80){
//				Thread.yield();
//			}
//			System.out.println("Line: " + sensor.getLightValue());
//			this.notifyLineDetected();
//			Sound.playNote(Sound.FLUTE, 1800, 500);
//			while(sensor.getLightValue() > 80){
//				Thread.yield();
//			}
//			Sound.playNote(Sound.PIANO, 800, 500);
//			System.out.println("past line detection");
		}
	}
	
	public void registerListener(LineListener listener){
		listeners.add(listener);
	}
	
	public void notifyLineDetected(){
		Sound.playNote(Sound.PIANO, 700, 500);
		for(LineListener l : listeners){
			l.lineDetected();
		}
	}
	
}
