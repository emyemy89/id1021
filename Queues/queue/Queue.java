public class Queue {
    Node head;  // Points to the front of the queue

    private class Node {
        Integer item;
        Node next;
        private Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Queue() {
        this.head = null;
    }

    public void enqueue(Integer item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(item, null);
        }
    }

    // Method to pre-fill the queue to a given size
    public static Queue fillQueue(int size) {
        Queue queue = new Queue();
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);  // Pre-fill the queue with dummy values
        }
        return queue;
    }

    // Benchmark method to measure the time taken to enqueue a single element into a queue of given size
    private static long benchmarkEnqueue(int size) {
        Queue queue = fillQueue(size);  // Create a queue with 'size' elements

        long startTime = System.nanoTime();
        queue.enqueue(1);  // Enqueue just 1 element
        long endTime = System.nanoTime();

        return endTime - startTime;  // Return the time taken to enqueue one element
    }

    public Integer dequeue() {
        if (head == null) {
            return null;
        }
        
        Integer dequeuedItem = head.item;
        head = head.next;
        return dequeuedItem;
    }

    public static void main(String[] args) {
        //int[] sizes = {100, 500, 1000, 5000, 8000, 10000, 15000}; // Sizes to benchmark

        int[] sizes = {100, 200, 500, 1000, 1500, 2500, 5000}; 
        // Extended warm-up for JIT
        for (int i = 0; i < 200; i++) { // Warm-up iterations
            benchmarkEnqueue(1000);
            benchmarkEnqueue(5000);
        }

        // Benchmarking
        int trials = 200; // Number of trials
        System.out.println("Size | Execution Time (ns)");
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
