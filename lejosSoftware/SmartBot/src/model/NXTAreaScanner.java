package model;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;
import model.parents.RoverEventListener;
import model.parents.StateChanged;

/**
 * Created by Stephen on 10/29/2014.
 * In project: NeumontWork
 */
public class NXTAreaScanner extends NXTRoverEventSource implements StateChanged<RoverEvent>
{
    private final UltrasonicSensor eye;
    private final LightSensor lineDetector;

    public NXTAreaScanner( UltrasonicSensor ultrasonicSensor, SensorPort lightSensorPort )
    {
        this.eye = ultrasonicSensor;
        this.lineDetector = new LightSensor(lightSensorPort);
        lightSensorPort.addSensorPortListener(new SensorPortListener()
        {
            @Override
            public void stateChanged( SensorPort aSource, int aOldValue, int aNewValue )
            {
                if ( aNewValue > 700 ) fireEvent(new RoverEvent(this));
            }
        });
    }

    public void start()
    {
        Runnable eyeRunner = new Runnable()
        {
            @Override
            public void run()
            {
                scan();
                Thread.yield();
            }
        }, lightRunner = new Runnable()
        {
            @Override
            public void run()
            {
                lineDetector.setFloodlight(true);
            }
        };
        Thread t1 = new Thread(lightRunner);
        t1.start();
        new Thread(eyeRunner).start();
    }

    private void scan()
    {
        eye.ping();
        int[] distances = new int[8];
        eye.getDistances(distances);
        int trustedDistance = distances[0];
        if ( trustedDistance < 40 && trustedDistance > 1 )
            fireEvent(new RoverTrashEvent(this, trustedDistance));
    }

    @Override
    public void fireEvent( RoverEvent roverEvent )
    {
        if ( roverEvent instanceof RoverTrashEvent )
        {
            for ( Object listener : subcribers.getListenerList() )
            {
                if ( listener instanceof RoverEventListener )
                {
                    ( (RoverEventListener) listener ).trashFound(eye, ( (RoverTrashEvent) roverEvent ));
                }
            }
        }
        else // It's a line event
        {
            for ( Object listener : subcribers.getListenerList() )
            {
                if ( listener instanceof RoverEventListener )
                {
                    RoverEventListener roverEventListener = (RoverEventListener) listener;
                    roverEventListener.perimeterTripped(this, roverEvent);
                }
            }
        }
    }

}
