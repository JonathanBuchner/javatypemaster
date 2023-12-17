import java.util.UUID;

/**
 * HighScore.java
 * 
 * This class will hold and retrieve the high score.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public class HighScore {
    private UUID id;
    private String challengeName;
    private String highFirstName;
    private int highScore;
    private String yourFirstName;
    private String yourLastName;
    private int yourHighScore;

    /**
     * Default constructor.
     */
    public HighScore() { }

    /**
     * Constructor.
     * 
     * @param id The UUID of the high score.
     * @param challengeName The name of the challenge.
     * @param highFirstName The first name of the person with the high score.
     * @param highScore The high score.
     * @param yourFirstName Your first name.
     * @param yourHighScore Your high score.
     */
    public HighScore(UUID id, String challengeName, String highFirstName, int highScore, String yourFirstName, String yourLastName, int yourHighScore) {
        this();
        this.id = id;
        this.challengeName = challengeName;
        this.highFirstName = highFirstName;
        this.highScore = highScore;
        this.yourFirstName = yourFirstName;
        this.yourLastName = yourLastName;
        this.yourHighScore = yourHighScore;
    }

    // Getters

    /**
     * Gets the UUID of the high score.
     * 
     * @return The UUID of the high score.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of the challenge.
     * 
     * @return The name of the challenge.
     */
    public String getChallengeName() {
        return challengeName;
    }

    /**
     * Gets the first name of the person with the high score.
     * 
     * @return The first name of the person with the high score.
     */
    public String getHighFirstName() {
        return highFirstName;
    }

    /**
     * Gets the high score.
     * 
     * @return The high score.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Gets your first name.
     * 
     * @return Your first name.
     */
    public String getYourFirstName() {
        return yourFirstName;
    }

    /**
     * Gets your last name.
     * 
     * @return Your last name.
     */
    public String getYourLastName() {
        return yourLastName;
    }

    /**
     * Gets your high score.
     * 
     * @return Your high score.
     */
    public int getYourHighScore() {
        return yourHighScore;
    }

    // Setters

    /**
     * Sets the UUID of the high score.
     * 
     * @param id The UUID of the high score.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the name of the challenge.
     * 
     * @param challengeName The name of the challenge.
     */
    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    /**
     * Sets the first name of the person with the high score.
     * 
     * @param highFirstName The first name of the person with the high score.
     */
    public void setHighFirstName(String highFirstName) {
        this.highFirstName = highFirstName;
    }

    /**
     * Sets the high score.
     * 
     * @param highScore The high score.
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Sets your first name.
     * 
     * @param yourFirstName Your first name.
     */
    public void setYourFirstName(String yourFirstName) {
        this.yourFirstName = yourFirstName;
    }

    /**
     * set your last name
     * 
     * @param String yourLastName
     */
    public void setYourLastName(String yourLastName) {
        this.yourLastName = yourLastName;
    }

    /**
     * Sets your high score.
     * 
     * @param yourHighScore Your high score.
     */
    public void setYourHighScore(int yourHighScore) {
        this.yourHighScore = yourHighScore;
    }


}
