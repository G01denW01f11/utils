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

    public void runTests() throws InvocationTargetException {
        //I can't imagine that runTests() will be called more than once during a single
        //execution of the program, but just to be safe...
        failedTests.clear();
        for (TestCase test: tests) {
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

    private void runTest(TestCase test) throws InvocationTargetException {
        try {
            test.getClass().getMethod("setUp").invoke(test);
        } catch (IllegalAccessException e) {
            throw new AssertionError("Test's setUp method must be public");
        } catch (NoSuchMethodException e) {
            //Not all com.g01denw01f11.utils.tests need to have a setUp() method
            //so just don't do anything
        }

        for (Method method : test.getClass().getMethods()) {
            if (method.getName().startsWith("test")) {
                if (method.getName().contains("Throws")) {
                    @SuppressWarnings("unchecked")
                    Class<Throwable> err = (Class<Throwable>)method.getParameterTypes()[0];
                    try {
                        method.invoke(method.getDeclaringClass().newInstance(), err.newInstance());
                    } catch (InvocationTargetException e) {
                        //noinspection StatementWithEmptyBody
                        if (e.getTargetException().getClass().equals(err)) {
                            //test passes
                        } else {
                            failedTests.put(new AssertionError(err.getSimpleName() + " was not thrown"), method);
                        }
                    } catch (Exception e) {
                        failedTests.put(new AssertionError(e.getClass().getSimpleName() + " thrown."), method);
                    }
                } else {

                    try {
                        method.invoke(test);
                    } catch (InvocationTargetException e) {
                        if (e.getTargetException() instanceof AssertionError) {
                            failedTests.put((AssertionError) e.getTargetException(), method);
                        } else {
                         failedTests.put(new AssertionError(e.getClass().getSimpleName() + " thrown."), method);
                        }
                    } catch (IllegalAccessException e) {
                        //this should just give the user a sane message about where he tried
                        //to use something private?
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void printResults() {
        int numFailed = testMethods.size() - failedTests.size();
        System.out.printf("Passed %d com.g01denw01f11.utils.tests of %d\n", numFailed, testMethods.size());
        for (AssertionError e : failedTests.keySet()) {
            System.out.printf("Failed test %s: %s", failedTests.get(e).getName(), e.getMessage());
        }
    }

    private List<TestCase> tests = new LinkedList<>();
    private List<Method> testMethods = new LinkedList<>();
    private Map<AssertionError, Method> failedTests = new HashMap<>();

}
