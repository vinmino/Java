import java.util.Scanner;
import java.text.DecimalFormat;

public class TaxTime {

	public static void main(String[] args) {
		
		double rate = 0.2;
		double deductable = 10000;
		double perDependant = 2000;
		 
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Gross Income: ");
		double income = keyboard.nextDouble();
		
		System.out.print("Number of Dependants: ");
		double dependants = keyboard.nextDouble();
		
		double taxes = (income - (dependants * perDependant) - deductable) * rate;
		
		DecimalFormat df = new DecimalFormat("$#,###.00");
		
		System.out.print("Total taxes due: " + df.format(taxes) + "\n");
			
		

	}

}
