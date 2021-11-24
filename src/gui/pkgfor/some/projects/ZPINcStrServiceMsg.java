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

/**
 *
 * @author wladimirowichbiaran
 */
public enum ZPINcStrServiceMsg {
    ERROR_FILE_NOT_EXIST("notExistFileError"),
    ERROR_FILE_NOT_CANONICAL_PATH("Can not File.getCanonicalPath() for: "),
    ERROR_NOT_CREATE("Can not create: "),
    NOT_EXIST_OR_WRONG("not exist or wrong"),
    NOT_WRITE_INTO_FILE("Can not write into file: "),
    
    READED_DATA_IS_EMPTY("readed data is Empty"),
    PATH_INDEX_DIRECTORY("Path of index directory: "),
    PATH_WORK_FILE("Path of work file: "),
    PATH_SUBDIR("Path of subDir: "),
    EXIST("Exist: "),
    CANREAD("canRead: "),
    CANWRITE("canWrite: "),
    NEWLINE("\n"),
    TAB("\t"),
    SPACE(" "),
    COLON(":"),
    HASH_CALC("Calculated hash: "),
    HASH_RECORD("in record hash: "),
    RESULT("result: "),
    HASH_RECORD_IS("RecordHash is: "),
    WORK_CFG_HASH("Work config hash: "),
    FOR_WRITE("for write: "),
    FROM_READ("from read: "),
    
    STRING_EQUAL("String equal"),
    PATH_INPUT("Path input: "),
    PATH_DEFAULT("Path default: "),
    PATH_CONTINUE_NOT_VALID("Path continue not valid"),
    PATH_START_NOT_VALID("Path start not valid"),
    PATH_FOR_NOT_WINDOWS_SYSTEM("Path for not windows system"),
    DEFAULT_STAGE("default stage"),
    COUNT_OF_SEARCHED_RECORDS("Count of searched records: "),
    FUNCTIONAL_NOT_RELEASED("Functional not released "),
    NOT_IN_SEARCH_COUNT("Not in search: ");
    
    private String strMsg;
    ZPINcStrServiceMsg(String strMsg){
        this.strMsg = strMsg;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#mcCreateWorkDir(ru.newcontrol.ncfv.NcDiskInfo) }
     * <li>{@link ru.newcontrol.ncfv.NcManageCfg#mcCheckAndCreateFolderStructure() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcParamCfgToDiskReleaser#getWorkFileParams(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcParamCfgToDiskReleaser#getIdxDirStructure(java.lang.String) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcParamFvManager#isNcParamFvDataHashTrue(ru.newcontrol.ncfv.NcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.NcParamFvManager#ncParamFvDataOutPut(ru.newcontrol.ncfv.NcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.NcParamFvManager#checkToWrite(ru.newcontrol.ncfv.NcParamFv) }
     * <li>{@link ru.newcontrol.ncfv.NcParamFvManager#checkFromRead(ru.newcontrol.ncfv.NcParamFv) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPathFromUserChecker#strInputPathFormatFilter(java.lang.String, java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcPathFromUserChecker#strInputPathFormatFilterForDefault(java.lang.String) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcPreIdxWork#checkTmpIDsData() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#outSearchResult(java.util.TreeMap, java.util.TreeMap) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchKeyWordInput#getIdDataForSplittedKeyWord(java.util.ArrayList) }
     * </ul>
     * @return 
     */
    protected String getStr(){
        return strMsg;
    }
}
