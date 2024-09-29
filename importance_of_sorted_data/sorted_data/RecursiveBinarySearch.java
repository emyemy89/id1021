public class RecursiveBinarySearch {

    // Recursive binary search method
    private static boolean recursive(int[] arr, int key, int min, int max) {
        // Base case: if the range is invalid, return false
        if (min > max) {
            return false;
        }

        // Find the middle index
        int mid = min + (max - min) / 2;

        // Base case, return true
        if (arr[mid] == key) {
            return true;
        }

        //  search in the left half
        if (arr[mid] > key) {
            return recursive(arr, key, min, mid - 1);
        }

        // search in the right half
        else {
            return recursive(arr, key, mid + 1, max);
        }
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        int key = 7;

        // use recursive binary search
        boolean found = recursive(sortedArray, key, 0, sortedArray.length - 1);

        if (found) {
            System.out.println("Key " + key + " found in the array.");
        } else {
            System.out.println("Key " + key + " not found in the array.");
        }
    }
}

