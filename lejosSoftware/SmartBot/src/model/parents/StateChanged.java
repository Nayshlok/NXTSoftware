package model.parents;

import java.util.EventObject;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public interface StateChanged<T extends EventObject>
{
    void fireEvent( T event);
}
