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
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param typeWords
     * @param WordToStorage
     */
    protected static void putInStorageWord(String typeWords, TreeMap<Long, NcDcIdxSubStringToOperationUse> WordToStorage){
        
        TreeMap<Long, File> listFilesInStorage = new TreeMap<Long, File>();
        TreeMap<Long, NcDcIdxStorageWordToFile> readedData = new TreeMap<Long, NcDcIdxStorageWordToFile>();
        TreeMap<Long, NcDcIdxStorageWordToFile> foundedData = new TreeMap<Long, NcDcIdxStorageWordToFile>();
        TreeMap<Long, NcDcIdxStorageWordToFile> readedDataFormLastFile = new TreeMap<Long, NcDcIdxStorageWordToFile>();
        boolean foundAndUpdated = false;
        boolean appendNewRecord = false;
        long lastRecordId = -1;
        File lastRecordFile = new File("notInitFile");
        for(Map.Entry<Long, NcDcIdxSubStringToOperationUse> itemWord : WordToStorage.entrySet() ){
            String wordInHex = itemWord.getValue().hexSubString;
            String word = itemWord.getValue().strSubString;
            listFilesInStorage = getStorageWordExistFiles(typeWords, wordInHex, word);
            if( !listFilesInStorage.isEmpty() ){
                for(Map.Entry<Long, File> itemFile : listFilesInStorage.entrySet() ){
                    readedData.putAll(NcIdxStorageWordFileReader.ncReadFileContainedId(itemFile.getValue()));
                    foundedData.clear();
                    foundedData.putAll(searchRecordInStorageWord(readedData, wordInHex, word));
                    if( !foundedData.isEmpty() ){
                        foundedData.firstEntry().getValue().wordCount = foundedData.firstEntry().getValue().wordCount + 1;
                        readedData.put(foundedData.firstEntry().getKey(), foundedData.firstEntry().getValue());
                        int countOfWrited = NcIdxStorageWordFileWriter.ncUpdateData(itemFile.getValue(), readedData);
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
                    if( NcIdxFileManager.getStrCanPathFromFile(lastRecordFile).equalsIgnoreCase(strFileNameForRecord) ){
                        long nextWordId = readedDataFormLastFile.lastEntry().getValue().wordId++;
                        NcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                        readedDataFormLastFile.put(forNewRecordId, toRecordData);
                        int countOfWrited = NcIdxStorageWordFileWriter.ncUpdateData(lastRecordFile, readedDataFormLastFile);
                        appendNewRecord = countOfWrited == readedDataFormLastFile.size();
                    }
                    else{
                        long nextWordId = 0;
                        if( !readedDataFormLastFile.isEmpty() ){
                            nextWordId = readedDataFormLastFile.lastEntry().getValue().wordId++;
                        }
                        NcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                        forNewRecordId = 0;
                        readedData.clear();
                        readedData.put(forNewRecordId, toRecordData);
                        File forRecord = new File(strFileNameForRecord);
                        int countOfWrited = NcIdxStorageWordFileWriter.ncUpdateData(forRecord, readedData);
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
                    NcDcIdxStorageWordToFile toRecordData = convertFormOperationToFileData(itemWord.getValue(), nextWordId);
                    readedDataFormLastFile.put(forNewRecordId, toRecordData);
                    int countOfWrited = NcIdxStorageWordFileWriter.ncUpdateData(lastRecordFile, readedDataFormLastFile);
                    appendNewRecord = countOfWrited == readedDataFormLastFile.size();
                }    
            }
            listFilesInStorage.clear();
        }        
    }
    protected static TreeMap<Long, NcDcIdxStorageWordToFile> getFromStorageWordAllRecords(String typeWords){
        TreeMap<Long, File> listFilesInStorage = new TreeMap<Long, File>();
        TreeMap<Long, NcDcIdxStorageWordToFile> readedData = new TreeMap<Long, NcDcIdxStorageWordToFile>();
        
        listFilesInStorage = getStorageWordAllExistFiles(typeWords);
        if( !listFilesInStorage.isEmpty() ){
            for(Map.Entry<Long, File> itemFile : listFilesInStorage.entrySet() ){
                readedData.putAll(NcIdxStorageWordFileReader.ncReadFileContainedId(itemFile.getValue()));
            }
        }
        listFilesInStorage.clear();
        
        return readedData;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param inFuncReadedData
     * @param wordInHex
     * @param word
     * @return
     */
    private static TreeMap<Long, NcDcIdxStorageWordToFile> searchRecordInStorageWord(TreeMap<Long, NcDcIdxStorageWordToFile> inFuncReadedData, String wordInHex, String word){
        TreeMap<Long, NcDcIdxStorageWordToFile> toReturnFoundedData = new TreeMap<Long, NcDcIdxStorageWordToFile>();
        for(Map.Entry<Long, NcDcIdxStorageWordToFile> itemReadedData : inFuncReadedData.entrySet() ){
                boolean compareWordResult = word.hashCode() == itemReadedData.getValue().wordHash;
                boolean compareWordInHexResult = wordInHex.hashCode() == itemReadedData.getValue().wordInHexHash;
                if( compareWordResult && compareWordInHexResult ){
                    toReturnFoundedData.put(itemReadedData.getKey(), itemReadedData.getValue());
                    return toReturnFoundedData;
                }
            }
        return new TreeMap<Long, NcDcIdxStorageWordToFile>();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
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
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
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
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param inFuncData
     * @param inFuncNextWordId
     * @return
     */
    private static NcDcIdxStorageWordToFile convertFormOperationToFileData(NcDcIdxSubStringToOperationUse inFuncData, long inFuncNextWordId){
        NcDcIdxStorageWordToFile toReturn = new NcDcIdxStorageWordToFile(
                inFuncNextWordId,
                NcIdxLongWordManager.isLongWord(inFuncData.strSubString),
                inFuncData.hexSubString,
                inFuncData.strSubString,
                1
        );
        return toReturn;
    }
}
