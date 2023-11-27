/**
 * Challenge.java
 * 
 * Represents a challenge.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.util.UUID;

public class Challenge {
    private UUID id;
    private String name;
    private String description;
    private String text;

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
}
