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
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.ProviderNotFoundException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThStorageWordLogicRead {
    protected void doReadFromIndexStorageWord(ZPIThStorageWordRule outerRuleStorageWord){
        ZPIThIndexRule indexRule;
        ZPIThIndexStatistic indexStatistic;
        ZPIThStorageWordRule funcRuleStorageWord;
        AppFileStorageIndex currentIndexStorages;
        try{
            AdilRule adilRule = outerRuleStorageWord.getIndexRule().getAdilRule();
            AdilState adilState = adilRule.getAdilState();
            Integer numberProcessIndexSystem = 8;
            String msgToLog = AdilConstants.INFO_LOGIC_POSITION
                    + AdilConstants.CANONICALNAME
                    + ZPIThStorageWordLogicRead.class.getCanonicalName()
                    + AdilConstants.METHOD
                    + "doReadFromIndexStorageWord()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + AdilConstants.START);
            
            long counIterations = 0;
            /**
             * @todo
             * Rule
             * Statistic for this index system
             */
            funcRuleStorageWord = (ZPIThStorageWordRule) outerRuleStorageWord;
            ZPIThStorageWordBusReadedFlow storageWordFlowReaded = funcRuleStorageWord.getStorageWordState().getStorageWordFlowReaded();
            indexRule = funcRuleStorageWord.getIndexRule();
            //indexStatistic = indexRule.getIndexStatistic();
            //indexStatistic.updateDataStorages();
            
            currentIndexStorages = funcRuleStorageWord.getIndexRule().getIndexState().currentIndexStorages();
            URI byPrefixGetUri = currentIndexStorages.byPrefixGetUri(AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD);
            Map<String, String> byPrefixGetMap;
            byPrefixGetMap = currentIndexStorages.byPrefixGetMap( 
                    AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD);
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
        
        
            do{ 
                try{
                    ZPIThStorageWordState wordState = (ZPIThStorageWordState) funcRuleStorageWord.getStorageWordState();
                    ZPIThStorageWordBusReader busJobForStorageWordRouterJobToReader = 
                            (ZPIThStorageWordBusReader) wordState.getBusJobForStorageWordRouterJobToReader();
                    
                    ConcurrentHashMap<Integer, ConcurrentHashMap<UUID, ConcurrentHashMap<String, String>>> 
                            maxUsedBusesSet = busJobForStorageWordRouterJobToReader.getMaxUsedBusesSet();
                    
                    for(Map.Entry<Integer, ConcurrentHashMap<UUID, ConcurrentHashMap<String, String>>> busVal : maxUsedBusesSet.entrySet()){
                        for(Map.Entry<UUID, ConcurrentHashMap<String, String>> forReaderJobUUID : busVal.getValue().entrySet()){
                            /**
                             * @todo get PointFlow for UUID
                             * call write func
                             * get keySet this level list, remove it from list, insert
                             * into function for write removed data 
                             */
                            UUID mainFlowLabel = forReaderJobUUID.getKey();

                            for(Map.Entry<String, String> itemsTagNames : forReaderJobUUID.getValue().entrySet()){
                                /**
                                 * @todo to do function for return removed data from cache
                                 */
                                Integer typeWordBusNumber = busVal.getKey();
                                String hexTagName = itemsTagNames.getKey();
                                String subStringValue = itemsTagNames.getValue();

                                ZPIThStorageWordStatusMainFlow storageWordStatistic = (ZPIThStorageWordStatusMainFlow) outerRuleStorageWord.getStorageWordStatusMainFlow();

                                ConcurrentHashMap<UUID, ConcurrentHashMap<Integer, UUID>> typeWordTagFileNameFlowUuids = 
                                    (ConcurrentHashMap<UUID, ConcurrentHashMap<Integer, UUID>>) storageWordStatistic.getTypeWordTagFileNameFlowUuids(
                                    typeWordBusNumber, 
                                    hexTagName, 
                                    subStringValue);
                                
                                ConcurrentHashMap<Integer, UUID> flowPointsUUID = 
                                        (ConcurrentHashMap<Integer, UUID>) typeWordTagFileNameFlowUuids.get(mainFlowLabel);
                                int countKeysByNamesExist = 0;
                                if( flowPointsUUID != null ) {
                                    if( !flowPointsUUID.isEmpty() ){
                                        if( flowPointsUUID.size() == 5 ){
                                            if( !flowPointsUUID.containsKey("ThStorageWordStatusDataFs".hashCode()) ){
                                                ConcurrentHashMap<Integer, UUID> removeBadDataFsUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadDataFsUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusDataFs for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            if( !flowPointsUUID.containsKey("ThStorageWordStatusName".hashCode()) ){
                                                ConcurrentHashMap<Integer, UUID> removeBadNameUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadNameUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusName for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            if( !flowPointsUUID.containsKey("ThStorageWordStatusActivity".hashCode()) ){
                                                ConcurrentHashMap<Integer, UUID> removeBadActivityUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadActivityUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusActivity for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            if( !flowPointsUUID.containsKey("ThStorageWordStatusDataCache".hashCode()) ){
                                                ConcurrentHashMap<Integer, UUID> removeBadDataCacheUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadDataCacheUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusDataCache for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            if( !flowPointsUUID.containsKey("ThStorageWordStatusWorkers".hashCode()) ){
                                                ConcurrentHashMap<Integer, UUID> removeBadWorkersUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadWorkersUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusWorkers for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            /**
                                             * validate values, read from file
                                             */
                                            ZPIThStorageWordCache storageWordCache = 
                                                    (ZPIThStorageWordCache) storageWordStatistic.getStorageWordCache();
                                            ZPIThStorageWordStatusActivity storageWordStatusActivity = 
                                                    (ZPIThStorageWordStatusActivity) storageWordStatistic.getStorageWordStatusActivity();

                                            UUID getKeyActivity = (UUID) flowPointsUUID.get("ThStorageWordStatusActivity".hashCode());
                                            try{
                                                storageWordStatusActivity.validateCountParams(getKeyActivity);
                                            } catch (IllegalArgumentException exActiv) {
                                                System.err.println(exActiv.getMessage());
                                                ConcurrentHashMap<Integer, UUID> removeBadActivityUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadActivityUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusActivity for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }
                                            ZPIThStorageWordStatusDataCache storageWordStatusDataCache = 
                                                    (ZPIThStorageWordStatusDataCache) storageWordStatistic.getStorageWordStatusDataCache();

                                            UUID getKeyDataCache = (UUID) flowPointsUUID.get("ThStorageWordStatusDataCache".hashCode());
                                            try{
                                                storageWordStatusDataCache.validateCountParams(getKeyDataCache);
                                            } catch (IllegalArgumentException exDataCache) {
                                                System.err.println(exDataCache.getMessage());
                                                ConcurrentHashMap<Integer, UUID> removeBadDataCacheUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadDataCacheUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusDataCache for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }

                                            ZPIThStorageWordStatusDataFs storageWordStatusDataFs = 
                                                    (ZPIThStorageWordStatusDataFs) storageWordStatistic.getStorageWordStatusDataFs();

                                            UUID getKeyDataFs = (UUID) flowPointsUUID.get("ThStorageWordStatusDataFs".hashCode());
                                            try{
                                                storageWordStatusDataFs.validateCountParams(getKeyDataFs);
                                            } catch (IllegalArgumentException exDataFs) {
                                                System.err.println(exDataFs.getMessage());
                                                ConcurrentHashMap<Integer, UUID> removeBadDataFsUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadDataFsUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusDataFs for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }


                                            ZPIThStorageWordStatusName storageWordStatusName = 
                                                    (ZPIThStorageWordStatusName) storageWordStatistic.getStorageWordStatusName();

                                            UUID getKeyName = (UUID) flowPointsUUID.get("ThStorageWordStatusName".hashCode());
                                            try{
                                                storageWordStatusName.validateCountParams(getKeyName);
                                            } catch (IllegalArgumentException exName) {
                                                System.err.println(exName.getMessage());
                                                ConcurrentHashMap<Integer, UUID> removeBadNameUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadNameUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusName for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }


                                            ZPIThStorageWordStatusWorkers storageWordStatusWorkers = 
                                                    (ZPIThStorageWordStatusWorkers) storageWordStatistic.getStorageWordStatusWorkers();

                                            UUID getKeyWorkers = (UUID) flowPointsUUID.get("ThStorageWordStatusWorkers".hashCode());
                                            try{
                                                storageWordStatusWorkers.validateCountParams(getKeyWorkers);
                                            } catch (IllegalArgumentException exWorkers) {
                                                System.err.println(exWorkers.getMessage());
                                                ConcurrentHashMap<Integer, UUID> removeBadWorkersUUID = typeWordTagFileNameFlowUuids.remove(mainFlowLabel);
                                                removeBadWorkersUUID = null;
                                                System.err.println("-----------------"
                                                        + "||||||||||||||||||"
                                                        + "-----------------"
                                                        + "||||||||||||||||||"
                                                        + "----------------- data in flow key not valid, removed, "
                                                        + "reason not set ThStorageWordStatusWorkers for UUID "
                                                        + mainFlowLabel.toString());
                                                continue;
                                            }

                                            ConcurrentHashMap<Integer, String> statusNameForKeyPointFlow = 
                                                    storageWordStatusName.getStatusNameForKeyPointFlow(getKeyName);
                                            ConcurrentHashMap<Integer, Integer> statusDataCacheForKeyPointFlow = 
                                                    storageWordStatusDataCache.getStatusDataCacheForKeyPointFlow(getKeyDataCache);
                                            ConcurrentHashMap<Integer, Boolean> statusWorkersForKeyPointFlow = 
                                                    storageWordStatusWorkers.getStatusWorkersForKeyPointFlow(getKeyWorkers);

                                            //isWriteProcess - 1640531930

                                            //Boolean getIsWriteInProcess = statusWorkersForKeyPointFlow.get(1640531930);
                                            //if( getIsWriteInProcess ){
                                            //    continue;
                                            //}

                                            //storageDirectoryName - 1962941405
                                            String storageDirectoryName = (String) statusNameForKeyPointFlow.get(1962941405);
                                            //currentFileName - 1517772480
                                            String currentFileName = (String) statusNameForKeyPointFlow.get(1517772480);
                                            //newFileName - 521024487
                                            String newFileName = statusNameForKeyPointFlow.get(521024487);

                                            if( !currentFileName.equalsIgnoreCase(newFileName) ){
                                                continue;
                                            }

                                            Path forReadFileName = fsForReadData.getPath(storageDirectoryName, currentFileName);

                                            ConcurrentHashMap<String, String> readedFormData = 
                                                    new ConcurrentHashMap<String, String>();

                                            if( Files.exists(forReadFileName) ){
                                                try(ObjectInputStream ois =
                                                    new ObjectInputStream(Files.newInputStream(forReadFileName)))
                                                {
                                                    readedFormData.putAll((ConcurrentHashMap<String, String>) ois.readObject());

                                                    ZPIThStorageWordCacheReaded thStorageWordCacheReaded = storageWordStatistic.getStorageWordCacheReaded();

                                                    Boolean isCachedReadedData = Boolean.FALSE;

                                                    isCachedReadedData = thStorageWordCacheReaded.addAllDataIntoCacheReaded(
                                                            typeWordBusNumber, 
                                                            hexTagName, 
                                                            subStringValue, 
                                                            readedFormData);
                                                    //isCachedReadedData - -660426229
                                                    statusWorkersForKeyPointFlow.put(-660426229, isCachedReadedData);
                                                    //currentInCacheReaded - -835384455
                                                    statusDataCacheForKeyPointFlow.put(-835384455, readedFormData.size());
                                                    storageWordFlowReaded.addToListOfReadedFlowUuids(typeWordBusNumber, 
                                                            hexTagName, 
                                                            subStringValue, 
                                                            mainFlowLabel);
                                                    adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                                                    msgToLog
                                                        + AdilConstants.STATE
                                                        + AdilConstants.VARNAME
                                                        + "forReadFileName.toUri().toString()"
                                                        + AdilConstants.VARVAL
                                                        + forReadFileName.toUri().toString()
                                                        + AdilConstants.VARNAME
                                                        + "readedFormData.size()"
                                                        + AdilConstants.VARVAL
                                                        + String.valueOf(readedFormData.size())
                                                    );
                                                    ConcurrentHashMap<String, String> remove = busVal.getValue().remove(mainFlowLabel);
                                                    remove = null;
                                                } catch(ClassNotFoundException exCnf){
                                                    System.err.println(exCnf.getMessage());
                                                    exCnf.printStackTrace();
                                                } catch(InvalidClassException exIce){
                                                    System.err.println(exIce.getMessage());
                                                    exIce.printStackTrace();
                                                } catch(StreamCorruptedException exSce){
                                                    System.err.println(exSce.getMessage());
                                                    exSce.printStackTrace();
                                                } catch(OptionalDataException exOde){
                                                    System.err.println(exOde.getMessage());
                                                    exOde.printStackTrace();
                                                } catch(IOException exIo){
                                                    System.err.println(exIo.getMessage());
                                                    exIo.printStackTrace();
                                                }
                                            }
                                        }
                                    }

                                }
                                try{
                                    /*checkDataForWrite(funcRuleStorageWord, typeWordBusNumber, hexTagName, subStringValue);*/
                                } catch(IllegalArgumentException exArg) {
                                    System.err.println(exArg.getMessage());
                                    exArg.printStackTrace();
                                }
                            }
                        }
                }
                    /**
                     * for this typeWord, hexTagName, subString get from flowPoint need to read file name
                     * read it and insert data into cacheReaded, set flow point flag as readed, in write
                     * read flag readed and rewrite existing file, or write in new file name after that
                     * delete existing file
                     */
                    
                    /*ConcurrentSkipListMap<UUID, TdataWord> readedFormData =
                    new ConcurrentSkipListMap<UUID, TdataWord>();
                    if( Files.exists(nowWritedFile) ){
                    try(ObjectInputStream ois =
                    new ObjectInputStream(Files.newInputStream(nowWritedFile)))
                    {
                    readedFormData.putAll((ConcurrentSkipListMap<UUID, TdataWord>) ois.readObject());
                    } catch(Exception ex){
                    ex.printStackTrace();
                    }
                    try{
                    Path mvOldDir = fsForReadData.getPath(AppFileNamesConstants.DIR_INDEX_OLD_DATA);
                    if( Files.notExists(mvOldDir) ){
                    Files.createDirectories(mvOldDir);
                    }
                    Path forNewMove = fsForReadData.getPath(AppFileNamesConstants.DIR_INDEX_OLD_DATA
                    ,writerPath + "-"
                    + AppFileOperationsSimple.getNowTimeStringWithMS()
                    + "-"
                    + String.valueOf(counIterations));
                    Files.move(nowWritedFile, forNewMove);
                    } catch(UnsupportedOperationException ex){
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                    } catch(FileAlreadyExistsException ex){
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                    } catch(DirectoryNotEmptyException ex){
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                    } catch(AtomicMoveNotSupportedException ex){
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                    } catch(IOException ex){
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                    }
                    }
                    ConcurrentSkipListMap<UUID, TdataWord> readyForWriteData =
                    new ConcurrentSkipListMap<UUID, TdataWord>();
                    readyForWriteData.putAll(readedFormData);
                    readyForWriteData.putAll(writerData);*/
        } finally {
                    
                }
            } while( outerRuleStorageWord.isRunnedStorageWordWorkRouter() );
        } catch(FileSystemAlreadyExistsException exAlExist){
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exAlExist.getMessage());
            exAlExist.printStackTrace();
        } catch(FileSystemNotFoundException exFsNotExist){
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exFsNotExist.getMessage());
            exFsNotExist.printStackTrace();
        } catch(ProviderNotFoundException exProvNotFound){
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exProvNotFound.getMessage());
            exProvNotFound.printStackTrace();
        } catch(IllegalArgumentException exIllArg){
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exIllArg.getMessage());
            exIllArg.printStackTrace();
        } catch(SecurityException exSec){
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exSec.getMessage());
            exSec.printStackTrace();
        } catch (IOException exIo) {
            System.err.println(ZPIThStorageWordLogicRead.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exIo.getMessage());
            exIo.printStackTrace();
        }
        adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + AdilConstants.FINISH);
        
        } finally {
            
        }

    }
}
