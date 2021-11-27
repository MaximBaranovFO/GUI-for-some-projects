/*
 * Copyright 2018 wladimirowichbiaran.
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
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcFsIdxStorageInit {
    
    protected static ThreadLocal<ZPINcParamFs> getStorage(ZPINcSwGUIComponentStatus lComp){
        ThreadLocal<ZPINcParamFs> toReturn = openStorage();
        ZPINcParamFs fileStorageParam = toReturn.get();
        
        ArrayList<String> arrStr = new ArrayList<String>();
        arrStr.add("ZPINcParamFs:");
        arrStr.add(fileStorageParam.toString());
        
        arrStr.add("Storage:");
        arrStr.add(toReturn.toString());
        arrStr.add("isOpen: " + fileStorageParam.getIdxFs().isOpen());
        //arrStr.add("getDirDirList: " + fileStorageParam.getDirDirList().toString());
        
        //ZPINcParamFs dataStorage = initStorageStructure(fileStorage);
        //toReturn.set(dataStorage);
        
        
        arrStr.add("attempt to open storage:");
        /*try(FileSystem fsZipIndexStorage = 
            FileSystems.newFileSystem(toReturn, getFsPropExist())){
            arrStr.add("isOpen: " + fsZipIndexStorage.isOpen());
            Iterable<Path> rootDirectories = fsZipIndexStorage.getRootDirectories();
            
            Iterator<Path> iterator = rootDirectories.iterator();
            while (iterator.hasNext()) {
                Path next = iterator.next();
                arrStr.add(next.toString());

            }
        } catch (IOException ex) {
            ZPINcAppHelper.logException(ZPINcFsIdxStorageInit.class.getCanonicalName(), ex);
        }*/
        
        ZPINcThWorkerUpGUITreeWork.workTreeAddChildren(lComp, arrStr);
        return toReturn;
    }
    
    private static ThreadLocal<ZPINcParamFs> openStorage(){
        Path pathIndexFile = buildPathToFileOfIdxStorage();
        Map<String, String> fsProperties = getFsPropExist();
        ThreadLocal<ZPINcParamFs> toReturn = new ThreadLocal<>();
        if( !ZPINcFsIdxOperationFiles.existAndHasAccessRWNotLink(pathIndexFile) ){
            fsProperties = getFsPropCreate();
        }
        URI uriZipIndexStorage = URI.create("jar:" + pathIndexFile.toUri());
        try(FileSystem fsZipIndexStorage = 
            FileSystems.newFileSystem(uriZipIndexStorage, fsProperties)){
            
            ZPINcParamFs dataStorage = initStorageStructure(fsZipIndexStorage);
            
            toReturn.set(dataStorage);
            return toReturn;
        } catch (IOException ex) {
            ZPINcAppHelper.logException(ZPINcFsIdxStorageInit.class.getCanonicalName(), ex);
        }
        String strMsg = "Imposible to create file for index Storage, see log";
        ZPINcAppHelper.outMessage(
            ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
            + strMsg
        );
        throw new RuntimeException(strMsg);
    }
    
    

    protected static ZPINcParamFs initStorageStructure(FileSystem inFS){
        
        Path dirDirList = inFS.getPath(ZPINcStrFileDir.DIR_DIR_LIST.getStr());
        
        ZPINcFsIdxOperationDirs.create(dirDirList);
        
        Path dirFileExist = inFS.getPath(ZPINcStrFileDir.DIR_FILE_EXIST.getStr());
        ZPINcFsIdxOperationDirs.create(dirFileExist);
        
        Path dirFileHash = inFS.getPath(ZPINcStrFileDir.DIR_FILE_HASH.getStr());
        ZPINcFsIdxOperationDirs.create(dirFileHash);
        
        Path dirFileList = inFS.getPath(ZPINcStrFileDir.DIR_FILE_LIST.getStr());
        ZPINcFsIdxOperationDirs.create(dirFileList);
        
        Path dirFileType = inFS.getPath(ZPINcStrFileDir.DIR_FILE_TYPE.getStr());
        ZPINcFsIdxOperationDirs.create(dirFileType);
        
        Path dirJournal = inFS.getPath(ZPINcStrFileDir.DIR_JOURNAL.getStr());
        ZPINcFsIdxOperationDirs.create(dirJournal);
        
        Path dirLongWordList = inFS.getPath(ZPINcStrFileDir.DIR_LONG_WORD_LIST.getStr());
        ZPINcFsIdxOperationDirs.create(dirLongWordList);
        
        Path dirLongWordData = inFS.getPath(ZPINcStrFileDir.DIR_LONG_WORD_DATA.getStr());
        ZPINcFsIdxOperationDirs.create(dirLongWordData);
        
        Path dirStorageWord = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr());
        ZPINcFsIdxOperationDirs.create(dirStorageWord);
        
        Path dirStorageWordAbc = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr(),
                ZPINcTypeOfWord.NCLVLABC.getName());
        ZPINcFsIdxOperationDirs.create(dirStorageWordAbc);
        
        Path dirStorageWordNum = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr(),
                ZPINcTypeOfWord.NCLVLNUM.getName());
        ZPINcFsIdxOperationDirs.create(dirStorageWordNum);
        
        Path dirStorageWordRabc = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr(),
                ZPINcTypeOfWord.NCLVLRABC.getName());
        ZPINcFsIdxOperationDirs.create(dirStorageWordRabc);
        
        Path dirStorageWordSpace = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr(),
                ZPINcTypeOfWord.NCLVLSPACE.getName());
        ZPINcFsIdxOperationDirs.create(dirStorageWordSpace);
        
        Path dirStorageWordSym = inFS.getPath(ZPINcStrFileDir.DIR_STORAGE_WORD.getStr(),
                ZPINcTypeOfWord.NCLVLSYM.getName());
        ZPINcFsIdxOperationDirs.create(dirStorageWordSym);
        
        Path dirTmp = inFS.getPath(ZPINcStrFileDir.DIR_TMP.getStr());
        ZPINcFsIdxOperationDirs.create(dirTmp);
        
        Path dirWord = inFS.getPath(ZPINcStrFileDir.DIR_WORD.getStr());
        ZPINcFsIdxOperationDirs.create(dirWord);
        
        ZPINcParamFs forReturn = new ZPINcParamFs(inFS, 
            dirDirList, 
            dirFileExist, 
            dirFileHash, 
            dirFileList, 
            dirFileType, 
            dirJournal, 
            dirLongWordList, 
            dirLongWordData, 
            dirStorageWord, 
            dirStorageWordAbc, 
            dirStorageWordNum, 
            dirStorageWordRabc, 
            dirStorageWordSpace, 
            dirStorageWordSym, 
            dirTmp, 
            dirWord);
        return forReturn;
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

    protected static Path buildPathToFileOfIdxStorage(){
        String fileStorageName = ZPINcStrFileDir.FILE_INDEX_CONTAINS.getStr();
        Path workPath = getWorkDirByRWAccess();
        Path workFilePath = Paths.get(workPath.toString(), fileStorageName);
        workFilePath = workFilePath.normalize().toAbsolutePath();
        return workFilePath;
    }
    private static Path getWorkDirByRWAccess() throws RuntimeException{
        try {
            Path userHomePath = ZPINcFsDefaults.getUserHomePath();
            if( ZPINcFsIdxOperationDirs.existAndHasAccessRWNotLink(userHomePath) ){
                return userHomePath;
            }
        } catch (IOException ex) {
            ZPINcAppHelper.logException(
                    ZPINcFsIdxStorageInit.class.getCanonicalName(), ex);
        }
        try {
            Path appPath = ZPINcFsDefaults.getAppPath();
            if( ZPINcFsIdxOperationDirs.existAndHasAccessRWNotLink(appPath) ){
                return appPath;
            }
        } catch (IOException ex) {
            ZPINcAppHelper.logException(
                    ZPINcFsIdxStorageInit.class.getCanonicalName(), ex);
        }
        String strMsg = "User home and application directories is wrong, "
                + "for application work need read, write access"
                + "in not sybolink links dirictories";
        ZPINcAppHelper.outMessage(
            ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
            + strMsg
        );
        throw new RuntimeException(strMsg);
    }
}
