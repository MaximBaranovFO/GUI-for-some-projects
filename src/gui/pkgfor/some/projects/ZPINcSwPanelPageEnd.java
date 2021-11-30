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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwPanelPageEnd {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * @return
     */
    protected static JPanel getPanel(ZPINcSwGUIComponentStatus lComp){
        JPanel southPanel = new JPanel();
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageEnd();
        lComp.putComponents(componentPath, southPanel);
        Border southBorder = BorderFactory.createTitledBorder("SOUTH panel");
        southPanel.setBorder(southBorder);
        
        southPanel.add(
            ZPINcSwStatusPanel.addProgressBar(lComp)
        );
        JButton buttonGetStack = ZPINcSwGUIComponent.createButton("Show", null, "Show now StackTrace");
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageEndButtonGetStack();
        lComp.putComponents(componentPath, buttonGetStack);
        
        buttonGetStack.addActionListener(new ActionListener(){
            public void  actionPerformed(ActionEvent e){
                ZPINcSwStatusPanel.indicationStartProgressBar(lComp);
                ZPINcThWorkerUpGUITreeState.stateTreeAddChildren(lComp);
                ZPINcSwStatusPanel.indicationStopProgressBar(lComp);
            }
        });
        southPanel.add(buttonGetStack);
        toLALRgetPanel();
        return southPanel;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwPanelPageEnd#getPanel(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRgetPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelPageEndgetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_PAGEEND.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_PANEL_LINESTART.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
}
