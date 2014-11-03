import model.NXTRoverEventSource;
import model.RoverEvent;
import model.parents.RoverEventListener;
import model.parents.StateChanged;

/**
 * Created by Stephen on 10/29/2014.
 * In project: NeumontWork
 */
public class NXTAreaScanner extends NXTRoverEventSource implements StateChanged<RoverEvent>
{
    public void detectedTrash(RoverEvent roverEvent){

        fireEvent(roverEvent);
    }

    @Override
    public void fireEvent( RoverEvent roverEvent )
    {
        for ( java.lang.Object listener : subcribers.getListenerList() )
        {
            if ( listener.getClass().isInstance(RoverEventListener))
            {
                ( (RoverEventListener) listener ).trashFound(listener, roverEvent);
            }
        }
    }

}
