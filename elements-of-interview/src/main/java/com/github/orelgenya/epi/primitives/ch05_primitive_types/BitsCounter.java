package com.github.orelgenya.epi.primitives.ch05_primitive_types;

public class BitsCounter {

    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }
}
