import java.io.BufferedReader;
import java.io.FileReader;

public class OpenAddressing {
    Area[] table;
    int size;
    int count;
    int lookupProbes; // Tracks the number of probes for the current lookup

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

    public OpenAddressing(String file, int size) {
        this.size = size * 2; // Make the array twice as large as needed
        this.table = new Area[this.size];
        this.count = 0;
        this.lookupProbes = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String areaName = row[1];
                int population = Integer.parseInt(row[2].trim());
                Area area = new Area(zipCode, areaName, population);
                add(area);
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
        System.out.println("Initialization complete. Size: " + size);
    }

    public void add(Area area) {
        int index = hashFunction(area.zipCode);
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = area;
        count++;
    }

    public int hashFunction(int key) {
        return key % size;
    }

    public Area lookup(int zipCode) {
        int index = hashFunction(zipCode);
        lookupProbes = 0;
        while (table[index] != null) {
            lookupProbes++;
            if (table[index].zipCode == zipCode) {
                return table[index];
            }
            index = (index + 1) % size;
        }
        lookupProbes++; // Count the final probe attempt
        return null;
    }

    public static long benchmark(OpenAddressing hashTable, int zipCode) {
        long startTime = System.nanoTime();
        hashTable.lookup(zipCode);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        String fileName = "postnummer.csv";
        int zipCodeToTest = 11115; // 98499

        int[] sizes = {10000, 20000,30000}; // Array sizes to test
        System.out.println("Zip Code | Array Size | Avg Lookup Time (ns) | Avg Probes");

        for (int size : sizes) {
            OpenAddressing openAddressing = new OpenAddressing(fileName, size);

            long totalTime = 0;
            int totalProbes = 0;
            int trials = 100; // Number of trials for each array size

            for (int i = 0; i < trials; i++) {
                long time = benchmark(openAddressing, zipCodeToTest);
                totalTime += time;
                totalProbes += openAddressing.lookupProbes;
            }

            System.out.println("Zip Code: " + zipCodeToTest + " | Array Size: " + size + " | Avg Lookup Time (ns): " + (totalTime / trials) + " | Avg Probes: " + (totalProbes / trials));
        }
    }
}
