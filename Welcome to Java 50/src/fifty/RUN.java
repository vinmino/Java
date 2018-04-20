package fifty;

import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.print("Input a phrase: ");
		String phrase = key.nextLine();
		
		for (int i = 0; i < 50; i++) {
			System.out.println(phrase);
			phrase = " " + phrase;
		}
		for (int i = 0; i < 50; i ++) {
			System.out.println(phrase);
			phrase = phrase.substring(1);
		}
	}

}
