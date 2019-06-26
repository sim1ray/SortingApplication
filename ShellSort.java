public class ShellSort extends Sorting {
    @Override
    public void sort(Comparable[] array) {
        int gap = 1;
        int n = array.length;
        // Calculate an appropriate gap
        while (gap < n/3) {
            gap = 3*gap + 1;
        }
        // Continuously decrease gap size
        for (; gap > 0; gap /= 3) {
            // Perform insertion sort for the elements separated by the given gap
            for (int i = gap; i < n; i++) {
                for (int j = i; j >= gap && lessThan(array[j],array[j-gap]); j -= gap) {
                    swap(j,j-gap, array);
                }
            }
        }
    }
}
