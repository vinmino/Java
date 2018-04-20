import java.util.Scanner;

public class GravityCalculator {

	public static void main(String[] args) {
		double a = -9.8;
		double vi = 0;
		double xi = 0;
		System.out.println("This Gravtiy Calculator will determine the postion of a falling object after the inputed amount of seconds");
		System.out.print("Please input a time: ");
		Scanner keyboard = new Scanner(System.in);
		double t = keyboard.nextDouble();
		
		double xf = 0.5 * a * Math.pow(t, 2) + vi * t + xi;
		
		System.out.println("The final postion of the object is " + xf + " meters from the starting point after " + Math.round(t) + " seconds");
		
	}

}
