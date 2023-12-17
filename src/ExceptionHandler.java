/**
 * ExceptionHandler.java
 * 
 * This class handles exceptions and alerts the user of an error.
 *
 * @author Jonathan Buchner Dec 2023.
 */

import javax.swing.JOptionPane;

public final class ExceptionHandler {
    public static void handleFatelExceptions(Exception exception, String message) {
        message = message + "\n\nContact game administrator if this problem continues. Exiting program.";
        JOptionPane.showMessageDialog(null, message , "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(exception.getMessage());
        System.out.println(exception.getStackTrace());
        
        System.exit(0);
    }
}
