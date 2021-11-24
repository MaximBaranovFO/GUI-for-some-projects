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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxLongWordFileWriter {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxLongWordManager#putLongWordInFile(java.util.TreeMap, ru.newcontrol.ncfv.NcDcIdxLongWordListToFile) }
     * </ul>
     * @param ncWordToRec
     * @param recHexWord
     * @param recID
     * @return 
     */    
    protected static long ncWriteForLongWord(TreeMap<Long, NcDcIdxWordToFile> ncWordToRec, String recHexWord, long recID){
        if( ncWordToRec == null ){
            return -1;
        }
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(recHexWord)))
        {
            oos.writeObject(ncWordToRec);
        }
        catch(Exception ex){
            NcAppHelper.logException(
                    NcIdxLongWordFileWriter.class.getCanonicalName(), ex);
            return -1;
        } 
        return ncWordToRec.size();
    }
    
}
