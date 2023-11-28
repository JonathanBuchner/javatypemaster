/**
 * Comparer_tests.java
 * 
 * This class is used to test the Comparer class.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class Comparer_tests extends TestClass {
    
    /**
     * Default constructor.
     */
    public Comparer_tests() { 
        super("Testing the comparer class.");
    }

    /**
     * This method tests that the Comparer can compare a string to the current word
     * and if equal advance the index.
     */
    public Test canCompareAStringToTheCurrentWord() {
        Test test = new Test();
        test.setDescription("Can compare a string to the current word");
        int expected = 1;

        // Setting up the comparer.
        Comparer comparer = new Comparer();
        comparer.setCorrectText("This is a test.");
        comparer.setCurrentWordIndex(0);

        // Testing the comparer.
        comparer.compare("This");
        test.setTestResult(Assert.areEqual(expected, comparer.getCurrentWordIndex()));

        return test;
    }

    /**
     * This method tests that the Comparer can compare a string to the current word
     * and if not equal not advance the index.
     */
    public Test canCompareWrongStringToTheCurrentWord() {
        Test test = new Test();
        test.setDescription("Can compare a string to the current word");
        int expected = 0;

        // Setting up the comparer.
        Comparer comparer = new Comparer();
        comparer.setCorrectText("this is a test.");
        comparer.setCurrentWordIndex(0);

        // Testing the comparer.
        comparer.compare("This");
        test.setTestResult(Assert.areEqual(expected, comparer.getCurrentWordIndex()));

        return test;
    }
}
