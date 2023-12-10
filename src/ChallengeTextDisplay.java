/**
 * ChallengeTextDisplay.java
 * 
 * This class is used to compare user inputted text to the correct text.
 * 
 * Note: This class went through several iterations. Figuring out that I wanted
 * to use a rows of JPanel to display panel rather than a string was the key.
 */

import java.util.*;

public class ChallengeTextDisplay {
    private String text;
    private ArrayList<ArrayList<Word>> wordRows;
   
    /**
     * Default constructor.
     */
    public ChallengeTextDisplay() { }

    /**
     * Constructor.
     * 
     * @param text The text to display.
     */
    public ChallengeTextDisplay(String text) {
        this();
        this.text = text;
        setWordRows();
    }

    /**
     * Set word rows.
     */
    public void setWordRows() {
        wordRows = new ArrayList<ArrayList<Word>>();

        // Each row will be split by a new line.
        for (String line : text.split("\n")) {
            ArrayList<Word> wordRow = new ArrayList<Word>();
            StringBuilder sb = new StringBuilder();

            // Each word will be split by a space.  If there are three or more
            // spaces, a tab will be added.
            int i = 0;
            while (i < line.length()) {
                if (line.charAt(i) == ' ') {

                    // If the string builder is not empty, add the word to the row
                    // and reset the string builder.
                    if (sb.length() != 0) {
                        wordRow.add(new Word(sb.toString()));
                        sb = new StringBuilder();
                    }       

                    // If there are three or more spaces add a tab.
                    if (i + 4 < line.length() && line.substring(i, i + 4 ).equals("    ")) {
                        wordRow.add(new Word("\t", true));
                        i += 3;
                    }
                }
                else {
                    sb.append(line.charAt(i));
                }
                
                i++;
            }

            // Add the last word to the row.
            if (sb.length() != 0) {
                wordRow.add(new Word(sb.toString()));
                sb = new StringBuilder();
            }

            wordRows.add(wordRow);
        }
    }

    // Getters

    /**
     * Get the text.
     * 
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the word rows.
     * 
     * @return The word rows.
     */
    public ArrayList<ArrayList<Word>> getWordRows() {
        return wordRows;
    }

    // Setters

    /**
     * Set the text.
     * 
     * @param text The text.
     */
    public void setText(String text) {
        this.text = text;
    }
}
