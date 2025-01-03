public class Map1 {
    private City[][] table; // 2D array for chaining
    private int size; // Current number of elements in the hash table
    private int capacity; // Size of the hash table

    public Map1(int capacity) {
        this.capacity = capacity;
        table = new City[capacity][]; // Initialize the table as a 2D array
        size = 0;
    }

    private static Integer hash(String name, int mod) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }

    // public void addCity(City city) {
    //     int index = hash(city.getN(), capacity); // Hash the city name
    //     // Ensure there is a sub-array for this index
    //     if (table[index] == null) {
    //         table[index] = new City[capacity]; // Create a new array at this index
    //     }
    //     // Find the next available slot in the sub-array
    //     for (int i = 0; i < table[index].length; i++) {
    //         if (table[index][i] == null) { // Find empty spot
    //             table[index][i] = city; // Add city to the appropriate bucket
    //             size++;
    //             break;
    //         }
    //     }
    // }

    // public City lookup(String name) {
    //     int index = hash(name, capacity); // Get the index for the hashed name
    //     if (table[index] != null) { // Ensure there's a sub-array at this index
    //         for (int i = 0; i < table[index].length; i++) {
    //             if (table[index][i] != null && table[index][i].getN().equals(name)) { // Compare city name with the input
    //                 return table[index][i]; // Return the city if found
    //             }
    //         }
    //     }
    //     return null; // Return null if city not found
    // }



    public City lookup(String name) {
        int index = hash(name, capacity); // Get the index for the hashed name
        // Initialize sub-array if not present
        if (table[index] == null) {
            table[index] = new City[capacity]; // Create a new array at this index
        }
        // Check if the city is already in the bucket
        for (int i = 0; i < table[index].length; i++) {
            if (table[index][i] != null && table[index][i].getN().equals(name)) {
                return table[index][i]; // Return the city if found
            }
        }

        // If not found, add a new city
        City newCity = new City(name); // Create a new city object
        for (int i = 0; i < table[index].length; i++) {
            if (table[index][i] == null) { // Find an empty spot
                table[index][i] = newCity; // Add the new city to the bucket
                return newCity; // Return the newly added city
            }
        }
        return null; // In case all spots are filled 
    }




    public void collisions(int mod) {
        int max = 20; // Maximum number of elements to consider
        int[] data = new int[mod]; // Array to count the number of cities in each bucket
        int[] cols = new int[max]; // Array to count how many buckets have a specific number of collisions
    
        // Count how many cities hash to each index
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                data[i] = 0; // Initialize counter for this bucket
                for (int j = 0; j < table[i].length; j++) {
                    if (table[i][j] != null) {
                        data[i]++; // Count the cities in this bucket
                    }
                }
            }
        }
    
        // Count how many buckets have a certain number of cities
        for (int i = 0; i < mod; i++) {
            if (data[i] < max) {
                cols[data[i]]++; // Count the collisions for each bucket
            }
        }
    
        // Print the results
        System.out.print(mod + ": ");
        for (int i = 1; i < max; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }
}
