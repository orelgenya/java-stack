package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

/**
 * O(n), testing each bit.
 * */
public class BruteForceParitySolver implements IParitySolver {

    @Override
    public Result parityOf(long input) {
        byte parity = 0;
        int opsCount = 0;

        while (input != 0 && opsCount < MAX_OPS_COUNT) {
            opsCount++;

            parity ^= input & 1;
            input >>>= 1;
        }

        return new Result(opsCount, parity == 0);
    }

}
