/*
 * Vincent Lomino
 * F Block
 * Bug Report: This code works good, except any of the inputs are something other than an interger.
 * If that happens, it throws an exception. I didn't have enough time or effort to fix this and keep it from breaking.
 */

package theGame;

import java.util.Scanner;

public class RUNGAME {
	
	public static void main(String[] args) {

		HighLow game;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("This is a game of High or Low!");
		System.out.println("If you would like to play the standard game, input 1");
		System.out.println("If you would like to input your own range, input 2");
		System.out.print("Game Mode: ");
		
		int choice = keyboard.nextInt();
		
		if (choice == 1) {
			game = new HighLow();
		} else if (choice == 2) {
			int max;
			System.out.print("Input desired maximum possible guess: ");
			max = keyboard.nextInt();
			game = new HighLow(max);
		} else {
			System.out.println("Wow, you can't even start the game correctly. You are a failure in life...");
			game = new HighLow(false);
			System.out.close();
		}
		
				
		do {
			
			game.guess();
			
			switch (game.check())
			{
			case 1:
				break;
			case 2:
				System.out.println("Lower");
				break;
			case 3:
				System.out.println("Higher");
				break;
			case 4:
				String sarcasm;
				switch (game.previousCheck())
				{
				case 2: 
					sarcasm = "lower!";
					break;
				case 3:
					sarcasm = "higher!";
					break;
				default:
					sarcasm = "NULL";
					break;
				}
				System.out.println("You obviously aren't paying attnetion... I JUST told you that that number is " + sarcasm);
				break;
			default:
				System.out.println("You screwed something up...");
			}
			
		} while(game.check() != 1);
		
		if (game.getCounter() == 1) {
			System.out.println("Your guessed the correct number of " + game.getComputer() + " on your first try! You should win a medal or something!");
		} else {
			System.out.println( + game.getCounter() + " guesses!");
		}
		
		keyboard.close();
		
	}

}
