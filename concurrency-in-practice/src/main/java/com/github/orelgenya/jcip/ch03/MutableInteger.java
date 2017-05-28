package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    private int get() { return value; }
    public void set(int value) { this.value = value; }
}
