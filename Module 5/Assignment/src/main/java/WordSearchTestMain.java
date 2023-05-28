public class WordSearchTestMain {

    public static void main (String[] args) {

        //Testing game and initial board setup as well as .getBoard().
        WordSearchGame game = WordSearchGameFactory.createGame();
        System.out.println(game.getBoard());

        //Testing .loadLexicon().
        game.loadLexicon("C:\\Users\\gamer\\Comp 2210 Files\\Module 5\\"
                + "Assignment\\src\\main\\resources\\wordFiles\\words.txt");

        //Testing .setBoard().
        String[] a1 = {
                "E","E","C","A",
                "A","L","E","P",
                "H","N","B","O",
                "Q","T","T","Y"
        };;
        game.setBoard(a1);
        System.out.println(game.getBoard());

        //Testing .isValidWord()
        System.out.println(game.isValidWord("CAT"));

        //Testing .isValidPrefix()
        System.out.println(game.isValidPrefix("XXX"));

    }

}
