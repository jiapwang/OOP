package BotTrustSimulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	static final int row1_Y = 50; 	//first row buttons Y-value
	static final int row2_Y = 150;	//second row buttons Y-value
	static final int robotSize = 24;	 //
	
	Timer t;
	
	Bot OrangeRobot;
	Bot BlueRobot;
	Button [] Buttons = new Button [100];
	int xArr [] = new int [50];
	
	static ArrayList<String> botlst = new ArrayList<String>();
	static ArrayList<Integer> buttonlst = new ArrayList<Integer>();
	
	int time  = 0; 
	String turn_Message = "Orange start at button 1 | Blue start at button 1";
	
	
	GamePanel () {
		t = new Timer(1000,this); 
		OrangeRobot = new Bot ('O');
		BlueRobot = new Bot ('B');
		
		botlst.add("O");
		buttonlst.add(5);
		botlst.add("O");
		buttonlst.add(20);
		botlst.add("B");
		buttonlst.add(100);
		botlst.add("B");
		buttonlst.add(47);
		
		for (int i = 0; i < 50; i++) {
			xArr[i] = i * 25; 
		}
		
		for (int i = 0; i < 100; i++) {
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
		
		
		g.setColor(Color.GRAY);
		for (int i = 0; i <100; i++) {
			g.fillRect(Buttons[i].getX(), Buttons[i].getY(), Buttons[i].getButtonSize(), Buttons[i].getButtonSize());
		}
		
		g.setColor(Color.orange);
		if(OrangeRobot.getPos() <= 50)
			g.fillOval(xArr[OrangeRobot.getPos()-1], row1_Y, robotSize, robotSize);
		else {
			g.fillOval(xArr[OrangeRobot.getPos()-51], row2_Y, robotSize, robotSize);
		}
		
		g.setColor(Color.BLUE);
		if (BlueRobot.getPos() <= 50)
			g.fillOval(xArr[BlueRobot.getPos()-1], row1_Y, robotSize, robotSize);
		else {
			g.fillOval(xArr[BlueRobot.getPos()-51], row2_Y, robotSize, robotSize);
		}
		
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
		if (e.getSource() == t) {
			if(botlst.isEmpty()) {
				t.stop();
			}
			else {
				tick();
			}
		}
	}
	
	private void tick() {
		time++;
		turn_Message = "";
		
		boolean btnPressed = false;
		int positionInQueue;
		int button;
		
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
		
		System.out.println();
		
		if (btnPressed) {
			botlst.remove(0);
			buttonlst.remove(0);
		}
			
		repaint();
	}
		
	void go() {
		t.start();
	}
			
}
