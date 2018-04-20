package size25Array;

import java.util.Arrays;

public class arrayFun {

    public static int average(int[] array) {
        int average = 0;
        for (int num : array) {
            average += num;
        }
        average /= 20;
        return average;
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < 20; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }

    public static int min(int[] array) {
        int min = array[0];
        for (int i = 1; i < 20; i++) {
            if (array[i] < min) min = array[i];
        }
        return min;
    }

    public static int median(int[] array) {
        int[] carbonCopy = new int[20];
        for (int i = 0; i < 20; i++) { //makes a copy of the part of the array that matters so the sort won't throw everything off
            carbonCopy[i] = array[i];
        }
        Arrays.sort(carbonCopy); //orders the cloned one from least to greatest
        int median;
        median = (carbonCopy[10] + carbonCopy[11]) / 2;
        return median;
    }

    public static int totalSum(int[] array) {
        int sum = 0;
        for (int i = 0; i < 20; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static void main(String[] args) {

        int[] array = new int[25];

        for (int i = 0; i < 20; i++) { //fills the array
            array[i] = (int)(Math.random() * 100 + 1);
        }

        array[20] = average(array);
        array[21] = min(array);
        array[22] = max(array);
        array[24] = totalSum(array);
        array[23] = median(array);

        System.out.printf("The average is: %d\nThe minimum value is:%d\nThe maximum value is:%d\nThe median is:%d\nThe total sum is:%d" , array[20], array[21], array[22], array[23], array[24]);


    }


}
