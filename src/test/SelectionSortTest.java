//package test;
//
//import org.junit.Test;
//import sorting.SortingAlgorithm;
//import sorting.selectionsort.SelectionSort;
//
//import static org.junit.Assert.assertArrayEquals;
//
//public class SelectionSortTest {
//
//    @Test
//    public void testSelectionSortWithEmptyArray() {
//        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
//        Integer[] arrayToSort = {};
//        Integer[] sortedArray = {};
//        selectionSort.sort(arrayToSort);
//        assertArrayEquals(sortedArray, arrayToSort);
//    }
//
//    @Test
//    public void testSelectionSortWithAlreadySortedArray() {
//        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
//        Integer[] arrayToSort = {1, 2, 4, 5, 7, 9};
//        Integer[] sortedArray = {1, 2, 4, 5, 7, 9};
//        selectionSort.sort(arrayToSort);
//        assertArrayEquals(sortedArray, arrayToSort);
//    }
//
//    @Test
//    public void testSelectionSortWithDuplicateValues() {
//        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
//        Integer[] arrayToSort = {4, 2, 7, 1, 9, 5, 2, 1};
//        Integer[] sortedArray = {1, 1, 2, 2, 4, 5, 7, 9};
//        selectionSort.sort(arrayToSort);
//        assertArrayEquals(sortedArray, arrayToSort);
//    }
//
//    @Test
//    public void testSelectionSortWithNegativeValues() {
//        SortingAlgorithm<Integer> selectionSort = new SelectionSort<>();
//        Integer[] arrayToSort = {4, -2, 7, 1, -9, 5};
//        Integer[] sortedArray = {-9, -2, 1, 4, 5, 7};
//        selectionSort.sort(arrayToSort);
//        assertArrayEquals(sortedArray, arrayToSort);
//    }
//}
