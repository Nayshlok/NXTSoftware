package test.model.detectors;

public class FauxTouchSensor {

	private boolean isPressed = false;
	
	public boolean isPressed(int rawValue){
		return (rawValue < 600);
	}	
	
	public boolean isPressed(){
		return isPressed;
	}
	
	public void setPressed(boolean pressed){
		this.isPressed = pressed;
	}
}
