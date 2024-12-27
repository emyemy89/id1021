import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T9{

    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }
    }

    private Node root; 

    public T9() {      // Trie
        root = new Node(); 
    }


    private static int char2code(char w){

            switch (w) {
                case 'a' :
                return 0;
                case 'b' :
                return 1;          
                case 'c' :
                return 2;
                case 'd' :
                return 3;
                case 'e' :
                return 4;
                case 'f' :
                return 5;
                case 'g' :
                return 6;
                case 'h' :
                return 7;
                case 'i' :
                return 8;
                case 'j' :
                return 9;               
                case 'k' :
                return 10;
                case 'l' :
                return 11;
                case 'm' :
                return 12;
                case 'n' :
                return 13;
                case 'o' :
                return 14;
                case 'p' :
                return 15;
                case 'r' :
                return 16;
                case 's' :
                return 17;
                case 't' :
                return 18;
                case 'u' :
                return 19;
                case 'v' :
                return 20;
                case 'x' :
                return 21;
                case 'y' :
                return 22;
                case 'z' :
                return 23;
                case 'å' :
                return 24;
                case 'ä' :
                return 25;
                case 'ö' :
                return 26;

            }
            return -1;
        }

        private static char code2char(int code){
            switch (code) {
                case 0 :
                return 'a';
                
                case 1 :
                return 'b';          
                case 2 :
                return 'c';
                case 3 :
                return 'd';
                case 4 :
                return 'e';
                case 5 :
                return 'f';
                case 6 :
                return 'g';
                case 7 :
                return 'h';
                case 8 :
                return 'i';
                case 9 :
                return 'j';               
                case 10 :
                return 'k';
                case 11 :
                return 'l';
                case 12 :
                return 'm';
                case 13 :
                return 'n';
                case 14 :
                return 'o';
                case 15 :
                return 'p';
                case 16 :
                return 'r';
                case 17 :
                return 's';
                case 18 :
                return 't';
                case 19 :
                return 'u';
                case 20 :
                return 'v';
                case 21 :
                return 'x';
                case 22 :
                return 'y';
                case 23 :
                return 'z';
                case 24 :
                return 'å';
                case 25 :
                return 'ä';
                case 26 :
                return 'ö';

            }
            return '?';
        }


    //    public static int key2index(int key){
    //         return key-1;
    //    }

    public static int key2index(char key) {
        switch (key) {
            case '1': return 0; 
            case '2': return 1; 
            case '3': return 2; 
            case '4': return 3; 
            case '5': return 4; 
            case '6': return 5; 
            case '7': return 6; 
            case '8': return 7; 
            case '9': return 8; 
            default: return -1;
        }
    }

    


    public void add(String word){
        Node current=root;
        char[] chars=word.toCharArray();
        int j;

        for(int i=0; i<chars.length;i++){
            j=char2code(chars[i]);
            if (current.next[j]==null){
                current.next[j]= new Node();
            }
            current=current.next[j];
        }
        current.valid=true;   

    }



    public ArrayList<String> decode (String seq){

        ArrayList<String> list=new ArrayList<>();

        char[] chars=seq.toCharArray();
        int[] ints = new int[chars.length];
        int j;
        for(int i=0; i<chars.length;i++){
            j=key2index(chars[i]);   // could be a problem cause i is char not int
            //j=key2index(Integer.parseInt(String.valueOf(chars[i])));
            ints[i]=j;   

        }
        // add words to the list
        collect(root,list,ints,0,"");
        return list;
    }

    

    public void collect(Node current,ArrayList<String> list,int[] ints, int i, String s){
        //base case
        // if(i==ints.length && current.valid==true){
        //     list.add(s);
        //     return;
        // }

        if (i == ints.length) {
            if (current.valid) {
                list.add(s);
            }
            return; // Stop further checks
        }


        //current code
        int cur=ints[i];
        int k=0;
        while(k<3){
            //check each of the 3 digits
            Node next= current.next[cur*3+k];
            if(next!=null){
                char nextc=code2char(cur*3+k);
                collect(next,list,ints, i+1,s+nextc);
            }
            k++;
        }
    }


    
    
    
    
    
    
    
    
    
    
    


    public static void main(String[] args) {

         T9 t9 = new T9();
    String filePath = "kelly.txt"; // Adjust the path if needed

    // Step 1: Read words from the file and populate the T9 tree
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            t9.add(line.trim()); // Add each word to the T9 tree
            System.out.println("Added word: " + line.trim()); // Debug: Print each word added
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Test some key sequences based on the provided word list
    String[] testSequences = {"533", "648855", "733","225","4577","327"}; // "nio", "sjutton", "tio"
    for (String keySequence : testSequences) {
        System.out.println("Decoding sequence: " + keySequence); // Debug: Print the key sequence
        ArrayList<String> possibleWords = t9.decode(keySequence);

        // Step 3: Print out the possible words
        System.out.println("Possible words for the key sequence \"" + keySequence + "\":");
        if (possibleWords.isEmpty()) {
            System.out.println("No words found for this sequence.");
        } else {
            for (String word : possibleWords) {
                System.out.println(word);
            }
        }
    }

    String keySequence = "26372"; // Sequence for 'andra'
ArrayList<String> possibleWords = t9.decode(keySequence);
System.out.println("Possible words for the key sequence \"" + keySequence + "\":");
for (String word : possibleWords) {
    System.out.println(word);
}
}

    }






    
