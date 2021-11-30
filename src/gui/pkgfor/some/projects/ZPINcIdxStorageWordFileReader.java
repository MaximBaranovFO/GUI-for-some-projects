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
public class ZPINcIdxStorageWordFileReader {

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxStorageWordManager#putInStorageWord(java.lang.String, java.util.TreeMap) }
     * </ul>
     * @param inFuncFile
     * @return
     */
    protected static TreeMap<Long, ZPINcDcIdxStorageWordToFile> ncReadFileContainedId(File inFuncFile){
        TreeMap<Long, ZPINcDcIdxStorageWordToFile> ncReadedData;
        
        if ( !ZPINcIdxFileManager.fileExistRWAccessChecker(inFuncFile)){
            return new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        };
        //mcGetWorkCfgDirName() + workFileNames[0];
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inFuncFile)))
        {
            ncReadedData = (TreeMap<Long, ZPINcDcIdxStorageWordToFile>)ois.readObject();
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcIdxStorageWordFileReader.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDcIdxStorageWordToFile>();
        } 
        return ncReadedData;
    }
}
