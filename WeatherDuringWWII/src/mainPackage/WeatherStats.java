package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeatherStats {
    /* Data* */
    String[][] totalDataSet;
    File data;

    /* Constructor */
    public WeatherStats(String fileName) {
        try {
            this.data = new File(fileName);
            Scanner tester = new Scanner(this.data);
            tester.close();
        } catch (FileNotFoundException ex) {
            System.out.print("\n-----File not found-----\n-----Ending Program-----\n");
            System.exit(666);
        }

    }

    /* Methods */
    public void readFileAndPopulate() {

    }

    public String[] addToStringArray(String[] oldArray, String input) {
        String[] output = new String[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            output[i] = oldArray[i];
        }
        output[output.length - 1] = input;
        return output;
    }

    public static void main(String[] args) {

    }

}

class DataTable {
    /* Data */

    /* Constructor */

    /* Methods */

}

class DataColumns {
    /* Data */

    /* Constructor */

    /* Methods */

}