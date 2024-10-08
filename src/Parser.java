/**
 * Parser.java
 * 
 * Contains methods for parsing the data file or the challenge files.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Notes: 
 * 
 * Originally, I thought I would be using regex expressions, but it
 * made if more complicated so I used the simple matching schema. E.g. Original approach
 * included using Scanner and regex, but String.split() and "".startsWith were
 * simpler instead.
 * 
 * I used chat gpt to get the date parsing to work.
 */

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public final class Parser {
    /**
     * Parse a challenge.
     * 
     * @param fileContents The contents of a challenge file.
     */
    public Challenge parseChallenge(ArrayList<String> fileContents) {
        Challenge challenge = new Challenge();
        
        for (String line : fileContents) {
            parsedChallengeLine(line, challenge);
        }

        // Set the challenge text
        int i = 0;
        while(!fileContents.get(i).startsWith("```java")) {
            i++;
        }

        challenge.setText("");
        i++;

        while(!fileContents.get(i).endsWith("```")) {
            challenge.setText(challenge.getText() + fileContents.get(i) + "\n");
            
            if (i >= fileContents.size() - 1) {
                throw new IllegalArgumentException("The challenge file is not valid.");
            }

            i++;
        }

        if (!challenge.isValid()) {
            throw new IllegalArgumentException("The challenge file is not valid.");
        }

        return challenge;
    }

    /**
     * Parse a challenge result.
     * 
     * @param fileContents The contents of the challenge result file.
     */
    public ArrayList<ChallengeResult> parseChallengeResult(ArrayList<String> fileContents, ArrayList<Challenge> challenges) {
         ArrayList<ChallengeResult> challengeResults = new  ArrayList<ChallengeResult>();
        
        for (String line : fileContents) {
            ChallengeResult challengeResult = getChallengeResult(line, challenges);
            challengeResults.add(challengeResult);
        }

        return challengeResults;
    }
    
    /**
     * Set the high scores for each challenge.
     * 
     * This method went through several iterations. Initially, highscores were a 
     * not a property of a challenge but rather passed around as there own 
     * array list. I realized that this was not a good approach as I was
     * having to match highscore id challenge ids.
     * 
     * An simpler approach to the below method would have been a loop inside a loop
     * to find the high score for each challenge. However, I changed it back to using
     * a hashmap to make it more efficient.
     * 
     * This method probably could be moved out of the parser class and into the
     * challenge class, but it also works living in this parser class for this program.
     * 
     * @param challengeResults The challenge results.
     * @param challenges The challenges.
     * @param firstName The first name.
     * @param lastName The last name.
     */
    public void setHighScores(ArrayList<ChallengeResult> challengeResults, ArrayList<Challenge> challenges, String firstName, String lastName) {
        HashMap<UUID,HighScore> highScores = new HashMap<UUID,HighScore>();

        // Get a high object for each challenge that is is put in a hashmap.
        for (Challenge challenge : challenges) {
            highScores.put(challenge.getId(), new HighScore(challenge.getId(), challenge.getName(), "*NEW*", 0, firstName, lastName, 0));
        }

        // Update the high scores with information from the challenge results.
        for (ChallengeResult challengeResult : challengeResults) {
            UUID challengeId = challengeResult.getChallenge().getId();
            HighScore highScore = highScores.get(challengeId);
            
            if (challengeResult.getSecondsToComplete() < highScore.getHighScore() || highScore.getHighScore() == 0) {
                highScore.setHighScore(challengeResult.getSecondsToComplete());
                highScore.setHighFirstName(challengeResult.getFirstName());
            }

            if (challengeResult.getFirstName().equals(firstName) && challengeResult.getLastName().equals(lastName)) {
                if (challengeResult.getSecondsToComplete() < highScore.getYourHighScore() || highScore.getYourHighScore() == 0) {
                    highScore.setYourHighScore(challengeResult.getSecondsToComplete());
                }
            }
        }

        // Put the high score into a challenge.
        for (Challenge challenge : challenges) {
            challenge.setHighScore(highScores.get(challenge.getId()));
        }
    }

    // Private methods

    /**
     * Gets a challenge result from a line of file contents.
     * 
     * @param line The line to parse.
     * @return The challenge result.
     */
    private ChallengeResult getChallengeResult(String line, ArrayList<Challenge> challenges) {
        ChallengeResult challengeResult = new ChallengeResult();
        String[] fields = line.split(ChallengeResult.getDeliminator());
        
        if (fields.length != 6) {
            throw new IllegalArgumentException("The row does not have the right number of entries.");
        }

        // Parse the fields
        
        // UUID
        challengeResult.setId(UUID.fromString(fields[0]));
        
        // Challenge
        Challenge challenge = getChallenge(UUID.fromString(fields[1]), challenges);
        challengeResult.setChallenge(challenge);
        
        // Date completed. I hade help from chat gpt to get this to work.
        String dateString = fields[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateCompleted = LocalDateTime.parse(dateString, formatter);
        challengeResult.setDateCompleted(dateCompleted);

        // First name, last name, seconds to complete
        challengeResult.setFirstName(fields[3]);
        challengeResult.setLastName(fields[4]);
        challengeResult.setSecondsToComplete(Integer.parseInt(fields[5]));

        return challengeResult;
    }

    private Challenge getChallenge(UUID challengeId, ArrayList<Challenge> challenges) {
        for (Challenge challenge : challenges) {
            if (challenge.getId().equals(challengeId)) {
                return challenge;
            }
        }

        throw new IllegalArgumentException("The challenge id does not exist.");
    }

    /**
     * Parses a line of file contents.
     * 
     * @param line The line to parse.
     * @param challenge The challenge to add the data to.
     */
    private void parsedChallengeLine(String line, Challenge challenge) {
        if (line.startsWith("UUID: ") && !challenge.idIsSet()) {
            
            // Parse the UUID
                challenge.setId(UUID.fromString(line.substring(6).trim()));
            
        } else if (line.startsWith("# ") && !challenge.nameIsSet()) {
            
            // Parse the name
             challenge.setName(line.substring(2).trim());

        } else if (line.startsWith("*") && line.endsWith("*") && !challenge.descriptionIsSet()) {
            
            // Parse the description
            challenge.setDescription(line.substring(1, line.length() - 1).trim());

        } 
        // Other lines are ignored
    }
}
