// public class Path {
//     private City city;
//     private City prev;
//     private Integer dist;

//     // constructor
//     public Path(City city, City prev, Integer dist) {
//         this.city = city;
//         this.prev = prev;
//         this.dist = dist;
//     }

//     public City getCity() {
//         return city;
//     }

//     public Integer getD() {
//         return dist;
//     }

//     @Override
//     public String toString() {
//         return "City: " + city.getN() + ", Previous: " + (prev != null ? prev.getN() : "None") + ", Distance: " + dist;
//     }
// }


public class Path {
    private City city;
    private City previous;
    private int distance;
    public Path previousPath;

    public Path(City city, City previous, int distance) {
        this.city = city;
        this.previous = previous;
        this.distance = distance;
        this.previousPath = null; // initialize previousPath as null
    }

    public City getCity() {
        return city;
    }

    public int getD() {
        return distance;
    }

    public Path getPreviousPath() {
        return previousPath;
    }

    public void setPreviousPath(Path previousPath) {
        this.previousPath = previousPath;
    }
}


