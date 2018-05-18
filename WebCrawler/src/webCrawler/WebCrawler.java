package webCrawler;

import java.net.URL;
import java.util.Scanner;

public class WebCrawler {

    //Data
    String[] pendingURLs = new String[1];
    String[] newlyFoundURLs = new String[0];
    String[] seenURLs = new String[0];
    String originalURL;
    boolean done = false;

    //Constructor(s)
    public WebCrawler() {
        System.out.print("Input a URL to start crawling: ");
        pendingURLs[0] = new Scanner(System.in).nextLine();
        originalURL = pendingURLs[0];
        if (originalURL.startsWith("http")) { //Deals with parsing the part after the .com if there is a http there too
            String prefix = originalURL.substring(0, originalURL.indexOf("/") + 1);
            String root = originalURL.substring(originalURL.indexOf("/") + 1);
            if (root.contains("/")) {
                originalURL = prefix + root.substring(0, root.indexOf("/"));
            }
        } else { //Deals with parsing the part after the .com
            if (originalURL.contains("/")) {
                originalURL = originalURL.substring(0, originalURL.indexOf("/"));
            }
        }
    }

    public WebCrawler(String urlInput) {
        pendingURLs[0] = urlInput;
        originalURL = pendingURLs[0];
        if (originalURL.startsWith("http")) { //Same as above constructor
            String prefix = originalURL.substring(0, originalURL.indexOf("/") + 1);
            String root = originalURL.substring(originalURL.indexOf("/") + 1);
            if (root.contains("/")) {
                originalURL = prefix + root.substring(0, root.indexOf("/"));
            }
        } else {
            if (originalURL.contains("/")) {
                originalURL = originalURL.substring(0, originalURL.indexOf("/"));
            }
        }
    }

    //Methods
    public String[] addToArray(String[] inputArray, String addOn) { //Takes in an array, makes an array 1 larger and adds the new element and all old ones to this array
        String[] outputArray = new String[inputArray.length + 1];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        outputArray[outputArray.length - 1] = addOn;
        return outputArray;
    }

    public String[] addToArray(String[] inputArray, String[] addOn) { //Tankes in two arrays, makes a single larger array, adds them both to it
        String[] outputArray = new String[inputArray.length + addOn.length];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        for (int i = 0; i < addOn.length; i++) {
            outputArray[inputArray.length + i] = addOn[i];
        }
        return outputArray;
    }

    public String[] delFromArray(String[] inputArray) { //Deletes the first element in the array and shortens it by one
        String[] outputArray = new String[inputArray.length - 1];
        for (int i = 1; i < inputArray.length; i++) {
            outputArray[i - 1] = inputArray[i];
        }
        return outputArray;
    }

    public String[] searchForURLs(String urlString) { //Parses the html and searches for "href="" and then parses the url out of the quotes
        String workingString;
        String[] foundURLs = new String[0];
        int startingIndex;
        try {
            if (urlString.startsWith("/")) {
                urlString = originalURL + urlString;
            }
            URL url = new URL(urlString);
            Scanner viewer = new Scanner(url.openStream());
            while (viewer.hasNextLine()) {
                workingString = viewer.nextLine();
                if (workingString.contains("href=\"")) {
                    startingIndex = workingString.indexOf("href=\"");
                    workingString = workingString.substring(startingIndex + 6);
                    workingString = workingString.substring(0, workingString.indexOf("\""));
                    if (!workingString.equals("#") && !workingString.contains("javascript:void(0)") && !workingString.contains("javascript: void(0)")) { //deals with common errors to make it more efficient
                        foundURLs = addToArray(foundURLs, workingString);
                    }
                }
            }
            return foundURLs;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isInsideArray(String[] array, String element) { //Checks if a url is already in an array
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void crawl() { //Does the actual work and logic outlined by the notes in the Google Classroom slides
        int counter = 0;
        while (pendingURLs.length > 0/* && counter <= 100*/) {
            newlyFoundURLs = this.searchForURLs(pendingURLs[0]);
            seenURLs = this.addToArray(seenURLs, pendingURLs[0]);
            System.out.println(seenURLs[seenURLs.length - 1]);
            pendingURLs = this.delFromArray(pendingURLs);
            counter++;
            if(newlyFoundURLs != null) {
                for (int i = 0; i < newlyFoundURLs.length; i++) {
                    if (!this.isInsideArray(seenURLs, newlyFoundURLs[i])) {
                        pendingURLs = this.addToArray(pendingURLs, newlyFoundURLs[i]);
                    }
                }
            }
        }
        this.done = true;
    }


    //Main Method
    public static void main(String[] args) { //Runs the program
        //WebCrawler webCrawler = new WebCrawler("http://www.wrsd.net/wrhs");
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl();
        /*for (int i = 0; i < webCrawler.seenURLs.length; i++) {
            System.out.println(webCrawler.seenURLs[i]);
        }*/

    }
}
