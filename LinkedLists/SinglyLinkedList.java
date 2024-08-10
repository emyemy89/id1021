public class SinglyLinkedList {
  //create head
  private ListNode head;

  // create node class with 2 proprieties
  // data and next
  private static class ListNode{
      private int data;
      private ListNode next;

      // constructor
      public ListNode(int data){
          this.data=data;
          this.next=null;
      }
  }

  // method to print the elements of the list
  public void display(){
    ListNode current= head;
    while(current!=null){
      System.out.print(current.data+", ");
      current=current.next;
    }
    System.out.println("Null");
  }

  // method to return the length
  public int length(){
    if(head==null){
        return 0;
    }
    ListNode current=head;
    int i=0;
    while(current!= null){
        i++;
        current=current.next;
    }
    return i;
  }

  // insert node in the beginning
  public void addStart(int val){
      ListNode newNode=new ListNode(val);
      newNode.next=head;
      head=newNode;
  }

  // insert node at the end
  public void addEnd(int val){
      ListNode newNode=new ListNode(val);
      if(head==null){
          head=newNode;
          return;
      }
      ListNode current=head;
      while(current.next!=null){
          current=current.next;
      }
      current.next=newNode;
  }

  //insert node at a given position
  public void insert(int val,int pos){
      ListNode newNode=new ListNode(val);
      if(pos==1){
        newNode.next=head;
        head=newNode;
      } else{
        ListNode count=head;
        int i=1;
        while(i<pos-1){
          count=count.next;
          i++;
        }
        ListNode current= count.next;
        count.next=newNode;
        newNode.next=current;
      }
    }

    //delete first node
    public ListNode deleteFirst(){
        if(head==null){
            return null;
        }
        ListNode deleted=head;
        head=head.next;
        deleted.next=null;
        return deleted;
    }

    // delete last node
    public ListNode deleteLast(){
        if(head==null || head.next==null){
            return null;
        }
        ListNode count=head;
        while(count.next.next!=null){
            count=count.next;
        }
        count.next=null;
        return count;
    }

    // delete a certain node
    public void delete(int pos){
        if(pos==1){
          head=head.next;  
        }
        ListNode prev=head;
        int i=1;
        while(i<pos-1){
            i++;
            prev=prev.next;
        }
        ListNode current=prev.next;
        prev.next=current.next;   
    }

    // search for a specific node
    public boolean search(int val){
        ListNode current=head;
        while(current!=null){
          if(current.data==val){
                return true;
            }
          current=current.next;
        }
        return false;
    }

    // reverse the linked list
    public void reverse(){
        ListNode current=head;
        ListNode next=null;
        ListNode prev=null;
        while(current!=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        head=prev;
    }

    // find middle node
    // we use slow and fast -pointer metodology
    // when fastPtr reaches the end, slowPtr will point to the middle node
    public ListNode findMiddle(){
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }
        return slowPtr;
    }

    // return n_th node
    // 2 pointers: reference pointer will move ahead n positions
    // to imitate an 'end' and main pointer will point to the n_th node
    public ListNode findNode(int n){
        // both of the ptrs point to head node
        ListNode mainPtr=head;
        ListNode refPtr=head;
        int i=0;
        while(i<n){
            refPtr=refPtr.next;
            i++;
        }
        while(refPtr!=null){
            refPtr=refPtr.next;
            mainPtr=mainPtr.next;
        }
        return mainPtr;
    }

    // remove duplicates from sorted list
    public void removeDuplicates(){
        ListNode current=head;
        while(current.next!=null && current!=null ){
            if(current.data==current.next.data){
                current.next=current.next.next;
            }
            else{
                current=current.next;
            }
        }
    }

    // insert a node in an ordered list
    public void insertOrdered(int val){
        ListNode newNode= new ListNode(val);
        ListNode prev=null;
        ListNode current=head;

        while(current.next!=null && current.data<val){
            prev=current;
            current=current.next;
        }

        newNode.next=current;
        prev.next=newNode;
    }

    // remove a given key from the list
    public void removeOrdered(int val){
        ListNode current=head;
        ListNode prev=null;
        while(current!=null && current.data!=val){
            prev=current;
            current=current.next;
        }
        if(current==null) {
            return;
        }
        prev.next=current.next;
    }

    // this method detects if we have a loop in the list
    // we have 2 pointers: a fast one and a slow one 
    // and if there is a loop at some point they will be equal
    public boolean detectLoop(){
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while(fastPtr!=null && fastPtr.next!=null){
            fastPtr=fastPtr.next.next;
            slowPtr=slowPtr.next;
            if(slowPtr==fastPtr){
                return true;
            }
        }
        return false;
    }

    public ListNode findStartLoop(){
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while(fastPtr!=null && fastPtr.next!=null){
            fastPtr=fastPtr.next.next;
            slowPtr=slowPtr.next;
            if(slowPtr==fastPtr){
                return getFirstNode(slowPtr);
            }
        }
        return null;
    }

    public ListNode getFirstNode(ListNode slowPtr){
        ListNode temp=head;
        while(temp!=slowPtr){
            temp=temp.next;
            slowPtr=slowPtr.next;
        }
        return slowPtr;
    }

    // remove the loop: we are using the floyd algorith again
    // and then we call method removeLoopHelper
    public void removeLoop(){
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while(fastPtr!=null && fastPtr.next!=null){
            fastPtr=fastPtr.next.next;
            slowPtr=slowPtr.next;
            if(slowPtr==fastPtr){
                removeLoopHelper(slowPtr);
                return;
            }
        }
    }
    // this method is very similar to getFirstNode()
    // we are basically setting the last node .next to null to break the loop
    public void removeLoopHelper(ListNode slowPtr){
        ListNode temp=head;
        while(slowPtr.next!=temp.next){
            temp=temp.next;
            slowPtr=slowPtr.next;
        }
        slowPtr.next=null;
    }

    // merge 2 sorted lists
     public static ListNode merge(ListNode a, ListNode b){
         // create a dummy node that will be the list
         ListNode dummy=new ListNode (0);
         // create a pointer for the nodes is th enew list
         ListNode ptr=dummy;
         while(a!=null && b!=null){
             // compare nodes and add them to the new list
             if(a.data<b.data){
                 ptr.next=a;
                 a=a.next;
             } else {
                 ptr.next=b;
                 b=b.next;
             }
             ptr=ptr.next;
         }
         if(a==null){
             ptr.next=b;
         }
         else{
             ptr.next=a;
         }
         return dummy.next;
     }

     // method to add 2 numbers represented as lists
     // a:        null  <-6<-3<-1 +
     // b:        null  <-7<-5<-2 =
     //                  ---------
     // dummy:  null <-1<-3<-8<-3
     public static ListNode addTwoNumbers(ListNode a, ListNode b){
         // create a dummy node that will be the list
         ListNode dummy=new ListNode(0);
         // create a pointer for the nodes is th enew list
         ListNode ptr=dummy;
         int carry=0;
         while(a!=null || b!=null){
           // create 2 variables to store the data of the nodes
           int x=(a!=null)? a.data:0;
           int y=(b!=null)? b.data:0;
           int sum=x+y+carry;
           // store the carry digit
           carry=sum/10;
           // create the node
           ptr.next=new ListNode(sum%10);
           ptr=ptr.next;
           if(a!=null) a=a.next;
           if(b!=null) b=b.next;
         }
         // add a new digit if necessary
         if(carry>0){
             ptr.next=new ListNode(carry);
         }
        
         return dummy.next;
     }
  public static void main(String[] args) {
      // create singly linked list
      SinglyLinkedList sll=new SinglyLinkedList();

      //create the list
      sll.head= new ListNode(10);
      ListNode second= new ListNode(8);
      ListNode third= new ListNode(1);
      ListNode fourth= new ListNode(11);
      ListNode fifth= new ListNode(15);
      sll.head.next=second;
      second.next=third;
      third.next=fourth;
      fourth.next=fifth;
      
      // display elements
      sll.display();

      // add new node at start
      sll.addStart(56);

      //add node at end
      sll.addEnd(12);

      //insert 55 at pos 2
      sll.insert(55,2);

      // display elements
      sll.display();

      //delete first node
      sll.deleteFirst();

      //delete last element
      sll.deleteLast();

      // delete node at pos 3
      sll.delete(3);

      // search for value 11
      System.out.println(sll.search(48));

      // display elements
      sll.display();


      // reverse list
      System.out.print("Reversed list: "); 
      sll.reverse();
      sll.display();

      //find middle mode
      ListNode middleNode=sll.findMiddle();
      System.out.println("Middle node is: "+middleNode.data);
      // find length
      System.out.println("The length is: "+sll.length());

      // find 5th node:
      ListNode fifthNode=sll.findNode(5);
      System.out.println("5th node is: "+fifthNode.data);

      // create an ordered list
      SinglyLinkedList ordered=new SinglyLinkedList();
      ordered.head= new ListNode(1);
      ListNode secondly= new ListNode(1);
      ListNode thirdly= new ListNode(3);
      ListNode fourthly= new ListNode(5);
      ListNode fifthly= new ListNode(5);
      ordered.head.next=secondly;
      secondly.next=thirdly;
      thirdly.next=fourthly;
      fourthly.next=fifthly;

      System.out.println("Here we remove the duplicates:");
     ordered.removeDuplicates();
     ordered.display(); 

     System.out.println("Here we insert an element:");
     ordered.insertOrdered(2);
     ordered.display();

     System.out.println("Here we remove an element: ");
     ordered.removeOrdered(5);
     ordered.display();


     // create a looped list
     SinglyLinkedList looped=new SinglyLinkedList();
      looped.head= new ListNode(1);
      ListNode sec= new ListNode(1);
      ListNode thi= new ListNode(3);
      ListNode fou= new ListNode(5);
      ListNode fif= new ListNode(5);
      looped.head.next=sec;
      sec.next=thi;
      thi.next=fou;
      fou.next=fif;
      fif.next=thi;
     System.out.println("Do we have a loop in the list? : "+looped.detectLoop());
     System.out.println("First Node in the loop : "+looped.findStartLoop().data);

     looped.removeLoop();
     looped.display();

     // create new ordered list
     SinglyLinkedList ordered1=new SinglyLinkedList();
     ordered1.addEnd(1);
     ordered1.addEnd(3);
     ordered1.addEnd(6);
    //  ordered1.addEnd(11);
     SinglyLinkedList ordered2=new SinglyLinkedList();
     ordered2.addEnd(2);
     ordered2.addEnd(5);
     ordered2.addEnd(7);
     

     // merge the 2 ordered lists
     System.out.println("Now we gonna merge these 2 lists: \n");
     System.out.print("List 1: "); ordered1.display();
     System.out.print("List 2: "); ordered2.display();
     SinglyLinkedList result=new SinglyLinkedList();
     result.head=merge(ordered1.head,ordered2.head);
     System.out.print("The result is: "); result.display();

     SinglyLinkedList addResult=new SinglyLinkedList();
     addResult.head=addTwoNumbers(ordered1.head,ordered2.head);
     System.out.print("The result of adding the 2 lists is: "); addResult.display();
  }

}
