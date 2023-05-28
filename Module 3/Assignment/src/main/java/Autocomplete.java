import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] terms;
    private final Term[] arrayCopy;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {

        if (terms == null) {
            throw new NullPointerException("Input array cannot be null.");
        }

        arrayCopy = Arrays.copyOf(terms, terms.length);
        Arrays.sort(arrayCopy);

    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {

        if (prefix == null) {
           throw new NullPointerException("Input prefix cannot be null.");
        }

        Term tempTerm = new Term(prefix, 0);
        int firstIndex = BinarySearch.firstIndexOf(arrayCopy, tempTerm, Term.byPrefixOrder(prefix.length()));
        int secondIndex = BinarySearch.lastIndexOf(arrayCopy, tempTerm, Term.byPrefixOrder(prefix.length()));

        Term[] finalArray = Arrays.copyOfRange(arrayCopy, firstIndex, secondIndex + 1);
        Arrays.sort(finalArray, Term.byDescendingWeightOrder());
        return finalArray;

    }

}

