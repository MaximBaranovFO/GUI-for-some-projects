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

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwPanelLineStart {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * @return
     */
    protected static JPanel getPanel(ZPINcSwGUIComponentStatus lComp){
        JPanel westPanel = new JPanel();
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelLineStart();
        lComp.putComponents(componentPath, westPanel);
        Border westBorder = BorderFactory.createTitledBorder("WEST panel");
        westPanel.setBorder(westBorder);
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
        
        ArrayList<JButton> ncAllDisk = ZPINcSwGUIComponent.getRootButtons(lComp);

        for(JButton itemDisk : ncAllDisk){
            westPanel.add(itemDisk);
            toLALRgetPanel();
        }
        return westPanel;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwPanelLineStart#getPanel(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRgetPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelLineStartgetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_PANEL_LINESTART.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_LINEEND.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
}
