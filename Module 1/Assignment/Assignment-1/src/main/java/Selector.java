import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   YOUR NAME (YOU@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
*
*/
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) throws IllegalArgumentException{

        if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero.");
        }
        else {

            int minimum = a[0];
            for (int i = 1; i < a.length; i++) {
                if (a[i] < minimum) {
                    minimum = a[i];
                }
            }

            return minimum;

        }

    }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) throws IllegalArgumentException {

        if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero.");
        }
        else {

            int maximum = a[0];
            for (int i = 1; i < a.length; i++) {
                if (a[i] > maximum) {
                    maximum = a[i];
                }
            }

            return maximum;

        }

    }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     *
     * Ex: if k is 5 you want a value from the array that has 4 values
     * less than it. If k is 1 you want a value with 0 values less than it.
     * In array [1, 2, 3, 4, 5] if k = 3 then the kmin will have 3 - 1 or
     * 2 values less than it, so the kmin would be 3 because only 2 and 1 are
     * less than it in the array.
     */
    public static int kmin(int[] a, int k) throws IllegalArgumentException {

        //Initializing variable for the kth min.
        int kthMin;

        //Check if 'a' is null or zero and if k is less than 1 or greater than the length of 'a'
        if ((a == null) || (a.length == 0) || (k < 1) || k > a.length) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero, " +
                    "or \"k\" cannot be less than one or greater than the length of the array.");
        } else {

            //Failsafe for if array 'a' is only of length one
            if (a.length == 1) {
                kthMin = a[0];
                return kthMin;
            }

            //Creating and sorting (lowest to highest) a copy of array 'a'
            int[] copyOfA = Arrays.copyOf(a, a.length);
            Arrays.sort(copyOfA);

            //Counting number of duplicates in the array
            int duplicateCount = 0;
            for (int i = 0; i < copyOfA.length - 1; i++) {
                if (copyOfA[i] == copyOfA[i + 1]) {
                    duplicateCount++;
                }
            }

            //Creating a new array to be filled with values without dupes.
            int[] copyOfANoDupe = new int[copyOfA.length - duplicateCount];

            //Filling array with unique values.
            int count = 0;
            for (int i = 0; i < copyOfA.length - 1; i++) {
                if (copyOfA[i] != copyOfA[i + 1]) {
                    copyOfANoDupe[count] = copyOfA[i];
                    count++;
                }
            }

            //Extra failsafe for if array 'a' is only of length one after getting
            //rid of duplicates

            copyOfANoDupe[count] = copyOfA[copyOfA.length - 1];

            //Finding actual value of the kth min.
            if (copyOfANoDupe.length == 1 && k != 1) {

                throw new IllegalArgumentException("Array length without duplicate values is too small "
                    + "to find kth min when k is: " + k);

            }
            else {

                int kthMinIndex = k - 1;
                kthMin = copyOfANoDupe[kthMinIndex];

            }

        }

        return kthMin;

    }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     *
     * Ex: if k is 5 you want a value from the array that has 4 values
     * (or k - 1, e.g. 5 - 1) greater than it. If k is 1 you want a value with
     * 0 values greater than it. In array [5, 9, 1, 3 ,7] if k = 3 then the kmax
     * will have 3 - 1 or 2 values greater than it, so the kmax would be 5 because
     * only 9 and 7 are greater than it in the array.
     */
    public static int kmax(int[] a, int k) {

        //Initializing variable for the kth min.
        int kthMax;

        //Check if 'a' is null or zero and if k is less than 1 or greater than the length of 'a'
        if ((a == null) || (a.length == 0) || (k < 1) || k > a.length) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero, " +
                    "or \"k\" cannot be less than one or greater than the length of the array.");
        } else {

            //Failsafe for if array 'a' is only of length one
            if (a.length == 1) {
                kthMax = a[0];
                return kthMax;
            }

            //Creating and sorting (lowest to highest) a copy of array 'a'
            int[] copyOfA = Arrays.copyOf(a, a.length);
            Arrays.sort(copyOfA);

            //Counting number of duplicates in the array
            int duplicateCount = 0;
            for (int i = 0; i < copyOfA.length - 1; i++) {
                if (copyOfA[i] == copyOfA[i + 1]) {
                    duplicateCount++;
                }
            }

            //Creating a new array to be filled with values without dupes.
            int[] copyOfANoDupe = new int[copyOfA.length - duplicateCount];

            //Filling array with unique values.
            int count = 0;
            for (int i = 0; i < copyOfA.length - 1; i++) {
                if (copyOfA[i] != copyOfA[i + 1]) {
                    copyOfANoDupe[count] = copyOfA[i];
                    count++;
                }
            }

            //Extra failsafe for if array 'a' is only of length one after getting
            //rid of duplicates

            copyOfANoDupe[count] = copyOfA[copyOfA.length - 1];

            //Finding actual value of the kth min.
            if (copyOfANoDupe.length == 1 && k != 1) {

                throw new IllegalArgumentException("Array length without duplicate values is too small "
                        + "to find kth min when k is: " + k);

            }
            else {

                int kthMaxIndex = (copyOfANoDupe.length - k);
                kthMax = copyOfANoDupe[kthMaxIndex];

            }

        }

        return kthMax;

    }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) throws IllegalArgumentException{

        if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero.");
        }
        else {

            int[] tempArray = new int[50];
            int tempArrayIndex = 0;

            for (int i = 0; i < a.length; i++) {
                if ((a[i] >= low) && (a[i] <= high)) {
                    tempArray[tempArrayIndex] = a[i];
                    tempArrayIndex++;
                }
            }

            int[] returnArray = Arrays.copyOf(tempArray,tempArrayIndex);
            return returnArray;

        }

    }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) throws IllegalArgumentException {

        boolean qualifies = false;
        int ceiling = 100000;

        if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero.");
        }
        else {

            for (int value : a) {
                if ((value <= ceiling) && (value >= key)) {
                        ceiling = value;
                        qualifies = true;
                }

            }

        }

        if (!qualifies) {
            throw new IllegalArgumentException("No qualifying value found.");
        }

        return ceiling;

    }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {

        boolean qualifies = false;
        int floor = -100000;

        if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("Array cannot be null or equal to zero.");
        }
        else {

            for (int value : a) {
                if ((value >= floor ) && (value <= key)) {
                    floor = value;
                    qualifies = true;
                }

            }

        }

        if (!qualifies) {
            throw new IllegalArgumentException("No qualifying value found.");
        }

        return floor;

    }

}
