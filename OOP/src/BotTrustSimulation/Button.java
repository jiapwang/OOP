package BotTrustSimulation;

public class Button {
	private int xPos;	//Button x-position
	private int yPos;	//Button y Position
	private int buttonSize;	   //Button size
	
	public Button (int x, int y){
		xPos = x;
		yPos = y;
		buttonSize = 24;
	}
	
	//return x-position
	public int getX () {
		return xPos;
	}
	
	//return y-position
	public int getY () {
		return yPos;
	}
	
	//return button size
	public int getButtonSize() {
		return buttonSize;
	}
}
