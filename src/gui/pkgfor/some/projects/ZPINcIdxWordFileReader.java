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
import java.io.ObjectInputStream;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxWordFileReader {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxWordManager#putWord(java.util.TreeMap) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxWordManager#getWord(java.util.TreeMap) }
     * </ul>
     * @param readedWord
     * @param rID
     * @return 
     */    
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> ncReadFromWord(String readedWord, long rID){
        TreeMap<Long, ZPINcDcIdxWordToFile> ncDataFromWordFile;
        String strCfgPath =  ZPINcIdxFileManager.getFileNameToRecord(
                ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirWords()) + "/w-" + readedWord, rID);
        if ( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(strCfgPath))){
            return new TreeMap<Long, ZPINcDcIdxWordToFile>();
        };
        //mcGetWorkCfgDirName() + workFileNames[0];
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(strCfgPath)))
        {
            ncDataFromWordFile = (TreeMap<Long, ZPINcDcIdxWordToFile>)ois.readObject();
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcIdxWordFileReader.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDcIdxWordToFile>();
        } 
        return ncDataFromWordFile;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxWordManager#getAllDataForWord(java.lang.String) }
     * </ul>
     * @param inFuncFile
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> ncReadFromWordFile(File inFuncFile){
        TreeMap<Long, ZPINcDcIdxWordToFile> ncDataFromWordFile;
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inFuncFile)))
        {
            ncDataFromWordFile = (TreeMap<Long, ZPINcDcIdxWordToFile>)ois.readObject();
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcIdxWordFileReader.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDcIdxWordToFile>();
        } 
        return ncDataFromWordFile;
    }
}
