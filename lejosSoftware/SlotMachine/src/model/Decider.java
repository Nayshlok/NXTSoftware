package model;

public class Decider {
	
	        // Has a set group of possible actions to take
	        private enum ActionToTake
	        {
	            ReturnSingleCoin, ReturnJackpot, ReturnNothing
	        }

	        // calculate the probability
	        private ActionToTake calculate()
	        {
	        	/*
	        	 * generate a random number
	        	 * Decide what action to take based on number
	        	 */
	        	
	        	// Generate a random number between 1 and 10	            
	            
	        	// if the number is 1, choose to return a jackpot 
	            // else if the number is 2, 3, or 4, return a single coin
	            // else if the number is greater than 4 but less than 10, return nothing
	        	return null;
	        }

	        // Will decide what action to take based on calculated probability
	        public void decide()
	        {
	            // calculate the action
	            // Based on the chosen action
	        	// fire the notice event (notify relevant parties)
	        }

}
