// public class City {

//     public String name;
//     public Integer id;
//     public Connections neighbours;


//     public City(String name, Integer id) {
//         this.name = name;
//         this.id = id;
//         this.neighbours = new Connections(); // Initialize the Connections object
//     }




//     public void connect(City nxt, Integer dist){
//         Connection newC= new Connection(nxt,dist);
//         neighbours.add(newC);
//     }


public class City {
    public String name; // Name of the city
    public Integer id;  // Unique identifier for the city
    private DynamicArray<Connection> connections; // Dynamic array to hold connections


    // Constructor
    public City(String name, Integer id) {
        this.name = name; // Set the city name
        this.id = id;     // Set the city ID
        this.connections = new DynamicArray<>(); // Initialize the dynamic array for connections
    }


    // Method to connect this city to another city with a distance
    public void connect(City nxt, Integer dist) {
        Connection newC = new Connection(nxt, dist); // Create a new Connection object
        connections.add(newC); // Add the new Connection to the dynamic array
    }


    // Getter for a connection at a specific index
    public Connection getConnection(int index) {
        return connections.get(index); // Return the specified connection
    }


    public DynamicArray<Connection> getConnections(){
        return connections;
    }


    // Method to get the number of connections
    public int getSize() {
        return connections.getSize(); // Return the current number of connections
    }

    public String getN(){
        return name;
    }

    public Integer getId(){
        return id;
    }




}
    
//     private class Path {

//         private City city;
//         private City prev;
//         private Integer dist;

//         // constructor
//         public Path(City city, City prev, Integer dist) {
//             this.city= city;
//             this.prev= prev;
//             this.dist= dist;
//         }

//         public City getCity(){
//             return city;
//         }

//         public Integer getD(){
//             return dist;
//         }



//     }


