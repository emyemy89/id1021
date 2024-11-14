// import java.util.Stack;
// import java.util.HashSet;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.Random;
// import java.util.Set;

public class BinaryTree {

    private class Node {
        private Integer value;
        private Node left, right;

        private Node(Integer value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }




    // void add(Integer value) : add a new node (leaf) to the tree that
    // holds the value. Note that the tree should still be sorted. If the value
    // exists, do nothing.

    public void add(Integer value) {
        root = add(root, value);
    }

    public Node add(Node current,Integer value){
        // Base case,create new node
        if(current==null){
           Node leaf=new Node(value);
           return leaf;
        }
        // do nothing if it exists
        // prevent duplicates
        if(current.value==value){
            return current;
        }
        // if greater go left
        if(current.value>value){
            if(current.left != null){
                current.left=add(current.left,value);
            } else {
                current.left= new Node(value);
            }
        }
        // if smaller go right
        if(current.value<value){
            if(current.right!=null){
                current.right=add(current.right, value);
            } else{
                current.right= new Node(value);
            }
        }
        return current;
    }




    public void addIterative(Integer value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode; // If the tree is empty, set the new node as the root
            return;
        }
        Node current = root;
        Node parent = null; // Keep track of the parent node
        while (current != null) {
            parent = current; // Track the parent
            if (value < current.value) {
                current = current.left; // Move left
            } else if (value > current.value) {
                current = current.right; // Move right
            } else {
                return; // Value already exists, do nothing
            }
        }
        // Attach the new node to the parent
        if (value < parent.value) {
            parent.left = newNode; // Attach as left child
        } else {
            parent.right = newNode; // Attach as right child
        }
    }



    public boolean lookup(Integer value) {
        return lookup(root, value);
    }

    public boolean lookup(Node current,Integer key){

        if(current==null){
            return false;
        }
        if(current.value==key){
            return true;
        }
        //search left
        if(current.value>key){
            if(current.left != null){
                return lookup(current.left,key);
            } else {
                return false;
            }
        }
        // search right
        if (current.value<key){
            if(current.right!=null){
                return lookup(current.right, key);
            } else{
                return false;
            }
        }
        // if not found
        return false;
    }






    // Benchmark method
    // private static long benchmark(int sizes) {
    //     Random rnd = new Random();
    //     BinaryTree tree = new BinaryTree();
    
    //     // Generate distinct random values and add them to the tree
    //     Set<Integer> uniqueValues = new HashSet<>();
    //     List<Integer> values = new ArrayList<>();
    //     while (uniqueValues.size() < sizes) {
    //         int rndValue = rnd.nextInt(sizes * 2); // Generate random values
    //         if (uniqueValues.contains(rndValue)) {
    //             continue; // Skip duplicates
                
    //         }else{
    //             uniqueValues.add(rndValue); // Add unique values
    //             values.add(rndValue);
    //         }
    //     }
    //     // make a set of unique values with random order

    
    //     // Print the unique values

    //     for (Integer value : values) {
    //         tree.add(value); // Add unique values
    //     }

        
    
    //     // Measure the time it takes to perform multiple lookups
    //     long totalLookupTime = 0;
    //     for (int i = 0; i < 10; i++) {
    //         int lookupValue = rnd.nextInt(sizes * 2); // Random value to look up
    //         long startTime = System.nanoTime();
    //         tree.lookup(lookupValue); // Single lookup
    //         long endTime = System.nanoTime();
    //         totalLookupTime += (endTime - startTime); // Accumulate lookup times
    //     }
    
    //     return totalLookupTime / 10; // Return average time in nanoseconds
    // }


    public void print() {
        Stack<Node> stk = new Dynamic<>();
        Node cur = root;
        int stackSize = 0; // Track stack size manually
    
        // Traverse while there are nodes to visit
        while (cur != null || stackSize > 0) {
            // Traverse to the leftmost node
            while (cur != null) {
                stk.push(cur);
                stackSize++; // Manually track the push operation
                cur = cur.left;
            }
    
            // Pop the node from the stack, and print its value
            cur = stk.pop();
            stackSize--; // Manually track the pop operation
            System.out.print(cur.value+ " ");
    
            // Move to the right subtree
            cur = cur.right;
        }
    }
    
    

        
    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree();

        tree.add(50);
        tree.add(40);

        tree.add(30);
        tree.add(20);
        tree.add(10);
        tree.add(70);
        tree.add(60);

        tree.add(100);
        tree.add(1120);

        // //tree.print2D();

        // System.out.println("In-Order Traversal of the Binary Tree:");
        // tree.print();

    
        // System.out.println("Lookup 10: " + tree.lookup(10)); // Should return true
        // System.out.println("Lookup 11: " + tree.lookup(11)); // Should return false



       
        
        // int[] sizes = {100, 500, 1000, 5000, 10000, 20000, 50000}; // Array sizes

        // // Warm-up for JIT
        // for (int i = 0; i < 100; i++) {
        //     benchmark(1000);
        //     benchmark(10000);
        // }
    
        // // Benchmarking
        // int trials = 100; // More trials
        // System.out.println("Tree Size | Execution Time (ns)");
        // for (int n : sizes) {
        //     long totalTime = 0;
        //     for (int i = 0; i < trials; i++) {
        //         totalTime += benchmark(n);
        //     }
        //     long avgTime = totalTime / trials; // Average time over all trials
        //     System.out.println(n + " | " + avgTime + " ns");
        // }

        tree.print();
    }
    
}