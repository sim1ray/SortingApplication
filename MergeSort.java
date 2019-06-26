public class MergeSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        mergeSort(array, 0, array.length-1);
    }

    private static void mergeSort(Comparable[] array, int first, int last) {
        if (first >= last) {
            return;
        }
        // Split array into two parts and sort both halves
        int middle = (first + last)/2;
        mergeSort(array, first, middle);
        mergeSort(array, middle+1, last);
        // Merge sorted halves
        mergeHalves(array, first, middle, middle+1, last);
    }

    private static void mergeHalves(Comparable[] array, int left, int leftEnd, int right, int rightEnd) {
        // Create a temp array to store sorted elements
        Comparable[] temp = new Comparable[array.length];
        int start = left;
        int index = left;   //to be incremented
        // Traverse halves simultaneously and sort
        while (left <= leftEnd  && right <= rightEnd) {
            if (lessThan(array[left], array[right])) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
            }
            index++;
        }

        // Traverse rest of elements of whichever half is not completed
        for (;left <= leftEnd; index++,left++) {
            temp[index] = array[left];
        }
        for (;right <= rightEnd; index++,right++) {
            temp[index] = array[right];
        }

        // Copy elements back to array from temp
        for (int i = start; i <= rightEnd; i++) {
            array[i] = temp[i];
        }
    }
}
