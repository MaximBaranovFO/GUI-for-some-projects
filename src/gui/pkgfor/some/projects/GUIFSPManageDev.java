/*
 * Copyright 2022 Администратор.
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Администратор
 */
public class GUIFSPManageDev {
    GUIFSPManageDev(){
        super();
    }
    protected void runEarlyDevVersions(){
                try {
            /**
             * Runnable r = () ->
            {
                GUIinterfaceNamesA.CreatorForGUINeedChanges creatorForGUINeedChanges = new GUIinterfaceNamesA.CreatorForGUINeedChanges();
                creatorForGUINeedChanges.InterfaceNamesAmainOfCreatorForGUINeedChanges();
            };
            EventQueue.invokeLater(r);
            **/
            final ExecutorService executor = Executors.newFixedThreadPool(1);
            Runnable r = () ->
            {
                GUIinterfaceNamesA.CreatorForGUINeedChanges creatorForGUINeedChanges = new GUIinterfaceNamesA.CreatorForGUINeedChanges();
                creatorForGUINeedChanges.InterfaceNamesAmainOfCreatorForGUINeedChanges();
            };
            executor.execute(r);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    protected void runWindowIterator(){
        try {
            AandBWhereWhoPanel whereIsNewWindowYouAreGandon = new AandBWhereWhoPanel();
            whereIsNewWindowYouAreGandon.repaint();
            whereIsNewWindowYouAreGandon.setVisible(true);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
