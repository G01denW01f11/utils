package com.g01denw01f11.utils.myunit;

/**
 * @author G01denW01f11
 */
public class TestCase {

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
}
