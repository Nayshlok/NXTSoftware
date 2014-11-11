import controller.NXTCar;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import model.NXTAreaScanner;

/**
 * Created by Stephen on 10/30/2014.
 * In project: NeumontWork
 */
public class Rover
{
    private NXTCar car = new NXTCar();
//    private BotBluetooth bluetooth;
    private UltrasonicSensor ultraSonic = new UltrasonicSensor(SensorPort.S3);
//    private LightSensor lightSensor = new LightSensor(SensorPort.S2);
    private TouchSensor touchSensor = new TouchSensor(SensorPort.S1);

    public void DoCleanUp()
    {
        /*
            Rover searches for cans
            foreach can found
                The rover pushes the can out of the circle
            Celebrate victory
         */

        NXTAreaScanner nxtAreaScanner = new NXTAreaScanner(ultraSonic, SensorPort.S2);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while ( true )
                {
                    car.rotate(10);
                    Thread.yield();
                }
            }
        }).start();

        nxtAreaScanner.start();
    }


}
