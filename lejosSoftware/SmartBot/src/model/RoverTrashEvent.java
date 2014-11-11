package model;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public class RoverTrashEvent extends RoverEvent
{
    private final int distanceToTrash;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RoverTrashEvent( Object source, int distance )
    {
        super(source);
        this.distanceToTrash = distance;
    }

    public int getDistanceToTrash()
    {
        return distanceToTrash;
    }
}
