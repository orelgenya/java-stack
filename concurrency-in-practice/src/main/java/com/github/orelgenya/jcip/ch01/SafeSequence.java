package com.github.orelgenya.jcip.ch01;

import com.github.orelgenya.jcip.annotations.GuardedBy;
import com.github.orelgenya.jcip.annotations.ThreadSafe;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class SafeSequence implements Sequence {
    @GuardedBy("this")
    private int nextValue;

    @Override
    public synchronized int nextValue() {
        return nextValue++;
    }

    @Override
    public int getValue() {
        return nextValue;
    }
}
