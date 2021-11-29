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

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSrchGetResult {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     * @param strInputedKeyWord 
     */
    protected static void outToConsoleSearchByKeyFromInput(String strInputedKeyWord){
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> searchedData;
        searchedData = makeSearchByKeyFromInput(strInputedKeyWord);
        for( Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemReaded : searchedData.entrySet() ){
            ZPINcAppHelper.outMessageToConsole(itemReaded.getValue().path);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSIMASearchResultTableModel#NcSIMASearchResultTableModel(java.lang.String) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#outToConsoleSearchByKeyFromInput(java.lang.String) }
     * </ul>
     * @param strForSearch
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxDirListToFileAttr> makeSearchByKeyFromInput(String strForSearch){
        TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        TreeMap<Long, ZPINcDcIdxWordToFile> strDistInResult = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        
        strHexForInVar.putAll(ZPINcSrchKeyWordInput.getDirListRecordByKeyWord(strForSearch));
        strDistInResult = ZPINcSrchFileDataCompare.getDistictIDs(strHexForInVar);

        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> readedData = new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        
        readedData.putAll(ZPINcIdxDirListManager.getByListIDs(strDistInResult));
        
        return readedData;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     */
    protected static void outToConsoleSearchByKeyFromFile(){
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> searchedData;
        searchedData = makeSearchByKeyFromFile();
        for( Map.Entry<Long, ZPINcDcIdxDirListToFileAttr> itemReaded : searchedData.entrySet() ){
            ZPINcAppHelper.outMessageToConsole(itemReaded.getValue().path);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSIMASearchResultTableModel#NcSIMASearchResultTableModel()  }
     * <li>{@link ru.newcontrol.ncfv.NcSIMASearchResultTableModel#NcSIMASearchResultTableModel(java.util.ArrayList, java.util.ArrayList) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#outToConsoleSearchByKeyFromFile() }
     * </ul>
     * Get KeyWordIn(Out)Search from file and output serch results
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxDirListToFileAttr> makeSearchByKeyFromFile(){
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
        
        return readedData;
    }
    /**
     * Not used
     * @param strHexForInVar 
     */
    private static void outToConsoleSearchedIDs(TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar){
        for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemID : strHexForInVar.entrySet() ){
            ZPINcAppHelper.outMessageToConsole("id: " + itemID.getValue().dirListID);
        }
        
    }
    /**
     * Not used
     * @param strHexForInVar
     * @param strHexForOutVar 
     */
    private static void outSearchResult(TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar, TreeMap<Long, ZPINcDcIdxWordToFile> strHexForOutVar){
        TreeMap<Long, ZPINcDcIdxWordToFile> CleanResult = ZPINcSrchFileDataCompare.getIdInWithoutOfOutSearchResult(strHexForInVar, strHexForOutVar);
        ZPINcAppHelper.outMessage(
            ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.CLEAN_RESULT.getStr()
            + ZPINcStrServiceMsg.COUNT_OF_SEARCHED_RECORDS.getStr() + CleanResult.size());
    }
}
