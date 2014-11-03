package model.parents;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * Created by Stephen on 10/29/2014.
 * In project: NeumontWork
 */
public abstract class AbstractNXTCar
{
    protected NXTRegulatedMotor left = Motor.B;
    protected NXTRegulatedMotor right = Motor.C;

    public abstract void forward();

    public abstract void reverse();

    public void rotate(int angle) {
        while ( left.getLimitAngle() < angle )
        {
            left.rotate(angle);
            right.rotate(-angle);
        }
    }
}
