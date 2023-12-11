/**
 * Inputter.java
 * 
 * This class will take the text from the user and update the challenge text display and 
 * will communicate when the system is complete.
 * 
 * @author Jonathan Buchner Dec 2023.
 */

import java.time.*;
import javax.swing.*;
import java.awt.event.*;

 public class Inputter extends JTextField {
    private ChallengeTextDisplay challengeTextDisplay;
    private JTextArea secondsPanel;
    private int column;
    private int row;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Timer timer;

    /**
     * Default constructor.
     */
    public Inputter() { 
        super();
        this.column = 0;
        this.row = 0;

        // Add the key listener.
        addKeyListener(new ProcessKeyPress());
    }

    /**
     * Constructor.
     * 
     * @param challengeTextDisplay The challenge text display.
     */
    public Inputter(ChallengeTextDisplay challengeTextDisplay, JTextArea secondsPanel) {
        this();
        this.challengeTextDisplay = challengeTextDisplay;
        this.secondsPanel = secondsPanel;

        // Set the first word.
        findValidWord();
    }

    /**
     * Check if the challenge is complete.
     */
    public boolean isComplete() {
        return this.row >= challengeTextDisplay.getWordRows().size() - 1 && this.column >= challengeTextDisplay.getWordRows().get(row).size();
    }

    /**
     * Get duration.
     */
    public int getDuration() {
        if (this.startTime == null || this.endTime == null) {
            throw new IllegalStateException("The challenge has not been started or ended.");
        }

        if (this.startTime.isAfter(this.endTime)) {
            throw new IllegalStateException("The start time is after the end time.");
        }

        long mills = Duration.between(this.startTime, this.endTime).toMillis();

        if (mills < 100) {
            throw new IllegalStateException("The duration is less than 100 millisecond.");
        }
        
        // 10 minutes is the max time.
        if (mills > 600000) {
            mills = 600000;
        }

        return (int)  mills;
    }

    /**
     * Get word.
     * 
     * @return The word.
     */
    private Word getCurrentWord() {
        return challengeTextDisplay.getWordRows().get(row).get(column);
    }

 
    /**
     * Start word.
     */
    private void findValidWord() {
        checkForWin();

        // if column is greater than the row length, move to the next row. Note that this works on empty rows.
        if (column >= challengeTextDisplay.getWordRows().get(row).size()) {
            column = 0;
            row++;
            findValidWord();

            return;
        }

        // Skip the word if it is marked as a skip.
        if (getCurrentWord().getSkip()) {
            nextWord();

            return;
        }

        setText("");
        getCurrentWord().setState(WordState.INCOMPLETE);
    }

    /**
     * Progress to next word.
     */
    private void nextWord() {
        column++;
        findValidWord();
    }

    /**
     * Check for win.
     */
    public void checkForWin() {
        if (isComplete()) {
            end();
            JOptionPane.showMessageDialog(null, "You completed the challenge in " + getDuration() + " milliseconds.");
            
        }
    }

    /**
     * Mark if the current input is a substring of the current word.
     */
    private void setWordState() {
        String text = getText();
        Word word = getCurrentWord();

        if (text == null || text.isEmpty()) {
            return;
        }

        if (startTime == null) {
            start();
        }

        // If the word is complete, then move to the next word.
        if (text.equals(word.getWord() + " ") || text.equals(word.getWord() + "\n")) {
            word.setState(WordState.COMPLETE);
            nextWord();

            return;
        } 
        
        // If the test starts with the current word, then mark it as incomplete.
        if (word.getWord().startsWith(text)) {
            word.setState(WordState.INCOMPLETE);

            return;
        } 
        
        // Else
        word.setState(WordState.INCORRECT);
    }

    /*
     * Start the challenge.
     */
    private void start() {
        this.startTime = LocalDateTime.now();
        this.timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Duration duration = Duration.between(startTime, LocalDateTime.now());
                int seconds = (int) duration.getSeconds();
                
                secondsPanel.setText(String.valueOf(seconds));
            }
        });
    }

    /**
     * End the challenge.
     */
    private void end() {
        this.endTime = LocalDateTime.now();
        setText("");
        if (this.timer != null) {
            this.timer.stop();
        }
    }

    // Getters

    /**
     * Get the challengeTextDisplay.
     * 
     * @return The challengeTextDisplay.
     */
    public ChallengeTextDisplay getChallengeTextDisplay() {
        return this.challengeTextDisplay;
    }

    /**
     * Get the column.
     * 
     * @return The column.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Get the row.
     * 
     * @return The row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the start time.
     * 
     * @return The start time.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Get the end time.
     * 
     * @return The end time.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Get the seconds panel.
     */
    public JTextArea getSecondsPanel() {
        return this.secondsPanel;
    }

    /**
     * Get the timer.
     */
    public Timer getTimer() {
        return this.timer;
    }

    // Setters

    /**
     * Set the challengeTextDisplay.
     * 
     * @param challengeTextDisplay The challengeTextDisplay.
     */
    public void setChallengeTextDisplay(ChallengeTextDisplay challengeTextDisplay) {
        this.challengeTextDisplay = challengeTextDisplay;
        findValidWord();
    }

    /**
     * Set the column.
     * 
     * @param column The column.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Set the row.
     * 
     * @param row The row.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Set the start time.
     * 
     * @param startTime The start time.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Set the end time.
     * 
     * @param endTime The end time.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Set the seconds panel.
     */
    public void setSecondsPanel(JTextArea secondsPanel) {
        this.secondsPanel = secondsPanel;
    }

    /**
     * Set the timer.
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    // Private classes

    /**
     * Handle the key press.
     * 
     * @param e The key event.
     */
    class ProcessKeyPress extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            setWordState();
        }
    }
}