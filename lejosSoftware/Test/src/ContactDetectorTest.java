
import org.junit.Assert;
import org.junit.Test;



public class ContactDetectorTest {
	private int pressCount = 0;
	private int releaseCount = 0;
	
	@Test
	public void testRunWithInterrupting(){
		FauxContactDetector detector = new FauxContactDetector();
		Assert.assertFalse(Thread.holdsLock(detector));
		Thread testThread = new Thread(detector);
		testThread.start();
		Assert.assertTrue(testThread.isAlive());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testThread.interrupt();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(testThread.isAlive());
	}
	
	@Test
	public void testRun(){
		FauxTouchSensor sensor = new FauxTouchSensor();
		FauxContactDetector detector = new FauxContactDetector(sensor);
		detector.registerListener(new TestListener());
		Assert.assertFalse(Thread.holdsLock(detector));
		Thread testThread = new Thread(detector);
		testThread.start();
		Assert.assertTrue(testThread.isAlive());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensor.isPressed(300);
		Assert.assertTrue(pressCount > 0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensor.isPressed(1000);
		Assert.assertTrue(releaseCount > 0);
	}
	
	@Test
	public void registAndNotify(){
		FauxContactDetector detector = new FauxContactDetector();
		for(int i = 0; i < 20; i++){
			detector.registerListener(new TestListener());
		}
		detector.notifyButtonPress();
		System.out.println("Register and notify: " + pressCount);
		Assert.assertTrue(pressCount == 20);
		detector.notifyButtonRelease();
		System.out.println("Notify release: " + releaseCount);
		Assert.assertTrue(releaseCount == 20);
	}
	
	private class TestListener implements ContactListener{

		@Override
		public void buttonPressed() {
			pressCount++;
		}

		@Override
		public void buttonReleased() {
			releaseCount++;
			
		}
		
	}
}
