import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Buckets {
    Bucket[] buckets;
    int maxKeys = 0;
    int bucketSize = 10000; // Assume less than 10,000 zip codes initially
    int lookupProbes;

    class Area {
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

    class Bucket {
        Area[] areas;
        int count;

        public Bucket() {
            this.areas = new Area[1];
            this.count = 0;
        }

        // public void addArea(Area area) {
        //     if (count == areas.length) {
        //         Area[] newAreas = new Area[areas.length * 2];
        //         for (int i = 0; i < areas.length; i++) {
        //             newAreas[i] = areas[i];
        //         }
        //         areas = newAreas;
        //     }
        //     areas[count++] = area;
        // }
    }

    public Buckets(String file) {
        this.buckets = new Bucket[bucketSize];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String areaName = row[1];
                int population = Integer.parseInt(row[2].trim());
                Area area = new Area(zipCode, areaName, population);
                addToBucket(area, zipCode);
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    // public void addToBucket(Area area, int zipCode) {
    //     int index = hashFunction(zipCode);
    //     if (buckets[index] == null) {
    //         buckets[index] = new Bucket();
    //     }
    //     buckets[index].addArea(area);
    // }


    public void addToBucket(Area area, int zipCode) {
        int index = hashFunction(zipCode);
        if (buckets[index] == null) {
            buckets[index] = new Bucket();
        }
        // Check if resizing is needed and add area
        Bucket bucket = buckets[index];
        if (bucket.count == bucket.areas.length) {
            Area[] newAreas = new Area[bucket.areas.length * 2];
            for (int i = 0; i < bucket.areas.length; i++) {
                newAreas[i] = bucket.areas[i];
            }
            bucket.areas = newAreas;
        }
        bucket.areas[bucket.count++] = area;
    }
    

    public int hashFunction(int key) {
        return key % bucketSize;
    }

    // public Area lookup(int zipCode) {
    //     int index = hashFunction(zipCode);
    //     if (buckets[index] != null) {
    //         for (int i = 0; i < buckets[index].count; i++) {
    //             if (buckets[index].areas[i].zipCode == zipCode) {
    //                 return buckets[index].areas[i];
    //             }
    //         }
    //     }
    //     return null;
    // }


    public Area lookup(int zipCode) {
        int index = hashFunction(zipCode);
        lookupProbes = 0;
        if (buckets[index] != null) {
            for (int i = 0; i < buckets[index].count; i++) {
                lookupProbes++;
                if (buckets[index].areas[i].zipCode == zipCode) {
                    return buckets[index].areas[i];
                }
            }
        }
        return null;
    }

    public void collisions(int mod) {
        int mx = 20;
        int[] data = new int[mod];
        int[] cols = new int[mx];
        
        for (Bucket bucket : buckets) {
            if (bucket != null) {
                for (int i = 0; i < bucket.count; i++) {
                    int index = bucket.areas[i].zipCode % mod;
                    data[index]++;
                }
            }
        }
        
        for (int i = 0; i < mod; i++) {
            if (data[i] < mx) {
                cols[data[i]]++;
            }
        }

        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }



    public static long benchmarkLookup(Buckets hashTable, int zipCode) {
        long startTime = System.nanoTime();
        hashTable.lookup(zipCode);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        // String fileName = "postnummer.csv";
        // Buckets keys = new Buckets(fileName);

        // // Add some zip codes manually for testing collisions
        // keys.addToBucket(keys.new Area(12345, "Test Area 1", 1000), 12345);
        // keys.addToBucket(keys.new Area(22345, "Test Area 2", 2000), 22345); // Same hash as 12345 for mod 10000
        // keys.addToBucket(keys.new Area(32345, "Test Area 3", 3000), 32345); // Same hash as 12345 for mod 10000

        // // Lookup to verify
        // int[] testZipCodes = {12345, 22345, 32345};
        // for (int zipCode : testZipCodes) {
        //     Buckets.Area area = keys.lookup(zipCode);
        //     if (area != null) {
        //         System.out.println("Found: " + area);
        //     } else {
        //         System.out.println("Not Found: " + zipCode);
        //     }
        // }

        // // Run collision detection with various mod values
        // int[] mods = {10000, 20000, 50000};
        // for (int mod : mods) {
        //     keys.collisions(mod);
        // }



        String fileName = "postnummer.csv";
        int zipCodeToTest = 11115; // The specific key to search for

        int[] sizes = {10000, 15000, 20000}; // Array sizes to test
        System.out.println("Zip Code | Array Size | Avg Lookup Time (ns) | Avg Probes");

        for (int size : sizes) {
            Buckets buckets = new Buckets(fileName);

            long totalTime = 0;
            int totalProbes = 0;
            int trials = 1000; // Increase number of trials for better averaging

            for (int i = 0; i < trials; i++) {
                buckets.lookupProbes = 0; // Ensure probes are reset
                long time = benchmarkLookup(buckets, zipCodeToTest);
                totalTime += time;
                totalProbes += buckets.lookupProbes;
            }

            System.out.println("Zip Code: " + zipCodeToTest + " | Array Size: " + size + " | Avg Lookup Time (ns): " + (totalTime / trials) + " | Avg Probes: " + (totalProbes / trials));
        }
    }
}
