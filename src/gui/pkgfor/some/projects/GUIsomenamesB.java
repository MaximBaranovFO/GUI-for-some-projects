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
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class GUIsomenamesB {
    private ConcurrentSkipListMap<String, Runnable> currentWorkerList;
    private int[] arrayWu;
    private static ValueSumCounter counterWu;
    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    public class ValueSumCounter extends RecursiveTask<Integer> {

    private int[] array;

    public ValueSumCounter(int[] array) {
        this.array = array;
    }

    /*@SneakyThrows*/
    @Override
    protected Integer compute() {
        if(array.length <= 2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIsomenamesB.class.getName()).log(Level.SEVERE, null, ex);
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


    GUIsomenamesB(){
        arrayWu = getInitArray(10000);
        counterWu = new ValueSumCounter(arrayWu);
        //forkJoinPool = new ForkJoinPool();
    }
    
    public static void doCreationTaskHowMain() {
        
        try {
        System.out.println(new Date());
        ValueSumCounter counterWu1 = GUIsomenamesB.counterWu;
        Integer workerSayIorAmnvoke = forkJoinPool.invoke(counterWu1);
        System.out.println(String.valueOf(workerSayIorAmnvoke));
        System.out.println(new Date());
        } catch(Exception exCounterWuError) {
            System.out.println(exCounterWuError.getMessage());
            exCounterWuError.printStackTrace();
        }
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 3;
        }
        return array;
    }

}
