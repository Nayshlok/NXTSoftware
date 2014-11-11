package model.detectors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.listeners.TimerListener;


public class RoverTimer implements Runnable{

	private List<TimerListener> listeners;
	private long startTime;
	
	public RoverTimer() {
		startTime = System.currentTimeMillis();
		listeners = new ArrayList<TimerListener>();
	}
	
	public void registerListener(TimerListener listener){
		listeners.add(listener);
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			if((System.currentTimeMillis() - startTime) >= 120000){
				this.notifyTimeUp();
			}
		}
	}
	
	public void notifyTimeUp(){
		for(TimerListener l : listeners){
			l.timeUp();
		}
	}
	
	public String getHumanReadableTime(){		
		int minutes = (int) (((System.currentTimeMillis() - startTime) / (60 * 1000)) % 60);
		int seconds = (int) (((System.currentTimeMillis() - startTime) / (1000)) % 60);

		return minutes +  ":" + seconds;
	}
}
