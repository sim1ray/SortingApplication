public class InsertionSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i-1;
            while (j >= 0 && lessThan(array[j+1],array[j])) {
                swap(j+1, j, array);
                j--;
            }
        }
    }
}
