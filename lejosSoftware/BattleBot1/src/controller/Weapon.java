package controller;

import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
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
			castWhip();
			Delay.msDelay(450);
			withdrawWhip();
			attacking = false;
		}

		/**
		 * Pull the whip back to rest position.
		 */
		private void withdrawWhip() {
			Motor.A.setAcceleration(10000);
			Motor.A.rotate(75);
		}

		/**
		 * Launch out the whip-arm with the intent of decapitating a Lego person
		 */
		private void castWhip() {
			Motor.A.setAcceleration(12000);
			Motor.A.setSpeed(Motor.A.getMaxSpeed());
			Motor.A.rotate(-65);
		}
	}
}
