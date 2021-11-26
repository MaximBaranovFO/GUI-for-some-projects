/*
 * Copyright 2018 wladimirowichbiaran.
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
import java.nio.file.Path;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppThWorkDirListRun implements Runnable {
    private ZPIAppThWorkDirListRule innerRuleForDirListWorkers;

    public ZPIAppThWorkDirListRun(final ZPIAppThWorkDirListRule ruleForDirListWorkers) {
        super();
        this.innerRuleForDirListWorkers = ruleForDirListWorkers;
    }
    
    @Override
    public void run() {
        //this.innerRuleForDirListWorkers.startDirlistTacker();
        Boolean needFinishStateDirlistReader = innerRuleForDirListWorkers.getNeedFinishStateDirlistReader();
        Path currentPathForMakeIndex = this.innerRuleForDirListWorkers.getCurrentPathForMakeIndex();
        ThreadLocal<ZPIThLogicDirListWalker> logicWalker = new ThreadLocal<ZPIThLogicDirListWalker>();
        try{
            try{
                logicWalker.set(new ZPIThLogicDirListWalker(this.innerRuleForDirListWorkers));
                logicWalker.get().doReadFsToPipe();
            
                ZPINcAppHelper.outToConsoleIfDevAndParamTrue("ThLogicDirListWalker.doReadFsToPipe end", 
                ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_DIR_LIST_WALKER_DO_READ_FS_TO_PIPE);
            } catch(IOException ex){
                ex.printStackTrace();
            }
        } finally {
                logicWalker.remove();
        }
    }
    
}
