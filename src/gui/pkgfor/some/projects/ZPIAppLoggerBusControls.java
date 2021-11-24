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
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @todo reRelease of this class for use in more instace of run,
 * fields of class need release in ThreadLocal variables
 * integrate for reader job process in class, logics for
 * do in write and read process need release into classes of write
 * and read job helpers
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerBusControls {
    private Integer sortItems;
    private ConcurrentSkipListMap<UUID, AppLoggerController> writerList;
    private ConcurrentSkipListMap<UUID, AppLoggerController> readerList;
    private ConcurrentSkipListMap<String, Path> listLogStorageFiles;
    private AppLoggerBusData dataBus;
    private UUID lastKey;
    ZPIAppLoggerBusControls(){
        createNewHtmlLogStorage();
        this.sortItems = 0;
        this.dataBus = new AppLoggerBusData();
        this.writerList = new ConcurrentSkipListMap<UUID, AppLoggerController>();
        this.readerList = new ConcurrentSkipListMap<UUID, AppLoggerController>();
    }
    protected AppLoggerController getByKey(String keyForGet){
        AppLoggerController getFromWriter = this.writerList.get(keyForGet);
        
        if( getFromWriter != null ){
            return getFromWriter;
        }
        AppLoggerController getFromReader = this.readerList.get(keyForGet);
        if( getFromReader != null ){
            return getFromReader;
        }
        return new AppLoggerController();
    }
    protected UUID addAndGetKey(AppLoggerController elementForAdd){
        UUID id = elementForAdd.getIdJob();
        if( elementForAdd.isReaderJob() ){
            this.readerList.put(id, elementForAdd);
        } else {
            this.writerList.put(id, elementForAdd);
        }
        
        this.lastKey = id;
        return id;
    }
    protected UUID getLastKey(){
        return this.lastKey;
    }
    
    protected UUID createJobWriteTableFile(
            ArrayBlockingQueue<String> outputForWrite
    ){
        ReentrantLock forCreateJobWriteTableFilelck = new ReentrantLock();
        forCreateJobWriteTableFilelck.lock();
        try{
            this.sortItems++;
            Path pathTable = AppFileOperationsSimple.getNewLogHtmlTableFile(
                    this.listLogStorageFiles.get(AppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR)
            );
            AppLoggerStateWriter initWriterNewJob = AppLoggerInfoToTables.initWriterNewJobLite(outputForWrite, pathTable);
            UUID writeTableId = initWriterNewJob.getID();
            initWriterNewJob.addSortItemNumber(this.sortItems);
            AppLoggerController appLoggerControllerWriteTable = new AppLoggerController(initWriterNewJob);
            this.writerList.put(writeTableId, appLoggerControllerWriteTable);
            return writeTableId;
        } finally {
            forCreateJobWriteTableFilelck.unlock();
        }    
    }
    
    
    protected UUID createJobWriteAnyFile(
            Path anyFile,
            ArrayBlockingQueue<String> outputForWrite
    ){
        ReentrantLock forCreateJobWritelck = new ReentrantLock();
        forCreateJobWritelck.lock();
        try{
            Path pathTable = anyFile;
            AppLoggerStateWriter initWriterNewJob = AppLoggerInfoToTables.initWriterNewJobLite(outputForWrite, pathTable);
            AppLoggerController appLoggerControllerForWrite = new AppLoggerController(initWriterNewJob);
            UUID writeId = initWriterNewJob.getID();
            this.writerList.put(writeId, appLoggerControllerForWrite);
            return writeId;
        } finally {
            forCreateJobWritelck.unlock();
        }
    }
    
    
    protected UUID createJobRead(Path tableElement){
        ReentrantLock forCreateJobReadlck = new ReentrantLock();
        forCreateJobReadlck.lock();
        try{
            AppLoggerStateReader initReaderNewJobLite = AppLoggerInfoToReport.initReaderNewJobLite(tableElement);
            UUID readId = initReaderNewJobLite.getID();
            initReaderNewJobLite.setAncorString(AppLoggerList.getAncorStructure(tableElement));
            //@todo add to bus data for reader structure
            AppLoggerController appLoggerControllerForRead = new AppLoggerController(this.dataBus.getReaderBus(), initReaderNewJobLite);
            this.readerList.put(readId, appLoggerControllerForRead);
            return readId;
        } finally {
            forCreateJobReadlck.unlock();
        }
    }
    protected AppLoggerController getNotFinishedWriterJob(){
        ReentrantLock forNotFinishedWriterlck = new ReentrantLock();
        forNotFinishedWriterlck.lock();
        try{
            for( Map.Entry<UUID, AppLoggerController> entrySet : this.writerList.entrySet() ){
                if( !entrySet.getValue().currentWriterJob().isToHTMLJobDone() ){
                    return entrySet.getValue();
                }
            }
            return new AppLoggerController();
        } finally {
            forNotFinishedWriterlck.unlock();
        }    
    }
    protected AppLoggerBusData getDataBus(){
        return this.dataBus;
    }
    protected ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>> getReadedDataBus(){
        return this.dataBus.getReaderBus();
    }
    protected ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>> getWritedDataBus(){
        return this.dataBus.getWritedBus();
    }
    protected ConcurrentSkipListMap<String, Path> createNewHtmlLogStorage(){
        String instanceStartTimeWithMS = 
                AppFileOperationsSimple.getNowTimeStringWithMS();
        Path logForHtmlCurrentLogSubDir = 
                    AppFileOperationsSimple.getLogForHtmlCurrentLogSubDir(instanceStartTimeWithMS);
        this.listLogStorageFiles = 
                AppFileOperationsSimple.getNewHtmlLogStorageFileSystem(logForHtmlCurrentLogSubDir);
        this.listLogStorageFiles.put(AppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR, logForHtmlCurrentLogSubDir);
        return this.listLogStorageFiles;
    }
    protected ConcurrentSkipListMap<String, Path> getHtmlLogStorage(){
        return this.listLogStorageFiles;
    }
    protected Path getJsFile(){
        Path fileJsMenuPrefix = this.listLogStorageFiles.get(AppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX);
        return fileJsMenuPrefix;
    }
    protected Path getCssFile(){
        Path fileCssPrefix = this.listLogStorageFiles.get(AppFileNamesConstants.LOG_HTML_CSS_PREFIX);
        return fileCssPrefix;
    }
    protected Path getIndexFile(){
        Path fileIndexPrefix = this.listLogStorageFiles.get(AppFileNamesConstants.LOG_INDEX_PREFIX);
        return fileIndexPrefix;
    }
    protected Path getCurrentLogSubDir(){
        return this.listLogStorageFiles.get(AppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR);
    }
}
