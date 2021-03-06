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
public class ZPINcIdxWordManager {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param StructureWord
     * @return 
     */    
    protected static long putWord(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureWord){
        long countWritedIDs = 0;
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureWord.entrySet()){
            String hexWord = item.getValue().hexSubString;
            //detect last recorded ID for word
            //get file name for now record
            long recID = 0;
            
            //ArrayList<String> strCfgPath = getDirForWordFileNames(hexWord);
            //recID = getLastIDForWord(strCfgPath);
            String oldName = "";
            String forRecName = "";
            TreeMap<Long, ZPINcDcIdxWordToFile> inDiskWordRecord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
            boolean isEqualNames = false;
            do{
                inDiskWordRecord = ZPINcIdxWordFileReader.ncReadFromWord(hexWord,recID);
                oldName = ZPINcIdxFileManager.getFileNameToRecord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirWords()) + "/w-" + hexWord,recID);
                if(inDiskWordRecord.isEmpty()){
                    break;
                }
                recID = inDiskWordRecord.lastEntry().getValue().recordID;
                recID++;
                forRecName = ZPINcIdxFileManager.getFileNameToRecord(
                        ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirWords()) + "/w-" + hexWord,recID);
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
            countWritedIDs = countWritedIDs + ZPINcIdxWordFileWriter.ncWriteForWord(inDiskWordRecord, hexWord, recID);
        }
        
        return countWritedIDs;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSearchInIndex#getIDsForKeyWord(java.lang.String) }
     * </ul>
     * @param StructureWord
     * @return 
     */    
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getWord(TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureWord){
        long countWritedIDs = 0;
        TreeMap<Long, ZPINcDcIdxWordToFile> retInDiskWordRecord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureWord.entrySet()){
            String hexWord = item.getValue().hexSubString;
            long recID = 0;
            String oldName = "";
            String forRecName = "";
            TreeMap<Long, ZPINcDcIdxWordToFile> inDiskWordRecord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
            boolean isEqualNames = false;
            do{
                inDiskWordRecord.clear();
                inDiskWordRecord = ZPINcIdxWordFileReader.ncReadFromWord(hexWord,recID);
                oldName = ZPINcIdxFileManager.getFileNameToRecord(ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirWords()) + "/w-" + hexWord,recID);
                if(inDiskWordRecord.isEmpty()){
                    break;
                }
                retInDiskWordRecord.putAll(inDiskWordRecord);
                recID = inDiskWordRecord.lastEntry().getValue().recordID;
                recID++;
                forRecName = ZPINcIdxFileManager.getFileNameToRecord(ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirWords()) + "/w-" + hexWord,recID);
                isEqualNames = ! oldName.equalsIgnoreCase(forRecName);
                if(isEqualNames){
                    if( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(forRecName))){
                        break;
                    }
                }
            }
            while(isEqualNames);
        }
        return retInDiskWordRecord;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isWordWrong(ZPINcDcIdxWordToFile inFuncData){
        if( inFuncData == null ){
            return true;
        }
        if( !isWordDataHashTrue(inFuncData) ){
            return true;
        }
        return false;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isWordHasEmptyFiled(ZPINcDcIdxWordToFile inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean recordIDIsEmpty = inFuncData.recordID == -777;
        boolean dirListIDIsEmpty = inFuncData.dirListID == -777;
        boolean wordIsEmpty = inFuncData.word == "";
        boolean wordHashIsEmpty = inFuncData.wordHash == -777;
        boolean pathPositionIsEmpty = inFuncData.pathPosition == -777;
        boolean wordLengthIsEmpty = inFuncData.wordLength == -777;
        return recordIDIsEmpty
                || dirListIDIsEmpty
                || wordIsEmpty
                || wordHashIsEmpty
                || pathPositionIsEmpty
                || wordLengthIsEmpty;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isWordDataEmpty(ZPINcDcIdxWordToFile inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean recordIDIsEmpty = inFuncData.recordID == -777;
        boolean dirListIDIsEmpty = inFuncData.dirListID == -777;
        boolean wordIsEmpty = inFuncData.word == "";
        boolean wordHashIsEmpty = inFuncData.wordHash == -777;
        boolean pathPositionIsEmpty = inFuncData.pathPosition == -777;
        boolean wordLengthIsEmpty = inFuncData.wordLength == -777;
        boolean hashIsTrue =  isWordDataHashTrue(inFuncData);
        return recordIDIsEmpty
                && dirListIDIsEmpty
                && wordIsEmpty
                && wordHashIsEmpty
                && pathPositionIsEmpty
                && wordLengthIsEmpty
                && hashIsTrue;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxWordManager#isWordWrong(ru.newcontrol.ncfv.ZPINcDcIdxWordToFile) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcIdxWordManager#isWordDataEmpty(ru.newcontrol.ncfv.ZPINcDcIdxWordToFile) }
     * </ul>
     * @param inFuncData
     * @return
     */
    protected static boolean isWordDataHashTrue(ZPINcDcIdxWordToFile inFuncData){
        return inFuncData.recordHash == (
                ""
                + inFuncData.recordID
                + inFuncData.dirListID
                + inFuncData.word
                + inFuncData.wordHash
                + inFuncData.pathPosition
                + inFuncData.wordLength
                + inFuncData.recordTime).hashCode();
    }
    /**
     * Not used
     * @param inFuncListKeyWords
     * @return 
     */
    private static TreeMap<Long, ZPINcDcIdxWordToFile> getSearchedWordData(TreeMap<Long, String> inFuncListKeyWords){
        return new TreeMap<Long, ZPINcDcIdxWordToFile>();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxWordManager#getAllDataForWord(java.lang.String) }
     * </ul>
     * Return TreeMap<Long, File> structure for words and heximal view of words, existing in index /w sub dirictory
     * @param wordInHex
     * @param word
     * @return 
     */
    private static TreeMap<Long, File> getWordExistFile(String wordInHex){
        TreeMap<Integer, File> listDirs = ZPINcIdxFileManager.getIndexWorkSubDirFilesList();
        File filePathDir = listDirs.get("/w".hashCode());
        boolean boolCheck = ZPINcIdxFileManager.dirExistRWAccessChecker(filePathDir);
        if( !boolCheck ){
            if( !filePathDir.mkdirs() ){
                return new TreeMap<Long, File>();
            }
        }
        long recordID = 0;
        File fileWithRecords;
        TreeMap<Long, File> listFiles = new TreeMap<Long, File>();
        do{
            String fileName = ZPINcIdxFileManager.getFileNameToRecord("/w-" + wordInHex, recordID);
            
            String strPathFile = ZPINcIdxFileManager.strPathCombiner(ZPINcIdxFileManager.getStrCanPathFromFile(filePathDir), fileName);
            
            fileWithRecords = new File(strPathFile);
            if( fileWithRecords.exists() ){
                listFiles.put(recordID, fileWithRecords);
            }
            recordID = recordID + 100;
        }
        while( fileWithRecords.exists() );
        return listFiles;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSrchKeyWordInput#getIdDataForSplittedKeyWord(java.util.ArrayList) }
     * </ul>
     * @param wordInHex
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getAllDataForWord(String wordInHex){
        TreeMap<Long, ZPINcDcIdxWordToFile> toReturnData = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, File> existFileList = new TreeMap<Long, File>();
        
        existFileList = getWordExistFile(wordInHex);
        
        if( existFileList.isEmpty() ){
            return toReturnData;
        }
        for( Map.Entry<Long, File> itemFile : existFileList.entrySet() ){
            toReturnData.putAll(ZPINcIdxWordFileReader.ncReadFromWordFile(itemFile.getValue()));
        }
        return toReturnData;
    }
    
}
