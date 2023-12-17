/**
 * TestException.java
 * 
 * Returned by tests to indicate the result of a test.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class TestException extends Test {
    protected Exception exception;

    /**
     * Default constructor.
     */
    public TestException() {
        super();
    };

    // Getters
    public Exception getException() {
        return this.exception;
    }

    // Setters
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * This method returns a string representation of the test.
     * 
     * @return A string representation of the test.
     */
    public String toString() {
        String exceptionString = this.exception == null ? 
            "No exception thrown." : 
            this.exception.getMessage();


        return "\n" +
        "Description: " + this.description + 
        "\nTest Result: " + this.testResult +
        "\nException: " +  exceptionString +
        "\n";
    }
}
