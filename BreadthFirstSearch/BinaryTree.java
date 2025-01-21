


import java.util.Stack;

public class BinaryTree<T> {

    public class Node  {
        private T value;      // Change Integer to generic type T
        private Node left, right;

        public Node(T value) {
            this.value = value;
            this.left = this.right = null;
        }

        public T getV(){
            return value;
        }

        public Node getR(){
            return right;
        }

        public Node getL(){
            return left;
        }
    }   

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public Sequence<T> sequence() {
        QueueImproved<BinaryTree<T>.Node> queue = new QueueImproved<BinaryTree<T>.Node>();
    
        if (root != null) {
            queue.enqueue(root);
        }
    
        Sequence<T> sequence = new Sequence<T>(queue); 
        return sequence;
    }
    
    public void bfs(){
        // create queue
        QueueImproved<Node> q = new QueueImproved<>();

        if(root==null){
            throw new IllegalArgumentException("Tree doesn't exist");
        }

        q.enqueue(root);
        Node val;

        while(!q.isEmpty()){
            val=q.dequeue();
            System.out.print(val.value+ " ");

            if (val.left!= null) {
                q.enqueue(val.left);
            }
            
            if (val.right!=null) {
                q.enqueue(val.right);
            }
           // val=queue.peek();
        }
    }

    // public void print() {
    //     Stack<Node> stk = new Stack<>();
    //     Node cur = this.root;
    //     int stackSize = 0; // Track stack size manually

    //     // Traverse while there are nodes to visit
    //     while (cur != null || stackSize > 0) {
    //         // Traverse to the leftmost node
    //         while (cur != null) {
    //             stk.push(cur);
    //             stackSize++; // Manually track the push operation
    //             cur = cur.left;
    //         } 
    //         // Pop the node from the stack, and print its value
    //         cur = stk.pop();
    //         stackSize--; // Manually track the pop operation
    //         System.out.print(cur.value+ " ");

    //         // Move to the right subtree
    //         cur = cur.right;
    //     }
    // }



    public void print() {
        Stack<Node> stk = new Stack<Node>();
        Node cur = this.root;
    
        // move to the leftmost node
        while (cur != null || !stk.isEmpty()) {
            // move to the leftmost node, push nodes as you go
            if (cur != null) {
                stk.push(cur);
                cur = cur.left;
            } else {
                // pop a node from the stack
                cur = stk.pop();
                // print value of node
                System.out.print(cur.value + " ");
                
                // move to the right subtree
                cur = cur.right;
            }
        }
        // done
    }
    
    
    

    public static void main(String[] args) {
        //QueueImproved<Node> queue = new QueueImproved<>();     
     BinaryTree<String> tree= new BinaryTree<>();
        tree.root=tree.new Node("A");
        tree.root.left=tree.new Node("B");
        tree.root.right=tree.new Node("C");
        tree.root.left.left=tree.new Node("D");
        tree.root.left.right=tree.new Node("E");
        tree.root.left.left.left=tree.new Node("H");
        tree.root.left.left.right=tree.new Node("I");

        tree.root.left.right.left=tree.new Node("J");
        tree.root.left.right.right=tree.new Node("K");

        tree.root.right.left=tree.new Node("F");
        tree.root.right.right=tree.new Node("G");

        tree.root.left.left=tree.new Node("D");



        Sequence<String> sequence = tree.sequence();

        // System.out.println(sequence.next());
        // System.out.println(sequence.next());
        // System.out.println(sequence.next());
        // System.out.println(sequence.next());


        String value = sequence.next();
        while (value != null) {
            System.out.print(value + " ");
            value = sequence.next();
        }

       // tree.print();

        tree.bfs();

    }
}
