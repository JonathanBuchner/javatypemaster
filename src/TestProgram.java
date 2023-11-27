public class TestProgram {

    public static void main(String[] args) {
        // Instantiate test runner. Currently there is only one test runner.
        ITestRunner testRunner = new ConsoleTestRunner();
        
        // Run the tests.
        Tests(testRunner);
    }

    private static void Tests(ITestRunner testRunner) {
        testRunner.Test(Dummy_tests.class);
        testRunner.Test(FileHelper_tests.class);
        testRunner.Test(Parser_challenge_tests.class);

        testRunner.printTestResults();
    }
}
