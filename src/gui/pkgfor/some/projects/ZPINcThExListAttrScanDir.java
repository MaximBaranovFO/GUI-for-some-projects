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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcThExListAttrScanDir<V>
        implements Callable<ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>>> {
    
    private static String typeThread;
    private ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> listForOut;
    private ZPINcThExDirTreeWalk dirWalker;
    private Path pathToStart;
    private ZPINcFsIdxFileVisitor fileVisitor;
    
    private BlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> pipeDirList;
    
    /*public NcThExListAttrScanDir(Path forReadList) throws IOException {
        Thread.currentThread().checkAccess();
        this.typeThread = "[LISTATTRSCAN]";
        ZPINcAppHelper.outCreateObjectMessage(this.typeThread, this.getClass());
        Path pathToStart = Paths.get(forReadList.toString());
        try{
            pathToStart = NcFsIdxOperationDirs.checkScanPath(pathToStart);
        } catch (IOException ex) {
            ZPINcAppHelper.logException(NcThExListAttrScanDir.class.getCanonicalName(), ex);
            throw new IOException(ex);
        }
        this.pipeDirList = new ArrayBlockingQueue(1000, true);
        this.fileVisitor = new ZPINcFsIdxFileVisitor(pipeDirList);
        this.pathToStart = pathToStart;
        this.listForOut = new ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>>();
    }*/
    
    public ZPINcThExListAttrScanDir(ZPINcThExDirTreeWalk thDirWalker) {
        Thread.currentThread().checkAccess();
        this.typeThread = "[LISTATTRSCAN]";
        ZPINcAppHelper.outCreateObjectMessage(this.typeThread, this.getClass());
        this.dirWalker = thDirWalker;
        this.pipeDirList = this.dirWalker.getQueue();
        this.listForOut = new ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>>();
    }

    @Override
    public ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> call() throws Exception {

        /*try {
            Files.walkFileTree(pathToStart, fileVisitor);
        } catch (IOException ex) {
            ZPINcAppHelper.logException(NcThWorkerGUIDirListScan.class.getCanonicalName(), ex);
            return new ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>>();
        }*/
        int emptyCount = 0;
        int size = 0;
        boolean hasData = Boolean.FALSE;
        try {
            do {
                boolean notExitFromReadData = Boolean.TRUE;
                do {
                    //size = fileVisitor.buffDirList.size();
                    size = this.pipeDirList.size();
                    if( (size > 0) ){
                        hasData = Boolean.TRUE;
                        emptyCount = 0;
                        ConcurrentSkipListMap<UUID, ZPINcDataListAttr> take = this.pipeDirList.take();
                        UUID key = UUID.randomUUID();
                        this.listForOut.put(key, take);
                    }
                    if( hasData ){
                       if( size == 0 ){
                            notExitFromReadData = Boolean.FALSE;
                        } 
                    }
                } while ( notExitFromReadData );
                emptyCount++;
            } while ( emptyCount < 5 );
        } catch (InterruptedException ex) {
            ZPINcAppHelper.logException(ZPINcThExListAttrScanDir.class.getCanonicalName(), ex);
        }
        return this.listForOut;
    }
    protected ConcurrentSkipListMap<UUID, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> getList(){
        return this.listForOut;
    }
    
}
