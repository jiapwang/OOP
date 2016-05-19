package BotTrustSimulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	static final int orange_row1_Y = 50; 	//first orange row Y-value
	static final int orange_row2_Y = 150;	//second orange row Y-value
	static final int blue_row1_Y = 350;	  //first blue row Y-value
	static final int blue_row2_Y = 450;	  //second blue row Y-value
	static final int robotSize = 24;	 //
	
	Timer t;
	
	Bot OrangeRobot;
	Bot BlueRobot;
	Button [] orangeButtons = new Button [100];
	Button [] blueButtons = new Button [100];
	int xArr [] = new int [50];
	
	static ArrayList<String> botlst = new ArrayList<String>();
	static ArrayList<Integer> buttonlst = new ArrayList<Integer>();
	
	int time  = 0; 
	String turn_Message = "Orange start at button 1 | Blue start at button 1";
	
	
	GamePanel () {
		setBackground(Color.gray);
		t = new Timer(400,this); 
		OrangeRobot = new Bot ('O');
		BlueRobot = new Bot ('B');
		
		botlst.add("O");
		buttonlst.add(2);
		botlst.add("O");
		buttonlst.add(100);
		//botlst.add("B");
		//buttonlst.add(2);
		botlst.add("B");
		buttonlst.add(100);
		
		for (int i = 0; i < 50; i++) {
			xArr[i] = i * 25; 
		}
		
		for (int i = 0; i < 100; i++) {
			if (i<50)
			orangeButtons[i] = new Button(xArr[i], 50);
			
			else {
				orangeButtons[i] = new Button (xArr[i-50], 150);
			}
		}
		
		for (int i = 0; i < 100; i++) {
			if (i<50)
			blueButtons[i] = new Button(xArr[i], 350);
			
			else {
				blueButtons[i] = new Button (xArr[i-50], 450);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		g.setColor(Color.ORANGE);
		for (int i = 0; i <100; i++) {
			g.fillOval(orangeButtons[i].getX(), orangeButtons[i].getY(), orangeButtons[i].getButtonSize(), orangeButtons[i].getButtonSize());
		}
		
		if(OrangeRobot.getPos() <= 50)
			g.fillRect(xArr[OrangeRobot.getPos()-1], orange_row1_Y+30, robotSize, robotSize);
		else {
			g.fillRect(xArr[OrangeRobot.getPos()-51], orange_row2_Y+30, robotSize, robotSize);
		}
		
		g.setColor(Color.BLUE);
		
		for (int i = 0; i < 100; i++) {
			g.fillOval(blueButtons[i].getX(), blueButtons[i].getY(), blueButtons[i].getButtonSize(), blueButtons[i].getButtonSize());
		}
		
		if (BlueRobot.getPos() <= 50)
			g.fillRect(xArr[BlueRobot.getPos()-1], blue_row1_Y+30, robotSize, robotSize);
		else {
			g.fillRect(xArr[BlueRobot.getPos()-51], blue_row2_Y+30, robotSize, robotSize);
		}
		
		g.setFont(new Font("default", Font.BOLD, 12));
		g.setColor(Color.BLACK);
		for (int i = 1; i <= 50; i++) {
			g.drawString(Integer.toString(i), xArr[i-1], orange_row1_Y);
			g.drawString(Integer.toString(i), xArr[i-1], blue_row1_Y);
		}
		
		for (int i = 51; i <= 100; i++) {
			g.drawString(Integer.toString(i), xArr[i-51], orange_row2_Y);
			g.drawString(Integer.toString(i), xArr[i-51], blue_row2_Y);
		}
		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("Time: " + Integer.toString(time) + " | "+ turn_Message, 300, 550);
		if (botlst.isEmpty()) {
			g.drawString("Simulation Complete", 500, 600);
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent p) {
		int keyPressed = p.getKeyCode();
		
		//Press space to pause
		if (keyPressed == KeyEvent.VK_6) {
			if (t.isRunning())
				t.stop();
			else
				t.start();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
			
}
