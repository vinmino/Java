package Problem_3;

public class Largest_Prime_Factor {

    public static void main(String[] args) {
        double num = 600851475143d;
        for (double i = 2d; i*i <= num; i++) {
            if (num % i == 0) {
                System.out.println(i);
            }
        }
    }

}
