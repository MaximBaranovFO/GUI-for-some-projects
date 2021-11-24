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
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#mcSearchOrSetWorkDir() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * </ul>
     * @param strIndexPath
     * @return
     */
    protected static boolean checkOrCreateIdxDirStructure(String strIndexPath){
        File fileWorkDir = new File(strIndexPath);
        boolean boolResultCreation = false;
        
        boolResultCreation = NcIdxFileManager.dirExistRWAccessChecker(fileWorkDir);
        if( !boolResultCreation ){
            boolResultCreation = fileWorkDir.mkdirs();
        }
        String[] strSubDirs = NcManageCfg.getWorkSubDirList();
        for( String itemSubDir : strSubDirs ){
            boolResultCreation = boolResultCreation && createSubDir(fileWorkDir, itemSubDir);
        }
        return boolResultCreation;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcParamCfgToDiskReleaser#checkOrCreateIdxDirStructure(java.lang.String) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getIndexSubDirectories(java.lang.String) }
     * </ul>
     * @param existParentDir
     * @param subDirName
     * @return
     */
    protected static boolean createSubDir(File existParentDir, String subDirName){
        String strPathName = NcIdxFileManager.strPathCombiner(NcIdxFileManager.getStrCanPathFromFile(existParentDir), subDirName);
        File fileForCreateDir = new File(strPathName);
        boolean boolCheck = NcIdxFileManager.dirExistRWAccessChecker(fileForCreateDir);
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
        boolean boolCheck = NcIdxFileManager.fileExistRWAccessChecker(fileForCreateDir);
        if( !boolCheck ){
            if( !NcIdxFileManager.dirExistRWAccessChecker(fileForCreateDir.getParentFile()) ){
                fileForCreateDir.getParentFile().mkdirs();
            }
            try {
                if( !fileForCreateDir.exists() ){
                    return fileForCreateDir.createNewFile();
                }
            } catch (IOException ex) {
                NcAppHelper.logException(
                    NcParamCfgToDiskReleaser.class.getCanonicalName(), ex);
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
        
        NcAppHelper.outMessage(NcStrLogMsgField.INFO.getStr()
            + NcStrServiceMsg.PATH_WORK_FILE.getStr()
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcIdxFileManager.getStrCanPathFromFile(fileWork)
            + NcStrServiceMsg.EXIST.getStr()
            + fileWork.exists() 
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcStrServiceMsg.CANREAD.getStr()
            + fileWork.canRead() 
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcStrServiceMsg.CANWRITE.getStr()
            + fileWork.canWrite() 
            + NcStrServiceMsg.NEWLINE.getStr());
        
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
        
        NcAppHelper.outMessage(NcStrLogMsgField.INFO.getStr()
            + NcStrServiceMsg.PATH_INDEX_DIRECTORY.getStr()
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcIdxFileManager.getStrCanPathFromFile(fileWorkDir)
            + NcStrServiceMsg.EXIST.getStr()
            + fileWorkDir.exists()
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcStrServiceMsg.CANREAD.getStr()
            + fileWorkDir.canRead()
            + NcStrServiceMsg.NEWLINE.getStr()
            + NcStrServiceMsg.CANWRITE.getStr()
            + fileWorkDir.canWrite()
            + NcStrServiceMsg.NEWLINE.getStr());
        
        
        String[] strSubDirs = NcManageCfg.getWorkSubDirList();
        for( String itemSubDir : strSubDirs ){
            String strPathSubDir = NcIdxFileManager.strPathCombiner(NcIdxFileManager.getStrCanPathFromFile(fileWorkDir), itemSubDir);
            File pathSubDir = new File(strPathSubDir);
            NcAppHelper.outMessage(NcStrLogMsgField.INFO.getStr()
                + NcStrServiceMsg.PATH_SUBDIR.getStr()
                + NcStrServiceMsg.NEWLINE.getStr()
                + NcIdxFileManager.getStrCanPathFromFile(pathSubDir)
                + NcStrServiceMsg.EXIST.getStr()
                + pathSubDir.exists()
                + NcStrServiceMsg.NEWLINE.getStr()
                + NcStrServiceMsg.CANREAD.getStr()
                + pathSubDir.canRead()
                + NcStrServiceMsg.NEWLINE.getStr()
                + NcStrServiceMsg.CANWRITE.getStr()
                + pathSubDir.canWrite()
                + NcStrServiceMsg.NEWLINE.getStr());
        }
        
    }
}
