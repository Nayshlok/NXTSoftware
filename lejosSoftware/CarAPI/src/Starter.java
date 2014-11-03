
import javax.microedition.sensor.SensorInfo;
import javax.microedition.sensor.SensorListener;

import lejos.nxt.ButtonListener;
import lejos.nxt.SensorPort;
import lejos.nxt.Button;
import lejos.nxt.SensorPortListener;


/**
 * Created by Stephen on 10/26/2014.
 * In project: NeumontWork
 */
public class Starter
{

    public static void main(String[] args) {
		Starter start=  new Starter();
        start.run();
    }
    
    NXTCar car = new NXTCar();
    //total first test= 900
	public void run(){
//		SensorPort.S1.addSensorPortListener(new TouchListen());
//		SensorPort.S1.setListenerTolerance(800);
		NXTCar secondCar = new NXTCar();
		secondCar.faceDirection(300);
		secondCar.faceDirection(90);
		secondCar.faceDirection(200);
		secondCar.faceDirection(0);
		
//		for(int i = 0; i < 1; i++){
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		//Button.waitForAnyPress();
	}
	
	private class TouchListen implements SensorPortListener{

		@Override
		public void stateChanged(SensorPort aSource, int aOldValue,
				int aNewValue) {
			//car.rotate();			
		}
		
	}

}
