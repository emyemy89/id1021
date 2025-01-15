public class Main {
    // public static void main(String[] args) {
    //     // Load the map from the CSV file
    //     Map map = new Map("europe.csv"); // Make sure this matches your Map constructor
    //     // Define the start and end cities
    //     City startCity = map.getCity("Malmö");
    //     City endCity = map.getCity("Kiruna");

    //     // Define a fixed value for mod
    //     int mod = 401; // or any suitable prime number
    //     if (startCity == null || endCity == null) {
    //         System.out.println("Start or end city not found.");
    //         return;
    //     }

    //     // Measure the time taken to find the shortest path
    //     long startTime = System.nanoTime(); // Start timing
    //     Path shortestPath = map.dijkstra(startCity, endCity, mod); // Pass mod here
    //     long endTime = System.nanoTime(); // End timing

    //     // Calculate the duration
    //     long duration = endTime - startTime;

    //     // Print the result
    //     if (shortestPath != null) {
    //         System.out.println("Shortest path from Malmö to Kiruna: " + shortestPath);
    //         System.out.println("Time taken (nanoseconds): " + duration);
    //         System.out.println("Time taken (milliseconds): " + duration / 1_000_000);
    //     } else {
    //         System.out.println("No path found from Malmö to Kiruna.");
    //     }
    // }


        public static void main(String[] args) {
            // Load the map from the CSV file
            Map map = new Map("europe.csv"); // Make sure this matches your Map constructor
    
            // Define the start and end cities
            City startCity = map.getCity("Turin");
            City endCity = map.getCity("Świnoujście");
    
            // Define a fixed value for mod
            int mod = 401; // or any suitable prime number
    
            if (startCity == null || endCity == null) {
                System.out.println("Start or end city not found.");
                return;
            }
    
            // Measure the time taken to find the shortest path
            long startTime = System.nanoTime(); // Start timing
            Path shortestPath = map.dijkstra(startCity, endCity, mod); // Pass mod here
            long endTime = System.nanoTime(); // End timing
    
            // Calculate the duration
            long duration = endTime - startTime;
    
            // Print the result
            if (shortestPath != null) {
                System.out.println("Shortest path from Malmö to Kiruna: " + reconstructFullPath(shortestPath));
                System.out.println("Time taken (nanoseconds): " + duration);
                System.out.println("Time taken (milliseconds): " + duration / 1_000_000);
            } else {
                System.out.println("No path found from Malmö to Kiruna.");
            }
        }
    
        private static String reconstructFullPath(Path path) {
            StringBuilder fullPath = new StringBuilder();
            while (path != null) {
                fullPath.insert(0, path.getCity().getN() + " -> ");
                path = path.getPreviousPath();
            }
            fullPath.delete(fullPath.length() - 4, fullPath.length()); // Remove the last " -> "
            return fullPath.toString();
        }
    

    
}
