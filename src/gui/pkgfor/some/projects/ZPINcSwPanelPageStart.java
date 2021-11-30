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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwPanelPageStart {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * @return
     */
    protected static JPanel getPanel(ZPINcSwGUIComponentStatus lComp){
        JPanel northPanel = new JPanel();
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStart();
        lComp.putComponents(componentPath, northPanel);
        Border northBorder = BorderFactory.createTitledBorder(
                ZPINcStrGUILabel.PARAMETERS_FOR_SEARCH.getStr());
        northPanel.setBorder(northBorder);
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTextField addNorthWordSearch = new JTextField();
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStartTextFieldSearch();
        lComp.putComponents(componentPath, addNorthWordSearch);
        
        addNorthWordSearch.setPreferredSize(new Dimension(300, 20));
        
        northPanel.add(addNorthWordSearch);
        JButton btnSearch = ZPINcSwGUIComponent.createButton("Search",null,"");
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStartButtonSearch();
        lComp.putComponents(componentPath, btnSearch);
        
        btnSearch.addActionListener(new ActionListener(){
            public void  actionPerformed(ActionEvent e){
                //ZPINcSwStatusPanel.indicationStartProgressBar(lComp);
                String strSearch = addNorthWordSearch.getText();
                //ZPINcSwThreadManager.setToViewSearchedResult(lComp, strSearch);
                ZPINcThWorkerUpGUIKeyWordSearch.searchKeyWordGetResult(lComp);
                
                //ZPINcSwStatusPanel.indicationStopProgressBar(lComp);
            }
        });
        
        northPanel.add(btnSearch);
        toLALRgetPanel();
        return northPanel;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwPanelPageStart#getPanel(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRgetPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelPageStartgetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_PAGESTART.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_PAGEEND.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
}
