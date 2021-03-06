/*
 * Copyright 2018 wladimirowichbiaran.
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

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerBusJob {
    private ArrayBlockingQueue<ZPIAppLoggerStateReader> listJobForReaderRunnable;
    private ArrayBlockingQueue<ZPIAppLoggerStateWriter> listJobForWriterRunnable;
    
    private int countOfJobForReaderQueueCapacityChange;
    private int countOfJobForWriterQueueCapacityChange;

    //@todo job for status done deleter need create
    
    public ZPIAppLoggerBusJob() {
        this.listJobForReaderRunnable = new ArrayBlockingQueue<ZPIAppLoggerStateReader>(ZPIAppConstants.LOG_HTML_JOB_QUEUE_SIZE);
        this.listJobForWriterRunnable = new ArrayBlockingQueue<ZPIAppLoggerStateWriter>(ZPIAppConstants.LOG_HTML_JOB_QUEUE_SIZE);
        
        this.countOfJobForReaderQueueCapacityChange = 0;
        this.countOfJobForWriterQueueCapacityChange = 0;
    }
    /**
     * 
     */
    protected ZPIAppLoggerStateWriter getInitedForWriter(){
        ZPIAppLoggerStateWriter poll = this.listJobForWriterRunnable.poll();
        if( poll != null ){
            return poll;
        }
        return new ZPIAppLoggerStateWriter("HaventJobForRun-AppLoggerBusJob.getInitedForWriter");
    }
    /**
     * 
     */
    protected ZPIAppLoggerStateReader getInitedForReader(){
        ZPIAppLoggerStateReader poll = this.listJobForReaderRunnable.poll();
        if( poll != null ){
            return poll;
        }
        return new ZPIAppLoggerStateReader("HaventJobForRun-AppLoggerBusJob.getInitedForReader");
    }
    /**
     * Job For Reader Runnables Part
     * @todo select methods for isJobDone, isFileSet, isNewRunner
     * @return 
     */
    protected Integer getCountJobForReader(){
        return this.listJobForReaderRunnable.size();
    }
    protected Boolean isJobForReaderEmpty(){
        return this.listJobForReaderRunnable.isEmpty();
    }
    protected void addStateJobForReaderRunnableBus(ZPIAppLoggerStateReader forPut){
        extendSizeForStateJobForReaderQueue();
        this.listJobForReaderRunnable.add(forPut);
    }

    protected void extendSizeForStateJobForReaderQueue(){
        if( ((this.listJobForReaderRunnable.size() 
                - (this.listJobForReaderRunnable.size() % 10)) / 10) 
                > this.listJobForReaderRunnable.remainingCapacity() 
        ){
            System.out.println("-|-|-|-|-QUEUE listJobForReaderRunnable WARNING SIZE "
                + this.listJobForReaderRunnable.size()
                + "-|-|-|-|-QUEUE REMAINING CAPACITY "
                + this.listJobForReaderRunnable.remainingCapacity()
                + "-|-|-|-|-COUNT OF CHANGE QUEUE CAPACITY"
                + getCountOfStateJobForReaderQueueCapacityChange()
            );
            int nowSize = this.listJobForReaderRunnable.size() + this.listJobForReaderRunnable.remainingCapacity();
            ArrayBlockingQueue<ZPIAppLoggerStateReader> extendedQueue = new ArrayBlockingQueue<ZPIAppLoggerStateReader>(nowSize * 10);
            do{ 
                ZPIAppLoggerStateReader poll = this.listJobForReaderRunnable.poll();
                if( poll != null ){
                    extendedQueue.add(poll);
                }
            }while( !this.listJobForReaderRunnable.isEmpty() );
            incrementCountOfStateJobForReaderQueueCapacityChange();
            this.listJobForReaderRunnable = extendedQueue;
        }
        
    }
    protected int getCountOfStateJobForReaderQueueCapacityChange(){
        return this.countOfJobForReaderQueueCapacityChange;
    }
    protected void incrementCountOfStateJobForReaderQueueCapacityChange(){
        this.countOfJobForReaderQueueCapacityChange++;
    }
    /**
     * Job For Writer Runnables Part
     * 
     * @return 
     */
    protected Integer getCountJobForWriter(){
        return this.listJobForWriterRunnable.size();
    }
    protected Boolean isJobForWriterEmpty(){
        return this.listJobForWriterRunnable.isEmpty();
    }
    protected void addStateJobForWriterRunnableBus(ZPIAppLoggerStateWriter forPut){
        extendSizeForStateJobForWriterQueue();
        this.listJobForWriterRunnable.add(forPut);
    }

    protected void extendSizeForStateJobForWriterQueue(){
        if( ((this.listJobForWriterRunnable.size() 
                - (this.listJobForWriterRunnable.size() % 10)) / 10) 
                > this.listJobForWriterRunnable.remainingCapacity() 
        ){
            System.out.println("-|-|-|-|-QUEUE listJobForReaderRunnable WARNING SIZE "
                + this.listJobForWriterRunnable.size()
                + "-|-|-|-|-QUEUE REMAINING CAPACITY "
                + this.listJobForWriterRunnable.remainingCapacity()
                + "-|-|-|-|-COUNT OF CHANGE QUEUE CAPACITY"
                + getCountOfStateJobForWriterQueueCapacityChange()
            );
            int nowSize = this.listJobForWriterRunnable.size() + this.listJobForWriterRunnable.remainingCapacity();
            ArrayBlockingQueue<ZPIAppLoggerStateWriter> extendedQueue = new ArrayBlockingQueue<ZPIAppLoggerStateWriter>(nowSize * 10);
            do{ 
                ZPIAppLoggerStateWriter poll = this.listJobForWriterRunnable.poll();
                if( poll != null ){
                    extendedQueue.add(poll);
                }
            }while( !this.listJobForWriterRunnable.isEmpty() );
            incrementCountOfStateJobForWriterQueueCapacityChange();
            this.listJobForWriterRunnable = extendedQueue;
        }
        
    }
    protected int getCountOfStateJobForWriterQueueCapacityChange(){
        return this.countOfJobForWriterQueueCapacityChange;
    }
    protected void incrementCountOfStateJobForWriterQueueCapacityChange(){
        this.countOfJobForWriterQueueCapacityChange++;
    }
}
