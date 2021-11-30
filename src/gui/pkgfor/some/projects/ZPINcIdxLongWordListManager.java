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
public class ZPINcIdxLongWordListManager {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param StructureLongWord
     * @return 
     */    
    protected static long putLongWord(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureLongWord){
        long countWritedIDs = 0;
        String nameLongWordList = "";
        ZPINcDcIdxLongWordListToFile dataForWrite;
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureLongWord.entrySet()){
            long recLongWordListID = 0;
            String hexWord = item.getValue().hexSubString;
            dataForWrite = new ZPINcDcIdxLongWordListToFile(
                    recLongWordListID,
                    hexWord,
                    item.getValue().strSubString);            
            dataForWrite = getOrCreateLongWordID(dataForWrite);
            recLongWordListID = dataForWrite.nameID;
            TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> subStructureLongWord = new TreeMap<Long, ZPINcDcIdxSubStringToOperationUse>();
            subStructureLongWord.put(recLongWordListID,item.getValue());
            countWritedIDs = countWritedIDs + ZPINcIdxLongWordManager.putLongWordInFile(subStructureLongWord,dataForWrite);
        }
        return countWritedIDs;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordListManager#putLongWord(java.util.TreeMap) }
     * </ul>
     * @param dataForWrite
     * @return 
     */    
    private static ZPINcDcIdxLongWordListToFile getOrCreateLongWordID(ZPINcDcIdxLongWordListToFile dataForWrite){
        String nameLongWordList = "";
        String nameNextLongWordList = "";
        long recID = dataForWrite.nameID;
        long countReadedFiles = 0;
        nameLongWordList = ZPINcIdxFileManager.getFileNameToRecord(
                ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWordList()) + "/wl-"
                + dataForWrite.name.substring(0, 4),recID);
        boolean isNamesEquals = false;
        boolean isNameEquals = false;
        boolean isNameHashEquals = false;
        boolean isSubStrEquals = false;
        boolean isSubStrHashEquals = false;
        TreeMap<Long, ZPINcDcIdxLongWordListToFile> readFromDiskData = new TreeMap<Long, ZPINcDcIdxLongWordListToFile>();
        do{
            readFromDiskData.clear();
            readFromDiskData = ZPINcIdxLongWordListFileReader.ncReadFileContainedId(dataForWrite, recID);
            for(Map.Entry<Long, ZPINcDcIdxLongWordListToFile> item  : readFromDiskData.entrySet()){
                isNameEquals = item.getValue().name.equalsIgnoreCase(dataForWrite.name);
                isNameHashEquals = item.getValue().nameHash == dataForWrite.nameHash;
                isSubStrEquals = item.getValue().word.equalsIgnoreCase(dataForWrite.word);
                isSubStrHashEquals = item.getValue().wordHash == dataForWrite.wordHash;
                if(isNameEquals
                        && isNameHashEquals
                        && isSubStrEquals
                        && isSubStrHashEquals){
                    return item.getValue();
                }
            }
            if(readFromDiskData.isEmpty()){
                readFromDiskData.put(recID, dataForWrite);
                break;
            }
            recID = readFromDiskData.lastEntry().getValue().nameID + 1; 
            nameNextLongWordList = ZPINcIdxFileManager.getFileNameToRecord(
                    ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWordList()) + "/wl-"
                    + dataForWrite.name.substring(0, 4),recID);
            isNamesEquals = ! nameLongWordList.equalsIgnoreCase(nameNextLongWordList);
            if(isNamesEquals){
                dataForWrite.nameID = recID;
                countReadedFiles++;
                readFromDiskData.put(recID, dataForWrite);
                if( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(nameNextLongWordList))){
                    break;
                }
            }
        }
        while(isNamesEquals);
        ZPINcIdxLongWordListFileWriter.ncWriteData(readFromDiskData, dataForWrite, recID);
        return dataForWrite;
    }
}
