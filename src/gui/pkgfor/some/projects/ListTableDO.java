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

import javax.swing.JButton;

/**
 *
 * @author Администратор
 */
public class ListTableDO {
    private boolean select;
    private String name;
    private int age;
    private JButton workerDo;
    private JButton workerShowInfo;
    private Boolean isDoSet = Boolean.FALSE;
    private Boolean isShowInfoSet = Boolean.FALSE;

        // Add getter's and setter's
    protected void setName(String inputedStr){
        name = new String(inputedStr);
    }
    protected void setAge(int inputedAge){
        age = inputedAge;
    }
    protected void setSelect(Boolean inputedSelect){
        select = inputedSelect;
    }
    protected void setDo(JButton inputedButtonForDo){
        isDoSet = Boolean.TRUE;
        workerDo = inputedButtonForDo;
    }
    protected void setWorkerShowInfo(JButton inputedButtonShowInfo){
        isShowInfoSet = Boolean.TRUE;
        
        workerShowInfo = inputedButtonShowInfo;
    }
    protected Boolean isSelect(){
        if (select) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDo(){
        if (isDoSet) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isWorkerShowInfo(){
        if (isShowInfoSet) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected int getAge(){
        return age;
    }
    protected String getName(){
        return name;
    }
}
