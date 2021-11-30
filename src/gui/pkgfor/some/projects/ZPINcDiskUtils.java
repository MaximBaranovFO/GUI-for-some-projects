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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Map;
import java.util.TreeMap;
//import javax.xml.bind.DatatypeConverter;



/**
 *
 * @author Администратор
 */
public class ZPINcDiskUtils {
    private static final long Kb = 1024;
    private static final long Mb = 1024 * 1024;
    private static TreeMap<Long, ZPINcDiskInfo> ncDiskInfo = getDiskInfo();
    
    /**
     *
     */
    protected ZPINcDiskUtils() {
    }
    
    /**
     * Not used
     * @param drive
     * @return
     */
    private static String getIntSerialNumber(String drive) {
        String result = "";
        int retSerialNumber = 0;
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            try (FileWriter fw = new java.io.FileWriter(file)) {
                String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                        +"Set colDrives = objFSO.Drives\n"
                        +"Set objDrive = colDrives.item(\"" + drive + "\")\n"
                        +"Wscript.Echo objDrive.SerialNumber";  // see note
                fw.write(vbs);
            } // see note
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            try (BufferedReader input = new BufferedReader
                      (new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    result += line;
                }
            }
        }
        catch(IOException ex) {
            ZPINcAppHelper.logException(ZPINcDiskUtils.class.getCanonicalName(), ex);
        }
        return result.trim();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcDiskUtils#ZPINcDiskUtils() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * <li>{@link ru.newcontrol.ncfv.NcParamJournalDisk#appendRecordInJournalDisk(java.util.TreeMap) }
     * </ul>
     * Get now information about disks in the system
     * @return 
     */
    protected static TreeMap<Long, ZPINcDiskInfo> getDiskInfo(){
        TreeMap<Long, ZPINcDiskInfo> toRetDI = new TreeMap<Long, ZPINcDiskInfo>();
        long diskID = 0;
        try {
            FileSystem fs = FileSystems.getDefault();
            
            for (FileStore store : fs.getFileStores()) {
                if(ZPINcAppHelper.isWindows()){
                    
                    char cLet = getDiskLetterFromFileStore(store);
                    long dSN = 0;
                    if(cLet != '#'){
                        dSN = convertSN(getIntSerialNumber("" + cLet));
                    }
                    
                    String strHexDsn = Long.toHexString(dSN);
                    ZPINcDiskInfo cDI = new ZPINcDiskInfo(
                            diskID, 
                            dSN,
                            strHexDsn,
                            store.toString(),
                            store.name(),
                            cLet,
                            store.type(),
                            getProgramAlias(diskID),
                            getHumanAlias(diskID),
                            store.getTotalSpace(),
                            (store.getTotalSpace() - store.getUnallocatedSpace()),
                            store.getUsableSpace(),
                            store.getUnallocatedSpace(),
                            store.isReadOnly());
                    toRetDI.put(diskID, cDI);
                }
                else{
                    ZPINcDiskInfo cDI = new ZPINcDiskInfo(
                            diskID, 
                            0,
                            "",
                            store.toString(),
                            store.name(),
                            '#',
                            store.type(),
                            getProgramAlias(diskID),
                            getHumanAlias(diskID),
                            store.getTotalSpace(),
                            (store.getTotalSpace() - store.getUnallocatedSpace()),
                            store.getUsableSpace(),
                            store.getUnallocatedSpace(),
                            store.isReadOnly());
                    toRetDI.put(diskID, cDI);
                    
                }
                    diskID++;
            }
        } catch (IOException ex) {
                ZPINcAppHelper.logException(ZPINcDiskUtils.class.getCanonicalName(), ex);
                return new TreeMap<Long, ZPINcDiskInfo>();
        }
        boolean isDiskHashTrue = ZPINcDiskUtils.isDiskInfoRecordsHashTure(toRetDI);
        if( !isDiskHashTrue ){
            ZPINcAppHelper.appExitWithMessage("Can't get and create disk info, error in records hash");
            return new TreeMap<Long, ZPINcDiskInfo>();
        }
        return toRetDI;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcDiskUtils#getDiskInfo() }
     * </ul>
     * @param store
     * @return
     */
    private static char getDiskLetterFromFileStore(FileStore store){
        String forProcess = store.toString().toUpperCase();
        
        char cLet = '#';
        if(ZPINcAppHelper.isWindows()){
            if( forProcess.indexOf(':') > -1 ){
                String  strDiskLetter = forProcess.substring(forProcess.indexOf(":") - 1, forProcess.indexOf(":"));
                
                if( strDiskLetter.matches("[A-Z]") && (strDiskLetter.length() == 1) ){
                    cLet = strDiskLetter.charAt(0);
                }
            }
        }
        return cLet;
    }

    /**
     * Not used
     * @param strPath
     * @return
     */
    private static char getDiskLetterFromPath(String strPath){
        String forProcess = strPath.toUpperCase().toString();
        char cLet = '#';
        if(ZPINcAppHelper.isWindows()){
            if(forProcess.indexOf(':') == 1){
                if( forProcess.matches("[A-Z]") ){
                    cLet = forProcess.charAt(0);
                }
            }
        }
        return cLet;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcDiskUtils#getDiskInfo() }
     * </ul>
     * @param indSN
     * @return 
     */
    private static long convertSN(String indSN){
        long outsn = 0;
        try{
            outsn = Long.parseLong(indSN);
        }
        catch(NumberFormatException ex){
            ZPINcAppHelper.logException(ZPINcDiskUtils.class.getCanonicalName(), ex);
            try{
                //outsn = DatatypeConverter.parseLong(indSN);
                outsn = jakarta.xml.bind.DatatypeConverter.parseLong(indSN);
            }
            catch(NumberFormatException extwo){
                ZPINcAppHelper.logException(
                        ZPINcDiskUtils.class.getCanonicalName(), extwo);
                return 0;
            }
        }
        return outsn;
    }
    /**
     * Not used
     * For example, when use USB drive, disk letter may be changed sometimes, 
     * for additional option in detect may be used totalSpace
     * @param ncFile
     * @return
     * diskID provided by class {@link ru.newcontrol.ncfv.ZPINcDiskInfo#diskID ZPINcDiskInfo}
     */
    private static long getDiskIDbyLetterTotalSpace(File ncFile){
        String strForDisk = ZPINcIdxFileManager.getStrCanPathFromFile(ncFile).toUpperCase().substring(0);
        char ncDiskLetterFromPath = strForDisk.charAt(0);
        long ncNowStrorageIndex = -1;
        for(Map.Entry<Long, ZPINcDiskInfo> ncOneOfDisk : ncDiskInfo.entrySet()){
            if ((ncOneOfDisk.getValue().diskLetter == ncDiskLetterFromPath)
                    && (ncFile.exists())
                    && (ncOneOfDisk.getValue().totalSpace == ncFile.getTotalSpace())){
                ncNowStrorageIndex = ncOneOfDisk.getValue().diskID;
            }
        }
        return ncNowStrorageIndex;
    }

    /**
     *
     * @param diskID
     * @return
     */
    protected static String getHumanAlias(long diskID){
        return "DiskAliasNumber-" + diskID;
    }

    /**
     *
     * @param diskID
     * @return
     */
    protected static String getProgramAlias(long diskID){
        return "DiskAliasNumber-" + diskID + "-" + System.nanoTime();
    }
    protected static boolean isZPINcDiskInfoHashTrue(ZPINcDiskInfo inFuncData){
        return inFuncData.reordHash == (""
            + inFuncData.diskID
            + inFuncData.longSerialNumber
            + inFuncData.strHexSerialNumber
            + inFuncData.strFileStore
            + inFuncData.strFileStoreName
            + inFuncData.diskLetter
            + inFuncData.diskFStype
            + inFuncData.programAlias
            + inFuncData.humanAlias
            + inFuncData.totalSpace
            + inFuncData.usedSpace
            + inFuncData.availSpace
            + inFuncData.unAllocatedSpace
            + inFuncData.isReadonly
            + inFuncData.recordCreationTime).hashCode();
    }
    protected static boolean isDiskInfoRecordsHashTure(TreeMap<Long, ZPINcDiskInfo> inFuncData){
        boolean isRecordHash = true;
        for(Map.Entry<Long, ZPINcDiskInfo> itemDisk : inFuncData.entrySet() ){
            boolean boolRecHashTrue = isZPINcDiskInfoHashTrue(itemDisk.getValue());
            if( !boolRecHashTrue ){
                printToConsoleZPINcDiskInfo(itemDisk.getValue());
                isRecordHash = false;
            }
        }
        return isRecordHash;
    }
    protected static void printToConsoleZPINcDiskInfo(ZPINcDiskInfo inFuncData){
        
        int calcHash = (""
            + inFuncData.diskID
            + inFuncData.longSerialNumber
            + inFuncData.strHexSerialNumber
            + inFuncData.strFileStore
            + inFuncData.strFileStoreName
            + inFuncData.diskLetter
            + inFuncData.diskFStype
            + inFuncData.programAlias
            + inFuncData.humanAlias
            + inFuncData.totalSpace
            + inFuncData.usedSpace
            + inFuncData.availSpace
            + inFuncData.unAllocatedSpace
            + inFuncData.isReadonly
            + inFuncData.recordCreationTime).hashCode();
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
        + "ID:\t" + inFuncData.diskID
        + "\nCalculated hash of record: " + calcHash
        + "\nIn record hash: " + inFuncData.reordHash
        + "\ncompare result: "
        + (calcHash == inFuncData.reordHash));
        
    }
}
