/*
 *  Copyright 2017 Administrator of development departament newcontrol.ru .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package gui.pkgfor.some.projects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



/**
 *
 * @author Администратор
 */
public class ZPINcSwingIndexManagerApp {
    
    

    /**
     * Not used
     */
    protected ZPINcSwingIndexManagerApp() {
        

    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.Ncfv#main(java.lang.String[]) }
     * </ul>
     */
    protected static void NcRunSIMA(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Significantly improves the look of the output in
                    // terms of the file names returned by FileSystemView!
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception weTried) {
                    weTried.getMessage();
                    weTried.getStackTrace();
                }
                createGui();
                
            }
        });
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#NcRunSIMA() }
     * </ul>
     */
    private static void createGui(){
        NcSwGUIComponentStatus listComponents = new NcSwGUIComponentStatus();
        
        JFrame frame = new JFrame(NcStrGUILabel.TITLE_APP.getStr());
        String componentPath = NcSwGUIComponentRouter.pathMainFrame();
        listComponents.putComponents(componentPath, frame);  
            
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        toLALRcreateGui();
        
        JPanel mainPanel = new JPanel();
        componentPath = NcSwGUIComponentRouter.pathMainFramePanel();
        listComponents.putComponents(componentPath, mainPanel);
        
        mainPanel.setLayout(new BorderLayout());
        
        frame.setJMenuBar(NcSwMainMenu.getMainMenu(listComponents));
        toLALRcreateGuiPanel();
        
        mainPanel.add(NcSwPanelPageEnd.getPanel(listComponents), BorderLayout.PAGE_END);
        mainPanel.add(NcSwPanelCenter.getPanel(listComponents), BorderLayout.CENTER);
        mainPanel.add(NcSwPanelPageStart.getPanel(listComponents), BorderLayout.PAGE_START);
        
        mainPanel.add(NcSwPanelLineStart.getPanel(listComponents), BorderLayout.LINE_START);
        
        
        
        JPanel panelLineEnd = NcSwPanelLineEnd.getPanel(listComponents);
        
        mainPanel.add(panelLineEnd, BorderLayout.LINE_END);


        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(320, 240));
        frame.setPreferredSize(new Dimension(800, 600));
        frame.repaint();
        frame.revalidate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        toLALRendOfCreateGUI();
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRcreateGui(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = NcStrLogMsgField.INFO.getStr()
                + NcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + NcStrLogMsgText.APP_GUI_START.getStr()
                + NcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + NcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr();
            NcAppHelper.outMessage(strLogMsg);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRcreateGuiPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = NcStrLogMsgField.INFO.getStr()
                + NcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + NcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr()
                + NcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + NcStrLogLogicVar.LA_JPANEL_CENTER.getStr();
            NcAppHelper.outMessage(strLogMsg);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRendOfCreateGUI(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelPageStartgetPanel() ){
            String strLogMsg = NcStrLogMsgField.INFO.getStr()
                + NcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + NcStrLogLogicVar.LA_SET_VISIBLE_GUI.getStr()
                + NcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + NcStrLogLogicVar.LA_GUI_WAIT_FOR_USER_INPUT.getStr();
            NcAppHelper.outMessage(strLogMsg);
        }
    }
}
