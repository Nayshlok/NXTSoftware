
public class Rover implements TimerListener{

	
	public Rover(){
		/*
		 * Create Driver
		 * Create LineDetector in separate thread
		 * Create DistanceDetector in separate thread
		 * Create ContactDetector in separate thread
		 *  Create RoverTimer in separate thread
		 * Create SoundManager
		 * register soundManager to contactDetector and DriverListener 
		 */
	}
	
	public void doCleanup(){
		/*
		 * The rover will tell the driver to rotate a full circle at 30 degree intervals.
		 * wait for return
		 * display time stored in timer
		 * tell Sound manager to play victory tune.
		 */
	}

	@Override
	public void timeUp() {
		/*
		 * tell sound Manager to play failure tune.
		 * Shutdown application.
		 */
		
	}
	
//	public void doCleanup(){
//		/*
//		 * The Rover will search for cans
//		 * 
//		 */
//	}
//	
//	public void searchForCans(){
//		/*
//		 * while the rover has not turned a full circle
//		 * 		the Driver will turn the robot
//		 * 		The Distance Detector will look for a can
//		 * 		if a can is detected
//		 * 			The rover will store the location of the can
//		 *  The Rover will remove the nearest can
//		 *  
//		 */
//	}
//	
//	public void removeCan(){
//		/*
//		 * The driver will move the rover toward the can.
//		 * The Distance detector will ensure the distance is closing without losing the can.
//		 * 
//		 */
//	}
}
