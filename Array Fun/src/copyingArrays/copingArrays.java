package copyingArrays;

public class copingArrays {

    public static void main(String[] args) {

        //makes the alphabet array and populates it with the alphabet
        char alphabet[] = new char[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) ('a' + i);
        }

        //makes the alphabet2 array and populates with alphabet array
        char alphabet2[] = new char[alphabet.length];
        for (int i = 0; i < alphabet2.length; i++) {
            alphabet2[i] = alphabet[i];
        }

        int rand;
        char temp;
        for (int i = 0; i < alphabet2.length; i++) {
            rand = (int)(Math.random()*alphabet.length);
            temp = alphabet2[i];
            alphabet2[i] = alphabet2[rand];
            alphabet2[rand] = temp;
        }

        for (int i = 0; i < alphabet2.length; i++) {
            if (i == 25) {
                System.out.print(alphabet[i]);
            } else {
                System.out.print(alphabet[i] + ", ");
            }
        }


    }
}
