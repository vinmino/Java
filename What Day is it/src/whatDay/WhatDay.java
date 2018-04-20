package whatDay;

public class WhatDay {
	/*
	 * Data
	 */
	private int day;
	private int month;
	private int year;
	private int numWeekDay;
	private int yearOfCen;
	private int zeroBasedCen;
	private int newMonth;
	private int newYear;
	
	/*
	 * Constructors
	 */
	public WhatDay() {
		System.out.println("\nObject creation was empty, please try again.\n");
	}
	public WhatDay(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/*
	 * Methods
	 */
	public void calculate() {
		this.clean();
		if (this.checkMonthDay()) {
			this.formula();
			this.century();
			System.out.println("\n" + this.dayOfWeek() + "\n");
			
		} else {
			System.out.println("That day doesn't exsits...");
		}
		
	}
	
	private void formula() {
		numWeekDay = (day + (26*(newMonth + 1)/10) + yearOfCen + (yearOfCen/4) + (zeroBasedCen/4) + 5*zeroBasedCen)%7;
	}
	
	private String dayOfWeek() {
		String weekDay;
		switch (numWeekDay) {
		case 0: weekDay = "Saturday"; break;
		case 1: weekDay = "Sunday"; break;
		case 2: weekDay = "Monday"; break;
		case 3: weekDay = "Tuesday"; break;
		case 4: weekDay = "Wednesday"; break;
		case 5: weekDay = "Thursday"; break;
		case 6: weekDay = "Friday"; break;
		default: weekDay = "error"; break;
		}
		System.out.println(yearOfCen);
		System.out.println(zeroBasedCen);
		System.out.println(numWeekDay);
		return weekDay;
	}
	
	private void clean() {
		if (month < 3) {
			newMonth = month + 12;
			newYear = year - 1;
		}
	}
	private void century() {
		yearOfCen = newYear % 100;
		zeroBasedCen = newYear / 100;
	}
	private boolean leapYear() {
		if ( (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ) {
			return true;
		} else {
			return false;
		}
	}
	private boolean checkMonthDay() {
		boolean result = true;
		switch (month) {
		case 1: result = (day <= 31) ? true:false; break;
		case 2: result = ( (day > 0 && day < 29 && !(this.leapYear())) || (day > 0 && day <= 29 && this.leapYear()) ) ? true:false; break;
		case 3: result = (day <= 31) ? true:false; break;
		case 4: result = (day <= 30) ? true:false; break;
		case 5: result = (day <= 31) ? true:false; break;
		case 6: result = (day <= 30) ? true:false; break;
		case 7: result = (day <= 31) ? true:false; break;
		case 8: result = (day <= 31) ? true:false; break;
		case 9: result = (day <= 30) ? true:false; break;
		case 10: result = (day <= 31) ? true:false; break;
		case 11: result = (day <= 30) ? true:false; break;
		case 12: result = (day <= 31) ? true:false; break;
		}
		return result;
	}
	
}
