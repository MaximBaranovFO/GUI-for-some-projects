/*
 *  Copyright 2017 Administrator of development departament newcontrol.ru .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package gui.pkgfor.some.projects;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *  ZPINcSearchInIndex.getWordSearchResult(strIn, strOut);
 * @author Администратор
 */
public class ZPINcSearchInIndex {
    
    private ZPINcIndexManageIDs ncThisManageIDs;
    private TreeMap<Long, ZPINcDiskInfo> ncDiskInfo;

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSIMASearchResultTableModel#ZPINcSIMASearchResultTableModel(java.lang.String) }
     * </ul>
     */
    protected ZPINcSearchInIndex() {
        ZPINcIMinFS ncwd = new ZPINcIMinFS();
        
        ncThisManageIDs = ncwd.getZPINcIndexManageIDs();
        ncDiskInfo = ncwd.getDiskInfo();
    }
    /** 
     * Not used
     * @param strKeyWordInSearch
     * @param strKeyWordOutSearch
     * @return 
     */ 
    private static void searchWordInIndex(){
        TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxWordToFile> strHexForOutVar = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxWordToFile> strDistInResult = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxWordToFile> strDistOutResult = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        
        ArrayList<String> arrKeyWordInSearch = ZPINcEtcKeyWordListManager.getKeyWordInSearchFromFile();
        for( String strItemIn : arrKeyWordInSearch ){
            strHexForInVar.putAll(ZPINcSrchKeyWordInput.getDirListRecordByKeyWord(strItemIn));
        }
        
        strDistInResult = ZPINcSrchFileDataCompare.getDistictIDs(strHexForInVar);
        
        ArrayList<String> arrKeyWordOutSearch = ZPINcEtcKeyWordListManager.getKeyWordOutSearchFromFile();
        for( String strItemOut : arrKeyWordOutSearch ){
            strHexForOutVar.putAll(ZPINcSrchKeyWordInput.getDirListRecordByKeyWord(strItemOut));
        }
        
        strDistOutResult = ZPINcSrchFileDataCompare.getDistictIDs(strHexForOutVar);
        
        
        
        TreeMap<Long, ZPINcDcIdxWordToFile> CleanResult = ZPINcSrchFileDataCompare.getIdInWithoutOfOutSearchResult(strDistInResult, strDistOutResult);
        
        
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> readedData = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        
        readedData.putAll(ZPINcIdxDirListManager.getByListIDs(CleanResult));
        
        for( Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemReaded : readedData.entrySet() ){
            ZPINcAppHelper.outMessage(itemReaded.getValue().path);
        }
    }
    /**
     * Not used
     * @param strKeyWordInSearch
     * @param strKeyWordOutSearch
     * @return 
     */
    private TreeMap<Long, ZPINcDcIdxDirListToFileAttr> getWordSearchResult(ArrayList<String> strKeyWordInSearch, ArrayList<String> strKeyWordOutSearch){
        TreeMap<Long, ZPINcDcIdxWordToFile> dataForInKeyWord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxWordToFile> dataForOutKeyWord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> retFormDiskDataResult = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> retFilteredDataResult = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        
        for(String items : strKeyWordInSearch){
            dataForInKeyWord.putAll(getIDsForKeyWord(items));
        }
        
        if(strKeyWordOutSearch.isEmpty()){
            for(Map.Entry<Long, ZPINcDcIdxWordToFile> itemIDSearch : dataForInKeyWord.entrySet()){
                retFormDiskDataResult.putAll(ZPINcIdxDirListFileReader.ncReadFromDirListFile(itemIDSearch.getValue().dirListID));
            
            //
            }
            return retFormDiskDataResult;
            //for all before coment and coment after
            
            /*for(Map.Entry<Long, ZPINcDirListToFilesForIndex> itemClean : retFormDiskDataResult.entrySet()){
                if(itemClean.getValue().i == itemIDSearch.getValue().di){
                    retFilteredDataResult.put(itemClean.getKey(), itemClean.getValue());
                }
            }
            }
            return retFilteredDataResult;*/
        }
        
        for(String items : strKeyWordOutSearch){
            dataForOutKeyWord.putAll(getIDsForKeyWord(items));
        }
        TreeMap<Long, ZPINcDcIdxWordToFile> dataForCompareKeyWord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        Object clone = dataForInKeyWord.clone();
        dataForCompareKeyWord = (TreeMap<Long, ZPINcDcIdxWordToFile>) clone;
        
        for(Map.Entry<Long, ZPINcDcIdxWordToFile> itemForIn : dataForInKeyWord.entrySet()){
            ZPINcDcIdxWordToFile dataInForCompare = itemForIn.getValue();
            for(Map.Entry<Long, ZPINcDcIdxWordToFile> itemForOut : dataForOutKeyWord.entrySet()){
                ZPINcDcIdxWordToFile dataOutForCompare = itemForOut.getValue();
                if(dataInForCompare.dirListID == dataOutForCompare.dirListID){
                    dataForCompareKeyWord.remove(itemForIn.getKey()) ;
                }
            }
        }
        
        
        
        for(Map.Entry<Long, ZPINcDcIdxWordToFile> itemIDSearch : dataForCompareKeyWord.entrySet()){
            retFormDiskDataResult.putAll(ZPINcIdxDirListFileReader.ncReadFromDirListFile(itemIDSearch.getValue().dirListID));
            for(Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemClean : retFormDiskDataResult.entrySet()){
                if(itemClean.getValue().dirListID == itemIDSearch.getValue().dirListID){
                    retFilteredDataResult.put(itemClean.getKey(), itemClean.getValue());
                }
            }
        }
            
        
        return retFilteredDataResult;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSearchInIndex#getWordSearchResult(java.util.ArrayList, java.util.ArrayList) }
     * </ul>
     * @param strKeyWordInSearch
     * @return 
     */
    private TreeMap<Long, ZPINcDcIdxWordToFile> getIDsForKeyWord(String strKeyWordInSearch){
                long searchID = 0;
        
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureABC = ZPINcPathToArrListStr.getStructureToRecord(
                ZPINcPathToArrListStr.NCLVLABC.retArrListStr(strKeyWordInSearch),
                strKeyWordInSearch,
                searchID);
        
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureRABC = ZPINcPathToArrListStr.getStructureToRecord(
                ZPINcPathToArrListStr.NCLVLRABC.retArrListStr(strKeyWordInSearch),
                strKeyWordInSearch,
                searchID);
                
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureNUM = ZPINcPathToArrListStr.getStructureToRecord(
                ZPINcPathToArrListStr.NCLVLNUM.retArrListStr(strKeyWordInSearch),
                strKeyWordInSearch,
                searchID);
                        
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureSYM = ZPINcPathToArrListStr.getStructureToRecord(
                ZPINcPathToArrListStr.NCLVLSYM.retArrListStr(strKeyWordInSearch),
                strKeyWordInSearch,
                searchID);
                                
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureSPACE = ZPINcPathToArrListStr.getStructureToRecord(
                ZPINcPathToArrListStr.NCLVLSPACE.retArrListStr(strKeyWordInSearch),
                strKeyWordInSearch,
                searchID);
        
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureWord = new TreeMap<Long, ZPINcDcIdxSubStringToOperationUse>();
        //For Word Length > 25
        TreeMap<Long, ZPINcDcIdxSubStringToOperationUse> StructureLongWord = new TreeMap<Long, ZPINcDcIdxSubStringToOperationUse>();
        long lwIdx = 0;
        long wIdx = 0;
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureABC.entrySet()){
            if (item.getValue().strSubString.length() > 25){
                StructureLongWord.put(lwIdx, item.getValue());
                lwIdx++;
            }
            else{
                StructureWord.put(wIdx, item.getValue());
                wIdx++;
            }
        }
        
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureRABC.entrySet()){
            if (item.getValue().strSubString.length() > 25){
                StructureLongWord.put(lwIdx, item.getValue());
                lwIdx++;
            }
            else{
                StructureWord.put(wIdx, item.getValue());
                wIdx++;
            }
        }
        
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureNUM.entrySet()){
            if (item.getValue().strSubString.length() > 25){
                StructureLongWord.put(lwIdx, item.getValue());
                lwIdx++;
            }
            else{
                StructureWord.put(wIdx, item.getValue());
                wIdx++;
            }
        }
        
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureSYM.entrySet()){
            if (item.getValue().strSubString.length() > 25){
                StructureLongWord.put(lwIdx, item.getValue());
                lwIdx++;
            }
            else{
                StructureWord.put(wIdx, item.getValue());
                wIdx++;
            }
        }
        
        for(Map.Entry<Long, ZPINcDcIdxSubStringToOperationUse> item : StructureSPACE.entrySet()){
            if (item.getValue().strSubString.length() > 25){
                StructureLongWord.put(lwIdx, item.getValue());
                lwIdx++;
            }
            else{
                StructureWord.put(wIdx, item.getValue());
                wIdx++;
            }
        }
        TreeMap<Long, ZPINcDcIdxWordToFile> searchedWords = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        searchedWords.putAll(ZPINcIdxLongWordManager.getLongWord(StructureLongWord));
        searchedWords.putAll(ZPINcIdxWordManager.getWord(StructureWord));
        
        return searchedWords;
    }
    /**
     * Not used
     * @return 
     */
    private TreeMap<Long, ZPINcDcIdxDirListToFileAttr> getKeyWordForSearch(){
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> Word;
        Word = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> LongWord;
        LongWord = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();

        return Word;
    }
}
