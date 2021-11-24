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
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     * @return
     */
    protected static JPanel getPanel(NcSwGUIComponentStatus lComp){
        JPanel northPanel = new JPanel();
        String componentPath = NcSwGUIComponentRouter.pathMainFramePanelPageStart();
        lComp.putComponents(componentPath, northPanel);
        Border northBorder = BorderFactory.createTitledBorder(
                NcStrGUILabel.PARAMETERS_FOR_SEARCH.getStr());
        northPanel.setBorder(northBorder);
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTextField addNorthWordSearch = new JTextField();
        componentPath = NcSwGUIComponentRouter.pathMainFramePanelPageStartTextFieldSearch();
        lComp.putComponents(componentPath, addNorthWordSearch);
        
        addNorthWordSearch.setPreferredSize(new Dimension(300, 20));
        
        northPanel.add(addNorthWordSearch);
        JButton btnSearch = NcSwGUIComponent.createButton("Search",null,"");
        componentPath = NcSwGUIComponentRouter.pathMainFramePanelPageStartButtonSearch();
        lComp.putComponents(componentPath, btnSearch);
        
        btnSearch.addActionListener(new ActionListener(){
            public void  actionPerformed(ActionEvent e){
                //NcSwStatusPanel.indicationStartProgressBar(lComp);
                String strSearch = addNorthWordSearch.getText();
                //NcSwThreadManager.setToViewSearchedResult(lComp, strSearch);
                NcThWorkerUpGUIKeyWordSearch.searchKeyWordGetResult(lComp);
                
                //NcSwStatusPanel.indicationStopProgressBar(lComp);
            }
        });
        
        northPanel.add(btnSearch);
        toLALRgetPanel();
        return northPanel;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelPageStart#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRgetPanel(){
        if( NcfvRunVariables.isLALRNcSwPanelPageStartgetPanel() ){
            String strLogMsg = NcStrLogMsgField.INFO.getStr()
                + NcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + NcStrLogLogicVar.LA_JPANEL_PAGESTART.getStr()
                + NcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + NcStrLogLogicVar.LA_JPANEL_PAGEEND.getStr();
            NcAppHelper.outMessage(strLogMsg);
        }
    }
}
