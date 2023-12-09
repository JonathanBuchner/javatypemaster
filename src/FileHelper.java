/**
 * FileHelper.java
 * 
 * This class is used to read and write to the file system.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Note: I borrowed from my own work in CaesarCipher.java and ExamAnalysis.java.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public final class FileHelper {

    /**
     * Get files in directory.
     */
    public static ArrayList<File> getFilesInDirectory(String directoryPath) throws Exception {
        ArrayList<File> files = new ArrayList<File>();
        File directory = validateFilePath(directoryPath);

        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory " + directoryPath + " does not exist.");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directory " + directoryPath + " is not a directory.");
        }

        if (!directory.canRead()) {
            throw new IllegalArgumentException("Directory " + directoryPath + " cannot be read.");
        }

        for (File file : directory.listFiles()) {
            files.add(file);
        }

        return files;
    }

    /**
     * Creates a file.
     * 
     * @param filePath The file name.
     * @return The file.
     * @throws IllegalArgumentException If the file already exists.
     * @throws IOException If the file cannot be created.
     */
    public static File createFile(String filePath) throws Exception {
        
        File file = validateFilePath(filePath);
        validateFileDoesNotExist(file);
        
        file.createNewFile();

        return file;
    }

    /**
     * Write line to file.
     * 
     * @param filePath The file path.
     */
    public static void writeLineToFile(String filePath, String line) throws Exception {
        File file = getFile(filePath);
        PrintWriter writer = new PrintWriter(file);
        
        writer.println(line);
        
        writer.close();
    }

    /**
     * Reads a file.
     * 
     * @param filePath The file path.
     * @return The file contents.
     */
    public static ArrayList<String> readFile(String filePath) throws Exception {
        File file = getFile(filePath);
        
        return readFile(file);
    }

    /**
     * Read a file.
     * 
     * @param file The file.
     * 
     * @return The file contents.
     */
    public static ArrayList<String> readFile(File file) throws Exception {
        ArrayList<String> fileContents = new ArrayList<String>();
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNextLine()) {
            fileContents.add(inputFile.nextLine());
        }

        inputFile.close();
        
        return fileContents;
    }

    /**
     * Deletes a file.
     * 
     * @param filePath
     * @return
     */
    public static void deleteFile(String filePath) {
        File file = validateFilePath(filePath);
        
        file.delete();
    }

    /**
     * Gets a file from a file path.
     * 
     * @param filePath The file path.
     * @return The file.
     * @throws IllegalArgumentException If the file path is null, empty, does not exist, is not a file, or cannot be read.
     */
    public static File getFile(String filePath) {
        File file =  validateFilePath(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File path " + filePath + " does not exist.");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("File path " + filePath + " is not a file.");
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException("File path " + filePath + " cannot be read.");
        }

        return file;
    }

    private static File validateFilePath(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            throw new IllegalArgumentException("Must provide file name.");
        }

        Paths.get(filePath);

        return new File(filePath);
    }

    private static void validateFileDoesNotExist(File file) {
        if (file.exists()) {
            throw new IllegalArgumentException("File already exists.");
        }
    }
}
