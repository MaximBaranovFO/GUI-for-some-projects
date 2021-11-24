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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.security.ProtectionDomain;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppThWorkDirListRule {
    private ThreadGroup workerDirList;
    
    private ThIndexRule currentIndexRule;
    
    private String nameIndexStorage;
    private String nameDirlistReader;
    private String nameDirlistTacker;
    private String nameDirListPacker;
    private String nameDirListWriter;
    
    private Path currentPathForMakeIndex;
    private Boolean needFinishDirlistReader;
    private Boolean needFinishDirlistTacker;
    private Boolean needFinishDirListPacker;
    private Boolean needFinishDirListWriter;
    
    private FileSystem currentFsZipIndexStorage;
    private Boolean storageSetted;
    private Boolean dirlistReaderSetted;
    private Boolean dirlistTackerSetted;
    private Boolean dirListPackerSetted;
    private Boolean dirListWriterSetted;
    
    private Boolean dirlistReaderLogicRunned;
    private Boolean dirlistTackerLogicRunned;
    private Boolean dirListPackerLogicRunned;
    private Boolean dirListWriterLogicRunned;
    
    private Boolean dirlistReaderLogicFinished;
    private Boolean dirlistTackerLogicFinished;
    private Boolean dirListPackerLogicFinished;
    private Boolean dirListWriterLogicFinished;
    
    private Thread runDirlistReader;
    private Thread runDirlistTacker;
    private Thread runDirListPacker;
    private Thread runDirListWriter;
    
    
    private AppThWorkDirListState workDirListState;

    public ZPIAppThWorkDirListRule(Path pathForMakeIndex) {
        
        this.storageSetted = Boolean.FALSE;
        this.dirlistReaderSetted = Boolean.FALSE;
        this.dirlistTackerSetted = Boolean.FALSE;
        this.dirListPackerSetted = Boolean.FALSE;
        this.dirListWriterSetted = Boolean.FALSE;
        
        this.dirlistReaderLogicRunned = Boolean.FALSE;
        this.dirlistTackerLogicRunned = Boolean.FALSE;
        this.dirListPackerLogicRunned = Boolean.FALSE;
        this.dirListWriterLogicRunned = Boolean.FALSE;
        
        this.dirlistReaderLogicFinished = Boolean.FALSE;
        this.dirlistTackerLogicFinished = Boolean.FALSE;
        this.dirListPackerLogicFinished = Boolean.FALSE;
        this.dirListWriterLogicFinished = Boolean.FALSE;
        
        this.nameIndexStorage = "IndexStorage";
        this.nameDirlistReader = "DirlistReader";
        this.nameDirlistTacker = "DirlistTacker";
        this.nameDirListPacker = "DirListPacker";
        this.nameDirListWriter = "DirListWriter";
        
        this.workerDirList = new ThreadGroup("workerDirListGroup");
        
        this.currentPathForMakeIndex = pathForMakeIndex;
        this.needFinishDirlistReader = Boolean.FALSE;
        this.needFinishDirlistTacker = Boolean.FALSE;
        this.needFinishDirListPacker = Boolean.FALSE;
        this.needFinishDirListWriter = Boolean.FALSE;
    }
    protected void setWorkDirListState(AppThWorkDirListState outerWorkDirListState){
        this.workDirListState = outerWorkDirListState;
        this.storageSetted = Boolean.TRUE;
    }
    protected Boolean isStorageSetted(){
        return this.storageSetted;
    }
    protected Boolean isDirListReaderSetted(){
        return this.dirlistReaderSetted;
    }
    protected Boolean isDirListTackerSetted(){
        return this.dirlistTackerSetted;
    }
    protected Boolean isDirListPackerSetted(){
        return this.dirListPackerSetted;
    }
    protected Boolean isDirListWriterSetted(){
        return this.dirListWriterSetted;
    }
    
    protected Boolean isDirListReaderLogicRunned(){
        if( this.dirlistReaderLogicRunned ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListTackerLogicRunned(){
        if( this.dirlistTackerLogicRunned ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListPackerLogicRunned(){
        if( this.dirListPackerLogicRunned ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListWriterLogicRunned(){
        if( this.dirListWriterLogicRunned ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    protected Boolean isDirListReaderLogicFinished(){
        if( this.dirlistReaderLogicFinished ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListTackerLogicFinished(){
        if( this.dirlistTackerLogicFinished ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListPackerLogicFinished(){
        if( this.dirListPackerLogicFinished ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    protected Boolean isDirListWriterLogicFinished(){
        if( this.dirListWriterLogicFinished ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    protected void setDirListReaderLogicRunned(){
        this.dirlistReaderLogicRunned = Boolean.TRUE;
    }
    protected void setDirListTackerLogicRunned(){
        this.dirlistTackerLogicRunned = Boolean.TRUE;
    }
    protected void setDirListPackerLogicRunned(){
        this.dirListPackerLogicRunned = Boolean.TRUE;
    }
    protected void setDirListWriterLogicRunned(){
        this.dirListWriterLogicRunned = Boolean.TRUE;
    }
    
    protected void setDirListReaderLogicFinished(){
        this.dirlistReaderLogicFinished = Boolean.TRUE;
    }
    protected void setDirListTackerLogicFinished(){
        this.dirlistTackerLogicFinished = Boolean.TRUE;
    }
    protected void setDirListPackerLogicFinished(){
        this.dirListPackerLogicFinished = Boolean.TRUE;
    }
    protected void setDirListWriterLogicFinished(){
        this.dirListWriterLogicFinished = Boolean.TRUE;
    }
    
    protected AppThWorkDirListState getWorkDirListState(){
        return this.workDirListState;
    }
    protected String getNameIndexStorage(){
        return nameIndexStorage;
    }
    protected String getNameDirlistReader(){
        return nameDirlistReader;
    }
    protected String getNameDirlistTacker(){
        return nameDirlistTacker;
    }
    protected String getNameDirListPacker(){
        return nameDirListPacker;
    }
    protected String getNameDirListWriter(){
        return nameDirListWriter;
    }
    protected ThreadGroup getThreadGroupWorkerDirList(){
        return this.workerDirList;
    }
    protected FileSystem setFsZipIndexStorage(FileSystem outerFsZipIndexStorage){
        this.currentFsZipIndexStorage = outerFsZipIndexStorage;
        return this.currentFsZipIndexStorage;
    }
    protected FileSystem getFsZipIndexStorage(){
        return this.currentFsZipIndexStorage;
    }
    
    protected void setDirlistReader(Thread outerDirlistReader){
        //for run threads full dump to html uncomment this or call it
        //AppObjectsInfo.dumpAllStackToHtml();
        this.runDirlistReader = outerDirlistReader;
        this.dirlistReaderSetted = Boolean.TRUE;
    }
    protected void setDirlistTacker(Thread outerDirlistTacker){
        this.runDirlistTacker = outerDirlistTacker;
        this.dirlistTackerSetted = Boolean.TRUE;
    }
    protected void setDirListPacker(Thread outerDirListPacker){
        this.runDirListPacker = outerDirListPacker;
        this.dirListPackerSetted = Boolean.TRUE;
    }
    protected void setDirListWriter(Thread outerDirListWriter){
        this.runDirListWriter = outerDirListWriter;
        this.dirListWriterSetted = Boolean.TRUE;
    }
    protected void startDirlistReader(){
        this.runDirlistReader.start();
    }
    protected void startDirlistTacker(){
        this.runDirlistTacker.start();
    }
    protected void startDirListPacker(){
        this.runDirListPacker.start();
    }
    protected void startDirListWriter(){
        this.runDirListWriter.start();
    }
    
    protected Path getCurrentPathForMakeIndex(){
        return this.currentPathForMakeIndex;
    }
    protected Boolean getNeedFinishStateDirlistReader(){
        return this.needFinishDirlistReader;
    }
    protected Boolean getNeedFinishStateDirlistTacker(){
        return this.needFinishDirlistTacker;
    }
    protected Boolean getNeedFinishStateDirListPacker(){
        return this.needFinishDirListPacker;
    }
    protected Boolean getNeedFinishStateDirListWriter(){
        return this.needFinishDirListWriter;
    }
    protected void sayNeedFinishDirlistReader(){
        this.needFinishDirlistReader = Boolean.TRUE;
    }
    protected void sayNeedFinishDirlistTacker(){
        this.needFinishDirlistTacker = Boolean.TRUE;
    }
    protected void sayNeedFinishDirListPacker(){
        this.needFinishDirListPacker = Boolean.TRUE;
    }
    protected void sayNeedFinishDirListWriter(){
        this.needFinishDirListWriter = Boolean.TRUE;
    }
}
