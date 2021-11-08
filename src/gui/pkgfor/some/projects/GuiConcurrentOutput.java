/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pkgfor.some.projects;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Администратор
 */
public class GuiConcurrentOutput {
    private ConcurrentSkipListMap<Integer, Object> idnewwindow;
    private ConcurrentSkipListMap<Integer, String> idnewwindowListOfString;
    GuiConcurrentOutput(){
        this.idnewwindow = new ConcurrentSkipListMap();
    }
    protected void rebuildListStorage(){
        this.idnewwindow.clear();
        this.idnewwindow = null;
        this.idnewwindow = new ConcurrentSkipListMap();
    }
    private void addNewWindow(Object windowForAdd){
        addNewWindowListOfString(windowForAdd);
        this.idnewwindow.put(windowForAdd.hashCode(), windowForAdd);
    }
    private void addNewWindowListOfString(Object windowForAdd){
        this.idnewwindowListOfString.put(windowForAdd.hashCode(), windowForAdd.toString());
    }
    protected Integer countInListOfWindow(){
        return this.idnewwindow.size();
    }
}
