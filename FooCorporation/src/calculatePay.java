/*-------------------------*/
/*   Vincent Lomino        */
/*   F Block               */
/*   Bug Report: No bugs   */
/*-------------------------*/


import java.util.Scanner;
import java.text.DecimalFormat;

public class calculatePay {
	
	public static void main(String[] args) {
		
		//Initialize all variables and objects
		final double BASE_RATE = 8.0;
		final double OVERTIME_RATE = 8.0 * 1.5;
		double overtime;
		int oops;
		DecimalFormat df = new DecimalFormat("$##0.00");
		Scanner key = new Scanner(System.in);
		
		//Ask for the value of hours from the Scanner
		System.out.print("Input hours worked in one week: ");
		double hours = key.nextDouble();
		
		//Close the Scanner
		key.close();
		
		//Make sure the value is not over 60
		if (hours > 60.0) {
			hours = 60.0;
			oops = 1;
		} else if (hours < 0) {
			oops = 0;
		} else {
			oops = 2;
		}
		
		//Calculate overtime value
		if (hours > 40.0 && hours <= 60.0) {
			overtime = hours - 40.0;
			hours = 40.0;
		} else {
			overtime = 0.0;
		}
		
		//Calculate all the pays
		double basePay = hours * BASE_RATE;
		double overtimePay = overtime * OVERTIME_RATE;
		double total = basePay + overtimePay;
		
		//Output results
		System.out.println("Base Pay: " + df.format(basePay));
		System.out.println("Overtime Pay: " + df.format(overtimePay));
		System.out.println("Total Pay: " + df.format(total));
		if (oops == 1) {
			System.out.println("YOU WORKED TOO HARD!");
		} else  if (oops == 0) {
			System.out.println("YOU NOW OWE THE COMPANY MONEY!");
		}
	}

}

