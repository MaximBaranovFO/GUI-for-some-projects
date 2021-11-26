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

import java.net.URI;
import java.nio.file.FileSystem;
import java.util.Map;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppThWorkDirListWrite implements Runnable {
    private AppThWorkDirListRule innerRuleForDirListWorkers;

    public ZPIAppThWorkDirListWrite(AppThWorkDirListRule ruleForDirListWorkers) {
        super();
        this.innerRuleForDirListWorkers = ruleForDirListWorkers;
    }
    
    @Override
    public void run() {
        Boolean needFinishStateDirListWriter = innerRuleForDirListWorkers.getNeedFinishStateDirListWriter();
        //FileSystem fsZipIndexStorage = innerRuleForDirListWorkers.getFsZipIndexStorage();
        
        //NcParamFs dataStorage = NcFsIdxStorageInit.initStorageStructure(fsZipIndexStorage);
        
        ThreadLocal<ZPIThLogicDirListWriter> logicWriter = new ThreadLocal<ZPIThLogicDirListWriter>();
        try{
            logicWriter.set(new ZPIThLogicDirListWriter(this.innerRuleForDirListWorkers));//,
                //dataStorage));
            logicWriter.get().doWriter();
        } finally {
            logicWriter.remove();
        }
        AppObjectsInfo.dumpAllStackToHtml();
    }
    
}
