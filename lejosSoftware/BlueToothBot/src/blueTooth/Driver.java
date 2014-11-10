package blueTooth;

import lejos.nxt.Motor;

public class Driver implements Runnable, BluetoothListener{

	private boolean connected = false;
	private boolean rotation = false;
	private MyMotorListener listener;
	
	@Override
	public void run(){
		while(!connected){
			Thread.yield();
		}
		System.out.println(Motor.B.getSpeed());
		System.out.println(Motor.B.getMaxSpeed());
		Motor.B.setSpeed(360);
		Motor.B.resetTachoCount();
		//Motor.C.resetTachoCount();
		
		Motor.B.forward();
		//Motor.C.rotate(90);
		listener.testEvent();
		while(!rotation){
			Thread.yield();
		}
	}

	@Override
	public void bluetoothConnection() {
		connected = true;
	}
	
	public void register(MyMotorListener listener){
		this.listener = listener;
	}

	@Override
	public void reachedTwoRotations() {
		Motor.B.stop();
		rotation = true;
		listener.stoppedMotor();
	}
	
}
