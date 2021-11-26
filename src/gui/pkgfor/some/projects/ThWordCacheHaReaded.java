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
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap<Integer,   - (2) - Type of word index hash value
 *   ConcurrentHashMap<String,    - (2a) - tagFileName.substring(0,3)
 *     ConcurrentHashMap<Integer, - (2b) - subString.length                            
 *     ConcurrentHashMap<String, String> (3) - <hexWord (tagFileName), subString>
 * @author wladimirowichbiaran
 */
public class ThWordCacheHaReaded {
    /**
     * @todo
     * <typeWord, <tagName.substring(0,3), strName.length, <tagName, <UUID, TdataWord>>>>
     */
    private ConcurrentHashMap<Integer, 
            ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>>> cachedData;
    
    public ThWordCacheHaReaded() {
        this.cachedData = createNewListStoragesMapEmpty();
    }
    private ConcurrentHashMap<Integer, 
        ConcurrentHashMap<String, 
            ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>>> createNewListStoragesMapEmpty(){
        return new ConcurrentHashMap<Integer, 
                        ConcurrentHashMap<String, 
                            ConcurrentHashMap<Integer, 
                                ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>>>();
    }
    /**
     * @todo remove keys for empty lists
     */
    /**
     * remove and return list of data from cache, remove empty keys
     * {@code <typeWord, hexTagName>}
     * code func for poll by typeWord and hexTagName
     */
    /**
     * @todo after rerelease that, rebuild in Storage Word System
     * When all sources data has bin sended into index system Word, and processed, need
     * save to storage all cached data, 
     * this function return all cahed data for save to storage
     * @return {@code ConcurrentHashMap<Integer, ConcurrentHashMap<String, String>>}
     *                         <p>{@code <TypeWord, <TagName, SubString>>}
     */
    protected ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>>>> pollAllData(){
        
        Integer keyTypeWordList;
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>>>> createNewListStoragesMapCleaned;
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>>> removeIfNullCachedData;
        ConcurrentHashMap<String, 
                        ConcurrentHashMap<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>>> valueTypeWordList;
        ConcurrentHashMap<String, 
                        ConcurrentHashMap<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>>> valueTypeWordListCleaned;
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>> removeIfNullTypeWordList;
        String keyHexTagNameLetter;
        ConcurrentHashMap<Integer,
                        ConcurrentHashMap<String,
                        ConcurrentHashMap<UUID, ZPITdataWord>>> valueHexTagNameLetter;
        ConcurrentHashMap<Integer,
                        ConcurrentHashMap<String,
                        ConcurrentHashMap<UUID, ZPITdataWord>>> valueHexTagNameLetterCleaned;
        ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>> removeIfNullHexTagNameLetter;
        Integer keySubStringLength;
        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>> valueSubStringLength;
        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>> valueSubStringLengthCleaned;
        ConcurrentHashMap<UUID, ZPITdataWord> removeSubStringLength;
        String keyHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> valueHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> dataByHexTagName;
        UUID keyData;
        ZPITdataWord removedData;
        ZPITdataWord removeIfNullData;
        try {
            createNewListStoragesMapCleaned = createNewListStoragesMapEmpty();
            for( Map.Entry<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>>> entrySetTypeWord : this.cachedData.entrySet() ){
                
                keyTypeWordList = (Integer) entrySetTypeWord.getKey();
                
                valueTypeWordList = (ConcurrentHashMap<String, 
                        ConcurrentHashMap<Integer,
                        ConcurrentHashMap<String,
                        ConcurrentHashMap<UUID, ZPITdataWord>>>>) entrySetTypeWord.getValue();
                if( keyTypeWordList == null ){
                    continue;
                }
                if( valueTypeWordList == null ){
                    
                    removeIfNullCachedData = this.cachedData.remove(keyTypeWordList);
                    removeIfNullCachedData = null;
                    keyTypeWordList = null;
                    continue;
                }
                if( valueTypeWordList.isEmpty() ){
                    continue;
                }
                valueTypeWordListCleaned = new ConcurrentHashMap<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>>();
                for( Map.Entry<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>> entrySetHexTagNameLetter : valueTypeWordList.entrySet() ){
                    
                    keyHexTagNameLetter = entrySetHexTagNameLetter.getKey();
                    valueHexTagNameLetter = (ConcurrentHashMap<Integer,
                        ConcurrentHashMap<String,
                        ConcurrentHashMap<UUID, ZPITdataWord>>>) entrySetHexTagNameLetter.getValue();
                    if( keyHexTagNameLetter == null ){
                        continue;
                    }
                    if( valueHexTagNameLetter == null ){
                        
                        removeIfNullTypeWordList = valueTypeWordList.remove(keyHexTagNameLetter);
                        removeIfNullTypeWordList = null;
                        keyHexTagNameLetter = null;
                        continue;
                    }
                    if( valueHexTagNameLetter.isEmpty() ){
                        continue;
                    }
                    valueHexTagNameLetterCleaned = new ConcurrentHashMap<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>>();
                    for( Map.Entry<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>> entrySetSubStringLength : valueHexTagNameLetter.entrySet() ){
                        
                        keySubStringLength = (Integer) entrySetSubStringLength.getKey();
                        valueSubStringLength = (ConcurrentHashMap<String, 
                                                    ConcurrentHashMap<UUID, ZPITdataWord>>) entrySetSubStringLength.getValue();
                        if( keySubStringLength == null ){
                            continue;
                        }
                        if( valueSubStringLength == null ){
                            
                            removeIfNullHexTagNameLetter = valueHexTagNameLetter.remove(keySubStringLength);
                            removeIfNullHexTagNameLetter = null;
                            keySubStringLength = null;
                            continue;
                        }
                        if( valueSubStringLength.isEmpty() ){
                            continue;
                        }
                        valueSubStringLengthCleaned = new ConcurrentHashMap<String, 
                            ConcurrentHashMap<UUID, ZPITdataWord>>();
                        for( Map.Entry<String, 
                            ConcurrentHashMap<UUID, ZPITdataWord>> entrySetHexTagName : valueSubStringLength.entrySet() ){
                            keyHexTagName = (String) entrySetHexTagName.getKey();
                            valueHexTagName = (ConcurrentHashMap<UUID, ZPITdataWord>) entrySetHexTagName.getValue();
                            if( keyHexTagName == null ){
                                continue;
                            }
                            if( valueHexTagName == null ){
                                
                                removeSubStringLength = valueSubStringLength.remove(keyHexTagName);
                                removeSubStringLength = null;
                                keyHexTagName = null;
                                continue;
                            }
                            if( valueHexTagName.isEmpty() ){
                                continue;
                            }
                            dataByHexTagName = new ConcurrentHashMap<UUID, ZPITdataWord>();
                            for( Map.Entry<UUID, ZPITdataWord> itemFromHexTagNameList : valueHexTagName.entrySet() ){
                                keyData = (UUID) itemFromHexTagNameList.getKey();
                                removedData = (ZPITdataWord) valueHexTagName.remove(keyData);
                                if( keyData == null ){
                                    continue;
                                }
                                if( removedData == null ){
                                    
                                    removeIfNullData = (ZPITdataWord) valueHexTagName.remove(keyData);
                                    removeIfNullData = null;
                                    keyData = null;
                                    continue;
                                }
                                dataByHexTagName.put(keyData, removedData);
                            }
                            valueSubStringLengthCleaned.put(keyHexTagName, dataByHexTagName);
                        }
                        valueHexTagNameLetterCleaned.put(keySubStringLength, valueSubStringLengthCleaned);
                    }
                    valueTypeWordListCleaned.put(keyHexTagNameLetter, valueHexTagNameLetterCleaned);
                }
                createNewListStoragesMapCleaned.put(keyTypeWordList, valueTypeWordListCleaned);
            }
            return createNewListStoragesMapCleaned;
        }
        finally {
            keyTypeWordList = null;
            createNewListStoragesMapCleaned = null;
            removeIfNullCachedData = null;
            valueTypeWordList = null;
            valueTypeWordListCleaned = null;
            removeIfNullTypeWordList = null;
            keyHexTagNameLetter = null;
            valueHexTagNameLetter = null;
            valueHexTagNameLetterCleaned = null;
            removeIfNullHexTagNameLetter = null;
            keySubStringLength = null;
            valueSubStringLength = null;
            valueSubStringLengthCleaned = null;
            removeSubStringLength = null;
            keyHexTagName = null;
            valueHexTagName = null;
            dataByHexTagName = null;
            keyData = null;
            removedData = null;
            removeIfNullData = null;
        }
    }
    protected void cleanKeyForEmptyLists(){
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>>> valueTypeWordList;
        Integer keyRemovedTypeWordList;
        ConcurrentHashMap<String, ConcurrentHashMap<Integer, ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>> valueRemovedTypeWordList;
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>> valueHexTagNameLetter;
        String keyRemovedHexTagNameLetter;
        ConcurrentHashMap<Integer, ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>> valueRemovedHexTagNameLetter;
        ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>> valueSubStringLength;
        Integer keyRemovedSubStringLength;
        ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>> valueRemovedSubStringLength;
        ConcurrentHashMap<UUID, ZPITdataWord> dataByHexTagName;
        String keyRemovedHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> valueRemovedHexTagName;
        try{
            for( Map.Entry<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>>> entrySetTypeWord : this.cachedData.entrySet() ){
                
                valueTypeWordList = entrySetTypeWord.getValue();
                for( Map.Entry<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>> entrySetHexTagNameLetter : valueTypeWordList.entrySet() ){
                    
                    valueHexTagNameLetter = entrySetHexTagNameLetter.getValue();
                    for( Map.Entry<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>> entrySetSubStringLength : valueHexTagNameLetter.entrySet() ){
                        
                        valueSubStringLength = entrySetSubStringLength.getValue();
                        for( Map.Entry<String, 
                            ConcurrentHashMap<UUID, ZPITdataWord>> entrySetHexTagName : valueSubStringLength.entrySet() ){
                            
                            dataByHexTagName = entrySetHexTagName.getValue();
                            if( dataByHexTagName.isEmpty() ){
                                
                                keyRemovedHexTagName = entrySetHexTagName.getKey();
                                valueRemovedHexTagName = valueSubStringLength.remove(keyRemovedHexTagName);
                                valueRemovedHexTagName = null;
                                keyRemovedHexTagName = null;
                            }
                        }
                        if( valueSubStringLength.isEmpty() ){
                                
                                keyRemovedSubStringLength = entrySetSubStringLength.getKey();
                                valueRemovedSubStringLength = valueHexTagNameLetter.remove(keyRemovedSubStringLength);
                                valueRemovedSubStringLength = null;
                                keyRemovedSubStringLength = null;
                        }
                    }
                    if( valueHexTagNameLetter.isEmpty() ){
                                
                        keyRemovedHexTagNameLetter = entrySetHexTagNameLetter.getKey();
                        valueRemovedHexTagNameLetter = valueTypeWordList.remove(keyRemovedHexTagNameLetter);
                        valueRemovedHexTagNameLetter = null;
                        keyRemovedHexTagNameLetter = null;
                    }
                }
                if( valueTypeWordList.isEmpty() ){
                                
                    keyRemovedTypeWordList = entrySetTypeWord.getKey();
                    valueRemovedTypeWordList = this.cachedData.remove(keyRemovedTypeWordList);
                    valueRemovedTypeWordList = null;
                    keyRemovedTypeWordList = null;
                }
            }
        }
        finally {
            valueTypeWordList = null;
            valueHexTagNameLetter = null;
            valueSubStringLength = null;
            dataByHexTagName = null;
            valueRemovedTypeWordList = null;
            keyRemovedTypeWordList = null;
            valueRemovedHexTagNameLetter = null;
            keyRemovedHexTagNameLetter = null;
            valueRemovedSubStringLength = null;
            keyRemovedSubStringLength = null;
            valueRemovedHexTagName = null;
            keyRemovedHexTagName = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected Boolean isCacheEmpty(){
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>>> valueTypeWordList;
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>>> valueHexTagNameLetter;
        ConcurrentHashMap<String, 
                ConcurrentHashMap<UUID, ZPITdataWord>> valueSubStringLength;
        ConcurrentHashMap<UUID, ZPITdataWord> dataByHexTagName;
        try{
            for( Map.Entry<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>>> entrySetTypeWord : this.cachedData.entrySet() ){
                
                valueTypeWordList = entrySetTypeWord.getValue();
                for( Map.Entry<String, 
                    ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, 
                    ConcurrentHashMap<UUID, ZPITdataWord>>>> entrySetHexTagNameLetter : valueTypeWordList.entrySet() ){
                    
                    valueHexTagNameLetter = entrySetHexTagNameLetter.getValue();
                    for( Map.Entry<Integer, 
                        ConcurrentHashMap<String, 
                        ConcurrentHashMap<UUID, ZPITdataWord>>> entrySetSubStringLength : valueHexTagNameLetter.entrySet() ){
                        
                        valueSubStringLength = entrySetSubStringLength.getValue();
                        for( Map.Entry<String, 
                            ConcurrentHashMap<UUID, ZPITdataWord>> entrySetHexTagName : valueSubStringLength.entrySet() ){
                            
                            dataByHexTagName = entrySetHexTagName.getValue();
                            if( !dataByHexTagName.isEmpty() ){
                                return Boolean.FALSE;
                            }
                        }
                    }
                }
            }
            return Boolean.TRUE;
        }
        finally {
            valueTypeWordList = null;
            valueHexTagNameLetter = null;
            valueSubStringLength = null;
            dataByHexTagName = null;
        }
    }
    /**
     * 
     * @param dataInputed
     * @return 
     * @throws IllegalArgumentException when inputed data not valid
     */
    private ConcurrentHashMap<UUID, ZPITdataWord> getDataByDataWord(final ZPITdataWord dataInputed){
        ConcurrentHashMap<UUID, ZPITdataWord> dataTypeWordTagNameSubStr;
        ZPITdataWord dataFunc;
        String tagNameFunc;
        String strSubStringFunc;
        Integer typeWordFunc;
        Boolean tdataWordValid;
        try {
            dataFunc = (ZPITdataWord) dataInputed;
            
            tdataWordValid = ThWordHelper.isTdataWordValid(dataFunc);
            if( !tdataWordValid ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " not valid data for get from cache object class " + ZPITdataWord.class.getCanonicalName() 
                        + " object data " + dataFunc.toString());
            }
            tagNameFunc = dataFunc.hexSubString;
            strSubStringFunc = dataFunc.strSubString;
            typeWordFunc = dataFunc.typeWord;
            
            dataTypeWordTagNameSubStr = getDataTypeWordTagNameSubStr(typeWordFunc, strSubStringFunc, tagNameFunc);
            return dataTypeWordTagNameSubStr;
        }
        finally {
            dataFunc = null;
            tagNameFunc = null;
            strSubStringFunc = null;
            typeWordFunc = null;
            dataTypeWordTagNameSubStr = null;
            tdataWordValid = null;
        }
    }
    /**
     * 
     * @param typeWordInput
     * @param strSubStringInput
     * @param tagHexNameInput
     * @return 
     * ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>> (3) - 
 <hexWord (tagFileName), <UUID, ZPITdataWord>>
     */
    private ConcurrentHashMap<UUID, ZPITdataWord> getDataTypeWordTagNameSubStr(
            final Integer typeWordInput,
            final String strSubStringInput,
            final String tagHexNameInput){
        //(1)
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>> valListByTypeWord;
        //(2a)
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>> valListByTagNameCode;
        //(2b)
        ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>> valListBySubStrLength;
        ConcurrentHashMap<UUID, ZPITdataWord> valTagNameListData;
        
        String tagNameFunction;
        String strSubStringFunction;
        Integer typeWordFunction;
        try{
            tagNameFunction = (String) tagHexNameInput;
            strSubStringFunction = (String) strSubStringInput;
            typeWordFunction = (Integer) typeWordInput;
            int strSubStringlength = strSubStringFunction.length();
            int tagNamelength = tagNameFunction.length();
            if( (strSubStringlength * 4) != tagNamelength ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " illegal length of inputed in index string, hexTagName: "
                        + tagNameFunction + " lengthHex: " + tagNameFunction.length()
                        + " strSubString: " + strSubStringFunction + " lengthStr: " + strSubStringFunction.length()
                        + " lengthHex == lengthStr * 4 ");
            }
            if( tagNamelength < 4 ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " illegal length of inputed in index string, hexTagName: "
                        + tagNameFunction + " length: " + tagNameFunction.length()
                        + " < 4 ");
            }
            valListByTypeWord = getListByType(typeWordFunction);
            String substringTagName = tagNameFunction.substring(0, 3);
            valListByTagNameCode = valListByTypeWord.get(substringTagName);
            if( valListByTagNameCode == null ){
                valListByTagNameCode = new ConcurrentHashMap<Integer, 
                                                ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>();
                valListByTypeWord.put(substringTagName, valListByTagNameCode);
            }
            valListBySubStrLength = valListByTagNameCode.get(strSubStringlength);
            if( valListBySubStrLength == null ){
                valListBySubStrLength = new ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>();
                valListByTagNameCode.put(strSubStringlength, valListBySubStrLength);
            }
            valTagNameListData = valListBySubStrLength.get(tagNameFunction);
            if( valTagNameListData == null ){
                valTagNameListData = new ConcurrentHashMap<UUID, ZPITdataWord>();
                valListBySubStrLength.put(tagNameFunction, valTagNameListData);
            }
            return valTagNameListData;
        } finally {
            valListByTypeWord = null;
            valListByTagNameCode = null;
            valListBySubStrLength = null;
            valTagNameListData = null;
            tagNameFunction = null;
            strSubStringFunction = null;
            typeWordFunction = null;
        }
    }
    /**
     * 
     * @param dataInputed
     * @return 
     * @throws IllegalArgumentException when inputed data not valid
     */
    protected ConcurrentHashMap<UUID, ZPITdataWord> pollDataByDataWord(
            final ZPITdataWord dataInputed){
        ConcurrentHashMap<UUID, ZPITdataWord> dataTypeWordTagNameSubStr;
        ZPITdataWord dataFunc;
        String tagNameFunc;
        String strSubStringFunc;
        Integer typeWordFunc;
        Boolean tdataWordValid;
        try {
            dataFunc = (ZPITdataWord) dataInputed;
            tdataWordValid = ThWordHelper.isTdataWordValid(dataFunc);
            if( !tdataWordValid ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " inputed not valid data for poll from cache object class " 
                        + ZPITdataWord.class.getCanonicalName() 
                        + " object data " + dataFunc.toString());
            }
            tagNameFunc = dataFunc.hexSubString;
            strSubStringFunc = dataFunc.strSubString;
            typeWordFunc = dataFunc.typeWord;
            dataTypeWordTagNameSubStr = null;
            try {
                dataTypeWordTagNameSubStr = pollTypeWordTagFileNameData(typeWordFunc, strSubStringFunc, tagNameFunc);
            } catch (IllegalArgumentException exIll) {
                throw new IllegalArgumentException(exIll.getMessage());
            } catch (NullPointerException exNull) {
                throw new NullPointerException(exNull.getMessage());
            }
            if( dataTypeWordTagNameSubStr == null ){
                throw new NullPointerException(ThWordCacheHa.class.getCanonicalName() 
                        + " for word by type " + String.valueOf(typeWordFunc)
                        + " tagName " + tagNameFunc
                        + " subString " + strSubStringFunc
                        + " data in cache is null");
            }
            return dataTypeWordTagNameSubStr;
        }
        finally {
            dataFunc = null;
            tagNameFunc = null;
            strSubStringFunc = null;
            typeWordFunc = null;
            dataTypeWordTagNameSubStr = null;
            tdataWordValid = null;
        }
    }
    /**
     * Remove and return data from cache
     * {@code ConcurrentHashMap<Integer, 
      ConcurrentHashMap<String, 
           ConcurrentHashMap<Integer, 
               ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>>>}
     * {@code <TypeWord,
      <hexTagName.substring(0, 3),
           <subString.length(),
               <hexTagName, --- removed key for list return
                   <data.randomUUID, (ZPITdataWord) data>>>>>}
     * @param typeWord
     * @param strSubString
     * @param tagName
     * 
     * @return 
     * ConcurrentHashMap<String, String> (3) - <hexWord (tagFileName), subString>
     * @throws IllegalArgumentException when data not valid
     * @throws NullPointerException when data in cache for keys not exist
     */
    protected ConcurrentHashMap<UUID, ZPITdataWord> pollTypeWordTagFileNameData(
            final Integer typeWordInput,
            final String strSubStringInput,
            final String tagHexNameInput){
        //(1)
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>> valListByTypeWord;
        //(2a)
        ConcurrentHashMap<Integer, 
                ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>> valListByTagNameCode;
        //(2b)
        ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>> valListBySubStrLength;
        ConcurrentHashMap<UUID, ZPITdataWord> valTagNameListData;
        
        String tagNameFunc;
        String strSubStringFunc;
        Integer typeWordFunc;
        try{
            tagNameFunc = (String) tagHexNameInput;
            strSubStringFunc = (String) strSubStringInput;
            typeWordFunc = (Integer) typeWordInput;
            int strSubStringlength = strSubStringFunc.length();
            int tagNamelength = tagNameFunc.length();
            if( (strSubStringlength * 4) != tagNamelength ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " illegal length of inputed in index string, hexTagName: "
                        + tagNameFunc + " lengthHex: " + tagNameFunc.length()
                        + " strSubString: " + strSubStringFunc + " lengthStr: " + strSubStringFunc.length()
                        + " lengthHex == lengthStr * 4 ");
            }
            if( tagNamelength < 4 ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " illegal length of inputed in index string, hexTagName: "
                        + tagNameFunc + " length: " + tagNameFunc.length()
                        + " < 4 ");
            }
            valListByTypeWord = getListByType(typeWordFunc);
            String substringTagName = tagNameFunc.substring(0, 3);
            valListByTagNameCode = valListByTypeWord.get(substringTagName);
            if( valListByTagNameCode == null ){
                throw new NullPointerException(ThWordCacheHa.class.getCanonicalName() 
                        + " for word by type " + String.valueOf(typeWordFunc)
                        + " tagName " + tagNameFunc
                        + " subString " + strSubStringFunc
                        + " data in cache is null");
            }
            valListBySubStrLength = valListByTagNameCode.get(strSubStringlength);
            if( valListBySubStrLength == null ){
                throw new NullPointerException(ThWordCacheHa.class.getCanonicalName() 
                        + " for word by type " + String.valueOf(typeWordFunc)
                        + " tagName " + tagNameFunc
                        + " subString " + strSubStringFunc
                        + " data in cache is null");
            }
            valTagNameListData = valListBySubStrLength.remove(tagNameFunc);
            if( valTagNameListData == null ){
                throw new NullPointerException(ThWordCacheHa.class.getCanonicalName() 
                        + " for word by type " + String.valueOf(typeWordFunc)
                        + " tagName " + tagNameFunc
                        + " subString " + strSubStringFunc
                        + " data in cache is null");
            }
            return valTagNameListData;
        } finally {
            valListByTypeWord = null;
            valListByTagNameCode = null;
            valListBySubStrLength = null;
            valTagNameListData = null;
            tagNameFunc = null;
            strSubStringFunc = null;
            typeWordFunc = null;
        }
    }
    /**
     * create default or get from lists
     * @param getListByTypeWord
     * @param tagName
     * @return lvl (3)
     */
    /*protected ConcurrentHashMap<String, String> getTagFileNameParams(
            final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> inputedListByTypeWord,
            final String tagName){
        ConcurrentHashMap<String, String> funcListByTagFileName;
        ConcurrentHashMap<String, ConcurrentHashMap<String, String>> funcListByTypeWord;
        try{
            funcListByTypeWord = (ConcurrentHashMap<String, ConcurrentHashMap<String, String>>) inputedListByTypeWord;
            funcListByTagFileName = inputedListByTypeWord.get(tagName);
            if( funcListByTagFileName == null ){
                funcListByTagFileName = new ConcurrentHashMap<String, String>();
                funcListByTypeWord.put(tagName, funcListByTagFileName);

            }
            return funcListByTagFileName;
        } finally {
            funcListByTagFileName = null;
            funcListByTypeWord = null;
        }
    }*/
    /**
     * return list of not limited files from structure
     * @param typeWordOuter
     * @return 
     *   ConcurrentHashMap<String,    - (2a) - tagFileName.substring(0,3)
     *     ConcurrentHashMap<Integer, - (2b) - subString.length                            
     *     ConcurrentHashMap<String, String> - <hexWord (tagFileName), subString>
     */
    private ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>> getListByType(final int typeWordOuter){
        ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>> forListReturn;
        try{
            forListReturn = this.cachedData.get(typeWordOuter);
            if( forListReturn == null ){
                forListReturn = new ConcurrentHashMap<String, 
                ConcurrentHashMap<Integer, 
                    ConcurrentHashMap<String, ConcurrentHashMap<UUID, ZPITdataWord>>>>();
                this.cachedData.put(typeWordOuter, forListReturn);
            }
            return forListReturn;
        } finally {
            forListReturn = null;
        }
    }
    /**
     * 
     * @param typeWord
     * @param tagName
     * @param strSubString
     * @param keysPointsFlow ConcurrentHashMap<String, String>
     *          <ThWordStatusDataFs.hashCode(), recordUUID>
     *          <ThWordStatusName.hashCode(), recordUUID>
     *          <ThWordStatusActivity.hashCode(), recordUUID>
     *          <ThWordStatusDataCache.hashCode(), recordUUID>
     *          <ThWordStatusWorkers.hashCode(), recordUUID>
     * @throws IllegalArgumentException when inputed data not valid
     */
    protected Boolean setDataIntoCacheFlow(
            final ZPITdataWord dataForSet){
        Integer funcTypeWord;
        String funcSubString;
        String funcHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> inputedData;
        ConcurrentHashMap<UUID, ZPITdataWord> typeWordTagFileNameFlowUuids;
        UUID recordUUID;
        ZPITdataWord funcData;
        Boolean tdataWordValid;
        try {
            funcData = (ZPITdataWord) dataForSet;
            tdataWordValid = ThWordHelper.isTdataWordValid(funcData);
            if( !tdataWordValid ){
                throw new IllegalArgumentException(ThWordCacheHa.class.getCanonicalName() 
                        + " inputed not valid data for set into cache object class " 
                        + ZPITdataWord.class.getCanonicalName() 
                        + " object data " + funcData.toString());
            }
            funcTypeWord = (Integer) funcData.typeWord;
            funcSubString = (String) funcData.strSubString;
            funcHexTagName = (String) funcData.hexSubString;
            recordUUID = (UUID) funcData.recordUUID;
            try{
                typeWordTagFileNameFlowUuids = getDataTypeWordTagNameSubStr(
                        funcTypeWord,
                        funcSubString, 
                        funcHexTagName);
            } catch(IllegalArgumentException exSetInCahe) {
                System.err.println(exSetInCahe.getMessage());
                return Boolean.FALSE;
            }
            inputedData = new ConcurrentHashMap<UUID, ZPITdataWord>();
            inputedData.put(recordUUID, funcData);
            
            typeWordTagFileNameFlowUuids.putAll(inputedData);
            return Boolean.TRUE;
        } finally {
            recordUUID = null;
            typeWordTagFileNameFlowUuids = null;
            inputedData = null;
            funcTypeWord = null;
            funcSubString = null;
            funcHexTagName = null;
            funcData = null;
            tdataWordValid = null;
        }
    }
    
    /**
     * @todo recode for check inputed data, call
     * @param typeWord
     * @param tagName
     * @param strSubString
     * @param outerInputedData - readed list data for insert into cacheReaded
     * @param keysPointsFlow ConcurrentHashMap<String, String>
     *          <ThWordStatusDataFs.hashCode(), recordUUID>
     *          <ThWordStatusName.hashCode(), recordUUID>
     *          <ThWordStatusActivity.hashCode(), recordUUID>
     *          <ThWordStatusDataCache.hashCode(), recordUUID>
     *          <ThWordStatusWorkers.hashCode(), recordUUID>
     */
    protected Boolean addAllDataIntoCache(
            final ConcurrentHashMap<UUID, ZPITdataWord> outerInputedData){
        ConcurrentHashMap<UUID, ZPITdataWord> inputedData;
        ZPITdataWord removeElementForAdd;
        try {
            inputedData = (ConcurrentHashMap<UUID, ZPITdataWord>) outerInputedData;
            if( inputedData == null ) {
                return Boolean.FALSE;
            }
            if( inputedData.isEmpty() ){
                return Boolean.FALSE;
            }
            for(Map.Entry<UUID, ZPITdataWord> itemsForAdd : inputedData.entrySet()){
                removeElementForAdd = inputedData.remove(itemsForAdd.getKey());
                if( removeElementForAdd != null){
                    setDataIntoCacheFlow((ZPITdataWord) itemsForAdd.getValue());
                }
            }
            return Boolean.TRUE;
        } finally {
            inputedData = null;
        }
    }
    /**
     * @param typeWord
     * @param tagName
     * @param strSubString
     * @param keysPointsFlow ConcurrentHashMap<String, String>
     *          <ThWordStatusDataFs.hashCode(), recordUUID>
     *          <ThWordStatusName.hashCode(), recordUUID>
     *          <ThWordStatusActivity.hashCode(), recordUUID>
     *          <ThWordStatusDataCache.hashCode(), recordUUID>
     *          <ThWordStatusWorkers.hashCode(), recordUUID>
     */
    protected Boolean isCacheHasData(
            final Integer typeWord, 
            final String tagName, 
            final String strSubString){
        Integer funcTypeWord;
        String funcSubString;
        String funcHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> typeWordTagFileNameFlowUuids;
        try {
            funcTypeWord = (Integer) typeWord;
            funcSubString = (String) strSubString;
            funcHexTagName = (String) tagName;
            try{
                typeWordTagFileNameFlowUuids = (ConcurrentHashMap<UUID, ZPITdataWord>) getDataTypeWordTagNameSubStr(
                        funcTypeWord,
                        funcSubString,
                        funcHexTagName);
            } catch(IllegalArgumentException exSetInCahe) {
                System.err.println(exSetInCahe.getMessage());
                return Boolean.FALSE;
            }
            if( typeWordTagFileNameFlowUuids == null ) {
                return Boolean.FALSE;
            }
            
            if( typeWordTagFileNameFlowUuids.isEmpty() ){
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
        } finally {
            typeWordTagFileNameFlowUuids = null;
            funcTypeWord = null;
            funcSubString = null;
            funcHexTagName = null;
        }
    }
    /**
     * 
     * @param typeWord
     * @param tagName
     * @param strSubString
     * @param keysPointsFlow ConcurrentHashMap<String, String>
     *          <ThWordStatusDataFs.hashCode(), recordUUID>
     *          <ThWordStatusName.hashCode(), recordUUID>
     *          <ThWordStatusActivity.hashCode(), recordUUID>
     *          <ThWordStatusDataCache.hashCode(), recordUUID>
     *          <ThWordStatusWorkers.hashCode(), recordUUID>
     */
    protected Integer sizeDataInCache(
            final Integer typeWord, 
            final String tagName, 
            final String strSubString){
        Integer funcTypeWord;
        String funcSubString;
        String funcHexTagName;
        ConcurrentHashMap<UUID, ZPITdataWord> typeWordTagFileNameFlowUuids;
        Integer returnedValue;
        try {
            funcTypeWord = (Integer) typeWord;
            funcSubString = (String) strSubString;
            funcHexTagName = (String) tagName;
            returnedValue = 0;
            try{
                typeWordTagFileNameFlowUuids = getDataTypeWordTagNameSubStr(
                        funcTypeWord,
                        funcSubString,
                        funcHexTagName);
            } catch(IllegalArgumentException exSetInCahe) {
                System.err.println(exSetInCahe.getMessage());
                exSetInCahe.printStackTrace();
                return returnedValue;
            }
            returnedValue = (int) typeWordTagFileNameFlowUuids.size();
            return returnedValue;
        } finally {
            typeWordTagFileNameFlowUuids = null;
            funcTypeWord = null;
            funcSubString = null;
            funcHexTagName = null;
            returnedValue = null;
        }
    }
    /*protected void printCacheData(){
        for( Map.Entry<Integer,ConcurrentHashMap<String, ConcurrentHashMap<Integer, ConcurrentHashMap<String, String>>>> cachedTypes : this.cachedData.entrySet()){
            for(Map.Entry<String, ConcurrentHashMap<Integer, ConcurrentHashMap<String, String>>> hexSubByte : cachedTypes.getValue().entrySet()){
                for(Map.Entry<Integer, ConcurrentHashMap<String, String>> itemLength : hexSubByte.getValue().entrySet()){
                    for(Map.Entry<String, String> itemData : itemLength.getValue().entrySet()){
                        System.out.println(" -  -  -  -  -   -      -     -     -       -    -   -  hexName " 
                                + itemData.getKey() 
                                + " subStr " 
                                + itemData.getValue());
                    }
                }
            }
        
        }
    }*/
    /**
     * isCacheEmpty
     */
    /**
     * return CacheDataForFinishedWrite by bus type
     */
}
