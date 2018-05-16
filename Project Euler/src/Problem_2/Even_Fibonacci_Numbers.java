package Problem_2;

public class Even_Fibonacci_Numbers {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1, j = 2, t; j < 4000000; t = j, j += i, i = t) {
            if (j % 2 == 0) {
                sum += j;
            }
        }
        System.out.print("The total is " + sum);
    }
}
