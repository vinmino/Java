package converter;

import java.util.Scanner;

public class HexToDecimal {
	/*
	 * Data
	 */
	Scanner key = new Scanner(System.in);
	String input;
	String hexIndex = "0123456789ABCDEF";
	int output;
	
	/*
	 * Constructors
	 */
	public HexToDecimal() {
		System.out.print("Input a single digit of hex: ");
		this.input = key.nextLine();
		this.input.trim();
		while (input.length() != 1) {
			if (input.length() != 1) {
				System.out.print("I already told you, its just one digit... You gave me more than one!\nLets try this again...\nInput a single digit of hex: ");
				this.input = key.nextLine();
				this.input.trim();
			}
		}
	}
	
	public HexToDecimal(String input) {
		if (input.length() != 1) {
			System.out.println("Object creation failed. The input should only be one digit of hex");
		} else {
			this.input = input;
		}
	}
	
	
	/*
	 * Methods
	 */
	public void convert() {
		this.clean();
		this.output = this.hexIndex.indexOf(input);
		if (this.output == -1) {
			System.out.println("HA! You thought that you could stump me with a single digit\nthat isn't a hex digit! YOU WERE WRONG!");
		} else {
			System.out.println("Your HEX digit in base ten is " + output);
		}

	}
	
	private void clean() {
		this.input = this.input.toUpperCase();
	}
	
	
	
	
	
	
}
