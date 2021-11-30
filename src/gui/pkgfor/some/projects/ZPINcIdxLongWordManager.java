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
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxLongWordManager {

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordListManager#putLongWord(java.util.TreeMap) }
     * </ul>
     * @param StructureLongWord
     * @param dataFuncForWrite
     * @return 
     */    
    protected static long putLongWordInFile(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureLongWord, ZPINcDcIdxLongWordListToFile dataFuncForWrite){
        long countWritedIDs = 0;
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureLongWord.entrySet()){
            String hexWord = item.getValue().hexSubString;
            long recID = 0;
            String oldName = "";
            String forRecName = "";
            TreeMap<Long, ZPINcDcIdxWordToFile> inDiskWordRecord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
            boolean isEqualNames = false;
            do{
                oldName = ZPINcIdxFileManager.getFileNameToRecordLongWord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWord()) + "/n-"
                        + dataFuncForWrite.name.substring(0, 4), dataFuncForWrite.nameID, recID);
                inDiskWordRecord = ZPINcIdxLongWordFileReader.ncReadFromLongWordFile(oldName,recID);
                
                if(inDiskWordRecord.isEmpty()){
                    break;
                }
                recID = inDiskWordRecord.lastEntry().getValue().recordID;
                recID++;
                forRecName = ZPINcIdxFileManager.getFileNameToRecordLongWord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWord()) + "/n-"
                        + dataFuncForWrite.name.substring(0, 4), dataFuncForWrite.nameID, recID);
                isEqualNames = ! oldName.equalsIgnoreCase(forRecName);
                if(isEqualNames){
                    inDiskWordRecord.clear();
                }
            }
            while(isEqualNames);

            ZPINcDcIdxWordToFile forRec = 
                new ZPINcDcIdxWordToFile(
                    recID,
                    item.getValue().toFileId,
                    item.getValue().strSubString,
                    item.getValue().positionSubString,
                    item.getValue().lengthSubString);
            inDiskWordRecord.put(recID, forRec);
            forRecName = ZPINcIdxFileManager.getFileNameToRecordLongWord(
                ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWord()) + "/n-"
                + dataFuncForWrite.name.substring(0, 4), dataFuncForWrite.nameID, recID);
            countWritedIDs = countWritedIDs + ZPINcIdxLongWordFileWriter.ncWriteForLongWord(
                inDiskWordRecord, forRecName, recID);
        }
        
        return countWritedIDs;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSearchInIndex#getIDsForKeyWord(java.lang.String) }
     * </ul>
     * @param StructureLongWord
     * @return 
     */    
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getLongWord(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureLongWord){
        TreeMap<Long, ZPINcDcIdxWordToFile> retReadedData = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        long countWritedIDs = 0;
        String nameLongWordList = "";
        ZPINcDcIdxLongWordListToFile dataForRead;
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureLongWord.entrySet()){
            long recLongWordListID = 0;
            String hexWord = item.getValue().hexSubString;
            dataForRead = new ZPINcDcIdxLongWordListToFile(
                    recLongWordListID,
                    hexWord, 
                    item.getValue().strSubString);
            
            dataForRead = getForSearchLongWordID(dataForRead);
            // toChangeCheckRecord
            if((dataForRead.nameID == -777)
                && (dataForRead.name.length() == 0)
                && (dataForRead.nameHash == -777)
                && (dataForRead.word.length() == 0)
                && (dataForRead.wordHash == -777)){
                break;
            }
            
            recLongWordListID = dataForRead.nameID;
            TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> subStructureLongWord = new TreeMap<Long, ZPINcDcIdxSubStringToOperationUse>();
            subStructureLongWord.put(recLongWordListID,item.getValue());
            retReadedData.putAll(getLongWordFromFile(subStructureLongWord,dataForRead));
        }
        return retReadedData;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordManager#getLongWord(java.util.TreeMap) }
     * </ul>
     * @param dataForRead
     * @return 
     */    
    private static ZPINcDcIdxLongWordListToFile getForSearchLongWordID(ZPINcDcIdxLongWordListToFile dataForRead){

        String nameLongWordList = "";
        String nameNextLongWordList = "";
        long recID = dataForRead.nameID;
        nameLongWordList = ZPINcIdxFileManager.getFileNameToRecord(
                ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWordList()) + "/wl-"
                + dataForRead.name.substring(0, 4),recID);
        boolean isNamesEquals = false;
        boolean isNameEquals = false;
        boolean isNameHashEquals = false;
        boolean isSubStrEquals = false;
        boolean isSubStrHashEquals = false;
        
        TreeMap<Long, ZPINcDcIdxLongWordListToFile> readFromDiskData = new TreeMap<Long, ZPINcDcIdxLongWordListToFile>();
        do{
            readFromDiskData.clear();
            readFromDiskData = ZPINcIdxLongWordListFileReader.ncReadFileContainedId(dataForRead, recID);
            for(Map.Entry<Long, ZPINcDcIdxLongWordListToFile> item  : readFromDiskData.entrySet()){
                isNameEquals = item.getValue().name.equalsIgnoreCase(dataForRead.name);
                isNameHashEquals = item.getValue().nameHash == dataForRead.nameHash;
                isSubStrEquals = item.getValue().word.equalsIgnoreCase(dataForRead.word);
                isSubStrHashEquals = item.getValue().wordHash == dataForRead.wordHash;
                if(isNameEquals
                        && isNameHashEquals
                        && isSubStrEquals
                        && isSubStrHashEquals){
                    return item.getValue();
                }
            }
          
            recID = readFromDiskData.lastEntry().getValue().nameID + 1; 
            nameNextLongWordList = ZPINcIdxFileManager.getFileNameToRecord(
                    ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWordList()) + "/wl-"
                    + dataForRead.name.substring(0, 4),recID);
            isNamesEquals = ! nameLongWordList.equalsIgnoreCase(nameNextLongWordList);
            if(isNamesEquals){
                dataForRead.nameID = recID;
                if( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(nameNextLongWordList))){
                    break;
                }
            }
        }
        while(isNamesEquals);
        
        dataForRead = new ZPINcDcIdxLongWordListToFile();
        return dataForRead;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordManager#getLongWord(java.util.TreeMap) }
     * </ul>
     * @param StructureLongWord
     * @param dataFuncForWrite
     * @return 
     */
    private static TreeMap<Long, ZPINcDcIdxWordToFile> getLongWordFromFile(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureLongWord, ZPINcDcIdxLongWordListToFile dataFuncForWrite){
        TreeMap<Long, ZPINcDcIdxWordToFile> retReadedData = new TreeMap<Long, ZPINcDcIdxWordToFile>();

        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureLongWord.entrySet()){
            long recID = 0;
            String oldName = "";
            String forRecName = "";
            TreeMap<Long, ZPINcDcIdxWordToFile> inDiskWordRecord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
            boolean isEqualNames = false;
            do{
                oldName = ZPINcIdxFileManager.getFileNameToRecordLongWord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWord()) + "/n-"
                        + dataFuncForWrite.name.substring(0, 4), dataFuncForWrite.nameID, recID);
                inDiskWordRecord = ZPINcIdxLongWordFileReader.ncReadFromLongWordFile(oldName,recID);
                if(inDiskWordRecord.isEmpty()){
                    break;
                }
                retReadedData.putAll(inDiskWordRecord);
                recID = inDiskWordRecord.lastEntry().getValue().recordID;
                recID++;
                forRecName = ZPINcIdxFileManager.getFileNameToRecordLongWord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWord()) + "/n-"
                        + dataFuncForWrite.name.substring(0, 4), dataFuncForWrite.nameID, recID);
                isEqualNames = ! oldName.equalsIgnoreCase(forRecName);
                if(isEqualNames){
                    if( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(forRecName))){
                        break;
                    }
                }
            }
            while(isEqualNames);
        }
        return retReadedData;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#convertFormOperationToFileData(ru.newcontrol.ncfv.ZPINcDcIdxSubStringToOperationUse, long) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSrchKeyWordInput#getIdDataForSplittedKeyWord(java.util.ArrayList) }
     * </ul>
     * @param inFuncWord
     * @return
     */
    protected static boolean isLongWord(String inFuncWord){
        if (inFuncWord.length() > 25){
            return true;
        }
        return false;
    }
}
