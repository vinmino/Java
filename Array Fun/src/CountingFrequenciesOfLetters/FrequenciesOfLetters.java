package CountingFrequenciesOfLetters;

public class FrequenciesOfLetters {


    public static void main(String[] args) {
        //makes empty arrays of size 100 and of size 26
        char[] letters = new char[100];
        int[] freq = new int[26];

        //fills the 100 sized array with random lowercase letters
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char)('a' + Math.random() * ('z' - 'a' + 1));
        }

        //adds 1 to the index of the freq array that the letters array index is past the letter 'a'
        for (int i = 0; i < letters.length; i++) {
            freq[letters[i] - 'a'] += 1;
        }

        //outputs the top row of letters
        for (int i = 0; i < freq.length; i++) {
            //System.out.println("The letter " + (char)('a' + i) + " appeared " + freq[i] + " times.");
            System.out.print("\t|" + (char)('a' + i) + "|");
        }

        //outputs the bottom row of frequencies
        System.out.print("\n");
        for (int i = 0; i < freq.length; i++) {
            //System.out.println("The letter " + (char)('a' + i) + " appeared " + freq[i] + " times.");
            System.out.print("\t|" + freq[i] + "|");
        }


    }



}
