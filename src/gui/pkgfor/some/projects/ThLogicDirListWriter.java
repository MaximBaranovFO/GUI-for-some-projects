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

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ThLogicDirListWriter {
    private AppThWorkDirListRule innerRuleForDirListWorkers;
    //private NcParamFs currentWriterFs;
    
    private ThreadLocal<Long> counterPackCount;
    private ThreadLocal<Long> counterDataSize;

    public ThLogicDirListWriter(AppThWorkDirListRule ruleForDirListWorkers){//,
            //NcParamFs currentStorageFs) {
        this.innerRuleForDirListWorkers = ruleForDirListWorkers;
        //this.currentWriterFs = currentStorageFs;
    }
    protected void doWriter(){
        this.counterPackCount = new ThreadLocal<Long>();
        this.counterPackCount.set(0L);
        
        this.counterDataSize = new ThreadLocal<Long>();
        this.counterDataSize.set(0L);
        
        do{
            this.innerRuleForDirListWorkers.setDirListWriterLogicRunned();
        } while( !this.innerRuleForDirListWorkers.isDirListWriterLogicRunned() );
        
        final ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPITdataDirListFsObjAttr>> pipePackerToWriter = 
                this.innerRuleForDirListWorkers.getWorkDirListState().getPipePackerToWriter();
        outStatesOfWorkLogic(" Writer start run part");
        Map<String, String> fsProperties = NcFsIdxStorageInit.getFsPropCreate();
        //try(FileSystem fsZipIndexStorage = 
        //    FileSystems.getFileSystem(this.currentWriterFs)){
        do{
            outStatesOfWorkLogic(" Writer start part wait for Packer finished");
            //@todo all code to class
            do{
                try{
                    Thread.currentThread().sleep(5);
                } catch(InterruptedException ex){
                    outStatesOfWorkLogic(" sleep is interrupted with message" + ex.getMessage());
                }
            }while( !this.innerRuleForDirListWorkers.isDirListPackerLogicRunned() );
            
            if( AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_DIR_LIST_WRITER_PIPE_TO_STRING ){
                outStatesOfWorkLogic(pipePackerToWriter.toString() + " size " + pipePackerToWriter.size());
            }
            if( pipePackerToWriter != null){
                do{
                    ConcurrentSkipListMap<UUID, ZPITdataDirListFsObjAttr> poll = pipePackerToWriter.poll();
                    if( poll != null ){
                        outStatesOfWorkLogic(" polled from pipePackerToWriter size is " + poll.size());
                        if( poll.size() == 100 ){
                            Long tmpSumPack = this.counterPackCount.get() + 1L;
                            this.counterPackCount.set( tmpSumPack );
                        }
                        Long tmpSumData = this.counterDataSize.get() + (long) poll.size();
                        this.counterDataSize.set( tmpSumData );
                        
                        this.writeDataToStorage(poll);//, this.currentWriterFs);
                    }
                    outDataProcessedOfWorkLogic(this.counterPackCount.get(), 
                            this.counterDataSize.get(),
                            pipePackerToWriter.size());
                } while( !pipePackerToWriter.isEmpty() );
            } else {
                outStatesOfWorkLogic(" pipePackerToWriter is null");
            }
        }while( !this.innerRuleForDirListWorkers.isDirListPackerLogicFinished() );

        do{
            ConcurrentSkipListMap<UUID, ZPITdataDirListFsObjAttr> poll = pipePackerToWriter.poll();
            if( poll != null ){
                outStatesOfWorkLogic(" polled from pipePackerToWriter size is " + poll.size());
                if( poll.size() == 100 ){
                    Long tmpSumPack = this.counterPackCount.get() + 1L;
                    this.counterPackCount.set( tmpSumPack );
                }
                Long tmpSumData = this.counterDataSize.get() + (long) poll.size();
                this.counterDataSize.set( tmpSumData );

                this.writeDataToStorage(poll);//, this.currentWriterFs);
            }
            outDataProcessedOfWorkLogic(this.counterPackCount.get(), 
                    this.counterDataSize.get(),
                    pipePackerToWriter.size());
        } while( !pipePackerToWriter.isEmpty() );
        
        do{
            this.innerRuleForDirListWorkers.setDirListWriterLogicFinished();
        } while( !this.innerRuleForDirListWorkers.isDirListWriterLogicFinished() );
        
        outStatesOfWorkLogic(" Writer end run part");
    }
    private void outStatesOfWorkLogic(String strForOutPut){
        String strRunLogicLabel = ThLogicDirListPacker.class.getCanonicalName() 
                            + "[THREADNAME]" + Thread.currentThread().getName()
                            + strForOutPut;
        NcAppHelper.outToConsoleIfDevAndParamTrue(strRunLogicLabel, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_DIR_LIST_WRITER_RUN);
    }
    private void outDataProcessedOfWorkLogic(Long packIn, Long dataIn, Integer pipeSize){
        String strRunLogicLabel = ThLogicDirListPacker.class.getCanonicalName() 
                            + "[THREADNAME]" + Thread.currentThread().getName()
                            + "                                                   pack in    " 
                            + String.valueOf(packIn) 
                            + "  data in   "
                            + String.valueOf(dataIn) 
                            + "  ptwPs     "
                            + String.valueOf(pipeSize);
        NcAppHelper.outToConsoleIfDevAndParamTrue(strRunLogicLabel, AppConstants.LOG_LEVEL_IS_DEV_TO_CONS_DIR_LIST_WRITER_DATA_COUNT);
    }
    private void writeDataToStorage(final ConcurrentSkipListMap<UUID, ZPITdataDirListFsObjAttr> forWriteData){//,
           //NcParamFs indexStorage){
        ZPIThFsFileIndexStorage.writeData(forWriteData, this.innerRuleForDirListWorkers);
                //, indexStorage);
    }
}
