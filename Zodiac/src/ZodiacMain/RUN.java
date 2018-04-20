package ZodiacMain;

import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RUN {
	
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.println("This program outputs the Zodiac Sign of the people born in the inputed year");
		while (true) {
			System.out.print("\nInput the year you were born: ");
			int year = key.nextInt();
			DateFormat df = new SimpleDateFormat("yyyy");
			Calendar calObj = Calendar.getInstance();
			int currentYear = Integer.parseInt(df.format(calObj.getTime()));
			if (year > currentYear) {
				System.out.println("Holy moly you're a time travler!");
			}
			Zodiac lols = new Zodiac(year);
			lols.calculate();
		}
	}

}
