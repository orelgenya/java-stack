package com.github.orelgenya.jcip.ch01;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author OrelGenya
 */
public class SequenceTest {
    private final int nThreads = 16;
    private final int nIncrements = 10;
    private final AtomicInteger count = new AtomicInteger();
    private final CountDownLatch startGate = new CountDownLatch(1);
    private final CountDownLatch endGate = new CountDownLatch(nThreads);
    private final ConcurrentHashMap<Integer, ConcurrentSkipListSet<Integer>> map = new ConcurrentHashMap<>();
    private final ConcurrentSkipListSet<Integer> collisions = new ConcurrentSkipListSet<>();

    @Test
    public void testUnsafe() {
        System.out.println("UnsafeSequence test.");
        test(new UnsafeSequence());
        System.out.println();
    }

    @Test
    public void testSafe() {
        System.out.println("SafeSequence test.");
        test(new SafeSequence());
        System.out.println();
    }

    private void test(Sequence seq) {
        ExecutorService es = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++)
            es.submit(new MyTask(i, seq));

        startGate.countDown();
        try {
            endGate.await();
        } catch (InterruptedException ignored) {
        }
        es.shutdownNow();

        System.out.println("Called " + count + " times, Sequencer value = " + seq.getValue()
                + ", " + collisions.size() + " collisions.");
        for (Integer collision : collisions) {
            System.out.println(collision + ": " + map.get(collision));
        }
    }

    class MyTask implements Runnable {
        private final int id;
        private final Sequence seq;

        MyTask(int id, Sequence seq) {
            this.id = id;
            this.seq = seq;
        }

        @Override
        public void run() {
            try {
                startGate.await();
            } catch (InterruptedException ignored) {
            }

            for (int i = 0; i < nIncrements; i++) {
                int value = seq.nextValue();
                count.incrementAndGet();
                ConcurrentSkipListSet<Integer> threads = map.putIfAbsent(value,
                        new ConcurrentSkipListSet<Integer>() {{
                            add(id);
                        }});
                if (threads != null) {
                    threads.add(id);
                    collisions.add(value);
                }
            }
            endGate.countDown();
        }
    }
}
