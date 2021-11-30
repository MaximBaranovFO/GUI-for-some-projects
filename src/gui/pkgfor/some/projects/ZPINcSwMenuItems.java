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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwMenuItems {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuDevelop(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * For Development
     * @return 
     */
    protected static JMenuItem getLogFileReader(ZPINcSwGUIComponentStatus lComp){
        
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.LOG_VIEW.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog modalLogViewer =
                    ZPINcSwModalLogViewer.getDialogLogViewer(lComp);
                modalLogViewer.pack();
                modalLogViewer.setVisible(true);
            }
        });
        return toRetMi;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuDevelop(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * For Development
     * @return 
     */
    protected static JMenuItem getEnvironmentViewer(ZPINcSwGUIComponentStatus lComp){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.ENV_VIEW.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
                JFrame mainFrame =
                    (JFrame) lComp.getComponentByPath(componentPath);
                ZPINcSwModalDevHelper.showModalEnvironment(mainFrame);
            }
        });
        return toRetMi;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuDevelop(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * For Development
     * @return 
     */
    protected static JMenuItem getPropertiesViewer(ZPINcSwGUIComponentStatus lComp){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.PROPERTIES_VIEW.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
                JFrame mainFrame =
                    (JFrame) lComp.getComponentByPath(componentPath);
                ZPINcSwModalDevHelper.showModalProperties(mainFrame);
            }
        });
        return toRetMi;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuService() }
     * </ul>
     * For Settings
     * @return 
     */
    protected static JMenuItem getEtcEditor(ZPINcSwGUIComponentStatus lComp){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.SETTINGS.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
                JFrame mainFrame =
                    (JFrame) lComp.getComponentByPath(componentPath);
                //ZPINcSwModalDevHelper.showModalProperties(mainFrame);
                ZPINcSwModalSettingsHelper.showModalProperties(mainFrame);
            }
        });
        return toRetMi;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuFile() }
     * </ul>
     * For File
     * @return 
     */
    protected static JMenuItem getSubDirChecker(){
        return new JMenuItem(ZPINcStrGUILabel.CHECK_SUBDIR.getStr());
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuFile() }
     * </ul>
     * For File
     * @return 
     */
    protected static JMenuItem getAppExit(){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.APP_EXIT.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        return toRetMi;
    }
    /**
     * Not used
     * For Settings
     * @return 
     */
    private static JMenuItem getDirInEditor(){
        return new JMenuItem(ZPINcStrGUILabel.DIR_IN_INDEX.getStr());
    }
    /**
     * Not used
     * For Settings
     * @return 
     */
    private static JMenuItem getDirOutEditor(){
        return new JMenuItem(ZPINcStrGUILabel.DIR_OUT_INDEX.getStr());
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuHelp() }
     * </ul>
     * For Help
     * @return 
     */
    protected static JMenuItem getAbout(){
        return new JMenuItem(ZPINcStrGUILabel.ABOUT.getStr());
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuDevelop(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param lComp
     * @return 
     */
    protected static JMenuItem getStorageWordViewer(ZPINcSwGUIComponentStatus lComp){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.STORAGEWORD.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
                JFrame mainFrame =
                    (JFrame) lComp.getComponentByPath(componentPath);
                ZPINcSwModalStorageWord.showModalStorageWord(mainFrame);
            }
        });
        return toRetMi;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwMainMenu#getMenuDevelop(ru.newcontrol.ncfv.ZPINcSwGUIComponentStatus) }
     * </ul>
     * @param lComp
     * @return 
     */
    protected static JMenuItem getDirListViewer(ZPINcSwGUIComponentStatus lComp){
        JMenuItem toRetMi = new JMenuItem(ZPINcStrGUILabel.DIRECTORYLIST.getStr());
        toRetMi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
                JFrame mainFrame =
                    (JFrame) lComp.getComponentByPath(componentPath);
                ZPINcSwModalDirectoryList.showModalDirectoryList(mainFrame);
            }
        });
        return toRetMi;
    }
}
