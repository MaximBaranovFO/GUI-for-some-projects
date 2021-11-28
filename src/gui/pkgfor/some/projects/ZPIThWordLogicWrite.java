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
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThWordLogicWrite {
    protected void doWriteToIndexWord(final ZPIThWordRule outerRuleWord){
        ZPIThIndexRule indexRule;
        ZPIThIndexStatistic indexStatistic;
        ZPIThWordRule funcRuleWord;
        ZPIAppFileStorageIndex currentIndexStorages;
        UUID pollNextUuid;
        URI byPrefixGetUri;
        Map<String, String> byPrefixGetMap;
        ZPIThWordEventLogic eventLogic;
        try {
            ZPIAdilRule adilRule = outerRuleWord.getIndexRule().getZPIAdilRule();
            ZPIAdilState adilState = adilRule.getZPIAdilState();
            Integer numberProcessIndexSystem = 12;
            String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                    + ZPIAdilConstants.CANONICALNAME
                    + ZPIThWordLogicWrite.class.getCanonicalName()
                    + ZPIAdilConstants.METHOD
                    + "doWriteToIndexWord()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + ZPIAdilConstants.START);
            funcRuleWord = (ZPIThWordRule) outerRuleWord;
            
            indexRule = funcRuleWord.getIndexRule();
            //indexStatistic = indexRule.getIndexStatistic();
            //indexStatistic.updateDataStorages();
            currentIndexStorages = funcRuleWord.getIndexRule().getIndexState().currentIndexStorages();
            
            byPrefixGetUri = currentIndexStorages.byPrefixGetUri(ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
            
            byPrefixGetMap = currentIndexStorages.byPrefixGetMap( 
                    ZPIAppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
            for( Map.Entry<String, String> itemByPrefixGetMap : byPrefixGetMap.entrySet() ){
                adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + ZPIAdilConstants.STATE
                    + ZPIAdilConstants.VARNAME
                    + "byPrefixGetUri.toString()"
                    + ZPIAdilConstants.VARVAL
                    + byPrefixGetUri.toString()
                    + ZPIAdilConstants.VARNAME
                    + "itemByPrefixGetMap.getKey()"
                    + ZPIAdilConstants.VARVAL
                    + itemByPrefixGetMap.getKey()
                    + ZPIAdilConstants.VARNAME
                    + "itemByPrefixGetMap.getValue()"
                    + ZPIAdilConstants.VARVAL
                    + itemByPrefixGetMap.getValue()
                );
            }
            
            try( FileSystem fsForWriteData = FileSystems.newFileSystem(byPrefixGetUri, byPrefixGetMap) ){
                System.out.println("   ---   ---   ---   ---   ---   ---   ---   ---   ---   " 
                    + ZPIThWordLogicWrite.class.getCanonicalName() + " open storage " + fsForWriteData.getPath("/").toUri().toString());
                do {
                    pollNextUuid = outerRuleWord.getWordState().getBusEventShort().pollNextUuid(2, 3);
                    if( checkStateForUuidOnDoWrite(outerRuleWord, pollNextUuid) ){
                        //move uuid in bus event shot
                        //move uuid in statebuseventlocal
                        //do write data
                        //move uuid into wait read
                        eventLogic = (ZPIThWordEventLogic) outerRuleWord.getWordState().getEventLogic();
                        try {
                            eventLogic.writeDataToStorage(fsForWriteData, pollNextUuid);
                        } catch(IllegalStateException exIllState) {
                            System.err.println(exIllState.getMessage());
                            exIllState.printStackTrace();
                        }
                    } else {
                        //not founded in nextStep ready insertToCache uuids go into...
                    }
                } while( funcRuleWord.isRunnedWordWorkRouter() );
                //need write all cached data after end for all read jobs
            } catch(FileSystemAlreadyExistsException exAlExist){
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exAlExist.getMessage());
                exAlExist.printStackTrace();
            } catch(FileSystemNotFoundException exFsNotExist){
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exFsNotExist.getMessage());
                exFsNotExist.printStackTrace();
            } catch(ProviderNotFoundException exProvNotFound){
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exProvNotFound.getMessage());
                exProvNotFound.printStackTrace();
            } catch(IllegalArgumentException exIllArg){
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exIllArg.getMessage());
                exIllArg.printStackTrace();
            } catch(SecurityException exSec){
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exSec.getMessage());
                exSec.printStackTrace();
            } catch (IOException exIo) {
                System.err.println(ZPIThWordLogicWrite.class.getCanonicalName() 
                        + " error for open storage for index, reason " 
                        + exIo.getMessage());
                exIo.printStackTrace();
            }
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.FINISH);
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
    protected Boolean checkStateForUuidOnDoWrite(ZPIThWordRule outerRuleWord, UUID checkedReturnedUuid){
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
                        continue;
                    }
                    if( foundUuidInList[0] == 0 && foundUuidInList[1] == 2 ){
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
    protected void readNextUuidFromEventShot(){
        
    }
    protected void readExtendedInfoForUUID(){
        
    }
    protected void readDataFromCache(){
        
    }
    protected void writeDataToStorage(){
        
    }
    protected void deleteOldDataFromStorage(){
        
    }

}
