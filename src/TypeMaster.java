/**
 * TypeMaster.java
 * 
 * This is entry point of the program.  This class will create the main window and 
 * handle the state of the program.
 */

public class TypeMaster {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.begin();
    }
}
