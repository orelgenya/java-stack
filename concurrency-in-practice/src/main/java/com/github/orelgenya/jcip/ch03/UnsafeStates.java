package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class UnsafeStates {
    private String[] states = new String[] {
            "AK", "AL", "..."
    };

    public String[] getStates() { return states; }
}
