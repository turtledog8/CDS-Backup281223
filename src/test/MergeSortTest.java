package test;

import org.junit.Test;
import sorting.SortingAlgorithm;
import sorting.mergesort.MergeSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {

    @Test
    public void testMergeSortWithEmptyArray() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {};
        Integer[] sortedArray = {};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testMergeSortWithAlreadySortedArray() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {1, 2, 4, 5, 7, 9};
        Integer[] sortedArray = {1, 2, 4, 5, 7, 9};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testMergeSortWithDuplicateValues() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {4, 2, 7, 1, 9, 5, 2, 1};
        Integer[] sortedArray = {1, 1, 2, 2, 4, 5, 7, 9};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testMergeSortWithNegativeValues() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {4, -2, 7, 1, -9, 5};
        Integer[] sortedArray = {-9, -2, 1, 4, 5, 7};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }
    @Test
    public void testMergeSortWithReverseSortedArray() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {9, 7, 5, 4, 2, 1};
        Integer[] sortedArray = {1, 2, 4, 5, 7, 9};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testMergeSortWithMixedValues() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = {4, -2, 7, 1, 0, -5, 3};
        Integer[] sortedArray = {-5, -2, 0, 1, 3, 4, 7};
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testMergeSortWithLargeArray() {
        SortingAlgorithm<Integer> mergeSort = new MergeSort<>();
        Integer[] arrayToSort = generateLargeRandomArray(10000);
        Integer[] sortedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(sortedArray);
        mergeSort.sort(arrayToSort);
        assertArrayEquals(sortedArray, arrayToSort);
        System.out.println(Arrays.toString(sortedArray));
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
