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
public class ZPINcIdxDirListExistManager {

    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param toWriteData
     * @param dirListID
     * @return
     */
    protected static long putToDirListExistStart(ZPINcDcIdxDirListToFileExist toWriteData, long dirListID){
        TreeMap<Long, ZPINcDcIdxDirListToFileExist> ncReadFromFileData;
        TreeMap<Long, ZPINcDcIdxDirListToFileExist> ncWriteToFileData;
        ncWriteToFileData = new  TreeMap<>();
        ncReadFromFileData = ZPINcIdxDirListFileReader.ncReadFromDirListExist(dirListID);

        if( ncReadFromFileData.isEmpty() ){
            ncWriteToFileData.put(dirListID, toWriteData);
        }
        else{
            ncWriteToFileData.putAll(ncReadFromFileData);
            ncWriteToFileData.put(dirListID, toWriteData);
        }
        ZPINcIdxDirListFileWriter.ncWriteToDirListExist(ncWriteToFileData, dirListID);
        return ncWriteToFileData.size();
    }

    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @param toWriteData
     * @param dirListID
     * @return
     */
    protected static long putToDirListExistStop(ZPINcDcIdxDirListToFileExist toWriteData, long dirListID){
        TreeMap<Long, ZPINcDcIdxDirListToFileExist> ncReadFromFileData;
        TreeMap<Long, ZPINcDcIdxDirListToFileExist> ncWriteToFileData;
        ncWriteToFileData = new  TreeMap<>();
        ncReadFromFileData = ZPINcIdxDirListFileReader.ncReadFromDirListExist(dirListID);

        if( ncReadFromFileData.isEmpty() ){
            return -1;
        }
        else{
            ncWriteToFileData.put(dirListID, toWriteData);
            for(Map.Entry<Long, ZPINcDcIdxDirListToFileExist> itemIDSearch : ncReadFromFileData.entrySet()){
                if( itemIDSearch.getValue().dirListID == dirListID ){
                    ncReadFromFileData.put(itemIDSearch.getKey(), toWriteData);
                }
            }
            ncWriteToFileData.putAll(ncReadFromFileData);
        }
        ZPINcIdxDirListFileWriter.ncWriteToDirListExist(ncWriteToFileData, dirListID);
        return ncWriteToFileData.size();
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirListExistDataWrong(ZPINcDcIdxDirListToFileExist inFuncData){
        if( inFuncData == null ){
            return true;
        }
        if( !isDirListExistDataDataHashTrue(inFuncData) ){
            return true;
        }
        return false;
    }
    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirListExistDataHasEmptyFiled(ZPINcDcIdxDirListToFileExist inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean dirListIdIsEmpty = inFuncData.dirListID == -777;
        boolean diskIdIsEmpty = inFuncData.diskID == -777;
        boolean pathWithOutDiskLetterIsEmpty = inFuncData.pathWithOutDiskLetter == "";
        boolean pathHashIsEmpty = inFuncData.pathHash == -777;
        boolean nanoTimeStartAddToIndexIsEmpty = inFuncData.nanoTimeStartAddToIndex == -777;
        boolean nanoTimeEndAddToIndexIsEmpty = inFuncData.nanoTimeEndAddToIndex == -777;
        return dirListIdIsEmpty
                || diskIdIsEmpty
                || pathWithOutDiskLetterIsEmpty
                || pathHashIsEmpty
                || nanoTimeStartAddToIndexIsEmpty
                || nanoTimeEndAddToIndexIsEmpty;
    }
    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isDirListExistDataDataEmpty(ZPINcDcIdxDirListToFileExist inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean dirListIdIsEmpty = inFuncData.dirListID == -777;
        boolean diskIdIsEmpty = inFuncData.diskID == -777;
        boolean pathWithOutDiskLetterIsEmpty = inFuncData.pathWithOutDiskLetter == "";
        boolean pathHashIsEmpty = inFuncData.pathHash == -777;
        boolean nanoTimeStartAddToIndexIsEmpty = inFuncData.nanoTimeStartAddToIndex == -777;
        boolean nanoTimeEndAddToIndexIsEmpty = inFuncData.nanoTimeEndAddToIndex == -777;
        boolean hashIsTrue =  isDirListExistDataDataHashTrue(inFuncData);
        return dirListIdIsEmpty
                && diskIdIsEmpty
                && pathWithOutDiskLetterIsEmpty
                && pathHashIsEmpty
                && nanoTimeStartAddToIndexIsEmpty
                && nanoTimeEndAddToIndexIsEmpty
                && hashIsTrue;
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListExistManager#isDirListExistDataWrong(ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist) }
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListExistManager#isDirListExistDataDataEmpty(ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist) }
     * </ul>
     * @param inFuncData
     * @return
     */
    private static boolean isDirListExistDataDataHashTrue(ZPINcDcIdxDirListToFileExist inFuncData){
        return inFuncData.recordHash == (
                ""
                + inFuncData.dirListID
                + inFuncData.diskID
                + inFuncData.pathWithOutDiskLetter
                + inFuncData.pathHash
                + inFuncData.nanoTimeStartAddToIndex
                + inFuncData.nanoTimeEndAddToIndex
                + inFuncData.recordTime).hashCode();
    }
    
}
