package detector;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.RegulatedMotor;
import listener.RotationListener;


public class RotationDetector implements Runnable{

	private RegulatedMotor leftSide, rightSide;
	private List<RotationListener> listeners;
	public static final int DEGREE_FOR_REVOLUTION = 720;
	private boolean firedEvent = false;
	
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
			if(Math.abs(leftTach) >= DEGREE_FOR_REVOLUTION && !firedEvent){
				firedEvent = true;
				this.notifyFullRevolution();
			}
		}
	}
		
	public void resetRotation(){
		leftSide.resetTachoCount();
		rightSide.resetTachoCount();
		firedEvent = false;
	}
	
	public void notifyFullRevolution(){
		for(RotationListener l : listeners){
			l.reachedFullCircle();
		}
	}
}
