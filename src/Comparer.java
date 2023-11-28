/**
 * Matcher.java
 * 
 * This class is used to compare user inputted text to the correct text.
 */

public class Comparer {
    private String[] correctText;
    private int currentWordIndex;


    /**
     * Default constructor.
     */
    public Comparer() {
        currentWordIndex = 0;
    }
    /**
     
     * Constructor.
     * 
     * @param correctText The correct text.
     */
    public Comparer(String correctText) {
        this();
        setCorrectText(correctText);
    }

    // Getters

    /**
     * Get the correct text.
     * 
     * @return The correct text.
     */
    public String[] getCorrectText() {
        return correctText;
    }

    /**
     * Get the current word index.
     * 
     * @return The current word index.
     */
    public int getCurrentWordIndex() {
        return currentWordIndex;
    }

    // Setters

    /**
     * Set the correct text.
     * 
     * @param correctText The correct text.
     */
    public void setCorrectText(String correctText) {
        this.correctText = format(correctText);
    }

    /**
     * Set the current word index.
     * 
     * @param currentWordIndex The current word index.
     */
    public void setCurrentWordIndex(int currentWordIndex) {
        this.currentWordIndex = currentWordIndex;
    }

    /**
     * Compare the user inputted text to the correct text.
     * 
     * @param userInput The user inputted text.
     */
    public void compare(String userInput) {

        if (currentWordIndex >= correctText.length) {
            return;
        }

        if (userInput.equals(correctText[currentWordIndex])) {
            currentWordIndex++;
        }
    }

    /**
     * Reset the current word index.
     */
    public void reset() {
        currentWordIndex = 0;
    }

    // Private methods

    /**
     * Break the correct text into words.
     * 
     * @param text
     * @return text split into words
     */
    private String[] format(String text) {
        return text.replaceAll("\\s+", " ").split(" ");
    } 
}
