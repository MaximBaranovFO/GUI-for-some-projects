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
     * <li>{@link ru.newcontrol.ncfv.ZPINcfv#main(java.lang.String[]) }
     * </ul>
     */
    protected static void ZPINcRunSIMA(){
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
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#ZPINcRunSIMA() }
     * </ul>
     */
    protected static void createGui(){
        ZPINcSwGUIComponentStatus listComponents = new ZPINcSwGUIComponentStatus();
        
        JFrame frame = new JFrame(ZPINcStrGUILabel.TITLE_APP.getStr());
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
        listComponents.putComponents(componentPath, frame);  
            
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        toLALRcreateGui();
        
        JPanel mainPanel = new JPanel();
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanel();
        listComponents.putComponents(componentPath, mainPanel);
        
        mainPanel.setLayout(new BorderLayout());
        
        frame.setJMenuBar(ZPINcSwMainMenu.getMainMenu(listComponents));
        toLALRcreateGuiPanel();
        
        mainPanel.add(ZPINcSwPanelPageEnd.getPanel(listComponents), BorderLayout.PAGE_END);
        mainPanel.add(ZPINcSwPanelCenter.getPanel(listComponents), BorderLayout.CENTER);
        mainPanel.add(ZPINcSwPanelPageStart.getPanel(listComponents), BorderLayout.PAGE_START);
        
        mainPanel.add(ZPINcSwPanelLineStart.getPanel(listComponents), BorderLayout.LINE_START);
        
        
        
        JPanel panelLineEnd = ZPINcSwPanelLineEnd.getPanel(listComponents);
        
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
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRcreateGui(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogMsgText.APP_GUI_START.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRcreateGuiPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_CENTER.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * LogAppLogicRecord (LALR) - toLALR(class MethodName)
     * make record in log file
     */
    private static void toLALRendOfCreateGUI(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelPageStartgetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_SET_VISIBLE_GUI.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_GUI_WAIT_FOR_USER_INPUT.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
}
