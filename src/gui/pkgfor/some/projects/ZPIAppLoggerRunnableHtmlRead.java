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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppLoggerRunnableHtmlRead implements Runnable {
    
    private AppLoggerRule managerForThis;
    public ZPIAppLoggerRunnableHtmlRead(AppLoggerRule outerManagerForThis){
        super();
    this.managerForThis = outerManagerForThis;
    //this.managerForThis..setTrueFromHTMLNewRunner();
        String threadInfoToString = NcAppHelper.getThreadInfoToString(Thread.currentThread());
        System.out.println("*** ||| *** ||| *** create log reader *** ||| *** ||| ***" + threadInfoToString);
    }
    
    @Override
    public void run() {
        
        AppLoggerStateReader currentJob = this.managerForThis.currentReaderJob();
        if( !currentJob.isBlankObject() ){
            if( !currentJob.isFromHTMLJobDone() ){
                Path fileForReadInThisJob = currentJob.getFromHTMLLogFileName();
                //currentJob.setFalseFromHTMLJobDone();
                System.out.println("_|_|_|_|_|_ AppLoggerRunnableHtmlRead.run() fromHTMLLogFileName " 
                                + fileForReadInThisJob.toString() 
                                + " _|_|_|_|_|_"
                                + " start for read file");
                ArrayBlockingQueue<String> readedLines = new ArrayBlockingQueue<String>(AppConstants.LOG_HTML_MESSAGES_QUEUE_SIZE);
                String ancorString = currentJob.getAncorString();
                if( ancorString.length() > 17 ){
                    readedLines.add(ancorString);
                }
                try {
                    readedLines.addAll(Files.readAllLines(fileForReadInThisJob, Charset.forName("UTF-8")));
                    if( readedLines != null){
                        System.out.println("_|_|_|_|_|_ AppLoggerRunnableHtmlRead.run() fromHTMLLogFileName " 
                                + fileForReadInThisJob.toString() 
                                + " _|_|_|_|_|_"
                                + " readedLines.size() " + readedLines.size());
                        this.managerForThis.setStringBusForLogRead(readedLines);
                    } else {
                        System.out.println("_|_|NULL|_|_ AppLoggerRunnableHtmlRead.run() fromHTMLLogFileName " 
                                + fileForReadInThisJob.toString() 
                                + " _|_|NULL|_|_"
                                + " readedLines.size() is null");
                        this.managerForThis.setStringBusForLogRead(new ArrayBlockingQueue<String>(1));
                    }

                    currentJob.setFalseFromHTMLLogFileNameChanged();
                } catch (IOException ex) {
                    ex.getMessage();
                    ex.printStackTrace();
                }
            }
        }
        currentJob.setTrueFromHTMLJobDone();
        currentJob.setFalseFromHTMLNewRunner();
    }

}