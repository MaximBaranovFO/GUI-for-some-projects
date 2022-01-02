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

import java.awt.Component;
import java.awt.MenuComponent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.SubmissionPublisher;
import javax.swing.JFrame;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesFB {
    
    public class forGUIComponentListOfCodeNames {
        //getName, currentTimeMillis
        private ConcurrentSkipListMap<String, ConcurrentSkipListMap<String,Long>> codedListOfNames;
        //hashCode, currentTimeMillis
        private ConcurrentSkipListMap<String, ConcurrentSkipListMap<Integer,Long>> codedListOfHashCodeFromNames;
        protected forGUIComponentListOfCodeNames() {
            long nowCurrentTimeMillis = System.currentTimeMillis();
            
            /**
             * codedListOfNames = new ConcurrentSkipListMap((o1, o2) -> {
                o1 = new String("codedListOfNames|JFrame|Component|JButton|JText|etc");
                o2 = new ConcurrentSkipListMap(
                (bo1, bo2) -> {
                    bo1 = new String("Objects for threads or window | parent | fundamental | etc");
                    bo2 = nowCurrentTimeMillis;
                    return 0;
                
                }
                );
                return 0; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/LambdaBody
            });
            */
            ConcurrentSkipListMap<String,Long> someWayOfNamesFromString = new ConcurrentSkipListMap();
            someWayOfNamesFromString.put(forGUIComponentListOfCodeNames.class.getCanonicalName(), nowCurrentTimeMillis);
            codedListOfNames = new ConcurrentSkipListMap();
            codedListOfNames.put(
                new String("codedListOfNames|JFrame|Component|JButton|JText|etc"),
                someWayOfNamesFromString
            );
            
            Class<? extends ConcurrentSkipListMap> aClass = codedListOfNames.getClass();
            String name = aClass.getName();
            int hashCode = aClass.hashCode();
            
            ConcurrentSkipListMap<Integer,Long> someWayOfNamesFromHisHashCode = new ConcurrentSkipListMap();
            codedListOfHashCodeFromNames = new ConcurrentSkipListMap();
            someWayOfNamesFromHisHashCode.put(hashCode, nowCurrentTimeMillis);
            codedListOfHashCodeFromNames.put(
                    new String("Objects for threads or window | parent | fundamental | etc")
                    , someWayOfNamesFromHisHashCode
            );
            
        }
        
        protected void detectTypeAndAddIntoList(Class<?> inputedClassForAddedElement) {
            //12
            inputedClassForAddedElement.arrayType();
            inputedClassForAddedElement.componentType();
            //1.5
            inputedClassForAddedElement.getCanonicalName();
            //1.1
            try {
                inputedClassForAddedElement.getClasses(); 
                inputedClassForAddedElement.getDeclaredClasses();
                inputedClassForAddedElement.getDeclaredFields();
                inputedClassForAddedElement.getMethods();
            } catch(SecurityException exSecure){
                System.out.println(exSecure.getMessage());
                exSecure.printStackTrace();
            }
            //1.8
            inputedClassForAddedElement.getAnnotatedSuperclass();
        }
        
    }
    
    public class GUIElementsUpdate{
        private ConcurrentSkipListMap<Long, JFrame> listOfFrames;
        private Boolean isElementsCreated = Boolean.FALSE;
        private Long lastElementWhoAdd;
        private forGUIComponentListOfCodeNames archiveOfChangedValues;
        protected GUIElementsUpdate(){
            archiveOfChangedValues = new forGUIComponentListOfCodeNames();
            lastElementWhoAdd = Long.valueOf(0);
            listOfFrames = new ConcurrentSkipListMap();
            isElementsCreated = Boolean.TRUE;
        }
        //GUIinterfaceNamesFB.GUIElementsUpdate().addElementIntoList(JFrame elementForAdd);
        public Boolean addElementIntoList(JFrame elementForAdd){
            ArrayBlockingQueue<String> threadClassGetDeclaredFieldsCommandsOut = ZPIAppObjectsInfoHelperClasses.getThreadClassGetDeclaredFieldsCommandsOut(elementForAdd.getClass());
            archiveOfChangedValues.detectTypeAndAddIntoList(elementForAdd.getClass());
            if( !isElementsCreated )
                return Boolean.FALSE;
            if( elementForAdd == null )
                return Boolean.FALSE;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                listOfFrames.put(currentTimeMillis, elementForAdd);
            } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                return Boolean.FALSE;
            } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                return Boolean.FALSE;
            }
            if( listOfFrames.size() == 0 )
                return Boolean.FALSE;
            lastElementWhoAdd = Long.valueOf(currentTimeMillis);
            return Boolean.TRUE;
        }
        public JFrame getCurrentElementOrNullIfNotHave(){
            if( !isElementsCreated )
                return null;
            if( listOfFrames.size() == 0 )
                return null;
            return listOfFrames.get(lastElementWhoAdd);
        }
        public Boolean cleanListOfElementsSomeExistAndNotHaveNow(){
            if( !isElementsCreated )
                return Boolean.FALSE;
            do {
                if( listOfFrames.size() != 0 ) {
                    Map.Entry<Long, JFrame> pollFirstEntry = listOfFrames.pollFirstEntry();
                    Long keyFirst = pollFirstEntry.getKey();
                    keyFirst = null;
                    JFrame valueFirst = pollFirstEntry.getValue();
                    destructionElement(valueFirst);
                    if( valueFirst != null )
                        valueFirst = null;
                }
                if( listOfFrames.size() != 0 ) {
                    Map.Entry<Long, JFrame> pollLastEntry = listOfFrames.pollLastEntry();
                    Long keyLast = pollLastEntry.getKey();
                    keyLast = null;
                    JFrame valueLast = pollLastEntry.getValue();
                    destructionElement(valueLast);
                    if( valueLast != null )
                        valueLast = null;
                }
            } while(listOfFrames.size() != 0);
            listOfFrames.clear();
            return Boolean.TRUE;
        }
        private JFrame destructionElement(JFrame elementForDestruction){
            int idx = 0;
            if( elementForDestruction.isVisible() )
                elementForDestruction.setVisible(Boolean.FALSE);
            Component[] components = elementForDestruction.getComponents();
            idx = components.length;
            if( idx > 0 ) {
                do {
                    Component component = elementForDestruction.getComponent(idx - 1);
                    component = destructionComponent(component);
                    if( idx > 0 )
                        idx--;
                } while(idx != 0);
            }
            ComponentListener[] componentListeners = elementForDestruction.getComponentListeners();
            componentListeners.notifyAll();
            elementForDestruction.getComponentCount();
            elementForDestruction.removeAll();
            elementForDestruction = null;
            System.gc();
            return null;
        }
        private Component destructionComponent(Component componentForDestruction){
            if( componentForDestruction.isVisible() )
                componentForDestruction.setVisible(Boolean.FALSE);
            ComponentListener[] componentListeners = componentForDestruction.getComponentListeners();
            if( componentListeners.length > 0 ) {
                try {
                    componentListeners.notifyAll();
                } catch (IllegalMonitorStateException exMonitor) {
                    System.out.println(exMonitor.getMessage());
                    exMonitor.printStackTrace();
                }
            }
            /**
            componentForDestruction.remove(popup);
            componentForDestruction.removeComponentListener(l);
            componentForDestruction.removeFocusListener(l);
            componentForDestruction.removeHierarchyBoundsListener(l);
            componentForDestruction.removeHierarchyListener(l);
            componentForDestruction.removeInputMethodListener(l);
            componentForDestruction.removeKeyListener(l);
            componentForDestruction.removeMouseListener(l);
            componentForDestruction.removeMouseMotionListener(l);
            componentForDestruction.removeMouseWheelListener(l);
            componentForDestruction.removePropertyChangeListener(listener);
            */
            componentForDestruction.removeNotify();
            componentForDestruction = null;
            System.gc();
            return null;
        }
        private MenuComponent currentMenuComponent(Component componentForDestruction){
            componentForDestruction.getFocusListeners();
            componentForDestruction.getHierarchyBoundsListeners();
            componentForDestruction.getHierarchyListeners();
            componentForDestruction.getInputMethodListeners();
            KeyListener[] keyListeners = componentForDestruction.getKeyListeners();
            for(int idxKeyListeners = 0; idxKeyListeners < keyListeners.length - 1; idxKeyListeners++)
                componentForDestruction.getListeners(keyListeners[idxKeyListeners].getClass());
            
            componentForDestruction.getMouseListeners();
            componentForDestruction.getMouseMotionListeners();
            componentForDestruction.getMouseWheelListeners();
            PropertyChangeListener[] propertyChangeListeners = componentForDestruction.getPropertyChangeListeners();
            
            propertyChangeListeners.toString();
            //for do class who set property names in list
            String propertyName = propertyChangeListeners.getClass().getName();
            componentForDestruction.getPropertyChangeListeners(propertyName);
            
            
            System.gc();
            return null;
        }
    }

    
    public class GUIComponentObjectStatus {
    
    private TreeMap<Integer, Component> modalLogView;
    private ConcurrentHashMap<Integer, Object> componentGUIList;
    /**
     * Used in !!!WARNING!!! need change
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     */
    protected GUIComponentObjectStatus(){
        modalLogView = new TreeMap<Integer, Component>();
        componentGUIList = new ConcurrentHashMap<Integer, Object>();
    }
    /** !!!WARNING!!! need change
     * Not used
     * @return 
     */
    protected TreeMap<Integer, Component> getComponentsList(){
        return modalLogView;
    }
    protected ConcurrentHashMap<Integer, Object> getObjectsList(){
        return componentGUIList;
    }
    /**
     * Used in !!!WARNING!!! need change
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwMenuItems#getEnvironmentViewer(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>{@link ru.newcontrol.ncfv.NcSwMenuItems#getPropertiesViewer(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getButtonUpdate(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwThreadManager#setToViewSearchedResult(ru.newcontrol.ncfv.NcSwGUIComponentStatus, java.lang.String) }
     * </ul>
     * @param typeToGet
     * @return 
     */
    protected Component getComponentByPath(String typeToGet){
        return modalLogView.get(typeToGet.hashCode());
    }
    protected Object getObjectByPath(String typeToGet){
        return componentGUIList.get(typeToGet.hashCode());
    }
    /**
     * Used in !!!WARNING!!! need change
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getDialogLogViewer(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getPanelCenter(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>{@link ru.newcontrol.ncfv.NcSwModalLogViewer#getScrolledTree(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelCenter#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelLineEnd#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelLineStart#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelPageEnd#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwPanelPageStart#getPanel(ru.newcontrol.ncfv.NcSwGUIComponentStatus) }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSwingIndexManagerApp#createGui() }
     * </ul>
     * @param typeToAdd
     * @param compToAdd 
     */
    protected void putComponents(String typeToAdd, Component compToAdd){
        if( modalLogView == null ){
            modalLogView = new TreeMap<Integer, Component>();
        }
        Component toUnset = modalLogView.get(typeToAdd.hashCode());
        toUnset = null;
        modalLogView.put(typeToAdd.hashCode(), compToAdd);
    }
    
    protected void putObject(String typeToAdd, Object compToAdd){
        if( componentGUIList == null ){
            componentGUIList = new ConcurrentHashMap<Integer, Object>();
        }
        Object toUnset = componentGUIList.get(typeToAdd.hashCode());
        toUnset = null;
        componentGUIList.put(typeToAdd.hashCode(), compToAdd);
    }
    
}
}
