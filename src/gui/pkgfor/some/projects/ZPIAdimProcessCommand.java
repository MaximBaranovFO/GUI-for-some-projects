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
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAdimProcessCommand {
    /**
     * Check list of commands if it is valide return list result numbers
     * {@code <Integer commandRunnerInterpritation, Integer numberProcess>}
     * @param ruleAdimInputed
     * @param numberProcessInputed
     * @return 
     */
    protected static ConcurrentSkipListMap<Integer, Integer> commandDetector(ZPIAdimRule ruleAdimInputed, Integer numberProcessInputed){
        
        ConcurrentSkipListMap<Integer, Integer> resultProcessorCommand = new ConcurrentSkipListMap<Integer, Integer>();
        if( ruleAdimInputed == null ){
            return resultProcessorCommand;
        }
        if( numberProcessInputed == null ){
            return resultProcessorCommand;
        }
        Integer numberProcess = numberProcessInputed;
        ZPIAdimRule ruleAdimFunc = (ZPIAdimRule) ruleAdimInputed;
        ZPIAdilState adilStateFunc = (ZPIAdilState) ruleAdimFunc.getZPIAdilRule().getZPIAdilState();
        String msgLog = new String().concat(ZPIAdilConstants.CANONICALNAME
                .concat(ZPIAdimProcessCommand.class.getCanonicalName()))
                .concat(ZPIAdilConstants.METHOD)
                .concat("commandDetector()");
        adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, msgLog.concat(ZPIAdilConstants.START));
        
        ZPIAdifControlFlag adifControlFlag = ruleAdimFunc.getZPIAdifControlFlag();
        UUID runnerId = UUID.fromString(Thread.currentThread().getName());
        adifControlFlag.createForRunnerUuidFlagList(runnerId);
        
        
        
        
        ZPIAdibProcessCommand adibProcessCommand = (ZPIAdibProcessCommand) ruleAdimFunc.getZPIAdibProcessCommand();
        ConcurrentSkipListMap<Integer, Integer> commandsList = adibProcessCommand.getCommandsList();
        Boolean commandListValide = adibProcessCommand.isCommandListValide(commandsList);
        
        Integer commandType = 0;
        Integer commandQueueSize = 0;
        Integer commandPoll = Integer.MIN_VALUE;
        Integer commandOldValue =  Integer.MIN_VALUE;
        Integer startCommandCode = commandsList.get(0);
        Integer stopCommandCode = commandsList.get(1);
        Integer setPauseFromUserCommandCode = commandsList.get(2);
        Integer cancelPauseFromUserCommandCode = commandsList.get(3);
        Boolean isSetPauseFromUser = adifControlFlag.getRunnerFlagByNumber(runnerId, 1);
        adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "isSetPauseFromUser",
                                            String.valueOf(isSetPauseFromUser),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("get flags from flow control"));
        try {
            
            /**
             * @todo if command part code into static procedures
             * worker state flags into flow control objects...
             */
            if( commandListValide ){
                for( commandType = 0; commandType < 3; commandType++ ){
                    do {
                        //readNextCommandAfterSleepPause: {
                            if( isSetPauseFromUser ){
                                commandOldValue = commandPoll;
                            }
                            commandPoll = adibProcessCommand.commandPoll(commandType, numberProcess);
                            commandQueueSize = adibProcessCommand.commandSizeQueue(commandType, numberProcess);
                            adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "isSetPauseFromUser",
                                            String.valueOf(isSetPauseFromUser),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandOldValue",
                                            String.valueOf(commandOldValue),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("poll command from processCommand"));
                            if( commandPoll.equals(Integer.MIN_VALUE) ){
                                if( isSetPauseFromUser ){
                                     commandPoll = commandOldValue;
                                }
                            }
                            adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "isSetPauseFromUser",
                                            String.valueOf(isSetPauseFromUser),
                                            "numberProcess",
                                            String.valueOf(numberProcess),
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("new command readed, decode command"));
                            if( isSetPauseFromUser ){
                                if( startCommandCode.equals(commandPoll) ){
                                    isSetPauseFromUser = Boolean.FALSE;
                                    adifControlFlag.changeFlagValueByNumber(runnerId, 1, isSetPauseFromUser);
                                }
                                if( stopCommandCode.equals(commandPoll) ){
                                    isSetPauseFromUser = Boolean.FALSE;
                                    adifControlFlag.changeFlagValueByNumber(runnerId, 1, isSetPauseFromUser);
                                }
                                if( !cancelPauseFromUserCommandCode.equals(commandPoll) ){
                                    commandPoll = setPauseFromUserCommandCode;
                                }
                            }
                            if( startCommandCode.equals(commandPoll) ){
                                adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("commandStart"));
                                adilStateFunc.logStackTrace(numberProcess);
                                //@todo ZPIAdimFactory logic procedure key number put into returned list for exit from this procedure
                                resultProcessorCommand.put(0, numberProcess);
                            }
                            if( stopCommandCode.equals(commandPoll) ){
                                adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("commandStop"));
                                adilStateFunc.logStackTrace(numberProcess);
                                //@todo ZPIAdimFactory logic procedure key number put into returned list for exit from this procedure
                                resultProcessorCommand.put(1, numberProcess);
                            }
                            if( setPauseFromUserCommandCode.equals(commandPoll) ){
                                isSetPauseFromUser = Boolean.TRUE;
                                adifControlFlag.changeFlagValueByNumber(runnerId, 1, isSetPauseFromUser);
                                adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("SetPauseFromUser goto sleep"));
                                adilStateFunc.logStackTrace(numberProcess);
                                ZPIAdimFactory.workerSleep();
                                adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("SetPauseFromUser wakeup"));
                                adilStateFunc.logStackTrace(numberProcess);
                                //break readNextCommandAfterSleepPause;
                            }
                            if( cancelPauseFromUserCommandCode.equals(commandPoll) ){
                                isSetPauseFromUser = Boolean.FALSE;
                                adifControlFlag.changeFlagValueByNumber(runnerId, 1, isSetPauseFromUser);
                                adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, 
                                        msgLog.concat(ZPIAdilHelper.variableNameValue(new String[]{
                                            "commandPoll",
                                            String.valueOf(commandPoll),
                                            "commandQueueSize",
                                            String.valueOf(commandQueueSize),
                                        })).concat(ZPIAdilConstants.DESCRIPTION).concat("CancelPauseFromUser"));
                                adilStateFunc.logStackTrace(numberProcess);
                                resultProcessorCommand.put(0, numberProcess);
                            }
                        //}
                    }while( ((int) commandQueueSize > 0) || (setPauseFromUserCommandCode.equals(commandPoll)) );
                }
            }
            return resultProcessorCommand;
        } finally {
            adilStateFunc.putLogLineByProcessNumberMsgInfo(numberProcess, msgLog.concat(ZPIAdilConstants.FINISH));
            adifControlFlag = null;
            runnerId = null;
            numberProcess = null;
            ruleAdimFunc = null;
            adilStateFunc = null;
            adibProcessCommand = null;
            commandsList = null;
            commandListValide = null;
            ZPIAdihUtilization.utilizeStringValues(new String[] {msgLog});
        }
    }
}
