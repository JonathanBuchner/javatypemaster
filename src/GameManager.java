/**
 * GameManager.java
 * 
 * This class will manage the game.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameManager {
    private static final String challengeFilesPath = "../data/challenges/";
    private static final String challengeResultsPath = "../data/data.csv";

    private Parser parser;
    private MusicPlayer musicPlayer;
    private Display display;
    private ArrayList<ChallengeResult> challengeResults;
    private ArrayList<Challenge> challenges;
    private String firstName;
    private String lastName;
    
    /**
     * Default constructor.
     */
    public GameManager() { 
        try {
            parser = new Parser();
            musicPlayer = new MusicPlayer();
            display = new Display();
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Failed to initialize game.  Contact game administrator.");
        }
    }

    /**
     * Begin the game.
     */
    public void begin() {

        // Introduce the game
        int agreement = display.introduceGame();

        // Close game if user does not agree to terms.
        if (agreement != JOptionPane.YES_OPTION) {
            display.informMustAgree();
            System.exit(0);
        }

        // Get the user's name.
        firstName = playerNameFormatter(display.getFirstName());
        lastName = playerNameFormatter(display.getLastName());

        // Close game if user does not enter a name.
        if (firstName.isEmpty() || lastName.isEmpty() || firstName.length() < 2 || lastName.length() < 2) {
            display.informMustSupplyName();
            System.exit(0);
        }

        // Get the challenges.
        try {
            challenges = populateChallenges(challengeFilesPath);

            if (challenges.size() == 0) {
                throw new Exception("No challenges found.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e,"Failed to populate challenges.  There may be a problem with one or more challenge files or the challenge file pathing.");
        }

        // Get the challenge results.
        try {
            ArrayList<String> challengeResultFileContents = FileHelper.readFile(challengeResultsPath);
            challengeResults = parser.parseChallengeResult(challengeResultFileContents, challenges);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e,"Failed to populate challenge results.  There may be a problem with the challenge results file or the challenge results file pathing.");
        }

        // Get the high scores.
        try {
            parser.setHighScores(challengeResults, challenges, firstName, lastName);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e,"Failed to set high scores.  There may be a problem with the challenge or data files.");
        }

        try {
            // Play the intro music.
            musicPlayer.playIntro();

            // Display the main menu.
            display.initialize(challenges, firstName, lastName);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e,"Failed to initialize game.  Contact game administrator.");
        }
    }


    // Getters

    /**
     * Get challenge file path
     * 
     * @return The challenge file path.
     */
    public static String getChallengeFilePath() {
        return challengeFilesPath;
    }

    /*
     * Get challenge results path
     * 
     * @return The challenge results path.
     */
    public static String getChallengeResultsPath() {
        return challengeResultsPath;
    }

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
     * Get the parser.
     * 
     * @return The parser.
     */
    public Parser getParser() {
        return parser;
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
            try { 
                ArrayList<String> challengeFileContents = FileHelper.readFile(challengeFile);
                Challenge challenge = parser.parseChallenge(challengeFileContents);
                challenges.add(challenge);
            }
            catch (Exception e) {
                System.out.println("Failed to load challenge.");
                System.out.println("Error: " + e.getMessage());
            }
        }

        return challenges;
    }

    private String playerNameFormatter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
