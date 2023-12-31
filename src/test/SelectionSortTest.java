package test;

import org.junit.Test;
import sorting.SortingAlgorithm;
import sorting.selectionsort.SelectionSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SelectionSortTest {

    @Test
    public void testSelectionSortWithEmptyArray() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = {};
        Integer[] sortedArray = {};
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testSelectionSortWithAlreadySortedArray() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = {1, 2, 4, 5, 7, 9};
        Integer[] sortedArray = {1, 2, 4, 5, 7, 9};
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testSelectionSortWithDuplicateValues() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = {4, 2, 7, 1, 9, 5, 2, 1};
        Integer[] sortedArray = {1, 1, 2, 2, 4, 5, 7, 9};
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testSelectionSortWithNegativeValues() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = {4, -2, 7, 1, -9, 5};
        Integer[] sortedArray = {-9, -2, 1, 4, 5, 7};
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testSelectionSortWithLargeArray() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = generateLargeRandomArray(10000); // Adjust the size as needed
        Integer[] sortedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(sortedArray);
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
        System.out.println(Arrays.toString(sortedArray));

    }

    @Test
    public void testSelectionSortWithReversedArray() {
        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
        Integer[] arrayToSort = {9, 7, 5, 4, 2, 1};
        Integer[] sortedArray = {1, 2, 4, 5, 7, 9};
        selectionSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    private Integer[] generateLargeRandomArray(int size) {
        Random random = new Random();
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(); // Generate random integer values
        }

        return array;
    }
}
