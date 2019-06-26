public class BubbleSort extends Sorting {

    @Override
    public void sort(Comparable[] array) {
        boolean isSorted = false;
        int lastUnsorted = array.length - 1;
        // Continue as long as array is not sorted
        while (!isSorted){
            isSorted = true;
            for (int j = 0; j < lastUnsorted; j++) {
                if (lessThan(array[j+1], array[j])) {
                    swap(j, j+1, array);
                    isSorted = false;
                }
            }
            lastUnsorted--;
        }
    }

}
