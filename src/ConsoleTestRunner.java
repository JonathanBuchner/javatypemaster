/**
 * TestWriter.java
 * 
 * This class is used write test to the console. I wanted to design a test runner
 * that would find all my test methods for a class and run them, so I explored
 * using reflection to look up the methods that had a test return type and 
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Note: A better approach may have been to use a test framework like JUnit, but
 * we didn't learn about the framework in class and this allowed me to demonstrate
 * my understanding of reflection.
 * 
 * I used chatgpt to help me build this class, specifically identifying the 
 * method signature public void Test(Class<? extends TestClass> testClass). I could
 * not figure out how to pass a child class of TestClass. Code below is not
 * an enterprise testing approach but works for my needs.
 */

import java.lang.reflect.Method;

public final class ConsoleTestRunner implements ITestRunner {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private int testPassedCount = 0;
    private int testFailedCount = 0;
    
    // Public static methods

    /**
     * This method prints the test results.
     */
    public void printTestResults() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Test results:");
        System.out.println("--------------------------------------------------\n");
        System.out.println(ANSI_GREEN + "Passed: " + testPassedCount + ANSI_RESET);
        System.out.println(ANSI_RED + "Failed: " + testFailedCount + " (One fail is expected)" + ANSI_RESET);
    }
    
    // Public methods

    /**
     * This method runs the tests.
     * 
     * @param testClass The test class to run.
     */
    public void Test(Class<? extends TestClass> testClass) {
        try {
            // Create an instance of the test class and print that we are .
            TestClass instance = testClass.getDeclaredConstructor().newInstance();

            // Print the starting test message.
            printStartingTest(testClass.getSimpleName(), instance.getDescription());

            // Loop through the methods of the test class and if the return type
            // is Test and no parameters then we want to invoke the test method.
            for (Method method : testClass.getDeclaredMethods()) {
                if (idValidTestMethod(method)) {
                    
                    // Invoke the method / run the test.
                    Test test = (Test) method.invoke(instance);
                    
                    // Print the result of the test.
                    printTestResult(test); 
                }
            }
        } catch (Exception e) {
            printException(e);
        }
    }

    // Private methods

    /**
     * This method prints the starting test message.
     * 
     * @param name The name of the test class.
     * @param description The description of the test class.
     */
    private void printStartingTest(String name, String description) {
        System.out.println(ANSI_YELLOW);
        System.out.println("--------------------------------------------------");
        System.out.println("Running test class '" + name + "'");
        System.out.println(description);
        System.out.println("--------------------------------------------------");
        System.out.println(ANSI_RESET);
    }

    /**
     * This method identifies if the method is a valid test method.
     * 
     * @param method The method to check.
     * @return True if the method is a valid test method, false otherwise.
     */
    private boolean idValidTestMethod(Method method) {
        boolean isValidTestMethod = 
            method.getReturnType().equals(Test.class) ||
            method.getParameterCount() == 0;

        return isValidTestMethod;
    }

    /**
     * This method prints the result of the test.
     * 
     * @param test The test to print.
     */
    private void printTestResult(Test test) {
        if (test.getTestResult()) {
            testPassedCount++;
            System.out.println(ANSI_GREEN + "Passed " + test.toString() + ANSI_RESET);
        } else {
            testFailedCount++;
            System.out.println(ANSI_RED + "Failed " + test.toString() + ANSI_RESET);
        }
    }

    /**
     * This method prints the exception.
     * 
     * @param e The exception to print.
     */
    private void printException(Exception e) {
        System.out.println(ANSI_RED + "An error occurred while running the tests." + ANSI_RESET);
        System.err.println(e.getMessage());
    }
}
