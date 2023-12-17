/**
 * Word.java
 * 
 * This class is used to represent a word in the text of a challenge.
 * 
 * author Jonathan Buchner Dec 2023.
 */
import java.awt.*;
import javax.swing.*;

class Word extends JLabel {
    private String word;
    private boolean skip;
    private WordState state;

    /*
     * Default constructor.
     */
    public Word() {
        setFont(new Font("Verdana", Font.PLAIN, 15));
        this.state = WordState.NOT_STARTED;
        setForeground(Color.BLACK);
    }

    /*
     * Constructor.
     * 
     * @param word The word.
     */
    public Word(String word) {
        this();
        this.setText(word);
        this.word = word;
        this.skip = false;
    }

    /*
     * Constructor.
     * 
     * @param word The word.
     * @param skip The skip.
     */
    public Word(String word, boolean skip) {
        this(word);
        this.skip = skip;
    }

    /*
     * Constructor.
     * 
     * @param word The word.
     * @param skip The skip.
     * @param isTab The isTab.
     */
    public Word(String word, boolean skip, boolean isTab) {
        this("    ", skip);
    }

    /* 
     * Sets the color based on the state.
     */
    public void setColor() {
        if (this.state == WordState.COMPLETE) {
            this.setCorrect();
        } else if (this.state == WordState.INCORRECT) {
            this.setIncorrect();
        } else if (this.state == WordState.IN_PROGRESS) {
            this.setInprogress();
        } else {
            this.setNotStarted();
        }
    }

    /**
     * Set correct color.
     */
    private void setCorrect() {
        this.setForeground(new Color(0, 163, 108));
    }

    /**
     * Set incomplete color.
     */
    private void setInprogress() {
        this.setForeground(Color.BLUE);
    }

    /**
     * Set incorrect color.
     */
    private void setIncorrect() {
        this.setForeground(Color.RED);
    }

    /**
     * Set not started color.
     */
    private void setNotStarted() {
        this.setForeground(Color.BLACK);
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

    /**
     * Get the state.
     * 
     * @return The state.
     */
    public WordState getState() {
        return state;
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

    /**
     * Set the state.
     * 
     * @param state
     */
    public void setState(WordState state) {
        this.state = state;
        this.setColor();
    }
}