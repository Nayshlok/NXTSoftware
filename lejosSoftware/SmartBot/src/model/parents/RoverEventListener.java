package model.parents;

import model.RoverEvent;

import java.util.EventListener;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public interface RoverEventListener extends EventListener
{
    public void perimeterTripped( Object sender, RoverEvent roverEvent );

    public void trashTouched( Object sender, RoverEvent roverEvent );

    public void leftLandingPerimeter( Object sender, RoverEvent roverEvent );

    public void enteredLandingPerimeter( Object sender, RoverEvent roverEvent );

    void trashFound( Object sender, RoverEvent roverEvent );
}
