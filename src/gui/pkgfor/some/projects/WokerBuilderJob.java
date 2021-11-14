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

import java.nio.file.Path;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.SwingUtilities;

/**
 *
 * @author Администратор
 */
public class WokerBuilderJob {
    private ConcurrentSkipListMap<Integer, Path> idAtFileSystemPath;
    private ConcurrentSkipListMap<Integer, Boolean> idJobRunFinish;
    private ConcurrentSkipListMap<Integer, Object> idWorkerAlg;
    private Boolean caseNowInJob;
     
    WokerBuilderJob(){
        this.caseNowInJob = Boolean.FALSE;
        this.idAtFileSystemPath = new ConcurrentSkipListMap<Integer, Path>();
        this.idJobRunFinish = new ConcurrentSkipListMap<Integer, Boolean>();
        this.idWorkerAlg = new ConcurrentSkipListMap<Integer, Object>();
    }
    private void runThreadWorker(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //new GUIForSomeProjects();
            }
        });
    }
    private Boolean isJobInWork(){
        return this.caseNowInJob;
    }
    /** 
       class SerialExecutor implements Executor {
   final Queue<Runnable> tasks = new ArrayDeque<>();
   final Executor executor;
   Runnable active;

   SerialExecutor(Executor executor) {
     this.executor = executor;
   }

   public synchronized void execute(Runnable r) {
     tasks.add(() -> {
       try {
         r.run();
       } finally {
         scheduleNext();
       }
     });
     if (active == null) {
       scheduleNext();
     }
   }

   protected synchronized void scheduleNext() {
     if ((active = tasks.poll()) != null) {
       executor.execute(active);
     }
   }
 }
     */
    /** 
       class SerialExecutor implements Executor {
   final Queue<Runnable> tasks = new ArrayDeque<>();
   final Executor executor;
   Runnable active;

   SerialExecutor(Executor executor) {
     this.executor = executor;
   }

   public synchronized void execute(Runnable r) {
     tasks.add(() -> {
       try {
         r.run();
       } finally {
         scheduleNext();
       }
     });
     if (active == null) {
       scheduleNext();
     }
   }

   protected synchronized void scheduleNext() {
     if ((active = tasks.poll()) != null) {
       executor.execute(active);
     }
   }
 }
     */
    /** 
      class PrimeNumbersTask extends
         SwingWorker<List<Integer>, Integer> {
     PrimeNumbersTask(JTextArea textArea, int numbersToFind) {
         //initialize
     }

     @Override
     public List<Integer> doInBackground() {
         while (! enough && ! isCancelled()) {
                 number = nextPrimeNumber();
                 publish(number);
                 setProgress(100 * numbers.size() / numbersToFind);
             }
         }
         return numbers;
     }

     @Override
     protected void process(List<Integer> chunks) {
         for (int number : chunks) {
             textArea.append(number + "\n");
         }
     }
 }

 JTextArea textArea = new JTextArea();
 final JProgressBar progressBar = new JProgressBar(0, 100);
 PrimeNumbersTask task = new PrimeNumbersTask(textArea, N);
 task.addPropertyChangeListener(
     new PropertyChangeListener() {
         public  void propertyChange(PropertyChangeEvent evt) {
             if ("progress".equals(evt.getPropertyName())) {
                 progressBar.setValue((Integer)evt.getNewValue());
             }
         }
     });

 task.execute();
 System.out.println(task.get()); //prints all prime numbers
     */
}
