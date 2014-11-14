import controller.NXTCar;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import model.NXTAreaScanner;
import model.RoverEvent;
import model.RoverTrashEvent;
import model.parents.RoverEventListener;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public class Rover implements RoverEventListener
{
    private NXTCar car = new NXTCar();
//    private BotBluetooth bluetooth;
    private UltrasonicSensor ultraSonic = new UltrasonicSensor(SensorPort.S3);
//    private LightSensor lightSensor = new LightSensor(SensorPort.S2);
    private TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
    private boolean touching = false;

    public void DoCleanUp()
    {
        /*
            Rover searches for cans
            foreach can found
                The rover pushes the can out of the circle
            Celebrate victory
         */

        NXTAreaScanner nxtAreaScanner = new NXTAreaScanner(ultraSonic, SensorPort.S2);

        nxtAreaScanner.addRoverEventListeners(this);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while ( true )
                {
                    car.rotate(360, true);
                    Thread.yield();
                }
            }
        }).start();

        nxtAreaScanner.start();
    }

    @Override
    public void perimeterTripped( Object sender, RoverEvent roverEvent )
    {

    }

    @Override
    public void trashTouched( Object sender, RoverEvent roverEvent )
    {
        this.touching = true;
    }

    @Override
    public void leftLandingPerimeter( Object sender, RoverEvent roverEvent )
    {
        car.reverse();
        car.rotate(90);
    }

    @Override
    public void enteredLandingPerimeter( Object sender, RoverEvent roverEvent )
    {

    }

    @Override
    public void trashFound( Object sender, RoverTrashEvent roverEvent )
    {
        car.forward();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        ( (NXTAreaScanner) sender ).publicScan();
    }
}
