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
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcThMifRunDirList extends Thread {
    private String typeObject;
    private ZPINcFsIdxFileVisitor fv;
    private Path ps;
    private ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> pd;
    private ZPINcThExStatus jobStatus;
    
    
    public ZPINcThMifRunDirList(
            ArrayBlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> pipeDirListOuter,
            Path pathToStartOuter,
            ZPINcThExStatus outerJobStatus) {
        this.pd = pipeDirListOuter;
        ZPINcFsIdxFileVisitor ncFsIdxFileVisitor = new ZPINcFsIdxFileVisitor(pd);
        this.fv = ncFsIdxFileVisitor;
        Path realPath = Paths.get("/");
        this.ps = realPath;
        this.typeObject = "[MIFRUNDIRLIST]" + this.toString();
        this.jobStatus = outerJobStatus;
        ZPINcAppHelper.outCreateObjectMessage(this.typeObject, this.getClass());
    }

    
    @Override
    public void run() {
        System.out.println("[RUNNER][START][IN]");
        try {
            Files.walkFileTree(this.ps, this.fv);
        } catch (IOException ex) {
            ZPINcAppHelper.logException(ZPINcThMifRunDirList.class.getCanonicalName(), ex);
        } catch (IllegalStateException ex) {
            ZPINcAppHelper.logException(ZPINcThMifRunDirList.class.getCanonicalName(), ex);
        } catch (SecurityException ex) {
            ZPINcAppHelper.logException(ZPINcThMifRunDirList.class.getCanonicalName(), ex);
        }
        System.out.println("[RUNNER][FINISH][EXIT]");
    }
    
}
