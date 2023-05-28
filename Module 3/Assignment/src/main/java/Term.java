import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 *
 */
public class Term implements Comparable<Term> {

    private final String query;
    private final long weight;
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {

        if (query == null) {
            throw new NullPointerException("Input query cannot be null.");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Input weight cannot be negative.");
        }

        this.query = query;
        this.weight = weight;

    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {

        return new byDescendingWeightOrderClass();

    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("Input length cannot be less than or equal to zero.");
        }

        return new byPrefixOrderClass(length);

    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return this.getQuery().compareTo(other.getQuery());
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){

        return (this.query + "\t" + this.weight);

    }

    private String getQuery() {
        return query;
    }

    private long getWeight() {
        return  weight;
    }

    private static class byPrefixOrderClass implements Comparator<Term> {

        private final int length;
        private byPrefixOrderClass(int length) {
            this.length = length;
        }

        private String findLength(String termString) {

            int finalIndex;

            if (termString.length() > length) {
                finalIndex = length;
            }
            else {
                finalIndex  = termString.length();
            }

            return termString.substring(0, finalIndex);

        }

        @Override
        public int compare(Term term1, Term term2) {

            String query1Cut = findLength(term1.getQuery());
            String query2Cut = findLength(term2.getQuery());

            if (query1Cut.compareToIgnoreCase(query2Cut) < 0) {
                return -1;
            }
            else if (query1Cut.compareToIgnoreCase(query2Cut) > 0) {
                return 1;
            }
            else {
                return 0;
            }

        }

    }

    private static class byDescendingWeightOrderClass implements Comparator<Term> {

        @Override
        public int compare(Term term1, Term term2) {

            if (term1.getWeight() < term2.getWeight()) {
                return 1;
            }
            else if (term1.getWeight() > term2.getWeight()) {
                return -1;
            }
            else {
                return 0;
            }

        }

    }

}



