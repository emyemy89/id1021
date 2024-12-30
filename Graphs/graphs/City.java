public class City {


    private String name;
    public Connection[] connections; // Array of connections from this city
    //private int count; // To keep track of the number of connections

    public City(String name) {
        this.name = name;
        this.connections = new Connection[60]; // Array for connections
        //this.count = 0; // Initialize the connection count
    }

    public String getN() {
        return name; // Return the name of the city
    }

    public Connection[] getConnections() {
        return connections; // Return the connections array
    }

    public void connect(City nxt, int dst) {
        // Find the first available space in the connections array
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) { // Check for empty space
                Connection newC = new Connection(nxt, dst);
                connections[i] = newC; // Add the new connection
                return; // Exit the method after adding the connection
            }
        }
        throw new IllegalStateException("Can't add more connections");
    }
}
