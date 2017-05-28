package com.github.orelgenya.epi.primitives.ch05_primitive_types.parity;

import com.github.orelgenya.epi.primitives.ch05_primitive_types.parity.ParityComputing.Result;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParityComputingTest {

    private static List<Result> results = new ArrayList<>();

    public final long input;
    public final boolean expectedParity;

    @Parameters(name = "{index}: parity[{0}]={1}")
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

    public ParityComputingTest(long input, boolean expectedParity) {
        this.input = input;
        this.expectedParity = expectedParity;
    }

    @Test
    public void test() {
        Result result = ParityComputing.parity(input);

        Assert.assertEquals(expectedParity, result.parity);
        System.out.println("Iterations: " + result.opsCount);
        results.add(result);
    }

    @AfterClass
    public static void printStats() {

    }
}