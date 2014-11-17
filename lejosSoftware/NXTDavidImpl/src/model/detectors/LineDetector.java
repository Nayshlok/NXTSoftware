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
	//Good board: 150, bad board: 200
	private final int LINE_THRESHOLD = 200;
	
	public LineDetector() {
		sensor = new LightSensor(SensorPort.S2, true);
		sensor.calibrateHigh();
		listeners = new ArrayList<LineListener>();
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			//RConsole.println("Light value: " + sensor.getLightValue());
			if(sensor.getLightValue() < LINE_THRESHOLD){
				RConsole.println("about to notify light listeners");
				this.notifyLineDetected();
				while(sensor.getLightValue() < LINE_THRESHOLD){
					Thread.yield();
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
