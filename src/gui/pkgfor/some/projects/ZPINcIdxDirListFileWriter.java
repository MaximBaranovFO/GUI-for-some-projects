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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxDirListFileWriter {
    /**
     * Directory List
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListManager#putToDirectoryList(ru.newcontrol.ncfv.NcDcIdxDirListToFileAttr) }
     * </ul>
     * @param ncDataToDirListFile
     * @param recID
     * @return 
     */    
    protected static int ncWriteToDirListFile(TreeMap<Long, NcDcIdxDirListToFileAttr> ncDataToDirListFile, long recID){
        if( ncDataToDirListFile == null ){
            return -1;
        }
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(NcIdxFileManager.getFileNameToRecord(NcIdxFileManager.getStrCanPathFromFile(NcManageCfg.getDirList())+"/dl",recID))))
        {
            oos.writeObject(ncDataToDirListFile);
        }
        catch(Exception ex){
            NcAppHelper.logException(
                    NcIdxDirListFileWriter.class.getCanonicalName(), ex);
            return -1;
        } 
        return ncDataToDirListFile.size();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListExistManager#putToDirListExistStart(ru.newcontrol.ncfv.NcDcIdxDirListToFileExist, long) }
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListExistManager#putToDirListExistStop(ru.newcontrol.ncfv.NcDcIdxDirListToFileExist, long) }
     * </ul>
     * @param ncDataToDirListFile
     * @param dirListID
     * @return
     */
    protected static int ncWriteToDirListExist(TreeMap<Long, NcDcIdxDirListToFileExist> ncDataToDirListFile, long dirListID){
        String strCfgPath = NcIdxFileManager.getFileNameToRecord(NcIdxFileManager.getStrCanPathFromFile(NcManageCfg.getDirListExist()) + "/e", dirListID);
        if( ncDataToDirListFile == null ){
            return -1;
        }
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(strCfgPath)))
        {
            oos.writeObject(ncDataToDirListFile);
        }
        catch(Exception ex){
            NcAppHelper.logException(
                    NcIdxDirListFileWriter.class.getCanonicalName(), ex);
            return -1;
        } 
        return ncDataToDirListFile.size();
    }
}
