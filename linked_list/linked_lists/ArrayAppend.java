import java.util.Random;

public class ArrayAppend {

    // Array append method
    public static int[] append(int[] arrayA, int[] arrayB) {
        int n = arrayA.length;
        int m = arrayB.length;
        
        // Create a new array with size n + m
        int[] result = new int[n + m];
        System.arraycopy(arrayA, 0, result, 0, n);
        System.arraycopy(arrayB, 0, result, n, m);
        
        return result;
    }



    // Benchmark method
    private static long benchmark(int sizeA, int sizeB) {
        Random rnd = new Random();

        // Generate arrayA of size sizeA
        int[] arrayA = new int[sizeA];
        for (int i = 0; i < sizeA; i++) {
            arrayA[i] = rnd.nextInt(sizeA * 2); 
        }

        // Generate arrayB of fixed size
        int[] arrayB = new int[sizeB];
        for (int i = 0; i < sizeB; i++) {
            arrayB[i] = rnd.nextInt(sizeB * 2); 
        }

        long startTime = System.nanoTime();
        append(arrayA, arrayB);
        long endTime = System.nanoTime();

        return endTime - startTime; 
    }

    public static void main(String[] args) {

      // int[] sizes = {100, 1000, 5000, 10000, 100000};
     // int[] sizes = {100, 500, 1000, 2000, 4000, 8000, 12000}; 
     int[] sizes = {100, 200, 500, 1000, 2000, 5000, 10000};

        int fixedSizeB = 10; // Fixed size for arrayB

        // Warm-up runs
        for (int i = 0; i < 100; i++) {
            benchmark(1000, fixedSizeB); // Warm-up to stabilize JVM
        }

        // Benchmarking
        int trials = 100; // Increased number of trials
        System.out.println("SizeA | Execution Time (ns)");
        for (int n : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                totalTime += benchmark(n, fixedSizeB);
            }
            long avgTime = totalTime / trials;
            System.out.println(n + " | " + avgTime + " ns");
        }
    }
}
