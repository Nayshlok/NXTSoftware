package model;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;
import model.listeners.CoinListener;
import model.listeners.DepositListener;
import model.listeners.JackpotListener;

/**
 * Created by Stephen on 11/19/2014.
 * In project: NXTSoftware
 */
public class CoinBin implements JackpotListener, DepositListener// Coin receptacle
{
	private RegulatedMotor forRotation = Motor.C;
	private int coins = 0;
	private List<CoinListener> listeners = new ArrayList<CoinListener>();
    // Is told to dump by the slot machine:
        // All
        // One
        // or no coins

	public void registerListener(CoinListener listener){
		listeners.add(listener);
	}

	@Override
	public void coinDeposited() {
		coins++;
		RConsole.println("Coin received:" + coins);
		if(coins >= 4){
			notifyFullBank();
		}
	}

	@Override
	public void jackpot() {
		RConsole.println("Got jackpot");
		forRotation.rotate(-45);
		Delay.msDelay(250);
		forRotation.rotate(45);
		notifyEmptyBank();
	}
	
	public void notifyFullBank(){
		for(CoinListener l : listeners){
			l.fullBank();
		}
	}
	
	public void notifyEmptyBank(){
		for(CoinListener l : listeners){
			l.emptyBank();
		}
	}
}
