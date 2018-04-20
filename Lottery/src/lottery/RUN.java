package lottery;

import java.util.Scanner;

public class RUN {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int choice;
		System.out.print("Input 1 to play the game 2 to have the computer pay the game: ");
		choice = keyboard.nextInt();
		if (choice == 1) {
			int attempts = 0;
			while (true) {
				LotteryGame loto = new LotteryGame(true);
				loto.clean();
				attempts++;
				loto.play(attempts);
			}
		} else if (choice == 2) {
			LotteryGame npc = new LotteryGame(false);
			int compGames;
			System.out.print("Input the number of the games you would like the computer to play: ");
			compGames = keyboard.nextInt();
			npc.NPC(compGames);
		} else {
			System.out.println("You are a failure, restart the program and try again");
		}
	}
}
