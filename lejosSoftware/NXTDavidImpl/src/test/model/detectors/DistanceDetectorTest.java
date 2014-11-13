package test.model.detectors; 

import model.detectors.DistanceDetector;
import model.listeners.DistanceListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DistanceDetector Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 13, 2014</pre> 
* @version 1.0 
*/ 
public class DistanceDetectorTest {

    DistanceDetector testDetector;
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
    testDetector = new DistanceDetector();
}

@After
public void after() throws Exception
{
    testDetector = null;
}

/** 
* 
* Method: run() 
* 
*/ 
@Test
public void testRun() throws Exception { 
    Assert.assertTrue(Thread.holdsLock(testDetector));
}

/** 
* 
* Method: registerListener(DistanceListener listener) 
* 
*/ 
@Test
public void testRegisterListener() throws Exception {
    testDetector.registerListener(listener);
    Assert.assertTrue(testDetector.getListeners().size() > 0);
}

/** 
* 
* Method: unregister(DistanceListener listener) 
* 
*/ 
@Test
public void testUnregister() throws Exception { 
    testDetector.unregister(testDetector.getListeners().get(0));

    Assert.assertTrue("Post register, the list should be empty",
            testDetector.getListeners().size() == 0);
} 

/** 
* 
* Method: getSensor() 
* 
*/ 
@Test
public void testGetSensor() throws Exception { 
    Assert.assertNotNull("The listener list should never be null",
            testDetector.getListeners());
} 

/** 
* 
* Method: getListeners() 
* 
*/ 
@Test
public void testGetListeners() throws Exception { 
    testDetector.registerListener(listener);
    Assert.assertFalse("Listeners should not be empty",testDetector.getListeners().isEmpty());
} 

/** 
* 
* Method: notifyNearObject(int distance) 
* 
*/ 
@Test
public void testNotifyNearObject() throws Exception { 
//TODO: Test goes here...
} 


} 
