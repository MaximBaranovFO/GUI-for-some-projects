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
import java.io.IOException;

/**
 *
 * @author Администратор
 */
public class ZPINcParamCfgToDiskReleaser {
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcManageCfg#mcSearchOrSetWorkDir() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param strIndexPath
     * @return
     */
    protected static boolean checkOrCreateIdxDirStructure(String strIndexPath){
        File fileWorkDir = new File(strIndexPath);
        boolean boolResultCreation = false;
        
        boolResultCreation = ZPINcIdxFileManager.dirExistRWAccessChecker(fileWorkDir);
        if( !boolResultCreation ){
            boolResultCreation = fileWorkDir.mkdirs();
        }
        String[] strSubDirs = ZPINcManageCfg.getWorkSubDirList();
        for( String itemSubDir : strSubDirs ){
            boolResultCreation = boolResultCreation && createSubDir(fileWorkDir, itemSubDir);
        }
        return boolResultCreation;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamCfgToDiskReleaser#checkOrCreateIdxDirStructure(java.lang.String) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getIndexSubDirectories(java.lang.String) }
     * </ul>
     * @param existParentDir
     * @param subDirName
     * @return
     */
    protected static boolean createSubDir(File existParentDir, String subDirName){
        String strPathName = ZPINcIdxFileManager.strPathCombiner(ZPINcIdxFileManager.getStrCanPathFromFile(existParentDir), subDirName);
        File fileForCreateDir = new File(strPathName);
        boolean boolCheck = ZPINcIdxFileManager.dirExistRWAccessChecker(fileForCreateDir);
        if( !boolCheck ){
            return fileForCreateDir.mkdirs();
        }
        return boolCheck;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param strFileName
     * @return
     */
    protected static boolean checkOrCreateFiles(String strFileName){
        
        File fileForCreateDir = new File(strFileName);
        if( fileForCreateDir.isDirectory() ){
            return false;
        }
        boolean boolCheck = ZPINcIdxFileManager.fileExistRWAccessChecker(fileForCreateDir);
        if( !boolCheck ){
            if( !ZPINcIdxFileManager.dirExistRWAccessChecker(fileForCreateDir.getParentFile()) ){
                fileForCreateDir.getParentFile().mkdirs();
            }
            try {
                if( !fileForCreateDir.exists() ){
                    return fileForCreateDir.createNewFile();
                }
            } catch (IOException ex) {
                ZPINcAppHelper.logException(
                    ZPINcParamCfgToDiskReleaser.class.getCanonicalName(), ex);
                return false;
            }
        }
        return boolCheck;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param strFileName
     */
    protected static void getWorkFileParams(String strFileName){
        File fileWork = new File(strFileName);
        
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrServiceMsg.PATH_WORK_FILE.getStr()
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcIdxFileManager.getStrCanPathFromFile(fileWork)
            + ZPINcStrServiceMsg.EXIST.getStr()
            + fileWork.exists() 
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcStrServiceMsg.CANREAD.getStr()
            + fileWork.canRead() 
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcStrServiceMsg.CANWRITE.getStr()
            + fileWork.canWrite() 
            + ZPINcStrServiceMsg.NEWLINE.getStr());
        
    }    

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param strIndexPath
     */
    protected static void getIdxDirStructure(String strIndexPath){
        File fileWorkDir = new File(strIndexPath);
        
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrServiceMsg.PATH_INDEX_DIRECTORY.getStr()
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcIdxFileManager.getStrCanPathFromFile(fileWorkDir)
            + ZPINcStrServiceMsg.EXIST.getStr()
            + fileWorkDir.exists()
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcStrServiceMsg.CANREAD.getStr()
            + fileWorkDir.canRead()
            + ZPINcStrServiceMsg.NEWLINE.getStr()
            + ZPINcStrServiceMsg.CANWRITE.getStr()
            + fileWorkDir.canWrite()
            + ZPINcStrServiceMsg.NEWLINE.getStr());
        
        
        String[] strSubDirs = ZPINcManageCfg.getWorkSubDirList();
        for( String itemSubDir : strSubDirs ){
            String strPathSubDir = ZPINcIdxFileManager.strPathCombiner(ZPINcIdxFileManager.getStrCanPathFromFile(fileWorkDir), itemSubDir);
            File pathSubDir = new File(strPathSubDir);
            ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrServiceMsg.PATH_SUBDIR.getStr()
                + ZPINcStrServiceMsg.NEWLINE.getStr()
                + ZPINcIdxFileManager.getStrCanPathFromFile(pathSubDir)
                + ZPINcStrServiceMsg.EXIST.getStr()
                + pathSubDir.exists()
                + ZPINcStrServiceMsg.NEWLINE.getStr()
                + ZPINcStrServiceMsg.CANREAD.getStr()
                + pathSubDir.canRead()
                + ZPINcStrServiceMsg.NEWLINE.getStr()
                + ZPINcStrServiceMsg.CANWRITE.getStr()
                + pathSubDir.canWrite()
                + ZPINcStrServiceMsg.NEWLINE.getStr());
        }
        
    }
}
