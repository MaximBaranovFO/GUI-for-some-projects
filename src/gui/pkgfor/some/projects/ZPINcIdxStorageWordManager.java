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
public class ZPINcIdxStorageWordManager {

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param typeWords
     * @param WordToStorage
     */
    protected static void putInStorageWord(String typeWords, TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> WordToStorage){
        
        TreeMap<Long, File> listFilesInStorage = new TreeMap<Long, File>();
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> readedData = new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> foundedData = new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> readedDataFormLastFile = new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        boolean foundAndUpdated = false;
        boolean appendNewRecord = false;
        long lastRecordId = -1;
        File lastRecordFile = new File("notInitFile");
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> itemWord : WordToStorage.entrySet() ){
            String wordInHex = itemWord.getValue().hexSubString;
            String word = itemWord.getValue().strSubString;
            listFilesInStorage = getStorageWordExistFiles(typeWords, wordInHex, word);
            if( !listFilesInStorage.isEmpty() ){
                for(Map.Entry<Long, File> itemFile : listFilesInStorage.entrySet() ){
                    readedData.putAll(ZPINcIdxStorageWordFileReader.ncReadFileContainedId(itemFile.getValue()));
                    foundedData.clear();
                    foundedData.putAll(searchRecordInStorageWord(readedData, wordInHex, word));
                    if( !foundedData.isEmpty() ){
                        foundedData.firstEntry().getValue().wordCount = foundedData.firstEntry().getValue().wordCount + 1;
                        readedData.put(foundedData.firstEntry().getKey(), foundedData.firstEntry().getValue());
                        int countOfWrited = ZPINcIdxStorageWordFileWriter.ncUpdateData(itemFile.getValue(), readedData);
                        foundAndUpdated = countOfWrited == readedData.size();
                    }
                    if( !readedData.isEmpty() ){
                        lastRecordId = readedData.lastKey();
                        lastRecordFile = itemFile.getValue();
                        readedDataFormLastFile.clear();
                        readedDataFormLastFile.putAll(readedData);
                    }
                    readedData.clear();
                }
                if( !foundAndUpdated ){
                    long forNewRecordId = lastRecordId + 1;
                    String strFileNameForRecord = getStorageWordByIdFile(typeWords, wordInHex, word, forNewRecordId);
                    if( ZPINcIdxFileManager.getStrCanPathFromFile(lastRecordFile).equalsIgnoreCase(strFileNameForRecord) ){
                        long nextWordId = readedDataFormLastFile.lastEntry().getValue().wordId++;
                        ZPINcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                        readedDataFormLastFile.put(forNewRecordId, toRecordData);
                        int countOfWrited = ZPINcIdxStorageWordFileWriter.ncUpdateData(lastRecordFile, readedDataFormLastFile);
                        appendNewRecord = countOfWrited == readedDataFormLastFile.size();
                    }
                    else{
                        long nextWordId = 0;
                        if( !readedDataFormLastFile.isEmpty() ){
                            nextWordId = readedDataFormLastFile.lastEntry().getValue().wordId++;
                        }
                        ZPINcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                        forNewRecordId = 0;
                        readedData.clear();
                        readedData.put(forNewRecordId, toRecordData);
                        File forRecord = new File(strFileNameForRecord);
                        int countOfWrited = ZPINcIdxStorageWordFileWriter.ncUpdateData(forRecord, readedData);
                        appendNewRecord = countOfWrited == readedDataFormLastFile.size();
                    }
                }
                //code to append
            }
            else{
                if( lastRecordId == -1 ){
                    long forNewRecordId = lastRecordId + 1;
                    String strFileNameForRecord = getStorageWordByIdFile(typeWords, wordInHex, word, forNewRecordId);
                    lastRecordFile = new File(strFileNameForRecord);
                    readedDataFormLastFile.clear();
                    long nextWordId = 0;
                    ZPINcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                    readedDataFormLastFile.put(forNewRecordId, toRecordData);
                    int countOfWrited = ZPINcIdxStorageWordFileWriter.ncUpdateData(lastRecordFile, readedDataFormLastFile);
                    appendNewRecord = countOfWrited == readedDataFormLastFile.size();
                }    
            }
            listFilesInStorage.clear();
        }        
    }
    protected static TreeMap<Long, ZPINcDcIdxStorageWordToFile> getFromStorageWordAllRecords(String typeWords){
        TreeMap<Long, File> listFilesInStorage = new TreeMap<Long, File>();
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> readedData = new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        
        listFilesInStorage = getStorageWordAllExistFiles(typeWords);
        if( !listFilesInStorage.isEmpty() ){
            for(Map.Entry<Long, File> itemFile : listFilesInStorage.entrySet() ){
                readedData.putAll(ZPINcIdxStorageWordFileReader.ncReadFileContainedId(itemFile.getValue()));
            }
        }
        listFilesInStorage.clear();
        
        return readedData;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param inFuncReadedData
     * @param wordInHex
     * @param word
     * @return
     */
    private static TreeMap<Long, ZPINcDcIdxStorageWordToFile> searchRecordInStorageWord(TreeMap<Long, ZPINcDcIdxStorageWordToFile> inFuncReadedData, String wordInHex, String word){
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> toReturnFoundedData = new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        for(Map.Entry<Long, ZPINcDcIdxStorageWordToFile> itemReadedData : inFuncReadedData.entrySet() ){
                boolean compareWordResult = word.hashCode() == itemReadedData.getValue().wordHash;
                boolean compareWordInHexResult = wordInHex.hashCode() == itemReadedData.getValue().wordInHexHash;
                if( compareWordResult && compareWordInHexResult ){
                    toReturnFoundedData.put(itemReadedData.getKey(), itemReadedData.getValue());
                    return toReturnFoundedData;
                }
            }
        return new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param typeWords
     * @param inFuncWordInHex
     * @param inFuncWord
     * @return
     */
    private static TreeMap<Long, File> getStorageWordExistFiles(String typeWords, String inFuncWordInHex, String inFuncWord){
        switch (typeWords){
            case "NCLVLABC":
                return ZPINcTypeOfWord.NCLVLABC.getStorageWordExistFileName(inFuncWordInHex, inFuncWord);
            case "NCLVLRABC":
                return ZPINcTypeOfWord.NCLVLRABC.getStorageWordExistFileName(inFuncWordInHex, inFuncWord);
            case "NCLVLNUM":
                return ZPINcTypeOfWord.NCLVLNUM.getStorageWordExistFileName(inFuncWordInHex, inFuncWord);
            case "NCLVLSYM":
                return ZPINcTypeOfWord.NCLVLSYM.getStorageWordExistFileName(inFuncWordInHex, inFuncWord);
            case "NCLVLSPACE":
                return ZPINcTypeOfWord.NCLVLSPACE.getStorageWordExistFileName(inFuncWordInHex, inFuncWord);
        }
        return new TreeMap<Long, File>();
    }
    private static TreeMap<Long, File> getStorageWordAllExistFiles(String typeWords){
        switch (typeWords){
            case "NCLVLABC":
                return ZPINcTypeOfWord.NCLVLABC.getStorageWordAllExistFileName();
            case "NCLVLRABC":
                return ZPINcTypeOfWord.NCLVLRABC.getStorageWordAllExistFileName();
            case "NCLVLNUM":
                return ZPINcTypeOfWord.NCLVLNUM.getStorageWordAllExistFileName();
            case "NCLVLSYM":
                return ZPINcTypeOfWord.NCLVLSYM.getStorageWordAllExistFileName();
            case "NCLVLSPACE":
                return ZPINcTypeOfWord.NCLVLSPACE.getStorageWordAllExistFileName();
        }
        return new TreeMap<Long, File>();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param typeWords
     * @param inFuncWordInHex
     * @param inFuncWord
     * @param inFuncId
     * @return
     */
    private static String getStorageWordByIdFile(String typeWords, String inFuncWordInHex, String inFuncWord, long inFuncId){
        switch (typeWords){
            case "NCLVLABC":
                return ZPINcTypeOfWord.NCLVLABC.getStorageWordByIdFileName(inFuncWordInHex, inFuncWord, inFuncId);
            case "NCLVLRABC":
                return ZPINcTypeOfWord.NCLVLRABC.getStorageWordByIdFileName(inFuncWordInHex, inFuncWord, inFuncId);
            case "NCLVLNUM":
                return ZPINcTypeOfWord.NCLVLNUM.getStorageWordByIdFileName(inFuncWordInHex, inFuncWord, inFuncId);
            case "NCLVLSYM":
                return ZPINcTypeOfWord.NCLVLSYM.getStorageWordByIdFileName(inFuncWordInHex, inFuncWord, inFuncId);
            case "NCLVLSPACE":
                return ZPINcTypeOfWord.NCLVLSPACE.getStorageWordByIdFileName(inFuncWordInHex, inFuncWord, inFuncId);
        }
        return "canNotCreateName";
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param inFuncData
     * @param inFuncNextWordId
     * @return
     */
    private static ZPINcDcIdxStorageWordToFile convertFormOperationToFileData(ZPINcDcIdxSubStringToOperationUse inFuncData, long inFuncNextWordId){
        ZPINcDcIdxStorageWordToFile toReturn = new ZPINcDcIdxStorageWordToFile(
                inFuncNextWordId,
                ZPINcIdxLongWordManager.isLongWord(inFuncData.strSubString),
                inFuncData.hexSubString,
                inFuncData.strSubString,
                1
        );
        return toReturn;
    }
}
