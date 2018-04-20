/*
 * This code lets the user build the quiz. You can choose the largest
 * number used in the random number generator, and then you can choose
 * the number of questions for the quiz. The quiz then spits out the 
 * time the quiz took, and the correct answers, and the incorrect answers
 * and the score and the question and answer history.
 * 
 * Vincent Lomino
 * F Block
 */



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RUN {
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		Scanner key = new Scanner(System.in);
		ArrayList<Boolean> ans = new ArrayList<Boolean>();
		ArrayList<String> probs = new ArrayList<String>();
		
		int prob1;
		int prob2;
		String prob;
		int tempAns;
		int user;
		double correct = 0;
		double incorrect = 0;
		int largest;
		int length;
		
		System.out.println("This program creates a subtraction quiz that uses the largest desired number as a range to make the quiz the desired length");
		System.out.print("Input the largest desired number for the quiz: ");
		largest = key.nextInt();
		System.out.print("Input the number of desired questions for the quiz: ");
		length = key.nextInt();
		System.out.println("\n");
		
		
		final long StartTime = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {	
			prob1 = rand.nextInt(largest) + 1;
			prob2 = rand.nextInt(prob1);
			prob = prob1 + " - " + prob2 + " = ";
			tempAns = prob1 - prob2;
			System.out.print(prob);
			user = key.nextInt();
			System.out.println(((tempAns == user) ? "You are correct!\n":("Your answer is wrong.\n" + prob1 + " - " + prob2 + " should be " + tempAns + "\n")));
			ans.add((tempAns == user) ? true : false );
			probs.add(prob + user);
		}
		final long EndTime = System.currentTimeMillis();
		long ElapsedTime = EndTime - StartTime;
		
		for (int i = 0; i < probs.size(); i++) {
			if (ans.get(i)) {
				correct++;
			} else {
				incorrect++;
			}
		}
		
		System.out.println("\nThe quiz took " + ElapsedTime / 1000.0 + " seconds");
		System.out.println("The number of correct answers is: " + Math.round(correct));
		System.out.println("The number of incorrect answers is: " + Math.round(incorrect));
		System.out.println("Your score is " + (correct/(correct + incorrect)) * 100 + "%\n");
		
		
		for (int i = 0; i < probs.size(); i++) {
			System.out.println(probs.get(i) + ((ans.get(i)) ? " Correct":" Incorrect"));
		}
		
	}
}
