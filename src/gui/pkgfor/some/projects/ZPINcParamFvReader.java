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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcParamFvReader {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcEtcKeyWordListManager#getKeyWordInSearchFromFile() }
     * <li>{@link ru.newcontrol.ncfv.NcEtcKeyWordListManager#getKeyWordOutSearchFromFile() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getIndexWorkSubDirFilesList() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getTmpIdsFile() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getCurrentWorkCfg() }
     * </ul>
     * Read data from serializable 
     * {@link ru.newcontrol.ncfv.ZPINcParamFv}
     * saved on the disk in *.dat file
     * and return
     * @return empty object if it not read or not set before
     */
    protected static ZPINcParamFv readDataFromWorkCfg(){
        ZPINcParamFv readedDiskInfo;
        String strDataInAppDir = ZPINcIdxFileManager.getWorkCfgPath();
        if( strDataInAppDir.length() < 1 ){
            ZPINcLogLogicCfg.NcParamFvReaderReadDataGenerate();
            return new ZPINcParamFv();
        }
        File fileJornalDisk = new File(strDataInAppDir);
        if( !ZPINcIdxFileManager.fileExistRWAccessChecker(fileJornalDisk) ){
            ZPINcLogLogicCfg.NcParamFvReaderReadDataGenerate();
            return new ZPINcParamFv();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(strDataInAppDir)))
        {
            readedDiskInfo = (ZPINcParamFv)ois.readObject();
            ZPINcParamFvManager.checkFromRead(readedDiskInfo);
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcPreRunFileViewer.class.getCanonicalName(), ex);
            ZPINcLogLogicCfg.NcParamFvReaderReadDataGenerate();
            return new ZPINcParamFv();
        }
        ZPINcLogLogicCfg.NcParamFvReaderReadDataRead();
        return readedDiskInfo;
    }

}
