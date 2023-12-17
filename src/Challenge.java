/**
 * Challenge.java
 * 
 * Challenge object. Challenges are stored as markdown files and are loaded into
 * this object upon the start of the game.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.util.UUID;

public class Challenge {
    private UUID id;
    private String name;
    private String description;
    private String text;
    private HighScore highScore;

    /**
     * Default constructor.
     */
    public Challenge() { }

    /**
     * Constructor.
     * 
     * @param id The UUID of the challenge.
     * @param name The name of the challenge.
     * @param description The description of the challenge.
     * @param text The text of the challenge.
     */
    public Challenge(UUID id, String name, String description, String text) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.text = text;
    }

    /**
     * Constructor.
     * 
     * @param id The UUID of the challenge.
     * @param name The name of the challenge.
     * @param description The description of the challenge.
     * @param text The text of the challenge.
     * @param highScore The high score of the challenge.
     */
    public Challenge(UUID id, String name, String description, String text, HighScore highScore) {
        this(id, name, description, text);
        this.highScore = highScore;
    }

    /**
     * Checks if the UUID of the challenge is set.
     * 
     * @return True if the UUID of the challenge is set, false otherwise.
     */
    public boolean idIsSet() {
        return id != null;
    }

    /**
     * Checks if the name of the challenge is set.
     * 
     * @return True if the name of the challenge is set, false otherwise.
     */ 
    public boolean nameIsSet() {
        return name != null;
    }

    /**
     * Checks if the description of the challenge is set.
     * 
     * @return True if the description of the challenge is set, false otherwise.
     */
    public boolean descriptionIsSet() {
        return description != null;
    }

    /**
     * Checks if the text of the challenge is set.
     * 
     * @return True if the text of the challenge is set, false otherwise.
     */
    public boolean textIsSet() {
        return text != null;
    }

    /**
     * Checks if the challenge is valid.
     * 
     * @return True if the challenge is valid, false otherwise.
     */
    public boolean isValid() {
        return idIsSet() 
            && nameIsSet() 
            && descriptionIsSet() 
            && textIsSet();
    }

    // Getters

    /**
     * Gets the UUID of the challenge.
     * 
     * @return The UUID of the challenge.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of the challenge.
     * 
     * @return The name of the challenge.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the challenge.
     * 
     * @return The description of the challenge.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the text of the challenge.
     * 
     * @return The text of the challenge.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the high score of the challenge.
     * 
     * @return The high score of the challenge.
     */
    public HighScore getHighScore() {
        return highScore;
    }

    // Setters

    /**
     * Sets the UUID of the challenge.
     * 
     * @param id The UUID of the challenge.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the name of the challenge.
     * 
     * @param name The name of the challenge.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the challenge.
     * 
     * @param description The description of the challenge.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the text of the challenge.
     * 
     * @param text The text of the challenge.
     */
    public void setText(String text) {
        this.text = text;
    } 

    /**
     * Set high score
     */
    public void setHighScore(HighScore highScore) {
        this.highScore = highScore;
    }
}
