import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {




        // Create a Map instance with the existing train.csv file
        Map map = new Map("trains.csv");
        
        // Print the cities and their connections for verification
        for (City city : map.cities) {
            if (city != null) { // Check if the city is not null
                System.out.println("City: " + city.getN());
                for (Connection conn : city.connections) { // Make sure to check for null
                    if (conn != null) { // Check if the connection is not null
                        System.out.println("Connections: " + conn.neighbour.getN() + " (Distance: " + conn.time + ")");
                    }
                }
            }
        }


        // String filePath = "trains.csv"; // Update this to the correct path of your CSV file.
        // int mod = 80; // Set the mod value to 80 as discussed previously.
        
        // // Create a new Map instance
        // Map map = new Map(filePath, mod);
        
        // // Call the collisions method to analyze collisions
        // map.collisions(mod);
        
        // // Optional: You can print out the cities or perform other operations as needed.
        // // For example, if you want to print the cities:
        // map.printCities();



    
    }
}
