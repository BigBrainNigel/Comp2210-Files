import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if ((a == null) || (key == null) || (comparator == null)) {
            throw new NullPointerException("No input parameter's can be null.");
        }

        int initIndex = initBinarySearch(a, key, comparator);

        for (int i = 0; i <initIndex; i++) {
            if (comparator.compare(a[i], a[initIndex]) == 0) {
                return i;
            }
        }

        return initIndex;

    }

    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if ((a == null) || (key == null) || (comparator == null)) {
            throw new NullPointerException("No input parameter's can be null.");
        }

        int initIndex = initBinarySearch(a, key, comparator);

        for (int i = a.length - 1; i > initIndex; i--) {
            if (initIndex < 0) {
                break;
            }
            else if (comparator.compare(a[i], a[initIndex]) == 0) {
                return i;
            }
        }

        return initIndex;

    }

    private static <Key> int initBinarySearch(Key[] a, Key key, Comparator<Key> comparator) {

        int left = 0;
        int right = a.length - 1;
        int middle;
        while (left <= right) {

            middle = left + ((right - left) / 2);

            if (comparator.compare(key, a[middle]) < 0) {
                right = middle - 1;
            }
            else if (comparator.compare(key, a[middle]) > 0) {
                left = middle + 1;
            }
            else {
                return middle;
            }

        }

        return -1;

    }

}
