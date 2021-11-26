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
public class ZPIThDirListRule {
    //Released version
    private ThreadGroup workerThDirList;
    private ZPIThDirListState currentDirListState;
    private Boolean isSetDirListState;
    
    private ZPIThDirListStatistic currentDirListCounter;
    private Boolean isSetDirListCounter;
    
    
    private ZPIThDirListWorkRead runnableWorkerDirListRead;
    private Boolean isSetDirListWorkReader;
    private Boolean isRunDirListWorkReader;
    
    private ZPIThDirListWorkWrite runnableWorkerDirListWrite;
    private Boolean isSetDirListWorkWriter;
    private Boolean isRunDirListWorkWriter;
    
    private ZPIThDirListWorkManager runnableWorkerDirListManager;
    private Boolean isSetDirListWorkManager;
    private Boolean isRunDirListWorkManager;
    
    private ZPIThIndexRule outerIndexRule;

    public ZPIThDirListRule (final ZPIThIndexRule outerRule) {
        //Released version
        this.outerIndexRule = outerRule;
        this.workerThDirList = new ThreadGroup(UUID.randomUUID().toString());
        setFalseDirListState();
        setFalseDirListCounter();
        setFalseDirListWorkReader();
        setFalseDirListWorkWriter();
        setFalseDirListWorkManager();
        setFalseRunnedDirListWorkReader();
        setFalseRunnedDirListWorkWriter();
        setFalseRunnedDirListWorkManager();
    }
    //Released version
    protected ZPIThIndexRule getIndexRule(){
        return this.outerIndexRule;
    }
    /**
     * ZPIThDirListState
     * @return 
     */
    protected ZPIThDirListState getDirListState(){
        if( !this.isDirListState() ){
            throw new IllegalArgumentException(ZPIThDirListState.class.getCanonicalName() + " object not set in " + ZPIThDirListRule.class.getCanonicalName());
        }
        return this.currentDirListState;
    }
    protected void setDirListState(final ZPIThDirListState stateDirListOuter){
        this.currentDirListState = stateDirListOuter;
        setTrueDirListState();
    }
    protected void setTrueDirListState(){
        this.isSetDirListState = Boolean.TRUE;
    }
    protected void setFalseDirListState(){
        this.isSetDirListState = Boolean.FALSE;
    }
    protected Boolean isDirListState(){
        if( this.isSetDirListState ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThDirListStatistic
     * @return 
     */
    protected ZPIThDirListStatistic getDirListCounter(){
        if( !this.isDirListCounter() ){
            throw new IllegalArgumentException(ZPIThDirListStatistic.class.getCanonicalName() + " object not set in " + ZPIThDirListRule.class.getCanonicalName());
        }
        return this.currentDirListCounter;
    }
    protected void setDirListCounter(final ZPIThDirListStatistic counterDirListOuter){
        this.currentDirListCounter = counterDirListOuter;
        setTrueDirListCounter();
    }
    protected void setTrueDirListCounter(){
        this.isSetDirListCounter = Boolean.TRUE;
    }
    protected void setFalseDirListCounter(){
        this.isSetDirListCounter = Boolean.FALSE;
    }
    protected Boolean isDirListCounter(){
        if( this.isSetDirListCounter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThDirListWorkRead
     * @return 
     */
    protected ZPIThDirListWorkRead getDirListWorkReader(){
        if( !this.isDirListWorkReader() ){
            throw new IllegalArgumentException(ZPIThDirListWorkRead.class.getCanonicalName() + " object not set in " + ZPIThDirListRule.class.getCanonicalName());
        }
        return this.runnableWorkerDirListRead;
    }
    protected void setDirListWorkReader(final ZPIThDirListWorkRead runnableWorkerDirListReadOuter){
        this.runnableWorkerDirListRead = runnableWorkerDirListReadOuter;
        setTrueDirListWorkReader();
    }
    protected void setTrueDirListWorkReader(){
        this.isSetDirListWorkReader = Boolean.TRUE;
    }
    protected void setFalseDirListWorkReader(){
        this.isSetDirListWorkReader = Boolean.FALSE;
    }
    protected Boolean isDirListWorkReader(){
        if( this.isSetDirListWorkReader ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedDirListWorkReader(){
        this.isRunDirListWorkReader = Boolean.TRUE;
    }
    protected void setFalseRunnedDirListWorkReader(){
        this.isRunDirListWorkReader = Boolean.FALSE;
    }
    protected Boolean isRunnedDirListWorkReader(){
        if( this.isRunDirListWorkReader ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThDirListWorkWrite
     * @return 
     */
    protected ZPIThDirListWorkWrite getDirListWorkWriter(){
        if( !this.isDirListWorkWriter() ){
            throw new IllegalArgumentException(ZPIThDirListWorkWrite.class.getCanonicalName() + " object not set in " + ZPIThDirListRule.class.getCanonicalName());
        }
        return this.runnableWorkerDirListWrite;
    }
    protected void setDirListWorkWriter(final ZPIThDirListWorkWrite runnableWorkerDirListWriteOuter){
        this.runnableWorkerDirListWrite = runnableWorkerDirListWriteOuter;
        setTrueDirListWorkWriter();
    }
    protected void setTrueDirListWorkWriter(){
        this.isSetDirListWorkWriter = Boolean.TRUE;
    }
    protected void setFalseDirListWorkWriter(){
        this.isSetDirListWorkWriter = Boolean.FALSE;
    }
    protected Boolean isDirListWorkWriter(){
        if( this.isSetDirListWorkWriter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedDirListWorkWriter(){
        this.isRunDirListWorkWriter = Boolean.TRUE;
    }
    protected void setFalseRunnedDirListWorkWriter(){
        this.isRunDirListWorkWriter = Boolean.FALSE;
    }
    protected Boolean isRunnedDirListWorkWriter(){
        if( this.isRunDirListWorkWriter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIThDirListWorkManager
     * @return 
     */
    protected ZPIThDirListWorkManager getDirListWorkManager(){
        if( !this.isDirListWorkManager() ){
            throw new IllegalArgumentException(ZPIThDirListWorkManager.class.getCanonicalName() + " object not set in " + ZPIThDirListRule.class.getCanonicalName());
        }
        return this.runnableWorkerDirListManager;
    }
    protected void setDirListWorkManager(final ZPIThDirListWorkManager runnableWorkerDirListManagerOuter){
        this.runnableWorkerDirListManager = runnableWorkerDirListManagerOuter;
        setTrueDirListWorkManager();
    }
    protected void setTrueDirListWorkManager(){
        this.isSetDirListWorkManager = Boolean.TRUE;
    }
    protected void setFalseDirListWorkManager(){
        this.isSetDirListWorkWriter = Boolean.FALSE;
    }
    protected Boolean isDirListWorkManager(){
        if( this.isSetDirListWorkManager ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected void setTrueRunnedDirListWorkManager(){
        this.isRunDirListWorkManager = Boolean.TRUE;
    }
    protected void setFalseRunnedDirListWorkManager(){
        this.isRunDirListWorkManager = Boolean.FALSE;
    }
    protected Boolean isRunnedDirListWorkManager(){
        if( this.isRunDirListWorkManager ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * Check for ready and not runned and run workers
     */
    //Check for ready and Run workers in threads
    protected void runReadFromDirList(){
        if( isDirListWorkReader() && !isRunnedDirListWorkReader() ){
            /**
             * @todo release workReader Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWorkReader = UUID.randomUUID().toString();
            this.outerIndexRule.addThreadNameInQueue(toStringWorkReader);
            Thread thForWorkRead = new Thread(this.workerThDirList, this.runnableWorkerDirListRead, toStringWorkReader);
            thForWorkRead.start();
        }
    }
    protected void runWriteToDirList(){
        if( isDirListWorkWriter() && !isRunnedDirListWorkWriter() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWorkWriter = UUID.randomUUID().toString();
            this.outerIndexRule.addThreadNameInQueue(toStringWorkWriter);
            Thread thForWorkWrite = new Thread(this.workerThDirList, this.runnableWorkerDirListWrite, toStringWorkWriter);
            thForWorkWrite.start();
        }
    }
    protected void runManagerDirListWorkers(){
        if( isDirListWorkManager() && !isRunnedDirListWorkManager() ){
            /**
             * @todo release workWriter Bus names for runned threads names threads
             * for release wait him finish functions
             */
            String toStringWorkManager = UUID.randomUUID().toString();
            this.outerIndexRule.addThreadNameInQueue(toStringWorkManager);
            Thread thForWorkManager = new Thread(this.workerThDirList, this.runnableWorkerDirListManager, toStringWorkManager);
            thForWorkManager.start();
        }
    }
    
    
}
