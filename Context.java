// Context class for implementing Sort

public class Context {
    private static String[] sortingStrategies = {"BubbleSort", "SelectionSort", "ShellSort", "InsertionSort",
                                            "MergeSort", "HeapSort", "QuickSort"};

    public Context() {
    }

    // Returns the Sorting Object as per the given name
    public static Sorting getSortMethod(String sortName) {
        try {
            return (Sorting) Class.forName(sortName).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    // Returns an array of all the possible sorting strategies
    public static String[] getSortingStrategies() {
        return sortingStrategies;
    }

}
