public class Main {


        public static void main(String[] args) {
            
            Map map = new Map("europe.csv"); 
    
            City startCity = map.getCity("Turin");
            City endCity = map.getCity("Świnoujście");
    
            int mod = 401; 
    
            if (startCity == null || endCity == null) {
                System.out.println("Start or end city not found.");
                return;
            }
    

            long startTime = System.nanoTime(); 
            Path shortestPath = map.dijkstra(startCity, endCity, mod); 
            long endTime = System.nanoTime();
    
            long duration = endTime - startTime;
    
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
