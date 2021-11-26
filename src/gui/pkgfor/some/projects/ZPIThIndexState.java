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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThIndexState {
    /**
     * @todo add to this rule set for rule FileList, StorageWord, Word, LongWord
     * create his bus in his state and set by his rule
     */
    private ZPIThDirListBusReaded busReadedJob;
    private Boolean isSetReadedJob;
    
    private ZPIThIndexWordBusWrited busWritedJob;
    private Boolean isSetWritedJob;
    
    private AppFileStorageIndex storagesForIndexList;
    private Boolean isSetStoriesForIndexList;
    /**
     * ZPIThFileListRule
     */
    private ZPIThFileListRule ruleFileListObject;
    private Boolean isSetFileListRule;
    /**
     * ThStorageWordRule
     */
    private ThStorageWordRule ruleStorageWordObject;
    private Boolean isSetStorageWordRule;
    ZPIThIndexState(){
        /**
         * ThFileListRule
         */
        setFalseRuleFileList();
        /**
         * ThStorageWordRule
         */
        setFalseRuleStorageWord();
        setFalseStoriesForIndexList();
        currentIndexStorages();
        setFalseReadedJob();
        setFalseWritedJob();
    }
    protected AppFileStorageIndex currentIndexStorages(){
        if( this.storagesForIndexList == null ){
            setTrueStoriesForIndexList();
            this.storagesForIndexList = new AppFileStorageIndex();
        }
        if( !this.isStoriesForIndexList() ){
            setTrueStoriesForIndexList();
            this.storagesForIndexList = new AppFileStorageIndex();
        }
        return this.storagesForIndexList;
    }
    protected void setTrueStoriesForIndexList(){
        this.isSetStoriesForIndexList = Boolean.TRUE;
    }
    protected void setFalseStoriesForIndexList(){
        this.isSetStoriesForIndexList = Boolean.FALSE;
    }
    protected Boolean isStoriesForIndexList(){
        if( this.isSetStoriesForIndexList ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThDirListBusReaded getBusJobForRead(){
        if( !this.isReadedJob() ){
            throw new IllegalArgumentException("Bus jobs for read not set in " + ZPIThIndexState.class.getCanonicalName());
        }
        return this.busReadedJob;
    }
    protected void setBusJobForRead(final ZPIThDirListBusReaded busReadOuter){
        this.busReadedJob = busReadOuter;
        setTrueReadedJob();
    }
    protected void setTrueReadedJob(){
        this.isSetReadedJob = Boolean.TRUE;
    }
    protected void setFalseReadedJob(){
        this.isSetReadedJob = Boolean.FALSE;
    }
    protected Boolean isReadedJob(){
        if( this.isSetReadedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThIndexWordBusWrited getBusJobForWrite(){
        if( !this.isReadedJob() ){
            throw new IllegalArgumentException("Bus jobs for write not set in " + ZPIThIndexState.class.getCanonicalName());
        }
        return this.busWritedJob;
    }
    protected void setBusJobForWrite(final ZPIThIndexWordBusWrited busWriteOuter){
        this.busWritedJob = busWriteOuter;
        setTrueWritedJob();
    }
    protected void setTrueWritedJob(){
        this.isSetWritedJob = Boolean.TRUE;
    }
    protected void setFalseWritedJob(){
        this.isSetWritedJob = Boolean.FALSE;
    }
    protected Boolean isWritedJob(){
        if( this.isSetWritedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return ZPIThFileListRule objects for manage index system FileList
     * @throws IllegalArgumentException when object not set
     */
    protected ZPIThFileListRule getRuleFileList(){
        if( !this.isRuleFileList() ){
            throw new IllegalArgumentException(ZPIThFileListRule.class.getCanonicalName()
                    + "not set in " + ZPIThIndexState.class.getCanonicalName());
        }
        return (ZPIThFileListRule) this.ruleFileListObject;
    }
    /**
     * set ZPIThFileListRule objects for manage index system FileList
     * @param busReadOuter 
     */
    protected void setRuleFileList(final ZPIThFileListRule busReadOuter){
        this.ruleFileListObject = (ZPIThFileListRule) busReadOuter;
        setTrueRuleFileList();
    }
    protected void setTrueRuleFileList(){
        this.isSetFileListRule = Boolean.TRUE;
    }
    protected void setFalseRuleFileList(){
        this.isSetFileListRule = Boolean.FALSE;
    }
    protected Boolean isRuleFileList(){
        if( this.isSetFileListRule ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return ThStorageWordRule objects for manage index system StorageWord
     * @throws #java.lang.IllegalArgumentException when object not set
     */
    protected ThStorageWordRule getRuleStorageWord(){
        if( !this.isRuleStorageWord() ){
            throw new IllegalArgumentException(ThStorageWordRule.class.getCanonicalName()
                    + "not set in " + ZPIThIndexState.class.getCanonicalName());
        }
        return (ThStorageWordRule) this.ruleStorageWordObject;
    }
    /**
     * set ThStorageWordRule objects for manage index system StorageWord
     * @param busReadOuter 
     */
    protected void setRuleStorageWord(final ThStorageWordRule busReadOuter){
        this.ruleStorageWordObject = (ThStorageWordRule) busReadOuter;
        setTrueRuleStorageWord();
    }
    protected void setTrueRuleStorageWord(){
        this.isSetStorageWordRule = Boolean.TRUE;
    }
    protected void setFalseRuleStorageWord(){
        this.isSetStorageWordRule = Boolean.FALSE;
    }
    protected Boolean isRuleStorageWord(){
        if( this.isSetStorageWordRule ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
