package test.model.detectors;

import junit.framework.Assert;
import model.listeners.ContactListener;

import org.junit.Test;

public class ContactDetectorTest {
	private int pressCount = 0;
	
	@Test
	public void testRunWithInterrupting(){
		
	}
	
	@Test
	public void registAndNotify(){
		FauxContactDetector detector = new FauxContactDetector();
		for(int i = 0; i < 20; i++){
			detector.registerListener(new TestListener());
		}
		detector.notifyButtonPress();
		Assert.assertEquals(true, pressCount == 20);
		System.out.println("Register and notify: " + (pressCount == 20));
	}
	
	private class TestListener implements ContactListener{

		@Override
		public void buttonPressed() {
			pressCount++;
		}

		@Override
		public void buttonReleased() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
