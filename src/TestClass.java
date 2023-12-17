/**
 * TestClass.java
 * 
 * This class is the base class for all test classes.
 */
public abstract class TestClass {
    public String description;

    /**
     * Default constructor.
     */
    public TestClass() {};

    /**
     * Constructor.
     * 
     * @param description The description of the test.
     */
    public TestClass(String description) {
        this();
        this.description = description;
    }

    // Getters

    /**
     * This method returns the description of the test class.
     * 
     * @return The description of the test.
     */
    public String getDescription() {
        return this.description;
    }

    // Setters

    /**
     * This method sets the description of the test class.
     * 
     * @param description The description of the test.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
