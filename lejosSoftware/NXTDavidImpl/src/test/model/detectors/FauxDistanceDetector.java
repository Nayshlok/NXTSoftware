package test.model.detectors;

import model.listeners.DistanceListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 11/14/2014.
 * In project: NXTSoftware
 */
public class FauxDistanceDetector implements Runnable
{

    private final FauxUltraSonicSensor sensor;
    private List<DistanceListener> listeners;

    public FauxDistanceDetector( )
    {
        this.sensor = new FauxUltraSonicSensor();
        this.listeners = new ArrayList<>();
    }

    public List<DistanceListener> getListeners()
    {
        return listeners;
    }

    public void setListeners( List<DistanceListener> listeners )
    {
        this.listeners = listeners;
    }

    public void unregister( DistanceListener distanceListener )
    {
        this.listeners.remove(distanceListener);
    }

    public boolean registerListener( DistanceListener listener )
    {
        return this.listeners.add(listener);
    }

    private void notifyNearObject( int rangeCheck )
    {
        System.out.println("Found object with data range: " + rangeCheck);
    }

    @Override
    public void run()
    {
        while ( !Thread.interrupted() )
        {
            sensor.ping();
            int[] receivedData = new int[8];
            int readIn = sensor.getDistances(receivedData);
            int rangeCheck = receivedData[0];
            if ( rangeCheck < 35 && rangeCheck != 0 )
            {
                this.notifyNearObject(rangeCheck);
            }
        }
        System.out.printf("The DistanceDetector Thread (%s) was interrupted\n", Thread.currentThread());
    }
}
