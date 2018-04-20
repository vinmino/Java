package readableScramble;


import java.util.ArrayList; //ArrayList is going to keep track of the strings of words in the sentence that is inputed
import java.util.Collections; //Collections is going to shuffle each words inner letters
import java.util.Scanner; //Takes input from user

public class mixer {
	
	//this method scrambles the letters in the middle of the word
	public static String wordScrambler(String word) {
		//Initialize the arrays to be used
		ArrayList<String> wordList = new ArrayList<String>(); //used to hold the entire word and work with this
		ArrayList<String> end  = new ArrayList<String>(); //this contains all the characters that shouldn't be shuffled around at the end of the word
		
		int cushion = 0; //cushion figures out how many characters on the end of the word shouldn't be shuffled. 
		
		for (int i = 0; i < word.length(); i++) { //This loop loops through the entire string from the input and puts each character as its own spot in the ArrayList 
			if (i != word.length()) wordList.add(word.substring(i, i+1)); //this is for all characters but the last one
			if (i == word.length()) wordList.add(word.substring(i)); //this is for the last character
			//they are going into the ArrayList as strings not chars
		}
		if (word.length() == 2 || word.length() == 1) return word;//this returns the string if its only one letter (or a letter and a space)
		String first = wordList.get(0); //saves the first character
		wordList.remove(0); //removes the first character
		
		//this loop figures out how large the cushion variable should be based the values being less than "A"
		for (int i = wordList.size() - 1; wordList.get(i).charAt(0) < "A".charAt(0); i-- ) {
			cushion++;
		}
		
		//This loop generates the end ArrayList of the size of the cushion variable 
		for (int i = 0; i < cushion + 1; i++) {
			end.add(wordList.get(wordList.size() - 1));//saves the last character
			wordList.remove(wordList.size() - 1);//removes the now saved character
		}
		
		
		Collections.shuffle(wordList); //shuffles the middle characters, aka the only ones left in the wordList ArrayList
		//since the Collections.shuffle() uses random numbers, sometimes the returned string is the same
		
		word = ""; //reset word to an empty string
		word += first; //concatenate the first letter to the beginning of the word
		
		//this loop concatenates the letters that have how been shuffled around to the word string
		for (int i = 0; i < wordList.size(); i++) { 
			word += wordList.get(i);
		}
		
		//this loop then concatenates the untouched end characters that didn't get shuffled
		for (int i = 0; i < end.size(); i++) {
			word += end.get(end.size() - 1 - i);
		}
		
		//returns the word
		return word;
	}
	
	//this method cuts the sentence that is input into an ArrayList of words
	public static ArrayList<String> cutter(String sentence) {
		
		//makes an ArrayList to hold the words
		ArrayList<String> pieces = new ArrayList<String>();
		
		//this loops cuts the sentence up based on if there is a space and it handles all of the spaces
		for (int i = 0; sentence.contains(" "); i++) {
			pieces.add(sentence.substring(0, sentence.indexOf(" ")+1)); //takes the first word and the space and adds it to the ArrayList
			sentence = sentence.substring(sentence.indexOf(" ")+1); //then takes the substring of the sentence that follows the word that was just taken out
		}
		
		return pieces;
	}
	
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		String user;
		
		System.out.print("Input a string of words to scramble the letters in: ");
		user = key.nextLine(); //saves the input from user
		
		ArrayList<String> words = new ArrayList<String>();
		
		words = cutter(user); //takes the input and returns an ArrayList then saves it. basically cuts up the sentence into an ArrayList of words
		
		for (int i = 0; i < words.size(); i++) { //for every word in the ArrayList, its passes it to the wordScrambler method to get scrambles, then prints it
			System.out.print(wordScrambler(words.get(i)));
		}
		
		key.close();
		
	}

}
