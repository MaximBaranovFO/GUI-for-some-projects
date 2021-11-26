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
public class ZPIThDirListState {
    private ZPIThDirListBusReaded busReadedJob;
    private Boolean isSetReadedJob;
    
    private ZPIThDirListBusWrited busWritedJob;
    private Boolean isSetWritedJob;
    
    private ZPIThDirListBusReaded busSendToIndexWord;
    private Boolean isSetSendToIndexWord;
    
    ZPIThDirListState(){
        setFalseReadedJob();
        setFalseWritedJob();
        setFalseSendToIndexWord();
    }
    /**
     * 
     * @return
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThDirListBusReaded getBusJobForRead(){
        if( !this.isReadedJob() ){
            throw new IllegalArgumentException("Bus jobs for read not set in " + ZPIThDirListState.class.getCanonicalName());
        }
        return this.busReadedJob;
    }
    protected void setBusJobForRead(final ZPIThDirListBusReaded busReadOuter){
        this.busReadedJob = busReadOuter;
        setTrueReadedJob();
    }
    protected void setTrueReadedJob(){
        this.isSetReadedJob = Boolean.TRUE;
    }
    protected void setFalseReadedJob(){
        this.isSetReadedJob = Boolean.FALSE;
    }
    protected Boolean isReadedJob(){
        if( this.isSetReadedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThDirListBusWrited getBusJobForWrite(){
        if( !this.isWritedJob() ){
            throw new IllegalArgumentException("Bus jobs for write not set in " + ZPIThDirListState.class.getCanonicalName());
        }
        return this.busWritedJob;
    }
    protected void setBusJobForWrite(final ZPIThDirListBusWrited busWriteOuter){
        this.busWritedJob = busWriteOuter;
        setTrueWritedJob();
    }
    protected void setTrueWritedJob(){
        this.isSetWritedJob = Boolean.TRUE;
    }
    protected void setFalseWritedJob(){
        this.isSetWritedJob = Boolean.FALSE;
    }
    protected Boolean isWritedJob(){
        if( this.isSetWritedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThDirListBusReaded getBusJobForSendToIndexWord(){
        if( !this.isSendToIndexWord() ){
            throw new IllegalArgumentException("Bus jobs for read not set in " + ZPIThDirListState.class.getCanonicalName());
        }
        return this.busSendToIndexWord;
    }
    protected void setBusJobForSendToIndexWord(final ZPIThDirListBusReaded busReadOuter){
        this.busSendToIndexWord = busReadOuter;
        setTrueSendToIndexWord();
    }
    protected void setTrueSendToIndexWord(){
        this.isSetSendToIndexWord = Boolean.TRUE;
    }
    protected void setFalseSendToIndexWord(){
        this.isSetSendToIndexWord = Boolean.FALSE;
    }
    protected Boolean isSendToIndexWord(){
        if( this.isSetSendToIndexWord ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
