package model.detectors;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.robotics.RegulatedMotor;
import model.listeners.RotationListener;


public class RotationDetector implements Runnable{

	private NXTMotor leftSide, rightSide;
	private List<RotationListener> listeners;
	public static final int DEGREE_FOR_REVOLUTION = 1090;
	
	public RotationDetector(){
		leftSide = new NXTMotor(MotorPort.B);
		rightSide = new NXTMotor(MotorPort.C);
		listeners = new ArrayList<RotationListener>();
	}
	
	public void registerListener(RotationListener listener){
		listeners.add(listener);
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			int leftTach = leftSide.getTachoCount();
			int rightTach = rightSide.getTachoCount();
			if(leftTach >= DEGREE_FOR_REVOLUTION && rightTach >= DEGREE_FOR_REVOLUTION){
				this.notifyFullRevolution();
				System.out.println("Fire revolution event");
			}
		}
	}
	
	public void notifyFullRevolution(){
		for(RotationListener l : listeners){
			l.reachedFullCircle();
		}
	}
}
