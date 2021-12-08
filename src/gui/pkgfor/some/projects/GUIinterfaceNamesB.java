/*
 * Copyright 2021 Администратор.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gui.pkgfor.some.projects;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

//import ru.skuptsov.thread.pool.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;



import static java.lang.String.format;
/**
 *    https://github.com/kuptservol/how-it-works/blob/master/src/main/java/ru/skuptsov/thread/pool/counters/MultithreadClient.java
 * @author Администратор
 */
public interface GUIinterfaceNamesB {
    
    /**
 * @author Sergey Kuptsov
 * @since 02/03/2017
 */
public class ThreadPoolWu implements Executor {
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;

    public ThreadPoolWu(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
/**
 * Doubled class with another name see Up
 * ///***>>>!!!--->>>!!!<<<---!!!<<<***\\\
 * Make changes here
 */    
 public class ExecPoolFromNetWorks implements Executor {
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;

    public ExecPoolFromNetWorks(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
 }

     /**
 * @author Sergey Kuptsov
 * @since 01/03/2017
 */
public class Counter {

    public static double count(double a) {
        for (int i = 0; i < 1000000; i++) {
            a = a + Math.tan(a);
        }

        return a;
    }
}
 
/**
 * @author Sergey Kuptsov
 * @since 01/03/2017
 * <p>
 * 4:
 * -2.4709739987083262E8
 * Executed by 23 s
 * <p>
 * 8:
 * -2.4709739987083262E8
 * Executed by 17 s
 * <p>
 * 16:
 * -2.4709739987083262E8
 * Executed by 18 s
 * <p>
 * 400:
 * -2.4709739987083262E8
 * Executed by 15 s
 */
public class MultithreadClient {
    private final Counter counter;
    MultithreadClient(){
        counter = new Counter();
    }
    
    public static void mainFromPoolWorker() throws ExecutionException, InterruptedException {
//        ExecutorService threadPool = Executors.newWorkStealingPool();
        ThreadPoolWu threadPool = new ThreadPoolWu(8);
        
        

        long start = System.nanoTime();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            final int j = i;
            
            double count = Counter.count(j);
            
            futures.add(CompletableFuture.supplyAsync(()->count,threadPool));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        System.out.println(format("Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000_000),
                value));

        threadPool.shutdown();
    }
}

/**
 * @author Sergey Kuptsov
 * @since 01/03/2017
 * <p>
 * sout:
 * -2.4709739987083262E8
 * Executed by 112 s
 */
public class SingleThreadClient {

    public static void mainFromSingleThreadClient() {
        Counter counter = new Counter();

        long start = System.nanoTime();

        double value = 0;
        for (int i = 0; i < 400; i++) {
            value += counter.count(i);
        }

        System.out.println(format("Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000_000),
                value));
    }
}

}
