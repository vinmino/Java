/*
 * Vincent Lomino
 * F Block
 * Bug Report: This code works good, except any of the inputs are something other than an interger.
 * If that happens, it throws an exception. I didn't have enough time or effort to fix this and keep it from breaking.
 */

package theGame;

import java.util.Random;
import java.util.Scanner;

public class HighLow {
	/* DATA */
	private int counter = 0;
	private int guess;
	private int previousGuess;
	private int computer;
	private Scanner key = new Scanner(System.in);

	/* CONSTRUCTORS */
	public HighLow() {
		System.out.println("Creation of game instance successful!");
		Random rand = new Random();
		this.computer = rand.nextInt(100) + 1;
		this.guess = 0;
	}
	
	public HighLow(int max) {
		System.out.println("Creation of game instance successful!");
		Random rand = new Random();
		this.computer = rand.nextInt(max) + 1;
		this.guess = 0;
	}
	
	public HighLow(boolean bool) {
		if (bool) {
			System.out.println("WOW, the coder really screwed up, this should never be true!");
		} else {
			System.out.println("After that this output the program closes the output. \nAKA it breaks itself. \nAKA You need to restart the program if you want to try and play again. \nAKA Go make your mother proud! :D");
		}
	}
	
	/* METHODS */
	public void guess() {
		System.out.print("Input an interger for a guess: ");
		
		this.previousGuess = this.guess;
		this.guess = key.nextInt();
		this.counter++;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public int getComputer() {
		return computer;
	}
	
	public int check() {
		if (guess == computer) {
			return 1;
		} else if (guess == previousGuess){
			return 4;
		} else if (guess > computer) {
			return 2;
		} else if (guess < computer) {
			return 3;
		} else {
			return 5;
		}
	}
	
	public int previousCheck() {
		if (guess == computer) {
			return 1;
		} else if (guess > computer) {
			return 2;
		} else if (guess < computer) {
			return 3;
		} else {
			return 5;
		}
	}
	
	public void close() {
		key.close();
	}

}
