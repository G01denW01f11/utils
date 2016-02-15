package com.g01denw01f11.utils.myunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author G01den@W01f11
 */
public class TestRunner {

    private List<TestCase> tests = new LinkedList<>();
    private List<Method> testMethods = new LinkedList<>();
    private Map<AssertionError, Method> failedTests = new HashMap<>();

    //For color output
    //This apparently doesn't work on Windows, but I don't care
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    //TODO: This looks yellow?
    public static final String ANSI_GREEN = "\u001B[32m";

    public void runTests() throws InvocationTargetException {
        //I can't imagine that runTests() will be called more than once during a single
        //execution of the program, but just to be safe...
        failedTests.clear();
        for (TestCase test : tests) {
            runTest(test);
        }
        printResults();
    }

    public void addTest(TestCase testCase) {
        tests.add(testCase);
        for (Method method : testCase.getClass().getMethods()) {
            if (method.getName().startsWith("test")) {
                testMethods.add(method);
            }
        }
    }

    private void setUp(TestCase test) throws InvocationTargetException {
        try {
            test.getClass().getMethod("setUp").invoke(test);
        } catch (IllegalAccessException e) {
            throw new AssertionError("Test's setUp method must be public");
        } catch (NoSuchMethodException e) {
            //Not all com.g01denw01f11.utils.tests need to have a setUp() method
            //so just don't do anything
        }
    }

    private void runTest(TestCase test) throws InvocationTargetException {
        //This method can throw if the TestCase's setUp() method throws something
        //If this happens, the tests will be meaningless, so we'll just
        //throw everything back to the caller
        setUp(test);

        for (Method method : testMethods) {
            if (method.getName().contains("Throws")) {
                @SuppressWarnings("unchecked")
                Class<Throwable> err = (Class<Throwable>) method.getParameterTypes()[0];
                try {
                    method.invoke(method.getDeclaringClass().newInstance(), err.newInstance());
                } catch (InvocationTargetException e) {
                    //noinspection StatementWithEmptyBody
                    if (!e.getTargetException().getClass().equals(err)) {
                        failedTests.put(new AssertionError(err.getSimpleName() + " was not thrown"), method);
                    }
                } catch (Exception e) {
                    failedTests.put(new AssertionError(e.getMessage()), method);
                }
            } else {
                try {
                    method.invoke(test);
                } catch (InvocationTargetException e) {
                    if (e.getTargetException() instanceof AssertionError) {
                        failedTests.put((AssertionError) e.getTargetException(), method);
                    } else {
                        failedTests.put(new AssertionError(e.getTargetException().toString()), method);
                    }
                } catch (IllegalAccessException e) {
                    //this should just give the user a sane message about where he tried
                    //to use something private?
                    failedTests.put(new AssertionError(e.toString()), method);
                }
            }
        }
    }

    private void printRed(String message) {
        System.out.printf("%s%s%s", ANSI_RED, message, ANSI_RESET);
    }

    private void printGreen(String message) {
        System.out.printf("%s%s%s", ANSI_GREEN, message, ANSI_RESET);
    }
    private void printResults() {
        int numFailed = testMethods.size() - failedTests.size();
        printGreen(String.format("Passed %d tests of %d\n", numFailed, testMethods.size()));
        for (AssertionError e : failedTests.keySet()) {
            printRed(String.format("Failed test %s: %s\n", failedTests.get(e).getName(), e.getMessage()));
        }
    }


}
