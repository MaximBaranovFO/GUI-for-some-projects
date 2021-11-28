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

/**
 * ThStorageWordStatusDataCache
 * countTMP   - (3a.4) - Integer currentInCache 
 *  - records count, need when 
 *                get job for write for example:
 *                fromJobToWriteDataSize + countRecordsOnFileSystem + 
 *                currentInCache = resultNowData < indexSystemLimitOnStorage
 *                => readFormFileSystem -> summaryReadedCacheFromJob ->
 *                setNew VolumeNumber -> setNew in countRecordsOnFileSystem
 *                -> sendJobForWriter about writeMove -> setNew NewFileName
 *                -> writeToFileSystem in CurrentFileName -> moveTo 
 *                NewFileName (data from sendedJobForWriter)
 *                = resultNowData > indexSystemLimitOnStorage => 
 *                I. - readFromFileSystem, summ...
 *                II. - resultNowData - indexSystemLimitOnStorage -> toCache
 *                III. - sendJobForWriter, update HashMap data ...
 *                = resultNowData == indexSystemLimitOnStorage =>
 *                I. - readFromFileSystem...
 *                II. - resultNowData
 *                III. - sendJobForWriter, update HashMap data ...
 *     - (3a.4) - Integer currentInCacheReaded set when data readed from
 *                  storage index data file
 *     - (3a.4) - Integer addNeedToFileSystemLimit 
 * - exist in data file
 *                records size => indexSystemLimitOnStorage - sizeFormFileName
 *                @todo when data read need calculate name and readed data size
 *     - (3a.4) - Integer indexSystemLimitOnStorage 
 * - limit from constants
 * 
 * @author wladimirowichbiaran
 */
public class ZPIThWordStatusDataCache {
    private final Long timeCreation;
    private final UUID objectLabel;
    /**
     * ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<Integer, Long>>
     * <keyPointFlowDataCache, <lastAccessNanotime.hashCode(), Long Value>>
     *                        <countDataUseIterationsSummary.hashCode(), Long Value>
     */
    private ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<Integer, Integer>> poolStatusDataCache;
    
