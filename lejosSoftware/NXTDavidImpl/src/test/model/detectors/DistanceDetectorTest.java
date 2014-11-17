package test.model.detectors;

import model.listeners.DistanceListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DistanceDetector Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Nov 13, 2014</pre>
 */
public class DistanceDetectorTest
{

    FauxDistanceDetector testDetector;
    private final DistanceListener listener = new DistanceListener()
    {
        @Override
        public void objectDetected( int distance )
        {
            System.out.println("Object detected in test");
        }
    };

    @Before
    public void before() throws Exception
    {
        testDetector = new FauxDistanceDetector();
//        testDetector.registerListener(new DistanceListener()
//        {
//            @Override
//            public void objectDetected( int distance )
//            {
//                System.out.println("Object detected at " + distance );
//            }
//        });
    }

    @After
    public void after() throws Exception
    {
        testDetector = null;
    }

    /**
     * Method: run()
     */
    @Test
    public void testRun() throws Exception
    {
        Assert.assertFalse(Thread.holdsLock(testDetector));
        Thread closer = new Thread(testDetector);
        closer.start();
        try
        {
            Assert.assertTrue("Aforementioned thread should be alive",
                    closer.isAlive());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            closer.interrupt();
        }
        finally
        {
            if (closer.isAlive()) closer.interrupt();
        }
    }

    /**
     * Method: registerListener(DistanceListener listener)
     */
    @Test
    public void testRegisterListener() throws Exception
    {
        Assert.assertEquals(false, testDetector.getListeners().size() > 0);
        testDetector.registerListener(listener);

        Assert.assertTrue(testDetector.getListeners().size() > 0);
        System.out.println("DistanceDetectorTest.testRegisterListener: \n\t" +
                testDetector.getListeners().get(0).getClass().getName());

        Assert.assertTrue(listener.getClass().asSubclass(DistanceListener.class).isAnonymousClass());

//        Assert.assertTrue(testDetector.getListeners().get(0)
//                .getClass().isInterface());

//        Assert.assertTrue("There should be equivalent types.", listener.getClass() == (DistanceListener.class));
    }

    /**
     * Method: unregister(DistanceListener listener)
     */
    @Test
    public void testUnregister() throws Exception
    {
        if (testDetector.registerListener(new DistanceListener()
        {
            @Override
            public void objectDetected( int distance )
            {
                System.out.println("Detected by the anonymous listener");
            }
        })) {

            Assert.assertTrue("There should be nothing", testDetector.getListeners().size() >= 0);
        }

        testDetector.unregister(testDetector.getListeners().get(0));

        Assert.assertTrue("Post register, the list should be empty",
                testDetector.getListeners().size() == 0);
    }

    /**
     * Method: getSensor()
     */
    @Test
    public void testGetSensor() throws Exception
    {
        Assert.assertNotNull("The listener list should never be null",
                testDetector.getListeners());
    }

    /**
     * Method: getListeners()
     */
    @Test
    public void testGetListeners() throws Exception
    {
        testDetector.registerListener(listener);
        Assert.assertFalse("Listeners should not be empty", testDetector.getListeners().isEmpty());
    }

    /**
     * Method: notifyNearObject(int distance)
     */
    @Test
    public void testNotifyNearObject() throws Exception
    {
//TODO: Test goes here...
    }


} 
