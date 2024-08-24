import java.util.Stack;
public class StringReverse {


    public static String reverse(String str) {
        Stack<Character> stack= new Stack<>();
        // create an array with the chars of the string
        char[] chars= str.toCharArray();
        for(char c:chars){
          stack.push(c);
        } 

        for(int i=0;i<str.length();i++){
          chars[i]=stack.pop();
        }
        return new String(chars);
    }
    public static void main(String[] args) {
        String str="ABCDEF";
        System.out.println("The string originally: "+ str);
        System.out.println("The string reversed: "+ reverse(str));
    }
}
