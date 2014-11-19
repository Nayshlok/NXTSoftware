package model;

import lejos.nxt.Motor;

/**
 * Created by Stephen on 11/19/2014.
 * In project: NXTSoftware
 */
public class CoinBin // Coin receptacle
{
    Motor forRotation;

    // Is told to dump by the slot machine:
        // All
        // One
        // or no coins

    void dumpAll()
    {
        // Rotate the motor clockwise a full rotation, spilling all the coins
        // Subtract all the coin from the bank
    }

    void dumpOne()
    {
        // Rotate the motor clockwise a minute degree, spill a single coin
        // Subtract one coin from the bank
    }
}
