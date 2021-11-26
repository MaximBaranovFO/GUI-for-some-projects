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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcLogFileManager {
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#readFromLog() }
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#writeLogLines(java.util.TreeMap) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getTreeNodes(javax.swing.tree.DefaultMutableTreeNode) }
     * </ul>
     * @return 
     */
    protected static File getLogFile(){
        String strAppDataSubDir = NcIdxFileManager.getOrCreateAppDataSubDir();
        String strLogFilePath = 
                NcIdxFileManager.strPathCombiner(strAppDataSubDir,
                NcStrFileDir.FILE_APP_LOG.getStr());
        File fileLog = new File(strLogFilePath);
        if( !NcIdxFileManager.fileExistRWAccessChecker(fileLog) ){
            createLogFile(strLogFilePath);
        }
        return fileLog;
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#createLogFile(java.lang.String) }
     * </ul>
     * @param ncStrCfgPath 
     */
    private static void createLogFile(String ncStrCfgPath){
        //@todo recode java.time.LocalDateTime.now().toString(); for 1.7 new Date(System.nanotime())
        String strTime = java.time.LocalDateTime.now().toString();
        String text = NcStrLogMsgField.TIME.getStr() + strTime;
        String strMsg = text + NcStrLogMsgField.MSG_INFO.getStr()
            + NcStrLogMsgText.LOG_CREATE.getStr();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ncStrCfgPath)))
        {
            bw.write(strMsg);
            bw.newLine();
        }
        catch(IOException ex){
            String strExitMsg = "Can not create log file in:\n"
                    + ncStrCfgPath + "\n";
            NcAppHelper.appExitWithMessage(strExitMsg + ex.getMessage());
        }
        ZPINcfvRunVariables.setCanRecord();
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcAppHelper#outMessageToAppLogFile(java.lang.String) }
     * </ul>
     * @param strToLog 
     */
    protected static void putToLogStr(String strToLog){
        if( strToLog.length() > 0 ){
            
            TreeMap<Long, String> strCurrentLog = new TreeMap<Long, String>();
            strCurrentLog.putAll(readFromLog());
            int idx = strCurrentLog.size();

            if( ZPINcfvRunVariables.isOutToLogNewRecordAppend() ){
                String strTime = java.time.LocalDateTime.now().toString();
                String text = NcStrLogMsgField.TIME.getStr() + strTime;
                String strMsg = text + NcStrLogMsgField.MSG_INFO.getStr()
                    + NcStrLogMsgText.LOG_RECORD_APPEND.getStr();
                strCurrentLog.put((long) idx, strMsg);
                idx++;
            }
            strCurrentLog.put((long) idx, strToLog);
            idx++;
            writeInLogLimitLines(strCurrentLog);
        }
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcAppHelper#outMessageToAppLogFile(java.lang.String) }
     * </ul>
     * @param toLogStr 
     */
    protected static void putToLog(TreeMap<Long, String> toLogStr){
        if( toLogStr.size() > 0 ){
            TreeMap<Long, String> strCurrentLog = new TreeMap<Long, String>();
            strCurrentLog.putAll(readFromLog());
            int idx = strCurrentLog.size();

            if( ZPINcfvRunVariables.isOutToLogNewRecordAppend() ){
                String strTime = java.time.LocalDateTime.now().toString();
                String text = NcStrLogMsgField.TIME.getStr() + strTime;
                String strMsg = text + NcStrLogMsgField.MSG_INFO.getStr()
                    + NcStrLogMsgText.LOG_RECORD_APPEND.getStr();
                strCurrentLog.put((long) idx, strMsg);
                idx++;
            }
            for(Map.Entry<Long, String> strItem : toLogStr.entrySet()){
                strCurrentLog.put((long) idx, strItem.getValue());
                idx++;
            }
            writeInLogLimitLines(strCurrentLog);
        }
        
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#putToLogStr(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#putToLog(java.util.TreeMap) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getTreeNodes(javax.swing.tree.DefaultMutableTreeNode) }
     * </ul>
     * @return 
     */
    protected static TreeMap<Long, String> readFromLog(){
        File fileLog = getLogFile();
        TreeMap<Long, String> strForReturn = new TreeMap<Long, String>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileLog)))
        {
            String s;
            long strIdx = 0;
            while((s=br.readLine())!=null){
                strForReturn.put(strIdx, s);
                strIdx++;
            }
        }
         catch(IOException ex){
            NcAppHelper.logException(
                NcLogFileManager.class.getCanonicalName(), ex);
        }
        return strForReturn;
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#putToLogStr(java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#putToLog(java.util.TreeMap) }
     * </ul>
     * @param toLogAllStr 
     */
    private static void writeInLogLimitLines(TreeMap<Long, String> toLogAllStr){
        int logCountLines = ZPINcfvRunVariables.getLogLinesCount();
        if( (toLogAllStr.size()) > logCountLines ){
                long idxLimit = toLogAllStr.size() - logCountLines;
                if( idxLimit < 1 ){
                    idxLimit = 0;
                }
                if( !toLogAllStr.containsKey(idxLimit) ){
                    idxLimit = toLogAllStr.firstKey();
                }
                TreeMap<Long, String> strNewLog = new TreeMap<Long, String>();
                strNewLog.putAll(toLogAllStr.tailMap(idxLimit));
                toLogAllStr.clear();
                toLogAllStr = null;
                toLogAllStr = new TreeMap<Long, String>();
                toLogAllStr.putAll(strNewLog);
        }
        writeLogLines(toLogAllStr);
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcLogFileManager#writeInLogLimitLines(java.util.TreeMap) }
     * </ul>
     * @param toLogStr 
     */
    private static void writeLogLines(TreeMap<Long, String> toLogStr){
        File fileLog = getLogFile();
        if( toLogStr.size() > 0 ){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog)))
            {
                for( Map.Entry<Long, String> itemStr : toLogStr.entrySet() ){
                    bw.write(itemStr.getValue());
                    bw.newLine();
                }
            }
            catch(IOException ex){
                String strMsg = "Can not create log file in:\n"
                        + NcIdxFileManager.getStrCanPathFromFile(fileLog) + "\n";
                NcAppHelper.appExitWithMessage(strMsg + ex.getMessage());
            }
        }
    }
}
