package myInteger;

public class MyInteger {
    int value;

    public MyInteger(int x) {
        this.value = x;
    }

    public boolean isEven() {
        return (value % 2 == 0);
    }

    public boolean isOdd() {
        return (value % 2 != 0);
    }

    public boolean isPrime() {
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEven(int x) {
        return (x % 2 == 0);
    }

    public static boolean isOdd(int x) {
        return (x % 2 != 0);
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isEven(MyInteger x) {
        return (x.value % 2 == 0);
    }

    public boolean isOdd(MyInteger x) {
        return (x.value % 2 != 0);
    }

    public boolean isPrime(MyInteger x) {
        for (int i = 2; i < x.value; i++) {
            if (x.value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(MyInteger x) {
        return (x.value == value);
    }

    public boolean equals(int x) {
        return (x == value);
    }

    public static int parseInt(char[] list) {
        return Integer.parseInt(list.toString());
    }

    public static int parseInt(String digits) {
        return Integer.parseInt(digits);
    }
}
