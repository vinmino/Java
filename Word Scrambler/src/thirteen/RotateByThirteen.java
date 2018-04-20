package thirteen;

import java.util.Scanner;

public class RotateByThirteen {

	//checks to see if the char is an uppercase letter
	public static boolean btw_AZ(char character) {
		if (character >= 'A' && character <= 'Z') {
			return true;
		} else {
			return false;
		}
	}

	//checks to see if the char is a lowercase letter
	public static boolean btw_az(char character) {
		if (character >= 'a' && character <= 'z') {
			return true;
		} else {
			return false;
		}
	}


	public static String thirteen(String user) {
		char curr;
		char[] userArr = new char[user.length()];//Makes an array of char to hold the chars in the string
		int diff;
		userArr = user.toCharArray(); //fills the array with the input string
		for (int i = 0; i < user.length(); i++) { //iterates through the array and changes each one by 13
			curr = userArr[i];
			if (btw_AZ(curr) || btw_az(curr)) { //checks to see if its a letter
				curr += 13;
			}
			if (btw_AZ(curr) && btw_AZ((char)(curr - 13)) || btw_az(curr) && btw_az((char)(curr - 13)) ) { //checks if the letter casing changed. if its the same, then it doesn't need to be touched
				userArr[i] = curr;
				continue;
			} else if (btw_AZ((char)(curr - 13))) {//if it was uppercase now its not, it loops the letter back to the beginning
				diff = curr - 'Z';
				curr = (char)('A' - 1 + diff);
			} else if (btw_az((char)(curr - 13))) {//if it was lowercase now its not, it loops the letter back to the beginning
				diff = curr - 'z';
				curr = (char)('a' - 1 + diff);
			}
			userArr[i] = curr; //updates the same index to the new char
		}
		user = ""; //empties the user string
		for (char letter : userArr) { //fills the user string
			user += letter;
		}
		return user;
	}
	
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.print("Input the string of text: ");
		String user = key.nextLine();
		
		String result = thirteen(user);
		System.out.println(result);
		key.close();
		
		

	}

}
