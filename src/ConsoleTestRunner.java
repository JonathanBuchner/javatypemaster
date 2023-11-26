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
 * not figure out how to pass a child class of TestClass.
 */

import java.lang.reflect.Method;

public final class ConsoleTestRunner implements ITestRunner {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    /**
     * This method runs the tests.
     */
    public void Test(Class<? extends TestClass> testClass) {
        try {
            // Create an instance of the test class.
            TestClass instance = instantiateTestClass(testClass);

            // Loop through the methods of the test class and if the return type
            // is Test and no parameters then we want to run the test.
            for (Method method : testClass.getDeclaredMethods()) {
                if (method.getReturnType().equals(Test.class) && method.getParameterCount() == 0) {
                    
                    // Invoke the method / run the test.
                    Test<?> test = (Test<?>) method.invoke(instance);
                    
                    // Print the result of the test.
                    printTestResult(test); 
                }
            }
        } catch (Exception e) {
            printException(e);
        }
    }

    // Private methods

    private TestClass instantiateTestClass(Class<? extends TestClass> testClass) throws Exception {
        
        TestClass instance = testClass.getDeclaredConstructor().newInstance();

        System.out.println("\n\u001B[33m");
        System.out.println("--------------------------------------------------");
        System.out.println("Running test class '" + testClass.getName() + "'");
        System.out.println(instance.getDescription());
        System.out.println("--------------------------------------------------");
        System.out.println("\u001B[33m");

        return instance;
    }

    /**
     * This method prints the result of the test.
     * 
     * @param test The test to print.
     */
    private void printTestResult(Test<?> test) {
        if (test.getTestResult()) {
            System.out.println(ANSI_GREEN + "Passed: " + test.toString() + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Failed: " + test.toString() + ANSI_RESET);
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
