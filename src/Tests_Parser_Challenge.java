/**
 * Parser_tests.java
 * 
 * Contains tests for the ParserHelper class.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Notes: Consider adding tests for testing the challenge text, including:
 * - Empty text
 * - Text with no code
 * - Text with complex code
 * - Text with code that has no closing ```
 * - Text with code that has no opening ```java
 */

import java.util.ArrayList;
import java.util.UUID;

public class Tests_Parser_Challenge extends TestClass {
    public Tests_Parser_Challenge() {
        super("\"Parser Tests - Challenge");
    }

    /**
     * Basic tests that the Parser can parse a challenge.
     */
    public Test canParseAChallenge() {
        TestException test = new TestException();
        test.setDescription("Can parse a challenge");
        String id = "UUID: 123e4567-e89b-12d3-a456-426614174000";
        String name = "# Test Challenge";
        String description = "*This is a test challenge.*";
        String text = "```java";
        String text1 = "This is the text of the challenge.";
        String text2 = "```";
        
        try {
            ArrayList<String> fileContents = new ArrayList<String>();
            fileContents.add(id);
            fileContents.add(name);
            fileContents.add(description);
            fileContents.add(text);
            fileContents.add(text1);
            fileContents.add(text2);

            Parser parser = new Parser();
            Challenge challenge = parser.parseChallenge(fileContents);

            test.setTestResult(challenge.getId().equals(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")));
            test.setAdditionalResult(Assert.areEqual("Test Challenge", challenge.getName()));
            test.setAdditionalResult(Assert.areEqual("This is a test challenge.", challenge.getDescription()));
            test.setAdditionalResult(Assert.areEqual("This is the text of the challenge.\n", challenge.getText()));

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);

            return test;
        }
    }

    /**
     * Basic tests that the Parser can parse a challenge.
     */
    public Test parserPrioritizesFirstMatch() {
        TestException test = new TestException();
        test.setDescription("Parser prioritizes first match");
        String id = "UUID: 123e4567-e89b-12d3-a456-426614174000";
        String name = "# Test Challenge";
        String description = "*This is a test challenge.*";
        String text = "```java";
        String text1 = "This is the text of the challenge.";
        String text2 = "```";
        

        try {
            ArrayList<String> fileContents = new ArrayList<String>();
            fileContents.add(id);
            fileContents.add(name);
            fileContents.add(description);
            fileContents.add(text);
            fileContents.add(text1);
            fileContents.add(text2);
            fileContents.add(id + " JUNK");
            fileContents.add(name + " JUNK");
            fileContents.add(description + " JUNK");
            fileContents.add(text + " JUNK");


            Parser parser = new Parser();
            Challenge challenge = parser.parseChallenge(fileContents);

            test.setTestResult(challenge.getId().equals(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")));
            test.setAdditionalResult(Assert.areEqual("Test Challenge", challenge.getName()));
            test.setAdditionalResult(Assert.areEqual("This is a test challenge.", challenge.getDescription()));
            test.setAdditionalResult(Assert.areEqual("This is the text of the challenge.\n", challenge.getText()));

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);

            return test;
        }
    }

    /**
     * Basic tests that the parser does not parse bad data.  All the inputs have mistakes.
     */
    public Test parserParsesCorrectly() {
        TestException test = new TestException();
        test.setDescription("Parser parses correctly.  Exception expected.");
        String id = "UUID 123e4567-e89b-12d3-a456-426614174000";
        String name = "#Test Challenge";
        String description = "*This is a test challenge.";
        String text = "```java";
        String text1 = "This is the text of the challenge.";
        String text2 = "```";

        try {
            ArrayList<String> fileContents = new ArrayList<String>();
            fileContents.add(id);
            fileContents.add(name);
            fileContents.add(description);
            fileContents.add(text);
            fileContents.add(text1);
            fileContents.add(text2);


            Parser parser = new Parser();
            Challenge challenge = parser.parseChallenge(fileContents);

            // Note that the each of the below tests has been negated because a 
            test.setTestResult(!challenge.getId().equals(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")));
            test.setAdditionalResult(Assert.areNotEqual("Test Challenge", challenge.getName()));
            test.setAdditionalResult(Assert.areNotEqual("This is a test challenge.", challenge.getDescription()));
            test.setAdditionalResult(Assert.areNotEqual("This is the text of the challenge.", challenge.getText()));
            test.setTestResult(!challenge.isValid());

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(e instanceof IllegalArgumentException); // This is the expected exception.

            return test;
        }
    }

    /**
     * Bad UUID will raise an exception.
     * 
     * Name, description, and text need to be correct for test to work correctly.
     */
    public Test badUuidRaisesException() {
        TestException test = new TestException();
        test.setDescription("Parser raises exception if bad UUID.  Exception expected.");
        String id = "UUID: 123e4567-e89b-12d3-a456-426614174000~"; // Bad UUID. Invalid character at the end.
        String name = "# Test Challenge";
        String description = "*This is a test challenge.*";
        String text = "```java";
        String text1 = "This is the text of the challenge.";
        String text2 = "```";

        ArrayList<String> fileContents = new ArrayList<String>();
        fileContents.add(id);
        fileContents.add(name);
        fileContents.add(description);
        fileContents.add(text);
        fileContents.add(text1);
        fileContents.add(text2);

        try {
            Parser parser = new Parser();
            parser.parseChallenge(fileContents);  // Should raise exception.
            test.setTestResult(false); // If we get here, the test failed.  Exception expected.

            return test; 
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(e instanceof NumberFormatException); // This is the expected exception.

            return test;
        }
    }
}
