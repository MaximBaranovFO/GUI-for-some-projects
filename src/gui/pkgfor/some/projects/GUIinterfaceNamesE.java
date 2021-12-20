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

import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.JFrame;

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
        if( windowForAdd == null )
            
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
