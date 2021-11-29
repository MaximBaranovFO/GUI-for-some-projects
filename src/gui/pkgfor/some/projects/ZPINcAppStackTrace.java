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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcAppStackTrace {
    protected static ArrayList<String> getAllStack(){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        
        Map<Thread, StackTraceElement[]> allStackTraces = 
                Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> allStackTrace
                : allStackTraces.entrySet()) {
            listStrToRet.addAll(
                    getThreadInfo(allStackTrace.getKey()));
            listStrToRet.addAll(
                    getStackTraceInfo(allStackTrace.getValue()));
        }
        return listStrToRet;
    }
    private static ArrayList<String> getThreadInfo(Thread inFuncThread){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        String strToOut = "";
        long id = inFuncThread.getId();
        String name = inFuncThread.getName();
        int priority = inFuncThread.getPriority();
        String stateName = inFuncThread.getState().name();
        strToOut = ZPINcStrLogMsgField.THREAD.getStr()
                + ZPINcStrLogMsgField.ID.getStr()
                + Long.toString(id)
                + ZPINcStrLogMsgField.NAME.getStr()
                + name
                + ZPINcStrLogMsgField.PRIORITY.getStr()
                + Integer.toString(priority)
                + ZPINcStrLogMsgField.STATE.getStr()
                + ZPINcStrLogMsgField.NAME.getStr()
                + stateName;
        listStrToRet.add(strToOut);
        ThreadGroup threadGroup = inFuncThread.getThreadGroup();
        listStrToRet.addAll(getThreadGroupInfo(threadGroup));
        return listStrToRet;
    }
    private static ArrayList<String> getThreadGroupInfo(ThreadGroup inFuncThreadGroup){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        String strToOut = "";
        int activeCount = inFuncThreadGroup.activeCount();
        int activeGroupCount = inFuncThreadGroup.activeGroupCount();
        int maxPriority = inFuncThreadGroup.getMaxPriority();
        String name = inFuncThreadGroup.getName();
        boolean daemon = inFuncThreadGroup.isDaemon();
        String strDaemon = daemon ? "true" : "false";
        boolean destroyed = inFuncThreadGroup.isDestroyed();
        String strDestroyed = destroyed ? "true" : "false";
        strToOut = ZPINcStrLogMsgField.THREAD_GROUP.getStr()
                + ZPINcStrLogMsgField.NAME.getStr()
                + name
                + ZPINcStrLogMsgField.MAX.getStr()
                + ZPINcStrLogMsgField.PRIORITY.getStr()
                + Integer.toString(maxPriority)
                + ZPINcStrLogMsgField.ACTIVE.getStr()
                + ZPINcStrLogMsgField.COUNT.getStr()
                + Integer.toString(activeCount)
                + ZPINcStrLogMsgField.ACTIVE.getStr()
                + ZPINcStrLogMsgField.GROUP.getStr()
                + ZPINcStrLogMsgField.COUNT.getStr()
                + Integer.toString(activeGroupCount)
                + ZPINcStrLogMsgField.IS.getStr()
                + ZPINcStrLogMsgField.DAEMON.getStr()
                + strDaemon
                + ZPINcStrLogMsgField.IS.getStr()
                + strDestroyed
                + ZPINcStrLogMsgField.DESTROYED.getStr();
        
        listStrToRet.add(strToOut);
        
        return listStrToRet;
    }
    private static ArrayList<String> getStackTraceInfo(StackTraceElement[] inFuncStackTrace){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        String strToOut = "";
        int idx = 0;
        String strToOutPref = ZPINcStrLogMsgField.STACK.getStr()
            + ZPINcStrLogMsgField.TRACE.getStr()
            + ZPINcStrLogMsgField.ELEMENT.getStr();
        for (StackTraceElement stackItem : inFuncStackTrace) {

            Class<?> classItem = stackItem.getClass();
            strToOut = strToOutPref
                    + ZPINcStrLogMsgField.NUM.getStr() 
                    + idx
                    + ZPINcStrLogMsgField.CLASSNAME.getStr()
                    + stackItem.getClassName();
            listStrToRet.add(strToOut);
            
            ArrayList<String> declMeth = getDeclaredMethodsInfo(classItem);
            for (String strMeth : declMeth) {
                strToOut = strToOutPref
                    + ZPINcStrLogMsgField.NUM.getStr() 
                    + idx
                    + strMeth;
                listStrToRet.add(strToOut);
            }
            
            ArrayList<String> declField = getDeclaredFieldsInfo(classItem);
            for (String strField : declField) {
                strToOut = strToOutPref
                    + ZPINcStrLogMsgField.NUM.getStr() 
                    + idx
                    + strField;
                listStrToRet.add(strToOut);
            }
            
            idx++;
        }
        return listStrToRet;
    }
    private static ArrayList<String> getDeclaredMethodsInfo(Class<?> classInFunc){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        String strToOut = "";
        Method[] declaredMethods = classInFunc.getClass().getDeclaredMethods();
        int methodIdx = 0;
        for (Method declaredMethod : declaredMethods) {
            String strName = declaredMethod.getName();
            strToOut = ZPINcStrLogMsgField.METHOD.getStr()
                + ZPINcStrLogMsgField.NUM.getStr()
                + Integer.toString(methodIdx)
                + ZPINcStrLogMsgField.NAME.getStr()
                + strName;
            listStrToRet.add(strToOut);
            Parameter[] parameters = declaredMethod.getParameters();
            int paramIdx = 0;
            for (Parameter parameter : parameters) {
                String paramName = parameter.getName();
                String paramType = parameter.getType().getCanonicalName();
                strToOut = ZPINcStrLogMsgField.PARAMETER.getStr()
                + ZPINcStrLogMsgField.NUM.getStr()
                + Integer.toString(paramIdx)
                + ZPINcStrLogMsgField.NAME.getStr()
                + paramName
                + ZPINcStrLogMsgField.TYPE.getStr()
                + paramType;
                paramIdx++;
            }
            methodIdx++;
        }
        return listStrToRet;
    }
    private static ArrayList<String> getDeclaredFieldsInfo(Class<?> classInFunc){
        ArrayList<String> listStrToRet = new ArrayList<String>();
        String strToOut = "";
        int fieldIdx = 0;
        Field[] declaredFields = classInFunc.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            strToOut = ZPINcStrLogMsgField.FIELD.getStr()
                + ZPINcStrLogMsgField.NUM.getStr()
                + fieldIdx;
            try {
                boolean boolAccValFlag = declaredField.isAccessible();
                declaredField.setAccessible(true);
                strToOut = strToOut
                    + ZPINcStrLogMsgField.TYPE.getStr()
                    + declaredField.getType().getCanonicalName();
                
                strToOut = strToOut
                    + ZPINcStrLogMsgField.NAME.getStr()
                    + declaredField.getName();
                
                strToOut = strToOut
                    + ZPINcStrLogMsgField.VALUE.getStr()
                    + declaredField.get(classInFunc.getClass()).toString();
                declaredField.setAccessible(boolAccValFlag);
            } catch (Exception ex){
                strToOut = strToOut
                    + ZPINcStrLogMsgField.EXCEPTION_MSG.getStr()
                    + ex.getMessage();
            }
            listStrToRet.add(strToOut);
            fieldIdx++;
        }
        return listStrToRet;
    }
}
