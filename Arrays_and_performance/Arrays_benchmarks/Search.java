import java.util.Random;

public class Search {
    
    private static long search(int n, int loop) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2);
        }
        
        // Use 'loop' to define the size of the 'keys' array
        int[] keys = new int[loop];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n * 2);
        }
        
        int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            for (int j = 0; j < n; j++) {
                if (key == array[j]) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    
    public static void main(String[] args) {
        int[] sizes = {200, 500, 700, 1000, 1500, 2500};
        // JIT warmup
        search(1000, 10000000);
        int loop = 15000;
        int k = 10; // Number of trials
        
        System.out.println("Array Size | Execution Time (ns)");
        for (int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
                if (t < min) {
                    min = t;
                }
            }
            System.out.println(n + " | " + min + " ns");
        }
    }
}
