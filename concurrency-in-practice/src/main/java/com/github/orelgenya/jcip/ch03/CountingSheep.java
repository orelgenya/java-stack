package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.ThreadSafe;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class CountingSheep extends Thread {
    private static volatile boolean asleep;
    private static volatile long sheepCount;

    @Override
    public void run() {
        while(!asleep) countSomeSheep();
        System.out.println(sheepCount);
    }

    private void countSomeSheep() {
        sheepCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        new CountingSheep().start();
        Thread.sleep(1);
        asleep = true;
    }
}
