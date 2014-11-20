package model;

import model.listeners.ReceiverListener;

public class CoinReceiver implements ReceiverListener{

	/*
	 * Listen for events to return the coin or deposit coins
	 */
	
	public void returnCoin(){
		/*
		 * return coin to player
		 */
	}
	
	@Override
	public void loseCoin() {
		/*
		 * Dump coin into bin
		 */
	}
}
