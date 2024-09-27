import java.util.Random;

public class BinarySearch {

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        while (true) {
            // Jump to the middle
            int index = first + (last - first) / 2;

            if (array[index] == key) {
                return true; // Key found
            }

            if (array[index] < key && index < last) {
                // If key is greater, adjust the first possible page
                first = index + 1;
            } else if (array[index] > key && index > first) {
                // If key is smaller, adjust the last possible page
                last = index - 1;
            } else {
                // Key is not present in the array
                return false;
            }
        }
    }

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
        binary_search(array, key); 
        long endTime = System.nanoTime();

        // Return elapsed time in nanoseconds
        return endTime - startTime;
    }

    public static void main(String[] args) {
       // int[] sizes = {1000, 10000, 50000, 100000, 500000, 64000000};
       int[] sizes = {1000, 5000, 10000, 100000, 200000, 1000000}; // Array sizes 
        int key = -1; 
        

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
                System.gc();
                totalTime += benchmark(n, key);
            }
            long avgTime = totalTime / trials;
            System.out.println(n + " | " + avgTime + " ns");
        }


    }

}
