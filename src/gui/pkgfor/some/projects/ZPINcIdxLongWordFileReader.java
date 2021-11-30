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
public class ZPINcIdxLongWordFileReader {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordManager#putLongWordInFile(java.util.TreeMap, ru.newcontrol.ncfv.ZPINcDcIdxLongWordListToFile) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordManager#getLongWordFromFile(java.util.TreeMap, ru.newcontrol.ncfv.ZPINcDcIdxLongWordListToFile) }
     * </ul>
     * @param readedWord
     * @param rID
     * @return 
     */    
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> ncReadFromLongWordFile(String readedWord, long rID){
        TreeMap<Long, ZPINcDcIdxWordToFile> ncDataFromWordFile;
        String strCfgPath =  readedWord;
        if ( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(strCfgPath))){
            return new TreeMap<Long, ZPINcDcIdxWordToFile>();
        };
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(strCfgPath)))
        {
            ncDataFromWordFile = (TreeMap<Long, ZPINcDcIdxWordToFile>)ois.readObject();
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcIdxLongWordFileReader.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDcIdxWordToFile>();
        } 
        return ncDataFromWordFile;
    }
}
