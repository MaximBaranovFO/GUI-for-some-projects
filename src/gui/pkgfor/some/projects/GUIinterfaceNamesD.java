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

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesD {
    public class SimpleClass extends RecursiveTask<String> {

    @Override
    protected String compute() {
         System.out.println("I am work in thread: " + Thread.currentThread().getName());
         return "I am just innocent simple class";
    }
    }
    public static void mainDoWorkerByAuthorsFromNetWithForkPool() {
        SimpleClass simpleClass = new SimpleClass();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(simpleClass));
    }
    public static void mainDoWorkerByAuthorsFromNetWithFork() {
        SimpleClass simpleClass = new SimpleClass();
        simpleClass.fork();
        System.out.println(simpleClass.join());
    }
    /**
     * https://habr.com/ru/post/565924/
     * REM То есть при вызове метода fork() задача не «выполнила сама себя» 
     * REM магическим образом, а была выполнена в том же потоке из которого 
     * REM и был вызван данный метод. Вызов метода ForkJoinPool.invoke() передал 
     * REM задачу на выполнение в один из потоков данного пула. Важно отметить, 
     * REM что метод fork() отправляет задачу в какой-либо поток, но при этом не 
     * REM запускает её выполнения. Для получения результата служит метод join().
     */
    public static void mainDoWorkerByAuthorsFromNetWithForkPoolAnother() {
        SimpleClass simpleClass = new SimpleClass();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(simpleClass));
    }
    public static void mainDoWorkerByAuthorsFromNetWithForkAnother() {
        SimpleClass simpleClass = new SimpleClass();
        simpleClass.fork();
        System.out.println(simpleClass.join());
    }
    /**
     * 
     */
    public class ValueSumCounter extends RecursiveTask<Integer> {

    private int[] array;

    public ValueSumCounter(int[] array) {
        this.array = array;
    }

    
    @Override
    protected Integer compute() {
        if(array.length <= 2) {
            System.out.printf("Task %s execute in thread %s%n", this, Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIinterfaceNamesD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Arrays.stream(array).sum();
        }
        ValueSumCounter firstHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, 0, array.length/2));
        ValueSumCounter secondHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, array.length/2, array.length));
        firstHalfArrayValueSumCounter.fork();
        secondHalfArrayValueSumCounter.fork();
        return firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
    }
}

public class MainDoWorkerByAuthorsFromNet {

    public static void mainDoWorkerByAuthorsFromNet() {
        int[] array = getInitArray(10000);
        ValueSumCounter counter = new ValueSumCounter(array);
        System.out.println(new Date());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(counter));
        System.out.println(new Date());
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 3;
        }
        return array;
    }
}
/**
 * REM Запустив код на выполнение, мы увидим, что для выполнения большого количества 
 * REM задач используется несколько одних и тех же потоков
 */
}
