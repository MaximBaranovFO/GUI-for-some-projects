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

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSrchFileDataCompare {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSrchGetResult#makeSearchByKeyFromInput(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcSrchGetResult#makeSearchByKeyFromFile() }
     * </ul>
     * Find duplicate of records by dirListID
     * @param strHexForInVar
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getDistictIDs(TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar){
        TreeMap<Long, ZPINcDcIdxWordToFile> inList = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        long newRecId = 0;
        for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemID : strHexForInVar.entrySet() ){
            boolean isExistID = false;
            for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemInListID : inList.entrySet() ){
                isExistID = itemInListID.getValue().dirListID == itemID.getValue().dirListID;
            }
            if( !isExistID ){
                inList.put(newRecId, itemID.getValue());
                newRecId++;
            }
        }
        return inList;
        
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSrchGetResult#makeSearchByKeyFromFile() }
     * <li>{@link ru.newcontrol.ncfv.ZPINcSrchGetResult#outSearchResult(java.util.TreeMap, java.util.TreeMap) }
     * </ul>
     * Delete records with dirListId out of search from in search list 
     * @param strHexForInVar
     * @param strHexForOutVar
     * @return 
     */
    protected static TreeMap<Long, ZPINcDcIdxWordToFile> getIdInWithoutOfOutSearchResult(TreeMap<Long, ZPINcDcIdxWordToFile> strHexForInVar, TreeMap<Long, ZPINcDcIdxWordToFile> strHexForOutVar){
        TreeMap<Long, ZPINcDcIdxWordToFile> inList = new TreeMap<Long, ZPINcDcIdxWordToFile>();
        long newRecId = 0;
        for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemID : strHexForInVar.entrySet() ){
            boolean isExistID = false;
            for( Map.Entry<Long, ZPINcDcIdxWordToFile> itemInListForOutID : strHexForOutVar.entrySet() ){
                isExistID = itemInListForOutID.getValue().dirListID == itemID.getValue().dirListID;
            }
            if( !isExistID ){
                inList.put(newRecId, itemID.getValue());
                newRecId++;
            }
        }
        return inList;
    }
}
