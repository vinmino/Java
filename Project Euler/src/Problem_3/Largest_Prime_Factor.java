package Problem_3;

public class Largest_Prime_Factor {

    public static void main(String[] args) {
        double num = 600851475143d;
        for (double i = 2; i <= num; i++) {
            if (num%i == 0) {
                while( num % i == 0)
                    num = num / i;
                System.out.println(i + " is a prime factor");
            }
        }
    }
}
