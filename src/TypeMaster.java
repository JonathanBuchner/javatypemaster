/**
 * TypeMaster.java
 * 
 * This is entry point of the program.  
 */

public class TypeMaster {
    public static void main(String[] args) {
        try {
            GameManager gameManager = new GameManager();
            gameManager.begin();
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Unexpected game exception");
        }
    }
}
