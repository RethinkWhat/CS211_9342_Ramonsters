import java.lang.*;
import java.util.*;
import java.io.*;

public class CompareSort implements SortingAlgorithms{

    public final File bestCase = new File("res/best-case.txt");
    public final File averageCase = new File("res/average-case.txt");
    public final File worstCase = new File("res/worst-case.txt");

    public static void main(String[] args) {
        try{
            CompareSort program = new CompareSort();
            program.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() throws Exception{
        String[] bestCase10K = populate(bestCase,10000);
        String[] bestCase50K = populate(bestCase, 50000);
        String[] bestCase200K = populate(bestCase, 200000);
        String[] bestCase500K = populate(bestCase, 500000);
        String[] bestCase1M = populate(bestCase, 1000000);

        String[] averageCase10K = populate(averageCase,10000);
        String[] averageCase50K = populate(averageCase, 50000);
        String[] averageCase200K = populate(averageCase, 200000);
        String[] averageCase500K = populate(averageCase, 500000);
        String[] averageCase1M = populate(averageCase, 1000000);

        String[] worstCase10K = populate(worstCase,10000);
        String[] worstCase50K = populate(worstCase, 50000);
        String[] worstCase200K = populate(worstCase, 200000);
        String[] worstCase500K = populate(worstCase, 500000);
        String[] worstCase1M = populate(worstCase, 1000000);
    }

    public String[] populate(File file, int lines) throws FileNotFoundException{
        Scanner fileScanner = new Scanner(file);
        String[] populated = new String[lines];

        int index = 0;

        while (fileScanner.hasNextLine() && index < lines){
            populated[index] = fileScanner.nextLine();
            index++;
        }

        fileScanner.close();

        return populated;
    }

    public String[] copyArray(String[] arr){
        String[] copied = new String[arr.length];

        for (int i = 0; i < copied.length; i++){
            copied[i] = arr[i];
        }

        return copied;
    }

    /**
     * @author RITHIK
     * @param arr
     * @return
     */
     /*
        Algorithm:
            1. Accept an ArrayList as parameters
            2. Create a copy of arr by calling copyArray() method
            3. Create a for loop to iterate till nameList.length -1 (x as increment value)
            4. Create a for loop to iterate  till nameList.length -1 - x(y as increment value)
            5. Create an if statement to check if a condition is true
            6. Create a temp variable and assign it the value of arr[x]
            7. Set arr[x] equal to arr[y]
            8. Set arr[y] to the temp variable
            9. Return the sorted array
     */
    public String[] bubbleSort(String[] arr){
        int outerLoopCount = 0;
        int innerLoopCount = 0;
        int comparisonCount = 0;
        int swapCount = 0;
        int statementsCount = 0;

        int i = 0;
        int j = 0;
        String[] sorted = copyArray(arr);
        int declareCount = 3;                                   // Deals with the i and j declared and the sorted variable

        for (; i < arr.length-1; i++) {
            outerLoopCount+=2;                                  // Deals with the 2 statements executed in the for loop above it
            for (; j<arr.length-1-i; j++) {
                innerLoopCount+=2;                              // Deals with the 2 statements executed in the for loop above it
                comparisonCount++;                              // Deals with the comparison done below
                if (arr[j].compareToIgnoreCase(arr[j+1])>0) {
                    String temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapCount+=3;                               // Deals with the three switch statements above
                }
            }
        }
        statementsCount = outerLoopCount + innerLoopCount + comparisonCount + swapCount + statementsCount + declareCount + 1;
        // +1 because of the return sorted statement below

        System.out.println("The total number of executed statements are " + statementsCount + ".");
        return sorted;
    }

    /**
     * @author PATRICK
     * @param arr
     * @return
     */
    public String[] selectionSort(String[] arr){

        String[] sorted = copyArray(arr);


        for (int i = 0; i < sorted.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[j].compareTo(sorted[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = sorted[i];
            sorted[i] = sorted[minIndex];
            sorted[minIndex] = temp;
        }

        return sorted;
    }

    /**
     * @author JOHAN RICKARDO A. ROXAS
     * @param arr given array
     * @return
     */
    public String[] insertionSort(String[] arr){
        String[] sorted = copyArray(arr);

        for (int i = 0; i < sorted.length; i++) {
            String key = sorted[i];
            int j = i - 1;
            while (j >= 0) {
                if (key.compareToIgnoreCase(sorted[j]) < 0) {
                    sorted[i + 1] = sorted[j];
                    j = j -1 ;
                }
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }
}
