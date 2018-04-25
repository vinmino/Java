package sumOf5x5;

public class sumOf5x5 {

    //method to fill an array with a specific amount of random numbers based on its size
    static int[][] twoDRandomNumbers(int[][] array) {
        int[][] tempArray = new int[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                tempArray[i][j] = (int)(1 + Math.random() * (100 - 1));
            }
        }
        return tempArray;
    }

    //method that adds the digits of the array of a multidimensional array
    static int sum2DArray(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        //initialize the array
        int[][] multiArray = new int[5][5];
        multiArray = twoDRandomNumbers(multiArray);

        //this prints the results
        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                if ( (j + 1) % 5 == 0) {
                    System.out.print(multiArray[i][j] + "\n");
                } else {
                    System.out.print(multiArray[i][j] + ", ");
                }
            }
        }
        System.out.print("The sum of all digits in the array is " + sum2DArray(multiArray));

    }

}
