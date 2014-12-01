package controller;

import lejos.nxt.Motor;
import lejos.util.Delay;

public class Weapon {

	private boolean attacking;
	
	public void beginAttack(){
		if(!attacking){
			Thread attackThread = new Thread(new WeaponAttack());
			attackThread.start();
		}
	}
	
	private class WeaponAttack implements Runnable{
		
		@Override
		public void run(){
			attacking = true;
			Motor.A.setAcceleration(12000);
			Motor.A.setSpeed(Motor.A.getMaxSpeed());
			Motor.A.rotate(-65);
			Delay.msDelay(1000);
			Motor.A.setAcceleration(6000);
			Motor.A.rotate(125);
			attacking = false;
		}
	}
}
