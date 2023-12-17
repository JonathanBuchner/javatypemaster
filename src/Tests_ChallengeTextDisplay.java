import java.util.ArrayList;

/**
 * Tests_ChallengeDisplay.java
 * 
 * This class is used to test the ChallengeDisplay class.
 * 
 * @author Jonathan Buchner Dec 2023.
 */


public class Tests_ChallengeTextDisplay extends TestClass {
    
    public Tests_ChallengeTextDisplay() {
        super("ChallengeDisplay Tests");
    }

    /**
     * This method tests that the ChallengeDisplay can parse challenge.
     * 
     * @return The result of the test.
     */
    public Test canSetWordRows() {
        String challengeText = "example of challenge \n text \n       with       spaces (string[] args) { \n        System.out.println('Does this work');  \n }";
        Test test = new Test("Can set word rows. Multiple asserts.");
        ChallengeTextDisplay cd = new ChallengeTextDisplay(challengeText); // This will automatically set the word rows.

        // Asserts
        ArrayList<ArrayList<Word>> wordRows = cd.getWordRows();

        // Word row 1
        ArrayList<Word> wordRow1 = wordRows.get(0);
        test.setTestResult(wordRow1.get(0).getWord().equals("example"));
        test.setAdditionalResult(wordRow1.get(1).getWord().equals("of"));
        test.setAdditionalResult(wordRow1.get(2).getWord().equals("challenge"));

        // Word row 2
        ArrayList<Word> wordRow2 = wordRows.get(1);
        test.setAdditionalResult(wordRow2.get(0).getWord().equals("text"));

        // Word row 3
        ArrayList<Word> wordRow3 = wordRows.get(2);
        test.setAdditionalResult(wordRow3.get(0).getWord().equals("    "));
        test.setAdditionalResult(wordRow3.get(1).getWord().equals("with"));
        test.setAdditionalResult(wordRow3.get(2).getWord().equals("    "));
        test.setAdditionalResult(wordRow3.get(3).getWord().equals("spaces"));
        test.setAdditionalResult(wordRow3.get(4).getWord().equals("(string[]"));
        test.setAdditionalResult(wordRow3.get(5).getWord().equals("args)"));
        test.setAdditionalResult(wordRow3.get(6).getWord().equals("{"));

        // Word row 4
        ArrayList<Word> wordRow4 = wordRows.get(3);
        test.setAdditionalResult(wordRow4.get(0).getWord().equals("    "));
        test.setAdditionalResult(wordRow4.get(1).getWord().equals("    "));
        test.setAdditionalResult(wordRow4.get(2).getWord().equals("System.out.println('Does"));
        test.setAdditionalResult(wordRow4.get(3).getWord().equals("this"));
        test.setAdditionalResult(wordRow4.get(4).getWord().equals("work');"));

        // Word row 5
        ArrayList<Word> wordRow5 = wordRows.get(4);
        test.setAdditionalResult(wordRow5.get(0).getWord().equals("}"));

        return test;
    }
}
