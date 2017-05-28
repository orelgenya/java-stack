package com.github.orelgenya.jcip.ch02;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author OrelGenya
 */
public class CountingFactorizersTest {
    private final int nThreads = 16;
    private final int nIncrements = 10;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final CyclicBarrier barrier = new CyclicBarrier(nThreads + 1);

    @Test
    public void testCountingFactorizer() {
        System.out.println("CountingFactorizer test:");
        test(new CountingFactorizer());
        System.out.println();
    }

    @Test
    public void testUnsafeCountingFactorizer() {
        System.out.println("UnsafeCountingFactorizer test:");
        test(new UnsafeCountingFactorizer());
        System.out.println();
    }

    private void test(AbstractCountingFactorizer factorizer) {
        try {
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Requester(i, factorizer));
            }
            barrier.await();
            barrier.await();
            long n = nThreads * nIncrements, c = n - factorizer.getCount();
            System.out.println("    called " + n + " times, got " + c + " collisions.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    class Requester implements Runnable {
        private final int id;
        private final AbstractCountingFactorizer factorizer;

        Requester(int id, AbstractCountingFactorizer factorizer) {
            this.id = id;
            this.factorizer = factorizer;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                for (int i = 0; i < nIncrements; i++) {
                    factorizer.service(null, null);
                }
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
