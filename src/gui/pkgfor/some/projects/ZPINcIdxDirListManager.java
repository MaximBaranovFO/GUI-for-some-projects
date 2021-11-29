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

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxDirListManager {
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param forRecordData
     * @param ncToAddInIndexFile
     * @return -1 if false append into Directory List, or ID of appened record
     */
    protected static long putToDirectoryList(ZPINcDcIdxDirListToFileAttr forRecordData){
        ZPINcIMinFS ncwd = new ZPINcIMinFS();
        ZPINcIndexManageIDs ncThisManagmentIDs = ncwd.getZPINcIndexManageIDs();
        
        ZPINcTmpNowProcessInfo ncNewManageIDs = ncThisManagmentIDs.getIdsReadedData();
        
        long nextID = forRecordData.dirListID;
        int writedSize = -1;
        if(forRecordData != null){
            //Check Records contained in file, after this recording
            //Record in Directory List File
            //Read from Disk Latest Data        
            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> ncDataToDirList = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> ncDataReadedFromDirList = ZPINcIdxDirListFileReader.ncReadFromDirListFile(nextID);
            //If Data not Readed, init Zero or stay with last ID     
            if(!ncDataReadedFromDirList.isEmpty()){
                nextID++;
                //After increment record ID, check for work file to new record, has limit of records and
                //used new file or append data into early recorded file
                String strWorkFileName = ncNewManageIDs.listname;
                String strWorkFileNameForNextRecord = ZPINcIdxFileManager.getFileNameToRecord(ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirList())+"/dl",nextID);
                if(strWorkFileName.equalsIgnoreCase(strWorkFileNameForNextRecord)){
                    ncDataToDirList.putAll(ncDataReadedFromDirList);
                }
            }
            forRecordData.dirListID = nextID;
            ncDataToDirList.put(nextID, forRecordData);
            //Write new data to file of directory list            
            writedSize = ZPINcIdxDirListFileWriter.ncWriteToDirListFile(ncDataToDirList, nextID);
            if(writedSize > 0){
                ncNewManageIDs.listnameid = nextID;
                ncNewManageIDs.listname = ZPINcIdxFileManager.getFileNameToRecord(ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirList())+"/dl",nextID);
                ncThisManagmentIDs.setNewIdsData(ncNewManageIDs);
            }
        }
        return nextID;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirectoryListDataAttrWrong(ZPINcDcIdxDirListToFileAttr inFuncData){
        if( inFuncData == null ){
            return true;
        }
        if( !isDirectoryListDataAttrHashTrue(inFuncData) ){
            return true;
        }
        return false;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirectoryListDataAttrHasEmptyFiled(ZPINcDcIdxDirListToFileAttr inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean dirListIdIsEmpty = inFuncData.dirListID == -777;
        boolean diskIdIsEmpty = inFuncData.diskID == -777;
        boolean diskSnLongIsEmpty = inFuncData.diskSnLong == -777;
        boolean diskTotalSpaceIsEmpty = inFuncData.diskTotalSpace == -777;
        boolean diskProgramAliasIsEmpty = inFuncData.diskProgramAlias == "";
        boolean diskProgramAliasHashIsEmpty = inFuncData.diskProgramAliasHash == -777;
        boolean diskSnHexIsEmpty = inFuncData.diskSnHex == "";
        boolean diskSnHexHashIsEmpty = inFuncData.diskSnHexHash == -777;
        boolean diskLetterIsEmpty = inFuncData.diskLetter == '#';
        boolean pathIsEmpty = inFuncData.path == "";
        boolean pathHashIsEmpty = inFuncData.pathHash == -777;
        boolean fileLengthIsEmpty = inFuncData.fileLength == -777;
        boolean fileCanReadIsEmpty = inFuncData.fileCanRead == false;
        boolean fileCanWriteIsEmpty = inFuncData.fileCanWrite == false;
        boolean fileCanExecuteIsEmpty = inFuncData.fileCanExecute == false;
        boolean fileIsHiddenIsEmpty = inFuncData.fileIsHidden == false;
        boolean fileLastModifiedIsEmpty = inFuncData.fileLastModified == -777;
        boolean fileIsDirectoryIsEmpty = inFuncData.fileIsDirectory == false;
        boolean fileIsFileIsEmpty = inFuncData.fileIsFile == false;
        boolean deletedRecIsEmpty = inFuncData.deletedRec == false;
        boolean changedRecordIDIsEmpty = inFuncData.changedRecordID == -777;
        return dirListIdIsEmpty
                || diskIdIsEmpty
                || diskSnLongIsEmpty
                || diskTotalSpaceIsEmpty
                || diskProgramAliasIsEmpty
                || diskProgramAliasHashIsEmpty
                || diskSnHexIsEmpty
                || diskSnHexHashIsEmpty
                || diskLetterIsEmpty
                || pathIsEmpty
                || pathHashIsEmpty
                || fileLengthIsEmpty
                || fileCanReadIsEmpty
                || fileCanWriteIsEmpty
                || fileCanExecuteIsEmpty
                || fileIsHiddenIsEmpty
                || fileLastModifiedIsEmpty
                || fileIsDirectoryIsEmpty
                || fileIsFileIsEmpty
                || deletedRecIsEmpty
                || changedRecordIDIsEmpty;
    }
    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirectoryListDataAttrEmpty(ZPINcDcIdxDirListToFileAttr inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean dirListIdIsEmpty = inFuncData.dirListID == -777;
        boolean diskIdIsEmpty = inFuncData.diskID == -777;
        boolean diskSnLongIsEmpty = inFuncData.diskSnLong == -777;
        boolean diskTotalSpaceIsEmpty = inFuncData.diskTotalSpace == -777;
        boolean diskProgramAliasIsEmpty = inFuncData.diskProgramAlias == "";
        boolean diskProgramAliasHashIsEmpty = inFuncData.diskProgramAliasHash == -777;
        boolean diskSnHexIsEmpty = inFuncData.diskSnHex == "";
        boolean diskSnHexHashIsEmpty = inFuncData.diskSnHexHash == -777;
        boolean diskLetterIsEmpty = inFuncData.diskLetter == '#';
        boolean pathIsEmpty = inFuncData.path == "";
        boolean pathHashIsEmpty = inFuncData.pathHash == -777;
        boolean fileLengthIsEmpty = inFuncData.fileLength == -777;
        boolean fileCanReadIsEmpty = inFuncData.fileCanRead == false;
        boolean fileCanWriteIsEmpty = inFuncData.fileCanWrite == false;
        boolean fileCanExecuteIsEmpty = inFuncData.fileCanExecute == false;
        boolean fileIsHiddenIsEmpty = inFuncData.fileIsHidden == false;
        boolean fileLastModifiedIsEmpty = inFuncData.fileLastModified == -777;
        boolean fileIsDirectoryIsEmpty = inFuncData.fileIsDirectory == false;
        boolean fileIsFileIsEmpty = inFuncData.fileIsFile == false;
        boolean deletedRecIsEmpty = inFuncData.deletedRec == false;
        boolean changedRecordIDIsEmpty = inFuncData.changedRecordID == -777;

        boolean hashIsTrue =  isDirectoryListDataAttrHashTrue(inFuncData);
        return dirListIdIsEmpty
                && diskIdIsEmpty
                && diskSnLongIsEmpty
                && diskTotalSpaceIsEmpty
                && diskProgramAliasIsEmpty
                && diskProgramAliasHashIsEmpty
                && diskSnHexIsEmpty
                && diskSnHexHashIsEmpty
                && diskLetterIsEmpty
                && pathIsEmpty
                && pathHashIsEmpty
                && fileLengthIsEmpty
                && fileCanReadIsEmpty
                && fileCanWriteIsEmpty
                && fileCanExecuteIsEmpty
                && fileIsHiddenIsEmpty
                && fileLastModifiedIsEmpty
                && fileIsDirectoryIsEmpty
                && fileIsFileIsEmpty
                && deletedRecIsEmpty
                && changedRecordIDIsEmpty
                && hashIsTrue;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListManager#isDirectoryListDataAttrWrong(ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr) }
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListManager#isDirectoryListDataAttrEmpty(ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr) }
     * </ul>
     * @param inFuncData
     * @return
     */
    private static boolean isDirectoryListDataAttrHashTrue(ZPINcDcIdxDirListToFileAttr inFuncData){
        return inFuncData.recordHash == (
            ""
            + inFuncData.dirListID 
            + inFuncData.diskID 
            + inFuncData.diskSnLong 
            + inFuncData.diskTotalSpace 
            + inFuncData.diskProgramAlias
            + inFuncData.diskProgramAliasHash
            + inFuncData.diskSnHex 
            + inFuncData.diskSnHexHash
            + inFuncData.diskLetter 
            + inFuncData.path 
            + inFuncData.pathHash
            + inFuncData.fileLength 
            + inFuncData.fileCanRead
            + inFuncData.fileCanWrite 
            + inFuncData.fileCanExecute 
            + inFuncData.fileIsHidden 
            + inFuncData.fileLastModified
            + inFuncData.fileIsDirectory 
            + inFuncData.fileIsFile 
            + inFuncData.recordTime 
            + inFuncData.deletedRec 
            + inFuncData.changedRecordID).hashCode();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromInput(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromFile() }
     * </ul>
     * @param inFuncData
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxDirListToFileAttr> getByListIDs(TreeMap<Long, ZPINcDcIdxWordToFile> inFuncData){
        TreeMap<Long, String> filesList = new TreeMap<Long, String>();
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> toReturn = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> readedData = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        long idx = 0;
        for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemIDs : inFuncData.entrySet() ){
            String strFileName = ZPINcIdxFileManager.getFileNameToRecord(ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirList())+"/dl", itemIDs.getValue().dirListID);
            boolean inListName = false;
            for( Map.Entry<Long, String> itemName : filesList.entrySet() ){
                inListName = itemName.getValue().equalsIgnoreCase(strFileName);
            }
            if( !inListName ){
                filesList.put(idx, strFileName);
                idx++;
            }
        }
        long recIdx = 0;
        for( Map.Entry<Long, String> itemName : filesList.entrySet() ){
            readedData.putAll(ZPINcIdxDirListFileReader.ncReadFromDirListFileByName(itemName.getValue()));
            for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemIDs : inFuncData.entrySet() ){
                for( Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemReaded : readedData.entrySet() ){
                    boolean isToRetId = itemIDs.getValue().dirListID == itemReaded.getValue().dirListID;
                    if( isToRetId ){
                        toReturn.put(recIdx, itemReaded.getValue());
                        recIdx++;
                    }
                }
                
            }
            readedData.clear();
        }
        return toReturn;
    }
}
