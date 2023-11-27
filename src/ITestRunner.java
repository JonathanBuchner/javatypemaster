/**
 * ITestWriter.java
 * 
 * This interface is used to handle tests. As of Nov 2023, it is used by console 
 * test runner to run tests.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

public interface ITestRunner {
    public void printTestResults();
    public void Test(Class<? extends TestClass> testClass);
}