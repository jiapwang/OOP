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
		
		Scanner input= null;
		
		//try opening input file
		try {
			input = new Scanner (new File("BotTrust-small-practice.in"));
		}
			catch (Exception e){
				System.out.println("File Does Not Exist At The Path Provided!");
		}
		
		int cases = input.nextInt();
		
		for (int x = 0; x < cases; x++) {
			GamePanel.OrangeRobot = null;
			GamePanel.BlueRobot = null;
			GamePanel.OrangeRobot = new Bot ('O');
			GamePanel.BlueRobot = new Bot ('B');
			
			int elements = input.nextInt();
			
			for (int y = 0; y < elements; y++) {
				GamePanel.botlst.add(input.next());
				GamePanel.buttonlst.add(input.nextInt());
			}
			
		gamepanel.go();
		}
	}
}
