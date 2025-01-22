public class DynamicArray<T> {
    private T[] array;
    private int size;  
    private int count;

    public DynamicArray() {
        this.size = 4; 
        this.array = (T[]) new Object[size]; 
        this.count = 0;
    }



    public void add(T val) {
        if (count == size) {
            resize(size * 2);
        }
        array[count++] = val;
    }



    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }



    // Get the current size (number of elements in the array)
    public int getSize() {
        return count;
    }



    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize]; 
        for (int i = 0; i < count; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        size = newSize;
    }



    public T removeLast() {
        if (count == 0) {
            throw new IllegalStateException("No elements to remove");
        }
        T val = array[--count]; 
        if (count < size / 4 && size > 4) {
            resize(size / 2);
        }
        return val;
    }



    
    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println(array[i]);
        }
    }
}

