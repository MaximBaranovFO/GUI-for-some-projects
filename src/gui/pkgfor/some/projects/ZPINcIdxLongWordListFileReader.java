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
public class ZPINcIdxLongWordListFileReader {
    /**
     * Directory List Word Long
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordListManager#getOrCreateLongWordID(ru.newcontrol.ncfv.ZPINcDcIdxLongWordListToFile) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxLongWordManager#getForSearchLongWordID(ru.newcontrol.ncfv.ZPINcDcIdxLongWordListToFile) }
     * </ul>
     * @param dataForRead
     * @param rID
     * @return 
     */      
    protected static TreeMap<Long, ZPINcDcIdxLongWordListToFile> ncReadFileContainedId(ZPINcDcIdxLongWordListToFile dataForRead, long rID){
        TreeMap<Long, ZPINcDcIdxLongWordListToFile> ncDataFromDirList;
        String strCfgPath = ZPINcIdxFileManager.getFileNameToRecord(
                ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcManageCfg.getDirLongWordList()) + "/wl-"
                + dataForRead.name.substring(0, 4),rID);
        if ( !ZPINcIdxFileManager.fileExistRWAccessChecker(new File(strCfgPath))){
            return new TreeMap<Long, ZPINcDcIdxLongWordListToFile>();
        }
        //mcGetWorkCfgDirName() + workFileNames[0];
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(strCfgPath)))
        {
            ncDataFromDirList = (TreeMap<Long, ZPINcDcIdxLongWordListToFile>)ois.readObject();
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcIdxLongWordListFileReader.class.getCanonicalName(), ex);
            return new TreeMap<Long, ZPINcDcIdxLongWordListToFile>();
        } 
        return ncDataFromDirList;
    }
    
}
