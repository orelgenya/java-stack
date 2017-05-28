package com.github.orelgenya.jcip.ch12;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author OrelGenya
 */
public class BoundedBufferTest {

    @Test
    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Assert.assertTrue(bb.isEmpty());
        Assert.assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        Assert.assertTrue(bb.isFull());
        Assert.assertFalse(bb.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPutsAndTakes() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 20; i++) {
            bb.put(i);
            bb.take();
        }
        Assert.assertTrue(bb.isEmpty());
        Assert.assertFalse(bb.isFull());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = bb.take();
                    Assert.fail("take isn't blocking");
                } catch (InterruptedException success) {}
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public static final int LOCKUP_DETECT_TIMEOUT = 100;
}
