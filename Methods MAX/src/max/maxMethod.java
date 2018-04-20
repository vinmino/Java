package max;

import java.util.Scanner;

public class maxMethod {

	
	
	
	public static int MAXIMUM(int a, int b) {
		
		return Math.max(a, b);
	}
	
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		int c;
		int d;
		
		while (true) {
			System.out.print("Enter two numbers to find the max between them: ");
			c = key.nextInt();
			d = key.nextInt();
			System.out.println("The larger number is: " + MAXIMUM(c, d) + "\n");
		}
		
		
	}

}
