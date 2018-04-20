import java.util.Scanner;
import java.text.DecimalFormat;

public class DancingAngels {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter the radius in millimeters: ");
		double radius = keyboard.nextDouble();
		
		System.out.print("Enter the space occupied by an angel in square micrometers: ");
		double occupied = keyboard.nextDouble();
		
		System.out.print("Enter the overlap factor: ");
		double overlap = keyboard.nextDouble();
		
		
		double area = Math.PI * Math.pow(radius, 2);
		double nonoverlap = occupied * (1 - overlap);
		double angels = area / nonoverlap;		
		
		DecimalFormat df = new DecimalFormat("0.###E0");
		
		System.out.print("The number of angels: " + df.format(angels));
	}
}
