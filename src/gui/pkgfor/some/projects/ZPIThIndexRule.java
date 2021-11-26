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

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThIndexRule {
    private ZPIThIndexDirList threadIndexDirList;
    private Boolean isSetThreadIndexDirList;
    private Boolean isRunThreadIndexDirList;
    
    private ZPIThIndexMaker threadIndexMaker;
    private Boolean isSetThreadIndexMaker;
    private Boolean isRunThreadIndexMaker;
            
    
    private ZPIThIndexWord threadIndexWord;
    private Boolean isSetThreadIndexWord;
    private Boolean isRunThreadIndexWord;
    
    private ZPIThIndexState currentIndexState;
    private Boolean isSetIndexState;
    
    private AdilRule loggerRule;
    private Boolean isSetAdilRule;
    
    private ZPIThIndexStatistic currentIndexStatistic;
    private Boolean isSetIndexStatistic;
    
    protected ArrayBlockingQueue<String> namesWorkerDirList;

    public ZPIThIndexRule() {
        setFalseThreadIndexDirList();
        setFalseThreadIndexMaker();
                
        setFalseRunnedThreadIndexDirList();
        setFalseRunnedThreadIndexMaker();
        setFalseIndexStatistic();
        setFalseAdilRule();
        this.namesWorkerDirList = new ArrayBlockingQueue<String>(50);
    }
    protected void addThreadNameInQueue(String forAdd){
        this.namesWorkerDirList.add(forAdd);
    }
    protected ArrayBlockingQueue<String> getQueueThreadNames(){
        return this.namesWorkerDirList;
    }
    /**
     * ZPIThIndexDirList
     * @return 
     */
    protected ZPIThIndexDirList getThreadIndexDirList(){
        if( !this.isThreadIndexDirList() ){
            throw new IllegalArgumentException(ZPIThIndexDirList.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.threadIndexDirList;
    }
    protected void setThreadIndexDirList(final ZPIThIndexDirList threadIndexDirListOuter){
        this.threadIndexDirList = threadIndexDirListOuter;
        setTrueThreadIndexDirList();
    }
    protected void setTrueThreadIndexDirList(){
        this.isSetThreadIndexDirList = Boolean.TRUE;
    }
    protected void setFalseThreadIndexDirList(){
        this.isSetThreadIndexDirList = Boolean.FALSE;
    }
    protected Boolean isThreadIndexDirList(){
        if( this.isSetThreadIndexDirList ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedThreadIndexDirList(){
        this.isRunThreadIndexDirList = Boolean.TRUE;
    }
    protected void setFalseRunnedThreadIndexDirList(){
        this.isRunThreadIndexDirList = Boolean.FALSE;
    }
    protected Boolean isRunnedThreadIndexDirList(){
        if( this.isRunThreadIndexDirList ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
        
    /**
     * ZPIThIndexMaker
     * @return 
     */
    protected ZPIThIndexMaker getThreadIndexMaker(){
        if( !this.isThreadIndexMaker() ){
            throw new IllegalArgumentException(ZPIThIndexMaker.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.threadIndexMaker;
    }
    protected void setThreadIndexMaker(final ZPIThIndexMaker threadIndexMakerOuter){
        this.threadIndexMaker = threadIndexMakerOuter;
        setTrueThreadIndexMaker();
    }
    protected void setTrueThreadIndexMaker(){
        this.isSetThreadIndexMaker = Boolean.TRUE;
    }
    protected void setFalseThreadIndexMaker(){
        this.isSetThreadIndexMaker = Boolean.FALSE;
    }
    protected Boolean isThreadIndexMaker(){
        if( this.isSetThreadIndexMaker ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedThreadIndexMaker(){
        this.isRunThreadIndexMaker = Boolean.TRUE;
    }
    protected void setFalseRunnedThreadIndexMaker(){
        this.isRunThreadIndexMaker = Boolean.FALSE;
    }
    protected Boolean isRunnedThreadIndexMaker(){
        if( this.isRunThreadIndexMaker ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThIndexWord
     * @return 
     */
    protected ZPIThIndexWord getThreadIndexWord(){
        if( !this.isThreadIndexWord() ){
            throw new IllegalArgumentException(ZPIThIndexWord.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.threadIndexWord;
    }
    protected void setThreadIndexWord(final ZPIThIndexWord threadIndexWordOuter){
        this.threadIndexWord = threadIndexWordOuter;
        setTrueThreadIndexWord();
    }
    protected void setTrueThreadIndexWord(){
        this.isSetThreadIndexWord = Boolean.TRUE;
    }
    protected void setFalseThreadIndexWord(){
        this.isSetThreadIndexWord = Boolean.FALSE;
    }
    protected Boolean isThreadIndexWord(){
        if( this.isSetThreadIndexWord ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedThreadIndexWord(){
        this.isRunThreadIndexWord = Boolean.TRUE;
    }
    protected void setFalseRunnedThreadIndexWord(){
        this.isRunThreadIndexWord = Boolean.FALSE;
    }
    protected Boolean isRunnedThreadIndexWord(){
        if( this.isRunThreadIndexWord ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThIndexState
     * @return 
     */
    protected ZPIThIndexState getIndexState(){
        if( !this.isIndexState() ){
            throw new IllegalArgumentException(ZPIThIndexState.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.currentIndexState;
    }
    protected void setIndexState(final ZPIThIndexState stateIndexOuter){
        this.currentIndexState = stateIndexOuter;
        setTrueIndexState();
    }
    protected void setTrueIndexState(){
        this.isSetIndexState = Boolean.TRUE;
    }
    protected void setFalseIndexState(){
        this.isSetIndexState = Boolean.FALSE;
    }
    protected Boolean isIndexState(){
        if( this.isSetIndexState ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * AdilRule
     * @return 
     */
    protected AdilRule getAdilRule(){
        if( !this.isAdilRule() ){
            throw new IllegalArgumentException(AdilRule.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.loggerRule;
    }
    protected void setAdilRule(final AdilRule loggerAdilRuleOuter){
        this.loggerRule = loggerAdilRuleOuter;
        setTrueAdilRule();
    }
    protected void setTrueAdilRule(){
        this.isSetAdilRule = Boolean.TRUE;
    }
    protected void setFalseAdilRule(){
        this.isSetAdilRule = Boolean.FALSE;
    }
    protected Boolean isAdilRule(){
        if( this.isSetAdilRule ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThIndexStatistic
     * @return 
     */
    protected ZPIThIndexStatistic getIndexStatistic(){
        if( !this.isIndexStatistic() ){
            throw new IllegalArgumentException(ZPIThIndexStatistic.class.getCanonicalName() + " object not set in " + ZPIThIndexRule.class.getCanonicalName());
        }
        return this.currentIndexStatistic;
    }
    protected void setIndexStatistic(final ZPIThIndexStatistic stateIndexOuter){
        this.currentIndexStatistic = stateIndexOuter;
        setTrueIndexStatistic();
    }
    protected void setTrueIndexStatistic(){
        this.isSetIndexStatistic = Boolean.TRUE;
    }
    protected void setFalseIndexStatistic(){
        this.isSetIndexStatistic = Boolean.FALSE;
    }
    protected Boolean isIndexStatistic(){
        if( this.isSetIndexStatistic ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
