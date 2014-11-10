import lejos.robotics.RegulatedMotor;


public class Driver implements DistanceListener, LineListener, RotationListener {

	private RegulatedMotor leftWheel, rightWheel;
	
	public Driver(){
		
	}
	
	public void registerDriverListener(DriverListener driveListen){
		/*
		 * Add listener to listeners
		 */
	}
	
	public void registerFinishedListener(FinishedMovementListener finishListen){
		/*
		 * Add listener to listeners
		 */
	}
	
	private void forward(){
		/*
		 * The Driver tells two motors to go forward
		 * yield thread loop to listen for events
		 */
	}
	
	private void backward(){
		/*
		 * The driver tells two motors to go backward
		 * notify listeners that it is moving backwards
		 * yield thread loop to listen for events
		 */
	}
	
	public void stop(){
		/*
		 * stop motors
		 * notify listeners that the rover has stopped.
		 */
	}
	
	private void turnClockwise(){
		/*
		 * The driver tells the two motors to rotate at different rates to turn the Rover
		 * yield thread loop to wait for events
		 */
	}

	@Override
	public void objectDetected(Object detector, int distance) {
		// TODO Auto-generated method stub
		/*
		 * Stop rotation.
		 * Move forward
		 */
	}

	@Override
	public void lineDetected() {
		/*
		 * if not completed task
		 * 	Store current direction.
		 * 	Stop driving, backup, and turn 90 degrees. 
		 * 	Begin full circle search.
		 * else completed task
		 * 	Keep driving two rotation
		 * 	Notify listener of finished movement
		 */
	}

	@Override
	public void reachedFullCircle() {
		/*
		 * Toggle boolean for completion
		 * drive forward 
		 */
	}

	
}
