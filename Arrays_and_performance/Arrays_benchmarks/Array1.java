import java.util.Random;

class Array1{

    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length ; i++) {
           sum = sum + array[indx[i]];
        }
        return sum;
    }

    public static long bench(int n, int loop) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i;
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) indx[i] = rnd.nextInt(n);
        int sum = 0;
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
        }
public static void main(String[] args) {
    // for (int i = 0; i < 10; i++) {
    //     long n0 = System.nanoTime();
    //     long n1 = System.nanoTime();
    //     System.out.println(" resolution " + (n1 - n0) + " nanoseconds");
    //     }

    // int[] given = {0,1,2,3,4,5,6,7,8,9};
    // int sum = 0;
    // for (int i = 0; i < 10; i++) {
    //     long t0 = System.nanoTime();
    //     sum += given[i];
    //     long t1 = System.nanoTime();
    //     System.out.println("one operation in " + (t1 - t0) + " ns");
    // }

    // int[] given = {0,1,2,3,4,5,6,7,8,9};
    // Random rnd = new Random();
    // int sum = 0;
    // long t0 = System.nanoTime();
    // for (int i = 0; i < 1000; i++) {
    //   sum += given[rnd.nextInt(10)];
    // }
    // long t1 = System.nanoTime();
    // System.out.println(" one read operation in " + (t1 - t0)/1000 + " nanoseconds");

    // for (int i = 0; i < 10; i++) {
    //     long t = bench(1000, 1000);
    //     System.out.println(" access: " + t + " ns");
    // }

    int n = 1000;
    int loop = 1000;
    int k = 10;
    long min = Long.MAX_VALUE;
    long max = 0;
    long total = 0;

    bench(n, 1000000);

    for (int i = 0; i < k; i++) {
      long t = bench(n, loop);
      if (t > max) max = t;
      if (t < min) min = t;
      total += t;
    }
    System.out.println(" avg: " + ((double) total)/loop/k);
    System.out.println(" min: " + ((double) min)/loop);
    System.out.println(" max: " + ((double) max)/loop);
 
    // int[] sizes = {100, 200, 400, 800, 1600, 3200};
    // // JIT warmup
    // bench(1000,10000000);
    // int loop = 1000;
    // int k = 10;
    // for(int n : sizes) {
    //   long min = Long.MAX_VALUE;
    //   for (int i = 0; i < k; i++) {
    //     long t = bench(n, loop);
    //     if (t < min) min = t;
    // }
    // System.out.println(n + " " + min + " ns");
    // }

}
}
