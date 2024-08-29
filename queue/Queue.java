import java.util.NoSuchElementException;

public class Queue {
  private ListNode front;
  private ListNode rear;
  private int length;

  public Queue(){
    this.front=null;
    this.rear=null;
    this.length=0;
  }

  private class ListNode{
    private int data;
    private ListNode next;

    public ListNode(int data){
      this.data=data;
      this.next=null;
    }
  }
  public int length(){
      return length;
  }
  public boolean isEmpty(){
      return length==0;
  }

  public void print(){
      ListNode current=front;
      while(current!=null){
        System.out.print(current.data+", ");
        current=current.next;
      }
      System.out.println(" Null");
  }

  // insert elements to the queue
  public void enqueue(int data){
    ListNode newNode= new ListNode(data);
    if(isEmpty()){
        front=newNode;
    }else{
        rear.next=newNode;
    }
    rear=newNode;
    length++;
  }

  //delete elements from the queue
  public void dequeue(){
    if(isEmpty()){
      throw new NoSuchElementException();
    }
    front=front.next;
    if(front==null){
      rear=null;
    }
    length --;
  }

  public int first(){
    if(isEmpty()){
      throw new NoSuchElementException("The queue is already empty");
    }
    return front.data;
  }
  public int last(){
    if(isEmpty()){
      throw new NoSuchElementException("The queue is already empty");
    }
    return rear.data;
  }
    public static void main(String[] args) {
      Queue queue= new Queue();
      queue.enqueue(0);
      queue.enqueue(1);
      queue.enqueue(2);
      queue.enqueue(3);
      queue.print();
      queue.dequeue();
      queue.dequeue();
      queue.print();
      System.out.println("The first element is: "+queue.first());
      System.out.println("The last element is: "+queue.last());
    }
}