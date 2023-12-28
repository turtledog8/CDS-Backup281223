package search;

public class BinarySearch<T extends Comparable<T>> {
    public int binarySearch(T[] list, T data) {
        assert data != null: "Data can not be null";
        assert list != null: "List can not be null";

        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (list[middle].compareTo(data) == 0) {
                return middle;
            }

            if (list[middle].compareTo(data) < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }
}
