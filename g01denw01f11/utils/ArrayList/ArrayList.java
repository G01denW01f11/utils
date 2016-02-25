package com.g01denw01f11.utils.ArrayList;

/**
 * Custom dynamic array class
 * @author G01denW01f11
 */
public class ArrayList<T> {
    private Object[] mData;
    private int size;
    private int capacity;

    public ArrayList(int capacity) {
        if (size < 0) {
            throw new IllegalArgumentException("ArrayList must have non-negative size");
        }

        this.capacity = capacity;
        this.mData = new Object[10];
    }

    public ArrayList() {
        this(10);
    }

    public int getSize() { return size; }

    public int getCapacity() { return capacity; }

    public void add(T element) {
        if (size >= capacity) {
            expand();
        }

        mData[size] = element;
        size++;

    }

    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) mData[index];
    }

    private void expand() {
        capacity = (int)(capacity * 1.5);
        Object[] newData = new Object[capacity * 2];
        for (int i = 0; i < size - 1; ++i) {
            newData[i] = mData[i];
        }
        this.mData = newData;
    }
}
