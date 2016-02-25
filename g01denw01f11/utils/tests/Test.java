package com.g01denw01f11.utils.tests;

import com.g01denw01f11.utils.myunit.TestRunner;

import java.lang.reflect.InvocationTargetException;

/**
 * Contain com.g01denw01f11.utils.tests for MyUnit
 * @author G01denW01f11
 */
public class Test {
     public static void main(String args[]){
         TestRunner runner = new TestRunner();
         runner.addTest(new AssertionTests());
         runner.addTest(new ArrayListTests());
         try {
             runner.runTests();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         }
     }

}
