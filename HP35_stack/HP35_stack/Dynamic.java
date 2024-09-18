

public class Dynamic<T> extends Stack<T> {
    T[] stack;  // Use T[] instead of int[]
    int top;
    int size;

    
    public Dynamic() {
        this.size = 4;  // Start with a small initial size
        stack = (T[]) new Object[size];  // Create an array of Objects and cast to T[]
        top = -1;  // Initialize top to -1 indicating an empty stack
    }

    @Override
    public void push(T val) {
        // Check if we reach max capacity
        if (top == size - 1) {
            changeSize(size * 2);  // Double the size if the stack is full
        }
        stack[++top] = val;
    }

    @Override
    public T pop() {
        if (top == -1) {
            throw new IllegalStateException("No more elements to pop");
        }
        T val = stack[top--];

        // Check if we need to shrink
        if (top < size / 4 && size > 4) {
            changeSize(size / 2);  // Shrink the size if underutilized
        }
        return val;
    }

    private void changeSize(int newSize) {
        T[] newStack = (T[]) new Object[newSize];  // Create a new array of Objects and cast to T[]
        // Copy old elements to the new array
        for (int i = 0; i <= top; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
        size = newSize;
    }
}
