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
public class ZPIThWordRule {
    private final ThreadGroup workerThWord;
    private final ZPIThIndexRule indexRule;

    /**
     * ZPIThWordWorkRouter
     */
    private ZPIThWordWorkRouter runnableWorkerWordRouter;
    private Boolean isSetWordWorkRouter;
    private Boolean isRunWordWorkRouter;
    /**
     * ZPIThWordWorkWrite
     */
    private ZPIThWordWorkWrite runnableWorkerWordWrite;
    private Boolean isSetWordWorkWrite;
    private Boolean isRunWordWorkWrite;
    /**
     * ZPIThWordWorkRead
     */
    private ZPIThWordWorkRead runnableWorkerWordRead;
    private Boolean isSetWordWorkRead;
    private Boolean isRunWordWorkRead;
    /**
     * ZPIThWordState
     */
    private ZPIThWordState currentWordState;
    private Boolean isSetWordState;
    /**
     * ZPIThWordStatusMainFlow
     */
    private ZPIThWordStatusMainFlow currentWordStatusMainFlow;
    private Boolean isSetWordStatusMainFlow;
    
    
    public ZPIThWordRule(final ZPIThIndexRule outerRuleIndex) {
        this.indexRule = (ZPIThIndexRule) outerRuleIndex;
        this.workerThWord = new ThreadGroup(UUID.randomUUID().toString());
        /**
         * ThWordState
         */
        setFalseWordState();
        /**
         * ThWordStatistic
         */
        setFalseWordStatusMainFlow();
        /**
         * ThWordWorkRouter
         */
        setFalseWordWorkRouter();
        setFalseRunnedWordWorkRouter();
        /**
         * ThWordWorkWrite
         */
        setFalseWordWorkWrite();
        setFalseRunnedWordWorkWrite();
        /**
         * ThWordWorkRead
         */
        setFalseWordWorkRead();
        setFalseRunnedWordWorkRead();
    }
    /**
     * ZPIThIndexRule
     * @return 
     */
    protected ZPIThIndexRule getIndexRule(){
        return (ZPIThIndexRule) this.indexRule;
    }
    /**
     * ZPIThWordWorkRouter
     * @return 
     */
    protected ZPIThWordWorkRouter getWordWorkRouter(){
        if( !this.isWordWorkRouter() ){
            throw new IllegalArgumentException(ZPIThWordWorkRouter.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerWordRouter;
    }
    protected void setWordWorkRouter(final ZPIThWordWorkRouter runnableWorkerWordRouterOuter){
        this.runnableWorkerWordRouter = (ZPIThWordWorkRouter) runnableWorkerWordRouterOuter;
        setTrueWordWorkRouter();
    }
    protected void setTrueWordWorkRouter(){
        this.isSetWordWorkRouter = Boolean.TRUE;
    }
    protected void setFalseWordWorkRouter(){
        this.isSetWordWorkRouter = Boolean.FALSE;
    }
    protected Boolean isWordWorkRouter(){
        if( this.isSetWordWorkRouter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedWordWorkRouter(){
        this.isRunWordWorkRouter = Boolean.TRUE;
    }
    protected void setFalseRunnedWordWorkRouter(){
        this.isRunWordWorkRouter = Boolean.FALSE;
    }
    protected Boolean isRunnedWordWorkRouter(){
        if( this.isRunWordWorkRouter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runRouterWordWork(){
        if( isWordWorkRouter() && !isRunnedWordWorkRouter() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWordWorkRouter = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringWordWorkRouter);
            Thread thForWorkRouter = new Thread(this.workerThWord, this.runnableWorkerWordRouter, toStringWordWorkRouter);
            thForWorkRouter.setPriority(5);
            thForWorkRouter.start();
        }
    }
    /**
     * ZPIThWordWorkWrite
     * @return 
     */
    protected ZPIThWordWorkWrite getWordWorkWrite(){
        if( !this.isWordWorkWrite() ){
            throw new IllegalArgumentException(ZPIThWordWorkWrite.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerWordWrite;
    }
    protected void setWordWorkWrite(final ZPIThWordWorkWrite runnableWorkerWordWriteOuter){
        this.runnableWorkerWordWrite = (ZPIThWordWorkWrite) runnableWorkerWordWriteOuter;
        setTrueWordWorkWrite();
    }
    protected void setTrueWordWorkWrite(){
        this.isSetWordWorkWrite = Boolean.TRUE;
    }
    protected void setFalseWordWorkWrite(){
        this.isSetWordWorkWrite = Boolean.FALSE;
    }
    protected Boolean isWordWorkWrite(){
        if( this.isSetWordWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedWordWorkWrite(){
        this.isRunWordWorkWrite = Boolean.TRUE;
    }
    protected void setFalseRunnedWordWorkWrite(){
        this.isRunWordWorkWrite = Boolean.FALSE;
    }
    protected Boolean isRunnedWordWorkWrite(){
        if( this.isRunWordWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runWriteWordWork(){
        if( isWordWorkWrite() && !isRunnedWordWorkWrite() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWordWorkWrite = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringWordWorkWrite);
            Thread thForWorkWrite = new Thread(this.workerThWord, this.runnableWorkerWordWrite, toStringWordWorkWrite);
            thForWorkWrite.setPriority(3);
            thForWorkWrite.start();
        }
    }
    /**
     * ZPIThWordWorkRead
     * @return 
     */
    protected ZPIThWordWorkRead getWordWorkRead(){
        if( !this.isWordWorkRead() ){
            throw new IllegalArgumentException(ZPIThWordWorkRead.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIThWordRule.class.getCanonicalName()
            );
        }
        return this.runnableWorkerWordRead;
    }
    protected void setWordWorkRead(final ZPIThWordWorkRead runnableWorkerWordReadOuter){
        this.runnableWorkerWordRead = (ZPIThWordWorkRead) runnableWorkerWordReadOuter;
        setTrueWordWorkRead();
    }
    protected void setTrueWordWorkRead(){
        this.isSetWordWorkRead = Boolean.TRUE;
    }
    protected void setFalseWordWorkRead(){
        this.isSetWordWorkRead = Boolean.FALSE;
    }
    protected Boolean isWordWorkRead(){
        if( this.isSetWordWorkRead ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedWordWorkRead(){
        this.isRunWordWorkRead = Boolean.TRUE;
    }
    protected void setFalseRunnedWordWorkRead(){
        this.isRunWordWorkRead = Boolean.FALSE;
    }
    protected Boolean isRunnedWordWorkRead(){
        if( this.isRunWordWorkRead ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runReadWordWork(){
        if( isWordWorkRead() && !isRunnedWordWorkRead() ){
            /**
             * @todo release workReadr Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWordWorkRead = UUID.randomUUID().toString();
            this.indexRule.addThreadNameInQueue(toStringWordWorkRead);
            Thread thForWorkRead = new Thread(this.workerThWord, this.runnableWorkerWordRead, toStringWordWorkRead);
            thForWorkRead.setPriority(4);
            thForWorkRead.start();
        }
    }
    /**
     * ZPIThWordState
     * @return 
     */
    protected ZPIThWordState getWordState(){
        if( !this.isWordState() ){
            throw new IllegalArgumentException(ZPIThWordState.class.getCanonicalName() + " object not set in " + ZPIThWordRule.class.getCanonicalName());
        }
        return this.currentWordState;
    }
    protected void setWordState(final ZPIThWordState stateWordOuter){
        this.currentWordState = (ZPIThWordState) stateWordOuter;
        setTrueWordState();
    }
    protected void setTrueWordState(){
        this.isSetWordState = Boolean.TRUE;
    }
    protected void setFalseWordState(){
        this.isSetWordState = Boolean.FALSE;
    }
    protected Boolean isWordState(){
        if( this.isSetWordState ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThWordStatusMainFlow
     * @return 
     */
    protected ZPIThWordStatusMainFlow getWordStatusMainFlow(){
        if( !this.isWordStatusMainFlow() ){
            throw new IllegalArgumentException(ZPIThWordStatusMainFlow.class.getCanonicalName() + " object not set in " + ZPIThWordRule.class.getCanonicalName());
        }
        return this.currentWordStatusMainFlow;
    }
    protected void setWordStatusMainFlow(final ZPIThWordStatusMainFlow stateWordOuter){
        this.currentWordStatusMainFlow = (ZPIThWordStatusMainFlow) stateWordOuter;
        setTrueWordStatusMainFlow();
    }
    protected void setTrueWordStatusMainFlow(){
        this.isSetWordStatusMainFlow = Boolean.TRUE;
    }
    protected void setFalseWordStatusMainFlow(){
        this.isSetWordStatusMainFlow = Boolean.FALSE;
    }
    protected Boolean isWordStatusMainFlow(){
        if( this.isSetWordStatusMainFlow ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
