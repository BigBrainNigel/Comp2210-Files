import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    private TreeSet<String> lexicon;
    private static final List<String> EMPTY_LADDER = new ArrayList<String>();

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {

            lexicon = new TreeSet<String>();

            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }

    }

    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str);
    }

    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
    @Override
    public int getHammingDistance(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return -1;
        }

        int distance = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }

    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
    @Override
    public List<String> getNeighbors(String word) {

        List<String> nbrs = new ArrayList<String>();

        if (!isWord(word)) {
            return nbrs;
        }

        Iterator<String> itr = lexicon.iterator();
        String temp;
        while (itr.hasNext()) {

            temp = itr.next();
            if (getHammingDistance(word, temp) == 1) {
                nbrs.add(temp);
            }

        }

        return nbrs;
    }

    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
    @Override
    public boolean isWordLadder(List<String> sequence) {

        if ((sequence == null) || (sequence.isEmpty()) || (!isWord(sequence.get(0)))) {
            return false;
        }

        for (int i = 0; i < sequence.size() - 1; i++) {

            String test1 = sequence.get(i);
            String test2 = sequence.get(i + 1);

            if (!isWord(test2)) {
                return false;
            }
            if (getHammingDistance(test1, test2) != 1) {
                return false;
            }

        }

        return true;

    }

    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     *
     * Breadth-first search must be used in all implementing classes.
     *
     * @param  start  the starting word
     * @param  end    the ending word
     * @return        a minimum length word ladder from start to end
     */
    @Override
    public List<String> getMinLadder(String start, String end) {

        String lowerStart = start.toLowerCase();
        String lowerEnd = end.toLowerCase();

        List<String> finalLadder = new ArrayList<String>();
        HashSet<String> visited = new HashSet<String>();

        Queue<Vertex> queue = new LinkedList<Vertex>();
        Vertex vert = new Vertex(lowerStart);
        visited.add(vert.data);
        queue.add(vert);

        if (lowerStart.equals(lowerEnd)) {
            finalLadder.add(lowerStart);
            return finalLadder;
        }

        while (queue.size() != 0) {

            Vertex currentVert = queue.poll();
            List<String> currentNbrs = currentVert.nbrs;

            for (String nbr : currentNbrs) {

                if (!visited.contains(nbr)) {

                    Vertex nbrVert = new Vertex(nbr, currentVert);
                    queue.offer(nbrVert);
                    visited.add(nbr);

                    if (nbr.equals(lowerEnd)) {

                        String word = nbrVert.data;
                        Vertex prev = nbrVert.prev;
                        finalLadder.add(word);

                        while (prev != null) {

                            word = prev.data;
                            finalLadder.add(0, word);
                            prev = prev.prev;

                        }

                        return finalLadder;

                    }

                }

            }

        }

        return EMPTY_LADDER;
    }

    public class Vertex {

        String data;
        Vertex prev;
        List<String> nbrs;

        public Vertex(String dataIn) {

            data = dataIn;
            nbrs = getNeighbors(data);

        }

        public Vertex(String dataIn, Vertex prevIn) {

            data = dataIn;
            prev = prevIn;
            nbrs = getNeighbors(data);

        }

    }

}

