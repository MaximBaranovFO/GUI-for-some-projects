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
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @todo logging for all createt objects and his states
 * @todo need correct clean for free resurses
 * @author wladimirowichbiaran
 */
public class ZPIEAppObjectsList {
    private ConcurrentSkipListMap<String, Runnable> currentWorkerList;
    private ArrayBlockingQueue<String> messagesQueueForLogging;
    
    private final Integer messagesQueueSize = ZPIAppConstants.LOG_LINES_COUNT;

    public ZPIEAppObjectsList() {
        this.currentWorkerList = new ConcurrentSkipListMap<>();
        this.messagesQueueForLogging = new ArrayBlockingQueue<String>(messagesQueueSize);
        currentWorkerList.put(ZPIAppMsgEnPrefixes.TH_NAME_LOG, new ZPIAppLoggerToTextRunnable(messagesQueueForLogging));
    }
    protected ConcurrentSkipListMap<String, Runnable> getWorkerList(){
        return currentWorkerList;
    }
    protected ArrayBlockingQueue<String> getLoggingQueue(){
        return messagesQueueForLogging;
    }
    protected Runnable getLogger(){
        Runnable getForReturn = currentWorkerList.get(ZPIAppMsgEnPrefixes.TH_NAME_LOG);
        
        if( getForReturn == null ){
            currentWorkerList.put(ZPIAppMsgEnPrefixes.TH_NAME_LOG, new ZPIAppLoggerToTextRunnable(messagesQueueForLogging));
            getForReturn = currentWorkerList.get(ZPIAppMsgEnPrefixes.TH_NAME_LOG);
        }
        return getForReturn;
    }
    protected String getPrefixInfo(){
        String prefixStr = ZPIAppMsgEnFiledForLog.FIELD_START
                + ZPIEAppFileOperationsSimple.getNowTimeStringWithMS()
                + ZPIAppMsgEnFiledForLog.FIELD_STOP
                + ZPIAppMsgEnFiledForLog.INFO;
        return prefixStr;
    }
    protected String getPrefixState(){
        String prefixStr = ZPIAppMsgEnFiledForLog.FIELD_START
                + ZPIEAppFileOperationsSimple.getNowTimeStringWithMS()
                + ZPIAppMsgEnFiledForLog.FIELD_STOP
                + ZPIAppMsgEnFiledForLog.STATE;
        return prefixStr;
    }
    protected String getPrefixWarning(){
        String prefixStr = ZPIAppMsgEnFiledForLog.FIELD_START
                + ZPIEAppFileOperationsSimple.getNowTimeStringWithMS()
                + ZPIAppMsgEnFiledForLog.FIELD_STOP
                + ZPIAppMsgEnFiledForLog.WARINING;
        return prefixStr;
    }
    protected String getPrefixError(){
        String prefixStr = ZPIAppMsgEnFiledForLog.FIELD_START
                + ZPIEAppFileOperationsSimple.getNowTimeStringWithMS()
                + ZPIAppMsgEnFiledForLog.FIELD_STOP
                + ZPIAppMsgEnFiledForLog.ERROR;
        return prefixStr;
    }
    
    protected void putLogMessageInfo(String strToLog){
        if( !strToLog.isEmpty() ){
            String prefixStr = getPrefixInfo()
                + strToLog;
            this.messagesQueueForLogging.add(prefixStr);
        }
        if( !this.messagesQueueForLogging.isEmpty()){
            if( this.messagesQueueForLogging.size() > (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT 
                - (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT % 10)) ){
                this.doLogger();
            }
        }
    }
    protected void putLogMessageState(String strToLog){
        if( !strToLog.isEmpty() ){
            String prefixStr = getPrefixState()
                + strToLog;
            this.messagesQueueForLogging.add(prefixStr);
        }
        if( !this.messagesQueueForLogging.isEmpty()){
            if( messagesQueueForLogging.size() > (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT 
                - (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT % 10)) ){
                this.doLogger();
            }
        }
    }
    protected void putLogMessageWarning(String strToLog){
        if( !strToLog.isEmpty() ){
            String prefixStr = getPrefixWarning()
                + strToLog;
            this.messagesQueueForLogging.add(prefixStr);
        }
        if( !this.messagesQueueForLogging.isEmpty()){
            if( this.messagesQueueForLogging.size() > (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT 
                - (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT % 10)) ){
                this.doLogger();
            }
        }
    }
    protected void putLogMessageError(String strToLog){
        if( !strToLog.isEmpty() ){
            String prefixStr = getPrefixError()
                + strToLog;
            this.messagesQueueForLogging.add(prefixStr);
        }
        if( !this.messagesQueueForLogging.isEmpty()){
            if( this.messagesQueueForLogging.size() > (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT 
                - (ZPIAppConstants.LIMIT_MESSAGES_FOR_LOG_IN_QUEUE_COUNT % 10)) ){
                this.doLogger();
            }
        }
    }
    protected void doLogger(){
        Thread foundedThread;
        Boolean existThread = Boolean.TRUE;
        try{
            foundedThread = new Thread(this.getLogger());
            //here test for many dump creator
            //AppObjectsInfo.dumpAllStackToHtml();
            try{
                foundedThread.join();
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
            
            foundedThread.start();
            
        } catch(NullPointerException ex){
            System.out.println("[CRITICALERROR]NullPointerException for init logger " + ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
    }
    protected Runnable addAnyThread(Runnable workerForAdd, String nameForKey) {
        String nameForWorker = nameForKey;
        Runnable  foundedThread = currentWorkerList.get(nameForWorker);
        if( foundedThread == null ){
            currentWorkerList.put(nameForWorker, workerForAdd);
            foundedThread = currentWorkerList.get(nameForWorker);
        }
        if( ZPIAppConstants.LOG_LEVEL_CURRENT > ZPIAppConstants.LOG_LEVEL_SILENT ){
            putLogMessageState("[ADDOBJECTTOLIST]" + nameForWorker);
            if( ZPIAppConstants.LOG_LEVEL_CURRENT > ZPIAppConstants.LOG_LEVEL_USE ){
                if( foundedThread != null ){
                    String classInfoToString = ZPIAppObjectsInfoHelperClasses.getClassInfoToString(foundedThread.getClass());
                    putLogMessageState("[CLASS]" + classInfoToString);
                }
            }
        }
        return foundedThread;
    }
    protected Runnable getThreadByKey(String nameForWorker){
        Runnable foundedThread = currentWorkerList.get(nameForWorker);
        return foundedThread;
    }
}
