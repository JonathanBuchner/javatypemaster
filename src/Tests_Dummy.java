/**
 * Test_Dummy.java
 * 
 * This class is used to confirm the test runner is working.
 * 
 * @author Jonathan Buchner Nov 2023.
 */
public class Tests_Dummy extends TestClass {
    /**
     * Default constructor.
     */
    public Tests_Dummy() {
        super("These are dummy tests. One test should pass. One test should fail.");
    }

    /**
     * This method tests that a test can pass.
     */
    public Test dummyTestPassed() {
        TestForEquality<String> test = new TestForEquality<String>();
        test.setDescription("Dummy test SHOULD PASS");
        test.setActual("Dummy");
        test.setExpected("Dummy");
        test.setTestResult(true);

        return test;
    }

    /**
     * This method is intended to tests that a test can fail.
     */
    public Test dummyTestFailed() {
        TestForEquality<String> test = new TestForEquality<String>();
        test.setDescription("Dummy test SHOULD FAIL");
        test.setActual("Dummy");
        test.setExpected("Not dummy");
        test.setTestResult(false);

        return test;
    }
}
