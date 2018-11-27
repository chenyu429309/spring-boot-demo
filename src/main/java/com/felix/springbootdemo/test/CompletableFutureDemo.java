package com.felix.springbootdemo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CompletableFutureDemo {
    @Test
    public void test() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            sleep(3000);
        });
        assertFalse(cf.isDone());
        sleep(5000);
        assertTrue(cf.isDone());
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里的关键词是thenApply：
     * <p>
     * then是指在当前阶段正常执行完成后（正常执行是指没有抛出异常）进行的操作。在本例中，当前阶段已经完成并得到值message。
     * Apply是指将一个Function作用于之前阶段得出的结果
     * Function是阻塞的，这意味着只有当大写操作执行完成之后才会执行getNow()方法。
     * （有返回值）
     */
    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    /***
     * 异步的的将方法作用于前一个Stage
     */
    @Test
    public void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            sleep(3000);
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        log.info(cf.getNow("123").toString());
        assertEquals("MESSAGE", cf.join());
        log.info(cf.join().toString());
    }

    public ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    @Test
    void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            sleep(3000);
            return s.toUpperCase();
        }, executor);
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    /**
     * 消费(Consume)前一个Stage的结果(无返回值)
     */
    @Test
    public void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> thenAccept_message = CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        log.info(thenAccept_message.toString());
        assertTrue(Boolean.TRUE, String.valueOf(result.length() > 0));
    }

    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> {
                    sleep(600000);
                    result.append(s);
                });
//        cf.join();
        assertTrue(Boolean.TRUE, String.valueOf(result.length() > 0));
    }

    /**
     * 首先，我们新建了一个已经完成并带有返回值message的CompletableFuture对象。
     * 然后我们调用thenApplyAsync方法，该方法会返回一个新的CompletableFuture。这个方法用异步的方式执行大写操作。
     * 这里还展示了如何使用delayedExecutor(timeout, timeUnit)方法来延时异步操作。
     * 然后我们创建了一个handler stage，exceptionHandler，这个阶段会处理一切异常并返回另一个消息message upon cancel。
     * 最后，我们显式的完成第二个阶段并抛出异常，它会导致进行大写操作的阶段抛出CompletionException。它还会触发handler阶段。
     */
    @Test
    public void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .exceptionally(e -> {
                    throw new RuntimeException("123");
                })
                .thenApplyAsync(String::toUpperCase,
                        executor);
        CompletableFuture exceptionHandler = cf.handle((s, th)
                -> (th == null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            log.info(cf.getNow("123").toString());
//            fail("Should have thrown an exception");
        } catch (CompletionException ex) { // just for testing
            log.error(ex.getCause().getMessage());
//            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }
        log.info(exceptionHandler.join().toString());
//        assertEquals("message upon cancel", exceptionHandler.join());
    }

    /**
     * applyToEither 一个CompletableFuture基于另一个CompletableFuture结束，执行一个Future(必须第一个结束，不然Future不会执行)
     */
    @Test
    public void applyToEitherExample() {
        String original = "Message";
//        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
//                .thenApplyAsync(s -> {
////                    sleep(5000);
//                    return s.toUpperCase();
//                });
//        CompletableFuture<Integer> future = CompletableFuture.completedFuture(original).thenApplyAsync(s -> {
////            sleep(500000000);
//            return 1 / 0;
//        });
//        CompletableFuture cf2 = cf1.applyToEither(new CompletableFuture()
//                ,
//                s -> s + " from applyToEither");
//        log.info(cf2.join() + "");
        CompletableFuture<String> future = CompletableFuture.completedFuture(original).thenApplyAsync(s -> s.toUpperCase())
                .applyToEitherAsync(
                        CompletableFuture.completedFuture(original).thenApplyAsync(s -> s.toLowerCase()), s -> s + " from applyToEither"
                        , executor);
        log.info("{}", future.join());

    }

    @Test
    public void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> s.toUpperCase())
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s ->
                        {
                            sleep(6000000);
                            return s.toLowerCase();
                        }),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        log.info("结果为：{}", result);
    }

    @Test
    public void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(s -> {
            result.append(s.toUpperCase());
            return s.toLowerCase();
        }).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        log.info("结果为：{}", result);
    }

    @Test
    public void thenComposeExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> s.toLowerCase())
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> s.toUpperCase())
                        .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    @Test
    public void allOfAsyncExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> s.toUpperCase()))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> {
                        cf.getNow("123").toString().toUpperCase();
                        result.append("done");
                    });

                });
        allOf.join();
        log.info("{}", result);
    }
}
