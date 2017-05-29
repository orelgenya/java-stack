package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public abstract class BaseParitySolverTest {

    protected static List<Result> results = new ArrayList<>();

    public final long input;
    public final boolean expectedParity;

    @Parameterized.Parameters(name = "{index}: parity[{0}]={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0b0000L, true},
                {0b0001L, false},
                {0b0010L, false},
                {0b0011L, true},
                {0b0101L, true},
                {0b0111L, false},
                {0b1111L, true},
                {0b1011L, false},
                {0b11110101L, true},
                {0b10110101L, false},
                {0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L, false},
                {0b10000000_10000000_00000000_00000000_00000000_00000000_00000000_00000000L, true},
        });
    }

    public BaseParitySolverTest(long input, boolean expectedParity) {
        this.input = input;
        this.expectedParity = expectedParity;
    }

    protected <T extends IParitySolver> void test(T paritySolver) {
        Result result = paritySolver.parityOf(input);

        Assert.assertEquals(expectedParity, result.parity);
        results.add(result);
    }
}
