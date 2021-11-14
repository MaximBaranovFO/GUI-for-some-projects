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

/**
 *
 * @author Администратор
 */
public class ListTableDO {
    private boolean select;
    private String name;
    private int age;

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
    protected Boolean isSelect(){
        if (select) {
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
