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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesC {
    /**
     * https://habr.com/ru/post/554608/
     */
    public class MotivatedByNetWorkInformation implements ExecutorService {
        public static void singleAboutAuthors(){
            ExecutorService service = Executors.newFixedThreadPool(3);
            service.execute(new Runnable() {
                public void run() {
                    System.out.println("Another thread was executed");
                }
            });
        }
        public void doWorkForSingle(){
            ExecutorService service1 = Executors.newSingleThreadExecutor();
            ExecutorService service2 = Executors.newFixedThreadPool(3);
            ExecutorService service3 = Executors.newScheduledThreadPool(3);
        }
        public static void doWorkForSingleWithContol(){
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future future = service.submit(new Runnable() {
                public void run() {
                    System.out.println("Another thread was executed");
                }
            });

            try {
                future.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            }
            service.shutdown();
        }
        public static void doWorkForSingleWithResult(){
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future future = service.submit(new Callable(){
                public Object call() throws Exception {
                    System.out.println("Another thread was executed");
                    return "result";
                }
            });

            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            }
            service.shutdown();
        }

        @Override
        public void shutdown() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isShutdown() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isTerminated() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> Future<T> submit(Runnable r, T t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Future<?> submit(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> clctn) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> clctn, long l, TimeUnit tu) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> clctn) throws InterruptedException, ExecutionException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> clctn, long l, TimeUnit tu) throws InterruptedException, ExecutionException, TimeoutException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void execute(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
