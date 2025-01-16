import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Map {
    private HashMap<String, City> cities; // Use a HashMap for faster lookups

    public Map(String file) {
        cities = new HashMap<>(); // Initialize the map of cities
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length < 3) continue; // Skip invalid lines
                String cityName1 = row[0].trim(); // First city name
                String cityName2 = row[1].trim(); // Second city name
                int distance = Integer.parseInt(row[2].trim()); // Distance between cities
                // Lookup or create the first city
                City cityOne = lookup(cityName1);
                // Lookup or create the second city
                City cityTwo = lookup(cityName2);
                // Add connections between the two cities
                cityOne.connect(cityTwo, distance);
                cityTwo.connect(cityOne, distance);
                // Print the connection
                System.out.println(cityName1 + " <-> " + cityName2 + " : " + distance);
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found or corrupt");
        }
    }
    

    // Method to lookup or create a city
    private City lookup(String cityName) {
        return cities.computeIfAbsent(cityName, k -> new City(k, cities.size()));
    }

    public City getCity(String name) {
        return cities.get(name);
    }

    public Path dijkstra(City src, City dst, int mod) {
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.dijkstra(src, dst, mod);
    }
}
