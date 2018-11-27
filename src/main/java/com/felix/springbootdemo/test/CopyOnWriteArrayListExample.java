package com.felix.springbootdemo.test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Volatile关键字的作用
 *  a、保证多线程间的可见性
 */
@Slf4j
public class CopyOnWriteArrayListExample {
    //请求总数
    public static final int clientToal = 100;
    //同时并发执行的线程数
    public static final int threadTotal = 200;
    public final static AtomicInteger CONCURRENT_INTEGER = new AtomicInteger(0);
    private static volatile int testInteger=0;
//        public static List<Integer> list = Lists.newArrayList();
    public static List<Integer> list = Lists.newCopyOnWriteArrayList();

    public static void main(String[] args) {
        init();
    }
    public static void init(){
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int s = 0; s < 100; s++) {
            CompletableFuture.runAsync(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CONCURRENT_INTEGER.addAndGet(1);
                        semaphore.release();
                    }
            ).exceptionally(e -> {
                throw new RuntimeException(e.getMessage());
            });
        }
        log.info("size:{}", CONCURRENT_INTEGER);
    }

    public static void copyOnWriteArrayListExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，api中包含接收
        Semaphore semaphore = new Semaphore(threadTotal);
        //等待所有的线程完成，再执行下一个操作
//        CountDownLatch countDownLatch = new CountDownLatch(clientToal);
        IntStream.range(0, 5000).forEach(
                s -> {
                    final int count = s;
                    executorService.execute(() -> {
                        try {
                            semaphore.acquire();
//                            CONCURRENT_INTEGER.addAndGet(1);
                            testInteger++;
//                            update(count);
                            semaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        countDownLatch.countDown();
                    });
                }
        );
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        executorService.shutdown();
        log.info("size:{}", testInteger);

    }

    private static void update(int i) {
        list.add(i);
    }
}
