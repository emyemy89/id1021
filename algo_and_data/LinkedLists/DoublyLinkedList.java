import java.util.NoSuchElementException;

public class DoublyLinkedList {
    // create 2 objects:
    private ListNode head;
    private ListNode tail;
    private int length;

    public class ListNode{
        int data;
        ListNode next;
        ListNode previous;

        public ListNode(int data){
            this.data=data;
        }
    }

    public DoublyLinkedList(){
        this.head=null;
        this.tail=null;
        this.length=0;

    }

    // method to see if the list in empty
    public boolean isEmpty(){
        return length==0;
    }

    // method to get the length
    public int length(){
        return length;
    }

    //print the nodes
    public void display(){
        if(head==null) return;
        ListNode current=head;
        while(current!= null){
            System.out.print(current.data+ ", ");
            current=current.next;
        }
        System.out.println("null");
    }

    // add node at the end
    public void addLast(int val){
        ListNode newNode=new ListNode(val);
        if(isEmpty()){
            head=newNode;
        } else{
            tail.next=newNode;
            newNode.previous=tail;
        }
        tail=newNode;
        length++;
    }
    public void addStart(int val){
        ListNode newNode=new ListNode(val);
        if(head==null){
            head=newNode;
        }else{
            head.previous=newNode;
            newNode.next=head;
        }
        head=newNode;
        length++;
    }

    public ListNode deleteStart(){
        if(head==null){
          throw new NoSuchElementException();
        }
        ListNode current=head;
        if(head==tail){
            tail=null;
        }else
        {
          head.next.previous=null;
        }
          head=head.next;
          current.next=null;
          length--;
          return current;
    }

    public void deleteEnd(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        ListNode current=tail;
        if(head==tail){
          head=null;
        }
        else{
          tail.previous.next=null;
        }
        tail=tail.previous;
        current.previous=null;
        length --;

    }

    public static void main(String[] args){
        DoublyLinkedList dll= new DoublyLinkedList();
        dll.addLast(2);
        dll.addLast(3);
        dll.addLast(5);
        dll.addLast(6);
        dll.addStart(1);
        dll.display();

        System.out.print("Here we delete an element from the beginning: ");
        dll.deleteStart();
        dll.display();

        System.out.print("Here we delete an element from the end: ");
        dll.deleteEnd();
        dll.display();
    }


}
