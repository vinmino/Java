package lottery;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;

public class LotteryGame {
	/*
	 * Data
	 */
	private int compNum;
	private int userNum;
	private int compNum1;
	private int compNum10;
	private int userNum1;
	private int userNum10;
	Scanner key = new Scanner(System.in);
	Random rand = new Random();
	DecimalFormat df = new DecimalFormat("$###,###,###,###,###,###,##0.00");
	/*
	 * Constructors
	 */
	public LotteryGame(boolean bool) {
		
		if (bool) {
			compNum = rand.nextInt(89) + 10;
		/*	System.out.println(compNum); */
			System.out.print("Input a two digit interger: ");
			userNum = key.nextInt();
		} else {
			compNum = rand.nextInt(89) + 10;
		}
		
		
	}
	/*
	 * Methods
	 */
	public void clean() {
		compNum1 = compNum % 10;
		compNum10 = compNum / 10;
		userNum1 = userNum % 10;
		userNum10 = userNum / 10;
	}
	
	public void play(int attempts) {
		if (compNum == userNum) {
			System.out.println("Woo hoo! You just won $10,000!" + " (" + attempts + ")");
		}  else if ( (compNum1 == userNum10) && (compNum10 == userNum1) ) {
			System.out.println("Woo hoo! You just won $3,000!" + " (" + attempts + ")");
		} else if ( (compNum1 == userNum1) || (compNum10  == userNum1) || (compNum1 == userNum10) || (compNum10  == userNum10) ) {
			System.out.println("Woo hoo! You just won $1,000!" + " (" + attempts + ")");
		} else {
			System.out.println("Woo hoo! You just won NOTHING! HA!" + " (" + attempts + ")");
		}
	}
	
	private int playNPC() {
		int status;
		if (compNum == userNum) {
			status = 1;
		}  else if ( (compNum1 == userNum10) && (compNum10 == userNum1) ) {
			status = 2;
		} else if ( (compNum1 == userNum1) || (compNum10  == userNum1) || (compNum1 == userNum10) || (compNum10  == userNum10) ) {
			status = 3;
		} else {
			status = 4;
		}
		return status;
	}
	
	public void newComp() {
		compNum = rand.nextInt(89) + 10;
	}
	
	public void NPC(int games) {
		int ten = 0;
		int three = 0;
		int one = 0;
		int loss = 0;
		for (int i = 0; i < games; i++) {
			userNum = rand.nextInt(89) + 10;
			this.clean();
			switch (this.playNPC()) {
			case 1: ten++;
				break;
			case 2: three++;
				break;
			case 3: one++;
				break;	
			default: loss++;
				break;
			}
		}
		
		System.out.println("The outcome of the game was a total of " + df.format((ten * 10000 + three * 3000 + one * 1000)) + " after " + (ten + three + one + loss) + " attempts");
	}
}
