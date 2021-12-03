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

import java.nio.file.Path;

/**
 *
 * @author Администратор
 */
public class GuiGridBagAndHelper {
    private static FsWorkerDirFiles workerForFsDo;
    private static GuiGridBagTwo numberTwo;
    private static GuiGridBagOne numberOne;
    GuiGridBagAndHelper(){
        GuiGridBagTwo helperForThisParams = new GuiGridBagTwo();
        GuiGridBagOne helperParams = new GuiGridBagOne();
        
        helperParams.setElementedParams();
        helperParams.runMainExec();
        
        numberTwo = helperForThisParams;
        numberOne = helperParams;
        
        workerForFsDo = new FsWorkerDirFiles();
        operationsInFsForAppExec();
        
        writeObjectToFs();
    }
    protected Object getWindowOne(){
        return this.numberOne;
        
    }
    protected Object getWindowTwo(){
        return this.numberTwo;
    }
    protected void writeObjectToFs(){
        workerForFsDo.checkOrCreateFileInSubWorkDirWithObject("C:\\_bmv\\id-jao\\asis_object001\\", numberOne);
        workerForFsDo.checkOrCreateFileInSubWorkDirWithObject("C:\\_bmv\\id-jao\\asis_object002\\", numberTwo);
        
    }
    private void operationsInFsForAppExec(){
        Path dirForXls = workerForFsDo.getDirForXls();
        
    }
}
