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

    public void testAssertFalse() {
        assertFalse(false);
    }

    public void testAssertEqual() {
        assertEqual(1, 1);
    }

    public void testAssertNotEqual() {
        assertNotEqual("foo", "bar");
    }

    public void testLessThan() {
        assertLessThan("bar", "foo");
    }

    public void testSetupCalled() {
        assertEqual(testString, "Initialized");
    }
}
