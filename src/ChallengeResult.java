/**
 * ChallengeResult.java
 * 
 * This class is used to store the result of a challenge.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Notes: I originally wanted to print a comma delimited file, but I realized
 * a comma was not a good deliminator.  I then decided as an odd string as 
 * a deliminator.  I chose ",^-" because it is unlikely to be used in a project.
 * There are better approaches, but I thought this one worked in this case.
 */

import java.time.LocalDateTime;
import java.util.UUID;


public class ChallengeResult {
    private static final String deliminator = "~!~"; // ,^- 
    private UUID id;
    private Challenge challenge;
    private LocalDateTime dateCompleted;
    private String firstName;
    private String lastName;
    private int secondsToComplete;

    /**
     * Default constructor.
     */
    public ChallengeResult() { }

    /**
     * Constructor.
     * 
     * @param id The UUID of the challenge result.
     * @param challenge The challenge that was completed.
     * @param dateCompleted The date the challenge was completed.
     * @param firstName The first name of the user who completed the challenge.
     * @param lastName The last name of the user who completed the challenge.
     * @param secondsToComplete The number of seconds it took to complete the challenge.
     */
    public ChallengeResult(UUID id, Challenge challenge, LocalDateTime dateCompleted, String firstName, String lastName, int secondsToComplete) {
        this();
        this.id = id;
        this.challenge = challenge;
        this.dateCompleted = dateCompleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondsToComplete = secondsToComplete;
    }

    // Getters

    /**
     * This method returns the deliminator used to separate the fields.
     * @return
     */
    public static String getDeliminator() {
        return deliminator;
    }

    /**
     * Gets the UUID of the challenge result.
     * 
     * @return The UUID of the challenge result.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the challenge that was completed.
     * 
     * @return The challenge that was completed.
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * Gets the date the challenge was completed.
     * 
     * @return The date the challenge was completed.
     */
    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    /**
     * Gets the first name of the user who completed the challenge.
     * 
     * @return The first name of the user who completed the challenge.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user who completed the challenge.
     * 
     * @return The last name of the user who completed the challenge.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the number of seconds it took to complete the challenge.
     * 
     * @return The number of seconds it took to complete the challenge.
     */
    public int getSecondsToComplete() {
        return secondsToComplete;
    }

    // Setters

    /**
     * Sets the UUID of the challenge result.
     * 
     * @param id The UUID of the challenge result.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the challenge that was completed.
     * 
     * @param challenge The challenge that was completed.
     */
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    /**
     * Sets the date the challenge was completed.
     * 
     * @param dateCompleted The date the challenge was completed.
     */
    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * Sets the first name of the user who completed the challenge.
     * 
     * @param firstName The first name of the user who completed the challenge.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user who completed the challenge.
     * 
     * @param lastName The last name of the user who completed the challenge.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the number of seconds it took to complete the challenge.
     * 
     * @param secondsToComplete The number of seconds it took to complete the challenge.
     */
    public void setSecondsToComplete(int secondsToComplete) {
        this.secondsToComplete = secondsToComplete;
    }

    /**
     * This method returns a string representation of the challenge result.
     * 
     * @return A string representation of the challenge result.
     */
    public String toString() {
        return ChallengeResult.deliminator +
            this.id.toString() + ChallengeResult.deliminator +
            this.challenge.getId().toString() + ChallengeResult.deliminator +
            this.dateCompleted.toString() + ChallengeResult.deliminator +
            this.firstName + ChallengeResult.deliminator +
            this.lastName + ChallengeResult.deliminator +
            this.secondsToComplete;
    }
}
