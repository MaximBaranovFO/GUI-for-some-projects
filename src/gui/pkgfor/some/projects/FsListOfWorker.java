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

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.SwingUtilities;

/**
 *
 * @author Администратор
 */
public class FsListOfWorker {
    private ConcurrentSkipListMap<Integer, Object> idnewwindow;
    private ConcurrentSkipListMap<Integer, Object> idListOfObjectsAwt;
    private Boolean createdAwtList = Boolean.FALSE;
    private ConcurrentSkipListMap<Integer, Object> idListOfObjectsSu;
    private Boolean createdSuList = Boolean.FALSE;
    private ConcurrentSkipListMap<Integer, String> idnewwindowListOfString;
    FsListOfWorker(){
        this.idnewwindow = new ConcurrentSkipListMap();
        
    }
    protected void initOfListWatcher(){
        runForListOfWorkerWhoWatchByEvQueue();
        runForListOfWorkerWhoWatch();
    }
    private void runForListOfWorkerWhoWatchByEvQueue(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                idListOfObjectsAwt = new ConcurrentSkipListMap();
                createdAwtList = Boolean.TRUE;
            }
        });
    }
    private void runForListOfWorkerWhoWatch(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                idListOfObjectsSu = new ConcurrentSkipListMap();
                createdSuList = Boolean.TRUE;
            }
        });
    }
    public static String getNowFormatedDateTime(){
        long currentDateTime = System.currentTimeMillis();
      
       //creating Date from millisecond
       Date currentDate = new Date(currentDateTime);
      
       //printing value of Date
       //System.out.println("current Date: " + currentDate);
      
       DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
       
      
       //formatted value of current Date
       return df.format(currentDate);
    }
    private static void RunPdfToJpeg(String strCmd){
        try {
            Process p = Runtime.getRuntime().exec(strCmd);
            int waitFor = 0;
            try {
                waitFor = p.waitFor();
            } catch (InterruptedException ex) {
               System.out.println(ex.getMessage());
               ex.printStackTrace();
            }
            OutputStream outputStream = p.getOutputStream();
            
            System.out.println("Command " + strCmd + " end with " + Integer.toString(waitFor));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(IdReestr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void RunTesseract(String strCmd){
        try {
            Process p = Runtime.getRuntime().exec(strCmd);
            int waitFor = 0;
            try {
                waitFor = p.waitFor();
            } catch (InterruptedException ex) {
               System.out.println(ex.getMessage());
               ex.printStackTrace();
            }
            OutputStream outputStream = p.getOutputStream();
            
            System.out.println("Command " + strCmd + " end with " + Integer.toString(waitFor));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(IdReestr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void rebuildListStorage(){
        this.idnewwindow.clear();
        this.idnewwindow = null;
        this.idnewwindow = new ConcurrentSkipListMap();
    }
    private void addNewWindow(Object windowForAdd){
        addNewWindowListOfString(windowForAdd);
        this.idnewwindow.put(windowForAdd.hashCode(), windowForAdd);
    }
    private void addNewWindowListOfString(Object windowForAdd){
        this.idnewwindowListOfString.put(windowForAdd.hashCode(), windowForAdd.toString());
    }
    protected Integer countInListOfWindow(){
        return this.idnewwindow.size();
    }
}
