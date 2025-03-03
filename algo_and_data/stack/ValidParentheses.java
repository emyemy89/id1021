import java.util.Stack;
public class ValidParentheses {


    // given a string that contains only the characters '(', ')', '[', ']', '{', '}'
    // we decide if it is valid or not
    //    To be valid it needs to:
    //      - open brackets should be closed by the same type of brackets
    //      - open brackets must be closed in the correct order

    public static boolean isValid(String str){
        Stack<Character> stack=new Stack<>();
        for (char c :str.toCharArray()){
            if(c=='(' || c=='[' || c=='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    char top=stack.peek();
                    if(c==')' && top=='(' ||
                      c==']' && top=='[' ||
                      c=='}' && top=='{'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
      String str="(}";
      System.out.println(isValid(str)); 
    }
}
