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
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAdihTemplateRunnable implements Runnable {
    private final Long timeCreation;
    private final UUID objectLabel;
    private final Integer numberProcessIndexSystem;
    private final ZPIAdimRule ruleAdim;
    private final ZPIAdilState adilState;
    /**
     * 
     * @param processIndexSystemNumber
     * @param outerRule 
     * @throws UnsupportedOperationException
     */
    public ZPIAdihTemplateRunnable(final Integer processIndexSystemNumber,
            final ZPIAdimRule outerRule){
        this.timeCreation = System.nanoTime();
        this.objectLabel = UUID.randomUUID();
        if( outerRule == null ){
            throw new UnsupportedOperationException(ZPIAdimRule.class.getCanonicalName() 
                    + " object for set in "
                    + ZPIAdihTemplateRunnable.class.getCanonicalName()
                    + " is null");
        }
        this.ruleAdim = (ZPIAdimRule) outerRule;
        if( processIndexSystemNumber == null ){
            throw new UnsupportedOperationException("processIndexSystemNumber for set in "
                    + ZPIAdihTemplateRunnable.class.getCanonicalName()
                    + " is null");
        }
        if( processIndexSystemNumber < 0 ){
            throw new UnsupportedOperationException("processIndexSystemNumber for set in "
                    + ZPIAdihTemplateRunnable.class.getCanonicalName()
                    + " is not natural ( processIndexSystemNumber < 0 (Zero) )");
        }
        this.numberProcessIndexSystem = processIndexSystemNumber;
        this.adilState = (ZPIAdilState) this.ruleAdim.getAdilRule().getZPIAdilState();
    }
    /**
     * @todo read command into static method of switch (ZPIAdimProcessCommand) class, log recived
     * command and call control object (AdimFactory) method with logic for command do
     * 
     * worker list controled by main class with see threads in stacktrace, app and 
     * after finished all runned workers,
     * log after that
     */
    
    
    @Override
    public void run(){
        ZPIAdifControlFlag adifControlFlag = this.ruleAdim.getZPIAdifControlFlag();
        UUID runnerId = UUID.fromString(Thread.currentThread().getName());
        adifControlFlag.createForRunnerUuidFlagList(runnerId);
        Boolean isDoCommnadStop = Boolean.FALSE;
        Boolean flowFlagIsDoCommnadStop = adifControlFlag.getRunnerFlagByNumber(runnerId, 0);
        if( flowFlagIsDoCommnadStop != null ){
            isDoCommnadStop = flowFlagIsDoCommnadStop;
        }
        String msgToLog = new String().concat(ZPIAdilConstants.CANONICALNAME
                .concat(ZPIAdihTemplateRunnable.class.getCanonicalName()))
                .concat(ZPIAdilConstants.METHOD)
                .concat("run()");
        ConcurrentSkipListMap<Integer, Integer> commandDetectorResult = null;
        Integer decocedCommand;
        Integer commandForProcess;
        try {
            forDoCommandStop: {
                if( !isDoCommnadStop ){
                    this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                        msgToLog
                        + ZPIAdilConstants.START);
                    this.adilState.logStackTrace(this.numberProcessIndexSystem);
                    commandDetectorResult = 
                            ZPIAdimProcessCommand.commandDetector(this.ruleAdim, this.numberProcessIndexSystem);
                    for(Map.Entry<Integer, Integer> itemCommands : commandDetectorResult.entrySet()){
                        commandForProcess = itemCommands.getValue();
                        if( commandForProcess.equals(this.numberProcessIndexSystem) ){
                            decocedCommand = itemCommands.getKey();
                            if( decocedCommand.equals(1) ){
                                isDoCommnadStop = Boolean.TRUE;
                                adifControlFlag.changeFlagValueByNumber(runnerId, 0, isDoCommnadStop);
                                break forDoCommandStop;
                            }
                            if( decocedCommand.equals(0) ){
                                this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                                msgToLog
                                + ZPIAdilConstants.START.concat("command logic start"));
                            }
                        }
                    }
                } else {
                    isDoCommnadStop = Boolean.FALSE;
                    adifControlFlag.changeFlagValueByNumber(runnerId, 0, isDoCommnadStop);
                }
            }
        } finally {
            this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.FINISH);
            commandDetectorResult = null;
        } 
    }
}
