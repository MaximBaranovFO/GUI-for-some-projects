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

import java.io.Serializable;

/**
 *
 * @author Администратор
 */
public class ZPINcDcIdxLongWordListToFile implements Serializable {
    /**
     * nameID - index of files contained data for long world 
     */
    public long nameID;
    /**
     * name - name for this files, heximal string of long word 
     */
    public String name;
    /**
     * nameHash - hash code for name heximal string 
     */
    public int nameHash;
    /**
     * word - word in string 
     */
    public String word;
    /**
     * wordHash - hash code for word in string 
     */
    public int wordHash;

    /**
     *
     */
    public long recordTime;

    /**
     *
     */
    public int recordHash;
    /**
     * 
     * @param name
     * @param word
     * @param wordHash 
     */
    public ZPINcDcIdxLongWordListToFile() {
        this.nameID = -777;
        this.name = "";
        this.nameHash = -777;
        this.word = "";
        this.wordHash = -777;
        long nowSysTime = System.nanoTime();
        this.recordTime = nowSysTime;
        this.recordHash = (
                ""
                + this.nameID
                + this.name
                + this.nameHash
                + this.word
                + this.wordHash
                + nowSysTime).hashCode();
    }

    /**
     *
     * @param nameID
     * @param name
     * @param word
     */
    public ZPINcDcIdxLongWordListToFile(long nameID, String name, String word) {
        this.nameID = nameID;
        this.name = name;
        this.nameHash = name.hashCode();
        this.word = word;
        this.wordHash = word.hashCode();
        long nowSysTime = System.nanoTime();
        this.recordTime = nowSysTime;
        this.recordHash = (
                ""
                + this.nameID
                + this.name
                + this.nameHash
                + this.word
                + this.wordHash
                + nowSysTime).hashCode();
    }
    
}
