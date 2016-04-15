//Google Code Jam 2016
//Problem A. Counting Sheep

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner; 

public class CountingSheep {
	public static void main ( String args[] ) {
		
		Scanner input = null;
		PrintWriter writer = null;
		boolean [] sheeps = new boolean[10];
		 
		int n; 
		String s; 
		
		//try opening input file
		try {
			input = new Scanner (new File("CountingSheep-small-practice.in"));
		}
			catch (Exception e){
				System.out.println("File Does Not Exist At The Path Provided!");
			}
				
				//try opening output file, if it doesn't exist create it
		try {
			writer = new PrintWriter("CountingSheepOutput.out", "UTF-8");
		}
			catch (Exception e) {
				System.out.println("ERROR: Writing file cannot be constructed!");
			}
		
		s=input.nextLine();
		final int T = Integer.parseInt(s);
		
		for (int t = 0; t < T; t++) {
			s= input.nextLine();
			final int N = n = Integer.parseInt(s);
			
			if (N == 0) {
				writer.print("Case #" + (t + 1) + ": " + "Insomnia" + "\n");
			}
			else {
				while (fullSheep(sheeps) == false) {
					s = Integer.toString(n); 
				
					for (int i = 0; i < s.length(); i++) {
					sheeps[Character.getNumericValue(s.charAt(i))] = true;
					}
					n += N;
				}
				writer.print("Case #" + (t + 1) + ": " + (n-N) + "\n");
			}
			
			for (int i = 0; i <sheeps.length; i ++) 
				sheeps[i]=false;
			
		}
		
		writer.close();
		System.out.println("Finito!!!");
		
	}
	
	
	public static boolean fullSheep(boolean [] arr) {
		for (int i = 0; i < arr.length; i ++) {
			if (arr[i] == false) 
				return false; 
		}
		return true;
	}
	
}
