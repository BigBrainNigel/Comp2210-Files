/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 */
public class CountNegatives {

    /**
     * Returns the number of negative values in the given array.
     */
    public static int countNegatives(int[] numArray) {

        int numNeg=0;

        for(int i = 0;i < numArray.length;i++){
            if (numArray[i] < 0) {
                numNeg += 1;
            }
        }
            return numNeg;

    }
}
