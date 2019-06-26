public class QuickSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(Comparable[] array, int first, int last) {
        if (first < last) {
            // Partition array
            int pivotIndex = partition(array, first, last);
            // Quick sort the portion of array before and after the pivot
            quickSort(array, first, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, last);
        }
    }

    private static int partition(Comparable[] array, int first, int last) {
        // Initially set the pivot to the last element
        Comparable pivot = array[last];
        int i = first - 1;
        // Move elements that are less than pivot to the front of the array
        for (int j = first; j < last; j++) {
            if (lessThan(array[j], pivot)) {
                i++;
                swap(i, j, array);
            }
        }
        // Swap pivot element with first element that is greater than it
        swap(i+1, last, array);
        return i+1;
    }
}
