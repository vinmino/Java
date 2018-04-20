package prime;

import java.util.ArrayList;
import java.util.Scanner;

public class RUN {

	public static boolean prime(int x) {
		for (int i = 2; i < x; i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true; 
	}
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		System.out.print("Input a number of primes to calculate: ");
		int user = key.nextInt();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		long start = System.currentTimeMillis();
		for (int j = 2; primes.size() < user; j++) {
			if (prime(j)) {
				primes.add(j);
			}
		}
		
		for (int i = 1; i <= primes.size(); i++) {
			if (i % 10 == 0 && i != 0) {
				System.out.print(primes.get(i-1) + "\n");
			} else {
				System.out.print(primes.get(i-1) + ", ");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("\n" + (end - start) / 1000.0 + " seconds");
		key.close();
	}

}
