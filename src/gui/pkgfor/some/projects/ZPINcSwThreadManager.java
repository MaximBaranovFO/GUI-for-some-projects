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

import java.lang.reflect.Proxy;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwThreadManager {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwPanelPageStart#getPanel(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param lComp
     * @param strSrch 
     */
    protected static void setToViewSearchedResult(ZPINcSwGUIComponentStatus lComp, String strSrch){
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
        JPanel centerPanel =
            (JPanel) lComp.getComponentByPath(componentPath);
        
        
        //ZPINcThProcGUICallbackInterface proxyInstGuiCb = getProxyInstanceGUICallback();
        TableModel locNewTableModel = new ZPINcSIMASearchResultTableModel(strSrch);
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenterTable();
        JTable guiTable = (JTable) lComp.getComponentByPath(componentPath);
        
        guiTable.setModel(locNewTableModel);
        centerPanel.repaint();
    }
    /**
     * Not used
     * @return 
     */
    private static ZPINcThProcGUICallbackInterface getProxyInstanceGUICallback(){
        
        ZPINcThProcGUICallback cbLoc = new ZPINcThProcGUICallback();
        ZPINcThProcInvocationHandler ncInvHandler = 
            new ZPINcThProcInvocationHandler(cbLoc);
        ZPINcThProcGUICallbackInterface proxyInstGui = (ZPINcThProcGUICallbackInterface)
        Proxy.newProxyInstance(
        ZPINcThProcGUICallbackInterface.class.getClassLoader(),
        new Class[]{ZPINcThProcGUICallbackInterface.class},
        ncInvHandler);
        return proxyInstGui;
    }
}
