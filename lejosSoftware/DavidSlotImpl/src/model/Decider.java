package model;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.comm.RConsole;
import model.listeners.CoinListener;
import model.listeners.ContactListener;
import model.listeners.JackpotListener;
import model.listeners.LineListener;
import model.listeners.ReceiverListener;

public class Decider implements ContactListener, LineListener, CoinListener{
        // Has a set group of possible actions to take
        public enum ActionToTake
        {
            ReturnSingleCoin, ReturnJackpot, ReturnNothing
        }

        private boolean coinInserted = false;
        private boolean fullBank = false;
        private List<JackpotListener> jackpotListeners = new ArrayList<JackpotListener>();
        private List<ReceiverListener> receiverListeners = new ArrayList<ReceiverListener>();
        
        // calculate the probability
        private ActionToTake calculate()
        {
            double v = Math.random() * 10;
            int determinant = ( (int) Math.floor(v) ) + 1;

            if ( determinant < 4 ) return ActionToTake.ReturnSingleCoin;
            else if ( determinant > 3 && determinant < 5 ) return ActionToTake.ReturnJackpot;
            else return ActionToTake.ReturnNothing;
        }

        // Will decide what action to take based on calculated probability
        public void decide()
        {
        	RConsole.println("Deciding");
            switch(calculate()){
			case ReturnJackpot:
				notifyJackpot();
				break;
			case ReturnNothing:
				if(fullBank){
					notifyReturn();
				}
				else{
					notifyLoss();
				}
				break;
			case ReturnSingleCoin:
				notifyReturn();
				break;
			default:
				break;
            
            }
        }

        public void registerJackpotListener(JackpotListener listener){
        	this.jackpotListeners.add(listener);
        }
        
        public void registerReceiverListener(ReceiverListener listener){
        	this.receiverListeners.add(listener);
        }
        
		@Override
		public void lineDetected() {
			coinInserted = true;
		}

		@Override
		public void buttonPressed() {
			if(coinInserted){
				decide();
				coinInserted = false;
			}
		}

		@Override
		public void buttonReleased() {
			// TODO Auto-generated method stub
			
		}
		
		public void notifyJackpot(){
			for(ReceiverListener l : receiverListeners){
				l.loseCoin();
			}
			for(JackpotListener l : jackpotListeners){
				l.jackpot();
			}
		}
		
		public void notifyReturn(){
			for(ReceiverListener l : receiverListeners){
				l.returnCoin();
			}
		}
		
		public void notifyLoss(){
			for(ReceiverListener l : receiverListeners){
				l.loseCoin();
			}
		}

		@Override
		public void fullBank() {
			fullBank = true;
		}

		@Override
		public void emptyBank() {
			fullBank = false;
		}
}
