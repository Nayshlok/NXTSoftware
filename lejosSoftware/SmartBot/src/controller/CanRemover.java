package controller;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class CanRemover {

	private NXTCar car;
	private BotBluetooth bluetooth;
	private UltrasonicSensor ultraSonic = new UltrasonicSensor(SensorPort.S3);
	private LightSensor lightSensor = new LightSensor(SensorPort.S2);
	private TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
	private int pushCount;
	private boolean crossedBlackLine = false;
	
	public CanRemover(BotBluetooth bluetooth){
		car = new NXTCar();
		this.bluetooth = bluetooth;
		SensorPort.S2.addSensorPortListener(new LightSensorListener());
	}
	
	public void run() {
		while(pushCount < 3){
			List<Integer> objectsInRange = fullCircleSearch();
			if(!objectsInRange.isEmpty()){
				car.faceDirection(objectsInRange.get(0));
				startLightSensor();
				while(!crossedBlackLine){
					car.forward();
				}
				if(touchSensor.isPressed()){
					pushCount++;
				}
				car.stop();
			}
		}
	}
	
	public List<Integer> fullCircleSearch(){
		List<Integer> objectsInRange = new ArrayList<>();
		for(int i = 0; i < 12; i++){
			car.rotate(i * 30);
			int object = scan();
			if(object != -1){
				objectsInRange.add(object);
			}
		}
		return objectsInRange;
	}
	
	public int scan(){
		ultraSonic.ping();
		int[] distances = new int[8]; 
		ultraSonic.getDistances(distances);
		return (distances[0] < 40) ? distances[0] : -1;
	}
	
	public void startLightSensor(){
		lightSensor.setFloodlight(true);
	}
	
	private class LightSensorListener implements SensorPortListener{

		@Override
		public void stateChanged(SensorPort aSource, int aOldValue,
				int aNewValue) {

			if(aNewValue > 700){
				crossedBlackLine = true;
			}
		}
		
	}
}
