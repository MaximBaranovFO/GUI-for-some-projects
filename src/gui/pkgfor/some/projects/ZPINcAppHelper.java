/*
 *  Copyright 2017 Administrator of development departament newcontrol.ru .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package gui.pkgfor.some.projects;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Администратор
 */
public class ZPINcAppHelper {

    /**
     *
     */
    protected static void outPutToConsoleDiskInfo(){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        if( !sysDisk.isEmpty() ){
            outMessageToConsole("If your need to write alias parameter into Configuration file");
            outMessageToConsole("write it in this example format:");
            String[] strForDisk = {"USB",
            "Black",
            "Document",
            "Flash",
            "Storage",
            "HDD",
            "Network",
            "System",
            "Bootable",
            "Silver",
            "Grey",
            "Old",
            "New",
            "FromWorker"};
            String strFirst = "";
            int i = 0;
            for(Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet()){
                if(i == 0){
                    strFirst = strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)];
                    outMessageToConsole("alias_" + itemDisk.getValue().diskID + "="
                    + strFirst);
                }
                else{
                    outMessageToConsole("alias_" + itemDisk.getValue().diskID + "="
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]
                    + strForDisk[(int) Math.round(Math.random()*12)]);
                }
                outMessageToConsole("Disk name and letter: " + itemDisk.getValue().strFileStore);
                outMessageToConsole("Disk name: " + itemDisk.getValue().strFileStoreName);
                if(isWindows()){
                    outMessageToConsole("Serial number: " + itemDisk.getValue().strHexSerialNumber);
                }
                outMessageToConsole("File system: " + itemDisk.getValue().diskFStype);
                outMessageToConsole("Total space in bytes: " + Long.toString(itemDisk.getValue().totalSpace));
                outMessageToConsole("Total space in Kb: " + Long.toString(Math.round(itemDisk.getValue().totalSpace/1024)));
                outMessageToConsole("Total space in Mb: " + Long.toString(Math.round(itemDisk.getValue().totalSpace/(1024*1024))));
                outMessageToConsole(" ");
                i++;
            }

            outMessageToConsole("where " + sysDisk.firstEntry().getValue().diskID + " is diskID and " + strFirst);
            outMessageToConsole("is User alias label returned in search results");
            
        }
        else{
            outMessageToConsole("Information about disks is Empty, contact your system Administrator");
            System.exit(0);
        }
    }

    /**
     *
     * @param pathErr
     */
    protected static void appExitWithMessageFSAccess(String pathErr){
        try {
            outMessage(ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
            + "For run application in the path: " + pathErr
            + "\n application must have permission on read, write on the file system"
            + "\n for use functions of this application your must have run it in the"
            + "\n system administrator privilegies, this application, read files on"
            + "\n your file system, create some files in your file system, for more"
            + "\n information about use this application read manual or contact to"
            + "\n your system administrator");
        } catch( java.lang.StackOverflowError exNewError) {
            System.out.println(exNewError.getMessage());
            exNewError.printStackTrace();
        }
        System.exit(0);
    }
    protected static void appExitWithMessage(String strErrMessage){
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.ERROR_CRITICAL.getStr() + strErrMessage);
        System.exit(0);
    }

    /**
     *
     */
    protected static void getNcSysProperties(){
        Properties sProp = System.getProperties();
        Set<String> strPropName = sProp.stringPropertyNames();
        Map<String, String> sEnv = System.getenv();
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("System.getProperties");
        outMessageToConsole("");
        for( String itemPorperties : strPropName ){
            outMessageToConsole("Property name: \t" + itemPorperties);
            outMessageToConsole("Property value: \t" + sProp.getProperty(itemPorperties));
        }
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("System.getenv");
        outMessageToConsole("");
        for(Map.Entry<String, String> itemEnv : sEnv.entrySet()){
            outMessageToConsole("Key of environment: \t" + itemEnv.getKey());
            outMessageToConsole("Value of environment: \t" + itemEnv.getValue());
        }
        
        File[] fileRoots = File.listRoots();
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("File.listRoots");
        outMessageToConsole("");
        for(File itemFile : fileRoots){
            try {
                outMessageToConsole("getAbsolutePath: " + ZPINcIdxFileManager.getStrCanPathFromFile(itemFile));
                outMessageToConsole("getCanonicalPath: " + itemFile.getCanonicalPath());
                outMessageToConsole("toString: " + itemFile.toString());
            
                outMessageToConsole("getName: " + itemFile.getName());
                outMessageToConsole("getFreeSpace: " + itemFile.getFreeSpace());
                outMessageToConsole("getUsableSpace: " + itemFile.getUsableSpace());
                outMessageToConsole("getTotalSpace: " + itemFile.getTotalSpace());
            } catch (IOException ex) {
                Logger.getLogger(ZPINcPreRunFileViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        FileSystem fs = FileSystems.getDefault();
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("FileSystems.getDefault.getFileStores");
        outMessageToConsole("");
        for (FileStore store : fs.getFileStores()) {
            outMessageToConsole("FileStore: " + store.toString());
            outMessageToConsole("name: " + store.name());
            
            outMessageToConsole("type: " + store.type());
            outMessageToConsole("isReadOnly: " + store.isReadOnly());
                
            try {
                outMessageToConsole("getTotalSpace: " + store.getTotalSpace());
                outMessageToConsole("getUsableSpace: " + store.getUsableSpace());
                outMessageToConsole("getUnallocatedSpace: " + store.getUnallocatedSpace());
            } catch (IOException ex) {
                Logger.getLogger(ZPINcPreRunFileViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("FileSystems.getDefault.getRootDirectories");
        outMessageToConsole("");
        for (Path storePath : fs.getRootDirectories()) {
            outMessageToConsole("getNameCount: " + storePath.getNameCount());
            outMessageToConsole("Path.toString: " + storePath.toString());
            outMessageToConsole("getFileSystem.toString: " + storePath.getFileSystem().toString());
        }
        outMessageToConsole("");
        outMessageToConsole("");
        outMessageToConsole("NcDiskUtils.getDiskInfo");
        outMessageToConsole("");
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        for( Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet() ){
            ZPINcAppHelper.outMessageToConsole("");
            ZPINcAppHelper.outMessageToConsole("diskID: \t" + Long.toString(itemDisk.getValue().diskID));
            ZPINcAppHelper.outMessageToConsole("humanAlias: \t" + itemDisk.getValue().humanAlias);
            ZPINcAppHelper.outMessageToConsole("programAlias: \t" + itemDisk.getValue().programAlias);
            ZPINcAppHelper.outMessageToConsole("strFileStore: \t" + itemDisk.getValue().strFileStore);
            ZPINcAppHelper.outMessageToConsole("strFileStoreName: \t" + itemDisk.getValue().strFileStoreName);
            ZPINcAppHelper.outMessageToConsole("DiskLetter: \t" + itemDisk.getValue().diskLetter);
            ZPINcAppHelper.outMessageToConsole("longSerialNumber: \t" + Long.toString(itemDisk.getValue().longSerialNumber));
            ZPINcAppHelper.outMessageToConsole("strHexSerialNumber: \t" + itemDisk.getValue().strHexSerialNumber);
            ZPINcAppHelper.outMessageToConsole("DiskFStype: \t" + itemDisk.getValue().diskFStype);
            ZPINcAppHelper.outMessageToConsole("isReadonly: \t" + itemDisk.getValue().isReadonly);
            ZPINcAppHelper.outMessageToConsole("availSpace: \t" + Long.toString(itemDisk.getValue().availSpace));
            ZPINcAppHelper.outMessageToConsole("totalSpace: \t" + Long.toString(itemDisk.getValue().totalSpace));
            ZPINcAppHelper.outMessageToConsole("unAllocatedSpace: \t" + Long.toString(itemDisk.getValue().unAllocatedSpace));
            ZPINcAppHelper.outMessageToConsole("usedSpace: \t" + Long.toString(itemDisk.getValue().usedSpace));
        }
        
    }

    /**
     *
     * @param strMessage
     */
    protected static void outMessage(String strMessage){
        if( ZPINcfvRunVariables.getStage() ){
            if( !ZPINcfvRunVariables.isNoOutToConsole() ){
                if( ZPINcfvRunVariables.getWithTrace() ){
                    String strNowTime = java.time.LocalDateTime.now().toString();
                    outMessageToConsole("at " + strNowTime + "\n");
                    Thread t = Thread.currentThread();
                    StackTraceElement[] nowT = t.getStackTrace();
                    int idx = 0;
                    for(StackTraceElement itemT : nowT ){
                        if( idx > 1 || ZPINcfvRunVariables.getTraceWithPrintFunc() ){
                            String strOutFile = "";
                            if( ZPINcfvRunVariables.getIncludeFile() ){
                                strOutFile = itemT.getFileName() + "\t";
                            }
                            String strOut = 
                                "\t" + itemT.getClassName()
                                + "." + itemT.getMethodName()
                                + "\t[" + itemT.getLineNumber() + "]"
                                + (itemT.isNativeMethod() ? "-native" : "");
                            outMessageToConsole(strOutFile + strOut);
                        }
                        idx++;
                    }
                }
                outMessageToConsole(strMessage);
            }
            outMessageToAppLogFile(strMessage);
        }
    }
    protected static void logException(String strClassName, Exception ex){
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.ERROR.getStr()
                + ZPINcStrLogMsgField.CLASSNAME.getStr()
                + strClassName
                + ZPINcStrLogMsgField.EXCEPTION_MSG.getStr()
                + ex.getMessage());
    }
    protected static String exceptionToString(Class<?> exceptionClass,
            Class<?> srcClass,
            String descrStr){
        String strForReturn = "";
        strForReturn = ZPIAppMsgEnFiledForLog.EX_CLASS
                + exceptionClass.getCanonicalName()
                + ZPIAppMsgEnFiledForLog.EX_SRC_CLASS
                + srcClass.getCanonicalName()
                + ZPIAppMsgEnFiledForLog.EX_DESCR
                + descrStr;
        return strForReturn;
    }
    /**
     *
     * @param strMessage
     */
    protected static void outMessageToConsole(String strMessage){
        System.out.println(strMessage);
    }

    /**
     *
     * @param strMessage
     */
    protected static void outMessageToAppLogFile(String strMessage){
        if( ZPINcfvRunVariables.isOutToLogFile() ){
            
            String strNowTime =  ZPINcStrLogMsgField.TIME.getStr()
                + java.time.LocalDateTime.now().toString();
            String strTimeAndMsg = strNowTime
                    + ZPINcStrLogMsgField.MSG.getStr() + strMessage;
            String strTrace = "";
            if( ZPINcfvRunVariables.isOutToLogFileWithTrace() ){
                TreeMap<Long, String> strForLog = new TreeMap<Long, String>();
                Thread t = Thread.currentThread();
                
                StackTraceElement[] nowT = t.getStackTrace();
                long idx = 0;
                strForLog.put(idx, strTimeAndMsg);
                idx++;
                String strThread = ZPINcStrLogMsgField.THREAD.getStr()
                + ZPINcStrLogMsgField.COUNT.getStr()
                + Thread.activeCount()
                + ZPINcStrLogMsgField.THREAD_GROUP_NAME.getStr()
                + t.getThreadGroup().getName()
                + ZPINcStrLogMsgField.ACTIVE.getStr()        
                + ZPINcStrLogMsgField.COUNT.getStr()
                + t.getThreadGroup().activeCount()
                + ZPINcStrLogMsgField.ACTIVE.getStr()
                + ZPINcStrLogMsgField.GROUP.getStr()
                + ZPINcStrLogMsgField.COUNT.getStr()
                + t.getThreadGroup().activeGroupCount();
                strForLog.put(idx, strThread);
                idx++;
                String strLoader = ZPINcStrLogMsgField.CLASSLOADER.getStr()
                    + ZPINcStrLogMsgField.CANONICALNAME.getStr()
                    + t.getContextClassLoader().getClass().getCanonicalName();
                strForLog.put(idx, strLoader);
                idx++;
                strForLog.put(idx, ZPINcStrLogMsgField.THREAD.getStr()
                    + ZPINcStrLogMsgField.TOSTRING.getStr()
                    + t.toString());
                idx++;
                strForLog.put(idx, ZPINcStrLogMsgField.THREAD.getStr()
                    + ZPINcStrLogMsgField.NAME.getStr()
                    + t.getName());
                idx++;
                strForLog.put(idx, ZPINcStrLogMsgField.THREAD.getStr()
                    + ZPINcStrLogMsgField.CANONICALNAME.getStr()
                    + t.getClass().getCanonicalName());
                idx++;
                strForLog.put(idx, ZPINcStrLogMsgField.THREAD.getStr()
                        + ZPINcStrLogMsgField.ID.getStr() + t.getId());
                idx++;
                strForLog.put(idx, ZPINcStrLogMsgField.THREAD.getStr()
                    + ZPINcStrLogMsgField.STATE.getStr()
                    + ZPINcStrLogMsgField.NAME.getStr() + t.getState().name());
                idx++;
                int stackIdx = 0;
                for(StackTraceElement itemT : nowT ){
                    if( stackIdx > 1
                        || ZPINcfvRunVariables.isOutToLogFileTraceWithPrintFunc() ){
                        
                        String strOutFile = "";
                        if( ZPINcfvRunVariables.isOutToLogFileIncludeFile() ){
                            
                            strOutFile = ZPINcStrLogMsgField.FILENAME.getStr()
                                + itemT.getFileName();
                        }
                        String strOut = 
                            ZPINcStrLogMsgField.CLASSNAME.getStr()
                            + itemT.getClassName()
                            + ZPINcStrLogMsgField.METHODNAME.getStr()
                            + itemT.getMethodName()
                            + ZPINcStrLogMsgField.LINENUM.getStr()
                            + itemT.getLineNumber()
                            + (itemT.isNativeMethod()
                                ? ZPINcStrLogMsgField.NATIVE.getStr() : "");
                        
                        strTrace = ZPINcStrLogMsgField.ELEMENTNUM.getStr()
                                + stackIdx + strOutFile + strOut;
                        stackIdx++;
                    }
                    if( strTrace.length() > 0 ){
                        
                        strForLog.put(idx, strTrace);
                    }
                    strTrace = "";
                    idx++;
                }
                
                ZPINcLogFileManager.putToLog(strForLog);
            }
            else{
                ZPINcLogFileManager.putToLogStr(strTimeAndMsg);
            }
        }
    }
    
/**
 * Find disk with maximum avalable space and not ReadOnly for make index work directory
 * @return index of record in class ZPINcDiskInfo
 */    
    protected static ZPINcDiskInfo getZPINcDiskInfoForMaxFreeSpace(){
        ZPINcDiskInfo ncDisk = null;
            long tmpFreeSpace = 0;
            TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
            if( !sysDisk.isEmpty() ){
                for ( Map.Entry<Long, ZPINcDiskInfo> nccd : sysDisk.entrySet() ){
                    if( !nccd.getValue().isReadonly ){
                        if(tmpFreeSpace < nccd.getValue().availSpace){
                            tmpFreeSpace = nccd.getValue().availSpace;
                            ncDisk = nccd.getValue();
                        }
                    }
                }
                return ncDisk;
            }
        return ncDisk;
    }

    /**
     *
     * @return
     */
    protected static boolean isWindows(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "win" ) >= 0); 
    }

    /**
     *
     * @return
     */
    protected static boolean isMac(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "mac" ) >= 0); 
    }

    /**
     *
     * @return
     */
    protected static boolean isUnix (){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
    }



    /**
     *
     * @param bytes
     * @return
     */
    protected static String toHex(byte[] bytes) {
        //return DatatypeConverter.printHexBinary(bytes);
       
        //return org.apache.commons.codec.binary.Hex.decodeHex(bytes);
        
        return jakarta.xml.bind.DatatypeConverter.printHexBinary(bytes);
    }
    protected static void strArrToConsoleOutPut(String[] strArrForOutPut){
        for(int i = 0; i < strArrForOutPut.length ; i++){
                ZPINcAppHelper.outMessage(
                    ZPINcStrLogMsgField.INFO.getStr()
                    + i + "\t" + strArrForOutPut[i]);
        }
    }
    protected static String getThreadInfoToString(Thread forStrBuild){
        ThreadGroup threadGroup = forStrBuild.getThreadGroup();
        String nameThreadGroup = threadGroup.getName();
        int activeCountThreadGroup = threadGroup.activeCount();
        int activeGroupCount = threadGroup.activeGroupCount();
        Class<?> aClass = forStrBuild.getClass();
        return ZPINcStrLogMsgField.INFO.getStr()
                    + ZPINcStrLogMsgField.THREAD_GROUP_NAME.getStr()
                    + nameThreadGroup
                    + ZPINcStrLogMsgField.ACTIVE.getStr()        
                    + ZPINcStrLogMsgField.COUNT.getStr()
                    + String.valueOf(activeCountThreadGroup)
                    + ZPINcStrLogMsgField.ACTIVE.getStr()
                    + ZPINcStrLogMsgField.GROUP.getStr()
                    + ZPINcStrLogMsgField.COUNT.getStr()
                    + String.valueOf(activeGroupCount)
                    + ZPINcStrLogMsgField.THREAD.getStr()
                    + ZPINcStrLogMsgField.ID.getStr()
                    + String.valueOf(forStrBuild.getId())
                    + ZPINcStrLogMsgField.PRIORITY.getStr()        
                    + String.valueOf(forStrBuild.getPriority())
                    + ZPINcStrLogMsgField.NAME.getStr()
                    + forStrBuild.getName()
                    + ZPINcStrLogMsgField.CANONICALNAME.getStr()
                    + aClass.getCanonicalName()
                    + ZPINcStrLogMsgField.GENERICSTRING.getStr()
                    + aClass.toGenericString();
    }
    protected static String getClassInfoToString(Class<?> forStrBuild){
        return ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrLogMsgField.CLASSNAME.getStr()
            + forStrBuild.getName()
            + ZPINcStrLogMsgField.TYPENAME.getStr()
            + forStrBuild.getTypeName()
            + ZPINcStrLogMsgField.CANONICALNAME.getStr()
            + forStrBuild.getCanonicalName()
            + ZPINcStrLogMsgField.GENERICSTRING.getStr()
            + forStrBuild.toGenericString();
    }
    protected static void outCreateObjectMessage(String strMsg, Class<?> forStrBuild){
        String classInfoToString = ZPINcAppHelper.getClassInfoToString(forStrBuild);
            ZPINcAppHelper.outMessage( ZPINcStrLogMsgField.INFO.getStr()
                    + ZPINcStrLogMsgField.CREATE.getStr()
                    + strMsg
                    + classInfoToString);
    }
    /**
     * 
     * @param msgForOut
     * @param appConstantsParam 
     */
    protected static void outToConsoleIfDevAndParamTrue(String msgForOut, Boolean appConstantsParam){
        if( (ZPIAppConstants.LOG_LEVEL_CURRENT > ZPIAppConstants.LOG_LEVEL_DEBUG) && appConstantsParam ){
                System.out.println(msgForOut);
            }
    }
}
