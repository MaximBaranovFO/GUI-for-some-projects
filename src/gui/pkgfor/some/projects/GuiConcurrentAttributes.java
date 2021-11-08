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

/**
 *
 * @author Администратор
 */
public class GuiConcurrentAttributes {
    private ConcurrentSkipListMap<Integer, Object> idnewwindow;
    private ConcurrentSkipListMap<Integer, String> idnewwindowListOfString;
    private Boolean isChanged;
    GuiConcurrentAttributes(){
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
