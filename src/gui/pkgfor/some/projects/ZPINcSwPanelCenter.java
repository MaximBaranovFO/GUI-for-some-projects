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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwPanelCenter {
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * @return
     */
    protected static JPanel getPanel(ZPINcSwGUIComponentStatus lComp){
        JPanel centerPanel = new JPanel();
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
        lComp.putComponents(componentPath, centerPanel);
        Border centerBorder = BorderFactory.createTitledBorder("CENTER panel");
        centerPanel.setBorder(centerBorder);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        TableModel ncTableModel = new ZPINcSIMASearchResultTableModel(new ArrayList<String>(), new ArrayList<String>());
        
        JTable ncTable = new JTable(ncTableModel);
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenterTable();
        lComp.putComponents(componentPath, ncTable);
        
        
        JScrollPane ncScrollTable = new JScrollPane(ncTable);
        
        
        centerPanel.add(ncScrollTable);
        
        ncScrollTable.revalidate();
        
        
        ZPINcLogLogicGUI.NcSwPanelCenterGetPanel();
        return centerPanel;
    }
    
}
