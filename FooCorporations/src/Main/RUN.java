/*
 * Vincent Lomino
 * F Block
 * This is the class with the main function that you run to use the other class.
 * 
 */

package Main;

import java.util.Scanner;
import java.text.DecimalFormat;


public class RUN {

	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		System.out.print("Input number of hours worked: ");
		double worked = key.nextDouble();
		key.close();
		
		if (worked > 60.0) {
			worked = 60.0;
		}
		
		calculatePay pineapple = new calculatePay(worked);
		
		DecimalFormat df = new DecimalFormat("$##0.00");
		
		double base = pineapple.getBasePay();
		double overtime = pineapple.getOvertimePay();
		double total = base + overtime;
		
		System.out.println("Base Pay: " + df.format(base));
		System.out.println("Overtime Pay: " + df.format(overtime));
		System.out.println("Total Pay: " + df.format(total));
	}

}
