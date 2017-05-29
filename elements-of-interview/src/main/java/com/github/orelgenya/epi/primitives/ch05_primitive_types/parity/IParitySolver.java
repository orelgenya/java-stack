package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

@FunctionalInterface
public interface IParitySolver {

    int MAX_OPS_COUNT = Long.SIZE * Long.SIZE;

    Result parityOf(long input);
}
