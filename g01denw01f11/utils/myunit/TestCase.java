package com.g01denw01f11.utils.myunit;

import com.g01denw01f11.utils.ArrayList.ArrayList;

import java.lang.reflect.Method;

/**
 * @author G01denW01f11
 */
public abstract class TestCase {

    public abstract void setUp();

    public ArrayList<Method> getTests() {
        ArrayList<Method> tests = new ArrayList<>();
        for (Method method : this.getClass().getMethods()) {
            if (method.getName().startsWith("test")) {
                tests.add(method);
            }
        }
        return tests;
    }

    public void assertTrue(boolean condition) {
        assertTrue(condition, null);
    }

    public void assertTrue(boolean condition, String message) {
        if (message == null) {
            message = "False is not True";
        }

        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public void assertFalse(boolean condition) {
        assertFalse(condition, null);
    }

    public void assertFalse(boolean condition, String message) {
        if (message == null) {
            message = "True is not False";
        }

        if (condition) {
            throw new AssertionError(message);
        }
    }

    public void assertEqual(Object o1, Object o2) {
        assertEqual(o1, o2, null);
    }

    public void assertEqual(Object o1, Object o2, String message) {
        if (message == null) {
            message = o1.toString() + " is not equal to " + o2.toString();
        }

        if (!o1.equals(o2)) {
            throw new AssertionError(message);
        }
    }

    public void assertNotEqual(Object o1, Object o2){
        assertNotEqual(o1, o2, null);
    }

    public void assertNotEqual(Object o1, Object o2, String message) {
        if (message == null) {
            message = o1.toString() + " is equal to  " + o2.toString();
        }

        if (o1.equals(o2)) {
            throw new AssertionError(message);
        }
    }

    public <T extends Comparable<T>> void assertGreaterThan(T o1, T o2) {
        assertGreaterThan(o1, o2, null);
    }

    public <T extends Comparable<T>> void assertGreaterThan(T o1, T o2, String message) {
        if (message == null) {
            message = o1.toString() + " <= " + o2.toString();
        }

        if (o1.compareTo(o2) <= 0) {
            throw new AssertionError(message);
        }
    }

    public <T extends Comparable<T>> void assertLessThan(T o1, T o2) {
        assertLessThan(o1, o2, null);
    }

    public <T extends Comparable<T>> void assertLessThan(T o1, T o2, String message) {
        if (message == null) {
            message = o1.toString() + " >= " + o2.toString();
        }

        if (o1.compareTo(o2) >= 0) {
            throw new AssertionError(message);
        }
    }
}
