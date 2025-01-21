
public class QueueImproved<T> {
    private Node head; 
    private Node tail;  

    
    private class Node {
        T item;           
        Node next;       
        
        private Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

   
    public QueueImproved() {
        this.head = null;  
        this.tail = null;  
    }

    
    public boolean isEmpty() {
        return head == null;
    }

   
    public void enqueue(T newNode) {
        Node newTail = new Node(newNode, null); 
        if (isEmpty()) {
            head = newTail; 
        } else {
            tail.next = newTail; 
        }
        tail = newTail; 
    }

    
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = head.item; 
        head = head.next; 
        if (head == null) {
            tail = null; 
        }
        return item; 
    }

   
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.item; 
    }



    public static void main(String[] args) {
    
}
}
