package model;

import java.util.List;

import lejos.nxt.Motor;
import model.listeners.CoinListener;

/**
 * Created by Stephen on 11/19/2014.
 * In project: NXTSoftware
 */
public class CoinBin // Coin receptacle
{
    private Motor forRotation;
    private int numberOfCoins;
    private List<CoinListener> listeners;
    // Is told to dump by the slot machine:
        // All
        // One
        // or no coins

    public void coinDeposited(){
    	//Adds a coin to number of stored coins
    	//Notify lsiteners of full bank
    }
    
    public void dumpAll()
    {
        // Rotate the motor clockwise a full rotation, spilling all the coins
        // Subtract all the coin from the bank
    	//notify listeners of empty bank
    }
}
