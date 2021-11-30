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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwModalLogViewer {
    private static String modalTitle = "View log file";
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMenuItems#getLogFileReader(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param lComp
     * @return 
     */
    protected static JDialog getDialogLogViewer(ZPINcSwGUIComponentStatus lComp){
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
        JFrame mainFrame = (JFrame) lComp.getComponentByPath(componentPath);
        
        ZPINcSwGUIComponentStatus compIndex = new ZPINcSwGUIComponentStatus();
        compIndex.putComponents("JFrame-mainFrame", mainFrame);
        JDialog modalWindow = new JDialog(mainFrame, modalTitle, true);
        compIndex.putComponents("JDialog-modalWindow", modalWindow);
        JPanel modalPanelPageStart = getPanelPageStart(compIndex);
        JPanel modalPanelCenter = getPanelCenter(compIndex);
        JPanel modalPanelPageEnd = getPanelPageEnd(modalWindow);
        
        modalWindow.add(modalPanelPageStart, BorderLayout.PAGE_START);
        modalWindow.add(modalPanelCenter, BorderLayout.CENTER);
        modalWindow.add(modalPanelPageEnd, BorderLayout.PAGE_END);
        Dimension pSize = new Dimension(800, 600);
        modalWindow.setSize(pSize);
        modalWindow.setPreferredSize(pSize);
        modalWindow.setLocationRelativeTo(mainFrame);
        modalWindow.repaint();
        return modalWindow;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param compLocalIndex
     * @return 
     */
    private static JPanel getPanelCenter(ZPINcSwGUIComponentStatus compLocalIndex){
        JPanel modalPanelInFunc = new JPanel();
        compLocalIndex.putComponents("JPanel-PanelCenter", modalPanelInFunc);
        JScrollPane treeScroll = getScrolledTree(compLocalIndex);
        modalPanelInFunc.add(treeScroll);
        return modalPanelInFunc;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getPanelCenter(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getButtonUpdate(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param compLocalIndex
     * @return 
     */
    private static JScrollPane getScrolledTree(ZPINcSwGUIComponentStatus compLocalIndex){
        DefaultMutableTreeNode treeTop = 
                new DefaultMutableTreeNode("Log file contained:");
        JTree treeNodes = getTreeNodes(treeTop);
        compLocalIndex.putComponents("JTree-treeNodes", treeNodes);
        JScrollPane treeView = new JScrollPane(treeNodes);
        compLocalIndex.putComponents("JScrollPane-treeView", treeView);
        return treeView;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getScrolledTree(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param forTreeTop
     * @return 
     */
    private static JTree getTreeNodes(DefaultMutableTreeNode forTreeTop){
        TreeMap<Long, String> strLogReaded = new TreeMap<Long, String>();
        File logFile = ZPINcLogFileManager.getLogFile();
        String strLogFile = logFile.getName();
        try {
            strLogFile = logFile.getCanonicalPath();
        } catch (IOException ex) {
            
            String strMsgText = ZPINcStrLogMsgField.CLASSNAME.getStr()
                + ZPINcSwModalLogViewer.class.getName()
                + ZPINcStrLogMsgField.MSG.getStr()
                + ex.getMessage();
            ZPINcAppHelper.outMessage(strMsgText);
        }
        strLogReaded.putAll(ZPINcLogFileManager.readFromLog());
        DefaultMutableTreeNode strReadedTime = null;
        DefaultMutableTreeNode strReadedState = null;
        DefaultMutableTreeNode strReadedElement = null;
        DefaultMutableTreeNode strReadedParent = getNN("Lines count ("
                + strLogReaded.size() + ") "
                + strLogFile + " (" + logFile.length() + ")");
        DefaultMutableTreeNode strReadedChild = null;
        
        forTreeTop.add(strReadedParent);
        

        for( Map.Entry<Long, String> strItem : strLogReaded.entrySet() ){
            if( strItem.getValue().contains(ZPINcStrLogMsgField.TIME.getStr()) ){
                String strForOut = ZPINcLogColorizer.getHtmlStr(strItem.getValue());
                strReadedTime = getNN(strForOut);
                if( strReadedParent == null ){
                            forTreeTop.add(strReadedElement);
                            continue;
                }
                strReadedParent.add(strReadedTime);
                continue;
            }
            if( strItem.getValue().contains(ZPINcStrLogMsgField.STATE.getStr()) ){
                strReadedState = getNN(strItem.getValue());
                if( strReadedTime == null ){
                        if( strReadedParent == null ){
                            forTreeTop.add(strReadedElement);
                            continue;
                        }
                        strReadedParent.add(strReadedElement);
                        continue;
                }
                strReadedTime.add(strReadedState);
                continue;
            }
            if( strItem.getValue().contains(ZPINcStrLogMsgField.ELEMENTNUM.getStr()) ){
                strReadedElement = getNN(strItem.getValue());
                if( strReadedState == null ){
                    if( strReadedTime == null ){
                        if( strReadedParent == null ){
                            forTreeTop.add(strReadedElement);
                            continue;
                        }
                        strReadedParent.add(strReadedElement);
                        continue;
                    }
                    strReadedTime.add(strReadedElement);
                    continue;
                }
                strReadedState.add(strReadedElement);
                continue;
            }
            
            strReadedChild = getNN(strItem.getValue());
            if( strReadedChild != null ){
                strReadedTime.add(strReadedChild);
            }
            
            
        }
        forTreeTop.add(strReadedParent);
        return new JTree(forTreeTop);
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getTreeNodes(javax.swing.tree.DefaultMutableTreeNode) }
     * </ul>
     * @param strNodeName
     * @return 
     */
    private static DefaultMutableTreeNode getNN(String strNodeName){
        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(strNodeName);
        return defaultMutableTreeNode;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param compLocalIndex
     * @return 
     */
    private static JPanel getPanelPageStart(ZPINcSwGUIComponentStatus compLocalIndex){
        JPanel modalPanelInFunc = new JPanel();
        JTextField textSearch = new JTextField();
        textSearch.setColumns(25);
        modalPanelInFunc.add(textSearch);
        JButton buttonSearch = getButtonSearch();
        modalPanelInFunc.add(buttonSearch);
        JButton buttonUpdate = getButtonUpdate(compLocalIndex);
        modalPanelInFunc.add(buttonUpdate);
        return modalPanelInFunc;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param modalWindowInFunc
     * @return 
     */
    private static JPanel getPanelPageEnd(JDialog modalWindowInFunc){
        JPanel modalPanelInFunc = new JPanel();
        JButton buttonClose = getButtonClose(modalWindowInFunc);
        modalPanelInFunc.add(buttonClose);
        return modalPanelInFunc;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getPanelPageEnd(javax.swing.JDialog) }
     * </ul>
     * @param modalWindowForButton
     * @return 
     */
    private static JButton getButtonClose(JDialog modalWindowForButton){
        JButton buttonClose = new JButton("Close");
        buttonClose.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                modalWindowForButton.setVisible(false);
            }
        });
        return buttonClose;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getPanelPageStart(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @return 
     */
    private static JButton getButtonSearch(){
        JButton buttonSearch = new JButton("Search");
        buttonSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                
            }
        });
        return buttonSearch;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwModalLogViewer#getPanelPageStart(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param compLocalIndex
     * @return 
     */
    private static JButton getButtonUpdate(ZPINcSwGUIComponentStatus compLocalIndex){
        JButton buttonSearch = new JButton("Update");
        buttonSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JScrollPane scrollPane = (JScrollPane) compLocalIndex.getComponentByPath("JScrollPane-treeView");
                //scrollPane.setVisible(false);
                //scrollPane = null;
                scrollPane = getScrolledTree(compLocalIndex);
                JPanel centralPanel = (JPanel) compLocalIndex.getComponentByPath("JPanel-PanelCenter");
                //centralPanel.add(scrollPane);
                //scrollPane.setVisible(true);
                scrollPane.revalidate();
                scrollPane.repaint();
                centralPanel.repaint();
            }
        });
        return buttonSearch;
    }
    
}
