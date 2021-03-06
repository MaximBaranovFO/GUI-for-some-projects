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
public class ZPIThIndexWord extends Thread{
    private ZPIThIndexRule ruleThIndex;
    ZPIThIndexWord(ZPIThIndexRule outerRule){
        super(UUID.randomUUID().toString());
        this.ruleThIndex = outerRule;
        //Thread.currentThread().setName(UUID.randomUUID().toString());
    }
    
    @Override
    public void run(){
        System.out.println(ZPIThIndexWord.class.getCanonicalName() 
                + " do it +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //ThIndexState indexState = this.ruleThIndex.getIndexState();
        ZPIThWordRule thWordRule = new ZPIThWordRule(this.ruleThIndex);
        //init State
        ZPIThWordStatusMainFlow wordStatusMainFlow = new ZPIThWordStatusMainFlow();
        thWordRule.setWordStatusMainFlow(wordStatusMainFlow);
        ZPIThWordBusFlowEvent busWordRouterJobToReaderOuter = new ZPIThWordBusFlowEvent(wordStatusMainFlow);
        ZPIThWordBusFlowEvent busWordRouterJobToWriterOuter = new ZPIThWordBusFlowEvent(wordStatusMainFlow);
        ZPIThWordBusFlowEvent stateWordReadedOuter = new ZPIThWordBusFlowEvent(wordStatusMainFlow);
        ZPIThWordState thWordState = new ZPIThWordState(thWordRule);
        thWordRule.setWordState(thWordState);
        thWordState.setBusJobForWordRouterJobToReader(busWordRouterJobToReaderOuter);
        thWordState.setBusJobForWordRouterJobToWriter(busWordRouterJobToWriterOuter);
        thWordState.setWordFlowReaded(stateWordReadedOuter);
        thWordState.newInstanceEventIndex(thWordRule);
        thWordState.newInstanceEventLogic(thWordRule);
        //ThWordBusReadedFlow thWordFlowRead = new ThWordBusReadedFlow(wordStatusMainFlow);
        //init Rule
        
        
        
        
        //init Workers
        
        ZPIThWordWorkRouter thWordWorkRouter = new ZPIThWordWorkRouter(thWordRule);
        thWordRule.setWordWorkRouter(thWordWorkRouter);
        ZPIThWordWorkWrite thWordWorkWrite = new ZPIThWordWorkWrite(thWordRule);
        thWordRule.setWordWorkWrite(thWordWorkWrite);
        ZPIThWordWorkRead thWordWorkRead = new ZPIThWordWorkRead(thWordRule);
        thWordRule.setWordWorkRead(thWordWorkRead);
        
        //set Word Rule in indexState
        
        // run Workers
        thWordRule.runRouterWordWork();
        thWordRule.runReadWordWork();
        thWordRule.runWriteWordWork();
        System.out.println(thWordWorkRouter.toString()
                + " do it +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
    }
    
}
