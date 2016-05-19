package BotTrustSimulation;

public class Bot {
	private String color; 	//robot color
	private int pos; 		//robot position
	
	//if c holds the value 'O' then set color equal to "Orange" and set robot position to 1
	//else set color equal to "Blue" and set robot position to 1
	public Bot (char c) {
		if (c == 'O') {
			color = "Orange";
			pos = 1;
		}
		else {
			color = "Blue"; 
			pos = 1;
		}
	}
	
	//return position of robot as integer
	public int getPos() {
		return pos;
	}
	
	public String moveRight() {
		pos++;
		return color + " move to button " + pos;
	}
	
	public String moveLeft() {
		pos--;
		return color + " move to button " + pos;
	}
	
	public String dontMove() {
		return color + " stay at button " + pos;
	}
	
	public String pushButton() {
		return color + " push button " + pos;
	}
}
