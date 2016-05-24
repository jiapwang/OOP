package BotTrustSimulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	static final int row1_Y = 50; 	//first row buttons Y-value
	static final int row2_Y = 150;	//second row buttons Y-value
	static final int robotSize = 24;	//size of the robot
	
	Timer t;
	
	static Bot OrangeRobot;	//Orange Bot object
	static Bot BlueRobot;	//Blue Bot object 
	
	Button [] Buttons = new Button [100];	//array of Button Objects
	int xArr [] = new int [50];	//array of the x-coordinates of the buttons
	
	static ArrayList<String> botlst = new ArrayList<String>();	//ArrayList holding the Robot push order
	static ArrayList<Integer> buttonlst = new ArrayList<Integer>();	//ArrayList holding the Button push order
	
	int time  = 0; 
	String turn_Message = "Orange start at button 1 | Blue start at button 1";	 
	
	GamePanel () {
		t = new Timer(500,this); 	
		OrangeRobot = new Bot ('O');	
		BlueRobot = new Bot ('B');
		
		for (int i = 0; i < 50; i++) {	//fill out the xArr array 
			xArr[i] = i * 25; 
		}
		
		for (int i = 0; i < 100; i++) {		//initalize the Buttons with the their positions
			if (i<50)
				Buttons[i] = new Button(xArr[i], 50);
			else {
				Buttons[i] = new Button (xArr[i-50], 150);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//paint the 2 rows of 50 buttons
		g.setColor(Color.GRAY);
		for (int i = 0; i <100; i++) {
			g.fillRect(Buttons[i].getX(), Buttons[i].getY(), Buttons[i].getButtonSize(), Buttons[i].getButtonSize());
		}
		
		//paint the location of the orange robot
		g.setColor(Color.orange);
		if(OrangeRobot.getPos() <= 50)
			g.fillOval(xArr[OrangeRobot.getPos()-1], row1_Y, robotSize, robotSize);
		else {
			g.fillOval(xArr[OrangeRobot.getPos()-51], row2_Y, robotSize, robotSize);
		}
		
		//paint the location of the blue robot
		g.setColor(Color.BLUE);
		if (BlueRobot.getPos() <= 50)
			g.fillOval(xArr[BlueRobot.getPos()-1], row1_Y, robotSize, robotSize);
		else {
			g.fillOval(xArr[BlueRobot.getPos()-51], row2_Y, robotSize, robotSize);
		}
		
		//paint the number of the buttons 1-100
		g.setFont(new Font("default", Font.BOLD, 12));
		g.setColor(Color.BLACK);
		for (int i = 1; i <= 50; i++) {
			g.drawString(Integer.toString(i), xArr[i-1], row1_Y);
		}
		
		for (int i = 51; i <= 100; i++) {
			g.drawString(Integer.toString(i), xArr[i-51], row2_Y);
		}
		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("Time: " + Integer.toString(time) + " | "+ turn_Message, 300, 230);
		if (botlst.isEmpty()) {
			g.drawString("Simulation Complete", 500, 280);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(botlst.isEmpty()) {
			t.stop();
		}
		else {
			tick();
		}
	}
	
	private void tick() {
		time++;
		turn_Message = "";
		
		boolean btnPressed = false;
		int positionInQueue;
		int button;
		
		/*
		 * Look for the first occurrence of "O" in botlst, take the index of that occurrence
		 * go to that index in buttonlst to find the button it needs to press and work towards 
		 * pushing that button. If "O" is not found, don't move the robot.
		 */
		if (botlst.indexOf("O") != -1) {
			positionInQueue = botlst.indexOf("O");
			button = (int) buttonlst.get(positionInQueue);
		
			if (OrangeRobot.getPos() == button && positionInQueue == 0) {
				turn_Message += OrangeRobot.pushButton();
				btnPressed = true;
			}
				else if (OrangeRobot.getPos() < button){
					turn_Message += OrangeRobot.moveRight();
				}
				else if (OrangeRobot.getPos() > button) {
					turn_Message += OrangeRobot.moveLeft();
				}
				else {
					turn_Message += OrangeRobot.dontMove();
				}
		}
		else {
			turn_Message += OrangeRobot.dontMove();
		}
		
		turn_Message += " | ";
		
		/*
		 * Look for the first occurrence of "B" in botlst, take the index of that occurrence
		 * go to that index in buttonlst to find the button it needs to press and work towards 
		 * pushing that button. If "B" is not found, don't move the robot.
		 */
		if (botlst.indexOf("B") != -1) {
			positionInQueue = botlst.indexOf("B");
			button = (int) buttonlst.get(positionInQueue);
			
			if (BlueRobot.getPos() == button && positionInQueue == 0) {
				turn_Message += BlueRobot.pushButton();
				btnPressed = true;
			}
				else if (BlueRobot.getPos() < button){
					turn_Message += BlueRobot.moveRight();
				}
				else if (BlueRobot.getPos() > button) {
					turn_Message += BlueRobot.moveLeft();
				}
				else {
					turn_Message += BlueRobot.dontMove();
				}
		}
		else {
			turn_Message += BlueRobot.dontMove();
		}
		
		//if a button has been pressed remove the first element from botlst and buttonlst
		if (btnPressed) {
			botlst.remove(0);
			buttonlst.remove(0);
		}	
		repaint();	//repaint the panel 
	}
	
	//start the timer
	void go() {		
		t.start();
	}
			
}
