/**
 * TestProgram.java
 * 
 * This class is the entry point for the test program.
 */
public class TestProgram {

    public static void main(String[] args) {
        // Instantiate test runner. Currently there is only one test runner.
        ITestRunner testRunner = new ConsoleTestRunner();
        
        // Run the tests.
        Tests(testRunner);
    }

    /**
     * This method runs the tests.
     * 
     * @param testRunner The test runner to use.
     */
    private static void Tests(ITestRunner testRunner) {
        testRunner.Test(Tests_Dummy.class);
        testRunner.Test(Tests_FileHelper.class);
        testRunner.Test(Tests_Parser_Challenge.class);
        testRunner.Test(Tests_Parser_ChallengeResult.class);
        testRunner.Test(Tests_ChallengeTextDisplay.class);

        testRunner.printTestResults();
    }
}
