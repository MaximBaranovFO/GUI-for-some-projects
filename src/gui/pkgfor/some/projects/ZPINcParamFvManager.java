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

import java.io.File;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcParamFvManager {
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getIndexWorkSubDirFilesList() }
     * <li>{@link ru.newcontrol.ncfv.NcIdxFileManager#getTmpIdsFile() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#NcManageCfg() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvWriter#writeDataInWorkCfg(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkInIndexFolderContent() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getCurrentWorkCfg() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     * @param inWorkCfg
     * @return 
     */
    protected static boolean isNcParamFvDataEmpty(ZPINcParamFv inWorkCfg){
        return (inWorkCfg.indexPath.length() == 0
        && inWorkCfg.keywordsOutOfSearch.length() == 0
        && inWorkCfg.keywordsInSearch.length() == 0
        && inWorkCfg.dirOutOfIndex.length() == 0
        && inWorkCfg.dirInIndex.length() == 0
        && inWorkCfg.diskUserAlias.isEmpty()
        && inWorkCfg.strHexMD5.length() == 0
        && inWorkCfg.strHexSHA1.length() == 0
        && inWorkCfg.strHexSHA256.length() == 0
        && inWorkCfg.strHexSHA512.length() == 0
        && inWorkCfg.tmIndexSubDirs.isEmpty());
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvManager#ncParamFvDataOutPut(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvManager#checkToWrite(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvManager#checkFromRead(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvWriter#writeDataInWorkCfg(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     * @param inWorkCfg
     * @return 
     */
    protected static boolean isNcParamFvDataHashTrue(ZPINcParamFv inWorkCfg){
        int calcHash = (""
            + inWorkCfg.indexPath
            + inWorkCfg.keywordsOutOfSearch
            + inWorkCfg.keywordsInSearch
            + inWorkCfg.dirOutOfIndex
            + inWorkCfg.diskUserAlias.hashCode()
            + inWorkCfg.strHexMD5
            + inWorkCfg.strHexSHA1
            + inWorkCfg.strHexSHA256
            + inWorkCfg.strHexSHA512
            + inWorkCfg.tmIndexSubDirs.hashCode()
            + inWorkCfg.recordTime).hashCode();
        boolean boolCompareResult = inWorkCfg.recordHash == calcHash;
        
        if( !boolCompareResult ){
            String strOut = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrServiceMsg.HASH_CALC.getStr()
                + calcHash
                + ZPINcStrServiceMsg.TAB.getStr()
                + ZPINcStrServiceMsg.HASH_RECORD.getStr()
                + inWorkCfg.recordHash
                + ZPINcStrServiceMsg.TAB.getStr()
                + ZPINcStrServiceMsg.RESULT.getStr() + boolCompareResult;
            ZPINcAppHelper.outMessage(strOut);
        }
        
        
        return boolCompareResult;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcPreRunFileViewer#getUpdatedAppCfg() }
     * </ul>
     * @param inWorkCfg
     * @param fileCfg
     * @return 
     */
    protected static ZPINcParamFv setFileHashes(ZPINcParamFv inWorkCfg, File fileCfg){
        String strHexMD5 = ZPINcAppHelper.toHex(ZPINcFileHash.MD5.checksum(fileCfg));
        String strHexSHA1 = ZPINcAppHelper.toHex(ZPINcFileHash.SHA1.checksum(fileCfg));
        String strHexSHA256 = ZPINcAppHelper.toHex(ZPINcFileHash.SHA256.checksum(fileCfg));
        String strHexSHA512 = ZPINcAppHelper.toHex(ZPINcFileHash.SHA512.checksum(fileCfg));
        ZPINcParamFv forOutParam = new ZPINcParamFv(
            inWorkCfg.indexPath,
            inWorkCfg.keywordsOutOfSearch,
            inWorkCfg.keywordsInSearch,
            inWorkCfg.dirOutOfIndex,
            inWorkCfg.dirInIndex,
            inWorkCfg.diskUserAlias,
            strHexMD5,
            strHexSHA1,
            strHexSHA256,
            strHexSHA512,
            inWorkCfg.tmIndexSubDirs
        );
        
        return forOutParam;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvWriter#writeDataInWorkCfg(ru.newcontrol.ncfv.ZPINcParamFv) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     * @param inWorkCfg 
     */
    protected static void ncParamFvDataOutPut(ZPINcParamFv inWorkCfg){
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrServiceMsg.HASH_RECORD_IS.getStr()
            + isNcParamFvDataHashTrue(inWorkCfg));
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.INDEX_PATH.getStr()
            + inWorkCfg.indexPath);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.KEYWORD_OUT_SEARCH.getStr()
            + inWorkCfg.keywordsOutOfSearch);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.KEYWORD_IN_SEARCH.getStr()
            + inWorkCfg.keywordsInSearch);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.DIR_OUT_INDEX.getStr()
            + inWorkCfg.dirOutOfIndex);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.DIR_IN_INDEX.getStr()
            + inWorkCfg.dirInIndex);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.DISK_USER_ALIAS_SIZE.getStr()
            + inWorkCfg.diskUserAlias.size());
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.STR_HEX_MD5.getStr()
            + inWorkCfg.strHexMD5);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.STR_HEX_SHA1.getStr()
            + inWorkCfg.strHexSHA1);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.STR_HEX_SHA256.getStr()
            + inWorkCfg.strHexSHA256);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.STR_HEX_SHA512.getStr()
            + inWorkCfg.strHexSHA512);
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.TM_INDEX_SUBDIRS.getStr()
            + inWorkCfg.tmIndexSubDirs.size());
        ZPINcAppHelper.outMessage(ZPINcStrLogMsgField.INFO.getStr()
            + ZPINcStrVarDescription.NCPARAMFV.getStr()
            + ZPINcStrVarDescription.RECORD_TIME.getStr()
            + inWorkCfg.recordTime);
    }
    /**
     * Not used
     * @param inWorkCfg 
     */
    private static void checkToWrite(ZPINcParamFv inWorkCfg){
        boolean isHash = isNcParamFvDataHashTrue(inWorkCfg);
        String strLvl = ZPINcStrLogMsgField.INFO.getStr();
        if( !isHash ){
            strLvl = ZPINcStrLogMsgField.ERROR.getStr();
        }
        ZPINcAppHelper.outMessage(strLvl
        + ZPINcStrLogMsgField.CHECK_RESULT.getStr()
        + ZPINcStrServiceMsg.WORK_CFG_HASH.getStr()
        + ZPINcStrServiceMsg.FOR_WRITE.getStr()
        + isHash);
        
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcParamFvReader#readDataFromWorkCfg() }
     * </ul>
     * @param inWorkCfg 
     */
    protected static void checkFromRead(ZPINcParamFv inWorkCfg){
        boolean isHash = isNcParamFvDataHashTrue(inWorkCfg);
        String strLvl = ZPINcStrLogMsgField.INFO.getStr();
        if( !isHash ){
            strLvl = ZPINcStrLogMsgField.ERROR.getStr();
        }
        ZPINcAppHelper.outMessage(strLvl
        + ZPINcStrLogMsgField.CHECK_RESULT.getStr()
        + ZPINcStrServiceMsg.WORK_CFG_HASH.getStr()
        + ZPINcStrServiceMsg.FROM_READ.getStr()
        + isHash);
    }
}
