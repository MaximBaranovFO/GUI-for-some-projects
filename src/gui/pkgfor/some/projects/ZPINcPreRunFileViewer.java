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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;



/**
 *
 * @author Администратор
 */
public class ZPIZPINcPreRunFileViewer {
    
    
    /**
     * Not used
     */
    protected ZPIZPINcPreRunFileViewer() {
        
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     */
    protected static void createNewCfg(){
        ArrayList<String> arrStrCfg = getReadedLinesFromEtcCfg();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getIndexWorkSubDirFilesList() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getTmpIdsFile() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#NcManageCfg() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#outToConsoleIdxDirs() }
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.ZPINcParamFv}
     */
    protected static ZPINcParamFv getCurrentWorkCfg(){
        File fileCfg = ZPINcIdxFileManager.getOrCreateCfgFile();
        ZPINcParamFv readedWorkCfg = ZPINcParamFvReader.readDataFromWorkCfg();
        
        boolean boolMD5 = false;
        boolean boolSHA1 = false;
        boolean boolSHA256 = false;
        boolean boolSHA512 = false;
        
        if( fileCfg.exists() && ( !ZPINcParamFvManager.isZPINcParamFvDataEmpty(readedWorkCfg) ) ){
                boolMD5 = readedWorkCfg.strHexMD5.equalsIgnoreCase(ZPINcAppHelper.toHex(NcFileHash.MD5.checksum(fileCfg)));
                boolSHA1 = readedWorkCfg.strHexSHA1.equalsIgnoreCase(ZPINcAppHelper.toHex(NcFileHash.SHA1.checksum(fileCfg)));
                boolSHA256 = readedWorkCfg.strHexSHA256.equalsIgnoreCase(ZPINcAppHelper.toHex(NcFileHash.SHA256.checksum(fileCfg)));
                boolSHA512 = readedWorkCfg.strHexSHA512.equalsIgnoreCase(ZPINcAppHelper.toHex(NcFileHash.SHA512.checksum(fileCfg)));
        }
        if( (!boolMD5) && (!boolSHA1) && (!boolSHA256) && (!boolSHA512) ){
            readedWorkCfg = getUpdatedAppCfg();
        }
        if( ZPINcParamFvManager.isZPINcParamFvDataEmpty(readedWorkCfg) ){
            readedWorkCfg = getUpdatedAppCfg();
            if( ZPINcParamFvManager.isZPINcParamFvDataEmpty(readedWorkCfg) ){
                ZPINcAppHelper.appExitWithMessage("Can't get for current work config, error");
            }
        }
        ZPINcLogLogicCfg.ZPINcPreRunFileViewerGetCurrentWorkCfg();
        
        return readedWorkCfg;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getCurrentWorkCfg() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.ZPINcParamFv}
     */
    private static ZPINcParamFv getUpdatedAppCfg(){
        File fileCfg = ZPINcIdxFileManager.getOrCreateCfgFile();
        ArrayList<String> arrStrCfg = getReadedLinesFromEtcCfg();
        ZPINcParamFv paramReadedCfg = parseEtcCfg(arrStrCfg);
        
        
        
        ZPINcParamFv paramFromValideCfg = validateAndApplyCfg(paramReadedCfg);
        
        
        
        ArrayList<String> arrStrToUpdateCfg = getLinesParametersForUpdateCfg(paramFromValideCfg);
        int countLines = updateEtcCfg(arrStrToUpdateCfg);
        ZPINcParamFv paramToWriteCfg = null;
        if( fileCfg.exists() ){
            paramToWriteCfg = ZPINcParamFvManager.setFileHashes(paramFromValideCfg, fileCfg);
        }
        boolean isWrited = ZPINcParamFvWriter.writeDataInWorkCfg(paramToWriteCfg);
        if( !isWrited ){
            ZPINcAppHelper.appExitWithMessage("Can't write work configuration parameters");
            return new ZPINcParamFv();
        }
        
        
        
        return paramToWriteCfg;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getDefaultParametersForCfg() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.ZPINcParamFv}
     */
    private static ZPINcParamFv getDefaultCfgValues(){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        TreeMap<Integer, String> diskUserAlias = new TreeMap<Integer, String>();
        for( Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet() ){
            diskUserAlias.put((int) itemDisk.getValue().diskID, itemDisk.getValue().humanAlias);
        }
        String strIndex = ZPINcStrFileDir.DIR_IDX.getStr();

        String homePath = ZPINcIdxFileManager.getUserHomeDirStrPath();
        String appDir = ZPINcIdxFileManager.getAppWorkDirStrPath();
        strIndex = ZPINcIdxFileManager.strPathCombiner(homePath, strIndex);
        String kwdOut = ZPINcIdxFileManager.strPathCombiner(appDir,
            ZPINcStrFileDir.FILE_SRCH_KEY_OUT.getStr());
        String kwdIn = ZPINcIdxFileManager.strPathCombiner(appDir,
            ZPINcStrFileDir.FILE_SRCH_KEY_IN.getStr());
        String dirOut = ZPINcIdxFileManager.strPathCombiner(appDir,
            ZPINcStrFileDir.FILE_SRCH_DIR_OUT.getStr());
        String dirIn = ZPINcIdxFileManager.strPathCombiner(appDir,
            ZPINcStrFileDir.FILE_SRCH_DIR_IN.getStr());
        
        ZPINcParamFv defaultCfgForReturn = new ZPINcParamFv(
        strIndex,
        kwdOut,
        kwdIn,
        dirOut,
        dirIn,
        diskUserAlias,
        "",
        "",
        "",
        "",
        new TreeMap<Integer, File>());

        return defaultCfgForReturn;
    }
    
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * Method for validate parameters from readed configuration file, if setted
     * parameters has wrong format, than used default value and configuration
     * file will be rewrited with work config
     * @param inFuncReadedCfg returned by method {@link #parseEtcCfg parseEtcCfg()};
     * @return Parameters to write into updated Cfg file
     * {@link ru.newcontrol.ncfv.ZPINcParamFv}
     */
    private static ZPINcParamFv validateAndApplyCfg(ZPINcParamFv inFuncReadedCfg){
        
        boolean aliasChanged = ZPINcParamJournalDisk.updateUserAliasInJournalDisk(inFuncReadedCfg.diskUserAlias);
        
        ZPINcParamFv defaultWorkCfg = getDefaultCfgValues();
        ZPINcParamFv outFuncWorkCfg;
        
        
        String strPotWorkDir = ZPINcPathFromUserChecker.strInputAppWorkDirFromUser(inFuncReadedCfg.indexPath, defaultWorkCfg.indexPath);
        String strPotFileKeyOutSearch = ZPINcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.keywordsOutOfSearch, defaultWorkCfg.keywordsOutOfSearch);
        String strPotFileKeyInSearch = ZPINcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.keywordsInSearch, defaultWorkCfg.keywordsInSearch);
        String strPotFileDirOutIndex = ZPINcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.dirOutOfIndex, defaultWorkCfg.dirOutOfIndex);
        String strPotFileDirInIndex = ZPINcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.dirInIndex, defaultWorkCfg.dirInIndex);
        
        
        boolean resultCheckWorkDir = ZPINcParamCfgToDiskReleaser.checkOrCreateIdxDirStructure(strPotWorkDir);
        boolean resultCheckWorkDirDefault = false;
        if( !resultCheckWorkDir ){
            ZPINcParamCfgToDiskReleaser.getIdxDirStructure(strPotWorkDir);
            strPotWorkDir = ZPINcPathFromUserChecker.strInputAppWorkDirDefault(defaultWorkCfg.indexPath);
            resultCheckWorkDirDefault = ZPINcParamCfgToDiskReleaser.checkOrCreateIdxDirStructure(strPotWorkDir);
        }
        boolean resultCheckFileKeyOutSearch = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyOutSearch);
        boolean resultCheckFileKeyOutSearchDefault = false;
        if( !resultCheckFileKeyOutSearch ){
            ZPINcParamCfgToDiskReleaser.getWorkFileParams(strPotFileKeyOutSearch);
            strPotFileKeyOutSearch = ZPINcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.keywordsOutOfSearch);
            resultCheckFileKeyOutSearchDefault = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyOutSearch);
        }
        boolean resultCheckFileKeyInSearch = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyInSearch);
        boolean resultCheckFileKeyInSearchDefault = false;
        if( !resultCheckFileKeyInSearch ){
            ZPINcParamCfgToDiskReleaser.getWorkFileParams(strPotFileKeyInSearch);
            strPotFileKeyInSearch = ZPINcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.keywordsInSearch);
            resultCheckFileKeyInSearchDefault = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyInSearch);
        }
        boolean resultCheckFileDirOutIndex = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirOutIndex);
        boolean resultCheckFileDirOutIndexDefault = false;
        if( !resultCheckFileDirOutIndex ){
            ZPINcParamCfgToDiskReleaser.getWorkFileParams(strPotFileDirOutIndex);
            strPotFileDirOutIndex = ZPINcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.dirOutOfIndex);
            resultCheckFileDirOutIndexDefault = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirOutIndex);
        }
        boolean resultCheckFileDirInIndex = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirInIndex);
        boolean resultCheckFileDirInIndexDefault = false;
        if( !resultCheckFileDirInIndex ){
            ZPINcParamCfgToDiskReleaser.getWorkFileParams(strPotFileDirInIndex);
            strPotFileDirInIndex = ZPINcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.dirInIndex);
            
            resultCheckFileDirInIndexDefault = ZPINcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirInIndex);
        }
        outFuncWorkCfg = new ZPINcParamFv(
                strPotWorkDir,
                strPotFileKeyOutSearch,
                strPotFileKeyInSearch,
                strPotFileDirOutIndex,
                strPotFileDirInIndex,
                inFuncReadedCfg.diskUserAlias,
                "",
                "",
                "",
                "",
                ZPINcIdxFileManager.getIndexSubDirectories(strPotWorkDir)
        );
        ZPINcLogLogicCfg.ZPINcPreRunFileViewerValidateAndApplyCfg();
        return outFuncWorkCfg;
    }

    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#checkInDirFileReadable(java.io.File) }
     * </ul>
     * @param forCheck
     */
    private static void checkInDirFileReadable(File fileForCheck){
        if( fileForCheck.isFile() ){
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.FILE_FOR_CHECK.getStr()
                + fileForCheck.getName());
        }
        if( fileForCheck.isDirectory() ){
            File[] filesListFromDir = fileForCheck.listFiles();
            for(File itemOfFiles : filesListFromDir){
                checkInDirFileReadable(itemOfFiles);
            }
        }
    }

    /**
     * Not used
     * @return
     */
    private static TreeMap<Long, ZPINcDiskInfo> initDiskInfo(){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        for( Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet() ){
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.DISK_ID.getStr()
                + Long.toString(itemDisk.getValue().diskID));
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.HUMAN_ALIAS.getStr()
                + itemDisk.getValue().humanAlias);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.PROGRAM_ALIAS.getStr()
                + itemDisk.getValue().programAlias);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.STR_FILE_STORE.getStr()
                + itemDisk.getValue().strFileStore);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.DISK_LETTER.getStr()
                + itemDisk.getValue().diskLetter);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.LONG_SERIAL_NUMBER.getStr()
                + Long.toString(itemDisk.getValue().longSerialNumber));
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.STR_HEX_SERIAL_NUMBER.getStr()
                + itemDisk.getValue().strHexSerialNumber);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.DISK_FS_TYPE.getStr()
                + itemDisk.getValue().diskFStype);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.IS_READ_ONLY.getStr()
                + itemDisk.getValue().isReadonly);
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.AVAIL_SPACE.getStr()
                + Long.toString(itemDisk.getValue().availSpace));
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.TOTAL_SPACE.getStr()
                + Long.toString(itemDisk.getValue().totalSpace));
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.UN_ALLOCATED_SPACE.getStr()
                + Long.toString(itemDisk.getValue().unAllocatedSpace));
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrVarDescription.USED_SPACE.getStr()
                + Long.toString(itemDisk.getValue().usedSpace));
        }
        return sysDisk;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param arrStrReadedCfg
     * @return {@link ru.newcontrol.ncfv.ZPINcParamFv }
     */
    private static ZPINcParamFv parseEtcCfg(ArrayList<String> arrStrReadedCfg){
        ZPINcParamFv defaultWorkCfg = getDefaultCfgValues();
        ZPINcParamFv paramReadedCfg;
        String indexPathTmp = defaultWorkCfg.indexPath;
        String keywordsOutOfSearchTmp = defaultWorkCfg.keywordsOutOfSearch;
        String keywordsInSearchTmp = defaultWorkCfg.keywordsInSearch;
        String dirOutOfIndexTmp = defaultWorkCfg.dirOutOfIndex;
        String dirInIndexTmp = defaultWorkCfg.dirInIndex;
        TreeMap<Integer, String> diskUserAliasTmp;
        diskUserAliasTmp = new TreeMap<Integer, String>();
        for( String itemStrCfg : arrStrReadedCfg ){
            String strFilter = itemStrCfg.replaceAll("\\s*(\\s|,|!|\"|\\||$|~|@|#|\\*|%|\\^|&|\\(|\\)|\\{|\\}|\\[|\\]|<|>|\\?)\\s*", "").toString();
            String[] arrStrKeyVal = strFilter.split("=");
            if(arrStrKeyVal.length == 2){
                String keyName = arrStrKeyVal[0].toLowerCase().toString();
                if(keyName.equalsIgnoreCase("indexpath")){
                    indexPathTmp = arrStrKeyVal[1].toString();
                }
                if(keyName.equalsIgnoreCase("file_name_keywords_out_of_search")){
                    keywordsOutOfSearchTmp = arrStrKeyVal[1].toString();
                }
                if(keyName.equalsIgnoreCase("file_name_keywords_in_search")){
                    keywordsInSearchTmp = arrStrKeyVal[1].toString();
                }
                if(keyName.equalsIgnoreCase("file_name_dir_out_of_index")){
                    dirOutOfIndexTmp = arrStrKeyVal[1].toString();
                }
                if(keyName.equalsIgnoreCase("file_name_dir_in_index")){
                    dirInIndexTmp = arrStrKeyVal[1].toString();
                }
                int intDiskID = -1;
                if(keyName.indexOf("alias") > -1){
                    String[] strAlias = keyName.split("_");
                    if(strAlias.length == 2){
                        if(strAlias[0].equalsIgnoreCase("alias")){
                            String strDiskID = "";
                            if( (strAlias[1].matches("[0-9]") ) ){
                                strDiskID = strAlias[1].toString();
                                if( strDiskID.length() > 0 ){
                                    intDiskID = Integer.parseInt(strDiskID);
                                }
                            }
                            if(intDiskID > -1){
                                diskUserAliasTmp.put((int) intDiskID, arrStrKeyVal[1].toString());
                            }
                        }
                    }
                }
            }
        }
        paramReadedCfg = new ZPINcParamFv(
            indexPathTmp,
            keywordsOutOfSearchTmp,
            keywordsInSearchTmp,
            dirOutOfIndexTmp,
            dirInIndexTmp,
            diskUserAliasTmp,
            "",
            "",
            "",
            "",
            new TreeMap<Integer, File>()
        );
        return paramReadedCfg;
    }



    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#createNewCfg() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getReadedLinesFromEtcCfg(){
        File fileCfg = ZPINcIdxFileManager.getOrCreateCfgFile();
        return readCfg(ZPINcIdxFileManager.getStrCanPathFromFile(fileCfg));
    }
    
    protected static ArrayList<String> getEtcCfgLinesFromDisk(){
        return getReadedLinesFromEtcCfg();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#createCfg(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#updateEtcCfg(java.util.ArrayList) }
     * </ul>
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getRemTextForCfgFile(){
        ArrayList<String> strTextRemark = new ArrayList<String>();
        strTextRemark.add("##################################################");
        strTextRemark.add("#   This Config file generated automaticaly by");
        strTextRemark.add("#   NewControlFileViewer application, your must change parameters");
        strTextRemark.add("#   in the configuration options, wrong parameters automaticaly");
        strTextRemark.add("#   changed and rewrited to default value");
        strTextRemark.add("#   ");
        strTextRemark.add("#   WARNING !!! Remember, this file automaticaly rewrited,");
        strTextRemark.add("#   if your need make remarks, do it in the your files");
        strTextRemark.add("#   ");
        strTextRemark.add("#   In the text of options may be used next symbols: A-Z a-z 0-9 : \\ / _ -");
        strTextRemark.add("#   If first symbol of string has #, then this is remark string");
        strTextRemark.add("#   ");
        strTextRemark.add("#   For parameter indexpath, and Windows based systems, if not set");
        strTextRemark.add("#   drive letter mask: C:\\, then application choice disk with maximum");
        strTextRemark.add("#   of avalable space and create work folder in that disk");
        strTextRemark.add("#   ");
        strTextRemark.add("#   If the disk not avalable, default choesd maximum freespace disk");
        strTextRemark.add("#   and create index in default folder name");
        strTextRemark.add("#   ");
        strTextRemark.add("#   If you have removed disk drive with lagest avalable space, application");
        strTextRemark.add("#   create index in it, after remove this disk drive from computer, application");
        strTextRemark.add("#   in the next run, not found work directory and create new on the disk drive");
        strTextRemark.add("#   with maximum of avalable space");
        strTextRemark.add("#   ");
        strTextRemark.add("#   If the file_name_* parameters not have full path for files, then application");
        strTextRemark.add("#   searched it in the appPath/files folder, where this conf file");
        strTextRemark.add("#   On the Windows based systems folder for search files of file_name_* parameters");
        strTextRemark.add("#   also is his value for example: file_name_dir_in_index=/somedir/dirin.list");
        strTextRemark.add("#   application search this file appDriveLetter:/somedir/dirin.list");
        strTextRemark.add("#   Very simple in file_name_* parameters write need file name and put it");
        strTextRemark.add("#   in appPath/files/file.name");
        strTextRemark.add("#   Additional alias of disks writed in string after automaticaly");
        strTextRemark.add("#   created, whith numbers ID of disks, where disk ID your must");
        strTextRemark.add("#   view, if run this application with: -getdisks parameter");
        strTextRemark.add("##################################################");
        return strTextRemark;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#createCfg(java.lang.String) }
     * </ul>
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getDefaultParametersForCfg(){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        ArrayList<String> strTextRemark = new ArrayList<String>();
        ZPINcParamFv defaultValues = getDefaultCfgValues();
        strTextRemark.add("indexpath=" + defaultValues.indexPath);
        strTextRemark.add("file_name_keywords_out_of_search=" + defaultValues.keywordsOutOfSearch);
        strTextRemark.add("file_name_keywords_in_search=" + defaultValues.keywordsInSearch);
        strTextRemark.add("file_name_dir_out_of_index=" + defaultValues.dirOutOfIndex);
        strTextRemark.add("file_name_dir_in_index=" + defaultValues.dirInIndex);
        for( Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet() ){
            strTextRemark.add("##################################################");
            strTextRemark.add("#_|_" + "disk_id_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().diskID);
            strTextRemark.add("#_|_" + "file_store_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strFileStore);
            strTextRemark.add("#_|_" + "file_store_name_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strFileStoreName);
            strTextRemark.add("#_|_" + "disk_serial_number_heximal_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strHexSerialNumber);
            strTextRemark.add("#_|_" + "disk_avalable_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().availSpace + "\tbyte");
            strTextRemark.add("#_|_" + "disk_used_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().usedSpace + "\tbyte"
                    + " disk_total_space - disk_unallocated_space");
            strTextRemark.add("#_|_" + "disk_total_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().totalSpace + "\tbyte");
            strTextRemark.add("#_|_" + "disk_unallocated_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().unAllocatedSpace + "\tbyte");
            strTextRemark.add("#_|_" + "program_alias_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().programAlias);
            strTextRemark.add("##################################################");
            strTextRemark.add("alias_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().humanAlias);
        }
        return strTextRemark;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcIdxFileManager#getOrCreateCfgFile() }
     * </ul>
     * @param ncStrCfgPath
     */
    protected static void createCfg(String strCfgPath){
        ArrayList<String> strTextRemark = getRemTextForCfgFile();
        ArrayList<String> strParameters = getDefaultParametersForCfg();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(strCfgPath)))
        {
            for(String itemStr : strTextRemark){
                String text = itemStr.toString();
                bw.write(text);
                bw.newLine();
            }
            for(String itemStr : strParameters){
                String text = itemStr.toString();
                bw.write(text);
                bw.newLine();
            }
        }
        catch(IOException ex){
            String strForExit =
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ZPINcStrServiceMsg.NOT_WRITE_INTO_FILE.getStr()
                + ZPINcStrVarDescription.STR_CFG_PATH.getStr()
                + strCfgPath;
            ZPINcAppHelper.outMessage(strForExit);
            ZPINcAppHelper.appExitWithMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ex.getMessage());
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param linesParametersForUpdateCfg
     * @return {@link java.util.ArrayList }
     */
    private static int updateEtcCfg(ArrayList<String> linesParametersForUpdateCfg){
        String strCfgPath = ZPINcIdxFileManager.getStrCanPathFromFile(ZPINcIdxFileManager.getOrCreateCfgFile());
        ArrayList<String> strTextRemark = getRemTextForCfgFile();
        ArrayList<String> strParameters = linesParametersForUpdateCfg;
        int lines = 0;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(strCfgPath)))
        {
            for(String itemStr : strTextRemark){
                String text = itemStr.toString();
                bw.write(text);
                bw.newLine();
                lines++;
            }
            for(String itemStr : strParameters){
                String text = itemStr.toString();
                bw.write(text);
                bw.newLine();
                lines++;
            }
        }
        catch(IOException ex){
            String strForExit =
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ZPINcStrServiceMsg.NOT_WRITE_INTO_FILE.getStr()
                + ZPINcStrVarDescription.STR_CFG_PATH.getStr()
                + strCfgPath;
            ZPINcAppHelper.outMessage(strForExit);
            ZPINcAppHelper.appExitWithMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ex.getMessage());
        }
        return lines;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param linesValues
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getLinesParametersForUpdateCfg(ZPINcParamFv linesValues){
        TreeMap<Long, ZPINcDiskInfo> sysDisk = ZPINcParamJournalDisk.getFromJournalDiskOrCreateIt();
        ArrayList<String> strTextRemark = new ArrayList<String>();
        
        strTextRemark.add("indexpath=" + linesValues.indexPath);
        strTextRemark.add("file_name_keywords_out_of_search=" + linesValues.keywordsOutOfSearch);
        strTextRemark.add("file_name_keywords_in_search=" + linesValues.keywordsInSearch);
        strTextRemark.add("file_name_dir_out_of_index=" + linesValues.dirOutOfIndex);
        strTextRemark.add("file_name_dir_in_index=" + linesValues.dirInIndex);
        for( Map.Entry<Long, ZPINcDiskInfo> itemDisk : sysDisk.entrySet() ){
            strTextRemark.add("##################################################");
            strTextRemark.add("#_|_" + "disk_id_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().diskID);
            strTextRemark.add("#_|_" + "file_store_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strFileStore);
            strTextRemark.add("#_|_" + "file_store_name_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strFileStoreName);
            strTextRemark.add("#_|_" + "disk_serial_number_heximal_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().strHexSerialNumber);
            strTextRemark.add("#_|_" + "disk_avalable_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().availSpace + "\tbyte");
            strTextRemark.add("#_|_" + "disk_used_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().usedSpace + "\tbyte"
                    + " disk_total_space - disk_unallocated_space");
            strTextRemark.add("#_|_" + "disk_total_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().totalSpace + "\tbyte");
            strTextRemark.add("#_|_" + "disk_unallocated_space_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().unAllocatedSpace + "\tbyte");
            strTextRemark.add("#_|_" + "program_alias_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().programAlias);
            strTextRemark.add("##################################################");
            strTextRemark.add("alias_" + itemDisk.getValue().diskID + "=" + itemDisk.getValue().humanAlias);
        }
        return strTextRemark;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcPreRunFileViewer#getReadedLinesFromEtcCfg() }
     * </ul>
     * @param ncStrCfgPath
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> readCfg(String ncStrCfgPath){
        ArrayList<String> strForReturn;
        strForReturn = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(ncStrCfgPath)))
        {
            String s;
            while((s=br.readLine())!=null){
                if( (s.indexOf("#") > 0) ){
                    String[] strSplit = s.split("#");
                    if ( strSplit.length > 0 ){
                        strForReturn.add(strSplit[0].trim());
                    }
                }
                if( (s.indexOf("#") == -1) && (s.indexOf("=") > 5)){
                    strForReturn.add(s.trim());
                }
            }
        }
         catch(IOException ex){
            ZPINcAppHelper.logException(
                ZPINcPreRunFileViewer.class.getCanonicalName(), ex);
        }   
        return strForReturn;
    }

    
}
