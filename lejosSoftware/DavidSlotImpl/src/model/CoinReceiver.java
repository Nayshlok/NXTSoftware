package model;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;
import model.listeners.CoinListener;
import model.listeners.ReceiverListener;

public class CoinReceiver implements ReceiverListener{

	private RegulatedMotor block = Motor.A; 
	private RegulatedMotor cover = Motor.B;
	
	@Override
	public void returnCoin(){
		block.rotate(-90);
		Delay.msDelay(250);
		block.rotate(90);
	}
	
	@Override
	public void loseCoin() {
		cover.rotate(90);
		block.rotate(-70);
		Delay.msDelay(250);
		block.rotate(70);
		cover.rotate(-90);
	}
}
