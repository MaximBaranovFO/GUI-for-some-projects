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
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcThWorkerGUIDirListScan {
    protected static void scanDirToIdxDirList(ZPINcSwGUIComponentStatus lComp, Path pathDirToScan) throws Exception{
        if( !ZPINcFsIdxOperationDirs.existAndHasAccessR(pathDirToScan) ){
            throw new Exception("Directory not have access for read" + pathDirToScan.toString());
        }
        Path pathDevDirToScan = Paths.get("/usr/home/wladimirowichbiaran/work");
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageStartButtonSearch();
        JButton buttonSearch = (JButton) lComp.getComponentByPath(componentPath);
        buttonSearch.setEnabled(false);
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelPageEndProgressBar();
        JProgressBar progressBar = (JProgressBar) lComp.getComponentByPath(componentPath);
        progressBar.setIndeterminate(true);
        
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
        JPanel panelLineEnd = (JPanel) lComp.getComponentByPath(componentPath);
        panelLineEnd.repaint();
        
        ArrayList<String> arrStr = new ArrayList<String>();
        BlockingQueue<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> pipeDirList = new ArrayBlockingQueue(1000, true);
        
        ZPINcFsIdxFileVisitor fileVisitor = new ZPINcFsIdxFileVisitor(pipeDirList);
        
        Path prePathToStart = pathDevDirToScan;
        
        prePathToStart = prePathToStart.normalize();
        try {
            prePathToStart = prePathToStart.toRealPath(LinkOption.NOFOLLOW_LINKS);
        } catch (IOException ex) {
            ZPINcAppHelper.logException(ZPINcFsIdxStorage.class.getCanonicalName(), ex);
        }
        arrStr.add("pathToStart:" + prePathToStart.toString());
        final Path pathToStart = prePathToStart;
        arrStr.add("[count Dir]"
        + fileVisitor.getCountPostVisitDir()
        + "[count File]"
        + fileVisitor.getCountVisitFile());
        UUID randomUUID = UUID.randomUUID();
        
        ConcurrentSkipListMap<UUID, ZPINcDataListAttr> makeForRecord = 
                new ConcurrentSkipListMap<UUID, ZPINcDataListAttr>();
                
        ConcurrentSkipListMap<UUID, ZPINcDataListAttr> listForRecord = 
                new ConcurrentSkipListMap<UUID, ZPINcDataListAttr>();
        
        ArrayList<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> listOfListForRecord =
                new ArrayList<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>>();
        ArrayList<Integer> listOfSizeIterarion = new ArrayList<Integer>();
        ZPINcThWorkerUpGUITreeWork.workTreeAddChildren(lComp, arrStr);
        arrStr.clear();
        SwingWorker<Void, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> underGroundWorker = 
                new SwingWorker<Void, ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> () {
                    
            @Override
            protected Void doInBackground() {
                try {
                    Files.walkFileTree(pathToStart, fileVisitor);
                } catch (IOException ex) {
                    ZPINcAppHelper.logException(ZPINcThWorkerGUIDirListScan.class.getCanonicalName(), ex);
                }
                int emptyCount = 0;
                int size = 0;
                boolean hasData = Boolean.FALSE;
                try {
                    do {
                        boolean notExitFromReadData = Boolean.TRUE;
                        do { 
                            size = fileVisitor.getBuffDirList().size();
                            if( (size > 0) ){
                                hasData = Boolean.TRUE;
                                emptyCount = 0;
                                publish(fileVisitor.getBuffDirList().take());
                            }
                            if( hasData ){
                               if( size == 0 ){
                                    notExitFromReadData = Boolean.FALSE;
                                } 
                            }
                            listOfSizeIterarion.add(size);
                        } while ( notExitFromReadData );
                        emptyCount++;
                    } while ( emptyCount < 5 );
                } catch (InterruptedException ex) {
                    ZPINcAppHelper.logException(ZPINcThWorkerGUIDirListScan.class.getCanonicalName(), ex);
                }
                return null;
            }
            
            @Override
            protected void process(List<ConcurrentSkipListMap<UUID, ZPINcDataListAttr>> chunks){
                ArrayList<String> arrOutStr = null;
                arrOutStr = new ArrayList<String>();
                int numPart = 0;
                for(ConcurrentSkipListMap<UUID, ZPINcDataListAttr> item : chunks){
                    arrOutStr.add("Part" + numPart + " of " + chunks.size());
                    for (Map.Entry<UUID, ZPINcDataListAttr> entry : item.entrySet()) {
                        UUID key = entry.getKey();
                        ZPINcDataListAttr value = entry.getValue();
                        arrOutStr.add("[key]" + key
                            + "[value]" + value.getShortDataToString());
                    }
                    makeForRecord.putAll(item);
                    //for publish and save to index code here
                    ZPINcThWorkerUpGUITreeOutput.outputTreeAddChildren(lComp, arrOutStr);
                    arrOutStr.clear();
                    numPart++;
                }
                if( makeForRecord.size() > 100 ){
                    int countKey = 0;
                    for (Map.Entry<UUID, ZPINcDataListAttr> itemForRecord : makeForRecord.entrySet()) {
                        if( countKey < 100){
                            UUID key = itemForRecord.getKey();
                            listForRecord.put(key, makeForRecord.get(key));
                        }
                        countKey++;
                    }
                    listOfListForRecord.add(listForRecord);
                    arrStr.add("[process]makeForRecord: " + makeForRecord.size());
                }
                arrStr.add("[process]listForRecord count: " + listForRecord.size());
                arrStr.add("[process]listOfListForRecord count: " + listOfListForRecord.size());
                arrStr.add("[process]makeForRecord: " + makeForRecord.size());
                ZPINcThWorkerUpGUITreeWork.workTreeAddChildren(lComp, arrStr);
                arrOutStr.clear();
                arrStr.clear();
            }
            
            @Override
            protected void done(){
                arrStr.add("[done]listForRecord count: " + listForRecord.size());
                arrStr.add("[done]listOfListForRecord count: " + listOfListForRecord.size());
                arrStr.add("[done]makeForRecord: " + makeForRecord.size());

                arrStr.add("[done]");
                arrStr.add("[done]listOfListForRecord: " + listOfListForRecord.size());
                ZPINcThWorkerUpGUITreeWork.workTreeAddChildren(lComp, arrStr);
                progressBar.setIndeterminate(false);
                buttonSearch.setEnabled(true);
                String componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanelCenter();
                JPanel panelCenter = (JPanel) lComp.getComponentByPath(componentPath);
                panelCenter.repaint();
            }
                    
        };
        underGroundWorker.execute();
    }
}
