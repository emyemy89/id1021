public class DynamicArray<T> {
    private T[] array;
    private int size;   // The current capacity of the array
    private int count;  // The number of elements currently in the array

    public DynamicArray() {
        this.size = 4;  // Start with an initial size
        this.array = (T[]) new Object[size];  // Create an array with initial capacity
        this.count = 0;  // Initially, the array has no elements
    }



    // Add a new element to the array
    public void add(T val) {
        if (count == size) {
            resize(size * 2);  // Double the size of the array if full
        }
        array[count++] = val;  // Add the new element and increase the count
    }



    // Get an element at a specific index
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



    // Private method to resize the array
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];  // Create a new array with the new size
        for (int i = 0; i < count; i++) {
            newArray[i] = array[i];  // Copy the old elements to the new array
        }
        array = newArray;  // Replace the old array with the new one
        size = newSize;  // Update the size to the new size
    }



    // Optional method to remove the last element if needed
    public T removeLast() {
        if (count == 0) {
            throw new IllegalStateException("No elements to remove");
        }
        T val = array[--count];  // Get the last element and decrease the count
        if (count < size / 4 && size > 4) {
            resize(size / 2);  // Shrink the size if too much space is unused
        }
        return val;
    }



    // Iterate over the elements manually (without using Iterator or imports)
    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println(array[i]);
        }
    }
}


// public class DynamicArray<T> {
//     private Object[] arr;
//     private int size;

//     public DynamicArray() {
//         arr = new Object[10]; // Initial capacity
//         size = 0;
//     }

//     public void add(T element) {
//         if (size == arr.length) resize();
//         arr[size++] = element;
//     }

//     public void add(int index, T element) {
//         if (size == arr.length) resize();
//         // Shift elements to the right to make space
//         for (int i = size; i > index; i--) {
//             arr[i] = arr[i - 1];
//         }
//         arr[index] = element;
//         size++;
//     }

//     public T get(int index) {
//         if (index < 0 || index >= size) {
//             throw new ArrayIndexOutOfBoundsException("Invalid index");
//         }
//         return (T) arr[index];
//     }

//     public T remove(int index) {
//         if (index < 0 || index >= size) {
//             throw new ArrayIndexOutOfBoundsException("Invalid index");
//         }
//         T removedElement = (T) arr[index];
//         // Shift elements to the left to fill the gap
//         for (int i = index; i < size - 1; i++) {
//             arr[i] = arr[i + 1];
//         }
//         size--;
//         return removedElement;
//     }

//     public int getSize() {
//         return size;
//     }

//     private void resize() {
//         Object[] newArr = new Object[arr.length * 2];
//         System.arraycopy(arr, 0, newArr, 0, arr.length);
//         arr = newArr;
//     }
// }
