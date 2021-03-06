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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppFileStorageIndex {
    private ConcurrentSkipListMap<String, Map<String, String>> storagesMap;
    private ArrayList<String> listOfPrefix;
    public ZPIAppFileStorageIndex() {
        this.storagesMap = ZPIAppFileStorageIndex.getStoragesMap();
        this.listOfPrefix = ZPIAppFileStorageIndex.getListOfPrefix();
    }
    protected URI byPrefixGetUri(String prefixOfStorage){
        URI uriFromMapByPrefix = ZPIAppFileStorageIndex.getUriFromMapByPrefix(this.storagesMap, prefixOfStorage);
        return uriFromMapByPrefix;
    }
    protected Map<String, String> byPrefixGetMap(String prefixOfStorage){
        Map<String, String> paramMapByPrefix = ZPIAppFileStorageIndex.getParamMapByPrefix(this.storagesMap, prefixOfStorage);
        return paramMapByPrefix;
    }
    protected void updateMapForStorages(){
        this.storagesMap = ZPIAppFileStorageIndex.getStoragesMap();
        this.listOfPrefix = ZPIAppFileStorageIndex.getListOfPrefix();
    }
    protected ArrayList<String> listOfPrefixes(){
        return this.listOfPrefix;
    }
    
    protected static Path getIndexFolder(){
        Path appRWEDCheckedPath = ZPIAppFileOperationsSimple.getUserHomeRWEDCheckedPath();
        Path orCreateAnySubDir = ZPIAppFileOperationsSimple.getOrCreateAnySubDir(appRWEDCheckedPath, 
                ZPIAppFileNamesConstants.DIR_IDX);
        return orCreateAnySubDir;
    }
    /**
     * get 
     * @param fsPrefixConstant
     * @return 
     */
    protected static Map<String, String> buildForStorageByPrefix(String fsPrefixConstant){
        Path indexFolder = ZPIAppFileStorageIndex.getIndexFolder();
        String prefixWithTime = fsPrefixConstant 
                + ZPIAppFileOperationsSimple.getNowTimeStringWithMS() 
                + ZPIAppFileNamesConstants.FILE_INDEX_EXT;
        Path getStoragePath = Paths.get(indexFolder.toString(), prefixWithTime);
        Map<String, String> fsPropExistOrCreate = ZPIAppFileStorageIndex.getFsPropExist();
        if( !ZPIAppFileOperationsSimple.existAndHasAccessRWNotLink(getStoragePath) ){
            fsPropExistOrCreate = ZPIAppFileStorageIndex.getFsPropCreate();
        }
        URI uriZipIndexStorage = URI.create("jar:" + getStoragePath.toUri());
        fsPropExistOrCreate.put(ZPIAppFileNamesConstants.FILE_INDEX_KEY_MAP_URI, uriZipIndexStorage.toString());
        return fsPropExistOrCreate;
    }
    protected static Map<String, String> getForStorageByPrefix(Path outerStoragePath){
        Map<String, String> fsPropExistOrCreate = ZPIAppFileStorageIndex.getFsPropExist();
        if( !ZPIAppFileOperationsSimple.existAndHasAccessRWNotLink(outerStoragePath) ){
            fsPropExistOrCreate = ZPIAppFileStorageIndex.getFsPropCreate();
        }
        URI uriZipIndexStorage = URI.create("jar:" + outerStoragePath.toUri());
        fsPropExistOrCreate.put(ZPIAppFileNamesConstants.FILE_INDEX_KEY_MAP_URI, uriZipIndexStorage.toString());
        return fsPropExistOrCreate;
    }
    protected static Map<String, String> getFsPropCreate(){
        Map<String, String> zipfsPropeties = new HashMap<>();
        zipfsPropeties.put("create","true");
        zipfsPropeties.put("encoding","UTF-8");
        
        return zipfsPropeties;
    }
    protected static Map<String, String> getFsPropExist(){
        Map<String, String> zipfsPropeties = new HashMap<>();
        zipfsPropeties.put("create","false");
        zipfsPropeties.put("encoding","UTF-8");
        return zipfsPropeties;
    }
    protected static ConcurrentSkipListMap<String, Map<String, String>> getStoragesMap(){
        ConcurrentSkipListMap<String, Map<String, String>> toReturnStorages = new ConcurrentSkipListMap<String, Map<String, String>>();
        ArrayList<String> listOfPrefix = ZPIAppFileStorageIndex.getListOfPrefix();
        for( String itemPrefix : listOfPrefix){
            Boolean haveException = Boolean.FALSE;
            Path fromIndexDirLastStorage;
            try{
                fromIndexDirLastStorage = ZPIAppFileStorageIndex.getFromIndexDirLastStorage(itemPrefix);
                Map<String, String> forStorageByPrefix = ZPIAppFileStorageIndex.getForStorageByPrefix(fromIndexDirLastStorage);
                toReturnStorages.put(itemPrefix, forStorageByPrefix);
            } catch (IOException ex) {
                System.err.print(ex.getMessage());
                ex.printStackTrace();
                haveException = Boolean.TRUE;
            }
            if( haveException ){
                Map<String, String> buildForStorageByPrefix = ZPIAppFileStorageIndex.buildForStorageByPrefix(itemPrefix);
                toReturnStorages.put(itemPrefix, buildForStorageByPrefix);
                System.out.println("For prefix " + itemPrefix
                        + " create " + buildForStorageByPrefix.size());
            }
        }
        return toReturnStorages;
    }
    protected static URI getUriFromMapByPrefix(ConcurrentSkipListMap<String, Map<String, String>> storagesMap,
            String fsPrefixConstant){
        Map<String, String> getMapWithURI = storagesMap.get(fsPrefixConstant);
        String strForUri = getMapWithURI.get(ZPIAppFileNamesConstants.FILE_INDEX_KEY_MAP_URI);
        URI uriZipIndexStorage = URI.create(strForUri);
        return uriZipIndexStorage;
    }
    protected static Map<String, String> getParamMapByPrefix(ConcurrentSkipListMap<String, Map<String, String>> storagesMap,
            String fsPrefixConstant){
        Map<String, String> getMapWithURI = storagesMap.get(fsPrefixConstant);
        Map<String, String> forReturnMap = new HashMap<>();
        for(Map.Entry<String, String> items : getMapWithURI.entrySet()){
            if( !items.getKey().toLowerCase().contains(ZPIAppFileNamesConstants.FILE_INDEX_KEY_MAP_URI.toLowerCase()) ){
                forReturnMap.put(new String(items.getKey()), new String(items.getValue()));
            }
        }
        return forReturnMap;
    }
    /**
     * 
     * @param prefixForFound
     * @return
     * @throws IOException 
     */
    protected static Path getFromIndexDirLastStorage(String prefixForFound) throws IOException{
        Path indexFolder = ZPIAppFileStorageIndex.getIndexFolder();
        ArrayList<Path> filesByMaskFromDir = ZPIAppFileOperationsSimple.getFilesByMaskFromDir(indexFolder, 
                "{" + prefixForFound + "}*");
        if( filesByMaskFromDir.isEmpty() ){
            throw new IOException(ZPIAppFileStorageIndex.class.getCanonicalName() 
                    + " not found storages for prefix " 
                    + prefixForFound);
        }
            Path getMaxCompared = filesByMaskFromDir.get(0);
            for(Path itemMask : filesByMaskFromDir){
                if( getMaxCompared.compareTo(itemMask) > 0 ){
                    getMaxCompared = itemMask;
                }
            }
        return getMaxCompared;
    }
    protected static ArrayList<String> getListOfPrefix(){
        ArrayList<String> listForReturn = new ArrayList<String>();
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_TMP);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_JOURNAL);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_FILE_LIST);
        //type and depth in dirictories structure
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_FILE_TYPE);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_FILE_HASH);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_FILE_EXIST);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_LIST);
        listForReturn.add(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_DATA);
        return listForReturn;
    }
    /**
     * @todo test open storages for exceptions,
     * stop routers if not open storages
     * @return 
     */
    protected static Boolean testOpenStore(){
        return Boolean.TRUE;
    }
}
