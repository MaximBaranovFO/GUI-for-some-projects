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
public class ZPINcPreRunFileViewer {
    
    
    /**
     * Not used
     */
    protected ZPINcPreRunFileViewer() {
        
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
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getIndexWorkSubDirFilesList() }
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getTmpIdsFile() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#NcManageCfg() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#outToConsoleIdxDirs() }
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.NcParamFv}
     */
    protected static NcParamFv getCurrentWorkCfg(){
        File fileCfg = NcIdxFileManager.getOrCreateCfgFile();
        NcParamFv readedWorkCfg = NcParamFvReader.readDataFromWorkCfg();
        
        boolean boolMD5 = false;
        boolean boolSHA1 = false;
        boolean boolSHA256 = false;
        boolean boolSHA512 = false;
        
        if( fileCfg.exists() && ( !NcParamFvManager.isNcParamFvDataEmpty(readedWorkCfg) ) ){
                boolMD5 = readedWorkCfg.strHexMD5.equalsIgnoreCase(NcAppHelper.toHex(NcFileHash.MD5.checksum(fileCfg)));
                boolSHA1 = readedWorkCfg.strHexSHA1.equalsIgnoreCase(NcAppHelper.toHex(NcFileHash.SHA1.checksum(fileCfg)));
                boolSHA256 = readedWorkCfg.strHexSHA256.equalsIgnoreCase(NcAppHelper.toHex(NcFileHash.SHA256.checksum(fileCfg)));
                boolSHA512 = readedWorkCfg.strHexSHA512.equalsIgnoreCase(NcAppHelper.toHex(NcFileHash.SHA512.checksum(fileCfg)));
        }
        if( (!boolMD5) && (!boolSHA1) && (!boolSHA256) && (!boolSHA512) ){
            readedWorkCfg = getUpdatedAppCfg();
        }
        if( NcParamFvManager.isNcParamFvDataEmpty(readedWorkCfg) ){
            readedWorkCfg = getUpdatedAppCfg();
            if( NcParamFvManager.isNcParamFvDataEmpty(readedWorkCfg) ){
                NcAppHelper.appExitWithMessage("Can't get for current work config, error");
            }
        }
        NcLogLogicCfg.NcPreRunFileViewerGetCurrentWorkCfg();
        
        return readedWorkCfg;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getCurrentWorkCfg() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.NcParamFv}
     */
    private static NcParamFv getUpdatedAppCfg(){
        File fileCfg = NcIdxFileManager.getOrCreateCfgFile();
        ArrayList<String> arrStrCfg = getReadedLinesFromEtcCfg();
        NcParamFv paramReadedCfg = parseEtcCfg(arrStrCfg);
        
        
        
        NcParamFv paramFromValideCfg = validateAndApplyCfg(paramReadedCfg);
        
        
        
        ArrayList<String> arrStrToUpdateCfg = getLinesParametersForUpdateCfg(paramFromValideCfg);
        int countLines = updateEtcCfg(arrStrToUpdateCfg);
        NcParamFv paramToWriteCfg = null;
        if( fileCfg.exists() ){
            paramToWriteCfg = NcParamFvManager.setFileHashes(paramFromValideCfg, fileCfg);
        }
        boolean isWrited = NcParamFvWriter.writeDataInWorkCfg(paramToWriteCfg);
        if( !isWrited ){
            NcAppHelper.appExitWithMessage("Can't write work configuration parameters");
            return new NcParamFv();
        }
        
        
        
        return paramToWriteCfg;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#validateAndApplyCfg(ru.newcontrol.ncfv.NcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getDefaultParametersForCfg() }
     * </ul>
     * @return {@link ru.newcontrol.ncfv.NcParamFv}
     */
    private static NcParamFv getDefaultCfgValues(){
        TreeMap<Long, NcDiskInfo> sysDisk = NcParamJournalDisk.getFromJournalDiskOrCreateIt();
        TreeMap<Integer, String> diskUserAlias = new TreeMap<Integer, String>();
        for( Map.Entry<Long, NcDiskInfo> itemDisk : sysDisk.entrySet() ){
            diskUserAlias.put((int) itemDisk.getValue().diskID, itemDisk.getValue().humanAlias);
        }
        String strIndex = NcStrFileDir.DIR_IDX.getStr();

        String homePath = NcIdxFileManager.getUserHomeDirStrPath();
        String appDir = NcIdxFileManager.getAppWorkDirStrPath();
        strIndex = NcIdxFileManager.strPathCombiner(homePath, strIndex);
        String kwdOut = NcIdxFileManager.strPathCombiner(appDir,
            NcStrFileDir.FILE_SRCH_KEY_OUT.getStr());
        String kwdIn = NcIdxFileManager.strPathCombiner(appDir,
            NcStrFileDir.FILE_SRCH_KEY_IN.getStr());
        String dirOut = NcIdxFileManager.strPathCombiner(appDir,
            NcStrFileDir.FILE_SRCH_DIR_OUT.getStr());
        String dirIn = NcIdxFileManager.strPathCombiner(appDir,
            NcStrFileDir.FILE_SRCH_DIR_IN.getStr());
        
        NcParamFv defaultCfgForReturn = new NcParamFv(
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
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * Method for validate parameters from readed configuration file, if setted
     * parameters has wrong format, than used default value and configuration
     * file will be rewrited with work config
     * @param inFuncReadedCfg returned by method {@link #parseEtcCfg parseEtcCfg()};
     * @return Parameters to write into updated Cfg file
     * {@link ru.newcontrol.ncfv.NcParamFv}
     */
    private static NcParamFv validateAndApplyCfg(NcParamFv inFuncReadedCfg){
        
        boolean aliasChanged = NcParamJournalDisk.updateUserAliasInJournalDisk(inFuncReadedCfg.diskUserAlias);
        
        NcParamFv defaultWorkCfg = getDefaultCfgValues();
        NcParamFv outFuncWorkCfg;
        
        
        String strPotWorkDir = NcPathFromUserChecker.strInputAppWorkDirFromUser(inFuncReadedCfg.indexPath, defaultWorkCfg.indexPath);
        String strPotFileKeyOutSearch = NcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.keywordsOutOfSearch, defaultWorkCfg.keywordsOutOfSearch);
        String strPotFileKeyInSearch = NcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.keywordsInSearch, defaultWorkCfg.keywordsInSearch);
        String strPotFileDirOutIndex = NcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.dirOutOfIndex, defaultWorkCfg.dirOutOfIndex);
        String strPotFileDirInIndex = NcPathFromUserChecker.strInputAppWorkFileFromUser(inFuncReadedCfg.dirInIndex, defaultWorkCfg.dirInIndex);
        
        
        boolean resultCheckWorkDir = NcParamCfgToDiskReleaser.checkOrCreateIdxDirStructure(strPotWorkDir);
        boolean resultCheckWorkDirDefault = false;
        if( !resultCheckWorkDir ){
            NcParamCfgToDiskReleaser.getIdxDirStructure(strPotWorkDir);
            strPotWorkDir = NcPathFromUserChecker.strInputAppWorkDirDefault(defaultWorkCfg.indexPath);
            resultCheckWorkDirDefault = NcParamCfgToDiskReleaser.checkOrCreateIdxDirStructure(strPotWorkDir);
        }
        boolean resultCheckFileKeyOutSearch = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyOutSearch);
        boolean resultCheckFileKeyOutSearchDefault = false;
        if( !resultCheckFileKeyOutSearch ){
            NcParamCfgToDiskReleaser.getWorkFileParams(strPotFileKeyOutSearch);
            strPotFileKeyOutSearch = NcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.keywordsOutOfSearch);
            resultCheckFileKeyOutSearchDefault = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyOutSearch);
        }
        boolean resultCheckFileKeyInSearch = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyInSearch);
        boolean resultCheckFileKeyInSearchDefault = false;
        if( !resultCheckFileKeyInSearch ){
            NcParamCfgToDiskReleaser.getWorkFileParams(strPotFileKeyInSearch);
            strPotFileKeyInSearch = NcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.keywordsInSearch);
            resultCheckFileKeyInSearchDefault = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileKeyInSearch);
        }
        boolean resultCheckFileDirOutIndex = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirOutIndex);
        boolean resultCheckFileDirOutIndexDefault = false;
        if( !resultCheckFileDirOutIndex ){
            NcParamCfgToDiskReleaser.getWorkFileParams(strPotFileDirOutIndex);
            strPotFileDirOutIndex = NcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.dirOutOfIndex);
            resultCheckFileDirOutIndexDefault = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirOutIndex);
        }
        boolean resultCheckFileDirInIndex = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirInIndex);
        boolean resultCheckFileDirInIndexDefault = false;
        if( !resultCheckFileDirInIndex ){
            NcParamCfgToDiskReleaser.getWorkFileParams(strPotFileDirInIndex);
            strPotFileDirInIndex = NcPathFromUserChecker.strInputAppWorkFileDefault(defaultWorkCfg.dirInIndex);
            
            resultCheckFileDirInIndexDefault = NcParamCfgToDiskReleaser.checkOrCreateFiles(strPotFileDirInIndex);
        }
        outFuncWorkCfg = new NcParamFv(
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
                NcIdxFileManager.getIndexSubDirectories(strPotWorkDir)
        );
        NcLogLogicCfg.NcPreRunFileViewerValidateAndApplyCfg();
        return outFuncWorkCfg;
    }

    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#checkInDirFileReadable(java.io.File) }
     * </ul>
     * @param forCheck
     */
    private static void checkInDirFileReadable(File fileForCheck){
        if( fileForCheck.isFile() ){
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.FILE_FOR_CHECK.getStr()
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
    private static TreeMap<Long, NcDiskInfo> initDiskInfo(){
        TreeMap<Long, NcDiskInfo> sysDisk = NcParamJournalDisk.getFromJournalDiskOrCreateIt();
        for( Map.Entry<Long, NcDiskInfo> itemDisk : sysDisk.entrySet() ){
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.DISK_ID.getStr()
                + Long.toString(itemDisk.getValue().diskID));
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.HUMAN_ALIAS.getStr()
                + itemDisk.getValue().humanAlias);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.PROGRAM_ALIAS.getStr()
                + itemDisk.getValue().programAlias);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.STR_FILE_STORE.getStr()
                + itemDisk.getValue().strFileStore);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.DISK_LETTER.getStr()
                + itemDisk.getValue().diskLetter);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.LONG_SERIAL_NUMBER.getStr()
                + Long.toString(itemDisk.getValue().longSerialNumber));
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.STR_HEX_SERIAL_NUMBER.getStr()
                + itemDisk.getValue().strHexSerialNumber);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.DISK_FS_TYPE.getStr()
                + itemDisk.getValue().diskFStype);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.IS_READ_ONLY.getStr()
                + itemDisk.getValue().isReadonly);
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.AVAIL_SPACE.getStr()
                + Long.toString(itemDisk.getValue().availSpace));
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.TOTAL_SPACE.getStr()
                + Long.toString(itemDisk.getValue().totalSpace));
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.UN_ALLOCATED_SPACE.getStr()
                + Long.toString(itemDisk.getValue().unAllocatedSpace));
            NcAppHelper.outMessage(
                NcStrLogMsgField.INFO.getStr()
                + NcStrVarDescription.USED_SPACE.getStr()
                + Long.toString(itemDisk.getValue().usedSpace));
        }
        return sysDisk;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param arrStrReadedCfg
     * @return {@link ru.newcontrol.ncfv.NcParamFv }
     */
    private static NcParamFv parseEtcCfg(ArrayList<String> arrStrReadedCfg){
        NcParamFv defaultWorkCfg = getDefaultCfgValues();
        NcParamFv paramReadedCfg;
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
        paramReadedCfg = new NcParamFv(
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
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#createNewCfg() }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getReadedLinesFromEtcCfg(){
        File fileCfg = NcIdxFileManager.getOrCreateCfgFile();
        return readCfg(NcIdxFileManager.getStrCanPathFromFile(fileCfg));
    }
    
    protected static ArrayList<String> getEtcCfgLinesFromDisk(){
        return getReadedLinesFromEtcCfg();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#createCfg(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#updateEtcCfg(java.util.ArrayList) }
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
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#createCfg(java.lang.String) }
     * </ul>
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getDefaultParametersForCfg(){
        TreeMap<Long, NcDiskInfo> sysDisk = NcParamJournalDisk.getFromJournalDiskOrCreateIt();
        ArrayList<String> strTextRemark = new ArrayList<String>();
        NcParamFv defaultValues = getDefaultCfgValues();
        strTextRemark.add("indexpath=" + defaultValues.indexPath);
        strTextRemark.add("file_name_keywords_out_of_search=" + defaultValues.keywordsOutOfSearch);
        strTextRemark.add("file_name_keywords_in_search=" + defaultValues.keywordsInSearch);
        strTextRemark.add("file_name_dir_out_of_index=" + defaultValues.dirOutOfIndex);
        strTextRemark.add("file_name_dir_in_index=" + defaultValues.dirInIndex);
        for( Map.Entry<Long, NcDiskInfo> itemDisk : sysDisk.entrySet() ){
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
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getOrCreateCfgFile() }
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
                NcStrLogMsgField.ERROR_CRITICAL.getStr()
                + NcStrServiceMsg.NOT_WRITE_INTO_FILE.getStr()
                + NcStrVarDescription.STR_CFG_PATH.getStr()
                + strCfgPath;
            NcAppHelper.outMessage(strForExit);
            NcAppHelper.appExitWithMessage(
                NcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ex.getMessage());
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param linesParametersForUpdateCfg
     * @return {@link java.util.ArrayList }
     */
    private static int updateEtcCfg(ArrayList<String> linesParametersForUpdateCfg){
        String strCfgPath = NcIdxFileManager.getStrCanPathFromFile(NcIdxFileManager.getOrCreateCfgFile());
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
                NcStrLogMsgField.ERROR_CRITICAL.getStr()
                + NcStrServiceMsg.NOT_WRITE_INTO_FILE.getStr()
                + NcStrVarDescription.STR_CFG_PATH.getStr()
                + strCfgPath;
            NcAppHelper.outMessage(strForExit);
            NcAppHelper.appExitWithMessage(
                NcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ex.getMessage());
        }
        return lines;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param linesValues
     * @return {@link java.util.ArrayList }
     */
    private static ArrayList<String> getLinesParametersForUpdateCfg(NcParamFv linesValues){
        TreeMap<Long, NcDiskInfo> sysDisk = NcParamJournalDisk.getFromJournalDiskOrCreateIt();
        ArrayList<String> strTextRemark = new ArrayList<String>();
        
        strTextRemark.add("indexpath=" + linesValues.indexPath);
        strTextRemark.add("file_name_keywords_out_of_search=" + linesValues.keywordsOutOfSearch);
        strTextRemark.add("file_name_keywords_in_search=" + linesValues.keywordsInSearch);
        strTextRemark.add("file_name_dir_out_of_index=" + linesValues.dirOutOfIndex);
        strTextRemark.add("file_name_dir_in_index=" + linesValues.dirInIndex);
        for( Map.Entry<Long, NcDiskInfo> itemDisk : sysDisk.entrySet() ){
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
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getReadedLinesFromEtcCfg() }
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
            NcAppHelper.logException(
                NcPreRunFileViewer.class.getCanonicalName(), ex);
        }   
        return strForReturn;
    }

    
}
