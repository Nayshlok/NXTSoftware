package model.listeners;

public interface DistanceListener {

	/*
	 * Implemented by those who wish to know when something nearby is detected.
	 */
	public void objectDetected(int distance);
	
}
