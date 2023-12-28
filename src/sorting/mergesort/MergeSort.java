package sorting.mergesort;

import sorting.SortingAlgorithm;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

    public T[] sort(T[] array) {
        assert array != null : "Array can not be null.";

        if (array.length <= 1) {
            return array;
        }
        int middleIndex = array.length / 2;
        T[] leftPart = Arrays.copyOfRange(array, 0, middleIndex);
        T[] rightPart = Arrays.copyOfRange(array, middleIndex, array.length);

        sort(leftPart);
        sort(rightPart);

        merge(array, leftPart, rightPart);

        return array;
    }

    private void merge(T[] array, T[] left, T[] right) {
        int leftPos = 0;
        int rightPos = 0;
        int mergedPos = 0;

        while (leftPos < left.length && rightPos < right.length) {
            if (left[leftPos].compareTo(right[rightPos]) < 0) {
                array[mergedPos++] = left[leftPos++];
            } else {
                array[mergedPos++] = right[rightPos++];
            }
        }
        while (leftPos < left.length) {
            array[mergedPos++] = left[leftPos++];
        }

        while (rightPos < right.length) {
            array[mergedPos++] = right[rightPos++];
        }
    }
}
