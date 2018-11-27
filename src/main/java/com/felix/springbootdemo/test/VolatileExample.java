package com.felix.springbootdemo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class VolatileExample {
    public static volatile int i = 0;
    public static AtomicInteger y = new AtomicInteger(0);

    public static void main(String[] args) {
        volatileExample1();
    }

    public static void volatileExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Semaphore semaphore = new Semaphore(100);
//        CountDownLatch countDownLatch = new CountDownLatch(100);
        IntStream.range(0, 1000).forEach(
                s -> executorService.execute(() -> {
//                    try {
//                        semaphore.acquire();
                    y.getAndIncrement();
//                        semaphore.release();
//                        countDownLatch.countDown();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }));
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        executorService.shutdown();
        log.info("y的值为：{}", y);
    }

    public static void volatileExample1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Semaphore semaphore = new Semaphore(100);
        CountDownLatch countDownLatch = new CountDownLatch(9);
        IntStream.range(0, 10).forEach(
                s -> executorService.execute(() -> {
//                    try {
//                        semaphore.acquire();
                    y.getAndIncrement();
//                        semaphore.release();
                        countDownLatch.countDown();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        log.info("y的值为：{}", y);
    }
}

