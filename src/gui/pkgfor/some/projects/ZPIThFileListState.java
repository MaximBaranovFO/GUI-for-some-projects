/*
 * Copyright 2019 wladimirowichbiaran.
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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIThFileListState {
    private ZPIThFileListBusToNext busFileListToNextJob;
    private Boolean isSetFileListToNextJob;

    
    ZPIThFileListState(){
        setFalseFileListToNextJob();
    }

    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThFileListBusToNext getBusJobForFileListToNext(){
        if( !this.isFileListToNextJob() ){
            throw new IllegalArgumentException(ZPIThFileListBusToNext.class.getCanonicalName() 
                    + " object type of Bus jobs "
                    + "for transfer data into next index system not set in " 
                    + ZPIThFileListState.class.getCanonicalName());
        }
        return this.busFileListToNextJob;
    }
    protected void setBusJobForFileListToNext(final ZPIThFileListBusToNext busFileListToNextOuter){
        this.busFileListToNextJob = busFileListToNextOuter;
        setTrueFileListToNextJob();
    }
    protected void setTrueFileListToNextJob(){
        this.isSetFileListToNextJob = Boolean.TRUE;
    }
    protected void setFalseFileListToNextJob(){
        this.isSetFileListToNextJob = Boolean.FALSE;
    }
    protected Boolean isFileListToNextJob(){
        if( this.isSetFileListToNextJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
}
