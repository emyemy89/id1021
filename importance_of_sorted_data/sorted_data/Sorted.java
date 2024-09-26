import java.util.Random;

public class Sorted {

    public static boolean sorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    // //  generate a sorted array
    // private static int[] generateSortedArray(int n) {
    //     Random rnd = new Random();
    //     int[] array = new int[n];
    //     int nxt = 0;
    //     for (int i = 0; i < n; i++) {
    //         nxt += rnd.nextInt(10) + 1;
    //         array[i] = nxt;
    //     }
    //     return array;
    // }

    private static long benchmark(int n, int key) {
        //int[] array = generateSortedArray(n);
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }

        // Start measuring time
        long startTime = System.nanoTime();
        sorted_search(array, key); 
        long endTime = System.nanoTime();

        // Return elapsed time in nanoseconds
        return endTime - startTime;
    }

    public static void main(String[] args) {
        //int[] sizes = {1000, 10000, 50000, 100000, 500000, 1000000}; 
        int[] sizes = {1000, 5000, 10000, 100000, 200000, 1000000}; // Array sizes
        int key = -1; 

        // warm-up  JIT
        for (int size : sizes) {
            for (int i = 0; i < 100; i++) {
                benchmark(size, key);
            }
        }

        // Perform benchmarking
        int trials = 100; 
        System.out.println("Array Size | Execution Time (ns)");
        for (int n : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                System.gc(); // Suggest GC to avoid heap buildup
                totalTime += benchmark(n, key);
            }
            long avgTime = totalTime / trials; 
            System.out.println(n + " | " + avgTime + " ns");
        }
    }
}
