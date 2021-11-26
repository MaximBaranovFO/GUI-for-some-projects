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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAdilWorkerWrite implements Runnable {
    private ZPIAdilRule ruleAdilWrite;
    
    ZPIAdilWorkerWrite(final ZPIAdilRule outerRuleAdilWrite){
        this.ruleAdilWrite = outerRuleAdilWrite;
    }
    
    @Override
    public void run(){
        System.out.println(ZPIAdilWorkerWrite.class.getCanonicalName() 
                + " run and say " 
                + this.ruleAdilWrite.toString());
        this.ruleAdilWrite.setTrueRunnerAdilWorkWrite();
        ZPIAdilLogicWrite logicLogLinesWrite;
        try{
            logicLogLinesWrite = new ZPIAdilLogicWrite();
            logicLogLinesWrite.doWriteLinesIntoLog(this.ruleAdilWrite);
        } finally {
            logicLogLinesWrite = null;
            this.ruleAdilWrite.setFalseRunnerAdilWorkWrite();
            this.ruleAdilWrite.needNextRunLogger();
        }
    }
    
}
