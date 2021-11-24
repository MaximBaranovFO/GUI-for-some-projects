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

import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerController {
    private AppLoggerStateWriter currentWriterJob;
    private AppLoggerStateReader currentReaderJob;
    private Boolean readerJob;
    private Boolean notExistJob;
    private ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>> readedArrayForLines;
    
    
    public ZPIAppLoggerController(){
        this.notExistJob = Boolean.TRUE;
    }
    
    public ZPIAppLoggerController(ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>> outerReadBus,
            AppLoggerStateReader newJob) {
        this.notExistJob = Boolean.FALSE;
        this.readerJob = Boolean.TRUE;
        this.readedArrayForLines = outerReadBus;
        this.currentReaderJob = newJob;
        this.currentWriterJob = new AppLoggerStateWriter("blankWriter-" + UUID.randomUUID().toString());
        
    }
    public ZPIAppLoggerController(AppLoggerStateWriter newJob) {
        this.notExistJob = Boolean.FALSE;
        this.readerJob = Boolean.FALSE;
        this.currentWriterJob = newJob;
        this.currentReaderJob = new AppLoggerStateReader("blankReader-" + UUID.randomUUID().toString());
    }
    public ZPIAppLoggerController(Path logForHtmlCurrentLogSubDir,
            ArrayBlockingQueue<String> outputForWrite) {
        this.notExistJob = Boolean.FALSE;
        this.readerJob = Boolean.FALSE;
        Path pathTable = AppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        this.currentWriterJob = AppLoggerInfoToTables.initWriterNewJobLite(outputForWrite, pathTable);
        ThreadGroup newJobThreadGroup = new ThreadGroup(currentWriterJob.getThreadGroupName());
        
        Thread writeToHtmlByThread = new Thread(newJobThreadGroup, 
                new AppLoggerRunnableWrite(this), 
                currentWriterJob.getThreadName());
        writeToHtmlByThread.start();
    }
    protected AppLoggerStateWriter currentWriterJob(){
        return this.currentWriterJob;
    }
    protected AppLoggerStateReader currentReaderJob(){
        return this.currentReaderJob;
    }
    protected void setStringBusForLogRead(UUID idResult, ArrayBlockingQueue<String> readedFormHtmlLines){
        ArrayBlockingQueue<String> fromReadFile = new ArrayBlockingQueue<String>(readedFormHtmlLines.size());
        String pollFirstForSave = "";
        do{
            pollFirstForSave = readedFormHtmlLines.poll();
            if( pollFirstForSave != null ){
                fromReadFile.add(new String(pollFirstForSave));
            }
        }while( !readedFormHtmlLines.isEmpty() );
        this.readedArrayForLines.put(idResult, fromReadFile);
    }
    protected UUID getIdJob(){
        if( this.readerJob ){
            return this.currentReaderJob().getID();
        } 
        return this.currentWriterJob().getID();
    }
    protected Boolean isReaderJob(){
        return this.readerJob;
    }
    protected Boolean notExistJob(){
        return this.notExistJob;
    }
}
