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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.file.Path;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppObjectsInfo {
    protected static void dumpAllStackToHtml(){
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ZPIAppLoggerCreationHtmlLog t = new ZPIAppLoggerCreationHtmlLog( threadGroup, UUID.randomUUID().toString() );
        t.start();
    }
    /**
     * For many run need reader code integrated to ZPIAppLoggerBusControls class
     * ru.newcontrol.ncfv.ZPIAppFileOperationsSimple.getNewLogHtmlTableFile line 398 generate exception about file exist
     * need current storage for log use in ThreadLocal variables, while this is field of class curent storage can not 
     * remove in finally block
     * 
     * @todo next realisations need
     */
    protected static void dumpAllStackToHtmlProcessForManyRun(){
        ThreadLocal<ZPIAppLoggerBusControls> jobCtrlThLoc = new ThreadLocal<ZPIAppLoggerBusControls>();
        
        jobCtrlThLoc.set(new ZPIAppLoggerBusControls());
        try{
            ArrayBlockingQueue<String> systemEnvironmentCommandsOut = ZPIAppObjectsInfoHelperClasses.getSystemEnvironmentCommandsOut();
            jobCtrlThLoc.get().createJobWriteTableFile(systemEnvironmentCommandsOut);

            for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
                Class<? extends Thread> aClass = elStTr.getKey().getClass();
                ArrayBlockingQueue<String> threadNameCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadNameCommandsOut(elStTr.getKey());
                jobCtrlThLoc.get().createJobWriteTableFile(threadNameCommandsOut);

                for( StackTraceElement elStack : elStTr.getValue() ){
                    ArrayBlockingQueue<String> stackTraceCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadStackTraceCommandsOut(elStack);
                    jobCtrlThLoc.get().createJobWriteTableFile(stackTraceCommandsOut);
                    Class<? extends StackTraceElement> stackClass = elStack.getClass();

                    ArrayBlockingQueue<String> classStackCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(stackClass);
                    jobCtrlThLoc.get().createJobWriteTableFile(classStackCommandsOut);

                    ArrayBlockingQueue<String> classStackGetDeclaredMethodsCommandsOut = 
                    ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(stackClass.getClass());
                    jobCtrlThLoc.get().createJobWriteTableFile(classStackGetDeclaredMethodsCommandsOut);

                    ArrayBlockingQueue<String> classStackGetDeclaredFieldsCommandsOut = 
                    ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(stackClass.getClass());
                    jobCtrlThLoc.get().createJobWriteTableFile(classStackGetDeclaredFieldsCommandsOut);

                    ArrayBlockingQueue<String> classGetDeclaredAnnotationCommandsOut = 
                    ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredAnnotationsCommandsOut(aClass.getClass());
                    jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredAnnotationCommandsOut);

                    ArrayBlockingQueue<String> classGetDeclaredConstructorsCommandsOut = 
                    ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredConstructorsCommandsOut(aClass.getClass());
                    jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredConstructorsCommandsOut);

                }

                //tableCreateJobs(logForHtmlCurrentLogSubDir, threadNameCommandsOut);
                ArrayBlockingQueue<String> classCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(aClass);
                jobCtrlThLoc.get().createJobWriteTableFile(classCommandsOut);
                //tableCreateJobs(logForHtmlCurrentLogSubDir, classCommandsOut);
                ArrayBlockingQueue<String> classGetDeclaredMethodsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(aClass);
                jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredMethodsCommandsOut);
                //tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredMethodsCommandsOut);
                ArrayBlockingQueue<String> classGetDeclaredFieldsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(aClass);
                jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredFieldsCommandsOut);
                //tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredFieldsCommandsOut);

                ArrayBlockingQueue<String> classGetDeclaredAnnotationCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredAnnotationsCommandsOut(aClass);
                jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredAnnotationCommandsOut);

                ArrayBlockingQueue<String> classGetDeclaredConstructorsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredConstructorsCommandsOut(aClass);
                jobCtrlThLoc.get().createJobWriteTableFile(classGetDeclaredConstructorsCommandsOut);
            }

            ArrayBlockingQueue<String> linesForSaveJsMenu = ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu();
            Path jsFile = jobCtrlThLoc.get().getJsFile();
            jobCtrlThLoc.get().createJobWriteAnyFile(jsFile, linesForSaveJsMenu);
            ArrayBlockingQueue<String> linesForSaveCss = ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss();
            Path cssFile = jobCtrlThLoc.get().getCssFile();
            jobCtrlThLoc.get().createJobWriteAnyFile(cssFile, linesForSaveCss);

            ZPIAppLoggerController notFinishedWriterJob = jobCtrlThLoc.get().getNotFinishedWriterJob();
            do{
                String outForJobsReaderParam = 
                        "notFinishedWriterJob.getIdJob().toString() "
                        + notFinishedWriterJob.getIdJob().toString()
                        + " notFinishedWriterJob.isReaderJob() "
                        + notFinishedWriterJob.isReaderJob()
                        + " notFinishedWriterJob.currentWriterJob().getThreadName() "
                        + notFinishedWriterJob.currentWriterJob().getThreadName()
                        + " notFinishedWriterJob.currentWriterJob().getThreadGroupName() "
                        + notFinishedWriterJob.currentWriterJob().getThreadGroupName()
                        + " notFinishedWriterJob.currentWriterJob().getID().toString() "
                        + notFinishedWriterJob.currentWriterJob().getID().toString()
                        + " notFinishedWriterJob.currentWriterJob().getPartLinesForWrite().size() "        
                        + notFinishedWriterJob.currentWriterJob().getPartLinesForWrite().size()
                        + " notFinishedWriterJob.currentWriterJob().isToHTMLJobDone() "
                        + notFinishedWriterJob.currentWriterJob().isToHTMLJobDone()
                        + " notFinishedWriterJob.currentWriterJob().isBlankObject() "
                        + notFinishedWriterJob.currentWriterJob().isBlankObject()
                ;
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outForJobsReaderParam, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_VIEW_THREADS_PARAM);
                runWriterJob(jobCtrlThLoc.get());
                notFinishedWriterJob = jobCtrlThLoc.get().getNotFinishedWriterJob();
            }while( !notFinishedWriterJob.notExistJob() );

            Path indexFile = jobCtrlThLoc.get().getIndexFile();
            summaryReportJobs(jobCtrlThLoc.get().getCurrentLogSubDir(), jsFile.getFileName(), cssFile.getFileName(), indexFile);
        } finally {
            //jobCtrlThLoc.get().getHtmlLogStorage().remove();
            jobCtrlThLoc.remove();
        }
    }
    
    /**
     * @todo from innercounter create class name structure for rows by types of functions
     */
    
    protected static void dumpAllStackToHtmlProcess(){
        
        ZPIAppLoggerBusControls jobControl = new ZPIAppLoggerBusControls();
        
        
        ArrayBlockingQueue<String> systemEnvironmentCommandsOut = ZPIAppObjectsInfoHelperClasses.getSystemEnvironmentCommandsOut();
        jobControl.createJobWriteTableFile(systemEnvironmentCommandsOut);
            
        for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
            Class<? extends Thread> aClass = elStTr.getKey().getClass();
            ArrayBlockingQueue<String> threadNameCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadNameCommandsOut(elStTr.getKey());
            jobControl.createJobWriteTableFile(threadNameCommandsOut);
            
            for( StackTraceElement elStack : elStTr.getValue() ){
                ArrayBlockingQueue<String> stackTraceCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadStackTraceCommandsOut(elStack);
                jobControl.createJobWriteTableFile(stackTraceCommandsOut);
                Class<? extends StackTraceElement> stackClass = elStack.getClass();
                
                ArrayBlockingQueue<String> classStackCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(stackClass);
                jobControl.createJobWriteTableFile(classStackCommandsOut);
                
                ArrayBlockingQueue<String> classStackGetDeclaredMethodsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(stackClass.getClass());
                jobControl.createJobWriteTableFile(classStackGetDeclaredMethodsCommandsOut);
                
                ArrayBlockingQueue<String> classStackGetDeclaredFieldsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(stackClass.getClass());
                jobControl.createJobWriteTableFile(classStackGetDeclaredFieldsCommandsOut);
                
                ArrayBlockingQueue<String> classGetDeclaredAnnotationCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredAnnotationsCommandsOut(aClass.getClass());
                jobControl.createJobWriteTableFile(classGetDeclaredAnnotationCommandsOut);

                ArrayBlockingQueue<String> classGetDeclaredConstructorsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredConstructorsCommandsOut(aClass.getClass());
                jobControl.createJobWriteTableFile(classGetDeclaredConstructorsCommandsOut);
                
            }
            
            //tableCreateJobs(logForHtmlCurrentLogSubDir, threadNameCommandsOut);
            ArrayBlockingQueue<String> classCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(aClass);
            jobControl.createJobWriteTableFile(classCommandsOut);
            //tableCreateJobs(logForHtmlCurrentLogSubDir, classCommandsOut);
            ArrayBlockingQueue<String> classGetDeclaredMethodsCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(aClass);
            jobControl.createJobWriteTableFile(classGetDeclaredMethodsCommandsOut);
            //tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredMethodsCommandsOut);
            ArrayBlockingQueue<String> classGetDeclaredFieldsCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(aClass);
            jobControl.createJobWriteTableFile(classGetDeclaredFieldsCommandsOut);
            //tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredFieldsCommandsOut);
            
            ArrayBlockingQueue<String> classGetDeclaredAnnotationCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredAnnotationsCommandsOut(aClass);
            jobControl.createJobWriteTableFile(classGetDeclaredAnnotationCommandsOut);
            
            ArrayBlockingQueue<String> classGetDeclaredConstructorsCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredConstructorsCommandsOut(aClass);
            jobControl.createJobWriteTableFile(classGetDeclaredConstructorsCommandsOut);
        }
        
        ArrayBlockingQueue<String> linesForSaveJsMenu = ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu();
        Path jsFile = jobControl.getJsFile();
        jobControl.createJobWriteAnyFile(jsFile, linesForSaveJsMenu);
        ArrayBlockingQueue<String> linesForSaveCss = ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss();
        Path cssFile = jobControl.getCssFile();
        jobControl.createJobWriteAnyFile(cssFile, linesForSaveCss);
        
        ZPIAppLoggerController notFinishedWriterJob = jobControl.getNotFinishedWriterJob();
        do{
            String outForJobsReaderParam = 
                    "notFinishedWriterJob.getIdJob().toString() "
                    + notFinishedWriterJob.getIdJob().toString()
                    + " notFinishedWriterJob.isReaderJob() "
                    + notFinishedWriterJob.isReaderJob()
                    + " notFinishedWriterJob.currentWriterJob().getThreadName() "
                    + notFinishedWriterJob.currentWriterJob().getThreadName()
                    + " notFinishedWriterJob.currentWriterJob().getThreadGroupName() "
                    + notFinishedWriterJob.currentWriterJob().getThreadGroupName()
                    + " notFinishedWriterJob.currentWriterJob().getID().toString() "
                    + notFinishedWriterJob.currentWriterJob().getID().toString()
                    + " notFinishedWriterJob.currentWriterJob().getPartLinesForWrite().size() "        
                    + notFinishedWriterJob.currentWriterJob().getPartLinesForWrite().size()
                    + " notFinishedWriterJob.currentWriterJob().isToHTMLJobDone() "
                    + notFinishedWriterJob.currentWriterJob().isToHTMLJobDone()
                    + " notFinishedWriterJob.currentWriterJob().isBlankObject() "
                    + notFinishedWriterJob.currentWriterJob().isBlankObject()
            ;
            ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outForJobsReaderParam, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_VIEW_THREADS_PARAM);
            runWriterJob(jobControl);
            notFinishedWriterJob = jobControl.getNotFinishedWriterJob();
        }while( !notFinishedWriterJob.notExistJob() );
        
        Path indexFile = jobControl.getIndexFile();
        summaryReportJobs(jobControl.getCurrentLogSubDir(), jsFile.getFileName(), cssFile.getFileName(), indexFile);
    }
    
    protected static void runWriterJob(ZPIAppLoggerBusControls jobControl){
        ZPIAppLoggerController notFinishedWriterJob = jobControl.getNotFinishedWriterJob();
        if( !notFinishedWriterJob.notExistJob() ){
            ZPIAppLoggerStateWriter currentWriterJob = notFinishedWriterJob.currentWriterJob();
            if( !currentWriterJob.isBlankObject() ){
                /*ReentrantLock forRunWriterJoblck = new ReentrantLock();
                forRunWriterJoblck.lock();
                try{*/
                    ZPIAppLoggerRunnableWrite writerRunnable = new ZPIAppLoggerRunnableWrite(notFinishedWriterJob);
                    ThreadGroup newJobThreadGroup = new ThreadGroup(currentWriterJob.getThreadGroupName());
                    Thread writeToHtmlByThread = new Thread(newJobThreadGroup, 
                            writerRunnable, 
                            currentWriterJob.getThreadName());
                    
                    writeToHtmlByThread.start();
                    waitForWriterJobsDone();
                /*} finally {
                    forRunWriterJoblck.unlock();
                }*/
            }
        }    
    }
    
    protected static void dumpAllStackToHtmlOld(){
        String instanceStartTimeWithMS = 
                ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        Path logForHtmlCurrentLogSubDir = 
                    ZPIAppFileOperationsSimple.getLogForHtmlCurrentLogSubDir(instanceStartTimeWithMS);
        
        //for get for css, js and more info about dirs
        ConcurrentSkipListMap<String, Path> listLogStorageFiles = 
                ZPIAppFileOperationsSimple.getNewHtmlLogStorageFileSystem(logForHtmlCurrentLogSubDir);
        listLogStorageFiles.put(ZPIAppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR, logForHtmlCurrentLogSubDir);
        
        
        for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
            Class<? extends Thread> aClass = elStTr.getKey().getClass();
            ArrayBlockingQueue<String> threadNameCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadNameCommandsOut(elStTr.getKey());
            tableCreateJobs(logForHtmlCurrentLogSubDir, threadNameCommandsOut);
            ArrayBlockingQueue<String> classCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(aClass);
            tableCreateJobs(logForHtmlCurrentLogSubDir, classCommandsOut);
            ArrayBlockingQueue<String> classGetDeclaredMethodsCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(aClass);
            tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredMethodsCommandsOut);
            ArrayBlockingQueue<String> classGetDeclaredFieldsCommandsOut = 
            ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(aClass);
            tableCreateJobs(logForHtmlCurrentLogSubDir, classGetDeclaredFieldsCommandsOut);
            for( StackTraceElement elementThreads : elStTr.getValue() ){
                elementThreads.getFileName();
            }
        }
        waitForWriterJobsDone();
        Path fileJsMenuPrefix = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX);
        ArrayBlockingQueue<String> linesForSaveJsMenu = ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu();
        anyFileCreateJobs(fileJsMenuPrefix, linesForSaveJsMenu);
        waitForWriterJobsDone();
        
        Path fileCssPrefix = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_HTML_CSS_PREFIX);
        ArrayBlockingQueue<String> linesForSaveCss = ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss();
        anyFileCreateJobs(fileCssPrefix, linesForSaveCss);
        waitForWriterJobsDone();
        
        
        Path fileIndexOfReport = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_INDEX_PREFIX);
        summaryReportJobs(logForHtmlCurrentLogSubDir, fileJsMenuPrefix.getFileName(), fileCssPrefix.getFileName(), fileIndexOfReport);
        
    }
    protected static void getThreadInfoToTable(){
        
    }
    /**
     * @todo recode for get dumpinfo to html for one thread
     * @param readedThread 
     */
    protected static void getThreadDebugInfoToHtml(Thread readedThread){
        
        StackTraceElement[] stackTrace = readedThread.getStackTrace();
        ThreadGroup threadGroup = readedThread.getThreadGroup();
        ClassLoader contextClassLoader = readedThread.getContextClassLoader();
        Class<? extends Thread> aClass = readedThread.getClass();
        
        String instanceStartTimeWithMS = 
                ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        Path logForHtmlCurrentLogSubDir = 
                    ZPIAppFileOperationsSimple.getLogForHtmlCurrentLogSubDir(instanceStartTimeWithMS);
        
        //for get for css, js and more info about dirs
        ConcurrentSkipListMap<String, Path> listLogStorageFiles = 
                ZPIAppFileOperationsSimple.getNewHtmlLogStorageFileSystem(logForHtmlCurrentLogSubDir);
        listLogStorageFiles.put(ZPIAppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR, logForHtmlCurrentLogSubDir);
        
        ArrayBlockingQueue<String> threadNameCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadNameCommandsOut(readedThread);
        tableClassJob(logForHtmlCurrentLogSubDir, threadNameCommandsOut);
        waitForWriterJobsDone();
        
        /*ArrayBlockingQueue<String> classCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassCommandsOut(aClass);
        tableCreateJobs(logForHtmlCurrentLogSubDir, classCommandsOut);
        waitForWriterJobsDone();*/
        
        /*ArrayBlockingQueue<String> classGetDeclaredMethodsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethodsCommandsOut(aClass);
        tableClassJob(logForHtmlCurrentLogSubDir, classGetDeclaredMethodsCommandsOut);
        waitForWriterJobsDone();
        
        ArrayBlockingQueue<String> classGetDeclaredFieldsCommandsOut = 
                ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(aClass);
        tableClassJob(logForHtmlCurrentLogSubDir, classGetDeclaredFieldsCommandsOut);
        waitForWriterJobsDone();*/
        
        /*Path fileJsMenuPrefix = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX);
        ArrayBlockingQueue<String> linesForSaveJsMenu = ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu();
        anyFileCreateJobs(fileJsMenuPrefix, linesForSaveJsMenu);
        waitForWriterJobsDone();
        
        Path fileCssPrefix = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_HTML_CSS_PREFIX);
        ArrayBlockingQueue<String> linesForSaveCss = ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss();
        anyFileCreateJobs(fileCssPrefix, linesForSaveCss);
        waitForWriterJobsDone();
        
        
        Path fileIndexOfReport = listLogStorageFiles.get(ZPIAppFileNamesConstants.LOG_INDEX_PREFIX);
        summaryReportJobs(logForHtmlCurrentLogSubDir, fileJsMenuPrefix.getFileName(), fileCssPrefix.getFileName(), fileIndexOfReport);*/
        
    }
    protected static void summaryReportJobs(Path currentLogSubDir, Path jsFile, Path cssFile, Path indexOfReport){
        
        ArrayList<Path> filesByMaskFromDir = ZPIAppFileOperationsSimple.getFilesByMaskFromDir(
            currentLogSubDir,
            "{" + ZPIAppFileNamesConstants.LOG_HTML_TABLE_PREFIX + "}*");
        
        ArrayBlockingQueue<ArrayBlockingQueue<String>> fromReadFile = new ArrayBlockingQueue<ArrayBlockingQueue<String>>(ZPIAppConstants.LOG_HTML_MESSAGES_QUEUE_SIZE);
        ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>> readerList = new ConcurrentSkipListMap<UUID, ArrayBlockingQueue<String>>();
        ConcurrentSkipListMap<UUID, ZPIAppLoggerStateReader> stateReaderList = new ConcurrentSkipListMap<UUID, ZPIAppLoggerStateReader>();
        for(Path tableElement : filesByMaskFromDir){
            ZPIAppLoggerStateReader initReaderNewJobLite = ZPIAppLoggerInfoToReport.initReaderNewJobLite(tableElement);
            initReaderNewJobLite.setAncorString(ZPIAppLoggerList.getAncorStructure(tableElement));
            ZPIAppLoggerController appLoggerControllerForRead = new ZPIAppLoggerController(readerList, initReaderNewJobLite);
            stateReaderList.put(initReaderNewJobLite.getID(), initReaderNewJobLite);
            
            ZPIAppLoggerRunnableRead readerRunnable = new ZPIAppLoggerRunnableRead(appLoggerControllerForRead);
            
            ThreadGroup newJobThreadGroupReader = new ThreadGroup(initReaderNewJobLite.getThreadGroupName());
            Thread readerThread = new Thread(newJobThreadGroupReader, 
                readerRunnable, 
                initReaderNewJobLite.getThreadName());
            
            readerThread.start();
        }
        waitForReadJobsDone();
        for( Map.Entry<UUID, ArrayBlockingQueue<String>> readedElement : readerList.entrySet() ){
            fromReadFile.add(readedElement.getValue());
            
            String strForOutToConsole = "-----------           "
                    + readedElement.getKey() 
                    + "               -----------"
                    + stateReaderList.get(readedElement.getKey()).getFromHTMLLogFileName().toString()       
                    + "-----------Lines in readed Array " 
                    + readedElement.getValue().size();
            
            ZPINcAppHelper.outToConsoleIfDevAndParamTrue(strForOutToConsole, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_LOGGER_READ_FROM_FILE_SIZE);
        }
        ArrayBlockingQueue<String> createLinesForIndex = ZPIAppObjectsInfoHelperHtml.buildLinesForIndex(
                readerList,
                fromReadFile, 
                jsFile, 
                cssFile, 
                filesByMaskFromDir,
                stateReaderList
        );
        anyFileCreateJobs(indexOfReport, createLinesForIndex);
        waitForWriterJobsDone();
        
    }
    protected static void waitForReadJobsDone(){
        Boolean readerInStack = Boolean.FALSE;
        String outStart = "-----------                          ----------------------Start wait Reader time " 
                + ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outStart, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_WAITER_START);
        do{
            readerInStack = Boolean.FALSE;
            for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
                String outAllStack = "Thread.id " + elStTr.getKey().getId() 
                        + " Thread.name " + elStTr.getKey().getName() 
                        + " Thread.state " + elStTr.getKey().getState();
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outAllStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_WAITER_ALL_STACK);
                if( elStTr.getKey().getName().contains("runReader")){
                    elStTr.getKey().getState();
                    readerInStack = Boolean.TRUE;
                }
                for( StackTraceElement elementThreads : elStTr.getValue() ){
                    String outAllPrintStack = "Stack element " 
                            + elementThreads.getFileName()
                            + " ( "
                            + elementThreads.getClassName()
                            + "."
                            + elementThreads.getMethodName() 
                            + " ["
                            + elementThreads.getLineNumber()
                            + "] isNative [" + String.valueOf(elementThreads.isNativeMethod())
                            + "] )";
                    ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outAllPrintStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_WAITER_ALL_PRINT_TRACE);
                }
            }
            for( StackTraceElement elementThreads : Thread.currentThread().getStackTrace() ){
                String outAllCurrentStack = "Stack element " 
                            + elementThreads.getFileName()
                            + " ( "
                            + elementThreads.getClassName()
                            + "."
                            + elementThreads.getMethodName() 
                            + " ["
                            + elementThreads.getLineNumber()
                            + "] isNative [" + String.valueOf(elementThreads.isNativeMethod()) 
                            + "] )";
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outAllCurrentStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_WAITER_CURRENT_STACK);
            }
        } while (readerInStack);
        String outStop = "-----------                          ----------------------Stop wait Reader time " 
                + ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outStop, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_READER_WAITER_STOP);
    }
    protected static void tableClassJob(
            Path logForHtmlCurrentLogSubDir,
            ArrayBlockingQueue<String> outputForWrite
    ){
        new ZPIAppLoggerController(logForHtmlCurrentLogSubDir, outputForWrite);
    }
    
    protected static void anyFileCreateJobs(
            Path anyFile,
            ArrayBlockingQueue<String> outputForWrite
    ){
        Path pathTable = anyFile;
        ZPIAppLoggerStateWriter initWriterNewJob = ZPIAppLoggerInfoToTables.initWriterNewJobLite(outputForWrite, pathTable);
        ZPIAppLoggerController appLoggerController = new ZPIAppLoggerController(initWriterNewJob);
        
        ZPIAppLoggerRunnableWrite writerRunnable = new ZPIAppLoggerRunnableWrite(appLoggerController);
        ThreadGroup newJobThreadGroup = new ThreadGroup(initWriterNewJob.getThreadGroupName());
        Thread writeToHtmlByThread = new Thread(newJobThreadGroup, 
                writerRunnable, 
                initWriterNewJob.getThreadName());
        writeToHtmlByThread.start();
    }
    
    protected static void tableCreateJobs(
            Path logForHtmlCurrentLogSubDir,
            ArrayBlockingQueue<String> outputForWrite
    ){
        Path pathTable = ZPIAppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        ZPIAppLoggerStateWriter initWriterNewJob = ZPIAppLoggerInfoToTables.initWriterNewJobLite(outputForWrite, pathTable);
        ZPIAppLoggerController appLoggerController = new ZPIAppLoggerController(initWriterNewJob);
        
        ZPIAppLoggerRunnableWrite writerRunnable = new ZPIAppLoggerRunnableWrite(appLoggerController);
        ThreadGroup newJobThreadGroup = new ThreadGroup(initWriterNewJob.getThreadGroupName());
        Thread writeToHtmlByThread = new Thread(newJobThreadGroup, 
                writerRunnable, 
                initWriterNewJob.getThreadName());
        writeToHtmlByThread.start();
    }
    protected static void waitForWriterJobsDone(){
        Boolean writerInStack = Boolean.FALSE;
        String outStart = "-----------                          ----------------------Start wait Writer time " 
                + ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outStart, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_START);
        do{
            writerInStack = Boolean.FALSE;
            for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
                String outAllStackThreadParam = "Thread.id " + elStTr.getKey().getId() 
                        + " Thread.name " + elStTr.getKey().getName() 
                        + " Thread.state " + elStTr.getKey().getState();
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outAllStackThreadParam, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_ALL_STACK);
                if( elStTr.getKey().getName().contains("runWriter")){
                    elStTr.getKey().getState();
                    String outFoundInStack = "-----------                          ----------------------found Writer at time " 
                            + ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
                    ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outFoundInStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_FOUNDED_IN_STACK);
                    try {
                        Thread.currentThread().sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    writerInStack = Boolean.TRUE;
                }
                for( StackTraceElement elementThreads : elStTr.getValue() ){
                    String outPrintTraceStack = "Stack element " 
                            + elementThreads.getFileName()
                            + " ( "
                            + elementThreads.getClassName()
                            + "."
                            + elementThreads.getMethodName() 
                            + " ["
                            + elementThreads.getLineNumber()
                            + "] isNative [" + String.valueOf(elementThreads.isNativeMethod())
                            + "] )";
                    ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outPrintTraceStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_ALL_PRINT_TRACE);
                }
            }
            for( StackTraceElement elementThreads : Thread.currentThread().getStackTrace() ){
                String outCurrentStack = "Stack element " 
                            + elementThreads.getFileName()
                            + " ( "
                            + elementThreads.getClassName()
                            + "."
                            + elementThreads.getMethodName() 
                            + " ["
                            + elementThreads.getLineNumber()
                            + "] isNative [" + String.valueOf(elementThreads.isNativeMethod())
                            + "] )";
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outCurrentStack, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_CURRENT_STACK);
            }
        } while (writerInStack);
        String outStop = "-----------                          ----------------------Stop wait Writer time " 
                + ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outStop, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_WAITER_STOP);
    }
    protected static void getThreadDebugInfoToHtmlVpre(Thread readedThread){
        //@todo check sizes in add content for bus
        //@todo into currentjob methods pull data in new busses
        ZPIAppLoggerList loggerHtml = new ZPIAppLoggerList();
        System.out.println("+|0000000|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++new ZPIAppLoggerList(); ");
        ZPIAppObjectsInfoHelperClasses.getThreadName(readedThread, loggerHtml.getLoggerBus().getCommandsOutPut());
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(loggerHtml.getLoggerBus().getCommandsOutPut(),loggerHtml.getLoggerBus().getListForRunnableLogStrs());
        
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        System.out.println("+|0000001|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++ZPIAppObjectsInfoHelperClasses.getThreadName(); ");
        ZPIAppObjectsInfoHelperClasses.getThreadClass(readedThread, loggerHtml.getLoggerBus().getCommandsOutPut());
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(loggerHtml.getLoggerBus().getCommandsOutPut(),loggerHtml.getLoggerBus().getListForRunnableLogStrs());
        
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        System.out.println("+|0000002|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++ZPIAppObjectsInfoHelperClasses.getThreadClass(); ");
        ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethods(readedThread, loggerHtml.getLoggerBus().getCommandsOutPut());
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(loggerHtml.getLoggerBus().getCommandsOutPut(),loggerHtml.getLoggerBus().getListForRunnableLogStrs());
        
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        
        //@todo all near job need do with reader jobs algoritms for relese templates in next iterations of dev
        
        System.out.println("+|0000003|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethods(); ");
        loggerHtml.getLoggerBus().addAllStringsToRunnableBus(ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu());
        loggerHtml.setTrueNeedForSaveJs();
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        System.out.println("+|0000004|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++loggerHtml.setTrueNeedForSaveJs(); ");
        loggerHtml.setFalseNeedForSaveJs();
        
        loggerHtml.getLoggerBus().addAllStringsToRunnableBus(ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss());
        loggerHtml.setTrueNeedForSaveCss();
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        System.out.println("+|0000005|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++loggerHtml.setTrueNeedForSaveCss(); ");
        loggerHtml.setFalseNeedForSaveCss();
        
        ArrayList<Path> filesByMaskFromDir = new ArrayList<Path>();
        System.out.println("+|0000005-1|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++loggerHtml.getLoggerBus().getLogHtmlListTableFiles(); ");
        for( Path fileForRead : loggerHtml.getLoggerBus().getLogHtmlListTableFiles() ){
            filesByMaskFromDir.add(fileForRead);
            System.out.println("+|" + fileForRead.toString());
        }
        //@todo fix it
        Path fileJsMenuPrefix = loggerHtml.getLoggerBus().getLogFilesListElement(ZPIAppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX).getFileName();
        Path fileCssPrefix = loggerHtml.getLoggerBus().getLogFilesListElement(ZPIAppFileNamesConstants.LOG_HTML_CSS_PREFIX).getFileName();
        
        ZPIAppObjectsInfoHelperHtml.getLinesForTopSaveIndex(loggerHtml.getLoggerBus().getListForRunnableLogStrs(), fileJsMenuPrefix, fileCssPrefix, filesByMaskFromDir);
        
        System.out.println("+|0000006|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++ZPIAppObjectsInfoHelperHtml.getLinesForTopSaveIndex(); ");
        loggerHtml.doReadFromLogHtmlListOfTables();
        System.out.println("+|0000007|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++loggerHtml.doReadFromLogHtmlListOfTables(); ");
        //loggerHtml.addReadedTablesIntoList();
        ZPIAppObjectsInfoHelperHtml.getLinesForBottomSaveIndex(loggerHtml.getLoggerBus().getListForRunnableLogStrs());
        System.out.println("+|0000008|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++ZPIAppObjectsInfoHelperHtml.getLinesForBottomSaveIndex(); ");
        loggerHtml.setTrueNeedForSaveIndexHtml();
        loggerHtml.doWriteToLogHtmlCurrentFile();
        loggerHtml.waitForPrevJobDoneForWriter();
        System.out.println("+|0000009|+|AppObjectsInfo||||||||+++++++++++|||||||||+++++++++++loggerHtml.doWriteToLogHtmlCurrentFile(); ");
        loggerHtml.setFalseNeedForSaveIndexHtml();
        
        for( Map.Entry<Thread, StackTraceElement[]> elStTr : Thread.getAllStackTraces().entrySet() ){
            System.out.println("Thread.id " + elStTr.getKey().getId() + " Thread.name " + elStTr.getKey().getName());
            for( StackTraceElement elementThreads : elStTr.getValue() ){
                System.out.println("Stack element " + elementThreads.getFileName());
            }
        }
        for( StackTraceElement elementThreads : Thread.currentThread().getStackTrace() ){
            System.out.println("Stack element " + elementThreads.getFileName());
        }
    }

    protected static void getThreadDebugInfoToHtmlOld(Thread readedThread){
        
        String nowTimeStringWithMS = 
                ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
        Path logForHtmlCurrentLogSubDir = 
                ZPIAppFileOperationsSimple.getLogForHtmlCurrentLogSubDir(nowTimeStringWithMS);
        ConcurrentSkipListMap<String, Path> newLogFileInLogHTML = 
                ZPIAppFileOperationsSimple.getNewLogFileInLogHTML(logForHtmlCurrentLogSubDir);
        newLogFileInLogHTML.put(ZPIAppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR, logForHtmlCurrentLogSubDir);
        
        
        Integer messagesQueueSize = ZPIAppConstants.LOG_HTML_MESSAGES_QUEUE_SIZE;
        ArrayBlockingQueue<ArrayBlockingQueue<String>> commandsOutPut = new ArrayBlockingQueue<ArrayBlockingQueue<String>>(messagesQueueSize);
        ZPIAppObjectsInfoHelperClasses.getInitBusInfo(commandsOutPut);
        
        ArrayBlockingQueue<String> listForRunnableLogStrs = new ArrayBlockingQueue<String>(messagesQueueSize);
        
        TreeMap<Integer, String> listForLogStrs = new TreeMap<Integer, String>();
        
        Path newLogHtmlTableFile = newLogFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_TABLE_PREFIX);
        
        ZPIAppLoggerToHTMLRunnable loggerToHtml = new ZPIAppLoggerToHTMLRunnable(
                listForRunnableLogStrs,
                newLogHtmlTableFile
        );
        
        
        //@todo chaos to system out
        
        
        int indexLinesToFile = 0;
        
        //rel
        ZPIAppObjectsInfoHelperClasses.getThreadName(readedThread, commandsOutPut);
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(commandsOutPut,listForRunnableLogStrs);
        
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        
        newLogHtmlTableFile = ZPIAppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        //rel
        ZPIAppObjectsInfoHelperClasses.getThreadClass(readedThread, commandsOutPut);
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(commandsOutPut,listForRunnableLogStrs);
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        
        newLogHtmlTableFile = ZPIAppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        //rel
        ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredMethods(readedThread, commandsOutPut);
        ZPIAppObjectsInfoHelperHtml.commandOutPutBusToHtml(commandsOutPut,listForRunnableLogStrs);
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,
                "[NAME]" + readedThread.getName()
                + "[CLASS]" + readedThread.getClass().getCanonicalName()
                + "[isInstanceOf(ZPIAppThWorkDirListRun.class)]" 
                + readedThread.getClass().isInstance(ZPIAppThWorkDirListRun.class));
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"* * * [Thread.getContextClassLoader()] * * *");
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"[getContextClassLoader()]" 
                + readedThread.getContextClassLoader().getClass().getCanonicalName());
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"[getUncaughtExceptionHandler()]" 
                + readedThread.getUncaughtExceptionHandler().getClass().getCanonicalName());
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"* * * [Thread.getClass().getClasses()] * * *");
        String strCanonicalNames = "";
        int idxId = 0;
        Class<?>[] classes = readedThread.getClass().getClasses();
        for( Class<?> str : classes ){
            strCanonicalNames = strCanonicalNames + "[" + idxId + "]" + str.getCanonicalName();
            idxId++;
        }
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"[getClasses()]" 
                + strCanonicalNames);
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"* * * [Thread.getStackTrace()] * * *");
        strCanonicalNames = "";
        idxId = 0;
        StackTraceElement[] traceElements = readedThread.getStackTrace();
        
        for(StackTraceElement traceElement : traceElements ){
            strCanonicalNames = strCanonicalNames + "[" + idxId + "]getClass().getCanonicalName()]" + traceElement.getClass().getCanonicalName();
            
            idxId++;
        }
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"[getStackTrace().length]"
                + traceElements.length
                + "[getStackTrace()]" 
                + strCanonicalNames);
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"* * * [Thread.getContextClassLoader().getClass().getClasses()] * * *");
        Class<?>[] classesContextClassLoader = readedThread.getContextClassLoader().getClass().getClasses();
        for( Class<?> str : classesContextClassLoader ){
            strCanonicalNames = strCanonicalNames + "[" + idxId + "]" + str.getCanonicalName();
            idxId++;
        }
        
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"[getContextClassLoader().getClass().getClasses()]" 
                + strCanonicalNames);
        
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,"* * * [Thread.getName() | .getClass() and some methods] * * *");
        indexLinesToFile++;
        listForLogStrs.put(indexLinesToFile,
                "[NAME]" + readedThread.getName()
                + "[CLASS][getName]" + readedThread.getClass().getName()
                + "[CLASS][getCanonicalName]" + readedThread.getClass().getCanonicalName()
                + "[CLASS][getSimpleName]" + readedThread.getClass().getSimpleName()
                + "[CLASS][getTypeName]" + readedThread.getClass().getTypeName()
                + "[CLASS][toGenericString]" + readedThread.getClass().toGenericString()
                + "[CLASS][toString]" + readedThread.getClass().toString()
                        
                + "[CLASS][getClass().isAssignableFrom(ZPIAppThWorkDirListRun.class)]"
                + readedThread.getClass().isAssignableFrom(ZPIAppThWorkDirListRun.class)
                + "[CLASS][getClass().isInstance(ZPIAppThWorkDirListRun.class)]"
                        
                + readedThread.getClass().isInstance(ZPIAppThWorkDirListRun.class));
        indexLinesToFile++;
        newLogHtmlTableFile = ZPIAppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        ZPIAppObjectsInfoHelperHtml.getStringListForSaveTable(listForRunnableLogStrs, listForLogStrs, "readedThread.getStackTrace()");
        System.out.println("for first record " + listForRunnableLogStrs.size() + "file name" + newLogHtmlTableFile.toString());
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        listForLogStrs.clear();        
        
        
        //************** ************** ************** ************** **************
        listForLogStrs = new TreeMap<Integer, String>();
        //listForLogStrs.clear();
        
        
        // all methods from Thread objects
        
        //readedThread.getClass().asSubclass(clazz);//Class<? extends U> 
        //readedThread.getClass().cast(idxId);//? extends Thread
        boolean desiredAssertionStatus = readedThread.getClass().desiredAssertionStatus();
        //boolean equals = readedThread.getClass().equals(idxId);
        indexLinesToFile = 0;
        AnnotatedType[] annotatedInterfaces = readedThread.getClass().getAnnotatedInterfaces();
        for(AnnotatedType element : annotatedInterfaces){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getAnnotatedInterfaces()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        
        AnnotatedType annotatedSuperclass = readedThread.getClass().getAnnotatedSuperclass();
        //readedThread.getClass().getAnnotation(annotationClass); //A
        Annotation[] annotations = readedThread.getClass().getAnnotations();
        for(Annotation element : annotations){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getAnnotations()[CLASS][getCanonicalName]" + element.annotationType().getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getAnnotationsByType(annotationClass); //A[]
        String canonicalName = readedThread.getClass().getCanonicalName();
        
        //readedThread.getClass().getClass().getClass();//Class<?>
        //readedThread.getClass().getClassLoader();//ClassLoader
        
        
        
        Class<?>[] classes1 = readedThread.getClass().getClasses();
        for(Class element : classes1){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getClasses()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getComponentType();//Class<?>
        
        //readedThread.getClass().getConstructor(classes);//Constructor <? extends Thread>
        
        Constructor<?>[] constructors = readedThread.getClass().getConstructors();
        for(Constructor element : constructors){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getConstructors()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getDeclaredAnnotation(annotationClass);//A
        
        Annotation[] declaredAnnotations = readedThread.getClass().getDeclaredAnnotations();
        for(Annotation element : declaredAnnotations){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,"getDeclaredAnnotations()[CLASS]"
                + "[toString()]" + element.toString()
                + "[annotationType().getCanonicalName()]" + element.annotationType().getCanonicalName()
                + "[getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getDeclaredAnnotationsByType(annotationClass);//A[]
        
        Class<?>[] declaredClasses = readedThread.getClass().getDeclaredClasses();
        for(Class element : declaredClasses){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,"getDeclaredClasses()[CLASS]"
                + "[getName()]" + element.getName()
                + "[getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getDeclaredConstructor(declaredClasses);//Constructor <? extends Thread>
        
        Constructor<?>[] declaredConstructors = readedThread.getClass().getDeclaredConstructors();
        for(Constructor element : declaredConstructors){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,"getDeclaredConstructors()[CLASS]"
                + "[getName()]" + element.getName()
                + "[getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getDeclaredField(canonicalName);//Field
        
        Field[] declaredFields = readedThread.getClass().getDeclaredFields();
        for(Field element : declaredFields){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,"getDeclaredFields()[CLASS]"
                + "[getName()]" + element.getName()
                + "[getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getDeclaredMethod(canonicalName, declaredClasses);//Method
        
        Method[] declaredMethods = readedThread.getClass().getDeclaredMethods();
        for(Method element : declaredMethods){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,"getDeclaredMethods()[CLASS]"
                + "[getName()]" + element.getName()
                + "[getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        Class<?> declaringClass = readedThread.getClass().getDeclaringClass(); //Class<?>
        Class<?> enclosingClass = readedThread.getClass().getEnclosingClass(); //Class<?>
        Constructor<?> enclosingConstructor = readedThread.getClass().getEnclosingConstructor(); //Constructor<?>
        Method enclosingMethod = readedThread.getClass().getEnclosingMethod(); //Method
        //readedThread.getClass().getEnumConstants();//? extends Thread[]
        
        
        Field[] fields = readedThread.getClass().getFields();
        for(Field element : fields){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getFields()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        
        Type[] genericInterfaces = readedThread.getClass().getGenericInterfaces();
        for(Type element : genericInterfaces){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getGenericInterfaces()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        Type genericSuperclass = readedThread.getClass().getGenericSuperclass(); //Type
        
        Class<?>[] interfaces = readedThread.getClass().getInterfaces();
        for(Class element : interfaces){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getInterfaces()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        //readedThread.getClass().getMethod(canonicalName, interfaces);//Method
        
        Method[] methods = readedThread.getClass().getMethods();
        for(Method element : methods){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getMethods()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        
        
        int modifiers = readedThread.getClass().getModifiers();
        String name = readedThread.getClass().getName();
        Package aPackage = readedThread.getClass().getPackage();
        ProtectionDomain protectionDomain = readedThread.getClass().getProtectionDomain();
        readedThread.getClass().getResource(canonicalName);//URL
        
        readedThread.getClass().getResourceAsStream(name);//InputStream
        
        
        
        Object[] signers = readedThread.getClass().getSigners();
        if( signers != null  ){
            for(Object element : signers){
                if( element != null ){
                    indexLinesToFile++;
                    listForLogStrs.put(indexLinesToFile,
                        "getSigners()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
                }
            }
        }
        
        
        String simpleName = readedThread.getClass().getSimpleName();
        Class<?> superclass = readedThread.getClass().getSuperclass();
        String typeName = readedThread.getClass().getTypeName();
        
        
        
        TypeVariable<? extends Class<? extends Thread>>[] typeParameters = readedThread.getClass().getTypeParameters();
        for(TypeVariable<? extends Class<? extends Thread>> element : typeParameters){
            indexLinesToFile++;
            listForLogStrs.put(indexLinesToFile,
                "getTypeParameters()[CLASS][getCanonicalName]" + element.getClass().getCanonicalName());
        }
        
        readedThread.getClass().hashCode();
        boolean annotation = readedThread.getClass().isAnnotation();
        //readedThread.getClass().isAnnotationPresent(annotationClass);//Boolean
        boolean anonymousClass = readedThread.getClass().isAnonymousClass();
        boolean array = readedThread.getClass().isArray();
        //readedThread.getClass().isAssignableFrom(cls);
        boolean aEnum = readedThread.getClass().isEnum();
        boolean aInterface = readedThread.getClass().isInterface();
        boolean localClass = readedThread.getClass().isLocalClass();
        boolean memberClass = readedThread.getClass().isMemberClass();
        boolean primitive = readedThread.getClass().isPrimitive();
        boolean synthetic = readedThread.getClass().isSynthetic();
        try{
            Thread newInstance = readedThread.getClass().newInstance(); //? extends Thread
        } catch(IllegalAccessException ex){
            ex.getMessage();
            ex.printStackTrace();
        } catch(InstantiationException ex){
            ex.getMessage();
            ex.printStackTrace();
        }
        readedThread.getClass().toGenericString();
        readedThread.getClass().toString();
        
        listForRunnableLogStrs.clear();
        // end for first block lines into recording list
        newLogHtmlTableFile = ZPIAppFileOperationsSimple.getNewLogHtmlTableFile(logForHtmlCurrentLogSubDir);
        
        ZPIAppObjectsInfoHelperHtml.getStringListForSaveTable(listForRunnableLogStrs, listForLogStrs, "readedThread.getClass().getTypeParameters()");
        listForLogStrs.clear();
        listForLogStrs = new TreeMap<Integer, String>();
        System.out.println("for second record " + listForRunnableLogStrs.size() + newLogHtmlTableFile.toString());
        //rel
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        // end for write first block lines into file
        
        newLogHtmlTableFile = newLogFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX);
        listForRunnableLogStrs.clear();
        
        
        ArrayBlockingQueue<String> linesForSaveJsMenu = ZPIAppObjectsInfoHelperHtml.getLinesForSaveJsMenu();
        String pollFirstForSaveJsMenu = "";
            do{
                pollFirstForSaveJsMenu = linesForSaveJsMenu.poll();
                if( pollFirstForSaveJsMenu != null ){
                    listForRunnableLogStrs.add(pollFirstForSaveJsMenu);
                }
            }while( !linesForSaveJsMenu.isEmpty() );
        
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        //********* ************* ************ ************** ************** ***************
        newLogHtmlTableFile = newLogFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_CSS_PREFIX);
        listForRunnableLogStrs.clear();
        //rel
        ArrayBlockingQueue<String> linesForSaveCss = ZPIAppObjectsInfoHelperHtml.getLinesForSaveCss();
        String pollFirstForSaveCss = "";
        do{
            pollFirstForSaveCss = linesForSaveCss.poll();
            if( pollFirstForSaveCss != null ){
                listForRunnableLogStrs.add(pollFirstForSaveCss);
            }
        }while( !linesForSaveCss.isEmpty() );
        
        writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
        //********* ************* ************ ************** ************** ***************
        
        ArrayBlockingQueue<String> generatedLinesForIndexFile = new ArrayBlockingQueue<String>(messagesQueueSize);
        /*ConcurrentSkipListMap<Integer, String> generatedLinesForIndexFile =
                new ConcurrentSkipListMap<Integer, String>();*/
        generateIndexFile(generatedLinesForIndexFile, newLogFileInLogHTML);
        // make index file
        if( generatedLinesForIndexFile.size() > 0 ){
            newLogHtmlTableFile = newLogFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_INDEX_PREFIX);

            listForRunnableLogStrs.clear();

            String pollFirstEntryIndexFile = "";
            do{
                pollFirstEntryIndexFile = generatedLinesForIndexFile.poll();
                if( pollFirstEntryIndexFile != null ){
                    listForRunnableLogStrs.add(pollFirstEntryIndexFile);
                }
            }while( !generatedLinesForIndexFile.isEmpty() );
            
            System.out.println(" for index record " + listForRunnableLogStrs.size() + " open in browser " + newLogHtmlTableFile.toString());
            writeLinesToFileByRunnable(listForRunnableLogStrs, loggerToHtml, newLogHtmlTableFile);
            
        }
        
    }
    protected static void writeLinesToFileByRunnable(ArrayBlockingQueue<String> listStrForLog,
            ZPIAppLoggerToHTMLRunnable writerToHtmlRunnable,
            Path fileForWrite){
        
        if (listStrForLog.size() > 0){
            String nowTimeStringWithMS = 
                    ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
            ThreadGroup newJobThreadGroup = new ThreadGroup("TmpGroup-" + nowTimeStringWithMS);
            
            if( !writerToHtmlRunnable.isNewRunner() ){
                //Check for old job is done
                try{
                    while( !writerToHtmlRunnable.isJobDone() ){
                        Thread curThr = Thread.currentThread();
                        curThr.sleep(50);
                    }
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                } catch(SecurityException ex){
                    ex.printStackTrace();
                }
            }
            
            
            while( !writerToHtmlRunnable.isLogFileNameChanged() ){
                writerToHtmlRunnable.setNewLogFileName(fileForWrite);
            }
            
            
            Thread writeToHtmlByThread = new Thread(newJobThreadGroup, writerToHtmlRunnable, "writerToHtml-" + nowTimeStringWithMS);
            System.out.println("State writer " + writeToHtmlByThread.getState().name());
            //Thread writeToHtmlByThread = new Thread(writerToHtmlRunnable, "writerToHtml-" + nowTimeStringWithMS);
            writeToHtmlByThread.start();
            System.out.println("State writer " + writeToHtmlByThread.getState().name());
            //Check for now job is done
            try{
                writeToHtmlByThread.join();
                while( !writerToHtmlRunnable.isJobDone() ){
                    Thread curThr = Thread.currentThread();
                    curThr.sleep(50);
                    System.out.println("State writer " + writeToHtmlByThread.getState().name());
                }
            } catch(InterruptedException ex){
                ex.printStackTrace();
            } catch(SecurityException ex){
                ex.printStackTrace();
            }
            System.out.println("State writer " + writeToHtmlByThread.getState().name());
            //@todo destroy for threadgroups...

            
        }
    }
    
    protected static void readLinesFromFileByRunnable(ArrayBlockingQueue<String> listStrFromFile,
            ZPIAppLoggerFromHTMLRunnable readerFromHtmlFile,
            Path fileForWrite){
        
        if (listStrFromFile.size() == 0){
            String nowTimeStringWithMS = 
                    ZPIAppFileOperationsSimple.getNowTimeStringWithMS();
            //@todo check stack trace to see for runned threads by his names and classes
            ThreadGroup newJobThreadGroup = new ThreadGroup("TmpGroupReadFile-" + nowTimeStringWithMS);
            if( !readerFromHtmlFile.isNewRunner() ){
                //Check for old job is done
                try{
                    while( !readerFromHtmlFile.isJobDone() ){
                        Thread curThr = Thread.currentThread();
                        curThr.sleep(50);
                    }
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                } catch(SecurityException ex){
                    ex.printStackTrace();
                }
            }
            
            while( !readerFromHtmlFile.isLogFileNameChanged() ){
                readerFromHtmlFile.setNewLogFileName(fileForWrite);
            }
            
            
            Thread readFromHtmlByThread = new Thread(newJobThreadGroup, readerFromHtmlFile, "readerFromHtml-" + nowTimeStringWithMS);
            System.out.println("State reader " + readFromHtmlByThread.getState().name());
            
            readFromHtmlByThread.start();
            System.out.println("State reader " + readFromHtmlByThread.getState().name());
            //Check for now job is done
            try{
                readFromHtmlByThread.join();
                while( !readerFromHtmlFile.isJobDone() ){
                    Thread curThr = Thread.currentThread();
                    curThr.sleep(50);
                    //curThr.notifyAll();
                    System.out.println("State reader " + readFromHtmlByThread.getState().name());
                }
            } catch(InterruptedException ex){
                ex.printStackTrace();
            } catch(SecurityException ex){
                ex.printStackTrace();
            }
            System.out.println("State reader " + readFromHtmlByThread.getState().name());
            //@todo destroy for threadgroups... see core threadgroup.destroy

        }
    }
    
    protected static void generateIndexFile(
            ArrayBlockingQueue<String> returnedLinesForIndexFile,
            ConcurrentSkipListMap<String, Path> listOfFileInLogHTML){
        
        Path dirForRead = listOfFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_KEY_FOR_CURRENT_SUB_DIR);
        
        //@todo in the loggerList (or Rule... xz) need flag for stop run reader if not set filesByMaskFromDir
        
        ArrayList<Path> filesByMaskFromDir = ZPIAppFileOperationsSimple.getFilesByMaskFromDir(
                dirForRead,
                "{" + ZPIAppFileNamesConstants.LOG_HTML_TABLE_PREFIX + "}*");
        Integer messagesQueueSize = 10000;
        ArrayBlockingQueue<String> readedLinesFromLogHTML = new ArrayBlockingQueue<String>(messagesQueueSize);

        if( filesByMaskFromDir.size() > 0 ){
            Path forFirstRead = filesByMaskFromDir.get(0);
            ZPIAppLoggerFromHTMLRunnable readerFromHtmlFile = new ZPIAppLoggerFromHTMLRunnable(
                    readedLinesFromLogHTML,
                    forFirstRead);
            Path fileJsMenuPrefix = listOfFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_JS_MENU_PREFIX).getFileName();
            Path fileCssPrefix = listOfFileInLogHTML.get(ZPIAppFileNamesConstants.LOG_HTML_CSS_PREFIX).getFileName();
            ZPIAppObjectsInfoHelperHtml.getLinesForTopSaveIndex(returnedLinesForIndexFile, fileJsMenuPrefix, fileCssPrefix, filesByMaskFromDir);        
            for( Path fileForRead : filesByMaskFromDir ){
                String strForAncor = "<p><a name=\"" + fileForRead.getFileName().toString().split("\\.")[0] + "\">"
                        + fileForRead.toString() + "</a></p>";
                returnedLinesForIndexFile.add(strForAncor);
                readLinesFromFileByRunnable(readedLinesFromLogHTML, readerFromHtmlFile, fileForRead);
                
                readedLinesFromLogHTML.add(strForAncor);
                returnedLinesForIndexFile.addAll(readedLinesFromLogHTML);
                readedLinesFromLogHTML.clear();
            }
            //indexOfLines++;
            ZPIAppObjectsInfoHelperHtml.getLinesForBottomSaveIndex(returnedLinesForIndexFile);
        }
    }
}
