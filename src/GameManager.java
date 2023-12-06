/**
 * GameManager.java
 * 
 * This class will manage the game.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class GameManager {
    private final String challengeFilesPath = "../data/challenges/";
    private final String challengeResultsPath = "../data/data.csv";

    private Parser parser;
    private ArrayList<ChallengeResult> challengeResults;
    private ArrayList<HighScore> highScores;
    private ArrayList<Challenge> challenges;
    private String firstName;
    private String lastName;
    
    /**
     * Default constructor.
     */
    public GameManager() { 
        parser = new Parser();
    }

    public void begin() {

        // Get the users name.

        // Get the challenges.
        try {
            challenges = populateChallenges(challengeFilesPath);
        } catch (Exception e) {
            System.out.println("Failed to populate challenges.");
            System.out.println("Error: " + e.getMessage());
        }

        // Get the challenge results.
        try {
            ArrayList<String> challengeResultFileContents = FileHelper.readFile(challengeResultsPath);
            challengeResults = parser.parseChallengeResult(challengeResultFileContents, challenges);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Get the high scores.
        try {
            // Get ID's of challenges
            ArrayList<UUID> challengeIds = new ArrayList<UUID>();
            
            for (Challenge challenge : challenges) {
                challengeIds.add(challenge.getId());
            }

            highScores = parser.getHighScores(challengeResults, null, firstName, lastName)
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        

        Display display = new Display();
        display.initialize(highScores);
    }


    // Getters

    /**
     * Get the challenges.
     * 
     * @return The challenges.
     */
    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    /**
     * Get the challenge results.
     * 
     * @return The challenge results.
     */
    public ArrayList<ChallengeResult> getChallengeResults() {
        return challengeResults;
    }

    /**
     * Get the high scores.
     * 
     * @return The high scores.
     */
    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }

    /**
     * Get the parser.
     * 
     * @return The parser.
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Get the challenge files path.
     * 
     * @return The challenge files path.
     */
    public String getChallengeFilesPath() {
        return challengeFilesPath;
    }

    /**
     * Get the challenge results path.
     * 
     * @return The challenge results path.
     */
    public String getChallengeResultsPath() {
        return challengeResultsPath;
    }

    /**
     * Get the first name.
     * 
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name.
     * 
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    // Setters

    /**
     * Set the challenges.
     * 
     * @param challenges The challenges.
     */
    public void setChallenges(ArrayList<Challenge> challenges) {
        this.challenges = challenges;
    }

    /**
     * Set the challenge results.
     * 
     * @param challengeResults The challenge results.
     */
    public void setChallengeResults(ArrayList<ChallengeResult> challengeResults) {
        this.challengeResults = challengeResults;
    }

    /**
     * Set the high scores.
     * 
     * @param highScores The high scores.
     */
    public void setHighScores(ArrayList<HighScore> highScores) {
        this.highScores = highScores;
    }

    /**
     * Set the parser.
     * 
     * @param parser The parser.
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Set the first name.
     * 
     * @param firstName The first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the last name.
     * 
     * @param lastName The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Private methods

    /**
     * Populate the challenges.
     * 
     * @return The challenges.
     */
    public ArrayList<Challenge> populateChallenges(String challengeFilesPath) throws Exception {
        ArrayList<Challenge> challenges = new ArrayList<Challenge>();

        ArrayList<File> challengeFiles = FileHelper.getFilesInDirectory(challengeFilesPath);
        
        for (File challengeFile : challengeFiles) {
            ArrayList<String> challengeFileContents = FileHelper.readFile(challengeFile.getPath());
            Challenge challenge = parser.parseChallenge(challengeFileContents);
            challenges.add(challenge);
        }

        return challenges;
    }

    /**
     * Get the high scores.
     * 
     * @return The high scores.
     */
    
}
