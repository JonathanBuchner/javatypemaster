/**
 * Dummy_tests.java
 * 
 * This class is used to confirm the test runner is working.
 * 
 * @author Jonathan Buchner Nov 2023.
 */
public class Dummy_tests extends TestClass {
    /**
     * Default constructor.
     */
    public Dummy_tests() {
        super("These are dummy tests. One test should pass. One test should fail.");
    }

    /**
     * This method tests that a test can pass.
     */
    public Test<String> dummyTestPassed() {
        return new Test<String>("Dummy test SHOULD PASS", "Dummy", "Dummy", true);
    }

    /**
     * This method tests that a test can fail.
     */
    public Test<String> dummyTestFailed() {
        return new Test<String>("Dummy test SHOULD FAIL", "Dummy", "Not Dummy", false);
    }
}
