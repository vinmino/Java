package arraysAndMethods;

public class arraysAndMethods {

    public static void main(String[] args) {
        //makes an array of random length between 50 and 100 indices
        int [] array = new int[(int)(50 + Math.random() * (100 - 50 + 1))];

        //fills the array with random values between 1 and 500
        for (int i = 0; i < array.length; i++) {
            array[i]= (int)(Math.random() * (500 + 1));
        }

        //passes the array through two functions and prints the average and sum
        System.out.println("The average of the data set is: " + averageArray(array));
        System.out.println("The sum of the data set is: " + sumArray(array));

    }


    public static int averageArray(int[] array) {
        //adds each element of the array to the average variable, then divides this by the length of the array
        int average = 0;
        for (int num : array) {
            average += num;
        }
        return average / array.length;
    }

    public static int sumArray(int[] array) {
        //adds each element of the array to the sum variable and returns the value
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

}

//no, this would not make sense to use an anonymous array because you are using the array for more than one method.
//that means that you would have to create the method twice, and this is redundant
