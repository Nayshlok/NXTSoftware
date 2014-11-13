package model.detectors;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import model.Rover;
import model.listeners.DistanceListener;

import java.util.ArrayList;
import java.util.List;


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
			if(distance[0] < 35 && distance[0] != 0){
//				Rover.bluetoothConnection.sendMessage("Distance is: " + distance);
				this.notifyNearObject(distance[0]);
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
