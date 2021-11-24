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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerRunnableWrite implements Runnable {
    private AppLoggerController busManager;
    public ZPIAppLoggerRunnableWrite(
            AppLoggerController outerManagerForThis
    ) {
        super();
        
        this.busManager = outerManagerForThis;
        this.busManager.currentWriterJob().setTrueToHTMLNewRunner();
        
        
        String outCreate = "*** ||| *** ||| *** create log writer *** ||| *** ||| ***";
        NcAppHelper.outToConsoleIfDevAndParamTrue(outCreate, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_RUNNABLE_CREATE);
    }
    
    @Override
    public void run() {
        ReentrantLock forRunnerWriterJoblck = new ReentrantLock();
        forRunnerWriterJoblck.lock();
        try{
            AppLoggerController managerForThis = busManager;
            String outGetNewJob = "managerForThis.getIdJob().toString() " 
                    + managerForThis.getIdJob().toString();
            
            NcAppHelper.outToConsoleIfDevAndParamTrue(outGetNewJob, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_RUNNABLE_GET_NEW_JOB);
            
            if( managerForThis != null ){
                String outGetNotNullNewJob = "managerForThis.getIdJob().toString() "
                    + managerForThis.getIdJob().toString()
                    + " managerForThis.isReaderJob() "
                    + managerForThis.isReaderJob()
                    + " managerForThis.currentWriterJob().getThreadName() "
                    + managerForThis.currentWriterJob().getThreadName()
                    + " managerForThis.currentWriterJob().getThreadGroupName() "
                    + managerForThis.currentWriterJob().getThreadGroupName()
                    + " managerForThis.currentWriterJob().getID().toString() "
                    + managerForThis.currentWriterJob().getID().toString()
                    + " managerForThis.currentWriterJob().getPartLinesForWrite().size() "        
                    + managerForThis.currentWriterJob().getPartLinesForWrite().size()
                    + " managerForThis.currentWriterJob().isToHTMLJobDone() "
                    + managerForThis.currentWriterJob().isToHTMLJobDone()
                    + " managerForThis.currentWriterJob().isBlankObject() "
                    + managerForThis.currentWriterJob().isBlankObject();
                NcAppHelper.outToConsoleIfDevAndParamTrue(outGetNotNullNewJob, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_RUNNABLE_VIEW_NEW_JOB_PARAM);
                
                AppLoggerStateWriter currentJob = managerForThis.currentWriterJob();
                if( !currentJob.isToHTMLJobDone() ){
                    ArrayList<String> forRecord = 
                            AppObjectsBusHelper.cleanBusArrayBlockingToArrayString(
                                    currentJob.getPartLinesForWrite());

                    String outViewSizeNewJob = "report writerRunnable size for " 
                            + forRecord.size()
                            + " write to "
                            + currentJob.getToHTMLLogFileName().toString();
                    NcAppHelper.outToConsoleIfDevAndParamTrue(outViewSizeNewJob, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_JOB_WRITER_RUNNABLE_VIEW_SIZE_JOB_PARAM);

                    //try {
                        if( currentJob.isToHtmlFileNameSet() ){
                            AppFileOperationsSimple.writeToFile(currentJob.getToHTMLLogFileName(), forRecord);
                            //Files.write(currentJob.getToHTMLLogFileName(), forRecord, Charset.forName("UTF-8"));
                            currentJob.setFalseToHTMLLogFileNameChanged();
                        }
                    /*} catch (IOException ex) {
                        ex.getMessage();
                        ex.printStackTrace();
                    }*/
                    //forRecord.clear();
                }

                currentJob.setFalseToHTMLNewRunner();
                currentJob.setTrueToHTMLJobDone();
            }
        } finally {
            forRunnerWriterJoblck.unlock();
            
        }
    }
}
