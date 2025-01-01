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
