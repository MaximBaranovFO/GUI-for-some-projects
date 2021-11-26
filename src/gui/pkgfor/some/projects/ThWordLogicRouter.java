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
public class ThWordLogicRouter {
    /**
     * with events logic version
     * @param outerRuleWord 
     */
    protected void doRouterForIndexWord(final ThWordRule outerRuleWord){
        ThWordRule wordRuleFunc;
        ZPIThIndexRule indexRuleFunc;
        ZPIThIndexState indexState;
        ThStorageWordRule storageWordRuleFunc;
        ThStorageWordState storageWordState;
        ThStorageWordBusOutput busOutputForWordRouter;
        try {
            AdilRule adilRule = outerRuleWord.getIndexRule().getAdilRule();
            AdilState adilState = adilRule.getAdilState();
            Integer numberProcessIndexSystem = 10;
            String msgToLog = AdilConstants.INFO_LOGIC_POSITION
                    + AdilConstants.CANONICALNAME
                    + ThWordLogicRouter.class.getCanonicalName()
                    + AdilConstants.METHOD
                    + "doRouterForIndexWord()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + AdilConstants.START);
            wordRuleFunc = (ThWordRule) outerRuleWord;
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
                + AdilConstants.FINISH);
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
            ThWordRule outerRuleWord, 
            ThStorageWordBusOutput busOutputForWordRouterInputed){
        ThStorageWordBusOutput busOutputForWordRouterFunc;
        LinkedTransferQueue<ZPITdataWord> busOutputByTypeWord;
        Integer typeWordOfBusOutput;
        try {
            busOutputForWordRouterFunc = (ThStorageWordBusOutput) busOutputForWordRouterInputed;
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
            ThWordRule outerRuleWord, 
            Integer typeWordOfBusOutputInputed, 
            LinkedTransferQueue<ZPITdataWord> busOutputByTypeWordInputed){
        ThWordEventLogic eventLogic;
        Boolean labelTypeData;
        Integer typeWordOfBusOutputFunc;
        String hexTagName;
        String subString;
        LinkedTransferQueue<ZPITdataWord> busOutputByTypeWordFunc;
        ConcurrentSkipListMap<UUID, ZPITdataWord> pollFromBusOutputDataPacket;
        ZPITdataWord pollDataItem;
        UUID itemKey;
        try {
            AdilRule adilRule = outerRuleWord.getIndexRule().getAdilRule();
            AdilState adilState = adilRule.getAdilState();
            Integer numberProcessIndexSystem = 10;
            String msgToLog = AdilConstants.INFO_LOGIC_POSITION
                    + AdilConstants.CANONICALNAME
                    + ThWordLogicRouter.class.getCanonicalName()
                    + AdilConstants.METHOD
                    + "generateMainFlowForDataFromBusOutput()";
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                    msgToLog
                    + AdilConstants.START);
            typeWordOfBusOutputFunc = (Integer) typeWordOfBusOutputInputed;
            busOutputByTypeWordFunc = (LinkedTransferQueue<ZPITdataWord>) busOutputByTypeWordInputed;
            hexTagName = new String();
            eventLogic = (ThWordEventLogic) outerRuleWord.getWordState().getEventLogic();
            
            do {
                pollDataItem = busOutputByTypeWordFunc.poll();
                if( pollDataItem != null ){
                    hexTagName = pollDataItem.hexSubString;
                    subString = pollDataItem.strSubString;
                    itemKey = pollDataItem.randomUUID;
                    /*System.out.println(ThWordLogicRouter.class.getCanonicalName() 
                            + "          ====        ++++      ====      <<< Logic Word"
                            + "----- <   <   <   ---   tagname " + hexTagName + " string " + subString);*/
                    adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                            msgToLog
                            + AdilConstants.STATE
                            + AdilConstants.VARNAME
                            + "typeWordOfBusOutputFunc"
                            + AdilConstants.VARVAL
                            + String.valueOf(typeWordOfBusOutputFunc)
                            + AdilConstants.VARNAME
                            + "itemKey"
                            + AdilConstants.VARVAL
                            + itemKey
                            + AdilConstants.VARNAME
                            + "hexTagName"
                            + AdilConstants.VARVAL
                            + hexTagName
                            + AdilConstants.VARNAME
                            + "subString"
                            + AdilConstants.VARVAL
                            + subString
                        );
                    eventLogic.insertIntoCacheData(typeWordOfBusOutputFunc, hexTagName, subString, pollDataItem);
                }
            } while( !busOutputByTypeWordFunc.isEmpty() );
            adilState.putLogLineByProcessNumberMsg(numberProcessIndexSystem, 
                msgToLog
                + AdilConstants.FINISH);
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
