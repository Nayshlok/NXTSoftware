
import model.RoverEvent;
import model.parents.StateChanged;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public class Rover extends NXTCar implements StateChanged<RoverEvent>
{

    private void pushCan()
    {
    }

    @Override
    public void fireEvent( RoverEvent roverEvent )
    {
    }
}
