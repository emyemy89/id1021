public class City {
    public String name; 
    public Integer id;  
    private DynamicArray<Connection> connections; 



    public City(String name, Integer id) {
        this.name = name; 
        this.id = id;    
        this.connections = new DynamicArray<>(); 
    }


    public void connect(City nxt, Integer dist) {
        Connection newC = new Connection(nxt, dist); 
        connections.add(newC); 
    }



    public Connection getConnection(int index) {
        return connections.get(index); 
    }


    public DynamicArray<Connection> getConnections(){
        return connections;
    }


    public int getSize() {
        return connections.getSize(); 
    }

    public String getN(){
        return name;
    }

    public Integer getId(){
        return id;
    }




}
    


