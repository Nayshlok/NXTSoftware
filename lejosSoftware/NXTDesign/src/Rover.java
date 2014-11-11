
public class Rover implements TimerListener, FinishedMovementListener{

	
	public Rover(){
		/*
		 * Create Driver
		 * Create LineDetector in separate thread
		 * Create DistanceDetector in separate thread
		 * Create ContactDetector in separate thread
		 * Create RoverTimer in separate thread
		 * Create RotationDetector in separate thread
		 * Create SoundManager in separate thread
		 * register soundManager to contactDetector and DriverListener 
		 * register Driver to DistanceDetector, LineDetector, and RotationDetector
		 */
	}
	
	public void start(){
		/*
		 * The rover will tell the driver to begin turning clockwise
		 */
	}
	
	@Override
	public void timeUp() {
		/*
		 * tell sound Manager to play failure tune.
		 * Shutdown application.
		 */
		
	}

	@Override
	public void finishedMovement() {
		/*
		 * Get time from timer and display it on screen
		 * Tell sound manager to play success sound
		 * shutdown application
		 */
	}
	
}
