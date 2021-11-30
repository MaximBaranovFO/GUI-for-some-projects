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
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcPreIdxWork {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     */
    protected static void outToConsoleIdxDirs(){
        ZPINcParamFv currentWorkCfg = ZPINcPreRunFileViewer.getCurrentWorkCfg();
        TreeMap<Integer, File> listSubDirs = new TreeMap<Integer, File>();
        listSubDirs.putAll(currentWorkCfg.tmIndexSubDirs);
        listSubDirs.put("index".hashCode(), new File(currentWorkCfg.indexPath));
        for( Map.Entry<Integer, File> items : listSubDirs.entrySet() ){
            ZPINcAppHelper.outMessageToConsole(ZPINcIdxFileManager.getStrCanPathFromFile(items.getValue()));
        }
        ZPINcAppHelper.outMessage("Next way");
        TreeMap<Integer, File> indexWorkSubDirFilesList = ZPINcIdxFileManager.getIndexWorkSubDirFilesList();
        for( Map.Entry<Integer, File> itemsNextWay : indexWorkSubDirFilesList.entrySet() ){
            ZPINcAppHelper.outMessageToConsole("key: " + itemsNextWay.getKey());
            ZPINcAppHelper.outMessageToConsole(ZPINcIdxFileManager.getStrCanPathFromFile(itemsNextWay.getValue()));
        }
        ZPINcAppHelper.outMessageToConsole("By name");
        String[] arrStrCode = ZPINcManageCfg.getWorkSubDirList();
        for( String itemSubDir : arrStrCode ){
            ZPINcAppHelper.outMessageToConsole("key name: " + itemSubDir + "\tkey value: " + itemSubDir.hashCode() );
            ZPINcAppHelper.outMessageToConsole(ZPINcIdxFileManager.getStrCanPathFromFile(indexWorkSubDirFilesList.get(itemSubDir.hashCode())));
        }
    }
    /**
     * Not used
     * Check files in index subFolders
     */
    private static void checkInIndexFolderContent(){
        
        ZPINcParamFv readedWorkCfg = ZPINcParamFvReader.readDataFromWorkCfg();
        if( ZPINcParamFvManager.isNcParamFvDataEmpty(readedWorkCfg) ){
            readedWorkCfg = ZPINcPreRunFileViewer.getCurrentWorkCfg();
        }
        
        File fileWorkDir = new File(readedWorkCfg.indexPath);
        TreeMap<Integer, File> listSubDirs = new TreeMap<Integer, File>();
        listSubDirs.putAll(readedWorkCfg.tmIndexSubDirs);
    
        File fileFx = listSubDirs.get("/fx".hashCode());
        TreeMap<Long, File> fileFromDirListExist = getNotFullFiles(fileFx);
        File fileFl = listSubDirs.get("/fl".hashCode());
        TreeMap<Long, File> fileFromDirList = getNotFullFiles(fileFl);
        File fileLw = listSubDirs.get("/lw".hashCode());
        checkFilesOnReadable(fileLw);
        File fileLn = listSubDirs.get("/ln".hashCode());
        checkFilesOnReadable(fileLn);
        File fileW = listSubDirs.get("/w".hashCode());
        checkFilesOnReadable(fileW);
        File fileT = listSubDirs.get("/t".hashCode());
        checkFilesOnReadable(fileT);
        File fileSw = listSubDirs.get("/sw".hashCode());
        checkFilesOnReadable(fileSw);
        checkTmpIDsData();
        getNotFinishedAppendToIndex(fileFromDirList, fileFromDirListExist);
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     * Compare for content of DirListAttr and DirListExist, by fileds:
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr#dirListID ZPINcDcIdxDirListToFileAttr.dirListID} == {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist#dirListID ZPINcDcIdxDirListToFileExist.dirListID}
     * <li>{@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr#diskID ZPINcDcIdxDirListToFileAttr.diskID} == {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist#diskID ZPINcDcIdxDirListToFileExist.diskID}
     * <li>{@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr#pathHash ZPINcDcIdxDirListToFileAttr.pathHash} == {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist#pathHash ZPINcDcIdxDirListToFileExist.pathHash}
     * </ul>
     * Output to console result of compare lists
     */
    protected static void getNotEqualsRecordDirListAttrVsExist(){
        long recordId = 0;
        
        File recordsAttr = ZPINcIdxFileManager.getFileForDirListAttrContainedRecordId(recordId);
        File recordsExist = ZPINcIdxFileManager.getFileForDirListExistContainedRecordId(recordId);
        int countError = 0;
        do{
            ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsAttr));
            ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsExist));
            if( ZPINcIdxFileManager.isErrorForFileOperation(recordsAttr) ){
                if( countError > 3 ){
                    break;
                }
                countError++;
                continue;
            }
            if( ZPINcIdxFileManager.isErrorForFileOperation(recordsExist) ){
                if( countError > 3 ){
                    break;
                }
                countError++;
                continue;
            }

            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> dataFromDirList = new TreeMap<>();
            TreeMap<Long, ZPINcDcIdxDirListToFileExist> dataFromDirListExist = new TreeMap<>();

            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> badDirIdDirListAttr = new TreeMap<>();
            TreeMap<Long, ZPINcDcIdxDirListToFileExist> badDirIdDirListExist = new TreeMap<>();

            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> badDiskIdDirListAttr = new TreeMap<>();
            TreeMap<Long, ZPINcDcIdxDirListToFileExist> badDiskIdDirListExist = new TreeMap<>();

            TreeMap<Long, ZPINcDcIdxDirListToFileAttr> badPathHashDirListAttr = new TreeMap<>();
            TreeMap<Long, ZPINcDcIdxDirListToFileExist> badPathHashDirListExist = new TreeMap<>();


            dataFromDirList.putAll((Map<? extends Long, ? extends ZPINcDcIdxDirListToFileAttr>) 
                    ZPINcIdxFileManager.getDataFromFile(recordsAttr));
            dataFromDirListExist.putAll((Map<? extends Long, ? extends ZPINcDcIdxDirListToFileExist>) 
                    ZPINcIdxFileManager.getDataFromFile(recordsExist));
            
            ZPINcAppHelper.outMessageToConsole("Start check for Attr count of records: " + dataFromDirList.size());
            ZPINcAppHelper.outMessageToConsole("Start check for Exist count of records: " + dataFromDirListExist.size());
            
            long DirListAttrId = dataFromDirList.firstEntry().getValue().dirListID;
            long DirListExistId = dataFromDirListExist.firstEntry().getValue().dirListID;
            long recordFirstAttrId = dataFromDirList.firstKey();
            long recordFirstExistId = dataFromDirListExist.firstKey();

            long recordLastAttrId = dataFromDirList.lastKey();
            long recordLastExistId = dataFromDirListExist.lastKey();

            ZPINcDcIdxDirListToFileAttr dataFirstAttr = dataFromDirList.firstEntry().getValue();
            ZPINcDcIdxDirListToFileExist dataFirstExist = dataFromDirListExist.firstEntry().getValue();

            ZPINcDcIdxDirListToFileAttr dataLastAttr = dataFromDirList.lastEntry().getValue();
            ZPINcDcIdxDirListToFileExist dataLastExist = dataFromDirListExist.lastEntry().getValue();

            ZPINcDcIdxDirListToFileAttr dataAttr = dataFromDirList.firstEntry().getValue();
            ZPINcDcIdxDirListToFileExist dataExist = dataFromDirListExist.firstEntry().getValue();
            
            boolean isDiskIdEquals = false;
            boolean isDirListId = false;
            boolean isDirNameHash = false;
            long currentAttrId = 0;
            long currentExistId = 0;

            long prevAttrId = 0;
            long prevExistId = 0;
            
            
            Iterator<ZPINcDcIdxDirListToFileAttr> iterAttr = dataFromDirList.values().iterator();
            Iterator<ZPINcDcIdxDirListToFileExist> iterExist = dataFromDirListExist.values().iterator();
            while( iterAttr.hasNext() ){

                dataAttr = iterAttr.next();
                if( iterExist.hasNext() ){
                    dataExist = iterExist.next();
                }
                if( dataAttr.equals(dataFirstAttr) ){
                    currentAttrId = recordFirstAttrId;
                }
                if( dataExist.equals(dataFirstExist) ){
                    currentExistId = recordFirstExistId;
                }
                isDiskIdEquals = dataAttr.diskID == dataExist.diskID;
                isDirListId = dataAttr.dirListID == dataExist.dirListID;
                isDirNameHash = dataAttr.pathHash == dataExist.pathHash;

                if( !isDiskIdEquals ){
                    badDiskIdDirListAttr.put(System.nanoTime(),dataAttr);
                    badDiskIdDirListExist.put(System.nanoTime(),dataExist);
                }

                if( !isDirListId ){
                    badDirIdDirListAttr.put(System.nanoTime(),dataAttr);
                    badDirIdDirListExist.put(System.nanoTime(),dataExist);
                }

                if( !isDirNameHash ){
                    badPathHashDirListAttr.put(System.nanoTime(),dataAttr);
                    badPathHashDirListExist.put(System.nanoTime(),dataExist);
                }
                
                if( iterAttr.hasNext() ){
                    prevAttrId = currentAttrId;
                    currentAttrId++;
                }

                if( iterExist.hasNext() ){
                    prevExistId = currentExistId;
                    currentExistId++;
                }

                
                ZPINcAppHelper.outMessageToConsole("Attr dirListID: " + dataAttr.dirListID + " Exist dirListID:" + dataExist.dirListID);
            }
            recordsAttr = ZPINcIdxFileManager.getFileForDirListAttrContainedRecordId(recordLastAttrId + 1);
            recordsExist = ZPINcIdxFileManager.getFileForDirListExistContainedRecordId(recordLastExistId + 1);
            
            if( !badDiskIdDirListAttr.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsAttr) + "\n BadDiskID count of records " + badDiskIdDirListAttr.size());
            }
            if( !badDirIdDirListAttr.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsAttr) + "\n BadDiskID count of records " + badDirIdDirListAttr.size());
            }
            if( !badPathHashDirListAttr.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsAttr) + "\n BadDiskID count of records " + badPathHashDirListAttr.size());
            }
            if( !badDiskIdDirListExist.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsExist) + "\n BadDiskID count of records " + badDiskIdDirListExist.size());
            }
            if( !badDirIdDirListExist.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsExist) + "\n BadDiskID count of records " + badDirIdDirListExist.size());
            }    
            if( !badPathHashDirListExist.isEmpty() ){
                ZPINcAppHelper.outMessageToConsole("In file: " + ZPINcIdxFileManager.getStrCanPathFromFile(recordsExist) + "\n BadDiskID count of records " + badPathHashDirListExist.size());
            }    
        }
        while(ZPINcIdxFileManager.isErrorForFileOperation(recordsAttr) ||
        ZPINcIdxFileManager.isErrorForFileOperation(recordsExist));
        
        
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * </ul>
     * This method return not finished (breaked) record of data
     * @param inFuncFileFromDirList
     * @param inFuncFileFromDirListExist 
     */
    private static void getNotFinishedAppendToIndex(TreeMap<Long, File> inFuncFileFromDirList, TreeMap<Long, File> inFuncFileFromDirListExist){
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> dataFromDirList = new TreeMap<>();
        TreeMap<Long, ZPINcDcIdxDirListToFileExist> dataFromDirListExist = new TreeMap<>();
        for(Map.Entry<Long, File> itemFromDirList : inFuncFileFromDirList.entrySet() ){
            dataFromDirList.putAll((Map<? extends Long, ? extends ZPINcDcIdxDirListToFileAttr>) ZPINcIdxFileManager.getDataFromFile(itemFromDirList.getValue()));
        }
        for(Map.Entry<Long, File> itemFromDirListExist : inFuncFileFromDirListExist.entrySet() ){
            dataFromDirListExist.putAll((Map<? extends Long, ? extends ZPINcDcIdxDirListToFileExist>) ZPINcIdxFileManager.getDataFromFile(itemFromDirListExist.getValue()));
        }
        ZPINcAppHelper.outMessageToConsole("DirList record count: " + dataFromDirList.size());
        ZPINcAppHelper.outMessageToConsole("Files count: " + inFuncFileFromDirList.size());
        ZPINcAppHelper.outMessageToConsole("DirList Exist record count: " + dataFromDirListExist.size());
        ZPINcAppHelper.outMessageToConsole("Files count: " + inFuncFileFromDirListExist.size());
        
        for(Map.Entry<Long, ZPINcDcIdxDirListToFileExist> itemFromDirListExist : dataFromDirListExist.entrySet() ){
            outToConsoleDirListExist(itemFromDirListExist.getValue());
            if( itemFromDirListExist.getValue().nanoTimeEndAddToIndex < 0 ){
                for(Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemFromDirList : dataFromDirList.entrySet() ){
                    if( itemFromDirListExist.getValue().dirListID == itemFromDirList.getValue().dirListID ){
                        outToConsoleDirListAttr(itemFromDirList.getValue());
                    }
                }
            }
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#getNotFinishedAppendToIndex(java.util.TreeMap, java.util.TreeMap) }
     * </ul>
     * Print to console data from {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr}
     * @param inFuncData {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileAttr}
     */
    protected static void outToConsoleDirListAttr(ZPINcDcIdxDirListToFileAttr inFuncData){
        ZPINcAppHelper.outMessageToConsole("dirListID: " + inFuncData.dirListID);
        ZPINcAppHelper.outMessageToConsole("diskID: " + inFuncData.diskID);
        ZPINcAppHelper.outMessageToConsole("diskSnLong: " + inFuncData.diskSnLong);
        ZPINcAppHelper.outMessageToConsole("diskTotalSpace: " + inFuncData.diskTotalSpace);
        ZPINcAppHelper.outMessageToConsole("diskProgramAlias: " + inFuncData.diskProgramAlias);
        ZPINcAppHelper.outMessageToConsole("diskProgramAliasHash: " + inFuncData.diskProgramAliasHash);
        ZPINcAppHelper.outMessageToConsole("diskSnHex: " + inFuncData.diskSnHex);
        ZPINcAppHelper.outMessageToConsole("diskSnHexHash: " + inFuncData.diskSnHexHash);
        ZPINcAppHelper.outMessageToConsole("diskLetter: " + inFuncData.diskLetter);
        ZPINcAppHelper.outMessageToConsole("path: " + inFuncData.path);
        ZPINcAppHelper.outMessageToConsole("pathHash: " + inFuncData.pathHash);
        ZPINcAppHelper.outMessageToConsole("fileLength: " + inFuncData.fileLength);
        ZPINcAppHelper.outMessageToConsole("fileCanRead: " + inFuncData.fileCanRead);
        ZPINcAppHelper.outMessageToConsole("fileCanWrite: " + inFuncData.fileCanWrite);
        ZPINcAppHelper.outMessageToConsole("fileCanExecute: " + inFuncData.fileCanExecute);
        ZPINcAppHelper.outMessageToConsole("fileIsHidden: " + inFuncData.fileIsHidden);
        ZPINcAppHelper.outMessageToConsole("fileLastModified: " + inFuncData.fileLastModified);
        ZPINcAppHelper.outMessageToConsole("fileIsDirectory: " + inFuncData.fileIsDirectory);
        ZPINcAppHelper.outMessageToConsole("fileIsFile: " + inFuncData.fileIsFile);
        ZPINcAppHelper.outMessageToConsole("recordTime: " + inFuncData.recordTime);
        ZPINcAppHelper.outMessageToConsole("deletedRec: " + inFuncData.deletedRec);
        ZPINcAppHelper.outMessageToConsole("changedRecordID: " + inFuncData.changedRecordID);
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#getNotFinishedAppendToIndex(java.util.TreeMap, java.util.TreeMap) }
     * </ul>
     * Print to console data from {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist}
     * @param inFuncData {@link ru.newcontrol.ncfv.ZPINcDcIdxDirListToFileExist}
     */
    private static void outToConsoleDirListExist(ZPINcDcIdxDirListToFileExist inFuncData){
        if( inFuncData.nanoTimeEndAddToIndex < 0 ){
            ZPINcAppHelper.outMessageToConsole("dirListID: " + inFuncData.dirListID);
            ZPINcAppHelper.outMessageToConsole("diskID: " + inFuncData.diskID);
            ZPINcAppHelper.outMessageToConsole("pathWithOutDiskLetter: " + inFuncData.pathWithOutDiskLetter);
            ZPINcAppHelper.outMessageToConsole("pathHash: " + inFuncData.pathHash);
            ZPINcAppHelper.outMessageToConsole("nanoTimeStartAddToIndex: " + inFuncData.nanoTimeStartAddToIndex);
            ZPINcAppHelper.outMessageToConsole("nanoTimeEndAddToIndex: " + inFuncData.nanoTimeEndAddToIndex);
            ZPINcAppHelper.outMessageToConsole("recordTime: " + inFuncData.recordTime);
        }
        
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * </ul>
     * List of data has 100 records, this method return file names for records < 100
     * @param inFuncSubDir
     * @return 
     */
    private static TreeMap<Long, File> getNotFullFiles(File inFuncSubDir){
        TreeMap<Integer, File> itemsInSubDir = ZPINcIdxFileManager.getFileListFromSubDir(inFuncSubDir);
        TreeMap<Long, File> notFullItemsInSubDir = new TreeMap<Long, File>();
        for(Map.Entry<Integer, File> itemFile : itemsInSubDir.entrySet()){
            if( itemFile.getValue().isDirectory() ){
                checkFilesOnReadable(itemFile.getValue());
            }
            else{
                int recordsInFile = ZPINcIdxFileManager.getCountRecordDataInFile(itemFile.getValue());
                if( recordsInFile < 0 ){
                    if ( itemFile.getValue().delete() ){
                        ZPINcAppHelper.outMessage(ZPINcIdxFileManager.getStrCanPathFromFile(itemFile.getValue()) +
                        "_|_|_|_deleted");
                    }
                }
                if( recordsInFile != 100){
                    notFullItemsInSubDir.put(System.nanoTime(), itemFile.getValue());
                }
            }
        }
        return notFullItemsInSubDir;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#getNotFullFiles(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkFilesOnReadable(java.io.File) }
     * </ul>
     * If on the file write operation breaked, data in file damage, this method test file
     * on readable and delete damaged files
     * @param inFuncSubDir 
     */
    private static void checkFilesOnReadable(File inFuncSubDir){
        TreeMap<Integer, File> itemsInSubDir = ZPINcIdxFileManager.getFileListFromSubDir(inFuncSubDir);
        for(Map.Entry<Integer, File> itemFile : itemsInSubDir.entrySet()){
            if( itemFile.getValue().isDirectory() ){
                checkFilesOnReadable(itemFile.getValue());
            }
            else{
                if( !ZPINcIdxFileManager.isDataInFileNotWrong(itemFile.getValue()) ){
                    if ( itemFile.getValue().delete() ){
                        ZPINcAppHelper.outMessage(ZPINcIdxFileManager.getStrCanPathFromFile(itemFile.getValue()) +
                        "_|_|_|_deleted");
                    }
                }
            }
        }
    }
    
    /**
     * Not used
     * @param inFuncNameSubDir
     * @return
     */
    private static Object getNcClassNameForSubDir(String inFuncNameSubDir){
        switch (inFuncNameSubDir){
            case "/t":
                    return Object.class;
            case "/di":
                    return Object.class;
            case "/j":
                    return Object.class;
            case "/fl":
                    return ZPINcDcIdxDirListToFileAttr.class;
            case "/ft":
                    return ZPINcDcIdxDirListToFileType.class;
            case "/fh":
                    return ZPINcDcIdxDirListToFileHash.class;
            case "/fx":
                    return ZPINcDcIdxDirListToFileExist.class;
            case "/w":
                    return ZPINcDcIdxWordToFile.class;
            case "/sw":
                    return ZPINcDcIdxStorageWordToFile.class;
            case "/lw":
                    return ZPINcDcIdxLongWordListToFile.class;
            case "/ln":
                    return ZPINcDcIdxWordToFile.class;
        }
        return Object.class;
    }
    // wirte to text log files in /di directory into file name = timeStart(from /fx subdir lists) in system.nanotime
    // -> /subdir/namefile, id dir list, id in storage word
    // 100 records into one file, after record, rename to timeStart-TimeStop in system.nanotime
    // after danage data, part of not readable files may be repair form parsed logs, smoke and sleep

    /**
     * Not used
     * @param inFuncSubDir
     */
    private static void outFilesFromSubDirToConsole(File inFuncSubDir){
        File[] inDirFiles = inFuncSubDir.listFiles();
        ZPINcAppHelper.outMessageToConsole("");
        ZPINcAppHelper.outMessageToConsole("Directory path: " + ZPINcIdxFileManager.getStrCanPathFromFile(inFuncSubDir));
        ZPINcAppHelper.outMessageToConsole("Count files in directory: " + inDirFiles.length);
        for( File itemFile : inDirFiles){
            ZPINcAppHelper.outMessageToConsole("" + itemFile.getName());
        }
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * </ul>
     */
    private static void checkTmpIDsData(){
        File fileTmpIDs = ZPINcIdxFileManager.getTmpIdsFile();
        boolean fileGet = ZPINcIdxFileManager.isErrorForFileOperation(fileTmpIDs);
        if( fileGet ){
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ZPINcStrVarDescription.TMP_IDS_FILE.getStr()
                + ZPINcStrServiceMsg.NOT_EXIST_OR_WRONG.getStr());
        }
        if( !fileGet ){
            ZPINcTmpNowProcessInfo readedTmpIDsData = ZPINcIndexManageIDs.getTmpIDsData(fileTmpIDs);
            boolean isTmpIdsDataWrong = ZPINcIndexManageIDs.isTmpIDsDataWrong(readedTmpIDsData);
            if( !isTmpIdsDataWrong ){
                getTmpIdsDataToConsole(readedTmpIDsData);
            }
            boolean isEmpty = ZPINcIndexManageIDs.isTmpIDsDataEmpty(readedTmpIDsData);
            if( isEmpty ){
                ZPINcAppHelper.outMessage(
                    ZPINcStrLogMsgField.ERROR.getStr()
                    + ZPINcStrVarDescription.TMP_IDS_FILE.getStr()
                    + ZPINcStrServiceMsg.READED_DATA_IS_EMPTY.getStr());
            }
        }
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkTmpIDsData() }
     * </ul>
     * @param readedTmpIDsData
     */
    private static void getTmpIdsDataToConsole(ZPINcTmpNowProcessInfo readedTmpIDsData){
        ZPINcAppHelper.outMessageToConsole("TmpIdsData: ");
        ZPINcAppHelper.outMessageToConsole("journalid: " + readedTmpIDsData.journalid + " \tjournalname: " + readedTmpIDsData.journalname);
        ZPINcAppHelper.outMessageToConsole("listnameid: " + readedTmpIDsData.listnameid + " \tlistnameid: " + readedTmpIDsData.listname);
        ZPINcAppHelper.outMessageToConsole("hashlistnnameid: " + readedTmpIDsData.hashlistnameid + " \thashlistname: " + readedTmpIDsData.hashlistname);
        ZPINcAppHelper.outMessageToConsole("longwordlistnameid: " + readedTmpIDsData.longwordlistnameid + " \tlongwordlistname: " + readedTmpIDsData.longwordlistname);
    }

    
}
