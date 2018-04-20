package carlo;

import java.util.Random;
import java.util.Scanner;

public class RUN {
	
	public static double length(double x, double y) {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		long points;
		double in = 0;
		double out = 0;
		double pi;
		double x;
		double y;
		Random rand = new Random();
		
		System.out.print("Input a total number of points to generate: ");
		points = key.nextLong();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < points; i++) {
			x = rand.nextDouble();
			y = rand.nextDouble();
			
			if ( length(x,y) <= 1 ) {
				in++;
			} else {
				out++;
			}
			
			if (i % 250000000 == 0) {
				System.out.println(i);
			}
						
		}

		
		pi = 4 *( in / (out + in) );
		long stop = System.currentTimeMillis();
		
		System.out.println(pi);
		System.out.println( (stop - start) / 1000 );
		key.close();
		
	}

}
