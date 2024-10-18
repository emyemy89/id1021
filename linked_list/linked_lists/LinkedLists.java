class LinkedList {
    Cell first;

    private class Cell {
        int head; 
        Cell tail; 

        Cell(int val, Cell tl) {
            head = val; 
            tail = tl; 
        }
    }

    LinkedList() {
        first = null; 
    }

    LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }


    private static long benchmarkAppend(int sizeA, int sizeB) {
        LinkedList listA = new LinkedList(sizeA); 
        LinkedList listB = new LinkedList(sizeB); 
    
        long startTime = System.nanoTime();
        listA.append(listB); 
        long endTime = System.nanoTime();
    
        return endTime - startTime;
    }
    



    
    public void add(int item) {
        first = new Cell(item, first);
    }


    public int length() {
        if (first == null) {  
            return 0;
        }        
        Cell current = first; 
        int i = 0;        
        while (current != null) {
            i++;  
            current = current.tail;  
        }      
        return i;  
    }

    

    public boolean find(int item) {
        Cell current = first;  
        
        while (current != null) {  
            if (current.head == item) {  
                return true; 
            }
            current = current.tail;  
        }       
        return false;  
    }

    public void remove(int value) {
        if (first == null) { 
            return;
        }
        if (first.head == value) {
            first = first.tail; 
            return;
        }   
        Cell prev = first;  
        while (prev.tail != null) {  
            if (prev.tail.head == value) {  
                prev.tail = prev.tail.tail;  
                return;  
            }
            prev = prev.tail;  
        }
    }
    
    
    public void printList() {
        Cell current = first; 
        while (current != null) {  
            System.out.print(current.head + " ");  
            current = current.tail; 
        }
        System.out.println(); 
    }



    public void append(LinkedList b) {
        // second list is empty, nothing to append
        if (b.first == null) {  
            return;
        }   
        Cell nxt = this.first;
        // first list is empty, just set it to the second list
        if (nxt == null) {  
            this.first = b.first;
            return;
        }    
        // find last cell of first list
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }    
        // link last cell to the first cell
        nxt.tail = b.first;    
        // clear the second list's first node to avoid confusion
        b.first = null;   
    }
    
    
   
    public static void main(String[] args) {
        // LinkedList list = new LinkedList();  

        // list.add(10);
        // list.add(20);
        // list.add(30);
        
        // System.out.print("List: ");
        // list.printList();
    
        // System.out.println("Find 20: " + list.find(20));  
        // System.out.println("Find 40: " + list.find(40)); 
    

        // System.out.println("Length: " + list.length());  
    
        // list.remove(10);
        // System.out.print("List after removing: ");
        // list.printList();  
    
        // System.out.println("Length: " + list.length());  


        // LinkedList list1 = new LinkedList();  
        // list1.add(3);
        // list1.add(4);
        // list1.add(5);

        // list.append(list1);
        // list.printList();



        //int[] sizes = {100, 200, 500, 1000, 2000, 5000, 10000};

        
        



        int[] sizes = {100, 500, 1000, 2000, 4000, 8000, 12000}; 
        int fixedSizeB = 10000; // Larger fixed size for list B

        // Extended warm-up for JIT
        for (int i = 0; i < 200; i++) { // Increased warm-up iterations
          benchmarkAppend(1000, fixedSizeB);
          benchmarkAppend(5000, fixedSizeB);
        }

        // Benchmarking
        int trials = 200; // Increased number of trials
        System.out.println("Size A | Execution Time (ns)");
        for (int sizeA : sizes) {
            long totalTime = 0;
            for (int i = 0; i < trials; i++) {
                System.gc(); // Suggest GC to avoid heap buildup
                totalTime += benchmarkAppend(sizeA, fixedSizeB); // Benchmark the append operation
            }
            long avgTime = totalTime / trials; // Average time over all trials
            System.out.println(sizeA + " | " + avgTime + " ns");
        }


//  int[] sizes = {100, 500, 1000, 2000, 4000, 8000, 12000}; 
// int fixedSizeA = 1000; // Fixed size for list A

// // Extended warm-up for JIT
// for (int i = 0; i < 200; i++) { // Increased warm-up iterations
//     benchmarkAppend(fixedSizeA, 1000);
//     benchmarkAppend(fixedSizeA, 5000);
// }


// int trials = 200; 
// System.out.println("Size B | Execution Time (ns)");
// for (int sizeB : sizes) {
//     long totalTime = 0;
//     for (int i = 0; i < trials; i++) {
//         System.gc(); // Suggest GC to avoid heap buildup
//         totalTime += benchmarkAppend(fixedSizeA, sizeB); 
//     }
//     long avgTime = totalTime / trials; // Average time over all trials
//     System.out.println(sizeB + " | " + avgTime + " ns");
// }


        
        
        

        
    }
}
