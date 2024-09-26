import java.util.Random;

public class Unsorted {

    
    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }



  
    private static long benchmark(int n, int key) {
        Random rnd = new Random();
        
        // Generate an array 
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2); // Values between 0 and 2n
        }

        // measurie time
        long startTime = System.nanoTime();
        unsorted_search(array, key); // Search for the key in the array
        long endTime = System.nanoTime();

        // Return elapsed time 
        return endTime - startTime;
    }



    public static void main(String[] args) {
        //int[] sizes = {1000, 10000, 50000, 100000, 500000, 1000000}; // Array sizes
        int[] sizes = {1000, 5000, 10000, 100000, 200000, 1000000}; // Array sizes
        int key = -1; // Key to search for, using a value that's not in the array for worst-case performance

        // Extended warm-up for JIT
        for (int i = 0; i < 100; i++) {
            benchmark(1000, key);
            benchmark(10000, key);
        }

        // benchmarking
        int trials = 100; // more trials
        System.out.println("Array Size | Execution Time (ns)");
        for (int n : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                System.gc(); // Suggest GC to avoid heap buildup
                totalTime += benchmark(n, key);
            }
            long avgTime = totalTime / trials; // Average time over all trials
            System.out.println(n + " | " + avgTime + " ns");
        }
    }
}

