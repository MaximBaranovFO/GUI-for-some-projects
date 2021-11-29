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
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Map;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThDirListLogicManager {
    
    
    protected void doIndexStorage(final ZPIThDirListRule ruleDirList){
        ZPIAdilRule adilRule = ruleDirList.getIndexRule().getZPIAdilRule();
        ZPIAdilState adilState = adilRule.getZPIAdilState();
        String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                + ZPIAdilConstants.CANONICALNAME
                + ZPIThDirListLogicManager.class.getCanonicalName()
                + ZPIAdilConstants.METHOD
                + "doIndexStorage()";
        adilState.putLogLineByProcessNumberMsg(2, 
                msgToLog
                + ZPIAdilConstants.START);
        /**
         * log process example
         * adilState.putLogLineByProcessNumberMsg(0, 
         *              msgToLog
         *              + ZPIAdilConstants.STATE
         *              + ZPIAdilConstants.VARNAME
         *              + "storageForJobElement"
         *              + ZPIAdilConstants.VARVAL
         *              + storageForJobElement.toUri().toString());
         */
        ZPIThDirListBusReaded busReadedJob = ruleDirList.getDirListState().getBusJobForRead();
        /**
         * ThIndexStatistic
         */
        ZPIThIndexStatistic indexStatistic = ruleDirList.getIndexRule().getIndexStatistic();
        indexStatistic.updateDataStorages();
        indexStatistic.printToConsoleListContent();
        
        Boolean ifException = Boolean.FALSE;
        
        ZPIThIndexRule indexRule = ruleDirList.getIndexRule();
        ZPIThIndexState indexState = indexRule.getIndexState();
        ZPIAppFileStorageIndex currentIndexStorages = indexState.currentIndexStorages();
        /**
         * currentIndexStorages.updateMapForStorages();// - for update Storages info
         */
        URI byPrefixGetUri = currentIndexStorages.byPrefixGetUri(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
        Map<String, String> byPrefixGetMap = currentIndexStorages.byPrefixGetMap(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
        try( FileSystem fsForReadData = FileSystems.newFileSystem(byPrefixGetUri, byPrefixGetMap) ){
            //NcParamFs dataStorage = NcFsIdxStorageInit.initStorageStructure(fsZipIndexStorage);
   
            //Insert thread code for do in Zip here
            
            System.out.println("Storage is " + fsForReadData.toString());
            
            Path lookPath = fsForReadData.getPath(ZPIAppFileNamesConstants.DIR_IDX_ROOT);
            int count = 0;
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(lookPath,"{" + ZPIAppFileNamesConstants.SZFS_DIR_LIST_FILE_PREFIX + "}*")) {
                for (Path storageForJobElement : stream) {
                    pathIsNotReadWriteLink(storageForJobElement);
                    pathIsNotFile(storageForJobElement);
                    ZPIThDirListStateJobReader thDirListStateJobReader = new ZPIThDirListStateJobReader(storageForJobElement, byPrefixGetUri);
                    busReadedJob.addReaderJob(thDirListStateJobReader);
                    //String replacedPath = entry.toString().replace(FILE_EXTENTION, FILE_FULL_EXTENTION);
                    //Path lockedFilePath = Paths.get(replacedPath);
                    //if( Files.notExists(lockedFilePath) ){
                        //return entry;
                    //}
                    //System.out.println("Directory is " + entry.toString());
                    adilState.putLogLineByProcessNumberMsg(2, 
                        msgToLog
                        + ZPIAdilConstants.STATE
                        + ZPIAdilConstants.VARNAME
                        + "count"
                        + ZPIAdilConstants.VARVAL
                        + String.valueOf(count)
                        + ZPIAdilConstants.VARNAME
                        + "storageForJobElement"
                        + ZPIAdilConstants.VARVAL
                        + storageForJobElement.toUri().toString());
                    //ThWordLogicFilter.processFilterInputedString(entry.toString());
                    count++;
                    
                    //Read into readedFromDirListDataBus
                    
                }
                //System.out.println("Count of files " + count);
                adilState.putLogLineByProcessNumberMsg(2, 
                        msgToLog
                        + ZPIAdilConstants.STATE
                        + ZPIAdilConstants.VARNAME
                        + "count"
                        + ZPIAdilConstants.VARVAL
                        + String.valueOf(count));
            if( count == 0 ){
                System.out.println("Directory is Empty " + lookPath.toString());
            }
            } catch (IOException | DirectoryIteratorException e) {
                e.printStackTrace();
                System.out.println("[ERROR] Can`t read count files in work directory " + lookPath.toString());
            }
        //Path returnFileName = getDictonariesUnfilteredDirDeclineNewFile();
            
            
            //Thread.start();
            //Thread.run();
        
        } catch (IOException ex) {
            ex.printStackTrace();
            ifException = Boolean.TRUE;
        } catch (Exception ex){
            ex.printStackTrace();
            ifException = Boolean.TRUE;
        }
        adilState.putLogLineByProcessNumberMsg(2, 
                msgToLog
                + ZPIAdilConstants.FINISH);
        adilRule.runAdilWorkWrite();
    }
    private static void pathIsNotReadWriteLink(Path innerWorkPath) throws IOException{
        if ( !Files.isReadable(innerWorkPath) ){
            System.out.println("[ERROR] File or Directory exist and it is not a Readable: " + innerWorkPath.toString());
            throw new IOException("[ERROR] File or Directory exist and it is not a Readable: " + innerWorkPath.toString());
        }
        if ( !Files.isWritable(innerWorkPath) ){
            System.out.println("[ERROR] File or Directory exist and it is not a Writable: " + innerWorkPath.toString());
            throw new IOException("[ERROR] File or Directory exist and it is not a Writable: " + innerWorkPath.toString());
        }
        if ( Files.isSymbolicLink(innerWorkPath) ){
            System.out.println("[ERROR] File or Directory exist and it is not a SymbolicLink: " + innerWorkPath.toString());
            throw new IOException("[ERROR] File or Directory exist and it is a SymbolicLink: " + innerWorkPath.toString());
        }
    }
    private static void pathIsNotFile(Path innerWorkPath) throws IOException{
        if ( !Files.exists(innerWorkPath, LinkOption.NOFOLLOW_LINKS) ){
            System.out.println("[ERROR] File or Directory not exist: " + innerWorkPath.toString());
            throw new IOException("[ERROR] File or Directory not exist: " + innerWorkPath.toString());
        }
        if ( Files.isDirectory(innerWorkPath, LinkOption.NOFOLLOW_LINKS) ){
            System.out.println("[ERROR] Directory exist and it is not a File: " + innerWorkPath.toString());
            throw new IOException("[ERROR] Directory exist and it is not a File: " + innerWorkPath.toString());
        }
    }
}
