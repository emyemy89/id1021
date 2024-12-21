import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ZipInt {
    Area[] postnr;

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

    public ZipInt(String file, int max) {
        ArrayList<Area> areas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.valueOf(row[0].replaceAll("\\s", ""));
                areas.add(new Area(zipCode, row[1], Integer.parseInt(row[2].trim())));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
        this.postnr = new Area[Math.min(max, areas.size())];
        for (int i = 0; i < this.postnr.length; i++) {
            this.postnr[i] = areas.get(i);
        }
    }

    public Area lookup(int zipCode) {
        for (int i = 0; i < this.postnr.length; i++) {
            if (postnr[i] != null && postnr[i].zipCode == zipCode) {
                return postnr[i];
            }
        }
        return null;
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

    private static long benchmark(ZipInt zipTable, int zipCode, boolean useBinarySearch) {
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
        int zipCodeToTest = 98499;  // The 11115
        boolean useBinarySearch =true; // Set to true to use binary search

        System.out.println("Zip Code | Array Size | Execution Time (ns)");

        int size = 10000;
        ZipInt zipTable = new ZipInt(fileName, size);

        long totalTime = 0;
        int trials = 100;
        for (int i = 0; i < trials; i++) {
            long time = benchmark(zipTable, zipCodeToTest, useBinarySearch);
            totalTime += time;
        }

        System.out.println("Zip Code: " + zipCodeToTest + " | Array Size: " + size + " | Avg Time (ns): " + (totalTime / trials));
    }
}
