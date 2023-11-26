/**
 * Test.java
 * 
 * Returned by tests to indicate the result of a test.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class Test<T> {
    public String description;
    public T actual;
    public T expected;
    public boolean testResult;

    /**
     * Default constructor.
     */
    public Test() {};

    /**
     * Constructor.
     * 
     * @param description The description of the test.
     * @param actual The actual result of the test.
     * @param expected The expected result of the test.
     * @param testResult The result of the test.
     */
    public Test(String description, T actual, T expected, boolean testResult) {
        this();
        this.description = description;
        this.actual = actual;
        this.expected = expected;
        this.testResult = testResult;
    }

    // Getters

    /**
     * This method returns the description of the test.
     * @return The description of the test.
     */
    public String getDescription() {
        
        return this.description;
    }

    /**
     * This method returns the actual test input.
     * 
     * @return The actual test input.
     */
    public T getActual() {
        return this.actual;
    }

    /**
     * This method returns the expected test input.
     *
     *  @return The expected test input.
     */
    public T getExpected() {
        return this.expected;
    }

    /**
     * This method returns the store result of the test.
     * 
     * @return The result of the test.
     */
    public boolean getTestResult() {
        return this.testResult;
    }

    // Setters

    /**
     * This method sets the description of the test.
     * 
     * @param description The description of the test.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method sets the actual test input.
     * 
     * @param actual The actual test input.
     */
    public void setActual(T actual) {
        this.actual = actual;
    }

    /**
     * This method sets the expected test input.
     *  
     * @param expected The expected test input.
     */
    public void setExpected(T expected) {
        this.expected = expected;
    }

    /**
     * This method sets the result of the test.
     * 
     * @param testResult The result of the test.
     */
    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }

    /**
     * This method returns a string representation of the test.
     * 
     * @return A string representation of the test.
     */
    public String toString() {
        return "\n" +
        "Description: " + this.description + 
        "\nActual: " + this.actual + 
        "\nExpected: " + this.expected + 
        "\nTest Result: " + this.testResult +
        "\n";
    }
}
