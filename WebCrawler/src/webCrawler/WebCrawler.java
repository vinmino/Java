package webCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebCrawler {

    //Data
    String[] pendingURLs = new String[1];
    String[] newlyFoundURLs = new String[0];
    String[] seenURLs = new String[0];
    boolean done = false;

    //Constructor(s)
    public WebCrawler() {
        System.out.print("Input a URL to start crawling: ");
        pendingURLs[0] = new Scanner(System.in).nextLine();
    }

    public WebCrawler(String urlInput) {
        pendingURLs[0] = urlInput;
    }

    //Methods
    public String[] addToArray(String[] inputArray, String addOn) {
        String[] outputArray = new String[inputArray.length + 1];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        outputArray[outputArray.length - 1] = addOn;
        return outputArray;
    }

    public String[] addToArray(String[] inputArray, String[] addOn) {
        String[] outputArray = new String[inputArray.length + addOn.length];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        for (int i = 0; i < addOn.length; i++) {
            outputArray[inputArray.length + i] = addOn[i];
        }
        return outputArray;
    }

    public String[] delFromArray(String[] inputArray) {
        String[] outputArray = new String[inputArray.length - 1];
        for (int i = 0; i < inputArray.length - 1; i++) {
            outputArray[i] = inputArray[i];
        }
        return outputArray;
    }

    public String[] searchForURLs(String urlString) {
        String workingString;
        String[] foundURLs = new String[0];
        int startingIndex;
        try {
            URL url = new URL(urlString);
            Scanner viewer = new Scanner(url.openStream());
            while (viewer.hasNextLine()) {
                workingString = viewer.nextLine();
                if (workingString.contains("href=\"")) {
                    startingIndex = workingString.indexOf("href=\"");
                    workingString = workingString.substring(startingIndex + 6);
                    workingString = workingString.substring(0, workingString.indexOf("\""));
                    foundURLs = addToArray(foundURLs, workingString);
                }
            }
            return foundURLs;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isInsideArray(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void crawl() {
        while (pendingURLs.length > 0) {
            newlyFoundURLs = searchForURLs(pendingURLs[pendingURLs.length - 1]);
            pendingURLs = this.delFromArray(pendingURLs);
            if(newlyFoundURLs[0] != null) {
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
    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler("http://www.wrsd.net/wrhs");
        webCrawler.crawl();
        for (int i = 0; i < webCrawler.seenURLs.length; i++) {
            System.out.println(webCrawler.seenURLs[i]);
        }

    }
}
