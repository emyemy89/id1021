import java.util.*;
import java.io.*;
import java.util.AbstractMap;

public class Pathfinding {

    static class Edge {
        String destination;
        int distance;

        Edge(String destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        // Reading the CSV data
        HashMap<String, List<Edge>> graph = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("europe.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String city1 = parts[0];
            String city2 = parts[1];
            int distance = Integer.parseInt(parts[2]);
            graph.putIfAbsent(city1, new ArrayList<>());
            graph.putIfAbsent(city2, new ArrayList<>());
            graph.get(city1).add(new Edge(city2, distance));
            graph.get(city2).add(new Edge(city1, distance));
        }
        br.close();

        // Finding shortest paths
        String startCity = "Malmö"; // Change this as needed
        HashMap<String, Integer> distances = new HashMap<>();
        HashMap<String, String> cameFrom = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.add(new AbstractMap.SimpleEntry<>(startCity, 0));
        distances.put(startCity, 0);

        long startTime = System.nanoTime();
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            String currentCity = entry.getKey();
            int currentDistance = entry.getValue();

            for (Edge edge : graph.get(currentCity)) {
                int newDist = currentDistance + edge.distance;
                if (newDist < distances.getOrDefault(edge.destination, Integer.MAX_VALUE)) {
                    distances.put(edge.destination, newDist);
                    cameFrom.put(edge.destination, currentCity);
                    pq.add(new AbstractMap.SimpleEntry<>(edge.destination, newDist));
                }
            }
        }
        long endTime = System.nanoTime();

        // Reconstructing the full path
        String goalCity = "Kiruna"; // Change this as needed
        List<String> path = reconstructPath(cameFrom, startCity, goalCity);
        System.out.println("Shortest path: " + String.join(" -> ", path));
        System.out.println("Distance: " + distances.get(goalCity));
        System.out.println("Time taken (nanoseconds): " + (endTime - startTime));
        System.out.println("Entries in done array: " + distances.size());
    }

    public static List<String> reconstructPath(HashMap<String, String> cameFrom, String start, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;
        while (current != null && !current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        if (current != null) {
            path.add(start);
        }
        Collections.reverse(path);
        return path;
    }
}
