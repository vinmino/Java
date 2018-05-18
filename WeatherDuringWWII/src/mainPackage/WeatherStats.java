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
        DataTable WorkingTable = new DataTable("C:\\Users\\vincentlomino19\\IdeaProjects\\Java\\WeatherDuringWWII\\src\\mainPackage\\Weather.csv");
    }

}

class DataTable {
    /* Data */
    String[] headersAndValues;
    DataColumns[] totalColumns;
    String[][] dataTable;
    int[] dataTypeList;
    int numOfRows;
    int numOfCols;

    /* Constructor */
    public DataTable(String filePath) {

        String[] starterStringArray = new String[0];
        try {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                starterStringArray = this.addToStringArray(starterStringArray, reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.print("\n-----File not found-----\n-----Ending Program-----\n");
            System.exit(666);
        }
        this.numOfRows = starterStringArray.length;
        System.out.print("The total number of rows is " + this.numOfRows + "\n");

        this.numOfCols = 1;
        String workingString = starterStringArray[0];
        if (!workingString.contains(",")) {
            System.out.print("\n-----This file is not formatted like a CSV should be-----\n-----Ending Program-----\n");
            System.exit(1234);
        }
        while (workingString.contains(",")) {
            this.numOfCols++;
            workingString = workingString.substring(workingString.indexOf(",") + 1);
        }
        System.out.print("The total number of columns is " + this.numOfCols + "\n");

        this.headersAndValues = new String[this.numOfCols];
        workingString = starterStringArray[0];
        int headersIndex = 0;
        while (workingString.contains(",") /*&& workingString.indexOf(",") != workingString.lastIndexOf(",")*/) {
            headersAndValues[headersIndex] = workingString.substring(0, workingString.indexOf(","));
            headersIndex++;
            workingString = workingString.substring(workingString.indexOf(",") + 1);
        }
        headersAndValues[headersIndex] = workingString;

        /*Scanner dataTypeQuestions = new Scanner(System.in);
        this.dataTypeList = new int[numOfCols];
        for (int i = 0; i < dataTypeList.length; i++) {
            System.out.print("Data Type List:\n\tDouble = 1\n\tString = 2\n\tBoolean = 3\n\tColumn " + i + ": ");
            dataTypeList[i] = dataTypeQuestions.nextInt();
        }
        dataTypeQuestions.close();*/


        totalColumns = new DataColumns[numOfCols];

    }

    /* Methods */
    public String[] addToStringArray(String[] oldArray, String input) {
        String[] output = new String[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            output[i] = oldArray[i];
        }
        output[output.length - 1] = input;
        return output;
    }

    public String[] deleteFromIndex(String[] array, int index) {
        String[] output = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                output[i] = array[i];
                j++;
            }
        }
        return output;
    }

}

class DataColumns {
    /* Data */
    String header;
    String[] data;

    /* Constructor */
    public DataColumns(String[] col) {
        this.header = col[0];
        this.data = new String[col.length - 1];
        for (int i = 1; i < col.length; i++) {
            data[i-1] = col[i];
            
        }

    }

    /* Methods */

}