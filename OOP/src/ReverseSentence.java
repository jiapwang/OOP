//Google Code Jam 2010 
//Problem B. Reverse Words

import java.io.*;
import java.util.Scanner;

public class ReverseSentence {
	public static void main (String Args []) {
		
		String sentence="";
		int numInput;
		Scanner input= null;
		PrintWriter writer = null;
		
		//try opening input file
		try {
			input = new Scanner (new File("ReverseSentence-small-practice.in"));
		}
			catch (Exception e){
				System.out.println("File Does Not Exist At The Path Provided!");
			}
		
		numInput = input.nextInt();
		input.nextLine();
		
		//try opening output file
		try {
			writer = new PrintWriter("ReverseSentenceOutput.out", "UTF-8");
		}
			catch (Exception e) {
				System.out.println("ERROR: Writing file cannot be constructed!");
			}
		
		//this loop takes numInput strings and performs sentence reversal 
		//on each string
		for (int i = 1; i <=numInput; i++) {
			
			sentence = input.nextLine();
			
			String[] words =  sentence.split(" ");
			String reverse = "";
			
			//concatenate the reverse strings into one string
			for(int n = words.length - 1; n >= 0 ; n--)
			{
			   reverse += words[n] + " ";
			}
			
			writer.print("Case #" + i + ": " + reverse + "\n");
			//System.out.println(reverse);
		}
		
		writer.close();
		System.out.println("Success!!!!");
		
				
		/*
		 * OUTPUT:
		 * 
		 * Case #1: test a is this 
		 * Case #2: foobar 
		 * Case #3: base your all 
		 * Case #4: class 
		 * Case #5: along pony 
		 */
		
		/*
		 * complete sentence reversal
	     * System.out.print("Completely reversed: ");
		 * for (int i = sentence.length() - 1; i >=0; i --) {
		 * System.out.print(sentence.charAt(i));
		 * }
		 * System.out.println();
		 */
				
	}
}
