import java.lang.reflect.Array;
import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME HERE (you@auburn.edu)
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
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {

        T min;

        if ((coll == null)) {
            throw new IllegalArgumentException("Collection is null.");
        } else if (comp == null) {
            throw new NoSuchElementException("Comparator is null.");
        } else {
            Iterator<T> itr = coll.iterator();
            min = (T) itr.next();

            while (itr.hasNext()) {
                T tempNum = (T) itr.next();
                if (comp.compare(tempNum, min) < 0) {
                    min = tempNum;
                }

            }

        }

        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {

        T max;

        if ((coll == null)) {
            throw new IllegalArgumentException("Collection is null.");
        } else if (comp == null) {
            throw new NoSuchElementException("Comparator is null.");
        } else {

            Iterator<T> itr = coll.iterator();
            max = (T) itr.next();

            while (itr.hasNext()) {
                T tempNum = (T) itr.next();
                if (comp.compare(tempNum, max) > 0) {
                    max = tempNum;
                }

            }

        }

        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * Ex: if k is 5 you want a value from the array that has 4 values
     * less than it. If k is 1 you want a value with 0 values less than it.
     * In array [1, 2, 3, 4, 5] if k = 3 then the kmin will have 3 - 1 or
     * 2 values less than it, so the kmin would be 3 because only 2 and 1 are
     * less than it in the array.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {

        T kthMin = null;

        if (coll == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        } else if (comp == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (coll.isEmpty()) {
            throw new NoSuchElementException("Collection cannot be empty.");
        } else if ((k < 1) || (k > coll.size())) {
            throw new NoSuchElementException("k value cannot be less thank 1 " +
                    "or k value cannot be greater than the size of the " +
                    "input collection.");
        }
        //Failsafe for if "coll" is == 1
        else if (coll.size() == 1) {
            Iterator<T> itr = coll.iterator();
            return itr.next();
        } else {

            //Creates a copy of "coll" and sorts it.
            ArrayList<T> copyOfColl = new ArrayList<T>();
            Iterator<T> itr = coll.iterator();
            while (itr.hasNext()) {
                copyOfColl.add(itr.next());
            }
            java.util.Collections.sort(copyOfColl, comp);

            //Checks is k == 1 and returns the first element in the collection if true. Else, creates
            //a copy of the sorted array that contains no duplicates, and tracks how many non-duplicate
            //values are added to the new collection. If the amount of non-duplicate values added is less
            //than the value of k, an exception is thrown. Else, the kth minimum is calculated.
            if (k == 1) {
                return copyOfColl.get(0);
            }
            else {

                ArrayList<T> copyOfCollNoDupes = new ArrayList<T>();
                int tracker = 1;

                for (int i = 0; i < copyOfColl.size(); i++) {
                    if (!((i + 1) >= (copyOfColl.size()))) {
                        if (!(copyOfColl.get(i).equals(copyOfColl.get(i + 1)))) {
                            copyOfCollNoDupes.add(copyOfColl.get(i));
                            tracker++;
                        }
                    }
                    else {
                        copyOfCollNoDupes.add(copyOfColl.get(i));
                        tracker++;
                    }
                }

                if (tracker < k) {
                    throw new NoSuchElementException("The " + k + "th minimum doesn't exist.");
                }
                else {
                    kthMin = copyOfCollNoDupes.get(k - 1);
                }

            }

            return kthMin;

        }
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * * Ex: if k is 5 you want a value from the array that has 4 values
     * (or k - 1, e.g. 5 - 1) greater than it. If k is 1 you want a value with
     * 0 values greater than it. In array [5, 9, 1, 3 ,7] if k = 3 then the kmax
     * will have 3 - 1 or 2 values greater than it, so the kmax would be 5 because
     * only 9 and 7 are greater than it in the array.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {

        T kthMax = null;

        if (coll == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        } else if (comp == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (coll.isEmpty()) {
            throw new NoSuchElementException("Collection cannot be empty.");
        } else if ((k < 1) || (k > coll.size())) {
            throw new NoSuchElementException("k value cannot be less thank 1 " +
                    "or k value cannot be greater than the size of the " +
                    "input collection.");
        }
        //Failsafe for if "coll" is == 1
        else if (coll.size() == 1) {
            Iterator<T> itr = coll.iterator();
            return itr.next();
        } else {

            //Creates a copy of "coll" and sorts it.
            ArrayList<T> copyOfColl = new ArrayList<T>();
            Iterator<T> itr = coll.iterator();
            while (itr.hasNext()) {
                copyOfColl.add(itr.next());
            }
            java.util.Collections.sort(copyOfColl, comp);

            //Checks is k == 1 and returns the first element in the collection if true. Else, creates
            //a copy of the sorted array that contains no duplicates, and tracks how many non-duplicate
            //values are added to the new collection. If the amount of non-duplicate values added is less
            //than the value of k, an exception is thrown. Else, the kth minimum is calculated.
            if (k == 1) {
                return copyOfColl.get(copyOfColl.size() - 1);
            }
            else {

                ArrayList<T> copyOfCollNoDupes = new ArrayList<T>();
                int tracker = 1;

                for (int i = 0; i < copyOfColl.size(); i++) {
                    if (!((i + 1) >= (copyOfColl.size()))) {
                        if (!(copyOfColl.get(i).equals(copyOfColl.get(i + 1)))) {
                            copyOfCollNoDupes.add(copyOfColl.get(i));
                            tracker++;
                        }
                    }
                    else {
                        copyOfCollNoDupes.add(copyOfColl.get(i));
                        tracker++;
                    }
                }

                if (tracker < k) {
                    throw new NoSuchElementException("The " + k + "th maximum doesn't exist.");
                }
                else {
                    kthMax = copyOfCollNoDupes.get(copyOfCollNoDupes.size() - k);
                }

            }

            return kthMax;

        }
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp) {

        ArrayList<T> rangeColl = new ArrayList<T>();

        if (coll == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        }
        else if (comp == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        else if (coll.isEmpty()) {
            throw new NoSuchElementException("Collection cannot be empty.");
        }
        else {

            Iterator<T> itr = coll.iterator();;

            while (itr.hasNext()) {

                T tempNum = itr.next();

                if ((comp.compare(tempNum, low) >= 0) && (comp.compare(tempNum, high) <= 0)) {
                    rangeColl.add(tempNum);
                }

            }
            if (rangeColl.isEmpty()) {
                throw new NoSuchElementException("No values in the input collection that fall within " +
                        "input low and high range.");
            }

        }

        return rangeColl;

    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {

        T ceiling = null;
        T tempNum;
        boolean qualifies = false;

        if (coll == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        }
        else if (comp == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        else if (coll.isEmpty()) {
            throw new NoSuchElementException("Collection cannot be empty.");
        }
        else {

            Iterator<T> itr = coll.iterator();
            while (itr.hasNext()) {

                tempNum = itr.next();

                if ((qualifies == false) && (comp.compare(tempNum, key) >= 0)) {
                    ceiling = tempNum;
                    qualifies = true;
                }
                else if ((comp.compare(tempNum, key) >= 0) && (comp.compare(tempNum, ceiling) <= 0)) {
                    ceiling = tempNum;
                }

            }

            if (!qualifies) {
                throw new NoSuchElementException("No qualifying value has been found in the " +
                        "input collection.");
            }

        }

        return ceiling;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {

        T floor = null;
        T tempNum;
        boolean qualifies = false;

        if (coll == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        }
        else if (comp == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        else if (coll.isEmpty()) {
            throw new NoSuchElementException("Collection cannot be empty.");
        }
        else {

            Iterator<T> itr = coll.iterator();
            while (itr.hasNext()) {

                tempNum = itr.next();
                if ((qualifies == false) && (comp.compare(tempNum, key) <= 0)) {
                    floor = tempNum;
                    qualifies = true;
                }
                else if ((comp.compare(tempNum, key) <= 0) && (comp.compare(tempNum, floor) >= 0)) {
                    floor = tempNum;
                    qualifies = true;
                }

            }

            if (!qualifies) {
                throw new NoSuchElementException("No qualifying value has been found in the " +
                        "input collection.");
            }

        }

        return floor;
    }

}
