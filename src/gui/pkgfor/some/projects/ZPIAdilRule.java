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
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAdilRule {
    private ZPIThIndexRule indexRule;
    
    private ZPIAdilWorkerWrite runnableAdilWorkWrite;
    private Boolean isRunAdilWorkWrite;
    private Boolean isSetAdilWorkWrite;
    
    private ZPIAdilState currentZPIAdilState;
    private Boolean isSetZPIAdilState;
    
    private LinkedTransferQueue<UUID> queueForRunLogger;
    
    ZPIAdilRule(ZPIThIndexRule indexRuleOuter){
        this.indexRule = (ZPIThIndexRule) indexRuleOuter;
        this.queueForRunLogger = new LinkedTransferQueue<UUID>();
        setFalseRunnerAdilWorkWrite();
        setFalseAdilWorkWrite();
        setFalseZPIAdilState();
    }
    /**
     * ZPIThIndexRule
     * @return 
     */
    protected ZPIThIndexRule getIndexRule(){
        return (ZPIThIndexRule) this.indexRule;
    }
    /**
     * ZPIAdilWorkerWrite
     * @return 
     */
    protected ZPIAdilWorkerWrite getAdilWorkWrite(){
        if( !this.isAdilWorkWrite() ){
            throw new IllegalArgumentException(ZPIAdilWorkerWrite.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIAdilRule.class.getCanonicalName()
            );
        }
        return this.runnableAdilWorkWrite;
    }
    protected void setAdilWorkWrite(final ZPIAdilWorkerWrite runnableAdilWorkWriteOuter){
        this.runnableAdilWorkWrite = (ZPIAdilWorkerWrite) runnableAdilWorkWriteOuter;
        setTrueAdilWorkWrite();
    }
    protected void setTrueAdilWorkWrite(){
        this.isSetAdilWorkWrite = Boolean.TRUE;
    }
    protected void setFalseAdilWorkWrite(){
        this.isSetAdilWorkWrite = Boolean.FALSE;
    }
    protected Boolean isAdilWorkWrite(){
        if( this.isSetAdilWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnerAdilWorkWrite(){
        this.isRunAdilWorkWrite = Boolean.TRUE;
    }
    protected void setFalseRunnerAdilWorkWrite(){
        this.isRunAdilWorkWrite = Boolean.FALSE;
    }
    protected Boolean isRunnedAdilWorkWrite(){
        if( this.isRunAdilWorkWrite ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void runAdilWorkWrite(){
        if( isAdilWorkWrite() && !isRunnedAdilWorkWrite() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringAdilWorkWrite = UUID.randomUUID().toString();
            //this.indexRule.addThreadNameInQueue(toStringAdilWorkWrite);
            Thread thForWorkRouter = new Thread(this.runnableAdilWorkWrite, toStringAdilWorkWrite);
            thForWorkRouter.setPriority(7);
            thForWorkRouter.start();
        } else {
            if( isAdilWorkWrite() ){
                this.queueForRunLogger.add(UUID.randomUUID());
            }
        }
    }
    protected void needNextRunLogger(){
        UUID poll;
        try {
            poll = this.queueForRunLogger.poll();
            if( poll != null ){
                runAdilWorkWrite();
            }
        } finally {
            poll = null;
        }
    }
    /**
     * ZPIAdilState
     * @return 
     */
    protected ZPIAdilState getZPIAdilState(){
        if( !this.isZPIAdilState() ){
            throw new IllegalArgumentException(ZPIAdilState.class.getCanonicalName() + " object not set in " + ZPIAdilRule.class.getCanonicalName());
        }
        return this.currentZPIAdilState;
    }
    protected void setZPIAdilState(final ZPIAdilState stateAdilOuter){
        this.currentZPIAdilState = (ZPIAdilState) stateAdilOuter;
        setTrueZPIAdilState();
    }
    protected void setTrueZPIAdilState(){
        this.isSetZPIAdilState = Boolean.TRUE;
    }
    protected void setFalseZPIAdilState(){
        this.isSetZPIAdilState = Boolean.FALSE;
    }
    protected Boolean isZPIAdilState(){
        if( this.isSetZPIAdilState ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
