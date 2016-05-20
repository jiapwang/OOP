package BotTrustSimulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main{
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Bot Trust Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		GamePanel gamepanel = new GamePanel();
		frame.add(gamepanel,BorderLayout.CENTER);

		frame.setSize(1250, 310);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);	//place the frame at the center of the screen
		frame.setVisible(true);
		frame.setResizable(false);

		gamepanel.go();
	}
	
}
