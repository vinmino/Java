package whatDay;

import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		while (true) {
		Scanner key = new Scanner(System.in);
		System.out.print("Input the day: "); 
		int day = key.nextInt();
		System.out.print("Input the month: ");
		int month = key.nextInt();
		System.out.print("Input the year: ");
		int year = key.nextInt();
		
		WhatDay object = new WhatDay(day, month, year);
		object.calculate();
		}

	}

}
