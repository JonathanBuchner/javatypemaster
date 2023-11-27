/**
 * FileReader_tests.java
 * 
 * This class is used to test the FileReader class.
 * 
 * @author Jonathan Buchner Nov 2023.
 */
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper_tests extends TestClass {
    private final String testFileName = Paths.get("", "test.md").toString();

    public FileHelper_tests() {
        super("FileReader Tests");
    }

    /**
     * This method tests that the FileReader can create a file.
     */
    public Test canCreateAndDeleteAFile() {
        TestException test = new TestException();
        test.setDescription("Can create file");

        try {
            FileHelper.createFile(testFileName);
            FileHelper.deleteFile(testFileName);
            test.setTestResult(true);

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);
            
            return test;
        }
    }

    /**
     * This method tests that the FileReader can write to a file.
     */
    public Test canWriteNothingToFile() {
        TestException test = new TestException();
        test.setDescription("Can write nothing to file");

        try {
            FileHelper.createFile(testFileName);
            FileHelper.writeLineToFile(testFileName, "");
            FileHelper.deleteFile(testFileName);
            test.setTestResult(true);

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);
            
            return test;
        }
    }

    /**
     * This method tests that the FileReader can read from a file.
     */
    public Test canReadAndWriteToFile() {
        TestException test = new TestException();
        test.setDescription("Can write and read to file");

        try {
            FileHelper.createFile(testFileName);
            FileHelper.writeLineToFile(testFileName, "test");
            ArrayList<String> fileContents = FileHelper.readFile(testFileName);
            FileHelper.deleteFile(testFileName);
            test.setTestResult(Assert.areEqual("test", fileContents.get(0)));

            return test;
        } catch (Exception e) {
            test.setException(e);
            test.setTestResult(false);
            
            return test;
        }
    }
}
