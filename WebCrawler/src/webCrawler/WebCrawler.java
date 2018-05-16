package webCrawler;

import java.util.Scanner;

public class WebCrawler {

    //Data
    String[] pendingURLs = new String[1];
    String[] seenURLs = new String[0];

    //Constructor(s)
    public WebCrawler() {
        System.out.print("Input a URL to start crawling: ");
        pendingURLs[0] = new Scanner(System.in).nextLine();
    }

    //Methods
    public String[] addToArray(String[] inputArray, String addOn) {
        String[] outputArray = new String[inputArray.length + 1];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        outputArray[outputArray.length-1] = addOn;
        return outputArray;
    }

    public String[] delFromArray(String[] inputArray) {
        String[] outputArray = new String[inputArray.length-1];
        for (int i = 0; i < inputArray.length - 1; i++) {
            outputArray[i] = inputArray[i];
        }
        return outputArray;
    }


    //Main Method
    public static void main(String[] args) {

    }

}
