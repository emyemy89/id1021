import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Key {
    Area[] postnr;
    int[] keys = new int[10000];
    
    public class Area {
        int zipCode;
        String areaName;
        int population;
        
        public Area(int zipCode, String areaName, int population) {
            this.zipCode = zipCode;
            this.areaName = areaName.trim();
            this.population = population;
        }
        
        @Override
        public String toString() {
            return "Zip Code: " + zipCode + ", Area: " + areaName + ", Population: " + population;
        }
    }
    
    public Key(String file) {
        this.postnr = new Area[100000]; // Array with a size of 100,000 for zip codes up to 99999
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.valueOf(row[0].replaceAll("\\s", ""));
                postnr[zipCode] = new Area(zipCode, row[1], Integer.parseInt(row[2].trim()));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }
    
    public Area lookup(int zipCode) {
        return postnr[zipCode];
    }
    
    public Area binarySearch(int zipCode, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        int midZipCode = postnr[mid].zipCode;

        if (midZipCode == zipCode) {
            return postnr[mid];
        } else if (midZipCode < zipCode) {
            return binarySearch(zipCode, mid + 1, right);
        } else {
            return binarySearch(zipCode, left, mid - 1);
        }
    }
    
    private static long benchmark(Key zipTable, int zipCode, boolean useBinarySearch) {
        // Warm up to ensure JIT optimization
        for (int i = 0; i < 1000000; i++) {
            zipTable.lookup(zipCode);
        }

        // Actual benchmark
        long startTime = System.nanoTime();
        if (useBinarySearch) {
            zipTable.binarySearch(zipCode, 0, zipTable.postnr.length - 1);
        } else {
            zipTable.lookup(zipCode);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }



    public void collisions(int mod) {
        int max = 20;
        int[] data = new int[mod];
        int[] cols = new int[max];
    
        for (int i = 0; i < max; i++) {
            int index = keys[i] % mod;
            data[index]++;
        }
    
        for (int i = 0; i < mod; i++) {
            if (data[i] < max) {
                cols[data[i]]++;
            }
        }
    
        System.out.print(mod + ": ");
        for (int i = 1; i < max; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }
    

    
    public static void main(String[] args) {
        String fileName = "postnummer.csv";
        int zipCodeToTest = 11115;  // Change this to 98499 to test the other
        boolean useBinarySearch = false; // Set to true to use binary search
        
        System.out.println("Zip Code | Array Size | Execution Time (ns)");
        
        // Array sizes to test
        int[] sizes = {100, 1000, 2000, 5000, 10000, 50000};
        for (int size : sizes) {
            Key zipTable = new Key(fileName);
            
            long minTime = Long.MAX_VALUE;
            int trials = 100;
            for (int i = 0; i < trials; i++) {
                long time = benchmark(zipTable, zipCodeToTest, useBinarySearch);
                if (time < minTime) {
                    minTime = time;
                }
            }
            
            System.out.println(zipCodeToTest + " | " + size + " | " + minTime + " ns");
        }



        // Run collision detection with various mod values

        // String fileName = "postnummer.csv";
        // ZipInt zipTable = new ZipInt(fileName, size);

        // int[] mods = {10000, 20000, 50000};
        // for (int mod : mods) {
        //     zipTable.collisions(mod);
        // }


    }
}
