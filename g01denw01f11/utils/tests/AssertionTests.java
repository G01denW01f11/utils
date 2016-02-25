package com.g01denw01f11.utils.tests;

import com.g01denw01f11.utils.myunit.TestCase;

/**
 * @author G01denW01f11
 */
@SuppressWarnings("unused")
public class AssertionTests extends TestCase {

    private String testString;

    public void setUp() {
        testString = "Initialized";
    }

    public void testAssertTrue() {
        assertTrue(true);
    }

    public void testAssertTrueThrowsOnFalse(AssertionError e) {
        assertTrue(false);
    }

    public void testAssertFalse() {
        assertFalse(false);
    }

    public void testAssertThrows(NullPointerException e) {
        throw new NullPointerException("Test method");
    }

    public void testAssertEqual() {
        assertEqual(1, 1);
    }

    public void testAssertEqualThrowsWhenUnequal(AssertionError e) {
        assertEqual(1, 2);
    }

    public void testAssertNotEqual() {
        assertNotEqual("foo", "bar");
    }

    public void testAssertNotEqualThrowsWhenEqual(AssertionError e) {
        assertNotEqual("Foo", "Foo");
    }

    public void testSetupCalled() {
        assertEqual(testString, "Initialized");
    }
}
