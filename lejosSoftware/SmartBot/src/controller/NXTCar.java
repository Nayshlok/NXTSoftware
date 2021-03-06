package controller;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import model.parents.AbstractNXTCar;

/**
 * Created by Stephen on 10/26/2014.
 * In project: NeumontWork
 */
public class NXTCar extends AbstractNXTCar {

	private final int DEGREES_FOR_REVOLUTION = 1100;

    private NXTRegulatedMotor left = Motor.B,
            right = Motor.C;
    
    private int facingDegree = 0;
    
    public NXTCar(){
    	left.setAcceleration(1000);
    	right.setAcceleration(1000);
    }
    
    /**
     * Turn both wheels forward.
     */
    public void forward() {
        left.forward();
        right.forward();
    }

    /**
     * Turn both wheels backward.
     */
    public void reverse() {

        left.backward();
        right.backward();

    }

    public void stop(){
    	left.flt(true);
    	right.flt();
    }
    
    public void turnRight() {
        left.forward();
        right.flt();
    }

    private void addFacing(int rotation){
    	facingDegree += rotation;
    	if(facingDegree > 360){
    		facingDegree -= 360;
    	}
    }
    
    public void faceDirection(int direction){
    	int toTurn = direction - facingDegree;
    	if(Math.abs(toTurn) > 180 && toTurn < 0){
    		toTurn = 360 - Math.abs(toTurn);
    	}
    	else if(Math.abs(toTurn) > 180 && toTurn > 0){
    		toTurn = Math.abs(toTurn) - 360;
    	}
    	//System.out.println("Target: " + direction + ", current: " + facingDegree + ", turning: " + toTurn);
    	rotate(toTurn);
    }
    
    public void rotate(int carRotate, boolean immediateReturn) {
    	addFacing(carRotate);
    	int angle = (DEGREES_FOR_REVOLUTION/360) * carRotate;
    	
        left.rotate(angle, true);
		//System.out.println(angle);
        right.rotate(-angle, immediateReturn);
		//System.out.println(-angle);
    }
    
    public int getFacing(){
    	return facingDegree;
    }


}
