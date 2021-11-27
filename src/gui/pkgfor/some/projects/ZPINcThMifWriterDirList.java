/*
 * Copyright 2018 wladimirowichbiaran.
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcThMifWriterDirList extends Thread {
    private String typeObject;
    private long sleepTimeDownRecordSpeed;
    private ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> listPackInner;
    private ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> toPackDirList;
    private ZPINcThExStatus outerJobStatus;

    public ZPINcThMifWriterDirList(
            ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> listPackOuter,
            ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> toPackDirListOuter,
            ZPINcThExStatus outerJobStatus) {
        this.sleepTimeDownRecordSpeed = 100L;
        this.listPackInner = listPackOuter;
        this.toPackDirList = toPackDirListOuter;
        this.typeObject = "[MIFWRITERDIRLIST]" + this.toString();
        ZPINcAppHelper.outCreateObjectMessage(this.typeObject, this.getClass());
    }
    
    
    @Override
    public void run() {
        
        Path pathIndexFile = ZPINcFsIdxStorageInit.buildPathToFileOfIdxStorage();
        Map<String, String> fsProperties = ZPINcFsIdxStorageInit.getFsPropExist();
        System.out.println("\n\n\n file storage path: " + pathIndexFile.toString());
        Boolean existFSfile = ZPINcFsIdxOperationFiles.existAndHasAccessRWNotLink(pathIndexFile);
        System.out.println("ZPINcFsIdxOperationFiles.existAndHasAccessRWNotLink(): " + existFSfile.toString());
        if( !existFSfile ){
            fsProperties = ZPINcFsIdxStorageInit.getFsPropCreate();
        }
        /*for (Map.Entry<String, String> entry : fsProperties.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Val: " + entry.getValue());
        }*/
        
        Boolean ifException = Boolean.FALSE;
        
        URI uriZipIndexStorage = URI.create("jar:file:" + pathIndexFile.toUri().getPath());
        try(FileSystem fsZipIndexStorage = 
            FileSystems.newFileSystem(uriZipIndexStorage, fsProperties)){
            
            ZPINcParamFs dataStorage = ZPINcFsIdxStorageInit.initStorageStructure(fsZipIndexStorage);
            do{
            int dataWaitCount = 0;
            
            do{
                /*Boolean ifDataBegin = Boolean.FALSE;
                do {                
                    if( !this.listPackInner.isEmpty() ){
                        ifDataBegin = Boolean.TRUE;
                        dataWaitCount = 0;
                    }
                } while ( !ifDataBegin );*/
                try{
                    Boolean boolMustBegin = Boolean.FALSE;
                    do {  
                        ConcurrentSkipListMap<UUID, ZPINcDataListAttr> nowPack = new ConcurrentSkipListMap<>();
                        Path dirDirList = dataStorage.getDirDirList();
                        Path pathForViewCount = fsZipIndexStorage.getPath(dirDirList.toString()).normalize();
                        Path getNew = pathForViewCount;
                        Boolean existFile = Boolean.FALSE;
                        long countFiles = 0;
                        String strIndex = "";
                        do{
                            
                            if(countFiles > 0){
                                strIndex = ZPINcStrFileDir.PRE_DIR_LIST.getStr() + Long.toString(countFiles * 100L + 100L);
                            }
                            else{
                                strIndex = ZPINcStrFileDir.PRE_DIR_LIST.getStr() + Long.toString(100L);
                            }
                            getNew = fsZipIndexStorage.getPath(dirDirList.toString(), strIndex).normalize();
                            existFile = Files.exists(getNew, LinkOption.NOFOLLOW_LINKS);
                            if(existFile){
                                countFiles++;
                            }
                           
                        }while( existFile );
                        
                        try{

                            nowPack = this.listPackInner.poll(13, TimeUnit.MICROSECONDS);

                            if ( nowPack == null ){
                                String strMsgError = 
                                    "[Writer] From [Taker] get null, need packet size of 100 element";
                                throw new IllegalArgumentException(strMsgError);
                            }

                            /*System.out.println("[Writer]From [Packer] get packet with hashCode "
                            + nowPack.hashCode()
                            + " toString val is " + nowPack.toString()
                            + " size of " + nowPack.size() 
                            + " in packet records, ready path for write is "
                            + getNew.toString());*/
                        } catch (InterruptedException ex) {
                            ZPINcAppHelper.logException(ZPINcThMifWriterDirList.class.getCanonicalName(), ex);
                        }
                            if(nowPack.size() == 100){
                                dataWaitCount = 0;
                                try(ObjectOutputStream oos = 
                                new ObjectOutputStream(
                                        Files.newOutputStream(getNew, 
                                                StandardOpenOption.CREATE, 
                                                StandardOpenOption.WRITE)
                                ))
                                {
                                    oos.writeObject(nowPack);
                                    //System.out.println(getNew.toString() + " -|-|- " + nowPack.size() + " elements writed");
                                }
                                catch(Exception ex){
                                    ZPINcAppHelper.logException(
                                            ZPINcThMifWriterDirList.class.getCanonicalName(), ex);
                                }
                            }
                            nowPack = new ConcurrentSkipListMap<>();
                            if( this.toPackDirList.size() != 0 ){
                                boolMustBegin = Boolean.TRUE;
                            }
                            if( this.listPackInner.size() != 0 ){
                                boolMustBegin = Boolean.TRUE;
                            }
                            
                            if( (this.toPackDirList.size() == 0)
                                && (this.listPackInner.size() == 0) ){
                                boolMustBegin = Boolean.FALSE;
                            }
                            
                    } while ( boolMustBegin );
                } catch (IllegalArgumentException ex){
                    ZPINcAppHelper.logException(ZPINcThMifWriterDirList.class.getCanonicalName(), ex);    
                }
                dataWaitCount++;
            } while ( dataWaitCount < 50 );
            System.out.println("[Writer]statusPacker-" + this.outerJobStatus.getPackerStatus().toString()
                        + "");
            } while ( (outerJobStatus.getPackerStatus() == Thread.State.RUNNABLE)
                    || (outerJobStatus.getPackerStatus() == Thread.State.TIMED_WAITING) );
        } catch (IOException ex) {
            ex.printStackTrace();
            ZPINcAppHelper.logException(ZPINcThMifWriterDirList.class.getCanonicalName(), ex);
            String strMsg = "Imposible to create file for index Storage, see log";
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + strMsg
            );
            ifException = Boolean.TRUE;
        } catch (Exception ex){
            ex.printStackTrace();
            ZPINcAppHelper.logException(ZPINcThMifWriterDirList.class.getCanonicalName(), ex);
            String strMsg = "Imposible for exec operation in the index Storage, see log"
                    + ZPINcStrLogMsgField.EXCEPTION_MSG.getStr() + ex.getMessage();
            ZPINcAppHelper.outMessage(
                ZPINcStrLogMsgField.ERROR_CRITICAL.getStr()
                + strMsg
            );
            ifException = Boolean.TRUE;
        }
        if( ifException ){
            System.out.println("[WRITER][ERRROR][FINISH][EXIT]");
        }
        System.out.println("[WRITER][FINISH][EXIT]");
    }
    
    protected static int writeDirListData(ConcurrentSkipListMap<UUID, ZPINcDataListAttr> dataToFile, Path dirDirList){
        if( dataToFile == null ){
            return -1;
        }
        try(ObjectOutputStream oos = 
                new ObjectOutputStream(
                new FileOutputStream(dirDirList.toFile())))
        {
            oos.writeObject(dataToFile);
        }
        catch(Exception ex){
            ZPINcAppHelper.logException(
                    ZPINcThMifWriterDirList.class.getCanonicalName(), ex);
            return -1;
        } 
        return dataToFile.size();
    }
    
}
