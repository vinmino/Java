package growth;

import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.print("Input a number of fractions to add: ");
		long numTimes = key.nextLong();
		double piOverFour = 0.0;
		boolean neg = false;
		long counter = 0;
		
		long start = System.currentTimeMillis();
		for (long i = 1; counter < numTimes; i = i + 2) {
			piOverFour = ( neg ) ? piOverFour + (-1) * (1/(double)i) : piOverFour + (1/(double)i);
			neg = (neg) ? false : true;
			counter++;
			if (counter % Math.round( (double) numTimes /  200 ) == 0) {
				System.out.println(piOverFour * 4 + "    " + counter);
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println(piOverFour * 4 + "    " + counter);
		System.out.println(Math.PI);
		System.out.println((end - start)/1000);
		
	}

}