    public ZPIThWordStatusDataCache(){
        this.timeCreation = System.nanoTime();
        this.objectLabel = UUID.randomUUID();
        this.poolStatusDataCache = new ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<Integer, Integer>>();
    }
    /**
     * 
     * @param keyPointFlowActivity
     * @return 
     * @throws IllegalStateException
     */
    private ConcurrentSkipListMap<Integer, Integer> getStatusDataCacheForKeyPointFlow(final UUID keyPointFlowDataCache){
        UUID inputedVal;
        ConcurrentSkipListMap<Integer, Integer> getStatusDataCacheFormPool;
        try{
            inputedVal = (UUID) keyPointFlowDataCache;
            getStatusDataCacheFormPool = this.poolStatusDataCache.get(inputedVal);
            if( getStatusDataCacheFormPool == null ){
                throw new IllegalStateException(ZPIThWordStatusDataCache.class.getCanonicalName()
                + " not exist record in list for "
                + inputedVal.toString() + " key point flow");
            }
            /**
             * @todo
             * update access time for point flow if in one point of all flow get
             * increment 
             */
            return getStatusDataCacheFormPool;
        } finally {
            inputedVal = null;
            getStatusDataCacheFormPool = null;
        }
    }
    /**
     * 
     * @param keyPointFlowDataCache
     * @return true if found and delete data
     */
    protected Boolean removeStatusDataCacheForKeyPointFlow(final UUID keyPointFlowDataCache){
        UUID inputedVal;
        ConcurrentSkipListMap<Integer, Integer> getRemovedStatusDataCacheFormPool;
        try{
            inputedVal = (UUID) keyPointFlowDataCache;
            getRemovedStatusDataCacheFormPool = (ConcurrentSkipListMap<Integer, Integer>) this.poolStatusDataCache.remove(inputedVal);
            if( getRemovedStatusDataCacheFormPool == null ){
                return Boolean.FALSE;
            }
            for( Map.Entry<Integer, Integer> itemOfPoint : getRemovedStatusDataCacheFormPool.entrySet() ){
                Integer removedKey = itemOfPoint.getKey();
                Integer removedVal = getRemovedStatusDataCacheFormPool.remove(removedKey);
                removedVal = null;
                removedKey = null;
            }
            getRemovedStatusDataCacheFormPool = null;
            return Boolean.TRUE;
        } finally {
            inputedVal = null;
            getRemovedStatusDataCacheFormPool = null;
        }
    }
    /**
     * not exist bus
     * @param typeWordByDetectedCodePoint
     * @return 
     */
    protected Boolean isStatusDataCacheNotExist(final UUID keyPointFlowDataCache){
        UUID inputedVal;
        try{
            inputedVal = (UUID) keyPointFlowDataCache;
            if( !this.poolStatusDataCache.containsKey(inputedVal) ){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } finally {
            inputedVal = null;
        }
    }
    /**
     * <ul>
     * <li>0 - currentInCache
     * <li>1 - currentInCacheReaded
     * <li>2 - addNeedToFileSystemLimit
     * <li>3 - indexSystemLimitOnStorage
     * <li>4 - inCacheVolumesCount
     * </ul>
     * @param keyPointFlowDataCacheInputed
     * @param paramNumber
     * @return 
     * @throw IllegalStateException is keyPointFlowDataCacheInputed not exist
     */
    protected Integer getValueForFlowPointByNumber(
            final UUID keyPointFlowDataFsInputed, 
            final Integer paramNumber){
        ConcurrentSkipListMap<Integer, Integer> getListValues;
        Integer returnedParamValue;
        UUID keyPointFlowDataCacheFunc;
        try{
            keyPointFlowDataCacheFunc = (UUID) keyPointFlowDataFsInputed;
            returnedParamValue = (Integer) paramNumber;
            if( isStatusDataCacheNotExist(keyPointFlowDataCacheFunc) ){
                throw new IllegalStateException(ZPIThWordStatusDataFs.class.getCanonicalName()
                        + " not exist values for UUID "
                        + keyPointFlowDataCacheFunc.toString()
                );
            }
            getListValues = this.poolStatusDataCache.get(keyPointFlowDataCacheFunc);
            Integer paramCodeByNumber = getParamCodeByNumber(returnedParamValue);
            Integer getParamForReturn = getListValues.get(paramCodeByNumber);
            return new Integer(getParamForReturn.intValue());
        } finally {
            keyPointFlowDataCacheFunc = null;
            getListValues = null;
        }
    }
    /**
     * create new structure for UUID, and set all values to 0 (zero)
     * @param keyPointFlowDataCacheInputed
     * @see setInitParamDataCache()
     */
    protected void createStructureParamsDataCache(
                        final UUID keyPointFlowDataCacheInputed){
        ConcurrentSkipListMap<Integer, Integer> countDataCache;
        UUID keyPointFlowDataCacheFunc;
        try{
            keyPointFlowDataCacheFunc = (UUID) keyPointFlowDataCacheInputed;
            if( isStatusDataCacheNotExist(keyPointFlowDataCacheFunc) ){
                countDataCache = setInitParamDataCache();
                this.poolStatusDataCache.put(keyPointFlowDataCacheFunc, countDataCache);
            }
        } finally {
            countDataCache = null;
            keyPointFlowDataCacheFunc = null;
        }
    }
    
    /**
     * 
     * @return 
     */
    private ConcurrentSkipListMap<Integer, Integer> setInitParamDataCache(){
        ConcurrentSkipListMap<Integer, Integer> returnedHashMap;
        Integer paramCodeByNumber;
        Integer countParamsDataCacheForSet;
        Integer idx;
        try {
            returnedHashMap = new ConcurrentSkipListMap<Integer, Integer>();
            countParamsDataCacheForSet = getParamCount();
            for(idx = 0; idx < countParamsDataCacheForSet; idx++ ){
                paramCodeByNumber = getParamCodeByNumber(idx);
                returnedHashMap.put(paramCodeByNumber, 0);
            }
            paramCodeByNumber = getParamCodeByNumber(2);
            returnedHashMap.put(paramCodeByNumber, ZPIAppConstants.STORAGE_WORD_RECORDS_COUNT_LIMIT);
            paramCodeByNumber = getParamCodeByNumber(3);
            returnedHashMap.put(paramCodeByNumber, ZPIAppConstants.STORAGE_WORD_RECORDS_COUNT_LIMIT);
            return returnedHashMap;
        } finally {
            idx = null;
            paramCodeByNumber = null;
            countParamsDataCacheForSet = null;
            returnedHashMap = null;
        }
    }
    /**
     *  
     * @param changedKeyPointFlowDataCache
     * @param paramNumber
     * <ul>
     * <li>0 - currentInCache
     * <li>1 - currentInCacheReaded
     * <li>2 - addNeedToFileSystemLimit
     * <li>3 - indexSystemLimitOnStorage
     * <li>4 - inCacheVolumesCount
     * </ul>
     * @param changedVal 
     * 
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code changedKeyPointFlowDataCache < 0 (zero) }
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#getParamCodeByNumber(int) }
     * @throws IllegalArgumentException if count of parameters or his
     * names not equal concept  in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     * @throws NullPointerException when inputed value is null in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     */
    protected void changeParamValByNumber(final UUID changedKeyPointFlowDataCache, final Integer paramNumber, final Integer changedVal){
        UUID changedKeyPointFlowDataCacheFunc;
        Integer paramNumberFunc;
        Integer changedValFunc;
        Integer paramCodeByNumber;
        ConcurrentSkipListMap<Integer, Integer> fromCurrentFlow;
        try{
            changedKeyPointFlowDataCacheFunc = (UUID) changedKeyPointFlowDataCache;
            validateCountParams(changedKeyPointFlowDataCacheFunc);
            paramNumberFunc = (Integer) paramNumber;
            changedValFunc = (Integer) changedVal;
            fromCurrentFlow = (ConcurrentSkipListMap<Integer, Integer>) this.poolStatusDataCache.get(changedKeyPointFlowDataCacheFunc);
            paramCodeByNumber = (Integer) getParamCodeByNumber(paramNumberFunc);
            fromCurrentFlow.put(paramCodeByNumber, changedValFunc);
            this.poolStatusDataCache.put(changedKeyPointFlowDataCacheFunc, fromCurrentFlow);
        } finally {
            changedKeyPointFlowDataCacheFunc = null;
            paramNumberFunc = null;
            changedValFunc = null;
            paramCodeByNumber = null;
            fromCurrentFlow = null;
        }
    }
    /**
     * 
     * @param changedKeyPointFlowDataCache 
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code changedKeyPointFlowDataCache < 0 (zero) }
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#getParamCodeByNumber(int) }
     * @throws IllegalArgumentException if count of parameters or his
     * names not equal concept  in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     * @throws NullPointerException when inputed value is null in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     */
    protected void incrementVolumeCountInCache(final UUID changedKeyPointFlowDataCache){
        UUID changedKeyPointFlowDataCacheFunc;
        Integer paramCodeByNumber;
        ConcurrentSkipListMap<Integer, Integer> fromCurrentFlow;
        Integer incrementalVolumeCount;
        try{
            changedKeyPointFlowDataCacheFunc = (UUID) changedKeyPointFlowDataCache;
            validateCountParams(changedKeyPointFlowDataCacheFunc);
            fromCurrentFlow = (ConcurrentSkipListMap<Integer, Integer>) this.poolStatusDataCache.get(changedKeyPointFlowDataCacheFunc);
            paramCodeByNumber = (Integer) getParamCodeByNumber(4);
            incrementalVolumeCount = fromCurrentFlow.get(paramCodeByNumber);
            incrementalVolumeCount++;
            fromCurrentFlow.put(paramCodeByNumber, incrementalVolumeCount);
            this.poolStatusDataCache.put(changedKeyPointFlowDataCacheFunc, fromCurrentFlow);
        } finally {
            changedKeyPointFlowDataCacheFunc = null;
            paramCodeByNumber = null;
            fromCurrentFlow = null;
            incrementalVolumeCount = null;
        }
    }
    /**
     * 
     * @param changedKeyPointFlowDataCache 
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code changedKeyPointFlowDataCache < 0 (zero) }
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#getParamCodeByNumber(int) }
     * @throws IllegalArgumentException if count of parameters or his
     * names not equal concept  in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     * @throws NullPointerException when inputed value is null in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     */
    protected void incrementRecordsCountInCache(final UUID changedKeyPointFlowDataCache){
        UUID changedKeyPointFlowDataCacheFunc;
        Integer paramCodeByNumber;
        ConcurrentSkipListMap<Integer, Integer> fromCurrentFlow;
        Integer incrementalRecordCountInCache;
        try{
            changedKeyPointFlowDataCacheFunc = (UUID) changedKeyPointFlowDataCache;
            validateCountParams(changedKeyPointFlowDataCacheFunc);
            fromCurrentFlow = (ConcurrentSkipListMap<Integer, Integer>) this.poolStatusDataCache.get(changedKeyPointFlowDataCacheFunc);
            paramCodeByNumber = (Integer) getParamCodeByNumber(0);
            incrementalRecordCountInCache = fromCurrentFlow.get(paramCodeByNumber);
            incrementalRecordCountInCache++;
            fromCurrentFlow.put(paramCodeByNumber, incrementalRecordCountInCache);
            this.poolStatusDataCache.put(changedKeyPointFlowDataCacheFunc, fromCurrentFlow);
        } finally {
            changedKeyPointFlowDataCacheFunc = null;
            paramCodeByNumber = null;
            fromCurrentFlow = null;
            incrementalRecordCountInCache = null;
        }
    }
    /**
     * 
     * @param changedKeyPointFlowDataCache 
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code changedKeyPointFlowDataCache < 0 (zero) }
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#getParamCodeByNumber(int) }
     * @throws IllegalArgumentException if count of parameters or his
     * names not equal concept  in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     * @throws NullPointerException when inputed value is null in used 
     * {@link ru.newcontrol.ncfv.ThWordStatusDataCache#validateCountParams(java.util.UUID) }
     */
    protected void incrementRecordsCountInCacheReaded(final UUID changedKeyPointFlowDataCache){
        UUID changedKeyPointFlowDataCacheFunc;
        Integer paramCodeByNumber;
        ConcurrentSkipListMap<Integer, Integer> fromCurrentFlow;
        Integer incrementalRecordCountInReadedCache;
        try{
            changedKeyPointFlowDataCacheFunc = (UUID) changedKeyPointFlowDataCache;
            validateCountParams(changedKeyPointFlowDataCacheFunc);
            fromCurrentFlow = (ConcurrentSkipListMap<Integer, Integer>) this.poolStatusDataCache.get(changedKeyPointFlowDataCacheFunc);
            paramCodeByNumber = (Integer) getParamCodeByNumber(1);
            incrementalRecordCountInReadedCache = fromCurrentFlow.get(paramCodeByNumber);
            incrementalRecordCountInReadedCache++;
            fromCurrentFlow.put(paramCodeByNumber, incrementalRecordCountInReadedCache);
            this.poolStatusDataCache.put(changedKeyPointFlowDataCacheFunc, fromCurrentFlow);
        } finally {
            changedKeyPointFlowDataCacheFunc = null;
            paramCodeByNumber = null;
            fromCurrentFlow = null;
            incrementalRecordCountInReadedCache = null;
        }
    }
    /**
     * <ul>
     * <li>0 - currentInCache
     * <li>1 - currentInCacheReaded
     * <li>2 - addNeedToFileSystemLimit
     * <li>3 - indexSystemLimitOnStorage
     * <li>4 - inCacheVolumesCount
     * </ul>
     * @return 
     */
    private String[] getParamNames(){
        String[] namesForReturn;
        try {
            namesForReturn = new String[] {
                "currentInCache",
                "currentInCacheReaded",
                "addNeedToFileSystemLimit",
                "indexSystemLimitOnStorage",
                "inCacheVolumesCount"
            };
            return namesForReturn;
        } finally {
            namesForReturn = null;
        }
    }
    /**
     * Return code of parameter by his number, calculeted from some fileds
     * @param numParam
     * <ul>
     * <li>0 - currentInCache
     * <li>1 - currentInCacheReaded
     * <li>2 - addNeedToFileSystemLimit
     * <li>3 - indexSystemLimitOnStorage
     * <li>4 - inCacheVolumesCount
     * </ul>
     * @return hashCode for Parameter by his number
     * @see getParamNames()
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code numParam < 0 (zero) }
     */
    private Integer getParamCodeByNumber(int numParam){
        String[] paramNames;
        Integer codeForParameter;
        try {
            paramNames = getParamNames();
            if( numParam < 0 ){
                throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                                + " parameters of flow statusDataCache in StorageWord is not valid, "
                                + " negative index sended, 0 (zero) > " + numParam + ", count parameters: " 
                                + paramNames.length);
            }
            if( numParam > (paramNames.length - 1) ){
                throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                                + " parameters of flow statusDataCache in StorageWord is not valid, "
                                + "count parameters: " 
                                + paramNames.length 
                                + ", need for return " + numParam);
            } 
            codeForParameter = paramNames[numParam]
                    .concat(String.valueOf(this.timeCreation))
                    .concat(this.objectLabel.toString()).hashCode();
            return codeForParameter;
        } finally {
            paramNames = null;
            codeForParameter = null;
        }
    }
    /**
     * Count records (array.length) returned from {@link #getParamNames }
     * @return 
     */
    private int getParamCount(){
        String[] paramNames;
        try {
            paramNames = getParamNames();
            return paramNames.length;
        } finally {
            paramNames = null;
        }
    }
    /**
     * 
     * @param numParam
     * <ul>
     * <li>0 - currentInCache
     * <li>1 - currentInCacheReaded
     * <li>2 - addNeedToFileSystemLimit
     * <li>3 - indexSystemLimitOnStorage
     * <li>4 - inCacheVolumesCount
     * </ul>
     * @return name of param by his number
     * @throws IllegalArgumentException when inputed number of parameter
     * out of bounds or {@code numParam < 0 (zero) }
     */
    private String getParamNameByNumber(int numParam){
        String[] paramNames;
        String paramName;
        try {
            paramNames = getParamNames();
            if( numParam < 0 ){
                throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                                + " parameters of flow statusDataCache in StorageWord is not valid, "
                                + " negative index sended, 0 (zero) > " + numParam + ", count parameters: " 
                                + paramNames.length);
            }
            if( numParam > (paramNames.length - 1) ){
                throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                                + " parameters of flow statusDataCache in StorageWord is not valid, "
                                + "count parameters: " 
                                + paramNames.length 
                                + ", need for return " + numParam);
            } 
            paramName = new String(paramNames[numParam]);
            return paramName;
        } finally {
            paramNames = null;
            paramName = null;
        }
    }
    /**
     * 
     * @param keyPointFlowDataCache
     * @throws IllegalArgumentException if count of parameters or his
     * names not equal concept
     * @throws NullPointerException when inputed value is null
     */
    
    protected void validateCountParams(final UUID keyPointFlowDataCache){
        UUID keyPointFlowDataCacheFunc;
        ConcurrentSkipListMap<Integer, Integer> statusDataCacheForKeyPointFlow;
        Integer sizeRec;
        Integer paramCount;
        Integer idxParam;
        Integer paramCodeByNumber;
        String paramNameByNumber;
        
        try {
            keyPointFlowDataCacheFunc = (UUID) keyPointFlowDataCache;
            if( keyPointFlowDataCacheFunc == null ){
                throw new NullPointerException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                        + " need point flow uuid, argument for validate is null");
            }
            if( !isStatusDataCacheNotExist(keyPointFlowDataCacheFunc) ){
                
                statusDataCacheForKeyPointFlow = (ConcurrentSkipListMap<Integer, Integer>) getStatusDataCacheForKeyPointFlow(keyPointFlowDataCacheFunc);
                sizeRec = (Integer) statusDataCacheForKeyPointFlow.size();
                paramCount = (Integer) getParamCount();
                if( sizeRec != paramCount ){
                    
                    throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                            + " parameters of flow statusDataCache in Word is not valid, "
                            + "count records " + sizeRec + " not equal " + paramCount);
                }
                
                for(idxParam = 0; idxParam < paramCount; idxParam++ ){
                    
                    paramCodeByNumber = getParamCodeByNumber(idxParam);
                    if( !statusDataCacheForKeyPointFlow.containsKey(paramCodeByNumber) ){
                        
                        paramNameByNumber = getParamNameByNumber(idxParam);
                        throw new IllegalArgumentException(ZPIThWordStatusDataCache.class.getCanonicalName() 
                            + " parameter "
                            + " for name: " + paramNameByNumber
                            + " in inputed data for set into flow statusDataCache not exist");
                    }
                }
            }
            
        } finally {
            sizeRec = null;
            paramCount = null;
            idxParam = null;
            statusDataCacheForKeyPointFlow = null;
            keyPointFlowDataCacheFunc = null;
            paramCodeByNumber = null;
            paramNameByNumber = null;
        }
    }
}
