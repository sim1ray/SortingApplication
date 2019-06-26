public class SelectionSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                // Find minimum element
                if (lessThan(array[j], array[min])) {
                    min = j;
                }
            }
            swap(i, min, array);
        }
    }
}
