package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.RegulatedMotor;
import model.listeners.RotationListener;


public class RotationDetector implements Runnable{

	private RegulatedMotor leftSide, rightSide;
	private List<RotationListener> listeners;
	public static final int DEGREE_FOR_REVOLUTION = 1090;
	
	public RotationDetector(){
		leftSide = Motor.B;
		rightSide = Motor.C;
		listeners = new ArrayList<RotationListener>();
	}
	
	public void registerListener(RotationListener listener){
		listeners.add(listener);
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			int leftTach = leftSide.getTachoCount();
			if(leftTach >= DEGREE_FOR_REVOLUTION){
				RConsole.println("Fire revolution event");
				this.notifyFullRevolution();
			}
		}
	}
	
	public void notifyFullRevolution(){
		for(RotationListener l : listeners){
			l.reachedFullCircle();
		}
	}
}
