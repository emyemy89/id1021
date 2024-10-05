import java.util.Random;

public class SelectionSort{


    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // Assume i is the smallest
            int candidate = i;

            // find the smallest element from i to the end
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[candidate]) {
                    candidate = j; // Update candidate 
                }
            }

            // Swap 
            int temp = array[candidate];
            array[candidate] = array[i];
            array[i] = temp;
        }
    }



    private static long benchmark(int n) {
        Random rnd = new Random();

        // Generate an array of size n
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2); // Values between 0 and 2n
        }

        // Measure the time it takes to perform selection sort
        long startTime = System.nanoTime();
        selectionSort(array); // Sorting the array
        long endTime = System.nanoTime();

        // Return elapsed time in nanoseconds
        return endTime - startTime;
    }

    public static void main(String[] args) {
         //int[] sizes = {1000, 10000, 50000, 100000, 500000, 1000000}; // Array sizes
        // int[] sizes = {1000, 5000, 10000, 100000, 200000, 1000000}; // Array sizes
        int[] sizes = {10,100, 200, 400, 1000, 2000, 5000}; // Array sizes
          
        //int key = -1; // Key to search for, using a value that's not in the array for worst-case performance
 
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