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
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerToTextRunnable implements Runnable {
    private ArrayBlockingQueue<String> messagesQueueForLogging;
    //private Integer linesCount;
    private Path newLogFile;

    public ZPIAppLoggerToTextRunnable(ArrayBlockingQueue<String> messagesQueueOuter) {
        super();
        messagesQueueForLogging = messagesQueueOuter;
        //linesCount = ZPIAppConstants.LOG_LINES_COUNT;
        newLogFile = ZPIAppFileOperationsSimple.getNewLogFile();
        String threadInfoToString = "Create logger" + ZPINcAppHelper.getThreadInfoToString(Thread.currentThread());
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(threadInfoToString, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_CREATE_TEXT_LOGGER);
    }
    
    @Override
    public void run() {
            //all variables in ThreadLocal...
            Integer linesCount = 0;
            //@todo rebuild for limit str count in files, add log rotate in arch folder, create zip for old logs
            ArrayList<String> strForLog = new ArrayList<String>();
            /*while ( !messagesQueueForLogging.isEmpty() ) {            
                strForLog.add(messagesQueueForLogging.poll());
                linesCount++;
            }*/


            ArrayList<String> lines = new ArrayList<>();
            try {
                lines.addAll(Files.readAllLines(newLogFile, Charset.forName("UTF-8")));
            } catch (IOException ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
            //recode for poll from queue, compare with log limit and write, or poll from queue count for log limit, write, got new name and write
            //lines.addAll(strForLog);
            if( (lines.size() + 1) > ZPIAppConstants.LOG_LINES_COUNT ){
                newLogFile = ZPIAppFileOperationsSimple.getNewLogFile();
                lines.clear();
            }
            
            String poll;
            do{
                poll = messagesQueueForLogging.poll();
                if( poll != null ){
                    if( !poll.isEmpty() ){
                        strForLog.add(poll);
                    }
                }
            }while( !messagesQueueForLogging.isEmpty() );
            
            if( (lines.size() + strForLog.size()) < (ZPIAppConstants.LOG_LINES_COUNT + 1) ){
                lines.addAll(strForLog);
                try {
                    Files.write(newLogFile, lines, Charset.forName("UTF-8"));
                } catch (IOException ex) {
                    ex.getMessage();
                    ex.printStackTrace();
                }
                lines.clear();
            } else {
               do{
                   
                    if( lines.size() < ZPIAppConstants.LOG_LINES_COUNT 
                        && !strForLog.isEmpty() ){
                        lines.add(strForLog.remove(0));
                        
                    } else {
                        newLogFile = ZPIAppFileOperationsSimple.getNewLogFile();
                        try {
                            Files.write(newLogFile, lines, Charset.forName("UTF-8"));
                        } catch (IOException ex) {
                            ex.getMessage();
                            ex.printStackTrace();
                        }
                        lines.clear();
                        
                    }
                    if( !lines.isEmpty()
                        && strForLog.isEmpty() ){
                        newLogFile = ZPIAppFileOperationsSimple.getNewLogFile();
                        try {
                            Files.write(newLogFile, lines, Charset.forName("UTF-8"));
                        } catch (IOException ex) {
                            ex.getMessage();
                            ex.printStackTrace();
                        }
                        lines.clear();
                    }
                }while( !strForLog.isEmpty() );
            }

    }
}
