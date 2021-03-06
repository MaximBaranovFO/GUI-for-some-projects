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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcLogLogicGUI {
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwMainMenu#getMainMenu(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    protected static void NcSwMainMenuGetMainMenu(){
        if( ZPINcfvRunVariables.isLALRNcSwMainMenuMainMenu() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_JMENUBAR.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_JMENU.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    /**
     * Used in 
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelCenter#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    protected static void NcSwPanelCenterGetPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelCentergetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_CENTER.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_SET_VISIBLE_GUI.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
}
