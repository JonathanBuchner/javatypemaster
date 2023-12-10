/**
 * Word.java
 * 
 * This class is used to represent a word in the text of a challenge.
 * 
 * @author Jonathan Buchner Dec 2023.
 */

import java.awt.*;
import javax.swing.*;

class Word extends JLabel {
    private String word;
    private boolean skip;

    public Word() {
        setFont(new Font("Verdana", Font.PLAIN, 11));
    }

    public Word(String word) {
        this();
        this.setText(word);
        this.word = word;
        this.skip = false;
    }

    public Word(String word, boolean skip) {
        this(word);
        this.skip = skip;
    }

    public void setCorrect() {
        this.setBackground(Color.GREEN);
    }

    public void setIncorrect() {
        this.setBackground(Color.RED);
    }

    // Getters

    /**
     * Get the word.
     * 
     * @return The word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the skip.
     * 
     * @return The skip.
     */
    public boolean getSkip() {
        return skip;
    }

    // Setters

    /**
     * Set the word.  Will also set text 
     * 
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
        this.setText(word);
    }

    /**
     * Set the skip.
     * 
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}