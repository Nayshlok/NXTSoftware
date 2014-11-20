import lejos.nxt.comm.RConsole;
import model.SlotMachine;

/**
 * Created by Stephen on 11/18/2014.
 * In project: NXTSoftware
 */
public class Starter
{
    public static void main( String[] args )
    {
    	RConsole.open();
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.startGame();
    }
}
