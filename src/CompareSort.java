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
        String[] bc10k = populate(bestCase,10000);
        String[] bc50k = populate(bestCase, 50000);
        String[] bc200k = populate(bestCase, 200000);
        String[] bc500k = populate(bestCase, 500000);
        String[] bc1m = populate(bestCase, 1000000);

        String[] ac10k = populate(averageCase,10000);
        String[] ac50k = populate(averageCase, 50000);
        String[] ac200k = populate(averageCase, 200000);
        String[] ac500k = populate(averageCase, 500000);
        String[] ac1m = populate(averageCase, 1000000);

        String[] wc10k = populate(worstCase,10000);
        String[] wc50k = populate(worstCase, 50000);
        String[] wc200k = populate(worstCase, 200000);
        String[] wc500k = populate(worstCase, 500000);
        String[] wc1m = populate(worstCase, 1000000);
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
