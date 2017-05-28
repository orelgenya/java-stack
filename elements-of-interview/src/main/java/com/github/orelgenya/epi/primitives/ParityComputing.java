package com.github.orelgenya.epi.primitives;

public class ParityComputing {
    public static Result parity(long input) {
        Result result = new Result();
        byte parity = 0;
        while (input != 0) {
            result.opsCount++;
            parity ^= input & 1;
            input >>>= 1;
        }
        result.parity = parity == 0;
        return result;
    }

    public static class Result {
        int opsCount;
        boolean parity;
    }
}
