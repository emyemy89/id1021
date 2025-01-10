public class Connection {
    public City neighbour;
    public int time;

    public Connection(City neighbour, int time) {
        this.neighbour = neighbour;
        this.time = time;
    }

    public City getN(){
        return neighbour;
    }

    public int getT(){
        return time;
    }
    
}


// public class Connections {
//     private Connection[] connections; // Array to hold connections
//     private int size; // Current number of connections
//     private int capacity; // Capacity of the connections array

//     public Connections() {
//         this.capacity = 4; // Start with an initial capacity
//         this.connections = new Connection[capacity];
//         this.size = 0;
//     }

    
//     public void add(Connection connection) {
//         if (size == capacity) {
//             resize(); // Resize if necessary
//         }
//         connections[size++] = connection; // Add
//     }

//     // Resize method
//     private void resize() {
//         capacity *= 2; 
//         Connection[] newConnections = new Connection[capacity];
//         for (int i = 0; i < size; i++) {
//             newConnections[i] = connections[i]; // Copy 
//         }
//         connections = newConnections; // Point to the new array
//     }

    
//     public Connection get(int index) {
//         if (index < 0 || index >= size) {
//             throw new IndexOutOfBoundsException("Index out of bounds");
//         }
//         return connections[index];
//     }

//     // Method to get the number of connections
//     public int getSize() {
//         return size; // Return current number of connections
//     }
// }

