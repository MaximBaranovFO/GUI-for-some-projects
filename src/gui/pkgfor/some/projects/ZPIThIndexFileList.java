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
public class ZPIThIndexFileList extends Thread{
    private ZPIThIndexRule ruleThIndex;
    ZPIThIndexFileList (ZPIThIndexRule outerRule){
        super(UUID.randomUUID().toString());
        this.ruleThIndex = outerRule;
        //Thread.currentThread().setName(UUID.randomUUID().toString());
    }
    
    @Override
    public void run(){
        System.out.println(ZPIThIndexFileList.class.getCanonicalName() 
                + " do it +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        ZPIThDirListBusReaded busJobForRead = this.ruleThIndex.getIndexState().getBusJobForRead();
        System.out.println(busJobForRead.getQueueSize().toString()
                + " do it +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        ZPIThFileListBusToNext thFileListBusToNext = new ZPIThFileListBusToNext();
        ZPIThFileListState thFileListState = new ZPIThFileListState();
        thFileListState.setBusJobForFileListToNext(thFileListBusToNext);

        ZPIThFileListRule thFileListRule = new ZPIThFileListRule(this.ruleThIndex);
        ZPIThIndexState indexState = this.ruleThIndex.getIndexState();
        indexState.setRuleFileList(thFileListRule);
        ZPIThFileListWorkBuild thFileListWorkBuild = new ZPIThFileListWorkBuild(thFileListRule);

        thFileListRule.setFileListState(thFileListState);
        thFileListRule.setFileListWorkBuild(thFileListWorkBuild);

        thFileListRule.runBuildFileListWork();
    }
    
}
