/**
 * Tests_Parser_ChallengeResult.java
 * 
 * Contains tests for the ParserHelper class.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.util.ArrayList;
import java.util.UUID;

public class Tests_Parser_ChallengeResult extends TestClass {
    public ArrayList<Challenge> challenges = new ArrayList<Challenge>();

    public Tests_Parser_ChallengeResult() {
        super("Parser Tests - ChallengeResult");

        // Create challenges for testing.
        Challenge challenge1 = new Challenge();
        challenge1.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        challenge1.setName("Test Challenge 1");
        challenge1.setDescription("This is a test challenge 1.");
        challenge1.setText("This is the text of the challenge 1.");

        Challenge challenge2 = new Challenge();
        challenge2.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        challenge2.setName("Test Challenge 2");
        challenge2.setDescription("This is a test challenge 2.");
        challenge2.setText("This is the text of the challenge 2.");

        Challenge challenge3 = new Challenge();
        challenge3.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));
        challenge3.setName("Test Challenge 3");
        challenge3.setDescription("This is a test challenge 3.");
        challenge3.setText("This is the text of the challenge 3.");

        challenges.add(challenge1);
        challenges.add(challenge2);
        challenges.add(challenge3);
    }

    /**
     * Basic tests that the Parser can parse a challenge result.
     */
    public Test canParseAChallengeResult() {
        TestException test = new TestException();
        test.setDescription("Can parse a challenge result");

        // Setting up challenge result text.
        String id = "123e4567-e89b-12d3-a456-426614174010";
        String challenge = "123e4567-e89b-12d3-a456-426614174000"; // This is the id of challenge 1.
        String dateCompleted = "2023-12-11T20:44:17.915";
        String firstName = "UserFirstName";
        String lastName = "UserLastName";
        String secondsToComplete = "60";

        ArrayList<String> fileContents = new ArrayList<String>();
        String row = id + ChallengeResult.getDeliminator() + 
            challenge + ChallengeResult.getDeliminator() + 
            dateCompleted + ChallengeResult.getDeliminator() + 
            firstName + ChallengeResult.getDeliminator() + 
            lastName + ChallengeResult.getDeliminator() + 
            secondsToComplete;
        fileContents.add(row);

        try {
            Parser parser = new Parser();
            ArrayList<ChallengeResult> challengeResults = parser.parseChallengeResult(fileContents, challenges);
            ChallengeResult challengeResult = challengeResults.get(0);

            test.setTestResult(challengeResult.getId().equals(UUID.fromString(id)));
            // This test is checking that the challenge id is the same as the id of the challenge result.
            test.setAdditionalResult(Assert.areEqual(challenges.get(0).getId().toString(), challengeResult.getChallenge().getId().toString()));
            test.setAdditionalResult(Assert.areEqual("UserFirstName", challengeResult.getFirstName()));
            test.setAdditionalResult(Assert.areEqual("UserLastName", challengeResult.getLastName()));
            test.setAdditionalResult(Assert.areEqual(60, challengeResult.getSecondsToComplete()));

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);

            return test;
        }
    }
}
