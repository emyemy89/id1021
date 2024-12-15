import java.io.BufferedReader;
import java.io.FileReader;

public class Keys {
    int[] keys = new int[10000]; // Allocate space for 10,000 zip codes
    int maxKeys = 0;

    public Keys(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.valueOf(row[0].replaceAll("\\s", ""));
                keys[maxKeys++] = zipCode; // Populate keys array
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    public void collisions(int mod) {
        int mx = 20;
        int[] data = new int[mod];
        int[] cols = new int[mx];

        for (int i = 0; i < maxKeys; i++) {
            int index = keys[i] % mod;
            data[index]++;
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

    public static void main(String[] args) {
        String fileName = "postnummer.csv";
        Keys keys = new Keys(fileName);

        int[] mods = {14768, 28474, 83746};
        for (int mod : mods) {
            keys.collisions(mod);
        }
    }
}
