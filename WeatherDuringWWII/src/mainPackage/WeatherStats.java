package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeatherStats {

    String[][] cells;
    String[] headers;
    String[] rows;

    public WeatherStats() {

        //System.out.print("Input an absolute file path: ");
        //File file = new File(new Scanner(System.in).nextLine());
        File file = new File("C:\\Users\\vincentlomino19\\IdeaProjects\\Java\\WeatherDuringWWII\\src\\mainPackage\\Weather.csv");
        try (Scanner reader = new Scanner(file)) {
            rows = new String[0];
            while (reader.hasNextLine()) {
                rows = addToStringArray(rows, reader.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.exit(666);
        }

        headers = rows[0].split(",");
        cells = new String[rows.length - 1][headers.length];
        for (int i = 1; i <= headers.length; i++) {
            cells[i - 1] = rows[i].split(",");
        }
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
        /*DataTable WorkingTable = new DataTable();
        WorkingTable.printDataTable();
        *//*System.out.println("The headers are: ");
        for (int i = 0; i < WorkingTable.getHeaders().length; i++) {
            System.out.println(WorkingTable.getHeaders()[i]);
        }*/
    }
}

class DataTable {
    public String[] headers;
    public String[] rows;
    public String[][] flippedCells;
    public String[][] originalCells;
    public DataColumn[] columns;

    public DataTable() {
        //System.out.print("Input an absolute file path: ");
        //File file = new File(new Scanner(System.in).nextLine());
        File file = new File("C:\\Users\\vincentlomino19\\IdeaProjects\\Java\\WeatherDuringWWII\\src\\mainPackage\\Weather.csv");
        try (Scanner reader = new Scanner(file)) {
            rows = new String[0];
            while (reader.hasNextLine()) {
                rows = addToStringArray(rows, reader.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.exit(666);
        }

        headers = rows[0].split(",");
        originalCells = new String[rows.length - 1][headers.length];
        for (int i = 1; i <= headers.length; i++) {
            originalCells[i - 1] = rows[i].split(",");
        }

        flippedCells = new String[headers.length][rows.length - 1];
        for (int i = 0; i < originalCells.length; i++) {
            for (int j = 0; j < originalCells[i].length; j++) {
                flippedCells[j][i] = originalCells[i][j];
            }
        }

        columns = new DataColumn[headers.length];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new DataColumn(headers[i], flippedCells[i]);
        }
    }

    public String[] addToStringArray(String[] oldArray, String input) {
        String[] output = new String[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            output[i] = oldArray[i];
        }
        output[output.length - 1] = input;
        return output;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[][] getOriginalCells() {
        return originalCells;
    }

    public String[][] getFlippedCells() {
        return flippedCells;
    }

    public String[] getRows() {
        return rows;
    }

    public DataColumn[] getColumns() {
        return columns;
    }

    public void printDataTable() {
        for (int j = 0; j < columns.length / 10; j++) {
            for (int i = 0; i < columns[j].getStringArray().length; i++) {
                if (columns[j].getStringArray().length - 1 == i) {
                    System.out.print(columns[j].getStringArray()[i] + "\n");
                } else {
                    System.out.print(columns[j].getStringArray()[i] + ",\t\t\t");
                }
            }
        }
    }
}

class DataColumn {

    String header;
    String[] input;
    String[] stringArray;
    double[] doubleArray;
    char[] charArray;

    public DataColumn(String header, String[] input) {
        this.header = header;
        this.stringArray = input;
        this.input = input;
    }

    public void setToDouble() {
        try {
            for (int i = 0; i < input.length; i++) {
                doubleArray[i] = Double.parseDouble(input[i]);
            }
        } catch(Exception ex) {
            System.out.println("-----This operation did not process correctly-----\n-----Check data set type-----");
        }
    }

    public void setToChar() {
        try {
            for (int i = 0; i < input.length; i++) {
                doubleArray[i] = input[i].toCharArray()[0];
            }
        } catch(Exception ex) {
            System.out.println("-----This operation did not process correctly-----\n-----Check data set type-----");
        }
    }

    public String getHeader() {
        return header;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public double[] getDoubleArray() {
        return doubleArray;
    }

    public char[] getCharArray() {
        return charArray;
    }
}
