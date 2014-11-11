package model;

import model.parents.RoverEventListener;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public abstract class NXTRoverEventSource
{
    protected EventListenerList subcribers = new EventListenerList();

    /**
     * Give this object more things to notify when the even occurs
     * @param eventListeners are the object we would like to subscribe
     */
    public void addRoverEventListeners( RoverEventListener... eventListeners )
    {
        for ( RoverEventListener eventListener : eventListeners )
        {
            subcribers.add(RoverEventListener.class, eventListener);
        }
    }

    /**
     * Detach the event subscribers from this object
     * @param eventListeners are the object we would like to de-subscribe
     */
    public void removeRoverEventListeners( RoverEventListener... eventListeners )
    {
        for ( RoverEventListener eventListener : eventListeners )
        {
            subcribers.remove(RoverEventListener.class, eventListener);
        }
    }

    public EventListenerList getSubcribers()
    {
//        The following will have to go somewhere eventually
//        Arrays.asList(subcribers.getSubcribers(model.NXTAreaScanner.class)).clear();
        return subcribers;
    }
}
