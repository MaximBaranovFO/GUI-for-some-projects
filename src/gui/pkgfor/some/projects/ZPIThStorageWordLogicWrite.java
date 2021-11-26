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
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThStorageWordLogicWrite {
    protected void doWriteToIndexStorageWord(final ZPIThStorageWordRule outerRuleStorageWord){
        ZPIThIndexRule indexRule;
        ZPIThIndexStatistic indexStatistic;
        ZPIThStorageWordRule funcRuleStorageWord;
        AppFileStorageIndex currentIndexStorages;
        try{
            AdilRule adilRule = outerRuleStorageWord.getIndexRule().getAdilRule();
            AdilState adilState = adilRule.getAdilState();
            Integer numberProcessIndexSystem = 9;
            String msgToLog = AdilConstants.INFO_LOGIC_POSITION
                    + AdilConstants.CANONICALNAME
                    + ZPIThStorageWordLogicWrite.class.getCanonicalName()
                    + AdilConstants.METHOD
                    + "doWriteToIndexStorageWord()";
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
            
            indexRule = funcRuleStorageWord.getIndexRule();
            //indexStatistic = indexRule.getIndexStatistic();
            //indexStatistic.updateDataStorages();
            currentIndexStorages = funcRuleStorageWord.getIndexRule().getIndexState().currentIndexStorages();
            URI byPrefixGetUri = currentIndexStorages.byPrefixGetUri(AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD);
            Map<String, String> byPrefixGetMap = currentIndexStorages.byPrefixGetMap(
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
            
            try( FileSystem fsForWriteData = FileSystems.newFileSystem(byPrefixGetUri, byPrefixGetMap) ){
        
            System.out.println("   ---   ---   ---   ---   ---   ---   ---   ---   ---   " 
                    + ZPIThStorageWordLogicWrite.class.getCanonicalName() + " open storage " + fsForWriteData.getPath("/").toUri().toString());
            do { 
                try {

                /**
                 * @todo release some bus for each index system, 
                 * got jobs and read write it in by queue system, workers whrere
                 * opened storages
                 * 
                 * save data in limited file packets
                 */
                ZPIThStorageWordState wordState = (ZPIThStorageWordState) funcRuleStorageWord.getStorageWordState();
                ZPIThStorageWordBusWriter busJobForWrite = wordState.getBusJobForStorageWordRouterJobToWriter();


                ConcurrentHashMap<Integer, ConcurrentHashMap<UUID, ConcurrentHashMap<String, String>>> maxUsedBusesSet = busJobForWrite.getMaxUsedBusesSet();

                for( Map.Entry<Integer, ConcurrentHashMap<UUID, ConcurrentHashMap<String, String>>> busVal : maxUsedBusesSet.entrySet() ){
                    for( Map.Entry<UUID, ConcurrentHashMap<String, String>> forWriterJobUUID : busVal.getValue().entrySet() ){
                        /**
                         * @todo get PointFlow for UUID
                         * call write func
                         * get keySet this level list, remove it from list, insert
                         * into function for write removed data 
                         */
                        UUID mainFlowLabel = forWriterJobUUID.getKey();

                        for( Map.Entry<String, String> itemsTagNames : forWriterJobUUID.getValue().entrySet() ){
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
                                         * validate values, write to file
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
                                        
                                        ConcurrentHashMap<Integer, String> statusNameForKeyPointFlow = storageWordStatusName.getStatusNameForKeyPointFlow(getKeyName);
                                        ConcurrentHashMap<Integer, Boolean> statusWorkersForKeyPointFlow = storageWordStatusWorkers.getStatusWorkersForKeyPointFlow(getKeyWorkers);
                                        //isWriteProcess - 1640531930
                                        Boolean getIsWriteInProcess = statusWorkersForKeyPointFlow.get(1640531930);
                                        if( getIsWriteInProcess ){
                                            continue;
                                        }
                                        
                                        
                                        
                                        //storageDirectoryName - 1962941405
                                        String storageDirectoryName = statusNameForKeyPointFlow.get(1962941405);
                                        
                                        Path storageTypeWordWritedFile = fsForWriteData.getPath(storageDirectoryName);
                                        if( Files.notExists(storageTypeWordWritedFile, LinkOption.NOFOLLOW_LINKS) ){
                                            try{
                                                Files.createDirectories(storageTypeWordWritedFile);
                                            } catch (FileAlreadyExistsException exAlreadyExist) {
                                                exAlreadyExist.printStackTrace();
                                            } catch (SecurityException exSecurity) {
                                                exSecurity.printStackTrace();
                                            } catch (UnsupportedOperationException exUnSupp) {
                                                exUnSupp.printStackTrace();
                                            }
                                            
                                        }
                                        
                                        //set into flow
                                        ConcurrentHashMap<Integer, Integer> statusDataFsForKeyPointFlow = storageWordStatusDataFs.getStatusDataFsForKeyPointFlow(getKeyDataFs);
                                        Integer volNum = statusDataFsForKeyPointFlow.get(-1832815869);
                                        Integer sizeDataSrc = 0;
                                        Integer sizeDataDest = 0;
                                        
                                        
                                        //flowFileNamePrefix - -980152217
                                        String prefixFileName = statusNameForKeyPointFlow.get(-980152217);
                                        
                                        //currentFileName - 1517772480
                                        //String currentFileName = statusNameForKeyPointFlow.get(1517772480);
                                        String currentFileName = new String()
                                            .concat(AppFileNamesConstants.SZFS_STORAGE_WORD_FILE_PREFIX)
                                            .concat(prefixFileName.concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR))
                                            .concat(String.valueOf(sizeDataSrc))
                                            .concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR)
                                            .concat(String.valueOf(volNum));
                                        //newFileName - 521024487
                                        //String newFileName = statusNameForKeyPointFlow.get(521024487);
                                        
                                        
                                        
                                        
                                        
                                        ConcurrentHashMap<String, String> pollTypeWordTagFileNameData = null;
                                        try{
                                            pollTypeWordTagFileNameData = storageWordCache.pollTypeWordTagFileNameData(typeWordBusNumber, hexTagName, subStringValue);
                                        } catch (NullPointerException exRetNull) {
                                            //@todo why null
                                            //System.err.println(exRetNull.getMessage());
                                            //exRetNull.printStackTrace();
                                            continue;
                                        }
                                        /**
                                         * @todo function for removed return cache data for write
                                         */
                                        if( pollTypeWordTagFileNameData == null ){
                                            System.err.println("poll data from cache is null");
                                            continue;
                                        }
                                        Boolean isDataToVol = Boolean.FALSE;
                                        if( pollTypeWordTagFileNameData.size() > AppConstants.STORAGE_WORD_RECORDS_COUNT_LIMIT ){
                                            isDataToVol = Boolean.TRUE;
                                        }
                                        sizeDataDest = pollTypeWordTagFileNameData.size();
                                        String newFileName = new String()
                                            .concat(AppFileNamesConstants.SZFS_STORAGE_WORD_FILE_PREFIX)
                                            .concat(prefixFileName.concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR))
                                            .concat(String.valueOf(sizeDataDest))
                                            .concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR)
                                            .concat(String.valueOf(volNum));
                                        /**
                                         * if size > limit pack data, write, and write new vol
                                         * with size - limit data
                                         * 
                                         * in statistic for remove main uuid need delete for all uuids...
                                         */
                                        
                                        /**
                                         * need additional flags 
                                         * isErrorOnWrite, 
                                         * isErrorOnMove, 
                                         * isNullOnDataInCache,
                                         * isErrorOnDataInCache,
                                         */
                                        if( !isDataToVol ){
                                            Path nowWritedFile = fsForWriteData.getPath(storageDirectoryName, currentFileName);
                                            
                                            try( ObjectOutputStream oos = 
                                                new ObjectOutputStream(Files.newOutputStream(nowWritedFile)))
                                            {
                                                /**
                                                 * java.lang.OutOfMemoryError: GC overhead limit exceeded
                                                 * ThStorageWordLogicWrite.java:384
                                                 */
                                                oos.writeObject(pollTypeWordTagFileNameData);
                                                oos.flush();
                                                /*System.out.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                                                        + " => => =>                                             => => => " 
                                                        + nowWritedFile.toUri().toString() 
                                                        + " writed size " + pollTypeWordTagFileNameData.size());*/
                                                adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                                                    msgToLog
                                                    + AdilConstants.STATE
                                                    + AdilConstants.VARNAME
                                                    + "nowWritedFile.toUri().toString()"
                                                    + AdilConstants.VARVAL
                                                    + nowWritedFile.toUri().toString()
                                                    + AdilConstants.VARNAME
                                                    + "pollTypeWordTagFileNameData.size()"
                                                    + AdilConstants.VARVAL
                                                    + pollTypeWordTagFileNameData.size()
                                                );
                                                statusWorkersForKeyPointFlow.put(1640531930, Boolean.TRUE);
                                                
                                                //more catch
                                            } catch(Exception ex){
                                                ex.printStackTrace();
                                            }
                                            //isMoveFileReady - -1884096596
                                            Boolean getIsMoveReady = statusWorkersForKeyPointFlow.get(-1884096596);
                                            if( getIsMoveReady ){
                                                continue;
                                            }

                                            Path moveToFile = fsForWriteData.getPath(storageDirectoryName, newFileName);
                                            //efd - Existing For Delete after move new
                                            String fileForTmpMove = newFileName + "-efd";
                                            Path moveEfdFile = fsForWriteData.getPath(storageDirectoryName, "-efd");
                                            Boolean isFileForMoveExist = Boolean.FALSE;
                                            try {
                                                isFileForMoveExist = Files.exists(moveToFile, LinkOption.NOFOLLOW_LINKS);
                                            } catch(SecurityException exSecure) {
                                                System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName()
                                                    + " security error " + exSecure.getMessage());
                                            }
                                            if( isFileForMoveExist ){
                                                moveEfdFile = fsForWriteData.getPath(storageDirectoryName, fileForTmpMove);
                                            }
                                            try{
                                                if( isFileForMoveExist ){
                                                    Files.move(moveToFile, moveEfdFile, StandardCopyOption.ATOMIC_MOVE);
                                                }
                                                Files.move(nowWritedFile, moveToFile, StandardCopyOption.ATOMIC_MOVE);
                                                statusWorkersForKeyPointFlow.put(-1884096596, Boolean.TRUE);
                                                statusWorkersForKeyPointFlow.put(-83825824, Boolean.TRUE);
                                                statusNameForKeyPointFlow.put(521024487, newFileName);
                                                statusNameForKeyPointFlow.put(1517772480, newFileName);


                                            } catch(SecurityException exSecurity) {
                                                System.err.println(exSecurity.getMessage());
                                                exSecurity.printStackTrace();
                                            } catch(AtomicMoveNotSupportedException exAtomic) {
                                                System.err.println(exAtomic.getMessage());
                                                exAtomic.printStackTrace();
                                            } catch(FileAlreadyExistsException exAlreadyExists) {
                                                System.err.println(exAlreadyExists.getMessage());
                                                exAlreadyExists.printStackTrace();
                                            } catch(UnsupportedOperationException exUnsupported) {
                                                System.err.println(exUnsupported.getMessage());
                                                exUnsupported.printStackTrace();
                                            }
                                            if( isFileForMoveExist ){
                                                try{
                                                    Files.deleteIfExists(moveEfdFile);
                                                } catch (DirectoryNotEmptyException exNotEmptyDir) {
                                                    exNotEmptyDir.printStackTrace();
                                                } catch (SecurityException exSecurity) {
                                                    exSecurity.printStackTrace();
                                                } catch (IOException exInOut) {
                                                    exInOut.printStackTrace();
                                                }
                                            }
                                        } else {
                                            do{
                                                ConcurrentHashMap<String, String> packetForWriteData = new ConcurrentHashMap<String, String>();
                                                for( Map.Entry<String, String> valForVolItem : pollTypeWordTagFileNameData.entrySet() ){
                                                    
                                                    String keyItem = (String) valForVolItem.getKey();
                                                    if( keyItem != null ){
                                                        String valItem = (String) pollTypeWordTagFileNameData.remove(keyItem);

                                                        packetForWriteData.put(keyItem, valItem);
                                                    
                                                        sizeDataDest = packetForWriteData.size();
                                                        if( ( sizeDataDest == AppConstants.STORAGE_WORD_RECORDS_COUNT_LIMIT ) || ( pollTypeWordTagFileNameData.isEmpty() ) ){
                                                            currentFileName = new String()
                                                                .concat(AppFileNamesConstants.SZFS_STORAGE_WORD_FILE_PREFIX)
                                                                .concat(prefixFileName.concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR))
                                                                .concat(String.valueOf(0))
                                                                .concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR)
                                                                .concat(String.valueOf(volNum));
                                                            //newFileName - 521024487
                                                            //String newFileName = statusNameForKeyPointFlow.get(521024487);
                                                            newFileName = new String()
                                                                .concat(AppFileNamesConstants.SZFS_STORAGE_WORD_FILE_PREFIX)
                                                                .concat(prefixFileName.concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR))
                                                                .concat(String.valueOf(sizeDataDest))
                                                                .concat(AppFileNamesConstants.FILE_DIR_PART_SEPARATOR)
                                                                .concat(String.valueOf(volNum));

                                                            Path nowWritedFile = fsForWriteData.getPath(storageDirectoryName, currentFileName);

                                                            try( ObjectOutputStream oos = 
                                                                new ObjectOutputStream(Files.newOutputStream(nowWritedFile)) )
                                                            {
                                                                oos.writeObject(packetForWriteData);
                                                                System.out.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                                                                        + " => => =>                                             => => => " 
                                                                        + nowWritedFile.toUri().toString() 
                                                                        + " writed size " + pollTypeWordTagFileNameData.size());
                                                                statusWorkersForKeyPointFlow.put(1640531930, Boolean.TRUE);
                                                            } catch(Exception ex){
                                                                ex.printStackTrace();
                                                            }
                                                            //isMoveFileReady - -1884096596
                                                            Boolean getIsMoveReady = statusWorkersForKeyPointFlow.get(-1884096596);
                                                            if( getIsMoveReady ){
                                                                continue;
                                                            }

                                                            Path moveToFile = fsForWriteData.getPath(storageDirectoryName, newFileName);
                                                            //efd - Existing For Delete after move new
                                                            String fileForTmpMove = newFileName + "-efd";
                                                            Path moveEfdFile = fsForWriteData.getPath(storageDirectoryName, "-efd");
                                                            Boolean isFileForMoveExist = Boolean.FALSE;
                                                            try {
                                                                isFileForMoveExist = Files.exists(moveToFile, LinkOption.NOFOLLOW_LINKS);
                                                            } catch(SecurityException exSecure) {
                                                                System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName()
                                                                    + " security error " + exSecure.getMessage());
                                                            }
                                                            if( isFileForMoveExist ){
                                                                moveEfdFile = fsForWriteData.getPath(storageDirectoryName, fileForTmpMove);
                                                            }
                                                            try{
                                                                if( isFileForMoveExist ){
                                                                    Files.move(moveToFile, moveEfdFile, StandardCopyOption.ATOMIC_MOVE);
                                                                }
                                                                Files.move(nowWritedFile, moveToFile, StandardCopyOption.ATOMIC_MOVE);
                                                                statusWorkersForKeyPointFlow.put(-1884096596, Boolean.TRUE);
                                                                statusWorkersForKeyPointFlow.put(-83825824, Boolean.TRUE);
                                                                statusNameForKeyPointFlow.put(521024487, newFileName);
                                                                statusNameForKeyPointFlow.put(1517772480, newFileName);
                                                                
                                                                
                                                            } catch(SecurityException exSecurity) {
                                                                System.err.println(exSecurity.getMessage());
                                                                exSecurity.printStackTrace();
                                                            } catch(AtomicMoveNotSupportedException exAtomic) {
                                                                System.err.println(exAtomic.getMessage());
                                                                exAtomic.printStackTrace();
                                                            } catch(FileAlreadyExistsException exAlreadyExists) {
                                                                System.err.println(exAlreadyExists.getMessage());
                                                                exAlreadyExists.printStackTrace();
                                                            } catch(UnsupportedOperationException exUnsupported) {
                                                                System.err.println(exUnsupported.getMessage());
                                                                exUnsupported.printStackTrace();
                                                            }
                                                            if( isFileForMoveExist ){
                                                                try{
                                                                    Files.deleteIfExists(moveEfdFile);
                                                                } catch (DirectoryNotEmptyException exNotEmptyDir) {
                                                                    exNotEmptyDir.printStackTrace();
                                                                } catch (SecurityException exSecurity) {
                                                                    exSecurity.printStackTrace();
                                                                } catch (IOException exInOut) {
                                                                    exInOut.printStackTrace();
                                                                }
                                                            }
                                                            volNum++;
                                                            statusDataFsForKeyPointFlow.put(-1832815869, volNum);
                                                            packetForWriteData = new ConcurrentHashMap<String, String>();
                                                        }
                                                    }
                                                }
                                            }while( !pollTypeWordTagFileNameData.isEmpty() );
                                        }
                                        
                                        
                                        
                                        Boolean getIsNeedDelete = statusWorkersForKeyPointFlow.get(-1172779240);
                                        if( getIsNeedDelete ){
                                            String getOldForDeleteFileName = statusNameForKeyPointFlow.get(2045325664);
                                            Path deleteFile = fsForWriteData.getPath(getOldForDeleteFileName);
                                            
                                            try{
                                                Files.deleteIfExists(deleteFile);
                                            } catch (DirectoryNotEmptyException exNotEmptyDir) {
                                                exNotEmptyDir.printStackTrace();
                                            } catch (SecurityException exSecurity) {
                                                exSecurity.printStackTrace();
                                            } catch (IOException exInOut) {
                                                exInOut.printStackTrace();
                                            }
                                            Boolean removeAllFlowStatusByUUID = storageWordStatistic.removeAllFlowStatusByUUID(mainFlowLabel);
                                            removeAllFlowStatusByUUID = null;
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

                } finally {
                }
            } while( outerRuleStorageWord.isRunnedStorageWordWorkRouter() );
            /**
             * @todo write all cache data 
             */
            
        } catch(FileSystemAlreadyExistsException exAlExist){
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exAlExist.getMessage());
            exAlExist.printStackTrace();
        } catch(FileSystemNotFoundException exFsNotExist){
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exFsNotExist.getMessage());
            exFsNotExist.printStackTrace();
        } catch(ProviderNotFoundException exProvNotFound){
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exProvNotFound.getMessage());
            exProvNotFound.printStackTrace();
        } catch(IllegalArgumentException exIllArg){
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exIllArg.getMessage());
            exIllArg.printStackTrace();
        } catch(SecurityException exSec){
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                    + " error for open storage for index, reason " 
                    + exSec.getMessage());
            exSec.printStackTrace();
        } catch (IOException exIo) {
            System.err.println(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
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
    private void checkDataForWrite(
            final ZPIThStorageWordRule outerRuleStorageWord,
            final Integer typeWordBusNumberInputed,
            final String hexTagNameInputed,
            final String subStringValueInputed
    ){
        Integer typeWordBusNumberFunc;
        String hexTagNameFunc;
        String subStringValueFunc;
        
        ConcurrentHashMap<String, String> pollTypeWordTagFileNameData;
        
        try{
            typeWordBusNumberFunc = (Integer) typeWordBusNumberInputed;
            hexTagNameFunc = (String) hexTagNameInputed;
            subStringValueFunc = (String) subStringValueInputed;
            
            ZPIThStorageWordState wordState = outerRuleStorageWord.getStorageWordState();
            ZPIThStorageWordBusWriter busJobForWrite = wordState.getBusJobForStorageWordRouterJobToWriter();
            ZPIThStorageWordStatusMainFlow storageWordStatistic = outerRuleStorageWord.getStorageWordStatusMainFlow();
            ZPIThStorageWordCache storageWordCache = storageWordStatistic.getStorageWordCache();
            ZPIThStorageWordStatusActivity storageWordStatusActivity = storageWordStatistic.getStorageWordStatusActivity();
            ZPIThStorageWordStatusDataCache storageWordStatusDataCache = storageWordStatistic.getStorageWordStatusDataCache();
            ZPIThStorageWordStatusDataFs storageWordStatusDataFs = storageWordStatistic.getStorageWordStatusDataFs();
            ZPIThStorageWordStatusName storageWordStatusName = storageWordStatistic.getStorageWordStatusName();
            ZPIThStorageWordStatusWorkers storageWordStatusWorkers = storageWordStatistic.getStorageWordStatusWorkers();
            
            ConcurrentHashMap<UUID, ConcurrentHashMap<Integer, UUID>> typeWordTagFileNameFlowUuids = 
                    (ConcurrentHashMap<UUID, ConcurrentHashMap<Integer, UUID>>) storageWordStatistic.getTypeWordTagFileNameFlowUuids(
                    typeWordBusNumberFunc, 
                    hexTagNameFunc, 
                    subStringValueFunc);
                        
            for( Map.Entry<UUID, ConcurrentHashMap<Integer, UUID>> itemFlowMainUUID : typeWordTagFileNameFlowUuids.entrySet() ){

                int countThStorageWordStatusDataFs = 0;
                int countThStorageWordStatusName = 0;
                int countThStorageWordStatusActivity = 0;
                int countThStorageWordStatusDataCache = 0;
                int countThStorageWordStatusWorkers = 0;

                for(Map.Entry<Integer, UUID> itemsElements : itemFlowMainUUID.getValue().entrySet()){
                    switch ( itemsElements.getKey() ){
                        case 24537146: 
                            countThStorageWordStatusDataFs++;
                            continue; //ThStorageWordStatusDataFs
                        case -835430034: 
                            countThStorageWordStatusName++;
                            continue; //ThStorageWordStatusName
                        case -1339250574: 
                            countThStorageWordStatusActivity++;
                            continue; //ThStorageWordStatusActivity
                        case 838467829: 
                            countThStorageWordStatusDataCache++;
                            continue; //ThStorageWordStatusDataCache
                        case 842641138: 
                            countThStorageWordStatusWorkers++;
                            continue; //ThStorageWordStatusWorkers
                    }
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters of data for write into file of StorageWord is not valid");
                }

                if( countThStorageWordStatusDataFs != 2 ){
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters flowDataFs is not valid");
                }
                if( countThStorageWordStatusName != 3 ){
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters flowName is not valid");
                    }
                if( countThStorageWordStatusActivity != 2 ){
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters flowActivity is not valid");  
                }     
                if( countThStorageWordStatusDataCache != 2 ){
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters flowDataCache is not valid");
                }
                if( countThStorageWordStatusWorkers != 5 ){
                    new IllegalArgumentException(ZPIThStorageWordLogicWrite.class.getCanonicalName() 
                            + " parameters flowWorkers is not valid");
                }

                UUID keyMainFlow = itemFlowMainUUID.getKey();
                ConcurrentHashMap<Integer, UUID> flowPointsMap = itemFlowMainUUID.getValue();
                
                UUID getUUIDStatusDataCache = flowPointsMap.get(838467829);
                
                if( !storageWordStatusWorkers.isStatusWorkersNotExist(keyMainFlow) ){
                    ConcurrentHashMap<Integer, Boolean> statusWorkersForKeyPointFlow = storageWordStatusWorkers.getStatusWorkersForKeyPointFlow(keyMainFlow);
                    Boolean isDataSendIntoCahe = statusWorkersForKeyPointFlow.get(-2091433802);
                    if( isDataSendIntoCahe ){
                        
                        pollTypeWordTagFileNameData = null;
                        try{
                            pollTypeWordTagFileNameData = storageWordCache.pollTypeWordTagFileNameData(typeWordBusNumberFunc, hexTagNameFunc, subStringValueFunc);
                        } catch (NullPointerException exRetNull) {
                            System.err.println(exRetNull.getMessage());
                            exRetNull.printStackTrace();
                            continue;
                        }
                        /**
                         * @todo function for removed return cache data for write
                         */
                        if( pollTypeWordTagFileNameData != null ){
                            dataFromCahePrint(pollTypeWordTagFileNameData);
                        }
                    }
                }
            }
        } finally {
            typeWordBusNumberFunc = null;
            hexTagNameFunc = null;
            subStringValueFunc = null;
            
            pollTypeWordTagFileNameData = null;
        }
        
    }
    private void dataFromCahePrint(final ConcurrentHashMap<String, String> inputedFromCacheData){
        ConcurrentHashMap<String, String> funcFromCacheData;
        try{
            if( inputedFromCacheData != null ){
                if( !inputedFromCacheData.isEmpty() ){
                    funcFromCacheData = (ConcurrentHashMap<String, String>) inputedFromCacheData;
                    Integer sizeOfReturnedPacket = (Integer) funcFromCacheData.size();
                    System.out.println("Returned from cache size" + sizeOfReturnedPacket);
                    for( Map.Entry<String, String> itemListFromCache : funcFromCacheData.entrySet() ){
                        System.out.println("-|-|-|-|-|-|-|-|-"
                                + "                        "
                                + "-|-|-|-|-|-|-|-|-"
                                + "                        "
                                + "Returned from cache tagName " + itemListFromCache.getKey()
                                + " subString " + itemListFromCache.getValue());
                    }
                }
            }
                
        } finally {
            funcFromCacheData = null;
        }
    }
   /* private void doWrite(){
        
        try{
            do{
                        if( !jobForWrite.isBlankObject() && !jobForWrite.isWritedDataEmpty() ){
                            //currentIndexStorages.updateMapForStorages();
                            String writerPath = jobForWrite.getFileNameForWrite();
                            
                            ConcurrentSkipListMap<UUID, TdataWord> writerData = 
                                    new ConcurrentSkipListMap<UUID, TdataWord>();
                            writerData.putAll(jobForWrite.getWriterData());
                            writerPath = indexStatistic.createNewNameForWriteWithAllAddRecords(
                                    AppFileNamesConstants.FILE_INDEX_PREFIX_WORD, 
                                    writerPath, 
                                    writerData);
                            
                            Path nowWritedFile = fsForReadData.getPath(writerPath);
                            
                            try(ObjectOutputStream oos = 
                                new ObjectOutputStream(Files.newOutputStream(nowWritedFile)))
                            {
                                oos.writeObject(writerData);
                                System.out.println(ThWordLogicWrite.class.getCanonicalName() 
                                        + " => => =>                                             => => => " 
                                        + nowWritedFile.toUri().toString() 
                                        + " writed size " + writerData.size());
                                indexStatistic.addToListSizeRemoveListProcess(
                                            AppFileNamesConstants.FILE_INDEX_PREFIX_WORD, 
                                            nowWritedFile);
                            } catch(Exception ex){
                                ex.printStackTrace();
                            }
                            writerData.clear();
                            writerData = null;
                            jobForWrite.cleanWriterData();
                            jobForWrite.setTrueWriterJobDone();
                        }
                        
                        jobForWrite = busJobForWrite.getJobForWrite();
                    } while( !busJobForWrite.isJobQueueEmpty() );
        } finally {
            
        }
    }*/
}
