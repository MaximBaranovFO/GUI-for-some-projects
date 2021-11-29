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
import java.util.List;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcThWorkerUpGUIKeyWordSearch {
    protected static void searchKeyWordGetResult(ZPINcSwGUIComponentStatus lComp){
        
        String strPathTable = 
                ZPINcSwGUIComponentRouter.pathMainFramePanelCenterTable();
        JTable tableShowSearchResult = (JTable) lComp.getComponentByPath(strPathTable);
        
        tableShowSearchResult.setEnabled(false);
        TableModel modelShowStack = tableShowSearchResult.getModel();
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStartTextFieldSearch();
        JTextField textField = (JTextField) lComp.getComponentByPath(componentPath);
        String strSrch = textField.getText();
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStartButtonSearch();
        JButton buttonSearch = (JButton) lComp.getComponentByPath(componentPath);
        buttonSearch.setEnabled(false);
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageEndProgressBar();
        JProgressBar progressBar = (JProgressBar) lComp.getComponentByPath(componentPath);
        progressBar.setIndeterminate(true);
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
        JPanel panelLineEnd = (JPanel) lComp.getComponentByPath(componentPath);
        panelLineEnd.repaint();
        
        TreeMap<Long, ZPINcDcIdxDirListToFileAttr> makeSearchResult = 
                new TreeMap<Long, ZPINcDcIdxDirListToFileAttr>();
        
        
        SwingWorker<Void, TreeMap<Long, ZPINcDcIdxDirListToFileAttr>> underGroundWorker = 
                new SwingWorker<Void, TreeMap<Long, ZPINcDcIdxDirListToFileAttr>> () {
                    
            @Override
            protected Void doInBackground() {
                TreeMap<Long, ZPINcDcIdxDirListToFileAttr> makeSearchByKeyFromInput = 
                        ZPINcSrchGetResult.makeSearchByKeyFromInput(strSrch);
                publish(makeSearchByKeyFromInput);
                return null;
            }
            
            @Override
            protected void process(List<TreeMap<Long, ZPINcDcIdxDirListToFileAttr>> chunks){
                for(TreeMap<Long, ZPINcDcIdxDirListToFileAttr> item : chunks){
                    makeSearchResult.putAll(item);
                }
                
                
            }
            
            @Override
            protected void done(){
                progressBar.setIndeterminate(false);
                TableModel locNewTableModel = new ZPINcSIMASearchResultTableModel(makeSearchResult);
                tableShowSearchResult.setModel(locNewTableModel);
                tableShowSearchResult.setEnabled(true);
                buttonSearch.setEnabled(true);
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
                JPanel panelCenter = (JPanel) lComp.getComponentByPath(componentPath);
                panelCenter.repaint();
                
            }
                    
        };
        underGroundWorker.execute();
    }
}
