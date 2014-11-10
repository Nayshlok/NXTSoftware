
public interface DistanceListener {

	/*
	 * Implemented by those who wish to know when something nearby is detected.
	 */
	public void objectDetected(Object detector, int distance);
	
}
