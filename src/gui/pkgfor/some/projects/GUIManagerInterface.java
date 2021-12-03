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
                ZPINcSwingIndexManagerApp.createGui();
                
                
                ZPIAppEtcSecurityHelper.createNewSecurity();
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
