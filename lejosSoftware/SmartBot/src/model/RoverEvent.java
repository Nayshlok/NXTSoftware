package model;

import java.util.EventObject;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public class RoverEvent extends EventObject
{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RoverEvent( Object source )
    {
        super(source);
    }

}
