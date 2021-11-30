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
public class ZPIThIndexStorageWord extends Thread{
    private ZPIThIndexRule ruleZPIThIndex;
    
    ZPIThIndexStorageWord(ZPIThIndexRule outerRule){
        super(UUID.randomUUID().toString());
        this.ruleZPIThIndex = outerRule;
        //ZPIThread.currentZPIThread().setName(UUID.randomUUID().toString());
    }
    
    @Override
    public void run(){
        System.out.println(ZPIThIndexStorageWord.class.getCanonicalName() 
                + " do it +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        ZPIThIndexState indexState = this.ruleZPIThIndex.getIndexState();
        //init Bus
        ZPIThStorageWordBusInput thStorageWordBusInput = new ZPIThStorageWordBusInput();
        ZPIThStorageWordBusOutput thStorageWordBusOutput = new ZPIThStorageWordBusOutput();
        ZPIThStorageWordBusOutput thStorageLongWordBusOutput = new ZPIThStorageWordBusOutput();
        ZPIThStorageWordBusWriter thStorageLongWordBusWriter = new ZPIThStorageWordBusWriter();
        ZPIThStorageWordBusReader thStorageLongWordBusReader = new ZPIThStorageWordBusReader();
        //init State
        ZPIThStorageWordState thStorageWordState = new ZPIThStorageWordState();
        thStorageWordState.setBusJobForStorageWordRouterJob(thStorageWordBusInput);
        thStorageWordState.setBusJobForWordWrite(thStorageWordBusOutput);
        thStorageWordState.setBusJobForLongWordWrite(thStorageLongWordBusOutput);
        thStorageWordState.setBusJobForStorageWordRouterJobToWriter(thStorageLongWordBusWriter);
        thStorageWordState.setBusJobForStorageWordRouterJobToReader(thStorageLongWordBusReader);
        ZPIThStorageWordStatusMainFlow thStorageWordStatusMainFlow = new ZPIThStorageWordStatusMainFlow();
        ZPIThStorageWordBusReadedFlow thStorageWordFlowRead = new ZPIThStorageWordBusReadedFlow(thStorageWordStatusMainFlow);
        //init Rule
        ZPIThStorageWordRule thStorageWordRule = new ZPIThStorageWordRule(this.ruleZPIThIndex);
        //set StorageWord Rule in indexState
        indexState.setRuleStorageWord(thStorageWordRule);
        thStorageWordRule.setStorageWordState(thStorageWordState);
        thStorageWordRule.setStorageWordStatusMainFlow(thStorageWordStatusMainFlow);
        thStorageWordState.setStorageWordFlowReaded(thStorageWordFlowRead);
        //init Workers
        ZPIThStorageWordWorkFilter thStorageWordWorkFilter = new ZPIThStorageWordWorkFilter(thStorageWordRule);
        thStorageWordRule.setStorageWordWorkFilter(thStorageWordWorkFilter);
        ZPIThStorageWordWorkRouter thStorageWordWorkRouter = new ZPIThStorageWordWorkRouter(thStorageWordRule);
        thStorageWordRule.setStorageWordWorkRouter(thStorageWordWorkRouter);
        ZPIThStorageWordWorkWrite thStorageWordWorkWrite = new ZPIThStorageWordWorkWrite(thStorageWordRule);
        thStorageWordRule.setStorageWordWorkWrite(thStorageWordWorkWrite);
        ZPIThStorageWordWorkRead thStorageWordWorkRead = new ZPIThStorageWordWorkRead(thStorageWordRule);
        thStorageWordRule.setStorageWordWorkRead(thStorageWordWorkRead);
        
        // run Workers
        thStorageWordRule.runFilterStorageWordWork();
        thStorageWordRule.runRouterStorageWordWork();
        
        thStorageWordRule.runReadStorageWordWork();
        thStorageWordRule.runWriteStorageWordWork();
    }
    
}
