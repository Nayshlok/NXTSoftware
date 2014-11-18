package test;

import model.SoundManager;
import model.detectors.ContactDetector;
import model.detectors.ContactInterface;

public class SoundTest {

	public static void main(String[] args) {
		ContactInterface touchDetect = new ContactDetector();
		SoundManager manager = new SoundManager();
	}

}
