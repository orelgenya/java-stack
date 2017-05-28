package com.github.orelgenya.jcip.ch01;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class UnsafeSequence implements Sequence {
    private int value;

    @Override
    /** Returns a unique value. */
    public int nextValue() {
        return value++;
    }

    @Override
    public int getValue() {
        return value;
    }
}
