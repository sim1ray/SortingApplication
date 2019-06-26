// Sorting interface

public abstract class Sorting {
    public abstract void sort(Comparable[] array);

    // Swaps elements at given indexes of given array
    protected static void swap(int index1, int index2, Object[] array) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // Returns whether a is less than b
    protected static boolean lessThan(Comparable a, Comparable b) {
        if (a.compareTo(b) == -1) {
            return true;
        }
        return false;
    }

}
