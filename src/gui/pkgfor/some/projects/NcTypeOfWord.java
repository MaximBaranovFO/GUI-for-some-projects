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
import java.util.TreeMap;


/**
 * 
 * @author Администратор
 */
public enum NcTypeOfWord {
    
    /**
     *
     */
    NCLVLABC("/a"),

    /**
     *
     */
    NCLVLRABC("/r"),

    /**
     *
     */
    NCLVLNUM("/n"),

    /**
     *
     */
    NCLVLSYM("/c"),

    /**
     *
     */
    NCLVLSPACE("/p");

    
    private String filtername;
    
    NcTypeOfWord(String filtername){
        this.filtername = filtername;
    }
    
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcTypeOfWord#getStorageWordExistFileName(java.lang.String, java.lang.String) }
     * <li>{@link ru.newcontrol.ncfv.NcTypeOfWord#getStorageWordByIdFileName(java.lang.String, java.lang.String, long) }
     * </ul>
     * @return
     */
    protected String getName(){
        return filtername;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#getStorageWordExistFiles(java.lang.String, java.lang.String, java.lang.String) }
     * </ul>
     * Return TreeMap<Long, File> structure for words and heximal view of words, existing in index sub dirictories
     * @param wordInHex
     * @param word
     * @return 
     */
    protected TreeMap<Long, File> getStorageWordExistFileName(String wordInHex, String word){
        
        TreeMap<Integer, File> listDirs = NcIdxFileManager.getIndexWorkSubDirFilesList();
        String strPathSubDir = NcIdxFileManager.strPathCombiner(NcIdxFileManager.getStrCanPathFromFile(listDirs.get("/sw".hashCode())), getName());
        File filePathSubDir = new File(strPathSubDir);
        boolean boolCheck = NcIdxFileManager.dirExistRWAccessChecker(filePathSubDir);
        if( !boolCheck ){
            if( !filePathSubDir.mkdirs() ){
                return new TreeMap<Long, File>();
            }
        }
        long recordID = 0;
        File fileWithRecords;
        TreeMap<Long, File> listFiles = new TreeMap<Long, File>();
        do{
            String fileName = NcIdxFileManager.getFileNameToRecord("/d-" + word.length() + "-" + wordInHex.substring(0, 4), recordID);
            String strPathFile = NcIdxFileManager.strPathCombiner(strPathSubDir, fileName);
            fileWithRecords = new File(strPathFile);
            if( fileWithRecords.exists() ){
                listFiles.put(recordID, fileWithRecords);
            }
            recordID = recordID + 100;
        }
        while( fileWithRecords.exists() );
        return listFiles;
    }
    protected TreeMap<Long, File> getStorageWordAllExistFileName(){
        
        TreeMap<Integer, File> listDirs = NcIdxFileManager.getIndexWorkSubDirFilesList();
        String strPathSubDir = NcIdxFileManager.strPathCombiner(NcIdxFileManager.getStrCanPathFromFile(listDirs.get("/sw".hashCode())), getName());
        File filePathSubDir = new File(strPathSubDir);
        boolean boolCheck = NcIdxFileManager.dirExistRWAccessChecker(filePathSubDir);
        if( !boolCheck ){
            if( !filePathSubDir.mkdirs() ){
                return new TreeMap<Long, File>();
            }
        }
        long recordID = 0;
        File fileWithRecords;
        TreeMap<Long, File> listFiles = new TreeMap<Long, File>();
        File[] listFiles1 = filePathSubDir.listFiles();
        long idx = 0;
        for (File file : listFiles1) {
            listFiles.put(idx, file);
            idx++;
        }
        
        return listFiles;
    }
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcIdxStorageWordManager#getStorageWordByIdFile(java.lang.String, java.lang.String, java.lang.String, long) }
     * </ul>
     * Return file name contained record id in Storage word for word, heximal view for word, and id of record
     * @param wordInHex
     * @param word
     * @param recordId
     * @return 
     */
    protected String getStorageWordByIdFileName(String wordInHex, String word, long recordId){
        TreeMap<Integer, File> listDirs = NcIdxFileManager.getIndexWorkSubDirFilesList();
        String strPathSubDir = NcIdxFileManager.strPathCombiner(NcIdxFileManager.getStrCanPathFromFile(listDirs.get("/sw".hashCode())), getName());
        File filePathSubDir = new File(strPathSubDir);
        boolean boolCheck = NcIdxFileManager.dirExistRWAccessChecker(filePathSubDir);
        if( !boolCheck ){
            if( !filePathSubDir.mkdirs() ){
                return "";
            }
        }
        String fileName = NcIdxFileManager.getFileNameToRecord("/d-" + word.length() + "-" + wordInHex.substring(0, 4), recordId);
        String strPathFile = NcIdxFileManager.strPathCombiner(strPathSubDir, fileName);
        return strPathFile;
    }

    
}
