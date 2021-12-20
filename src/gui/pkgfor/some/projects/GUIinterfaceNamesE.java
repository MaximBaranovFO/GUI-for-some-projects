/*
 * Copyright 2021 Администратор.
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
import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Администратор
 */
/**
 * Temporary, Atrributes of parameters from GUI window inputed in this interface
 * @author Администратор
 */
public interface GUIinterfaceNamesE {
    public class GuiFromWindowAttr {
    private final static GuiFromWindowAttr guiFromWindowAttr = new GuiFromWindowAttr();
    private ConcurrentSkipListMap<Integer, JFrame> idNowWindow;
    private final static ConcurrentSkipListMap<Integer, String> idNowWindowCreatedNamesListOfString = new ConcurrentSkipListMap();
    private final Boolean isBuildedChanged = Boolean.TRUE;
    GuiFromWindowAttr(){
        this.idNowWindow = new ConcurrentSkipListMap();
    }
    protected void rebuildListStorageWindow(){
        this.idNowWindow.clear();
        this.idNowWindow = null;
        this.idNowWindow = new ConcurrentSkipListMap();
    }
    protected static void correctTitleOfGUIWindow(JFrame correctedWindowForAdd){
        guiFromWindowAttr.addNewNameWindowToListOfString(correctedWindowForAdd);
    }
    private void addNewWindow(JFrame windowForAdd){
        addNewNameWindowToListOfString(windowForAdd);
        this.idNowWindow.put(windowForAdd.hashCode(), windowForAdd);
    }
    private static void addNewNameWindowToListOfString(JFrame windowForAdd){
        
        if( windowForAdd == null ) {
            GUIinterfaceNamesFB.GUIComponentObjectStatus guiComponentObjectStatus = new GUIinterfaceNamesFB.GUIComponentObjectStatus();
            JFrame someCreatedWindow = new JFrame(GUIinterfaceNamesFA.OldGUIReconstruction.getWindowName(String.valueOf(System.nanoTime())));
            Integer numberOfIteration = 0;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(String.valueOf(System.currentTimeMillis())), someCreatedWindow);
            numberOfIteration++;
            JPanel mainPanel = new JPanel();
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#002^").concat(String.valueOf(System.currentTimeMillis())), mainPanel);
            numberOfIteration++;
            BorderLayout borderLayout = new BorderLayout();
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#003^").concat(String.valueOf(System.currentTimeMillis())), borderLayout);
            mainPanel.setLayout(borderLayout);
            numberOfIteration++;
            someCreatedWindow.getContentPane().add(mainPanel);
            Dimension dimension = new Dimension(320, 240);
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(String.valueOf(System.currentTimeMillis())), dimension);
            someCreatedWindow.setMinimumSize(dimension);
            Dimension dimension1 = new Dimension(800, 600);
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(String.valueOf(System.currentTimeMillis())), dimension1);
            numberOfIteration++;
            
            someCreatedWindow.setPreferredSize(dimension1);
            numberOfIteration++;
            someCreatedWindow.repaint();
            someCreatedWindow.revalidate();
            someCreatedWindow.pack();
            someCreatedWindow.setLocationRelativeTo(null);
            someCreatedWindow.setVisible(true);
            
            windowForAdd = GUIinterfaceNamesF.EditedVersionWorkerForWithProviderConsumerPC.editedVersionMainRunProviderConsumer(someCreatedWindow);
        }    
        int hashCode = windowForAdd.hashCode(); 
        String windowCurrentName = getWindowCurrentName(windowForAdd);
        windowForAdd.setTitle(windowCurrentName);
        //String putidNowWindowCreatedNamesListOfString = this.idNowWindowCreatedNamesListOfString.put(hashCode, windowCurrentName);
        //ConcurrentSkipListMap<Integer, String> clone = this.idNowWindowCreatedNamesListOfString.clone();
        String removeTitleName = idNowWindowCreatedNamesListOfString.remove(hashCode);
        idNowWindowCreatedNamesListOfString.put(hashCode,windowForAdd.getName());
    }
    private void changeListOfGUINames(){
        
    }
    protected Integer countInListOfWindow(){
        return this.idNowWindow.size();
    }
    protected Integer countInListOfWindowNames(){
        return this.idNowWindowCreatedNamesListOfString.size();
    }
    private static String getWindowCurrentName(JFrame windowForChange){
        String title = windowForChange.getTitle();
        
        String windowName = GUIinterfaceNamesFA.OldGUIReconstruction.getWindowName(title);
        windowForChange.setTitle(windowName);
        return windowForChange.getTitle();
    }
}
}
