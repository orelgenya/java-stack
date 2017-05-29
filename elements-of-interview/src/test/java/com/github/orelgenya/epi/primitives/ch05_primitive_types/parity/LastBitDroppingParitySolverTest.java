package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

import org.junit.Test;

public class LastBitDroppingParitySolverTest extends BaseParitySolverTest {

    public LastBitDroppingParitySolverTest(long input, boolean expectedParity) {
        super(input, expectedParity);
    }

    @Test
    public void parityOf() throws Exception {
        test(new LastBitDroppingParitySolver());
    }

}