
import java.util.Random;

public class MergeSort {

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        // Copy all items from lo to hi from org to aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = org[k];
        }
    
        int i = lo;
        int j = mid + 1;
    
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j++];
            } else if (j > hi) {
                org[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                org[k] = aux[i++];
            } else {
                org[k] = aux[j++];
            }
        }
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            sort(org, aux, lo, mid);
            sort(org, aux, mid + 1, hi);
            merge(org, aux, lo, mid, hi);
        }
    }

    public static void sort(int[] org) {
        if (org.length == 0) return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length - 1);
    }

    private static long benchmark(int n) {
        Random rnd = new Random();

        // Generate an array of size n
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2);
        }

        // Measure the time it takes to perform selection sort
        long startTime = System.nanoTime();
        sort(array); // Sorting the array
        long endTime = System.nanoTime();

        // Return elapsed time in nanoseconds
        return endTime - startTime;
    }

    public static void main(String[] args) {
        //int[] sizes = {10, 100, 500, 1000, 2000, 5000, 10000};
    
        int[] sizes = {10, 100, 500, 1000, 2000, 5000, 10000};

        //int[] sizes = {100, 200, 400, 800, 1600, 3200};

        // Warm-up phase
        for (int i = 0; i < 10; i++) {
            benchmark(1000);
        }
        
        // Benchmarking
        int trials = 10;
        System.out.println("Array Size | Execution Time (ns)");
        for (int n : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                totalTime += benchmark(n);
            }
            long avgTime = totalTime / trials;
            System.out.println(n + " | " + avgTime + " ns");
        }
    }
}
