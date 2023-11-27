/**
 * ParserHelper.java
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
            throw new IllegalArgumentException("The line is not a valid challenge result.");
        }

        // Parse the fields
        
        // UUID
        challengeResult.setId(UUID.fromString(fields[0]));
        
        // Challenge
        Challenge challenge = getChallenge(UUID.fromString(fields[1]), challenges);
        challengeResult.setChallenge(challenge);
        
        // Date completed. I hade help from chat gpt to get this to work.
        String dateString = fields[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

        } else if (line.startsWith("```") && line.endsWith("```") && !challenge.textIsSet()) {
            challenge.setText(line.substring(3, line.length() - 3).trim());
        } 
        // Other lines are ignored
    }
}
