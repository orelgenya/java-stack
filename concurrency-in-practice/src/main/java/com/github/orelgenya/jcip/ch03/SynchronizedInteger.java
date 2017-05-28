package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.GuardedBy;
import com.github.orelgenya.jcip.annotations.ThreadSafe;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this")
    private int value;

    public synchronized int get() { return value; }
    public synchronized void set(int value) { this.value = value; }
}
