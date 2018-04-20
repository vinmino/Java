package randomRotate;

import java.util.Scanner;

public class RotateByRandom {

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


    public static String randomRotate(String user) {
        char curr;
        char[] userArr = new char[user.length()];//Makes an array of char to hold the chars in the string
        int diff;
        int rand;
        userArr = user.toCharArray(); //fills the array with the input string
        for (int i = 0; i < user.length(); i++) { //iterates through the array and changes each one by rand
            curr = userArr[i];
            rand = (int)(Math.random() * 25) + 1; //random number btw 1-25 for each iteration of the loop
            if (btw_AZ(curr) || btw_az(curr)) { //checks to see if its a letter
                curr += rand;
            }
            if (btw_AZ(curr) && btw_AZ((char)(curr - rand)) || btw_az(curr) && btw_az((char)(curr - rand)) ) { //checks if the letter casing changed. if its the same, then it doesn't need to be touched
                userArr[i] = curr;
                continue;
            } else if (btw_AZ((char)(curr - rand))) {//if it was uppercase now its not, it loops the letter back to the beginning
                diff = curr - 'Z';
                curr = (char)('A' - 1 + diff);
            } else if (btw_az((char)(curr - rand))) {//if it was lowercase now its not, it loops the letter back to the beginning
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

        String result = randomRotate(user);
        System.out.println(result);
        key.close();


    }


}
