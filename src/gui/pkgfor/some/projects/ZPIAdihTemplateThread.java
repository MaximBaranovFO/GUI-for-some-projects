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
public class ZPIAdihTemplateThread extends Thread{
    private final Integer numberProcessIndexSystem;
    private final ZPIAdimRule ruleAdim;
    private final ZPIAdilState adilState;
    private final Runnable runnerForWork;
    /**
     * 
     * @param processIndexSystemNumber
     * @param outerRule
     * @param outerRunner 
     * @throws UnsupportedOperationException
     */
    public ZPIAdihTemplateThread(final Integer processIndexSystemNumber,
            final ZPIAdimRule outerRule,
            final Runnable outerRunner){
        super(outerRunner, UUID.randomUUID().toString());
        if( outerRule == null ){
            throw new UnsupportedOperationException(ZPIAdimRule.class.getCanonicalName() 
                    + " object for set in "
                    + ZPIAdihTemplateThread.class.getCanonicalName()
                    + " is null");
        }
        this.ruleAdim = (ZPIAdimRule) outerRule;
        if( outerRunner == null ){
            throw new UnsupportedOperationException(Runnable.class.getCanonicalName() 
                    + " object for set in "
                    + ZPIAdihTemplateThread.class.getCanonicalName()
                    + " is null");
        }
        this.runnerForWork = (Runnable) outerRunner;
        if( processIndexSystemNumber == null ){
            throw new UnsupportedOperationException("processIndexSystemNumber for set in "
                    + ZPIAdihTemplateThread.class.getCanonicalName()
                    + " is null");
        }
        if( processIndexSystemNumber < 0 ){
            throw new UnsupportedOperationException("processIndexSystemNumber for set in "
                    + ZPIAdihTemplateThread.class.getCanonicalName()
                    + " is not natural ( processIndexSystemNumber < 0 (Zero) )");
        }
        this.numberProcessIndexSystem = processIndexSystemNumber;
        this.adilState = (ZPIAdilState) this.ruleAdim.getZPIAdilRule().getZPIAdilState();
    }
    
    @Override
    public void run() {
        String msgToLog = ZPIAdilConstants.INFO_LOGIC_POSITION
                + ZPIAdilConstants.CANONICALNAME
                + ZPIAdihTemplateThread.class.getCanonicalName()
                + ZPIAdilConstants.METHOD
                + "run()";
        try {
            this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.START);
            try {
                this.runnerForWork.run();
            } catch(Throwable trEx) {
                this.adilState.putLogLineByProcessNumberMsgExceptions(
                        this.numberProcessIndexSystem, 
                        Throwable.class.getCanonicalName(),
                        ZPIAdihTemplateThread.class.getCanonicalName(), 
                        this.runnerForWork.getClass().getCanonicalName() + ".run()", 
                        trEx.getMessage());
                this.adilState.logStackTrace(this.numberProcessIndexSystem);
                //@todo log stack trace to other dir
                //trEx.getStackTrace();
            }
        /*this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                        msgToLog
                        + ZPIAdilConstants.STATE
                        + ZPIAdilConstants.VARNAME
                        + "typeWordOfBusOutputFunc"
                        + ZPIAdilConstants.VARVAL
                        + String.valueOf(typeWordOfBusOutputFunc)
                        + ZPIAdilConstants.VARNAME
                        + "itemKey"
                        + ZPIAdilConstants.VARVAL
                        + itemKey
                        + ZPIAdilConstants.VARNAME
                        + "hexTagName"
                        + ZPIAdilConstants.VARVAL
                        + hexTagName
                        + ZPIAdilConstants.VARNAME
                        + "subString"
                        + ZPIAdilConstants.VARVAL
                        + subString
                    );*/
        } finally {
            this.adilState.putLogLineByProcessNumberMsg(this.numberProcessIndexSystem, 
                msgToLog
                + ZPIAdilConstants.FINISH);
        }
    }
}
