public class HeapSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        int n = array.length;
        int lastIndex = array.length - 1;
        // Build the max heap
        for (int i = n/2-1; i >= 0; i--) {
            heapify(array, i, n);
        }
        // Extract max element and heapify the rest of the tree
        while (lastIndex >= 0) {
            swap(0, lastIndex, array);
            heapify(array, 0, lastIndex);
            lastIndex--;
        }

    }

    // Converts given array within given bounds to a max heap
    private static void heapify(Comparable[] array, int startIndex, int lastIndex) {
        int max = startIndex;
        int left = 2*startIndex+1;     //left child
        int right = 2*startIndex+2;    //right child
        // Find the max element's index
        if (left < lastIndex && lessThan(array[max], array[left])) {
            max = left;
        }
        if (right < lastIndex && lessThan(array[max], array[right])) {
            max = right;
        }
        // Swap max element and start element if not equal, and heapify the rest
        if (max != startIndex) {
            swap(startIndex, max, array);
            heapify(array, max, lastIndex);
        }
    }
}
