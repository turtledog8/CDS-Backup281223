package sorting.selectionsort;

import sorting.SortingAlgorithm;

public class SelectionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    public T[] sort(T[] array) {
        assert array != null : "Array can not be null.";

        if (array.length == 0 || array[0] == null) {
            return array;
        }

        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < size ; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
