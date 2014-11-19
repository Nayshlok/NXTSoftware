package model;

/**
 * Created by Stephen on 11/18/2014.
 * In project: NXTSoftware
 */
public class SlotMachine
{
    public SlotMachine()
    {
        // Create the following:
            // Lever, which is composed of a touch sensor and event trigger
            // Coin (touch) Sensor, that notifies on coin insertion
    }

    public void startGame()
    {
        // Prompt the user
        // Wait forever for button press
        // On button press, initiate game logic:
            // Link listeners
            // Run new threads listener
        // Yield the thread
    }

    private static class Decider
    {
        // Has a set group of possible actions to take
        private enum ActionToTake
        {
            ReturnSingleCoin, ReturnJackpot, ReturnNothing
        }

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
            // calculate the action
            // fire the notice event (notify relevant parties)
        }
    }
}
