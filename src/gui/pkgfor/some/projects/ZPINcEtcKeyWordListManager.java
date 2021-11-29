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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcEtcKeyWordListManager {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromFile() }
     * </ul>
     * @return 
     */
    protected static ArrayList<String> getKeyWordInSearchFromFile(){
        ZPINcParamFv readedWorkCfg = ZPINcParamFvReader.readDataFromWorkCfg();
        ArrayList<String> strForReturn;
        strForReturn = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(
                readedWorkCfg.keywordsInSearch)))
        {
            String s;
            while((s=br.readLine())!=null){
                strForReturn.add(s.trim());
            }
        }
         catch(IOException ex){
            ZPINcAppHelper.logException(ZPINcEtcKeyWordListManager.class.getCanonicalName(), ex);
        }   
        return strForReturn;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSearchInIndex#searchWordInIndex() }
     * <li>
     * <li>{@link ru.newcontrol.ncfv.NcSrchGetResult#makeSearchByKeyFromFile() }
     * </ul>
     * @return 
     */
    protected static ArrayList<String> getKeyWordOutSearchFromFile(){
        ZPINcParamFv readedWorkCfg = ZPINcParamFvReader.readDataFromWorkCfg();
        ArrayList<String> strForReturn;
        strForReturn = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(
                readedWorkCfg.keywordsOutOfSearch)))
        {
            String s;
            while((s=br.readLine())!=null){
                strForReturn.add(s.trim());
            }
        }
         catch(IOException ex){
            ZPINcAppHelper.logException(ZPINcEtcKeyWordListManager.class.getCanonicalName(), ex);
        }   
        return strForReturn;
    }
}
