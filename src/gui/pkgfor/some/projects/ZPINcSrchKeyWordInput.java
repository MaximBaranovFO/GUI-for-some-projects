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
import java.util.TreeMap;


/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSrchKeyWordInput {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromInput(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromFile() }
     * </ul>
     * Filter and split KeyWords into words format used in index folder
     * file names with id data
     * @param inFuncKeyWord
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getDirListRecordByKeyWord(String inFuncKeyWord){
        TreeMap<Long, ZPINcDcIdxWordToFile> idsDataForKeyWord = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        
        ArrayList<String> strABC = ZPINcPathToArrListStr.NCLVLABC.retArrListStr(inFuncKeyWord);
        idsDataForKeyWord.putAll(getIdDataForSplittedKeyWord(strABC));
        
        ArrayList<String> strRABC = ZPINcPathToArrListStr.NCLVLRABC.retArrListStr(inFuncKeyWord);
        idsDataForKeyWord.putAll(getIdDataForSplittedKeyWord(strRABC));

        ArrayList<String> strNUM = ZPINcPathToArrListStr.NCLVLNUM.retArrListStr(inFuncKeyWord);
        idsDataForKeyWord.putAll(getIdDataForSplittedKeyWord(strNUM));
        
        ArrayList<String> strSYM = ZPINcPathToArrListStr.NCLVLSYM.retArrListStr(inFuncKeyWord);
        idsDataForKeyWord.putAll(getIdDataForSplittedKeyWord(strSYM));
        
        //ArrayList<String> strSPACE = ZPINcPathToArrListStr.NCLVLSPACE.retArrListStr(inFuncKeyWord);
        return idsDataForKeyWord;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSrchKeyWordInput#getDirListRecordByKeyWord(java.lang.String) }
     * </ul>
     * Input in function splitted strings of keywords for create
     * file names with id data in word subDirs
     * Output from function data from keywords files 
     * @param inFuncListWord
     * @return 
     */
    private static TreeMap<Long, ZPINcDcIdxWordToFile> getIdDataForSplittedKeyWord(ArrayList<String> inFuncListWord){
        
        ArrayList<String> strHexKeyWord = new ArrayList<String>();
        ArrayList<String> strWordForVar = new ArrayList<String>();
        ArrayList<String> strLongWord = new ArrayList<String>();
        ArrayList<String> strUpperLowerVar = new ArrayList<String>();
        
        TreeMap<Long, ZPINcDcIdxWordToFile> idsDataForReturn = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        
        for( String strItem : inFuncListWord ){
            if( ZPINcIdxLongWordManager.isLongWord(strItem) ){
                strLongWord.add(strItem);
            }
            else{
                
                String strLow = strItem.toLowerCase().toString();
                String strUp = strItem.toUpperCase().toString();
                
                if( !strLow.equals(strUp) ){
                    strWordForVar.add(strItem);
                }
                else{
                    strHexKeyWord.add(ZPINcPathToArrListStr.toStrUTFinHEX(strItem));
                }
            }
        }
        /**
         * @TODO search for LongWord Structure
         */
        if( !strLongWord.isEmpty() ){
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + ZPINcStrServiceMsg.FUNCTIONAL_NOT_RELEASED.getStr()
                + ZPINcStrServiceMsg.NOT_IN_SEARCH_COUNT
                + strLongWord.size());
        }
        
        TreeMap<Long, ZPINcIdxSubStringVariant> strSearchWordVarList = ZPINcSrchVariantMaker.getUpperLowerCaseVariant(strWordForVar);
        
        strUpperLowerVar = ZPINcSrchVariantMaker.getUpLowerCaseCombineKeyWord(strSearchWordVarList);
        
        for(String itemHexVar : strUpperLowerVar){
            strHexKeyWord.add(itemHexVar);
        }
        
        for( String strHexItemRABC : strHexKeyWord ){
            
            idsDataForReturn.putAll(ZPINcIdxWordManager.getAllDataForWord(strHexItemRABC));
        }
        
        return idsDataForReturn;
    }

}
