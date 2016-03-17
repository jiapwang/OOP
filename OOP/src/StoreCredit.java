import java.io.*;
import java.util.Scanner;

public class StoreCredit {
	public static void main (String args []) {
		
		Scanner input = null;
		PrintWriter writer = null;
		String s; //line retrieved from file
		int N;  //the number of test cases
		int C;	//the amount of credit you have at the store
		int I;	//the number of items in the store
		String [] strItems;  //String array of the prices 
		
		//try opening input file
		try {
			input = new Scanner (new File("A-small-practice.in"));
		}
			catch (Exception e){
				System.out.println("File Does Not Exist At The Path Provided!");
			}
		
		//try opening output file, if it doesn't exist create it
		try {
			writer = new PrintWriter("StoreCreditOutput.out", "UTF-8");
		}
			catch (Exception e) {
				System.out.println("ERROR: Writing file cannot be constructed!");
			}
		
		s=input.nextLine();
		N = Integer.parseInt(s);
	
		for ( int i = 0; i < N; i ++) {
			s = input.nextLine();
			C = Integer.parseInt(s);
			s = input.nextLine();
			I = Integer.parseInt(s);
			s = input.nextLine();
			strItems = s.split(" ");
			
			int [] intItems = new int [strItems.length]; // int array of the prices
			
			for (int n = 0; n <I; n++) {
				intItems[n] = Integer.parseInt(strItems[n]);
			}	
			
			writer.print("Case #" + (i + 1) + ": ");
			findCredit(intItems, C, I, writer);
		}
		System.out.println("Success!!");
		writer.close();
	}
	
	/*
	 * This function finds the 2 elements in the array that sums up to the credit. 
	 * Once the two positions are found it is printed and the function returns 
	 * to the calling method. 
	 */
	static void findCredit (int [] arr, int cred, int nItems, PrintWriter writer) {
		
		for (int i = 0; i < nItems; i++) {
			for (int n = i+1; n < nItems; n++){
				if (arr[i] + arr[n] == cred) {
					writer.print( (i+1) + " " + (n+1) + "\n");
					return;
				}
			}
		}
	}
	
	/*
	 * OUTPUT: 
	 * 
	 * Case #1: 2 3
	 * Case #2: 1 4
	 * Case #3: 4 5
	 * Case #4: 29 46
	 * Case #5: 11 56
	 * Case #6: 4 5
	 * Case #7: 40 46
	 * Case #8: 16 35
	 * Case #9: 55 74
	 * Case #10: 7 9
	 */
}
