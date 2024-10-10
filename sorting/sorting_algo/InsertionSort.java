import java.util.Random;

public class InsertionSort {

    //swap two elements in an array
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // Move the element at index i to its correct position in the sorted part
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                // Swap if current smaller than previous
                swap(array, j, j - 1); 
            }
        }
    }

    //unsorted

    private static long benchmark(int n) {
        Random rnd = new Random();

        // Generate an array of size n
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2); // Values between 0 and 2n
        }

        // Measure the time it takes to perform selection sort
        long startTime = System.nanoTime();
        insertionSort(array); // Sorting the array
        long endTime = System.nanoTime();

        // Return elapsed time in nanoseconds
        return endTime - startTime;
    }


    //sorted bench

    // private static long benchmark(int n) {
    //     //int[] array = generateSortedArray(n);
    //     Random rnd = new Random();
    //     int[] array = new int[n];
    //     int nxt = 0;
    //     for (int i = 0; i < n; i++) {
    //         nxt += rnd.nextInt(10) + 1;
    //         array[i] = nxt;
    //     }

    //     // Start measuring time
    //     long startTime = System.nanoTime();
    //     insertionSort(array); 
    //     long endTime = System.nanoTime();

    //     // Return elapsed time in nanoseconds
    //     return endTime - startTime;
    // }

    

    // Main method to test the insertion sort algorithm
    public static void main(String[] args) {
        int[] sizes = {10,100, 200, 400, 1000, 2000, 5000}; 
          
        
         // Extended warm-up for JIT
         for (int i = 0; i < 100; i++) {
             benchmark(1000);
             benchmark(10000);
         }
 
         // benchmarking
         int trials = 100; // more trials
         System.out.println("Array Size | Execution Time (ns)");
         for (int n : sizes) {
             long totalTime = 0;
             for (int i = 0; i < trials; i++) {
                 System.gc(); // Suggest GC to avoid heap buildup
                 totalTime += benchmark(n);
             }
             long avgTime = totalTime / trials; // Average time over all trials
             System.out.println(n + " | " + avgTime + " ns");
         }
    }
}

