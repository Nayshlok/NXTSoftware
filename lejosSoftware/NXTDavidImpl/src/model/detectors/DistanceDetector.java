package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.RConsole;
import model.listeners.DistanceListener;


public class DistanceDetector implements Runnable{

	private UltrasonicSensor sensor;
	private List<DistanceListener> listeners;
	
	public DistanceDetector(){
		sensor = new UltrasonicSensor(SensorPort.S3);
		listeners = new ArrayList<DistanceListener>();
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			sensor.ping();
			int[] distance = new int[8];
			int readIn = sensor.getDistances(distance);
			int validDistance = distance[0];
			if(validDistance < 50 && validDistance != 0){
				this.notifyNearObject(validDistance);
			}
		}
	}
	
	public void registerListener(DistanceListener listener){
		listeners.add(listener);
	}
	
	public void unregister(DistanceListener listener){
		listeners.remove(listener);
	}

	public UltrasonicSensor getSensor()
	{
		return sensor;
	}

	public List<DistanceListener> getListeners()
	{
		return listeners;
	}

	public void notifyNearObject(int distance){
		for(DistanceListener l : listeners){
			l.objectDetected(distance);
		}
	}	
	
}
