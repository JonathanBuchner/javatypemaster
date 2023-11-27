/**
 * Test.java
 * 
 * Returned by tests to indicate the result of a test.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class TestForEquality<T> extends Test {
    private T actual;
    private T expected;

    /**
     * Default constructor.
     */
    public TestForEquality() {
        super();
    };

    // Getters
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

    // Setters

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
