/**
 * Assert.java
 * 
 * This class is used to test equality between expected and actual response for 
 * test classes.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Note: This class was probably not necessary in hindsight, but I expected to
 * use this throughout all my test classes.
 */

public final class Assert {
    /**
     * This method tests equality between two strings.
     * 
     * @param expected The expected response.
     * @param actual The actual response.      
     * @return True if the expected and actual responses are equal, false 
     *         otherwise.
     */
    public static boolean areEqual(String expected, String actual) {
        return expected.equals(actual);
    }

    /**
     * This method tests equality between two integers.
     * 
     * @param expected The expected response.
     * @param actual The actual response.      
     * @return True if the expected and actual responses are equal, false 
     *         otherwise.
     */
    public static boolean areEqual(int expected, int actual) {
        return expected == actual;
    }

    /**
     * This method tests that two strings.are not equal.
     * 
     * @param expected The expected response.
     * @param actual The actual response.      
     * @return True if the expected and actual responses are equal, false 
     *         otherwise.
     */
    public static boolean areNotEqual(String expected, String actual) {
        return !expected.equals(actual);
    }
}
