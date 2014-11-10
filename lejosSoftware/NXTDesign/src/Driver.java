import lejos.robotics.RegulatedMotor;


public class Driver implements DistanceListener, LineListener {

	private RegulatedMotor leftWheel, rightWheel;
	
	public Driver(){
		
	}
	
	public void rotateFullCircle(int interval){
		/*
		 * while the driver has not turned a full circle
		 * 		The driver will turn amount passed in
		 * 		the driver will wait 300 milliseconds
		 * 
		 * return to Rover for victory.
		 */
	}
	
	private void forward(){
		/*
		 * The Driver tells two motors to go forward
		 */
	}
	
	private void backward(){
		/*
		 * The driver tells two motors to go backward
		 * notify listeners that it is moving backwards
		 */
	}
	
	public void stop(){
		/*
		 * notify listeners that the rover has stopped.
		 */
	}
	
	private void turn(){
		/*
		 * The driver tells the two motors to turn at different rates to turn the Rover
		 */
	}

	@Override
	public void objectDetected(Object detector, int distance) {
		// TODO Auto-generated method stub
		/*
		 * if direction is not stored
		 * 	Stops the rotation and drives forward instead.
		 * else direction is not stored
		 * 	do nothing
		 */
	}

	@Override
	public void lineDetected() {
		// TODO Auto-generated method stub
		/*
		 * Store current direction.
		 * Stop driving, backup, and turn 90 degrees. 
		 * Begin full circle search.
		 */
	}

	
}
