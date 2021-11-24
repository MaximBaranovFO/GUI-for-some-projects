/*
 *  Copyright 2017 Administrator of development departament newcontrol.ru .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package gui.pkgfor.some.projects;

import java.io.File;
import java.io.IOException;
/**
 *
 * @author Администратор
 */
public class ZPINcIndexMaker {
    private File currentFile;

    /**
     * Not used
     * @param ncFile
     */
    private ZPINcIndexMaker(File ncFile) {
        currentFile = ncFile;
    }

    /**
     * Not used
     * @param fncFile
     * @return
     */
    private String[] getFilterStringsArray(File fncFile){
        NcIndexPreProcessFiles ncIdxPreReturn = new NcIndexPreProcessFiles(fncFile);

        String[] toRet = new String[0];

        try {
            toRet = ncIdxPreReturn.getFileDataToSwing(fncFile);
        } catch (IOException ex) {
            NcAppHelper.logException(
                    NcIndexMaker.class.getCanonicalName(), ex);
        }
        return toRet;
    }
    
    /**
     * Not used
     * @param fncFile
     * @return
     */
    private String[] getMakeIndexForFile(File fncFile){
        NcIndexPreProcessFiles ncIdxPreReturn = new NcIndexPreProcessFiles(fncFile);

        String[] toRet = new String[0];

        try {
            toRet = ncIdxPreReturn.getResultMakeIndex(fncFile);
        } catch (IOException ex) {
            NcAppHelper.logException(
                    NcIndexMaker.class.getCanonicalName(), ex);
        }
        return toRet;
    }
}
