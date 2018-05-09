package tryCatch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchExamples {

    public static void main(String[] args) {
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 1000 + 1);
        }

        boolean continueAgain = true;
        Scanner key = new Scanner(System.in);
        do {
            try {
                System.out.print("Input an index to check in the array: ");
                int index = key.nextInt();
                System.out.println("The requested index has a value of " + numbers[index]);
                continueAgain = false;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Out of Bounds, try again...");
                key.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Input needs to be an integer, try again...");
                key.nextLine();
            }
        } while(continueAgain);

    }


}
