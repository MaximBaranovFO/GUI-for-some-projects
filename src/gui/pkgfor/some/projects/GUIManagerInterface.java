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

import java.nio.file.FileSystem;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author Администратор
 */
public class GUIManagerInterface {
    protected static void builderSoft(){
//        ZPIThIndexRule thIndexRule = new ZPIThIndexRule();
//        ZPIAdihZipStorages storeNew = new ZPIAdihZipStorages(thIndexRule);
        try {
        
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIManagerInterface.interfaceBuilder();
            }
        });
        } catch (Throwable exTrowBuilderSoft) {
                    System.err.println(exTrowBuilderSoft.getMessage());
                    exTrowBuilderSoft.printStackTrace();
                } finally {
                    try {
//                        storeNew.utilizeAllLists();
                    } catch(Throwable exTrowSecondBuilderSoftSecond) {
                        System.err.println(exTrowSecondBuilderSoftSecond.getMessage());
                        exTrowSecondBuilderSoftSecond.printStackTrace();
                    }
                }
    }
    protected static void interfaceBuilder(){
        int returnValueForThisFunction = 3163;
        
        //ZPINcfv zpiNcfvPre = new ZPINcfv();
                //zpiNcfvPre.ncfvMain();
                
                
                try {
                    // Significantly improves the look of the output in
                    // terms of the file names returned by FileSystemView!
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIDefaults defaults = UIManager.getDefaults();
                    //defaults.
                } catch(Exception weTried) {
                    weTried.getMessage();
                    weTried.getStackTrace();
                }
                
                final BlockingQueue<Character> bq;
      bq = new ArrayBlockingQueue<Character>(26);
      final ExecutorService executor = Executors.newFixedThreadPool(2);
      //final JFrame valueForThreadedWorkerRun;
      //valueForThreadedWorkerRun = windowForRun;
      Runnable producer = () ->
                          {
                              int eVnumberOfIterationProducer = 0;
                                   
                                   if(eVnumberOfIterationProducer < 1)
                                        eVnumberOfIterationProducer = 0;
                                   
                             for (char ch = 'A'; ch <= 'Z'; ch++)
                             {
                                try
                                {  
                                    eVnumberOfIterationProducer++;
                                   bq.put(ch);
                                   //Class<? extends JFrame> aClass = valueForThreadedWorkerRun.getClass();
                                   System.out.printf("%c produced by " +
                                                     "producer.%n at %s", ch, Thread.currentThread().getName(), System.currentTimeMillis());
                                
                
                
                }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                          };
                              executor.execute(producer);
                              Runnable consumer = () ->
                          {
                             int eVnumberOfIteration = 0;
                             char ch = '\0';
                             do
                             {
                                try
                                {
                                   ch = bq.take();
                                    
                                   
                                   if(eVnumberOfIteration < 1)
                                        eVnumberOfIteration = 0;
                                   eVnumberOfIteration++;
                                   long eVcurrentTimeMillis = System.currentTimeMillis();
                                    String eVvalueOf = String.valueOf(eVcurrentTimeMillis);
                                    
                                    /**
                                     * 
                                     */
                                    if(eVnumberOfIteration == 1)
                                        GUIinterfaceNamesFA.OldGUIReconstruction.ZPINcRunSIMAchanged();
                                        //GUIinterfaceNamesFA.OldGUIReconstruction.createGui();
                                        //ZPINcSwingIndexManagerApp.createGui();
                
                
                                    if(eVnumberOfIteration == 1)
                                        ZPIAppEtcSecurityHelper.createNewSecurity();
                                    /**/
                                    
                                    
                                    }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                             while (ch != 'Z');
                             executor.shutdownNow();
                          };
      executor.execute(consumer);
                                    
                

    }
    private void otherForDoAfterThatRun(){
    
                    ZPIThIndexRule thIndexRule = new ZPIThIndexRule();
                ZPIAdihZipStorages storeNew = new ZPIAdihZipStorages(thIndexRule);
                try {
                                System.out.println("*|*|* *|*|* *|*|* print created storages");
            //storeNew.printAllList();
            //storeNew.updateStorageList();
            //System.out.println("*|*|* *|*|* *|*|* print updated list of storages");
            FileSystem storeFileSystemByNumber = storeNew.getStoreFileSystemByNumber(3);
            System.out.println("*|"
                    + storeFileSystemByNumber.toString()
                    + "*|* *| opened "
                    + storeFileSystemByNumber.isOpen());
            FileSystem storeFileSystemByNumber1 = storeNew.getStoreFileSystemByNumber(10);
            System.out.println("*|"
                    + storeFileSystemByNumber1.toString()
                    + "*|* *| opened "
                    + storeFileSystemByNumber1.isOpen());
            FileSystem storeFileSystemByNumber2 = storeNew.getStoreFileSystemByNumber(11);
            System.out.println("*|"
                    + storeFileSystemByNumber2.toString()
                    + "*|* *| opened "
                    + storeFileSystemByNumber2.isOpen());
            storeNew.printAllList();

            storeNew.utilizeAllLists();
            System.out.println("*|*|* *|*|* *|*|* print utilized list of storages");
            storeNew.printAllList();
            
            ZPINcfv.tempRunNewConcept();
            System.out.println("end run *** *** *** tempRunNewConcept()");
                    ZPINcAppLoader.loadApp();
                    ZPINcAppHelper.getNcSysProperties();
                    ZPINcPreRunFileViewer.createNewCfg();
                    ZPINcParamFv appWorkCfg = ZPINcPreRunFileViewer.getCurrentWorkCfg();
                    ZPINcParamFv appEmpty = new ZPINcParamFv();
                    ZPINcSwingIndexManagerApp.ZPINcRunSIMA();
                } catch (Throwable exTrow) {
                    System.err.println(exTrow.getMessage());
                    exTrow.printStackTrace();
                } finally {
                    try {
                        storeNew.utilizeAllLists();
                    } catch(Throwable exTrowSecond) {
                        System.err.println(exTrowSecond.getMessage());
                        exTrowSecond.printStackTrace();
                    }
                }
            
        
        //return returnValueForThisFunction;
    
    }
}
