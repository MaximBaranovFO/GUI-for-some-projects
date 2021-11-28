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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Администратор
 */
public class ZPINcParamJournalDisk {

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcAppHelper#outPutToConsoleDiskInfo() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcAppHelper#getNcSysProperties() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcAppHelper#getZPINcDiskInfoForMaxFreeSpace() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getDefaultCfgValues() }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#initDiskInfo() }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getDefaultParametersForCfg() }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getLinesParametersForUpdateCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @return
     */
    protected static TreeMap<Long, ZPINcDiskInfo> getFromJournalDiskOrCreateIt(){
        TreeMap<Long, ZPINcDiskInfo> readedFromFileDiskInfo;
        if( !fileJournalDiskExist() ){
            TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcDiskUtils.getDiskInfo();
            int count = fileJournalDiskWrite(sysDisk);
            return sysDisk;
        }
        readedFromFileDiskInfo = fileJournalDiskRead();
        if( needToUpdateJournalDisk(readedFromFileDiskInfo) ){
            if ( updateRecordInJournalDisk() ){
                readedFromFileDiskInfo = fileJournalDiskRead();
            }
        }
        return readedFromFileDiskInfo;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * </ul>
     * @param inFuncDiskInfo
     * @return
     */
    private static boolean needToUpdateJournalDisk(TreeMap<Long, ZPINcDiskInfo> inFuncDiskInfo){
        FileSystem fs = FileSystems.getDefault();
        long tmpTotalSpace = 0;
        boolean totalSpaceDisk = false;
        boolean totalName = false;
        String fsName = "";
        for( FileStore itemFS : fs.getFileStores() ){
            try {
                tmpTotalSpace = itemFS.getTotalSpace();
            } catch (IOException ex) {
                ZPINcAppHelper.logException(
                    ZPINcParamJournalDisk.class.getCanonicalName(), ex);
            }
            fsName = itemFS.name();
            for( Map.Entry<Long, ZPINcDiskInfo> itemInFuncDisk : inFuncDiskInfo.entrySet() ){
                totalSpaceDisk = totalSpaceDisk || (tmpTotalSpace == itemInFuncDisk.getValue().totalSpace);
                totalName = totalName || ( fsName.equalsIgnoreCase(itemInFuncDisk.getValue().strFileStoreName) );
            }
            if( !totalSpaceDisk || !totalName ){
                return true;
            }
        }
        return false;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * </ul>
     * @return
     */
    private static boolean updateRecordInJournalDisk(){
        TreeMap<Long, ZPINcDiskInfo> readedFromFileDiskInfo;
        TreeMap<Long, ZPINcDiskInfo> writeToFileDiskInfo;
        readedFromFileDiskInfo = fileJournalDiskRead();
        writeToFileDiskInfo = appendRecordInJournalDisk(readedFromFileDiskInfo);
        if( readedFromFileDiskInfo.size() == writeToFileDiskInfo.size() ){
            int count = fileJournalDiskWrite(writeToFileDiskInfo);
            return count > 0;
        }
        return false;
    }

    /**
     * Not used
     * @param addRecordToFileDiskInfo
     * @return
     */
    private static boolean addRecordInJournalDisk(ZPINcDiskInfo addRecordToFileDiskInfo){
        TreeMap<Long, ZPINcDiskInfo> readedFromFileDiskInfo;
        TreeMap<Long, ZPINcDiskInfo> writeToFileDiskInfo;
        writeToFileDiskInfo = new TreeMap<Long, ZPINcDiskInfo>();
        readedFromFileDiskInfo = fileJournalDiskRead();
        writeToFileDiskInfo.putAll(readedFromFileDiskInfo);
        writeToFileDiskInfo.put(readedFromFileDiskInfo.lastKey() + 1, addRecordToFileDiskInfo);
        if( readedFromFileDiskInfo.size() == writeToFileDiskInfo.size() ){
            int count = fileJournalDiskWrite(writeToFileDiskInfo);
            return count > 0;
        }
        return false;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param diskUserAlias
     * @return
     */
    protected static boolean updateUserAliasInJournalDisk(TreeMap<Integer, String> diskUserAlias){
        TreeMap<Long, ZPINcDiskInfo> readedFromFileDiskInfo;
        TreeMap<Long, ZPINcDiskInfo> writeToFileDiskInfo;
        readedFromFileDiskInfo = fileJournalDiskRead();
        writeToFileDiskInfo = new TreeMap<Long, ZPINcDiskInfo>();
        writeToFileDiskInfo.putAll(readedFromFileDiskInfo);
        boolean boolAliasChanged = false;
        for( Map.Entry<Integer, String> itemAlias : diskUserAlias.entrySet() ){
            ZPINcDiskInfo toChangeDiskInfo = writeToFileDiskInfo.get((long) itemAlias.getKey());
            if( toChangeDiskInfo != null ){
                toChangeDiskInfo.humanAlias = itemAlias.getValue();
                writeToFileDiskInfo.put((long) itemAlias.getKey(), toChangeDiskInfo);
                boolAliasChanged = true;
            }
            toChangeDiskInfo = null;
        }
        
        
        if( boolAliasChanged ){
            int count = fileJournalDiskWrite(writeToFileDiskInfo);
            return count > 0;
        }
        return false;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#updateRecordInJournalDisk() }
     * </ul>
     * @param inFuncDiskInfo
     * @return
     */
    private static TreeMap<Long, ZPINcDiskInfo> appendRecordInJournalDisk(TreeMap<Long, ZPINcDiskInfo> inFuncDiskInfo){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcDiskUtils.getDiskInfo();
        TreeMap<Long, ZPINcDiskInfo> listToWriteDiskInfo = new TreeMap<Long, ZPINcDiskInfo>();
        TreeMap<Long, ZPINcDiskInfo> forAppend = new TreeMap<Long, ZPINcDiskInfo>();
        boolean checkOne = false;
        boolean checkTwo = false;
        boolean checkTree = false;
        boolean needCheckTree = false;
        boolean checkFour = false;
        listToWriteDiskInfo.putAll(inFuncDiskInfo);
        for( Map.Entry<Long, ZPINcDiskInfo> itemInFuncDisk : inFuncDiskInfo.entrySet() ){
            for( Map.Entry<Long, ZPINcDiskInfo> itemSysDisk : sysDisk.entrySet() ){
                if( itemSysDisk.getValue().strFileStoreName.equalsIgnoreCase(itemInFuncDisk.getValue().strFileStoreName) ){
                    checkOne = true;
                }
                if( itemInFuncDisk.getValue().diskFStype == itemSysDisk.getValue().diskFStype ){
                    checkTwo = true;
                }
                if( itemInFuncDisk.getValue().totalSpace == itemSysDisk.getValue().totalSpace ){
                    checkTree = true;
                }
                if( itemInFuncDisk.getValue().longSerialNumber != 0 ){
                    needCheckTree = true;
                    if ( itemInFuncDisk.getValue().longSerialNumber == itemSysDisk.getValue().longSerialNumber ){
                        checkFour = true;
                    }
                }
                if( (!checkOne) && (!checkTwo) && (!checkTree) ){
                    itemSysDisk.getValue().diskID = listToWriteDiskInfo.lastKey() + 1;
                    forAppend.put(itemSysDisk.getValue().diskID, itemSysDisk.getValue());
                }
            }
            if( (!checkOne) && (!checkTwo) && (!checkTree) ){
                    listToWriteDiskInfo.putAll(forAppend);
            }
            forAppend.clear();
            checkOne = false;
            checkTwo = false;
            checkTree = false;
            needCheckTree = false;
            checkFour = false;
        }
        return listToWriteDiskInfo;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * </ul>
     * @return
     */
    private static boolean fileJournalDiskExist(){
        String strDataInAppDir = ZPINcIdxFileManager.getJournalDiskPath();
        File fileJornalDisk = new File(strDataInAppDir);
        if( !ZPINcIdxFileManager.fileExistRWAccessChecker(fileJornalDisk) ){
            return false;
        }
        return true;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#updateRecordInJournalDisk() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#addRecordInJournalDisk(ru.newcontrol.ncfv.ZPINcDiskInfo) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#updateUserAliasInJournalDisk(java.util.TreeMap) }
     * </ul>
     * @param inFuncSysDisk
     * @return
     */
    private static int fileJournalDiskWrite(TreeMap<Long, ZPINcDiskInfo> inFuncSysDisk){
        String strDataInAppDir = ZPINcIdxFileManager.getJournalDiskPath();
        
        if( !ZPINcDiskUtils.isDiskInfoRecordsHashTure(inFuncSysDisk) ){
            ZPINcAppHelper.appExitWithMessage("Can't write disk info to journal, error in records hash");
            return -1;
        }
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(strDataInAppDir)))
        {
            oos.writeObject(inFuncSysDisk);
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcParamJournalDisk.class.getCanonicalName(), ex);
            return -1;
        } 
        return inFuncSysDisk.size();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#getFromJournalDiskOrCreateIt() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#updateRecordInJournalDisk() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#addRecordInJournalDisk(ru.newcontrol.ncfv.ZPINcDiskInfo) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamJournalDisk#updateUserAliasInJournalDisk(java.util.TreeMap) }
     * </ul>
     * @return
     */
    private static TreeMap<Long, ZPINcDiskInfo> fileJournalDiskRead(){
        TreeMap<Long, ZPINcDiskInfo> readedDiskInfo;
        String strDataInAppDir = ZPINcIdxFileManager.getJournalDiskPath();
        File fileJornalDisk = new File(strDataInAppDir);
        if( !ZPINcIdxFileManager.fileExistRWAccessChecker(fileJornalDisk) ){
            return new TreeMap<Long, ZPINcDiskInfo>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(strDataInAppDir)))
        {
            readedDiskInfo = (TreeMap<Long, ZPINcDiskInfo>)ois.readObject();
            if( !ZPINcDiskUtils.isDiskInfoRecordsHashTure(readedDiskInfo) ){
                ZPINcAppHelper.appExitWithMessage("Can't read disk info from journal, error in records hash");
            }
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcParamJournalDisk.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDiskInfo>();
        } 
        return readedDiskInfo;
    }
}
