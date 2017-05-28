package com.github.orelgenya.jcip.ch02;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null)                    /** Race conditions here! */
            instance = new ExpensiveObject();
        return instance;
    }

    public static class ExpensiveObject {
        private final double[] data = new double[100000];
    }
}
