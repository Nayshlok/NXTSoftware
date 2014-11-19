package model;

import model.event.EventListener;
import model.event.EventListenerList;

/**
 * Created by Stephen on 11/18/2014.
 * In project: NXTSoftware
 */
public class Lever implements EventListener
{
    EventListenerList listenerList;

    // Await TouchSensor change
    // Fire notice event to listeners

    public void detectChange()
    {
        // While values are an effective touch
            // fire the event
            // yield the Thread
    }

    @Override
    public void fireEvent()
    {
        // Construct the event object (source reference and trigger)
        // For each listener
            // call
    }
}
