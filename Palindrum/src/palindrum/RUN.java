package palindrum;

import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		String userString;
		String halfOne = "";
		String halfTwo = "";
		String halfMirror = "";
		String halfMid = "";
		
		
		System.out.print("Input a word to test: ");
		userString = key.nextLine();
		
		userString = userString.trim();
		userString = userString.toUpperCase();
		userString = userString.replaceAll(" ", "");
		
		if (userString.length() % 2 == 0 && userString.length() != 0) { //Even number word
			halfOne = userString.substring(0, (userString.length()/2));
			halfTwo = userString.substring((userString.length()/2));
		} else if (userString.length() % 2 == 1) { //Odd number word
			halfOne = userString.substring(0, (userString.length()/2));
			halfMid = userString.substring(userString.length()/2, userString.length()/2 + 1);
			halfTwo = userString.substring((userString.length()/2) + 1);
		} else {
			System.out.println("You didn't give me anything to work with you moron!!!!! >:-(");
		}
		
		for (int i = halfTwo.length() - 1; i >= 0; i--) { //creates a second string that flips the second half of the palindrome
			halfMirror += halfTwo.substring(i, i+1);
		}
		
		if (halfOne.matches(halfMirror)) {
			if (userString.length() % 2 == 0 && userString.length() != 0) {
				System.out.println("Your string is a palindrome!" + "  " + halfOne + "|" + halfTwo);
			} else if (userString.length() % 2 == 1) {
				System.out.println("Your string is a palindrome:" + "  " + halfOne + "|" + halfMid + "|" + halfTwo);
			}
		} else if (userString.length() == 0) {
			System.out.println("You never gave me anything to work with so it obviously isn't a palindrome!");
		} else {
			if (userString.length() % 2 == 0 && userString.length() != 0) {
				System.out.println("Your string is not a palindrome!" + "  " + halfOne + "|" + halfTwo);
			} else if (userString.length() % 2 == 1) {
				System.out.println("Your string is not a palindrome:" + "  " + halfOne + "|" + halfMid + "|" + halfTwo);
			}			
		}
		
		
	}

}
