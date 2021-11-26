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
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIndexManageIDs {
    private NcManageCfg ncThisMcCfg;
    /** Work directory object*/
    private File workDir;
    /** Data about count of IDs in Indexes readed from saved on Disk tempFile*/
    //private ZPINcTmpNowProcessInfo idsReadedData;
    /** Data about count of IDs in Indexes ready to save on Disk tempFile*/
    private ZPINcTmpNowProcessInfo idsToWriteData;
    /** Flag set to true if data about IDs changed and ready to save */
    private boolean idsReadyToWrite;
    /** Flag set to true when method checkDataForAllDirListFiles() finished
     * check for All work dirictories, if dirictories changed in change functions,
     * need set this flag in false
     */
    private boolean isDataOnDiskChecked;    
    /** Flag set to true when IDs data read form IDs temp file or repair from
     * work dirictories or generated Zeros
     */
    private boolean existLastIDs;
    /** Data about count of IDs in Indexes calculate from Files in work Dirictories*/
    private ZPINcTmpNowProcessInfo idsInDirData;
    /** Data readed from maximus Directory List File */
    private TreeMap<Long, NcDcIdxDirListToFileAttr> lastRecDataDFL;
    /** Data readed from maximus Directory List Hashes File */
    private ArrayList<NcDcIdxDirListToFileHash> lastRecDataDFHL;
    /** Data readed from maximus Directory List Long Word File */
    private ArrayList<NcDcIdxLongWordListToFile> lastRecDataDLWL;

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIMinFS#NcIMinFS() }
     * </ul>
     * @param ncThisMcCfg
     */
    protected ZPINcIndexManageIDs(NcManageCfg ncThisMcCfg) {
       this.ncThisMcCfg = ncThisMcCfg;
       createDirListFile();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getFileDataToSwing(java.io.File) }
     * </ul>
     * @return
     */
    protected boolean getLoadLastIDsStatus(){
        return this.existLastIDs;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#NcIndexManageIDs(ru.newcontrol.ncfv.NcManageCfg) }
     * </ul>
     * Create Directory List File
     * Generate and manage File names, check existing files
     */
    private void createDirListFile(){
        /** Read last IDs data form Disk, if record not found,
         * attemt to check folders and repair data or write Zero data in file
         */
        int retWriteTmpIDs = -1;
        ZPINcTmpNowProcessInfo retReadTmpIDs = readTmpIds();
        if( isTmpIDsDataEmpty(retReadTmpIDs) ){
            retReadTmpIDs = checkDataForAllDirListFiles();
            if( isTmpIDsDataEmpty(retReadTmpIDs) ){
                retReadTmpIDs = 
                    new ZPINcTmpNowProcessInfo("",
                        -1,
                        "",
                        -1,
                        "",
                        0,
                        "",
                        -1);
            }
            retWriteTmpIDs = writeTmpIDs(retReadTmpIDs);
        }
        
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListManager#putToDirectoryList(ru.newcontrol.ncfv.NcDcIdxDirListToFileAttr) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getFileDataToSwing(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#getResultMakeIndex(java.io.File) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexPreProcessFiles#makeIndexForFile(java.io.File) }
     * </ul>
     * @return
     */
    protected ZPINcTmpNowProcessInfo getIdsReadedData(){
        return readTmpIds();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxDirListManager#putToDirectoryList(ru.newcontrol.ncfv.NcDcIdxDirListToFileAttr) }
     * </ul>
     * @param fIdsToWrite
     * @return
     */
    protected int setNewIdsData(ZPINcTmpNowProcessInfo fIdsToWrite){
        int retWriteTmpIDs = writeTmpIDs(fIdsToWrite);
        if ( retWriteTmpIDs < 1 ){
            return retWriteTmpIDs;
        }
        return retWriteTmpIDs;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#createDirListFile() }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#setNewIdsData(ru.newcontrol.ncfv.NcTmpNowProcessInfo) }
     * </ul>
     * @param fIdsToWrite
     * @return 
     */
    private int writeTmpIDs(ZPINcTmpNowProcessInfo fIdsToWrite){
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(NcIdxFileManager.getTmpIdsFile())))
        {
            oos.writeObject(fIdsToWrite);
        }
        catch(Exception ex){
            NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
            return -1;
        }
        return 1;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#createDirListFile() }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#getIdsReadedData() }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#getTmpIDsData(java.io.File) }
     * </ul>
     * @return 
     */
    private static ZPINcTmpNowProcessInfo readTmpIds(){
        ZPINcTmpNowProcessInfo idsReadData;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NcIdxFileManager.getTmpIdsFile())))
        {
            idsReadData = (ZPINcTmpNowProcessInfo)ois.readObject();
        }
        catch(Exception ex){
            NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
            return new ZPINcTmpNowProcessInfo();
        }
        return idsReadData;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkTmpIDsData() }
     * </ul>
     * @param inFuncTmpIds
     * @return
     */
    protected static ZPINcTmpNowProcessInfo getTmpIDsData(File inFuncTmpIds){
        return readTmpIds();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkTmpIDsData() }
     * </ul>
     * @param inFuncData
     * @return
     */
    protected static boolean isTmpIDsDataWrong(ZPINcTmpNowProcessInfo inFuncData){
        if( inFuncData == null ){
            return true;
        }
        if( !isTmpIDsDataHashTrue(inFuncData) ){
            return true;
        }
        return false;
    }

    /**
     * Not used
     * @param inFuncData
     * @return
     */
    private static boolean isTmpIDsDataHasEmptyField(ZPINcTmpNowProcessInfo inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean journalnameIsEmpty = inFuncData.journalname == "";
        boolean journalidIsEmpty = inFuncData.journalid == -777;
        boolean listnameIsEmpty = inFuncData.listname == "";
        boolean listnameidIsEmpty = inFuncData.listnameid == -777;
        boolean hashlistIsEmpty = inFuncData.hashlistname == "";
        boolean hashlistnnameidIsEmpty = inFuncData.hashlistnameid == -777;
        boolean longwordlistnameIsEmpty = inFuncData.longwordlistname == "";
        boolean longwordlistnameidIsEmpty = inFuncData.longwordlistnameid == -777;
        return journalnameIsEmpty
                || journalidIsEmpty
                || listnameIsEmpty
                || listnameidIsEmpty
                || hashlistIsEmpty
                || hashlistnnameidIsEmpty
                || longwordlistnameIsEmpty
                || longwordlistnameidIsEmpty;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkTmpIDsData() }
     * </ul>
     * @param inFuncData
     * @return
     */
    protected static boolean isTmpIDsDataEmpty(ZPINcTmpNowProcessInfo inFuncData){
        if( inFuncData == null ){
            return true;
        }
        boolean journalnameIsEmpty = inFuncData.journalname == "";
        boolean journalidIsEmpty = inFuncData.journalid == -777;
        boolean listnameIsEmpty = inFuncData.listname == "";
        boolean listnameidIsEmpty = inFuncData.listnameid == -777;
        boolean hashlistIsEmpty = inFuncData.hashlistname == "";
        boolean hashlistnnameidIsEmpty = inFuncData.hashlistnameid == -777;
        boolean longwordlistnameIsEmpty = inFuncData.longwordlistname == "";
        boolean longwordlistnameidIsEmpty = inFuncData.longwordlistnameid == -777;
        
        boolean hashIsTrue =  isTmpIDsDataHashTrue(inFuncData);
        return journalnameIsEmpty
                && journalidIsEmpty
                && listnameIsEmpty
                && listnameidIsEmpty
                && hashlistIsEmpty
                && hashlistnnameidIsEmpty
                && longwordlistnameIsEmpty
                && longwordlistnameidIsEmpty
                && hashIsTrue;
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#isTmpIDsDataWrong(ru.newcontrol.ncfv.NcTmpNowProcessInfo) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#isTmpIDsDataEmpty(ru.newcontrol.ncfv.NcTmpNowProcessInfo) }
     * </ul>
     * @param inFuncData
     * @return
     */
    private static boolean isTmpIDsDataHashTrue(ZPINcTmpNowProcessInfo inFuncData){
        return inFuncData.recordHash == (""
                + inFuncData.journalname
                + inFuncData.journalid
                + inFuncData.listname
                + inFuncData.listnameid
                + inFuncData.hashlistname
                + inFuncData.hashlistnameid
                + inFuncData.longwordlistname
                + inFuncData.longwordlistnameid
                + inFuncData.recordTime).hashCode();
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#createDirListFile() }
     * </ul>
     * Method check afte record parameters: readyForRecord, count in readyForRecordData,
     * count records in current Directory List File, and if count readyForRecord has
     * record out of bound for current record File, than this method, creat chunk for
     * this recorded data, chunk 1 writed into current File and other data in chunk 2
     * recorded into new file
     * @return 
     */
    private ZPINcTmpNowProcessInfo checkDataForAllDirListFiles(){
        TreeMap<Integer, File> indexWorkSubDirFilesList = NcIdxFileManager.getIndexWorkSubDirFilesList();
        File ncmfsDFL = indexWorkSubDirFilesList.get("/fl".hashCode());
        if( NcIdxFileManager.isErrorForFileOperation(ncmfsDFL) ){
            NcAppHelper.outMessage(NcStrLogMsgField.ERROR.getStr()
            + "/fl directory check error"
            + NcIdxFileManager.getStrCanPathFromFile(ncmfsDFL));
            return new ZPINcTmpNowProcessInfo();
        }
        File ncmfsDFHL = indexWorkSubDirFilesList.get("/fx".hashCode());
        if( NcIdxFileManager.isErrorForFileOperation(ncmfsDFHL) ){
            NcAppHelper.outMessage(NcStrLogMsgField.ERROR.getStr()
            + "/fx directory check error"
            + NcIdxFileManager.getStrCanPathFromFile(ncmfsDFHL));
            return new ZPINcTmpNowProcessInfo();
        }
        File ncmfsDLWL = indexWorkSubDirFilesList.get("/lw".hashCode());
        if( NcIdxFileManager.isErrorForFileOperation(ncmfsDLWL) ){
            NcAppHelper.outMessage(NcStrLogMsgField.ERROR.getStr()
            + "/lw directory check error"
            + NcIdxFileManager.getStrCanPathFromFile(ncmfsDLWL));
            return new ZPINcTmpNowProcessInfo();
        }
        
        TreeMap<Integer, File> listDFL = NcIdxFileManager.getFileListFromSubDir(ncmfsDFL);
        TreeMap<Integer, File> listDFHL = NcIdxFileManager.getFileListFromSubDir(ncmfsDFHL);
        TreeMap<Integer, File> listDLWL = NcIdxFileManager.getFileListFromSubDir(ncmfsDLWL);
        
        

        /** Not released in this version */
        long fCjournalid = 0;
        String fCjournalname = "";
        /** Get last name for files in Dirictories */
        String fClistname = getLastNameFormDirListFiles(listDFL);
        String fChashlistname = getLastNameFormDirListFiles(listDFHL);
        String fClongwordlistname = getLastNameFormDirListFiles(listDLWL);

        /** Read data from last names in Directories, and get last IDs, if has
         errors in read (read function return -1), then set IDs to Zero*/
        long fClistnameid = -1;
        long fChashlistnnameid = -1;
        long fClongwordlistnameid = -1;
        if( readFromDFLIds(fClistname) > 0 ){
            fClistnameid = lastRecDataDFL.lastKey();
        }
        if( readFromDFHLIds(fChashlistname) > 0 ){
            fChashlistnnameid = lastRecDataDFHL.get(lastRecDataDFHL.size() - 1).dirListID;
        }
        if( readFromDLWLIds(fClongwordlistname) > 0 ){
            fClongwordlistnameid = lastRecDataDLWL.get(lastRecDataDLWL.size() - 1).nameID;
        }

        ZPINcTmpNowProcessInfo inDirIdsData = new ZPINcTmpNowProcessInfo(
            fCjournalname,
            fCjournalid, 
            fClistname,
            fClistnameid, 
            fChashlistname,
            fChashlistnnameid, 
            fClongwordlistname,
            fClongwordlistnameid);
        return inDirIdsData;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#checkDataForAllDirListFiles() }
     * </ul>
     * @param filesList
     * @return 
     */
    private String getLastNameFormDirListFiles(TreeMap<Integer, File> filesList){
        String retLastName = "";
        if( filesList.isEmpty() ){
            return retLastName;
        }
        for(Map.Entry<Integer, File> itemName : filesList.entrySet()){
            if ( NcIdxFileManager.getStrCanPathFromFile(itemName.getValue()).compareToIgnoreCase(retLastName) > 0){
                retLastName = NcIdxFileManager.getStrCanPathFromFile(itemName.getValue());
            }
        }
        return retLastName;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#checkDataForAllDirListFiles() }
     * </ul>
     * @param strFIds
     * @return 
     */
    private long readFromDFLIds(String strFIds){
        File fileFIds = new File(strFIds);
        if(fileExistRWAccessChecker(fileFIds)){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileFIds)))
            {
                lastRecDataDFL = (TreeMap<Long, NcDcIdxDirListToFileAttr>)ois.readObject();
            }
            catch(Exception ex){
                NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
                return -1;
            }
        }
        else{
            return -1;
        }
        return lastRecDataDFL.lastKey();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#checkDataForAllDirListFiles() }
     * </ul>
     * @param strFIds
     * @return 
     */
    private int readFromDFHLIds(String strFIds){
        File fileFIds = new File(strFIds);
        if(fileExistRWAccessChecker(fileFIds)){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileFIds)))
            {
                lastRecDataDFHL = (ArrayList<NcDcIdxDirListToFileHash>)ois.readObject();
            }
            catch(Exception ex){
                NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
                return -1;
            }
        }
        else{
            return -1;
        }
        return lastRecDataDFHL.size();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#checkDataForAllDirListFiles() }
     * </ul>
     * @param strFIds
     * @return 
     */
    private int readFromDLWLIds(String strFIds){
        File fileFIds = new File(strFIds);
        if(fileExistRWAccessChecker(fileFIds)){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileFIds)))
            {
                lastRecDataDLWL = (ArrayList<NcDcIdxLongWordListToFile>)ois.readObject();
            }
            catch(Exception ex){
                NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
                return -1;
            }
        }
        else{
            return -1;
        }
        return lastRecDataDLWL.size();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#readFromDFLIds(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#readFromDFHLIds(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcIndexManageIDs#readFromDLWLIds(java.lang.String) }
     * </ul>
     * @param strFIds
     * @return 
     */
    private boolean fileExistRWAccessChecker(File strFIds){
        try{
            if(strFIds.exists()
                    && strFIds.canRead()
                    && strFIds.canWrite()
                    && strFIds.isFile()){
                return true;
            }
        }catch(NullPointerException ex){
            NcAppHelper.logException(
                    NcIndexManageIDs.class.getCanonicalName(), ex);
            return false;
        }
        return false;
    }

}
