package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

import org.junit.Test;

public class BruteForceParitySolverTest extends BaseParitySolverTest {

    public BruteForceParitySolverTest(long input, boolean expectedParity) {
        super(input, expectedParity);
    }

    @Test
    public void test() {
        test(new BruteForceParitySolver());
    }
}