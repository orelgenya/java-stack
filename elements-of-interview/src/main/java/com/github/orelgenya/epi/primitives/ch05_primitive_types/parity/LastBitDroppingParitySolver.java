package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

/**
 * O(k), k - number bits set. Clears set bits from the end.
 */
public class LastBitDroppingParitySolver implements IParitySolver {

    @Override
    public Result parityOf(long input) {
        long temp = input;
        int opsCount = 0;
        byte parity = 0;

        while (temp != 0 && opsCount < MAX_OPS_COUNT) {
            opsCount++;

            parity ^= 1;
            temp &= temp - 1;
        }

        return new Result(opsCount, parity == 0);
    }
}
