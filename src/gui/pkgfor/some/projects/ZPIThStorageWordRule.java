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
 *
 * @author wladimirowichbiaran
 */
public class ZPIThStorageWordRule {
    private final ThreadGroup workerThStorageWord;
    private final ZPIThIndexRule indexRule;
    /**
     * ZPIThStorageWordWorkFilter
     */
    private ZPIThStorageWordWorkFilter runnableWorkerStorageWordFilter;
    private Boolean isSetStorageWordWorkFilter;
    private Boolean isRunStorageWordWorkFilter;
    /**
     * ZPIThStorageWordWorkRouter
     */
    private ZPIThStorageWordWorkRouter runnableWorkerStorageWordRouter;
    private Boolean isSetStorageWordWorkRouter;
    private Boolean isRunStorageWordWorkRouter;
    /**
     * ZPIThStorageWordWorkWrite
     */
    private ZPIThStorageWordWorkWrite runnableWorkerStorageWordWrite;
    private Boolean isSetStorageWordWorkWrite;
    private Boolean isRunStorageWordWorkWrite;
    /**
     * ZPIThStorageWordWorkRead
     */
    private ZPIThStorageWordWorkRead runnableWorkerStorageWordRead;
    private Boolean isSetStorageWordWorkRead;
    private Boolean isRunStorageWordWorkRead;
    /**
     * ZPIThStorageWordState
     */
    private ZPIThStorageWordState currentStorageWordState;
    private Boolean isSetStorageWordState;
    /**
     * ZPIThStorageWordStatusMainFlow
     */
    private ZPIThStorageWordStatusMainFlow currentStorageWordStatusMainFlow;
    private Boolean isSetStorageWordStatusMainFlow;
    
    
    public ZPIThStorageWordRule(final ZPIThIndexRule outerRuleIndex) {
        this.indexRule = (ZPIThIndexRule) outerRuleIndex;
        this.workerThStorageWord = new ThreadGroup(UUID.randomUUID().toString());
        /**
         * ThStorageWordState
         */
        setFalseStorageWordState();
        /**
         * ThStorageWordStatistic
         */
        setFalseStorageWordStatusMainFlow();
        
        /**
         * ThStorageWordWorkFilter
         */
        setFalseStorageWordWorkFilter();
        setFalseRunnedStorageWordWorkFilter();
        /**
         * ThStorageWordWorkRouter
         */
        setFalseStorageWordWorkRouter();
        setFalseRunnedStorageWordWorkRouter();
        /**
         * ThStorageWordWorkWrite
         */
        setFalseStorageWordWorkWrite();
        setFalseRunnedStorageWordWorkWrite();
        /**
         * ThStorageWordWorkRead
         */
        setFalseStorageWordWorkRead();
        setFalseRunnedStorageWordWorkRead();
    }
    /**
     * ZPIThIndexRule
     * @return 
     */
    protected ZPIThIndexRule getIndexRule(){
        return (ZPIThIndexRule) this.indexRule;
    }
    /**
     * ZPIThStorageWordWorkFilter
     * @return 
     */
    protected ZPIThStorageWordWorkFilter getStorageWordWorkFilter(){
        if( !this.isStorageWordWorkFilter() ){
            throw new IllegalArgumentException(ZPIThStorageWordWorkFilter.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerStorageWordFilter;
    }
    protected void setStorageWordWorkFilter(final ZPIThStorageWordWorkFilter runnableWorkerStorageWordFilterOuter){
        this.runnableWorkerStorageWordFilter = (ZPIThStorageWordWorkFilter) runnableWorkerStorageWordFilterOuter;
        setTrueStorageWordWorkFilter();
    }
    protected void setTrueStorageWordWorkFilter(){
        this.isSetStorageWordWorkFilter = Boolean.TRUE;
    }
    protected void setFalseStorageWordWorkFilter(){
        this.isSetStorageWordWorkFilter = Boolean.FALSE;
    }
    protected Boolean isStorageWordWorkFilter(){
        if( this.isSetStorageWordWorkFilter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedStorageWordWorkFilter(){
        this.isRunStorageWordWorkFilter = Boolean.TRUE;
    }
    protected void setFalseRunnedStorageWordWorkFilter(){
        this.isRunStorageWordWorkFilter = Boolean.FALSE;
    }
    protected Boolean isRunnedStorageWordWorkFilter(){
        if( this.isRunStorageWordWorkFilter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runFilterStorageWordWork(){
        if( isStorageWordWorkFilter() && !isRunnedStorageWordWorkFilter() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             * 
             * setPriority for groupThreads than need threads grouping in the
             * writers, readers, routing, filters, for manage his work priorities
             * 
             */
            String toStringStorageWordWorkFilter = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringStorageWordWorkFilter);
            Thread thForWorkFilter = new Thread(this.workerThStorageWord, this.runnableWorkerStorageWordFilter, toStringStorageWordWorkFilter);
            thForWorkFilter.setPriority(2);
            thForWorkFilter.start();
        }
    }
    /**
     * ZPIThStorageWordWorkRouter
     * @return 
     */
    protected ZPIThStorageWordWorkRouter getStorageWordWorkRouter(){
        if( !this.isStorageWordWorkRouter() ){
            throw new IllegalArgumentException(ZPIThStorageWordWorkRouter.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerStorageWordRouter;
    }
    protected void setStorageWordWorkRouter(final ZPIThStorageWordWorkRouter runnableWorkerStorageWordRouterOuter){
        this.runnableWorkerStorageWordRouter = (ZPIThStorageWordWorkRouter) runnableWorkerStorageWordRouterOuter;
        setTrueStorageWordWorkRouter();
    }
    protected void setTrueStorageWordWorkRouter(){
        this.isSetStorageWordWorkRouter = Boolean.TRUE;
    }
    protected void setFalseStorageWordWorkRouter(){
        this.isSetStorageWordWorkRouter = Boolean.FALSE;
    }
    protected Boolean isStorageWordWorkRouter(){
        if( this.isSetStorageWordWorkRouter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedStorageWordWorkRouter(){
        this.isRunStorageWordWorkRouter = Boolean.TRUE;
    }
    protected void setFalseRunnedStorageWordWorkRouter(){
        this.isRunStorageWordWorkRouter = Boolean.FALSE;
    }
    protected Boolean isRunnedStorageWordWorkRouter(){
        if( this.isRunStorageWordWorkRouter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runRouterStorageWordWork(){
        if( isStorageWordWorkRouter() && !isRunnedStorageWordWorkRouter() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringStorageWordWorkRouter = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringStorageWordWorkRouter);
            Thread thForWorkRouter = new Thread(this.workerThStorageWord, this.runnableWorkerStorageWordRouter, toStringStorageWordWorkRouter);
            thForWorkRouter.setPriority(5);
            thForWorkRouter.start();
        }
    }
    /**
     * ZPIThStorageWordWorkWrite
     * @return 
     */
    protected ZPIThStorageWordWorkWrite getStorageWordWorkWrite(){
        if( !this.isStorageWordWorkWrite() ){
            throw new IllegalArgumentException(ZPIThStorageWordWorkWrite.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerStorageWordWrite;
    }
    protected void setStorageWordWorkWrite(final ZPIThStorageWordWorkWrite runnableWorkerStorageWordWriteOuter){
        this.runnableWorkerStorageWordWrite = (ZPIThStorageWordWorkWrite) runnableWorkerStorageWordWriteOuter;
        setTrueStorageWordWorkWrite();
    }
    protected void setTrueStorageWordWorkWrite(){
        this.isSetStorageWordWorkWrite = Boolean.TRUE;
    }
    protected void setFalseStorageWordWorkWrite(){
        this.isSetStorageWordWorkWrite = Boolean.FALSE;
    }
    protected Boolean isStorageWordWorkWrite(){
        if( this.isSetStorageWordWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedStorageWordWorkWrite(){
        this.isRunStorageWordWorkWrite = Boolean.TRUE;
    }
    protected void setFalseRunnedStorageWordWorkWrite(){
        this.isRunStorageWordWorkWrite = Boolean.FALSE;
    }
    protected Boolean isRunnedStorageWordWorkWrite(){
        if( this.isRunStorageWordWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runWriteStorageWordWork(){
        if( isStorageWordWorkWrite() && !isRunnedStorageWordWorkWrite() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringStorageWordWorkWrite = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringStorageWordWorkWrite);
            Thread thForWorkWrite = new Thread(this.workerThStorageWord, this.runnableWorkerStorageWordWrite, toStringStorageWordWorkWrite);
            thForWorkWrite.setPriority(3);
            thForWorkWrite.start();
        }
    }
    /**
     * ZPIThStorageWordWorkRead
     * @return 
     */
    protected ZPIThStorageWordWorkRead getStorageWordWorkRead(){
        if( !this.isStorageWordWorkRead() ){
            throw new IllegalArgumentException(ZPIThStorageWordWorkRead.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerStorageWordRead;
    }
    protected void setStorageWordWorkRead(final ZPIThStorageWordWorkRead runnableWorkerStorageWordReadOuter){
        this.runnableWorkerStorageWordRead = (ZPIThStorageWordWorkRead) runnableWorkerStorageWordReadOuter;
        setTrueStorageWordWorkRead();
    }
    protected void setTrueStorageWordWorkRead(){
        this.isSetStorageWordWorkRead = Boolean.TRUE;
    }
    protected void setFalseStorageWordWorkRead(){
        this.isSetStorageWordWorkRead = Boolean.FALSE;
    }
    protected Boolean isStorageWordWorkRead(){
        if( this.isSetStorageWordWorkRead ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedStorageWordWorkRead(){
        this.isRunStorageWordWorkRead = Boolean.TRUE;
    }
    protected void setFalseRunnedStorageWordWorkRead(){
        this.isRunStorageWordWorkRead = Boolean.FALSE;
    }
    protected Boolean isRunnedStorageWordWorkRead(){
        if( this.isRunStorageWordWorkRead ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runReadStorageWordWork(){
        if( isStorageWordWorkRead() && !isRunnedStorageWordWorkRead() ){
            /**
             * @todo release workReadr Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringStorageWordWorkRead = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringStorageWordWorkRead);
            Thread thForWorkRead = new Thread(this.workerThStorageWord, this.runnableWorkerStorageWordRead, toStringStorageWordWorkRead);
            thForWorkRead.setPriority(4);
            thForWorkRead.start();
        }
    }
    /**
     * ZPIThStorageWordState
     * @return 
     */
    protected ZPIThStorageWordState getStorageWordState(){
        if( !this.isStorageWordState() ){
            throw new IllegalArgumentException(ZPIThStorageWordState.class.getCanonicalName() + " object not set in " + ZPIThWordRule.class.getCanonicalName());
        }
        return this.currentStorageWordState;
    }
    protected void setStorageWordState(final ZPIThStorageWordState stateWordOuter){
        this.currentStorageWordState = (ZPIThStorageWordState) stateWordOuter;
        setTrueStorageWordState();
    }
    protected void setTrueStorageWordState(){
        this.isSetStorageWordState = Boolean.TRUE;
    }
    protected void setFalseStorageWordState(){
        this.isSetStorageWordState = Boolean.FALSE;
    }
    protected Boolean isStorageWordState(){
        if( this.isSetStorageWordState ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThStorageWordStatusMainFlow
     * @return 
     */
    protected ZPIThStorageWordStatusMainFlow getStorageWordStatusMainFlow(){
        if( !this.isStorageWordStatusMainFlow() ){
            throw new IllegalArgumentException(ZPIThStorageWordStatusMainFlow.class.getCanonicalName() + " object not set in " + ZPIThWordRule.class.getCanonicalName());
        }
        return this.currentStorageWordStatusMainFlow;
    }
    protected void setStorageWordStatusMainFlow(final ZPIThStorageWordStatusMainFlow stateWordOuter){
        this.currentStorageWordStatusMainFlow = stateWordOuter;
        setTrueStorageWordStatusMainFlow();
    }
    protected void setTrueStorageWordStatusMainFlow(){
        this.isSetStorageWordStatusMainFlow = Boolean.TRUE;
    }
    protected void setFalseStorageWordStatusMainFlow(){
        this.isSetStorageWordStatusMainFlow = Boolean.FALSE;
    }
    protected Boolean isStorageWordStatusMainFlow(){
        if( this.isSetStorageWordStatusMainFlow ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
   
}
