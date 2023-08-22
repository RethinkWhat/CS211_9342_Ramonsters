import java.lang.*;
import java.util.*;

public class CompareSort implements SortingAlgorithms{
    public static void main(String[] args) {
        try{
            CompareSort program = new CompareSort();
            program.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() throws Exception{

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
    public String[] bubbleSort(String[] arr){
        String[] sorted = copyArray(arr);

        //Bubble Sort Algorithm Here :)))

        return sorted;
    }

    /**
     * @author PATRICK
     * @param arr
     * @return
     */
    public String[] selectionSort(String[] arr){
        String[] sorted = copyArray(arr);

        //Selection Sort Algorithm Here :)))

        return sorted;
    }

    /**
     * @author JOHAN
     * @param arr
     * @return
     */
    public String[] insertionSort(String[] arr){
        String[] sorted = copyArray(arr);

        //Insertion Sort Algorithm Here :)))

        return sorted;
    }
}
