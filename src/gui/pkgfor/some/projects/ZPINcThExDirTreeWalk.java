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
public class ZPINcThExDirTreeWalk<V> 
        implements Callable<BlockingQueue<ConcurrentSkipListMap<UUID, NcDataListAttr>>> {
    private static String typeThread;
    private boolean FAIR_QUEUE;
    private boolean boolDone;
    private int LENGTH_QUEUE;
    private Path pathToStart;
    private NcFsIdxFileVisitor fileVisitor;
    private BlockingQueue<ConcurrentSkipListMap<UUID, NcDataListAttr>> pipeDirList;
    
    public ZPINcThExDirTreeWalk(Path forReadList) throws IOException {
        Thread.currentThread().checkAccess();
        this.typeThread = "[TREEWALKER]";
        NcAppHelper.outCreateObjectMessage(this.typeThread, this.getClass());
        this.boolDone = Boolean.FALSE;
        this.FAIR_QUEUE = Boolean.TRUE;
        this.LENGTH_QUEUE = 1000;
        Path pathToStart = Paths.get(forReadList.toString());
        try{
            pathToStart = NcFsIdxOperationDirs.checkScanPath(pathToStart);
        } catch (IOException ex) {
            String strAddMsg = NcStrLogMsgField.MSG_ERROR.getStr()
                    + " wrong path for scan "
                    + forReadList.toString();
            NcAppHelper.outMessage(strAddMsg);
            NcAppHelper.logException(NcThExDirTreeWalk.class.getCanonicalName(), ex);
            throw new IOException(strAddMsg, ex);
        }
        this.pipeDirList = new ArrayBlockingQueue(this.LENGTH_QUEUE, this.FAIR_QUEUE);
        this.fileVisitor = new NcFsIdxFileVisitor(this.pipeDirList);
        this.pathToStart = pathToStart;
    }
    
    public ZPINcThExDirTreeWalk(Path forReadList, int lengthQueue) throws IOException {
        Thread.currentThread().checkAccess();
        this.typeThread = "[TREEWALKER]";
        NcAppHelper.outCreateObjectMessage(this.typeThread, this.getClass());
        this.LENGTH_QUEUE = lengthQueue;
        Path pathToStart = Paths.get(forReadList.toString());
        try{
            pathToStart = NcFsIdxOperationDirs.checkScanPath(pathToStart);
        } catch (IOException ex) {
            String strAddMsg = NcStrLogMsgField.MSG_ERROR.getStr()
                    + " wrong path for scan "
                    + forReadList.toString();
            NcAppHelper.outMessage(strAddMsg);
            NcAppHelper.logException(NcThExDirTreeWalk.class.getCanonicalName(), ex);
            throw new IOException(strAddMsg, ex);
        }
        this.pipeDirList = new ArrayBlockingQueue(this.LENGTH_QUEUE, this.FAIR_QUEUE);
        this.fileVisitor = new NcFsIdxFileVisitor(this.pipeDirList);
        this.pathToStart = pathToStart;
    }
    
    public ZPINcThExDirTreeWalk(Path forReadList,
            BlockingQueue<ConcurrentSkipListMap<UUID, NcDataListAttr>> pipeFromOut) throws IOException {
        Thread.currentThread().checkAccess();
        this.typeThread = "[TREEWALKER]";
        NcAppHelper.outCreateObjectMessage(this.typeThread, this.getClass());
        this.LENGTH_QUEUE = 1000;
        Path pathToStart = Paths.get(forReadList.toString());
        try{
            pathToStart = NcFsIdxOperationDirs.checkScanPath(pathToStart);
        } catch (IOException ex) {
            String strAddMsg = NcStrLogMsgField.MSG_ERROR.getStr()
                    + " wrong path for scan "
                    + forReadList.toString();
            NcAppHelper.outMessage(strAddMsg);
            NcAppHelper.logException(NcThExDirTreeWalk.class.getCanonicalName(), ex);
            throw new IOException(strAddMsg, ex);
        }
        this.pipeDirList = pipeFromOut;
        this.fileVisitor = new NcFsIdxFileVisitor(this.pipeDirList);
        this.pathToStart = pathToStart;
    }
    
    @Override
    public BlockingQueue<ConcurrentSkipListMap<UUID, NcDataListAttr>> call() throws Exception {
        try {
            Files.walkFileTree(this.pathToStart, this.fileVisitor);
        } catch (IOException ex) {
            NcAppHelper.logException(NcThExDirTreeWalk.class.getCanonicalName(), ex);
            this.boolDone = Boolean.TRUE;
            String strThreadInfo = NcAppHelper.getThreadInfoToString(Thread.currentThread());
            throw new Exception(
                    strThreadInfo
                    + NcStrLogMsgField.MSG_INFO.getStr()
                    + this.typeThread + " aborted, reason "
                    + NcStrLogMsgField.EXCEPTION_MSG.getStr()
                    + ex.getMessage(), ex);
            
        }
        this.boolDone = Boolean.TRUE;
        return this.fileVisitor.getBuffDirList();
    }
    protected BlockingQueue<ConcurrentSkipListMap<UUID, NcDataListAttr>> getQueue(){
        return this.fileVisitor.getBuffDirList();
    }
    
    protected boolean isDone(){
        return this.boolDone;
    }
    protected long getCountPostVisitDir(){
        return this.fileVisitor.getCountPostVisitDir();
    }
    protected long getCountPreVisitDir(){
        return this.fileVisitor.getCountPreVisitDir();
    }
    protected long getCountVisitFile(){
        return this.fileVisitor.getCountVisitFile();
    }
    protected long getCountVisitFileFailed(){
        return this.fileVisitor.getCountVisitFileFailed();
    }
    
}
