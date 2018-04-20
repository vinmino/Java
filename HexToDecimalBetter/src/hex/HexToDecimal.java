package hex;

import java.util.Scanner;

public class HexToDecimal {
	
	//This will keep track of the power to use on the "* 16" part of adding the digits together
	static int power = 0;
	
	//This method takes a string of one char(still a string) and returns the result of that one digit
	public static int hexToDecimalDigit(String digit) {
		String hexCode = "0123456789ABCDEF";
		int result = hexCode.indexOf(digit);
		result *= (int)Math.pow(16, power); //the result contains the index of the string that the char is at. then it uses the power to say what digit it is in so that it will have the right power
		power--; //then the power is decremented so that it will be ready for the next digit of the hex
		return result;
	}
	
	public static String clean(String raw) { //this method is there to clear spaces and make the letters Uppercase. it also sets the highest power to the length of the hex digit minus one (the highest number)
		String fresh = raw.trim().toUpperCase();
		power = fresh.length() - 1;
		return fresh;
	}
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		String user;
		int hexResult = 0;
		
		System.out.print("Input the hex number: ");
		user = key.nextLine();
		user = clean(user);
		
		//this will prevent errors
		try {
			//this loop goes through the entire inputed string and treats each digit to the hexToDecimalDigit() method
			for (int i = 0; i < user.length(); i++) {
				hexResult += hexToDecimalDigit(user.substring(i, i + 1));  {
				}
			}
			System.out.println("Your hex in decimal is: " + hexResult);
		} catch (Exception ex) {
			System.out.println("Something went wrong");
			System.exit(0);
		}
		key.close();
		
	}

}
