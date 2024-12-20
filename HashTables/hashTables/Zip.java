import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Zip {
    Area[] postnr;

    public class Area {
        String zipCode;
        String areaName;
        int population;

        public Area(String zipCode, String areaName, int population) {
            this.zipCode = zipCode.trim();
            this.areaName = areaName.trim();
            this.population = population;
        }

        @Override
        public String toString() {
            return "Zip Code: " + zipCode + ", Area: " + areaName + ", Population: " + population;
        }
    }

    public Zip(String file, int max) {
        ArrayList<Area> areas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                areas.add(new Area(row[0].trim(), row[1].trim(), Integer.parseInt(row[2].trim())));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
        this.postnr = new Area[Math.min(max, areas.size())];
        for (int i = 0; i < this.postnr.length; i++) {
            this.postnr[i] = areas.get(i);
        }
    }

    public Area lookup(String zipCode) {
        zipCode = zipCode.trim();
        for (int i = 0; i < postnr.length; i++) {
            if (postnr[i] != null && postnr[i].zipCode.equals(zipCode)) {
                return postnr[i];
            }
        }
        return null;
    }

    // public Area binarySearch(String zipCode, int left, int right) {
    //     if (left > right) {
    //         return null;
    //     }
    //     int mid = left + (right - left) / 2;
    //     String midZipCode = postnr[mid].zipCode;
    //     if (midZipCode.equals(zipCode)) {
    //         return postnr[mid];
    //     } else if (midZipCode.compareTo(zipCode) < 0) {
    //         return binarySearch(zipCode, mid + 1, right);
    //     } else {
    //         return binarySearch(zipCode, left, mid - 1);
    //     }
    // }

    public Area binarySearch(String zipCode, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        int midZipCode = Integer.valueOf(postnr[mid].zipCode.replaceAll("\\s", ""));
        int target = Integer.valueOf(zipCode.replaceAll("\\s", ""));
        if (midZipCode == target) {
            return postnr[mid];
        } else if (midZipCode < target) {
            return binarySearch(zipCode, mid + 1, right);
        } else {
            return binarySearch(zipCode, left, mid - 1);
        }
    }




    private static long benchmark(Zip zipTable, String zipCode, boolean useBinarySearch) {
        long startTime = System.nanoTime();
        if (useBinarySearch) {
            zipTable.binarySearch(zipCode, 0, zipTable.postnr.length - 1);
        } else {
            zipTable.lookup(zipCode);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        String fileName = "postnummer.csv";
        String zipCodeToTest = "98499";  // 11115
        boolean useBinarySearch = false; // Set to true to use binary search

        System.out.println("Zip Code | Array Size | Execution Time (ns)");

        int size = 10000; // A fixed, reasonable array size
        Zip zipTable = new Zip(fileName, size);

        long totalTime = 0;
        int trials = 100;
        for (int i = 0; i < trials; i++) {
            long time = benchmark(zipTable, zipCodeToTest, useBinarySearch);
            totalTime += time;
        }

        System.out.println("Zip Code: " + zipCodeToTest + " | Array Size: " + size + " | Avg Time (ns): " + (totalTime / trials));
    }
}
