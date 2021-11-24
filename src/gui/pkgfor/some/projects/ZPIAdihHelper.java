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

import java.util.UUID;

/**
 * Adih
 * <ul>
 * <li>Automated
 * <li>data
 * <li>indexing
 * <li>helper Helper for set and get simply variables and constants
 * </ul>
 * @author wladimirowichbiaran
 */
public class ZPIAdihHelper {
    /**
     * <ul>
     * <li>   0 -   UserHome
     * <li>   1 -   ClassPathApplicationDirectory
     * <li>   2 -   ncidxfvSubDirIndex
     * <li>   3 -   di-indexDirList
     * <li>   4 -   t-indexTempData
     * <li>   5 -   j-indexJournal
     * <li>   6 -   fl-indexFileList
     *              
     * <li>   7 -   ft-indexFileType
     * <li>   8 -   fh-indexFileHash
     * <li>   9 -   fx-indexFileExist
     * 
     * <li>  10 -   w-indexWord
     * <li>  11 -   sw-indexStorageWord
     * <li>  12 -   lw-indexLongWordList
     * <li>  13 -   ln-indexLongWordData
     * </ul>
     * @return 
     */
    protected static String[] getStoragesNames(){
        String[] namesForReturn = new String[] {};
        try {
            namesForReturn = null;
            namesForReturn = new String[] {
                "UserHome",
                "ClassPathApplicationDirectory",
                new String(AppFileNamesConstants.DIR_IDX).concat("SubDirIndex"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST).concat("indexDirList"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_TMP).concat("indexTempData"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_JOURNAL).concat("indexJournal"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_LIST).concat("indexFileList"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_TYPE).concat("indexFileType"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_HASH).concat("indexFileHash"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_EXIST).concat("indexFileExist"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_WORD).concat("indexWord"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD).concat("indexStorageWord"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_LIST).concat("indexLongWordList"),
                new String(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_DATA).concat("indexLongWordData"),
            };
            return namesForReturn;
        } finally {
            AdihUtilization.utilizeStringValues(namesForReturn);
            namesForReturn = null;
        }
    }
    /**
     * return from {@link AdilHelper#getParamNames() AdilHelper.getParamNames()}
     * @return 
     */
    protected static String[] getProcessNames(){
        return AdilHelper.getParamNames();
    }
    /**
     * <ul>
     * <li>   0 -   Start - - - not released
     * <li>   1 -   Stop - - - not released
     * <li>   2 -   PauseFromUser - - - not released
     * <li>   3 -   Cancel - - - not released
     * <li>   4 -   Status - - - not released
     * <li>   5 -   Statistic - - - not released
     * <li>   6 -   ReinitVariables - - - not released
     *              
     * <li>   7 -   ReinitDataBus - - - not released
     * <li>   8 -   ReinitDataBusInput - - - not released
     * <li>   9 -   ReinitDataBusOutput - - - not released
     * 
     * <li>  10 -   ReinitSelf - - - not released
     * <li>  11 -   ClaculatedSlowStart - - - not released
     * <li>  12 -   ClaculatedSlowEnd - - - not released
     * <li>  13 -   UserSlowStart - - - not released
     * <li>  14 -   UserSlowEnd - - - not released
     * </ul>
     * @return 
     */
    protected static String[] getCommandNames(){
        String[] namesForReturn = new String[] {};
        try {
            namesForReturn = null;
            namesForReturn = new String[] {
                "Start",
                "Stop",
                "SetPauseFromUser",
                "CancelPauseFromUser",
                "Status",
                "Statistic",
                "ReinitVariables",
                "ReinitDataBus",
                "ReinitDataBusInput",
                "ReinitDataBusOutput",
                "ReinitSelf",
                "ClaculatedSlowStart",
                "ClaculatedSlowEnd",
                "UserSlowStart",
                "UserSlowEnd",
            };
            return namesForReturn;
        } finally {
            AdihUtilization.utilizeStringValues(namesForReturn);
            namesForReturn = null;
        }
    }
    /**
     * <ul>
     * <li>   0 -   waitBus
     * <li>   1 -   doBus
     * <li>   2 -   readyBus
     * </ul>
     * @return 
     */
    protected static String[] getBusTypeNames(){
        String[] namesForReturn = new String[] {};
        try {
            namesForReturn = null;
            namesForReturn = new String[] {
                "waitBus",
                "doBus",
                "readyBus",
            };
            return namesForReturn;
        } finally {
            AdihUtilization.utilizeStringValues(namesForReturn);
            namesForReturn = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getSubDirIndex(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.DIR_IDX);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixDirList(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_DIR_LIST);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixTempData(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_TMP);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixJournal(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_JOURNAL);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixFileList(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_LIST);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixFileType(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_TYPE);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixFileHash(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_HASH);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixFileExist(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_FILE_EXIST);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixWord(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_WORD);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixWordStorage(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_STORAGE_WORD);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixWordLong(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_LIST);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * 
     * @return 
     */
    protected static String getPrefixWordLongNames(){
        String forReturnPrefix = new String();
        try {
            forReturnPrefix = new String(AppFileNamesConstants.FILE_INDEX_PREFIX_LONG_WORD_DATA);
            return forReturnPrefix;
        } finally {
            AdihUtilization.utilizeStringValues(new String[]{forReturnPrefix});
            forReturnPrefix = null;
        }
    }
    /**
     * <ul>
     * <li>   2 -   ncidxfv
     * <li>   3 -   di-
     * <li>   4 -   t-
     * <li>   5 -   j-
     * <li>   6 -   fl-
     *              
     * <li>   7 -   ft-
     * <li>   8 -   fh-
     * <li>   9 -   fx-
     * 
     * <li>  10 -   w-
     * <li>  11 -   sw-
     * <li>  12 -   lw-
     * <li>  13 -   ln-
     * </ul>
     * @param prefixIndex
     * @return Empty String if 2 < prefixIndex > 13 or prefix
     */
    protected static String getPrefixStorageByNumber(Integer prefixIndex){
        switch (prefixIndex) {
            case 2:
                return getSubDirIndex();
            case 3:
                return getPrefixDirList();
            case 4:
                return getPrefixTempData();
            case 5:
                return getPrefixJournal();
            case 6:
                return getPrefixFileList();
            case 7:
                return getPrefixFileType();
            case 8:
                return getPrefixFileHash();
            case 9:
                return getPrefixFileExist();
            case 10:
                return getPrefixWord();
            case 11:
                return getPrefixWordStorage();
            case 12:
                return getPrefixWordLong();
            case 13:
                return getPrefixWordLongNames();
            }
            return new String();
    }
    /**
     * 
     * @param workerInputed
     * @return 
     */
    protected static UUID getUuidWorkerFromName(AdihTemplateThread workerInputed){
        if( workerInputed == null ){
            return null;
        }
        try {
            return UUID.fromString(workerInputed.getName());
        } catch (IllegalArgumentException exIll){
            System.err.println(AdihHelper.class.getCanonicalName() 
                    + " can`t detect worker name for object "
                    + workerInputed.toString() + ", reason " 
                    + exIll.getMessage());
            exIll = null;
        }
        UUID returnedValue = UUID.randomUUID();
        try {
            try {
                workerInputed.setName(returnedValue.toString());
                return returnedValue;
            } catch(SecurityException exSec) {
                System.err.println(AdihHelper.class.getCanonicalName() 
                    + " can`t set worker name for object "
                    + workerInputed.toString() + ", reason " 
                    + exSec.getMessage());
                exSec = null;
            }
            return null;
        } finally {
            returnedValue = null;
        }
    }
}