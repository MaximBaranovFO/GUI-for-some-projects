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
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.ProviderNotFoundException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThWordLogicRead {
    protected void doReadFromIndexWord(final ZPIThWordRule outerRuleWord){
        ZPIThIndexRule indexRule;
        ZPIThIndexStatistic indexStatistic;
        ZPIThWordRule funcRuleWord;
        AppFileStorageIndex currentIndexStorages;
        UUID pollNextUuid;
        URI byPrefixGetUri;
        Map<String, String> byPrefixGetMap;
        ZPIThWordEventLogic eventLogic;
        try {
            AdilRule adilRule = outerRuleWord.getIndexRule().getAdilRule();
            AdilState adilState = adilRule.getAdilState();
            Integer numberProcessIndexSystem = 11;
            String msgToLog = AdilConstants.INFO_LOGIC_POSITION
                    + AdilConstants.CANONICALNAME
                    + ZPIThWordLogicRead.class.getCanonicalName()
                    + AdilConstants.METHOD
                    + "doReadFromIndexWord()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + AdilConstants.START);
            funcRuleWord = (ZPIThWordRule) outerRuleWord;
            
            indexRule = funcRuleWord.getIndexRule();
            //indexStatistic = indexRule.getIndexStatistic();
            //indexStatistic.updateDataStorages();
            currentIndexStorages = funcRuleWord.getIndexRule().getIndexState().currentIndexStorages();
            byPrefixGetUri = currentIndexStorages.byPrefixGetUri(AppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
            byPrefixGetMap = currentIndexStorages.byPrefixGetMap( 
                    AppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
            for( Map.Entry<String, String> itemByPrefixGetMap : byPrefixGetMap.entrySet() ){
                adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + AdilConstants.STATE
                    + AdilConstants.VARNAME
                    + "byPrefixGetUri.toString()"
                    + AdilConstants.VARVAL
                    + byPrefixGetUri.toString()
                    + AdilConstants.VARNAME
                    + "itemByPrefixGetMap.getKey()"
                    + AdilConstants.VARVAL
                    + itemByPrefixGetMap.getKey()
                    + AdilConstants.VARNAME
                    + "itemByPrefixGetMap.getValue()"
                    + AdilConstants.VARVAL
                    + itemByPrefixGetMap.getValue()
                );
            }
            try( FileSystem fsForReadData = FileSystems.newFileSystem(byPrefixGetUri, byPrefixGetMap) ){
                do {
                    pollNextUuid = outerRuleWord.getWordState().getBusEventShort().pollNextUuid(2, 2);
                    if( checkStateForUuidOnDoRead(outerRuleWord, pollNextUuid) ){
                        //move uuid in bus event shot
                        //move uuid in statebuseventlocal
                        //do read data
                        //move uuid into wait read
                        eventLogic = (ZPIThWordEventLogic) outerRuleWord.getWordState().getEventLogic();
                        try {
                            eventLogic.readDataFromStorage(fsForReadData, pollNextUuid);
                        } catch(IllegalStateException exIllState) {
                            System.err.println(exIllState.getMessage());
                            exIllState.printStackTrace();
                        }
                    } else {
                        //not founded in nextStep ready write uuids go into...
                    }
                } while( funcRuleWord.isRunnedWordWorkRouter() );
                //need read all cached data after end for all read jobs
            } catch(FileSystemAlreadyExistsException exAlExist){
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exAlExist.getMessage());
                exAlExist.printStackTrace();
            } catch(FileSystemNotFoundException exFsNotExist){
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exFsNotExist.getMessage());
                exFsNotExist.printStackTrace();
            } catch(ProviderNotFoundException exProvNotFound){
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exProvNotFound.getMessage());
                exProvNotFound.printStackTrace();
            } catch(IllegalArgumentException exIllArg){
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exIllArg.getMessage());
                exIllArg.printStackTrace();
            } catch(SecurityException exSec){
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exSec.getMessage());
                exSec.printStackTrace();
            } catch (IOException exIo) {
                System.err.println(ZPIThWordLogicRead.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exIo.getMessage());
                exIo.printStackTrace();
            }
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + AdilConstants.FINISH);
        } finally {
            pollNextUuid = null;
            indexRule = null;
            indexStatistic = null;
            funcRuleWord = null;
            currentIndexStorages = null;
            byPrefixGetUri = null;
            byPrefixGetMap = null;
            eventLogic = null;
        }
    }
    protected Boolean checkStateForUuidOnDoRead(ZPIThWordRule outerRuleWord, UUID checkedReturnedUuid){
        LinkedTransferQueue<Integer[]> foundedNodes;
        try {
            if( checkedReturnedUuid != null ){
                foundedNodes = outerRuleWord.getWordState().getBusEventShortNextStep().foundUuidInList(checkedReturnedUuid);
                while( !foundedNodes.isEmpty() ) {
                    Integer[] foundUuidInList = foundedNodes.poll();
                    if( foundUuidInList[0] == -1 || foundUuidInList[1] == -1 ){
                        continue;
                    }
                    if( foundUuidInList[0] == 0 || foundUuidInList[1] == 1 ){
                        return Boolean.TRUE;
                    }
                }
            }
            return Boolean.FALSE;
        }
        finally {
            foundedNodes = null;
        }
    }
    

    private static ConcurrentSkipListMap<UUID, ZPITdataWord> doUtilizationDataInitNew(ConcurrentSkipListMap<UUID, ZPITdataWord> prevData){
        utilizeTdataWord(prevData);
        return new ConcurrentSkipListMap<UUID, ZPITdataWord>();
    }
    private static void utilizeTdataWord(ConcurrentSkipListMap<UUID, ZPITdataWord> forUtilizationData){
        UUID keyForDelete;
        ZPITdataWord removedData;
        try {
            for( Map.Entry<UUID, ZPITdataWord> deletingItem : forUtilizationData.entrySet() ){
                keyForDelete = deletingItem.getKey();
                removedData = forUtilizationData.remove(keyForDelete);
                removedData.dirListFile = null;
                removedData.hexSubString = null;
                removedData.hexSubStringHash = null;
                removedData.lengthSubString = null;
                removedData.positionSubString = null;
                removedData.randomUUID = null;
                removedData.recordHash = null;
                removedData.recordTime = null;
                removedData.recordUUID = null;
                removedData.strSubString = null;
                removedData.strSubStringHash = null;
                removedData.typeWord = null;
                removedData = null;
                keyForDelete = null;
            }
            forUtilizationData = null;
        } finally {
            keyForDelete = null;
            removedData = null;
        }
    }
    /**
     * 
     * @param namePrefixFileNameFromFlowInputed
     * @param recordsCountInputed
     * @param volumeNumberInputed
     * @return 
     */
    private static String fileNameBuilder(
            final String namePrefixFileNameFromFlowInputed,
            final Integer recordsCountInputed,
            final Integer volumeNumberInputed){
        String namePrefixFunc;
        Integer recordsCountFunc;
        Integer volumeNumberFunc;
        String buildedFileName;
        try {
            namePrefixFunc = new String(namePrefixFileNameFromFlowInputed);
            recordsCountFunc = (Integer) recordsCountInputed;
            volumeNumberFunc = (Integer) volumeNumberInputed;
            buildedFileName = new String()
                .concat(AppFileNamesConstants.SZFS_WORD_FILE_PREFIX)
                .concat(namePrefixFunc.concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR))
                .concat(String.valueOf(recordsCountFunc))
                .concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR)
                .concat(String.valueOf(volumeNumberFunc));
            return buildedFileName;
        } finally {
            namePrefixFunc = null;
            recordsCountFunc = null;
            volumeNumberFunc = null;
            buildedFileName = null;
        }
    }

}
