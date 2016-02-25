package com.g01denw01f11.utils.tests;

import com.g01denw01f11.utils.ArrayList.ArrayList;
import com.g01denw01f11.utils.myunit.TestCase;

/**
 * @author G01denW01f11
 */
@SuppressWarnings("unused")
public class ArrayListTests extends TestCase {
    private ArrayList<Integer> arrayList;

    @Override
    public void setUp() {
        arrayList = new ArrayList<>();
    }

    public void testInitialCapacity() {
        assertEqual(arrayList.getCapacity(), 10);
    }

    public void testAddingElementsIncreasesSize() {
        arrayList.add(5);
        assertEqual(arrayList.getSize(), 1);
        arrayList.add(15);
        assertEqual(arrayList.getSize(), 2);
    }

    public void testCapacityExpands() {
        for (int i = 0; i < 15; ++i) {
            arrayList.add(i);
        }
        assertEqual(arrayList.getSize(), 15);
        assertGreaterThan(arrayList.getCapacity(), 10);
    }
}
