package model;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.comm.RConsole;
import model.listeners.ContactListener;
import model.listeners.LineListener;

/**
 * Created by Stephen on 11/18/2014.
 * In project: NXTSoftware
 */
public class SlotMachine
{
	private List<Thread> threadList;
	private boolean isCoinInserted = false;
	
    public SlotMachine()
    {
    	ContactDetector contact = new ContactDetector();
    	LightDetector light = new LightDetector();
    	Decider decider = new Decider();
    	CoinBin coinBin = new CoinBin();
    	CoinReceiver receiver = new CoinReceiver();
    	
    	decider.registerJackpotListener(coinBin);
    	decider.registerReceiverListener(receiver);
    	coinBin.registerListener(decider);
    	contact.registerListener(decider);
    	light.registerListener(decider);
    	
    	threadList = new ArrayList<Thread>();
    	threadList.add(new Thread(contact, "ContactThread"));
    	threadList.add(new Thread(light, "LightThread"));
    	
        // Create the following:
            // Lever, which is composed of a touch sensor and event trigger
            // Coin (touch) Sensor, that notifies on coin insertion
    }

    public void startGame()
    {
		System.out.println("Press any button to begin playing");
		Button.waitForAnyPress();
		RConsole.println("Started");
    	for(Thread t : threadList){
    		t.start();
    	}
    	while(true){
    		Thread.yield();
    	}
    }
}
