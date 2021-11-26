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
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThIndexDirList extends Thread{
    private ZPIThIndexRule ruleThIndex;
    
    ZPIThIndexDirList(final ZPIThIndexRule outerRule){
        super(UUID.randomUUID().toString());
        this.ruleThIndex = outerRule;
    }
    
    
    @Override
    public void run(){
        this.ruleThIndex.setTrueRunnedThreadIndexDirList();
        while( this.ruleThIndex.isRunnedThreadIndexMaker() ){
            try{
                System.out.println(" sleep sleep sleep sleep sleep sleep sleep sleep sleep sleep      indexDirList");
                this.sleep(1000*5);
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        ThreadLocal<ZPIThDirListBusReaded> thDirListBusDataReaded = new ThreadLocal<ZPIThDirListBusReaded>();
        ThreadLocal<ZPIThDirListBusWrited> thDirListBusDataWrited = new ThreadLocal<ZPIThDirListBusWrited>();
        ThreadLocal<ZPIThDirListRule> thDirListRule = new ThreadLocal<ZPIThDirListRule>();
        ThreadLocal<ZPIThDirListState> thDirListState = new ThreadLocal<ZPIThDirListState>();
        ThreadLocal<ZPIThDirListStatistic> thDirListStatistic = new ThreadLocal<ZPIThDirListStatistic>();
        System.out.println("                                                                                    indexDirList");
        //Rule create
        //State create
        /**
         * ThDirListWorkRead implements Runnable
         * ThDirListWorkWrite implements Runnable
         * create and set in ThDirListRule
         * also send ThDirListRule into ThDirListManager constructor
         */
        ThreadLocal<ZPIThDirListWorkRead> thDirListWorkRead = new ThreadLocal<ZPIThDirListWorkRead>();
        ThreadLocal<ZPIThDirListWorkWrite> thDirListWorkWrite = new ThreadLocal<ZPIThDirListWorkWrite>();
        ThreadLocal<ZPIThDirListWorkManager> thDirListManager = new ThreadLocal<ZPIThDirListWorkManager>();
        try{    
            /**
             * @todo this.ruleThIndex.getIndexState().getBusJobForRead(); add new bus into ThDirListState
             */
            ZPIThDirListBusReaded thDirListBusReaded = new ZPIThDirListBusReaded();
            thDirListBusDataReaded.set(thDirListBusReaded);
            ZPIThDirListBusWrited thDirListBusWrited = new ZPIThDirListBusWrited();
            thDirListBusDataWrited.set(thDirListBusWrited);
            
            ZPIThDirListRule thDirListRuleObject = new ZPIThDirListRule(this.ruleThIndex);
            System.out.println(ZPIThIndexDirList.class.getCanonicalName() 
                    + " create rule object " 
                    + thDirListRuleObject.toString());
            thDirListRule.set(thDirListRuleObject);
            
            ZPIThDirListState thDirListStateObject = new ZPIThDirListState();
            thDirListState.set(thDirListStateObject);
            //what is that
            thDirListState.get().setBusJobForSendToIndexWord(this.ruleThIndex.getIndexState().getBusJobForRead());
            thDirListState.get().setBusJobForRead(thDirListBusDataReaded.get());
            thDirListState.get().setBusJobForWrite(thDirListBusDataWrited.get());
            
            thDirListRule.get().setDirListState(thDirListState.get());
            //Statistic into State
            //
            
            ZPIThDirListStatistic thDirListStatisticObject = new ZPIThDirListStatistic();
            thDirListStatistic.set(thDirListStatisticObject);
            thDirListRule.get().setDirListCounter(thDirListStatistic.get());
            /**
             * @todo
             * ThDirListBusReaded Queue set into ThDirListState
             * ThDirListBusWrited Queue set into ThDirListState
             * ThDirListRule insert into all Runnable workers by his constructor
             * after create Runnable workers set him in ThDirListRule class fields
             * ThDirListManager implements from Runnable
             * ThDirListManager.doIndexStorage() release in ThDirListLogicManager
             * insert from ThDirListLogicManager queue into ThDirListBusReaded
             * release in ThDirListWorkRead and ThDirListLogicRead job execution
             * from ThDirListBusReaded Queue provided by ThDirListRule
             */
            
            thDirListWorkRead.set(new ZPIThDirListWorkRead(thDirListRuleObject));
            thDirListRule.get().setDirListWorkReader(thDirListWorkRead.get());
            
            thDirListWorkWrite.set(new ZPIThDirListWorkWrite(thDirListRuleObject));
            thDirListRule.get().setDirListWorkWriter(thDirListWorkWrite.get());
            
            //thDirListRule.get().runReadFromDirList();
            
            thDirListRule.get().runWriteToDirList();
            
            //need run into ThDirListWorkerManager
            thDirListManager.set(new ZPIThDirListWorkManager(thDirListRuleObject));
            thDirListRule.get().setDirListWorkManager(thDirListManager.get());
            thDirListRule.get().runManagerDirListWorkers();
            this.ruleThIndex.setFalseRunnedThreadIndexDirList();
        } finally {
            thDirListBusDataReaded.remove();
            thDirListBusDataWrited.remove();
            thDirListRule.remove();
            thDirListState.remove();
            thDirListStatistic.remove();
            thDirListWorkRead.remove();
            thDirListWorkWrite.remove();
            thDirListManager.remove();  
        }
    }
    
}
