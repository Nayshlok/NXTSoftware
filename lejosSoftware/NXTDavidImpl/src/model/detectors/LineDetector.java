package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
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
			if(sensor.getLightValue() < 150){
				RConsole.println("about to notify light listeners");
				this.notifyLineDetected();
				while(sensor.getLightValue() < 150){
					Thread.yield();
					RConsole.println("stick on line, reading: " + sensor.getLightValue());
				}
			}
//			while(sensor.getLightValue() > 80){
//				Thread.yield();
//			}
//			RConsole.println("Line: " + sensor.getLightValue());
//			this.notifyLineDetected();
//			Sound.playNote(Sound.FLUTE, 1800, 500);
//			while(sensor.getLightValue() > 80){
//				RConsole.println("Stuck in second light loop");
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
		for(LineListener l : listeners){
			l.lineDetected();
		}
	}
	
}
