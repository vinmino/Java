package genertor100;

public class Generate100s {

    public static char generateLetter() {
        return (char)('A' + Math.random() * ('Z' - 'A' + 1));
    }

    public static int generateDigit() {
        return (int)(Math.random() * 9 + 1);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) { //makes 100 random chars
            if (i % 10 == 0) { //makes a new line every ten
                System.out.print(generateLetter() + "\n");
            } else {
                System.out.print(generateLetter() + ", "); //adds commas
            }
        }

        for (int i = 1; i <= 100; i++) { //same as above just for digits not chars
            if (i % 10 == 0) {
                System.out.print(generateDigit() + "\n");
            } else {
                System.out.print(generateDigit() + ", ");
            }
        }

    }


}
