import model.parents.AbstractNXTCar;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

/**
 * Created by Stephen on 10/26/2014.
 * In project: NeumontWork
 */

public class NXTCar extends AbstractNXTCar implements SensorPortListener
{
    /**
     * Turn both wheels forward.
     */
    @Override
    public void forward()
    {
        left.forward();
        right.forward();
    }

    /**
     * Turn both wheels backward.9=
     */
    @Override
    public void reverse()
    {
        left.backward();
        right.backward();
    }

    /**
     * Implements the forward motion on the left wheel and stalls the right.
     * This causes the car to turn rightward.
     */
    public void turnRight()
    {
        left.forward();
        right.flt(true);
    }

    /**
     * Implements the forward motion on the right wheel and stalls the left.
     * This causes the car to turn leftward.
     */
    public void turnLeft()
    {
        left.flt(true);
        right.forward();
    }

    @Override
    public void stateChanged( SensorPort aSource, int aOldValue, int aNewValue )
    {
        if ( aNewValue > 500 )
        {
            try
            {
                System.out.println("Going forward");
                forward();
                Thread.sleep(400);
                System.out.println("Going backward");
                reverse();
                Thread.sleep(400);
                rotate(180);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
