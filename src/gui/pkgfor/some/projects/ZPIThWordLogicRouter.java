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

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThWordLogicRouter {
    /**
     * with events logic version
     * @param outerRuleWord 
     */
    protected void doRouterForIndexWord(final ZPIThWordRule outerRuleWord){
        ZPIThWordRule wordRuleFunc;
        ZPIThIndexRule indexRuleFunc;
        ZPIThIndexState indexState;
        ZPIThStorageWordRule storageWordRuleFunc;
        ZPIThStorageWordState storageWordState;
        ZPIThStorageWordBusOutput busOutputForWordRouter;
        try {
            ZPIAdilRule adilRule = outerRuleWord.getIndexRule().getZPIAdilRule();
            ZPIAdilState adilState = adilRule.getZPIAdilState();
            Integer numberProcessIndexSystem = 10;
            String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                    + ZPIAdilConstants.CANONICALNAME
                    + ZPIThWordLogicRouter.class.getCanonicalName()
                    + ZPIAdilConstants.METHOD
                    + "doRouterForIndexWord()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + ZPIAdilConstants.START);
            wordRuleFunc = (ZPIThWordRule) outerRuleWord;
            indexRuleFunc = wordRuleFunc.getIndexRule();
            indexState = indexRuleFunc.getIndexState();
            storageWordRuleFunc = indexRuleFunc.getIndexState().getRuleStorageWord();
            storageWordState = storageWordRuleFunc.getStorageWordState();
            busOutputForWordRouter = storageWordState.getBusJobForWordWrite();
            do{
                outerBusIterator(wordRuleFunc, busOutputForWordRouter);
                
            } while( storageWordRuleFunc.isRunnedStorageWordWorkFilter() );
            
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.FINISH);
        } finally {
            wordRuleFunc = null;
            indexRuleFunc = null;
            indexState = null;
            storageWordRuleFunc = null;
            storageWordState = null;
            busOutputForWordRouter = null;
        }
    }
    protected void outerBusIterator(
            ZPIThWordRule outerRuleWord, 
            ZPIThStorageWordBusOutput busOutputForWordRouterInputed){
        ZPIThStorageWordBusOutput busOutputForWordRouterFunc;
        LinkedTransferQueue<ZPITdataWord> busOutputByTypeWord;
        Integer typeWordOfBusOutput;
        try {
            busOutputForWordRouterFunc = (ZPIThStorageWordBusOutput) busOutputForWordRouterInputed;
            for( Map.Entry<Integer, LinkedTransferQueue<ZPITdataWord>> itemsBusByTypeWord : busOutputForWordRouterFunc.getExistBusEntrySetForTypeWord() ){
                //make event indexes, main flow, set stop flags and insert data into cache
                typeWordOfBusOutput = itemsBusByTypeWord.getKey();
                busOutputByTypeWord = itemsBusByTypeWord.getValue();
                if( busOutputByTypeWord != null ){
                    if( !busOutputByTypeWord.isEmpty() ){
                        generateMainFlowForDataFromBusOutput(outerRuleWord, typeWordOfBusOutput, busOutputByTypeWord);
                    }
                }
            }
        } finally {
            busOutputForWordRouterFunc = null;
            busOutputByTypeWord = null;
            typeWordOfBusOutput = null;
        }
    }
    protected void generateMainFlowForDataFromBusOutput(
            ZPIThWordRule outerRuleWord, 
            Integer typeWordOfBusOutputInputed, 
            LinkedTransferQueue<ZPITdataWord> busOutputByTypeWordInputed){
        ZPIThWordEventLogic eventLogic;
        Boolean labelTypeData;
        Integer typeWordOfBusOutputFunc;
        String hexTagName;
        String subString;
        LinkedTransferQueue<ZPITdataWord> busOutputByTypeWordFunc;
        ConcurrentSkipListMap<UUID, ZPITdataWord> pollFromBusOutputDataPacket;
        ZPITdataWord pollDataItem;
        UUID itemKey;
        try {
            ZPIAdilRule adilRule = outerRuleWord.getIndexRule().getZPIAdilRule();
            ZPIAdilState adilState = adilRule.getZPIAdilState();
            Integer numberProcessIndexSystem = 10;
            String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                    + ZPIAdilConstants.CANONICALNAME
                    + ZPIThWordLogicRouter.class.getCanonicalName()
                    + ZPIAdilConstants.METHOD
                    + "generateMainFlowForDataFromBusOutput()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + ZPIAdilConstants.START);
            typeWordOfBusOutputFunc = (Integer) typeWordOfBusOutputInputed;
            busOutputByTypeWordFunc = (LinkedTransferQueue<ZPITdataWord>) busOutputByTypeWordInputed;
            hexTagName = new String();
            eventLogic = (ZPIThWordEventLogic) outerRuleWord.getWordState().getEventLogic();
            
            do {
                pollDataItem = busOutputByTypeWordFunc.poll();
                if( pollDataItem != null ){
                    hexTagName = pollDataItem.hexSubString;
                    subString = pollDataItem.strSubString;
                    itemKey = pollDataItem.randomUUID;
                    /*System.out.println(ZPIThWordLogicRouter.class.getCanonicalName() 
                            + "          ====        ++++      ====      <<< Logic Word"
                            + "----- <   <   <   ---   tagname " + hexTagName + " string " + subString);*/
                    adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                            msgToLog
                            + ZPIAdilConstants.STATE
                            + ZPIAdilConstants.VARNAME
                            + "typeWordOfBusOutputFunc"
                            + ZPIAdilConstants.VARVAL
                            + String.valueOf(typeWordOfBusOutputFunc)
                            + ZPIAdilConstants.VARNAME
                            + "itemKey"
                            + ZPIAdilConstants.VARVAL
                            + itemKey
                            + ZPIAdilConstants.VARNAME
                            + "hexTagName"
                            + ZPIAdilConstants.VARVAL
                            + hexTagName
                            + ZPIAdilConstants.VARNAME
                            + "subString"
                            + ZPIAdilConstants.VARVAL
                            + subString
                        );
                    eventLogic.insertIntoCacheData(typeWordOfBusOutputFunc, hexTagName, subString, pollDataItem);
                }
            } while( !busOutputByTypeWordFunc.isEmpty() );
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.FINISH);
        } finally {
            eventLogic = null;
            labelTypeData = null;
            typeWordOfBusOutputFunc = null;
            busOutputByTypeWordFunc = null;
            pollFromBusOutputDataPacket = null;
            pollDataItem = null;
            itemKey = null;
            hexTagName = null;
            subString = null;
        }
    }
    
}
