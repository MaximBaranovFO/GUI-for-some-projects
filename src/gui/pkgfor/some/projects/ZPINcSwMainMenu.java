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

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwMainMenu {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#createGui() }
     * </ul>
     * @param lComp
     * @return 
     */
    protected static JMenuBar getMainMenu(ZPINcSwGUIComponentStatus lComp){
        JMenuBar menuMain = new JMenuBar();
        ZPINcLogLogicGUI.NcSwMainMenuGetMainMenu();
        menuMain.add(getMenuFile());
        menuMain.add(getMenuDevelop(lComp));
        menuMain.add(getMenuService(lComp));
        menuMain.add(getMenuHelp());
        return menuMain;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMainMenu(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @return 
     */
    private static JMenu getMenuFile(){
        JMenu menuFile = new JMenu("File");
        menuFile.add(ZPINcSwMenuItems.getSubDirChecker());
        
        menuFile.add(ZPINcSwMenuItems.getAppExit());
        return menuFile;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMainMenu(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param lComp
     * @return 
     */
    private static JMenu getMenuDevelop(ZPINcSwGUIComponentStatus lComp){
        JMenu menuDevelop = new JMenu("Development");
        menuDevelop.add(ZPINcSwMenuItems.getDirListViewer(lComp));
        menuDevelop.add(ZPINcSwMenuItems.getStorageWordViewer(lComp));
        menuDevelop.add(ZPINcSwMenuItems.getLogFileReader(lComp));
        menuDevelop.add(ZPINcSwMenuItems.getEnvironmentViewer(lComp));
        menuDevelop.add(ZPINcSwMenuItems.getPropertiesViewer(lComp));
        return menuDevelop;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMainMenu(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @return 
     */
    private static JMenu getMenuHelp(){
        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(ZPINcSwMenuItems.getAbout());
        return menuHelp;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMainMenu(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @return 
     */
    private static JMenu getMenuService(ZPINcSwGUIComponentStatus lComp){
        JMenu menuService = new JMenu("Service");
        menuService.add(ZPINcSwMenuItems.getEtcEditor(lComp));
        return menuService;
    }
    
}
