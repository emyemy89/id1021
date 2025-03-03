public class CircularSinglyLinkedList {

    private ListNode last;
    private int length;

    private class ListNode{
        private ListNode next;
        private int data;

        public ListNode(int data){
            this.data=data;
        }
    }

    public CircularSinglyLinkedList(){
        last=null;
        length=0;
    }

    public int length(){
        return length;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public void display(){
        if(last==null)  return;
        ListNode current=last.next;
        while(current!=last){
            System.out.print(current.data+", ");
            current=current.next;
        }
        System.out.println(current.data+", Null");
    }

    public void create(){
        ListNode first=new ListNode(1);
        ListNode second=new ListNode(2);
        ListNode third=new ListNode(3);
        ListNode fourth=new ListNode(4);
        ListNode fifth=new ListNode(5);

        first.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        fifth.next=first;

        last=fifth;

    }

    public void addStart(int data){
        ListNode newNode=new ListNode(data);
        if (last==null){
            last=newNode;
            newNode.next=newNode;
        }else{
            newNode.next=last.next;
            last.next=newNode;
        }
        length++;
    }

    public void addEnd(int data){
        ListNode newNode=new ListNode(data);
        if (last==null){
            last=newNode;
            newNode.next=newNode;
        }else{
            newNode.next=last.next;
            last.next=newNode;
            last=newNode;
        }
        length++;
    }

    public void removeStart(){
        if(last==null) return;

        ListNode current=last.next;
        last.next=last.next.next;
        current.next=null;
        length --;
    }
    public static void main(String[] args) {
        CircularSinglyLinkedList csll= new CircularSinglyLinkedList();
        csll.create();
        csll.display();

        System.out.print("Here we add a node at the beginning: ");
        csll.addStart(0);
        csll.display();

        System.out.print("Here we add a node at the end: ");
        csll.addEnd(6);
        csll.display();

        System.out.print("Here we remove a node from the start: ");
        csll.removeStart();
        csll.display();


    }
    
}
