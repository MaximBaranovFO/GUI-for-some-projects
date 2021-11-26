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

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThDirListBusReaded {
    private ConcurrentSkipListMap<UUID, ZPIThDirListStateJobReader> forReadQueue;
    
    protected ZPIThDirListBusReaded(){
        this.forReadQueue = new ConcurrentSkipListMap<UUID, ZPIThDirListStateJobReader>();
    }
    protected void addReaderJob(final ZPIThDirListStateJobReader jobForDone){
        if( !jobForDone.isBlankObject() ){
            this.forReadQueue.put(jobForDone.getID(), jobForDone);
        }
    }
    protected ZPIThDirListStateJobReader getJobForRead(){
        Map.Entry<UUID, ZPIThDirListStateJobReader> pollFirstEntry = this.forReadQueue.pollFirstEntry();
        if( pollFirstEntry != null ){
                return pollFirstEntry.getValue();
        }
        return new ZPIThDirListStateJobReader();
    }
    protected Boolean isJobQueueEmpty(){
        if( this.forReadQueue.isEmpty() ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Integer getQueueSize(){
        return (int) this.forReadQueue.size();
    }
    protected void cleanQueue(){
        this.forReadQueue = new ConcurrentSkipListMap<UUID, ZPIThDirListStateJobReader>();
    }
    
    protected void shrinkJobDoneItems(){
        
        ThreadLocal<ArrayBlockingQueue<UUID>> thListJobDone = new ThreadLocal<ArrayBlockingQueue<UUID>>();
        try{
            ArrayBlockingQueue<UUID> listOfDoneJob = new ArrayBlockingQueue<UUID>((int) this.forReadQueue.size());
            thListJobDone.set(listOfDoneJob);
            for( Map.Entry<UUID, ZPIThDirListStateJobReader> itemJob : this.forReadQueue.entrySet() ){
                if( itemJob.getValue().isReaderJobDone() ){
                    thListJobDone.get().add(itemJob.getKey());
                }
            }
            try{
                if( !thListJobDone.get().isEmpty() ){
                    do{
                        UUID poll = thListJobDone.get().poll();
                        if( poll != null){
                            this.forReadQueue.remove(poll);
                            UUID ceilingKey = this.forReadQueue.ceilingKey(poll);
                            if( ceilingKey == null ){
                                thListJobDone.get().add(poll);
                            }
                        }
                    }while( !thListJobDone.get().isEmpty() );
                }
            } catch ( ClassCastException ex ){
                ex.printStackTrace();
            } catch ( NullPointerException ex ){
                ex.printStackTrace();
            }
        } finally {
            thListJobDone.remove();
        }
    }
    protected void outToConsoleBusContent(){
        for(Map.Entry<UUID, ZPIThDirListStateJobReader> itemBus : this.forReadQueue.entrySet() ){
            System.out.println("Key: " 
                    + itemBus.getKey().toString()
                    + " Value: "
                    + itemBus.getValue().getReadedPath().toString()
                    + " isDoneJob: "
                    + String.valueOf(itemBus.getValue().isReaderJobDone())
            );
        }
    }
    
}
