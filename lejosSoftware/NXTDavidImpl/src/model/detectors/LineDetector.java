package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import model.Rover;
import model.listeners.LineListener;


public class LineDetector implements Runnable{

	private LightSensor sensor;
	private List<LineListener> listeners;
	
	public LineDetector() {
		sensor = new LightSensor(SensorPort.S2);
		sensor.setFloodlight(true);
		listeners = new ArrayList<LineListener>();
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			try{
				while(sensor.getLightValue() < 20){
					Thread.sleep(20);
				}
				System.out.println("Line: " + sensor.getLightValue());
				Rover.bluetoothConnection.sendMessage("Light Value: " + sensor.getLightValue());
				this.notifyLineDetected();
				while(sensor.getLightValue() > 20){
					Thread.sleep(20);
				}
			} catch (InterruptedException te){
				Thread.currentThread().interrupt();
			}
			
			Rover.bluetoothConnection.sendMessage("Light Value: " + sensor.getLightValue());
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
