package com.github.orelgenya.epi.primitives.ch05_primitive_types;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BitsCounterTest {


    public final int number;
    public final short bitsCount;

    @Parameterized.Parameters(name = "{index}: parity[{0}]={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0b0000, 0},
                {0b0001, 1},
                {0b0010, 1},
                {0b0011, 2},
                {0b0101, 2},
                {0b0111, 3},
                {0b1111, 4},
                {0b1011, 3},
                {0b11110101, 6},
                {0b10110101, 5},
                {0b10000000_00000000_00000000_00000000, 1},
                {0b10000000_10000000_00000000_00000000, 2},
        });
    }

    public BitsCounterTest(int number, int bitsCount) {
        this.number = number;
        this.bitsCount = (short) bitsCount;
    }

    @Test
    public void countBits() {
        assertEquals(bitsCount, BitsCounter.countBits(number));
    }

}