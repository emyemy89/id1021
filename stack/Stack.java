import java.util.EmptyStackException;

public class Stack {
    private ListNode top;
    private int length;

    class ListNode{
        private int data;
        private ListNode next;

        public ListNode(int data){
            this.data=data;
        }
    }

    public Stack(){
        top=null;
        length=0;
    }

    public int length(){
        return length;
    }
    
    public boolean isEmpty(){
        return length==0;
    }

    // push method to add to stack
    public void push(int data){
        ListNode newNode=new ListNode(data);
        newNode.next=top;
        top=newNode;
        length++;
    }

    //pop method to delete from stack
    public int pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        int data=top.data;
        top=top.next;
        length--;
        return data;
    }

    // peek method to retunr the data hold by top
    public int peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.data;
    }
    // given an array, this method will return a new array in which each element 
    // is the next greater element to the right of the original element
    // for array [2,7,4,8,4,1] the output is [7,8,8,-1,-1,-1]
    public static int[] nextGreaterElement(int [] arr){
        // to store the result
        int[] result= new int[arr.length] ;
        Stack stack=new Stack();
        for(int i=arr.length-1; i>=0;i--){
            if(!stack.isEmpty()){
              while(!stack.isEmpty() && stack.peek()<=arr[i]){
                  stack.pop();
              }
            }

            if(stack.isEmpty()){
              result[i]=-1;
            }else{
              result[i]=stack.peek();
            }
            stack.push(arr[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        // create a stack
        Stack stack= new Stack();
        stack.push(1);
        stack.push(5);
        stack.push(10);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());

        int[] arr={2,7,4,8,4,1};
        int[] result= nextGreaterElement(arr);
        ArrayUtil.print(result);
    

    }
}
