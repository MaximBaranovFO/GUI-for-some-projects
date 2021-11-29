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
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.ProviderNotFoundException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThDirListLogicRead {
    protected void doIndexStorage(final ZPIThDirListRule outerRuleDirListReadWork){
        ZPIAdilRule adilRule = outerRuleDirListReadWork.getIndexRule().getZPIAdilRule();
        ZPIAdilState adilState = adilRule.getZPIAdilState();
        String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                + ZPIAdilConstants.CANONICALNAME
                + ZPIThDirListLogicRead.class.getCanonicalName()
                + ZPIAdilConstants.METHOD
                + "doIndexStorage()";
        adilState.putLogLineByProcessNumberMsg(3, 
                msgToLog
                + ZPIAdilConstants.START);
        /**
         * @todo need optimized that part of code, if have a jobForReadList, then
         * index storage exist, check for exist and open it
         * exceptions to logger append records need too
         */
        System.out.println(ZPIThDirListLogicRead.class.getCanonicalName() + " =====>");
        ZPIThDirListBusReaded busJobForSendToIndexWord = outerRuleDirListReadWork.getDirListState().getBusJobForSendToIndexWord();
        ZPIThDirListBusReaded busReadedJob = outerRuleDirListReadWork.getDirListState().getBusJobForRead();
        ZPIThDirListStateJobReader jobForRead = busReadedJob.getJobForRead();
        if( !jobForRead.isBlankObject() ){
 
            ZPIThIndexRule indexRule = outerRuleDirListReadWork.getIndexRule();
            ZPIThIndexState indexState = indexRule.getIndexState();
            ZPIAppFileStorageIndex currentIndexStorages = indexState.currentIndexStorages();
            //currentIndexStorages.updateMapForStorages();
            /**
             * currentIndexStorages.updateMapForStorages();// - for update Storages info
             */
            System.out.println(ZPIThDirListLogicRead.class.getCanonicalName() + " preOpen storage =====>");
            URI byPrefixGetUri = currentIndexStorages.byPrefixGetUri(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
            Map<String, String> byPrefixGetMap = currentIndexStorages.byPrefixGetMap(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
            try( FileSystem fsForReadData = FileSystems.newFileSystem(byPrefixGetUri, byPrefixGetMap) ){    
                System.out.println(ZPIThDirListLogicRead.class.getCanonicalName() + " open storage " + fsForReadData.toString());
                int countJobs = 0;
                while( !busReadedJob.isJobQueueEmpty() ){
                    /**
                     * @todo recode to do while ...
                     * read one job
                     * add readerJob for Word
                     * goto sleep, while job not ready for Done
                     */
                    if( !jobForRead.isBlankObject() ){

                        Path filePath = ZPIThDirListFileSystemHelper.getFilePath(fsForReadData, jobForRead.getReadedPath());
                        ConcurrentSkipListMap<UUID, ZPITdataDirListFsObjAttr> readDataFromFile = ZPIThDirListFileSystemHelper.readDataFromFile(filePath);
                        jobForRead.putReadedData(readDataFromFile);
                        jobForRead.setTrueReaderJobDone();
                        busJobForSendToIndexWord.addReaderJob(jobForRead);
                        /*System.out.println("idx: " 
                                + countJobs 
                                + " file " 
                                + jobForRead.getReadedPath().toString() 
                                + " read records count " 
                                + jobForRead.getReadedDataSize().toString()
                                + " jobDone " + jobForRead.isReaderJobDone().toString()
                        );*/
                        
                        adilState.putLogLineByProcessNumberMsg(3, 
                            msgToLog
                            + ZPIAdilConstants.STATE
                            + ZPIAdilConstants.VARNAME
                            + "countJobs"
                            + ZPIAdilConstants.VARVAL
                            + String.valueOf(countJobs)
                            + ZPIAdilConstants.VARNAME
                            + "jobForRead.getReadedPath()"
                            + ZPIAdilConstants.VARVAL
                            + jobForRead.getReadedPath().toString()
                            + ZPIAdilConstants.VARNAME
                            + "jobForRead.getReadedDataSize()"
                            + ZPIAdilConstants.VARVAL
                            + String.valueOf(jobForRead.getReadedDataSize())
                            + ZPIAdilConstants.VARNAME
                            + "jobForRead.isReaderJobDone().toString()"
                            + ZPIAdilConstants.VARVAL
                            + String.valueOf(jobForRead.isReaderJobDone())
                        );
                        countJobs++;
                    }
                    jobForRead = busReadedJob.getJobForRead();
                }
                adilState.putLogLineByProcessNumberMsg(3, 
                            msgToLog
                            + ZPIAdilConstants.STATE
                            + ZPIAdilConstants.VARNAME
                            + "countJobs"
                            + ZPIAdilConstants.VARVAL
                            + String.valueOf(countJobs));
                
            } catch(FileSystemNotFoundException ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch(ProviderNotFoundException ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch(IllegalArgumentException ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch(SecurityException ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } 
        }
        adilState.putLogLineByProcessNumberMsg(3, 
                msgToLog
                + ZPIAdilConstants.FINISH);
    }
    
}
