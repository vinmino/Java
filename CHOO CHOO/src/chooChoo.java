import java.util.Scanner;
public class chooChoo {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		double vi = 55;
		double a = 3125;
		System.out.print("Input the time after acceleration started in seconds: ");
		double t = keyboard.nextInt();
		double hrs = t / 3600;
		double vf = (vi + (a*hrs));
		System.out.println("\nThe train was traveling at " + Math.round(vf) + " mph after " + t + " seconds.");
		
		double x = Math.pow(vf, 2)/(Math.pow(vi, 2) + 2*a);
		System.out.println("The train would have traveled " + x + " miles.");
		
		
	}

}
