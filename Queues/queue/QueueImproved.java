public class QueueImproved {
    Node head;  // Points to the front of the queue
    Node tail;  // Points to the end of the queue

    // Inner Node class representing each element in the linked list
    private class Node {
        Integer item;  // The value of the node
        Node next;     // Pointer to the next node

        // Constructor for Node class
        private Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    // Constructor initializes an empty queue
    public QueueImproved() {
        this.head = null;  // Initialize head as null
        this.tail = null;  // Initialize tail as null
    }

    // Method to enqueue a new item at the end of the queue
    public void enqueue(Integer item) {
        Node newNode = new Node(item, null);  // Create a new node
        
        if (tail == null) {
            // If the queue is empty, both head and tail point to the new node
            head = tail = newNode;
        } else {
            // Attach the new node to the end of the queue and update tail
            tail.next = newNode; // Link the old tail to the new node
            tail = newNode;      // Update the tail to be the new node
        }
    }

    // Method to dequeue an item from the front of the queue
    public Integer dequeue() {
        if (head == null) {
            // Return null if the queue is empty
            return null;
        }

        // Retrieve the item from the head and move head to the next node
        Integer dequeuedItem = head.item;
        head = head.next;  // Move the head to the next node

        // If the queue becomes empty after dequeuing, reset the tail to null
        if (head == null) {
            tail = null; // If the queue is now empty, tail should also be null
        }

        return dequeuedItem;
    }

    // Method to print the current queue
    public void printQueue() {
        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Benchmark method to measure the time taken to enqueue a single item
    private static long benchmarkEnqueue(int size) {
        Queue queue = new Queue(); // Create a new Queue

        long startTime = System.nanoTime();
        //for (int i = 0; i < size; i++) { // Enqueue the specified number of elements
            queue.enqueue(1);
        //}
        long endTime = System.nanoTime();

        return (endTime - startTime) / size; // Return the average time in nanoseconds per enqueue operation
    }

    public static void main(String[] args) {
        //int[] sizes = {100, 500, 1000, 2000, 4000, 8000, 12000}; // Different sizes to benchmark

        int[] sizes = {100, 200, 500, 1000, 1500, 2500, 5000}; 
        // Extended warm-up for JIT
        for (int i = 0; i < 200; i++) { // Warm-up iterations
            benchmarkEnqueue(1000); // Warm up by enqueueing 1000 items
        }

        // Benchmarking
        int trials = 200; // Number of trials for each size
        System.out.println("Size | Average Execution Time (ns)");
        for (int size : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                System.gc(); // Suggest GC to avoid heap buildup
                totalTime += benchmarkEnqueue(size); // Benchmark the enqueue operation
            }
            long avgTime = totalTime / trials; // Average time over all trials
            System.out.println(size + " | " + avgTime + " ns");
        }
    }
}
