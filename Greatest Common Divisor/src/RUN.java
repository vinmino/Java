import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		
		System.out.print("Enter first Integer: ");
		int first = key.nextInt();
		System.out.print("Enter second Integer: ");
		int second = key.nextInt();
		
		int smaller = (first < second) ? first : second;
		int larger = (first < second) ? second : first;
		int gcd = 1;
		
		for (int i = 1; i < smaller; i++) {
			gcd = (smaller % i == 0 && larger % i == 0) ? i:gcd;
		}
		System.out.println("The greatest common divisor for " + smaller + " and " +  larger + " is " + gcd);
		key.close();
		
	}

}
