/**
 * Test.java
 * 
 * Returned by tests to indicate the result of a test.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class Test {
    protected String description;
    protected boolean testResult;

    /**
     * Default constructor.
     */
    public Test() {};

    // Getters

    /**
     * This method returns the description of the test.
     * @return The description of the test.
     */
    public String getDescription() {
        
        return this.description;
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
        "\nTest Result: " + this.testResult +
        "\n";
    }
}
