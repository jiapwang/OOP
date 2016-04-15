//Google Code Jam 2016
//Problem A. Counting Sheep
//https://code.google.com/codejam/contest/6254486/dashboard

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner; 

public class CountingSheep {
	public static void main ( String args[] ) {
		
		Scanner input = null;
		PrintWriter writer = null;
		boolean [] sheeps = new boolean[10];
		 
		int n; 		//holds the multiples of N 
		String s; 	//holds the input from the input file
		
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
		final int T = Integer.parseInt(s);		//T holds the number of cases
		
		for (int t = 0; t < T; t++) {
			s= input.nextLine();
			final int N = n = Integer.parseInt(s);	//N is the starting integer retrieved from the input file
			
			if (N == 0) {
				writer.print("Case #" + (t + 1) + ": " + "Insomnia" + "\n");
			}
			else {
				while (fullSheep(sheeps) == false) {	//while "sheeps" array is not completely true, run while loop
					s = Integer.toString(n); 
					for (int i = 0; i < s.length(); i++) {		//get the digits of s and set the corresponding index in sheep equal to true
					sheeps[Character.getNumericValue(s.charAt(i))] = true;
					}
					n += N;
				}
				writer.print("Case #" + (t + 1) + ": " + (n-N) + "\n");		//when "sheep" array elements are all true, print case # and last value of n that completed the "sheep" array
			}
			
			for (int i = 0; i <sheeps.length; i ++) 	// for each case, reset "sheep" array to false
				sheeps[i]=false;		
		}
		writer.close();
		System.out.println("Finito!!!");
	}
	
	//this function checks to see if the boolean array passed in is completely true
	//return false if any single element in the array is false
	//return true otherwise
	public static boolean fullSheep(boolean [] arr) {
		for (int i = 0; i < arr.length; i ++) {
			if (arr[i] == false) 
				return false; 
		}
		return true;
	}
	
}
