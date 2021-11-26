/*
 * Copyright 2019 wladimirowichbiaran.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gui.pkgfor.some.projects;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAdihZipStorages {
    private final Long timeCreation;
    private final UUID objectLabel;
    private final ConcurrentSkipListMap<Integer, URI> storagesUriList;
    private final ConcurrentSkipListMap<Integer, Path> zipStoreFileList;
    private final ConcurrentSkipListMap<Integer, FileSystem> openedZipStoreList;
    ZPIAdihZipStorages(final ZPIThIndexRule ruleIndexOuter){
        this.timeCreation = System.nanoTime();
        this.objectLabel = UUID.randomUUID();
        this.storagesUriList = new ConcurrentSkipListMap<Integer, URI>();
        this.zipStoreFileList = new ConcurrentSkipListMap<Integer, Path>();
        this.openedZipStoreList = new ConcurrentSkipListMap<Integer, FileSystem>();
        createStoragesList();
        //fillOpenStoreList();
    }
    /**
     * This list of parameters changed in {@link ru.newcontrol.ncfv.AdihHelper#getStoragesNames AdihHelper.getStoragesNames()}
     * @return 
     */
    private String[] getStoragesNames(){
        return AdihHelper.getStoragesNames();
    }
    /**
     * <ul>
     * <li>   0 -   UserHome
     * <li>   1 -   ClassPathApplicationDirectory
     * <li>   2 -   ncidxfvSubDirIndex
     * 
     * <li>   3 -   di-indexDirList
     * <li>   4 -   t-indexTempData
     * <li>   5 -   j-indexJournal
     * <li>   6 -   fl-indexFileList
     *              
     * <li>   7 -   ft-indexFileType
     * <li>   8 -   fh-indexFileHash
     * <li>   9 -   fx-indexFileExist
     * 
     * <li>  10 -   w-indexWord
     * <li>  11 -   sw-indexStorageWord
     * <li>  12 -   lw-indexLongWordList
     * <li>  13 -   ln-indexLongWordData
     * </ul>
     * This list of parameters changed in {@link ru.newcontrol.ncfv.AdihHelper#getStoragesNames AdihHelper.getStoragesNames()}
     * Return code of parameter by his number, calculeted from some fileds
     * @param numParam
     * @return hashCode for Parameter by his number
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or not natural number <code>numParam &lt 0 (Zero)</code>
     * @see ru.newcontrol.ncfv.AdihHelper#getStoragesNames AdihHelper.getStoragesNames()
     */
    private Integer getParamCodeByNumber(int numParam){
        String[] paramNames;
        try {
            paramNames = getStoragesNames();
            if( numParam < 0 ){
                throw new IllegalArgumentException(ThWordStatusError.class.getCanonicalName() 
                                + " parameters of flow statusMainFlow in StorageWord is not valid, "
                                + " 0 (zero) > , need for return " + numParam + "count parameters: " 
                                + paramNames.length);
            }
            if( numParam > (paramNames.length - 1) ){
                throw new IllegalArgumentException(ThWordStatusError.class.getCanonicalName() 
                                + " parameters of flow statusMainFlow in StorageWord is not valid, "
                                + "count parameters: " 
                                + paramNames.length 
                                + ", need for return " + numParam);
            } 
            int codeForParameter = paramNames[numParam]
                    .concat(String.valueOf(this.timeCreation))
                    .concat(this.objectLabel.toString()).hashCode();
            return codeForParameter;
        } finally {
            paramNames = null;
        }
    }
    /**
     * Count records (array.length) returned from {@link #getParamNames }
     * @return 
     */
    private Integer getParamCount(){
        String[] paramNames;
        try {
            paramNames = getStoragesNames();
            return paramNames.length;
        } finally {
            paramNames = null;
        }
    }
    /**
     * 
     * @param numParam
     * @return name of param by his number
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or not natural number <code>numParam &lt 0 (Zero)</code>
     * @see ru.newcontrol.ncfv.AdihHelper#getParamNames AdihHelper.getParamNames()
     */
    private String getParamNameByNumber(int numParam){
        String[] paramNames;
        String paramName;
        try {
            paramNames = getStoragesNames();
            if( numParam < 0 ){
                throw new IllegalArgumentException(ThWordStatusMainFlow.class.getCanonicalName() 
                                + " parameters of flow statusMainFlow in StorageWord is not valid, "
                                + " 0 (zero) > , need for return " + numParam + "count parameters: " 
                                + paramNames.length);
            }
            if( numParam > (paramNames.length - 1) ){
                throw new IllegalArgumentException(ThWordStatusMainFlow.class.getCanonicalName() 
                                + " parameters of flow statusMainFlow in StorageWord is not valid, "
                                + "count parameters: " 
                                + paramNames.length 
                                + ", need for return " + numParam);
            } 
            paramName = new String(paramNames[numParam]);
            return paramName;
        } finally {
            paramNames = null;
            paramName = null;
        }
    }

    /**
     * generate list of typed bus for temporary cache lines for log
     */
    private void createStoragesList(){
        Path toAddIntoList = null;
        Path subDirIndex = null;
        URI uriZipIndexStorage;
        String prefixStorageByNumber = new String();
        Integer paramCodeByNumber;
        Integer countParamsDataFsForSet;
        Integer idx;
        try {
            countParamsDataFsForSet = getParamCount();
            
            paramCodeByNumber = getParamCodeByNumber(0);
            toAddIntoList = AdihFileOperations.getUserHomeCheckedPath();
            this.zipStoreFileList.put(paramCodeByNumber, toAddIntoList);
            
            paramCodeByNumber = getParamCodeByNumber(1);
            toAddIntoList = AdihFileOperations.getAppCheckedPath();
            this.zipStoreFileList.put(paramCodeByNumber, toAddIntoList);
            
            paramCodeByNumber = getParamCodeByNumber(2);
            toAddIntoList = createIfNotExistSubDirIndex();
            subDirIndex = toAddIntoList;
            this.zipStoreFileList.put(paramCodeByNumber, toAddIntoList);
            
            for(idx = 3; idx < countParamsDataFsForSet; idx++ ){
                prefixStorageByNumber = AdihHelper.getPrefixStorageByNumber(idx);
                toAddIntoList = buildZipStoragesPath(subDirIndex, prefixStorageByNumber);
                uriZipIndexStorage = URI.create(AppFileNamesConstants.PREFIX_TO_URI_STORAGES + toAddIntoList.toUri());
                paramCodeByNumber = getParamCodeByNumber(idx);
                this.zipStoreFileList.put(paramCodeByNumber, toAddIntoList);
                this.storagesUriList.put(paramCodeByNumber, uriZipIndexStorage);
            }
        } finally {
            toAddIntoList = null;
            subDirIndex = null;
            uriZipIndexStorage = null;
            AdihUtilization.utilizeStringValues(new String[]{prefixStorageByNumber});
            prefixStorageByNumber = null;
            paramCodeByNumber = null;
            countParamsDataFsForSet = null;
            idx = null;
        }
    }
    /**
     * <ul>
     * <li>   0 -   UserHome
     * <li>   1 -   ClassPathApplicationDirectory
     * <li>   2 -   ncidxfvSubDirIndex
     * 
     * <li>   3 -   di-indexDirList
     * <li>   4 -   t-indexTempData
     * <li>   5 -   j-indexJournal
     * <li>   6 -   fl-indexFileList
     *              
     * <li>   7 -   ft-indexFileType
     * <li>   8 -   fh-indexFileHash
     * <li>   9 -   fx-indexFileExist
     * 
     * <li>  10 -   w-indexWord
     * <li>  11 -   sw-indexStorageWord
     * <li>  12 -   lw-indexLongWordList
     * <li>  13 -   ln-indexLongWordData
     * </ul>
     * @param numberOfPrefixList
     * @return 
     */
    protected FileSystem getStoreFileSystemByNumber(Integer numberOfPrefixList){
        if( (numberOfPrefixList < 3) || (numberOfPrefixList > 13) ){
            return null;
        }
        Integer paramCodeByNumber = getParamCodeByNumber(numberOfPrefixList);
        FileSystem getOpenedStore = this.openedZipStoreList.get(paramCodeByNumber);
        Path storageFile;
        URI uriForStorage;
        try {
            if( getOpenedStore == null ){
                storageFile = this.zipStoreFileList.get(paramCodeByNumber);
                uriForStorage = this.storagesUriList.get(paramCodeByNumber);
                getOpenedStore = AdihFileOperations.getStorageFileSystem(storageFile, uriForStorage);
                this.openedZipStoreList.put(paramCodeByNumber, getOpenedStore);
            }
        return getOpenedStore;
        } finally {
            paramCodeByNumber = null;
            getOpenedStore = null;
            storageFile = null;
            uriForStorage = null;
        }
    }
    /**
     * 
     * @return path for user home directory
     */
    protected Path getPathUserHome(){
        Integer paramCodeByNumber;
        try {
            paramCodeByNumber = getParamCodeByNumber(0);
            return this.zipStoreFileList.get(paramCodeByNumber);
        } finally {
            paramCodeByNumber = null;
        }
    }
    /**
     * 
     * @return path for Application, where run class
     */
    protected Path getPathApplicationPath(){
        Integer paramCodeByNumber;
        try {
            paramCodeByNumber = getParamCodeByNumber(1);
            return this.zipStoreFileList.get(paramCodeByNumber);
        } finally {
            paramCodeByNumber = null;
        }
    }
    /**
     * 
     * @return path for index subDirectory in user home directory
     */
    protected Path getPathIndexSubDir(){
        Integer paramCodeByNumber;
        try {
            paramCodeByNumber = getParamCodeByNumber(2);
            return this.zipStoreFileList.get(paramCodeByNumber);
        } finally {
            paramCodeByNumber = null;
        }
    }
    /**
     * <ul>
     * <li>   0 -   UserHome
     * <li>   1 -   ClassPathApplicationDirectory
     * <li>   2 -   ncidxfvSubDirIndex
     * 
     * <li>   3 -   di-indexDirList
     * <li>   4 -   t-indexTempData
     * <li>   5 -   j-indexJournal
     * <li>   6 -   fl-indexFileList
     *              
     * <li>   7 -   ft-indexFileType
     * <li>   8 -   fh-indexFileHash
     * <li>   9 -   fx-indexFileExist
     * 
     * <li>  10 -   w-indexWord
     * <li>  11 -   sw-indexStorageWord
     * <li>  12 -   lw-indexLongWordList
     * <li>  13 -   ln-indexLongWordData
     * </ul> 
     * @param prefixStr
     * @return 
     */
    protected FileSystem getStoreFileSystemByPrefix(String prefixStr){
        if( prefixStr == null ){
            return null;
        }
        if( prefixStr.isEmpty() ){
            return null;
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(prefixStr) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_TMP) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_JOURNAL) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_LIST) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_TYPE) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_HASH) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_EXIST) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_WORD) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_LIST) ){
            return getStoreFileSystemByNumber(3);
        }
        if( prefixStr.toLowerCase().equalsIgnoreCase(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_DATA) ){
            return getStoreFileSystemByNumber(3);
        }
        return null;
    }
    /**
     * 
     */
    private void fillOpenStoreList(){
        Path storageFile;
        URI uriForStorage;
        Boolean pathIsFile;
        FileSystem storageFileSystem;
        try {
            for( Map.Entry<Integer, URI> itemOfURI : this.storagesUriList.entrySet() ){
                storageFile = this.zipStoreFileList.get(itemOfURI.getKey());
                if( storageFile != null ){
                    uriForStorage = itemOfURI.getValue();
                    storageFileSystem = AdihFileOperations.getStorageFileSystem(storageFile, uriForStorage);
                    if( storageFileSystem != null ){
                        this.openedZipStoreList.put(itemOfURI.getKey(), storageFileSystem);
                    }
                }
            }
        } finally {
            storageFile = null;
            uriForStorage = null;
            pathIsFile = null;
            storageFileSystem = null;
        }
    }
    /**
     * 
     */
    private void closeOpenedAndUtilizeValuesFromList(){
        FileSystem removedStorageItem;
        Boolean closeOpenedStorage;
        try {
            for( Map.Entry<Integer, FileSystem> itemOfFileSystems : this.openedZipStoreList.entrySet() ){
                removedStorageItem = itemOfFileSystems.getValue();
                closeOpenedStorage = AdihFileOperations.closeOpenedStorage(removedStorageItem);
                if( !closeOpenedStorage ){
                    //not closed, retry in other procedure or not opened
                }
            }
        } finally {
            removedStorageItem = null;
            closeOpenedStorage = null;
        }
    }
    /**
     * 
     */
    protected void utilizeAllLists(){
        
        Integer key;
        FileSystem removeZipStorageItem;
        URI removeUriStorageItem;
        Path removePathStorageItem;
        Boolean hasNotClosedStorages = Boolean.FALSE;
        try {
            retryCloseStorage: {
                hasNotClosedStorages = Boolean.FALSE;
                closeOpenedAndUtilizeValuesFromList();
                for( Map.Entry<Integer, FileSystem> itemOfFs : this.openedZipStoreList.entrySet() ){
                    key = itemOfFs.getKey();
                    if( !itemOfFs.getValue().isOpen() ){
                        removeZipStorageItem = this.openedZipStoreList.remove(key);
                        removeZipStorageItem = null;
                        key = null;
                    } else {
                        hasNotClosedStorages = Boolean.TRUE;
                    }
                }

                for( Map.Entry<Integer, URI> itemOfURI : this.storagesUriList.entrySet() ){
                    key = itemOfURI.getKey();
                    if( hasNotClosedStorages ){
                        if( this.openedZipStoreList.containsKey(key) ){
                            continue;
                        }
                    }
                    removeUriStorageItem = this.storagesUriList.remove(key);
                    removeUriStorageItem = null;
                    key = null;
                }

                for( Map.Entry<Integer, Path> itemOfPath : this.zipStoreFileList.entrySet() ){
                    key = itemOfPath.getKey();
                    if( hasNotClosedStorages ){
                        if( this.openedZipStoreList.containsKey(key) ){
                            continue;
                        }
                    }
                    removePathStorageItem = this.zipStoreFileList.remove(key);
                    removeZipStorageItem = null;
                    key = null;
                }
                if( hasNotClosedStorages ){
                    break retryCloseStorage;
                }
            }
            this.openedZipStoreList.clear();
            this.storagesUriList.clear();
            this.zipStoreFileList.clear();
        } finally {
            removePathStorageItem = null;
            removeZipStorageItem = null;
            removeUriStorageItem = null;
            key = null;
            hasNotClosedStorages = null;
        }
    }
    /**
     * Found on file system zip index storage and add it into list or create new
     * if it not found
     * @param parenForStorage
     * @param prefixStorage
     * @return 
     */
    private static Path buildZipStoragesPath(Path parenForStorage, String prefixStorage){
        Path forReturnStorage = null;
        Path searchinIndexDirStorageByPrefix = null;
        String parentDir = new String();
        String buildedName = new String();
        try{
            searchinIndexDirStorageByPrefix = AdihFileOperations.searchinIndexDirStorageByPrefix(parenForStorage, prefixStorage);
            if( searchinIndexDirStorageByPrefix != null ){
                return searchinIndexDirStorageByPrefix;
            }
            parentDir = parenForStorage.toString();
            buildedName = prefixStorage
                    .concat(AdihGetvalues.getNowTimeStringMillisFsNames())
                    .concat(AppFileNamesConstants.FILE_INDEX_EXT);
            try {
                    forReturnStorage = Paths.get(parentDir, buildedName);
            } catch(InvalidPathException exInvPath) {
                System.err.println(AdihZipStorages.class.getCanonicalName() 
                        + "[ERROR] Index SubDirectory build not complete path is " 
                        + parentDir + " and builded name " + buildedName
                        + AdilConstants.EXCEPTION_MSG 
                        + exInvPath.getMessage()
                );
                exInvPath.printStackTrace();
                System.exit(0);
            }
            return forReturnStorage;
        } finally {
            forReturnStorage = null;
            AdihUtilization.utilizeStringValues(new String[]{parentDir, buildedName});
            searchinIndexDirStorageByPrefix = null;
        }
    }
    /**
     * <code>/...pathToUserHome/ncidxfv/</code>
     * @return 
     */
    private static Path createIfNotExistSubDirIndex(){
        Boolean createDirIfNotExist = null;
        Path userHomeSubDirIndex = null;
        Path userHomeCheckedPath = AdihFileOperations.getUserHomeCheckedPath();
        String userHomeStr = userHomeCheckedPath.toString();
        String prefixStorageByNumber = AdihHelper.getPrefixStorageByNumber(2);
        try{
            try {
                    userHomeSubDirIndex = Paths.get(userHomeStr,prefixStorageByNumber);
            } catch(InvalidPathException exInvPath) {
                System.err.println(AdihZipStorages.class.getCanonicalName() 
                        + "[ERROR] Index SubDirectory build not complete path is " 
                        + userHomeStr + " and subDir " + prefixStorageByNumber
                        + AdilConstants.EXCEPTION_MSG 
                        + exInvPath.getMessage()
                );
                exInvPath.printStackTrace();
                System.exit(0);
            }

            createDirIfNotExist = AdihFileOperations.createDirIfNotExist(userHomeSubDirIndex);
            if( !createDirIfNotExist ){
                System.err.println(AdihZipStorages.class.getCanonicalName() 
                            + "[ERROR] Index SubDirectory build not complete path is " 
                            + userHomeSubDirIndex.toString() + " "
                            + AdihFileOperations.class.getCanonicalName() 
                            + ".createDirIfNotExist() return "
                            + String.valueOf(createDirIfNotExist)
                    );
                    System.exit(0);
            }
            return userHomeSubDirIndex;
        } finally {
            createDirIfNotExist = null;
            userHomeSubDirIndex = null;
            userHomeCheckedPath = null;
            AdihUtilization.utilizeStringValues(new String[]{userHomeStr, prefixStorageByNumber});
        }
    }
    /**
     * 
     */
    protected void updateStorageList(){
        utilizeAllLists();
        createStoragesList();
        fillOpenStoreList();
    }
    /**
     * 
     */
    protected void printAllList(){
        System.out.println("*** *** *** list opened storages");
        for( Map.Entry<Integer, FileSystem> entrySet : this.openedZipStoreList.entrySet() ){
            System.out.println("key " + String.valueOf(entrySet.getKey()) 
                    + " storage " 
                    + entrySet.getValue().toString()
                    + " is open " + String.valueOf(entrySet.getValue().isOpen())
            );
        }
        System.out.println("*** *** *** list URI for storage files");
        for( Map.Entry<Integer, URI> entrySet : this.storagesUriList.entrySet() ){
            System.out.println("key " + String.valueOf(entrySet.getKey()) + " storage " + entrySet.getValue().toString());
        }
        System.out.println("*** *** *** list Path for storage files");
        for( Map.Entry<Integer, Path> entrySet : this.zipStoreFileList.entrySet() ){
            System.out.println("key " + String.valueOf(entrySet.getKey()) + " storage " + entrySet.getValue().toString());
        }
    }
}
