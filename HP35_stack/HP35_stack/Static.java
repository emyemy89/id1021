public class Static<T> extends Stack<T> {
  T[] stack;
  int top;

  public Static(int size) {
      this.size = size;
      stack = (T[]) new Object[size];  // Create an array of Objects and cast to T[]
      top = -1;  // Initialize top to -1 indicating an empty stack
  }

  @Override
  public void push(T val) {
      if (top == stack.length - 1) {
          throw new StackOverflowError("Stack overflow");
      } else {
          top++;
          stack[top] = val;
      }
  }

  @Override
  public T pop() {
      if (top == -1) {
          throw new IllegalStateException("No elements to pop");
      } else {
          T val = stack[top];  // Return a value of type T
          top--;
          return val;
      }
  }

  public static void main(String[] args) {
      Static<Integer> s = new Static<>(2);  // Specify the type parameter Integer
      s.push(32);
      s.push(33);
      //s.push(34);


      System.out.println("pop : " + s.pop());
      System.out.println("pop : " + s.pop());
     // System.out.println("pop : " + s.pop());
      //System.out.println("pop : " + s.pop());
  }
}
