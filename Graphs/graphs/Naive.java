public class Naive {
    // public static void main(String[] args) {
    //     Map map = new Map("trains.csv"); // Load the map from the CSV file
    //     String from = args[0]; // Starting city
    //     String to = args[1]; // Destination city
    //     Integer max = Integer.valueOf(args[2]); // Maximum allowed time

    //     long t0 = System.nanoTime(); // Start time for performance measurement
    //     Integer dist = shortest(map.lookup(from), map.lookup(to), max); // Calculate shortest path
    //     long time = (System.nanoTime() - t0) / 1_000_000; // Calculate elapsed time

    //     // Output the result
    //     if (dist == null) {
    //         System.out.println("No path found within the allowed time.");
    //     } else {
    //         System.out.println("Shortest: " + dist + " min (" + time + " ms)");
    //     }
    // }

    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0) return null;  // If max time is negative, return null
        if (from==to) return 0;   // If we are at the destination, return 0
    
        Integer shrt = null;  // Variable to track the shortest distance found
    
        // Loop through all neighbors of the current city
        Connection[] neighbors = from.getConnections(); // Get the connections from the city
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] != null) {
                Connection conn = neighbors[i]; // Get the connection to the neighbor
                City neighbor = conn.getN(); // Get the neighboring city
                Integer distance = conn.getT(); // Get the travel time to the neighbor
    
                // Calculate remaining time after taking this connection
                Integer remainingMax = max - distance; 
    
                // Recursive call to find the shortest path from the neighbor to the destination
                Integer dist = shortest(neighbor, to, remainingMax); 
    
                // If a valid path is found
                if (dist != null) {
                    // Calculate total distance including the distance to the neighbor
                    Integer totalDist = dist + distance; 
    
                    // Update the shortest distance if needed
                    if (shrt == null || totalDist < shrt) {
                        shrt = totalDist; 
                    }
                }
            }
        }
        return shrt; // Return the shortest distance found
    }
    
    




    public static void main(String[] args) {


    //     Map map = new Map("trains.csv"); // Load the map from the CSV file
        
    //     // Define a single trip to test
    //     String from = "Malmö";
    //     String to = "Göteborg";
    //     Integer max = 300; // Set a sufficient max time
    
    //     long t0 = System.nanoTime(); // Start time for performance measurement
    //     Integer dist = shortest(map.lookup(from), map.lookup(to), max); // Calculate shortest path
    //     long time = (System.nanoTime() - t0) / 1_000_000; // Calculate elapsed time
    
    //     // Output the result
    //     if (dist == null) {
    //         System.out.println("No path found from " + from + " to " + to + " within " + max + " minutes.");
    //     } else {
    //         System.out.println("Shortest path from " + from + " to " + to + ": " + dist + " min (" + time + " ms)");
    //     }
    // }


    Map map = new Map("trains.csv"); // Load the map from CSV
        // Define the routes to benchmark
        String[][] routes = {
            {"Malmö", "Göteborg", "300"},
            {"Göteborg", "Stockholm", "500"},
            {"Malmö", "Stockholm", "500"},
            {"Stockholm", "Sundsvall", "500"},
            {"Stockholm", "Umeå", "500"}, // Ensure you have connections for Umeå
            {"Sundsvall", "Umeå", "300"},
            {"Umeå", "Göteborg", "1000"},
            {"Göteborg", "Umeå", "2000"}
        };

        for (String[] route : routes) {
            String from = route[0];
            String to = route[1];
            Integer max = Integer.valueOf(route[2]);

            long t0 = System.nanoTime(); // Start timing
            Integer dist = shortest(map.lookup(from), map.lookup(to), max);
            long time = (System.nanoTime() - t0) / 1_000_000; // Calculate elapsed time

            if (dist != null) {
                System.out.println("Shortest path from " + from + " to " + to + ": " + dist + " min (" + time + " ms)");
            } else {
                System.out.println("No valid path from " + from + " to " + to + " within the max time of " + max + " min. (" + time + " ms)");
            }
        }
    }

    


}
