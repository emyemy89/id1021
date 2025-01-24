import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PQueue {
    private ArrayList<Path> queue;

    public PQueue() {
        queue = new ArrayList<>();
    }

    // Add a new path to the queue and maintain sorted order based on distance
    // public void enqueue(Path path) {
    //     int insertIndex = 0;
    //     // Find the correct position to insert the path
    //     while (insertIndex < queue.size() && queue.get(insertIndex).getD() <= path.getD()) {
    //         insertIndex++;
    //     }
    //     // Insert the path at the correct position
    //     queue.add(insertIndex, path);
    // }


    public void enqueue(Path path) {
        int insertIndex = 0;
        while(insertIndex < queue.size() && queue.get(insertIndex).getD() <= path.getD()) {
            insertIndex++;
        }
        queue.add(insertIndex, path);
       // System.out.println("Enqueued Path: " + path.getCity().getN() + " with distance " + path.getD());
    }
    
    public Path dequeue() {
        if(queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Path path = queue.remove(0);
        System.out.println("Dequeued Path: " + path.getCity().getN() + " with distance " + path.getD());
        return path;
    }
    

    // Remove and return the path with the smallest distance (the first element)
    // public Path dequeue() {
    //     if (queue.isEmpty()) {
    //         throw new NoSuchElementException("Queue is empty");
    //     }
    //     return queue.remove(0); // Remove and return the first element
    // }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        // Create cities
        City malmo = new City("Malmo",0);
        City lund = new City("Lund",1);
        City stockholm = new City("Stockholm",2);

        PQueue queue = new PQueue();

        // Adding paths to the priority queue
        Path path1 = new Path(lund, malmo, 10);
        Path path2 = new Path(stockholm, malmo, 30);
        Path path3 = new Path(stockholm, lund, 20);

        queue.enqueue(path1);
        queue.enqueue(path2);
        queue.enqueue(path3);

        // Expected result: dequeuing should return paths in ascending order of distance
        while (!queue.isEmpty()) {
            Path p = queue.dequeue();
            System.out.println("City: " + p.getCity().getN() + ", Distance: " + p.getD());
        }
    }
}
