import java.util.NoSuchElementException;

public class QueueArray {
    private int[] queue;   
    private int i;          // Index of the front element 
    private int k;          // Index of the next free slot 
    private int size;       // Number of elements in the queue
    private int n;         //  array size

    
    public QueueArray(int capacity) {
        queue = new int[capacity];
        i = 0;              
        k = 0;              
        size = 0;          
        n = capacity;
    }

    public void enqueue(int value) {
        if (size == n && i==k) {
            resizeArray();
        }       
        queue[k] = value;
        k = (++k) % n; // Wrap around if necessary (mod n)
        size++;
    }

    // public int dequeue() {
    //     if (size == 0 && i==k) {
    //         throw new NoSuchElementException("Queue is empty");
    //     }

    //     int dequeuedVal = queue[i];
    //     queue[i] = 0;  // Optional for garbage collectino
    //     i = (++i) % n; // Wrap around if it is the case
    //     size--;
    //     return dequeuedVal;
    // }


    public int dequeue(){
        // if(i==k){
        //     throw new NoSuchElementException("Queue is emtpy");
        // }
        int val=queue[i];
        queue[i]=0;
        i++;
        return val;
    }




    // private void resizeArray() {
    //     int newCapacity = n * 2;  // Double
    //     int[] newArray = new int[newCapacity];

    //     // Copy elements
    //     for (int j = 0; j < size; j++) {
            
    //         int c=queue[(i + j) % n];
    //         newArray[j] = c;  
    //     }

    //     // update references and indices
    //     queue = newArray;
    //     i = 0;   
    //     k = size; 
    //     n = newCapacity;
    // }


    public void resizeArray(){
        //create new array
        int[] newArr=new int[n*2];
        //copy
        for(int j=0;j<size;j++){
            newArr[j]=queue[i];
            i=(i+1)%n;
           // j=j+1;          
        }
        //change
        queue=newArr;
        n=n*2;
        i=0;
        k=size;
    }


    public void printQueue() {
        System.out.print("Queue: ");
        for (int j = 0; j < n; j++) {
            System.out.print(queue[j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueArray queue = new QueueArray(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.printQueue();

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());

        queue.printQueue();

    }
}
