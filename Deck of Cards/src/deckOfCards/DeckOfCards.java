package deckOfCards;

public class DeckOfCards {

    //shuffles the deck randomly. returns a mixed up deck array
    public static int[] shuffle(int[] deck) {
        for (int z = 0; z < 7; z++) {
            for (int i = 0, j, temp; i < deck.length; i++) {
                j = (int) (Math.random() * deck.length);
                temp = deck[i];
                deck[i] = deck[j];
                deck[j] = temp;
            }
        }
        return deck;
    }

    //randomly picks 4 ints in the deck array and returns an array of int of a length 4
    public static int[] draw(int[] deck) {
        int[] hand = new int[4];
        for (int i = 0, rand; i < hand.length; i++) {
            rand = (int) (Math.random() * deck.length - 1);
            if (!(isInHand(hand, deck[rand]))) {
                hand[i] = deck[rand];
            } else {
                i--;
            }

        }
        return hand;
    }

    //checks to see if the card has already been chosen and is currently in the hand array of ints
    public static boolean isInHand(int[] hand, int testCard) {
        for (int cards : hand) {
            if (cards == testCard) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        int deck[] = new int[52];   // 0-51
        String suits[] = {" of Spades", " of Hearts", " of Diamonds", " of Clubs"};   // 0-3
        String values[] = new String[13];  // 0-12
        int tempHand[];
        int hand[][] = new int[4][2]; // 0-3[0,1] //two demensional array because why not? it keeps the hands organized and saves processing power

        //populates the deck
        for (int i = 0; i < deck.length; i++) {
            deck[i] += i;
        }
        values[0] = "Ace";
        values[12] = "King";
        values[11] = "Queen";
        values[10] = "Jack";

        for (int i = 1; i < 10; i++) {
            values[i] = i + 1 + "";
        }

        deck = shuffle(deck);
        tempHand = draw(deck);
        //fills the first index of each card with the value, and the second index of each card with its suit
        for (int i = 0; i < hand.length; i++) {
            hand[i][1] = tempHand[i] / 13;
            hand[i][0] = tempHand[i] % 13;
        }

        System.out.println("The four cards are: ");
        for (int[] card : hand) {
            System.out.println("\t" + values[card[0]] + suits[card[1]]);
        }


    }

}
