import com.sun.source.tree.Tree;

import java.util.*;
import java.io.File;
        import java.io.FileNotFoundException;

/**
 * Engine for a word search game.
 *
 * -------------------------------
 * **REMINDER FOR MYSELF!!!**
 * -------------------------------
 *      This project is in row-major order, meaning it's in a (row, column)
 *      format. Meaning, (y,x) instead of (x,y).
 *      REMEMBER (y,x) AND NOT NOT NOT NOT NOT NOT NOT (x,y)!!!
 * -------------------------------
 *
 * -------------------------------
 * **INSTANCE FIELDS**
 * -------------------------------
 * TreeSet<String> lexicon - TreeSet containing the words in an input lexicon file.
 * String[][] gameBoard - 2D array representing the game board that the letters
 *                        or phrases appear on.
 * int size - Size of the game board.
 * String[] INIT_LETTERS - Data to fill the INITIAL game board. Redundant, but I
 *                         like it lol.
 * int INIT_SIZE - Size of the INITIAL game board.
 *
 */
public class WordSearchGameEngine implements WordSearchGame{

    private TreeSet<String> lexicon;
    private String[][] gameBoard;
    private int size;
    private static final String[] INIT_LETTERS = {
            "E","E","C","A",
            "A","L","E","P",
            "H","N","B","O",
            "Q","T","T","Y"
    };
    private SortedSet<String> scorableWords;

    /**
     * Game constructor. Sets default game state.
     *                             |
     *   | E E C A |              |
     *   | A L E P | <-----------/
     *   | H N B O |
     *   | Q T T Y |
     *
     */
    public WordSearchGameEngine() {

        lexicon = null;
        setBoard(INIT_LETTERS);

    }

    /**
     * Loads the lexicon into a data structure for later use.
     *
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
    public void loadLexicon(String fileName) {

        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        try {

            lexicon = new TreeSet<String>();
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNext()) {
                lexicon.add(fileScanner.next().toUpperCase());
            }

            fileScanner.close();

        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     *
     * @param letterArray This array of length N^2 stores the contents of the
     *     game board in row-major order. Thus, index 0 stores the contents of board
     *     position (0,0) and index length-1 stores the contents of board position
     *     (N-1,N-1). Note that the board must be square and that the strings inside
     *     may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is  not
     *     square.
     */
    public void setBoard(String[] letterArray) {

        if (letterArray == null) {
            throw new IllegalArgumentException();
        }

        double N = Math.sqrt((double) letterArray.length);

        if ((N % 1) > 0.0000001) {
            throw new IllegalArgumentException();
        }

        size = (int) N;
        gameBoard = new String[size][size];
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = letterArray[index];
                index++;
            }

        }

    }

    /**
     * Creates a String representation of the board, suitable for printing to
     *   standard out. Note that this method can always be called since
     *   implementing classes should have a default board.
     */
    public String getBoard() {

        String output = "";

        for (int i = 0; i < size; i++) {
            output += "\n |";
            for (int j = 0; j < size; j++) {
                output += "\t" + gameBoard[i][j];
            }
            output += "\t|";
        }

        return output;

    }

    /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     *
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        scorableWords = new TreeSet<String>();

        return null;

    }



    /**
     * Computes the cummulative score for the scorable words in the given set.
     * To be scorable, a word must (1) have at least the minimum number of characters,
     * (2) be in the lexicon, and (3) be on the board. Each scorable word is
     * awarded one point for the minimum number of characters, and one point for
     * each character beyond the minimum number.
     *
     * @param words The set of words that are to be scored.
     * @param minimumWordLength The minimum number of characters required per word
     * @return the cummulative score of all scorable words in the set
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        //Solution here

        return 0;

    }

    /**
     * Determines if the given word is in the lexicon.
     *
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidWord(String wordToCheck) {

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        return lexicon.contains(wordToCheck);

    }

    /**
     * Determines if there is at least one word in the lexicon with the
     * given prefix.
     *
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidPrefix(String prefixToCheck) {

        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        String temp = lexicon.ceiling(prefixToCheck);
        if (temp != null) {
            if (temp.startsWith(prefixToCheck)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Determines if the given word is in on the game board. If so, it returns
     * the path that makes up the word.
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with  the path
     *     that makes up the word on the game board. If word is not on the game
     *     board, return an empty list. Positions on the board are numbered from zero
     *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
     *     board, the upper left position is numbered 0 and the lower right position
     *     is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public List<Integer> isOnBoard(String wordToCheck) {

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        LinkedList<Integer> templist = new LinkedList<Integer>();

        return null;

    }

    public String toWord(LinkedList<Integer> inputList) {

        String output = "";
        for (int num : inputList) {
            output += new Position(num).getData();
        }

        return output;

    }

    class Position {

        private int index;
        private int x;
        private int y;
        private String data;
        public static final int MAX_NEIGHBORS = 8;

        public Position(int x, int y) {

            this.x = x;
            this.y = y;
            index = ((this.y * size) + this.x);
            this.data = gameBoard[y][x];

        }

        public Position(int index) {

            this.index = index;
            x = index % size;
            y = (index - x) / size;
            this.data = gameBoard[y][x];

        }

        public String positionToString() {

            return ("y: " + y + " x: " + x
                    + "\n\t- coordinate format (y,x): (" + y + ", " + x + ")"
                    + "\nindex: " + index
                    + "\ndata at position: " + data);

        }

        public Position[] neighbors(LinkedList<Integer> visited) {

            Position[] nbrs = new Position[MAX_NEIGHBORS];
            int k = 0;

            for (int i = x - 1; i <=  x; i++) {

                for (int j = y - 1; j <= y; j++) {

                    if (!((i == x) && (j == y))) {

                        if (isValid(j, i) && !visited.contains((j * size) + i)) {
                            Position temp = new Position(i, j);
                            nbrs[k] = temp;
                            k++;
                        }

                    }

                }

            }

            return nbrs;

        }

        public boolean isValid(int yToTest, int xToTest) {
            return xToTest >= 0 && xToTest < size && yToTest >= 0 && yToTest < size;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getIndex() {
            return index;
        }

        public String getData() {
            return data;
        }

    }

}
