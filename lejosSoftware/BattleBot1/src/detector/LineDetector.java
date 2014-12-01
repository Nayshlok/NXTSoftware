package detector;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import listener.LineListener;


public class LineDetector implements Runnable{

	private LightSensor sensor;
	private List<LineListener> listeners;
	//Good board: 150, bad board: 200
	private int lineThreshold = 200;
	
	public LineDetector() {
		sensor = new LightSensor(SensorPort.S2, true);
		listeners = new ArrayList<LineListener>();
	}

	@Override
	public void run() {
		sensor.calibrateLow();
		lineThreshold = sensor.getLightValue() + 10;
		while(!Thread.interrupted()){
			if(sensor.getLightValue() > lineThreshold){
				this.notifyLineDetected();
				while(sensor.getLightValue() > lineThreshold){
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
