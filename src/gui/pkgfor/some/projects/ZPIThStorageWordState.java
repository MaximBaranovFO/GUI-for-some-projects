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
public class ZPIThStorageWordState {
    /**
     * ZPIThStorageWordBusInput
     */
    private ZPIThStorageWordBusInput busStorageWordRouterJob;
    private Boolean isSetStorageWordRouterJob;
    /**
     * ZPIThStorageWordBusWriter
     */
    private ZPIThStorageWordBusWriter busStorageWordRouterJobToWriter;
    private Boolean isSetStorageWordRouterJobToWriter;
    /**
     * ZPIThStorageWordBusReader
     */
    private ZPIThStorageWordBusReader busStorageWordRouterJobToReader;
    private Boolean isSetStorageWordRouterJobToReader;
    /**
     * ZPIThStorageWordBusOutput
     */
    private ZPIThStorageWordBusOutput busWordWritedJob;
    private Boolean isSetWordWritedJob;
    /**
     * ZPIThStorageWordBusOutput
     */
    private ZPIThStorageWordBusOutput busLongWordWritedJob;
    private Boolean isSetLongWordWritedJob;
    /**
     * ZPIThStorageWordBusReadedFlow thStorageWordFlowRead
     * @todo Bus into State
     */
    private ZPIThStorageWordBusReadedFlow thStorageWordFlowRead;
    private Boolean isSetStorageWordFlowReaded;
    
    public ZPIThStorageWordState() {
        setFalseStorageWordRouterJob();
        setFalseWordWritedJob();
        setFalseLongWordWritedJob();
        setFalseStorageWordRouterJobToWriter();
        setFalseStorageWordRouterJobToReader();
        /**
         * ThStorageWordFlowReaded
         */
        setFalseStorageWordFlowReaded();
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThStorageWordBusInput getBusJobForStorageWordRouterJob(){
        if( !this.isStorageWordRouterJob() ){
            throw new IllegalArgumentException("Bus jobs for output not set in " + ZPIThStorageWordState.class.getCanonicalName());
        }
        return this.busStorageWordRouterJob;
    }
    protected void setBusJobForStorageWordRouterJob(final ZPIThStorageWordBusInput busStorageWordRouterJobOuter){
        this.busStorageWordRouterJob = busStorageWordRouterJobOuter;
        setTrueStorageWordRouterJob();
    }
    protected void setTrueStorageWordRouterJob(){
        this.isSetStorageWordRouterJob = Boolean.TRUE;
    }
    protected void setFalseStorageWordRouterJob(){
        this.isSetStorageWordRouterJob = Boolean.FALSE;
    }
    protected Boolean isStorageWordRouterJob(){
        if( this.isSetStorageWordRouterJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThStorageWordBusWriter getBusJobForStorageWordRouterJobToWriter(){
        if( !this.isStorageWordRouterJobToWriter() ){
            throw new IllegalArgumentException("Bus jobs for output not set in " + ZPIThStorageWordState.class.getCanonicalName());
        }
        return (ZPIThStorageWordBusWriter) this.busStorageWordRouterJobToWriter;
    }
    protected void setBusJobForStorageWordRouterJobToWriter(final ZPIThStorageWordBusWriter busStorageWordRouterJobToWriterOuter){
        this.busStorageWordRouterJobToWriter = (ZPIThStorageWordBusWriter) busStorageWordRouterJobToWriterOuter;
        setTrueStorageWordRouterJobToWriter();
    }
    protected void setTrueStorageWordRouterJobToWriter(){
        this.isSetStorageWordRouterJobToWriter = Boolean.TRUE;
    }
    protected void setFalseStorageWordRouterJobToWriter(){
        this.isSetStorageWordRouterJobToWriter = Boolean.FALSE;
    }
    protected Boolean isStorageWordRouterJobToWriter(){
        if( this.isSetStorageWordRouterJobToWriter ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThStorageWordBusReader getBusJobForStorageWordRouterJobToReader(){
        if( !this.isStorageWordRouterJobToReader() ){
            throw new IllegalArgumentException("Bus jobs for output not set in " + ZPIThStorageWordState.class.getCanonicalName());
        }
        return (ZPIThStorageWordBusReader) this.busStorageWordRouterJobToReader;
    }
    protected void setBusJobForStorageWordRouterJobToReader(final ZPIThStorageWordBusReader busStorageWordRouterJobToReaderOuter){
        this.busStorageWordRouterJobToReader = (ZPIThStorageWordBusReader) busStorageWordRouterJobToReaderOuter;
        setTrueStorageWordRouterJobToReader();
    }
    protected void setTrueStorageWordRouterJobToReader(){
        this.isSetStorageWordRouterJobToReader = Boolean.TRUE;
    }
    protected void setFalseStorageWordRouterJobToReader(){
        this.isSetStorageWordRouterJobToReader = Boolean.FALSE;
    }
    protected Boolean isStorageWordRouterJobToReader(){
        if( this.isSetStorageWordRouterJobToReader ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThStorageWordBusOutput getBusJobForWordWrite(){
        if( !this.isWordWritedJob() ){
            throw new IllegalArgumentException("Bus jobs for output not set in " + ZPIThWordState.class.getCanonicalName());
        }
        return this.busWordWritedJob;
    }
    protected void setBusJobForWordWrite(final ZPIThStorageWordBusOutput busWordWriteOuter){
        this.busWordWritedJob = busWordWriteOuter;
        setTrueWordWritedJob();
    }
    protected void setTrueWordWritedJob(){
        this.isSetWordWritedJob = Boolean.TRUE;
    }
    protected void setFalseWordWritedJob(){
        this.isSetWordWritedJob = Boolean.FALSE;
    }
    protected Boolean isWordWritedJob(){
        if( this.isSetWordWritedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 
     * @return 
     * @throws #java.lang.IllegalArgumentException
     */
    protected ZPIThStorageWordBusOutput getBusJobForLongWordWrite(){
        if( !this.isLongWordWritedJob() ){
            throw new IllegalArgumentException("Bus jobs for output not set in " + ZPIThWordState.class.getCanonicalName());
        }
        return this.busLongWordWritedJob;
    }
    protected void setBusJobForLongWordWrite(final ZPIThStorageWordBusOutput busLongWordWriteOuter){
        this.busLongWordWritedJob = busLongWordWriteOuter;
        setTrueLongWordWritedJob();
    }
    protected void setTrueLongWordWritedJob(){
        this.isSetLongWordWritedJob = Boolean.TRUE;
    }
    protected void setFalseLongWordWritedJob(){
        this.isSetLongWordWritedJob = Boolean.FALSE;
    }
    protected Boolean isLongWordWritedJob(){
        if( this.isSetLongWordWritedJob ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
     /**
     * ZPIThStorageWordBusReadedFlow
     * @return 
     */
    protected ZPIThStorageWordBusReadedFlow getStorageWordFlowReaded(){
        if( !this.isStorageWordFlowReaded() ){
            throw new IllegalArgumentException(ZPIThStorageWordBusReadedFlow.class.getCanonicalName() + " object not set in " + ZPIThWordRule.class.getCanonicalName());
        }
        return this.thStorageWordFlowRead;
    }
    protected void setStorageWordFlowReaded(final ZPIThStorageWordBusReadedFlow stateWordOuter){
        this.thStorageWordFlowRead = stateWordOuter;
        setTrueStorageWordFlowReaded();
    }
    protected void setTrueStorageWordFlowReaded(){
        this.isSetStorageWordFlowReaded = Boolean.TRUE;
    }
    protected void setFalseStorageWordFlowReaded(){
        this.isSetStorageWordFlowReaded = Boolean.FALSE;
    }
    protected Boolean isStorageWordFlowReaded(){
        if( this.isSetStorageWordFlowReaded ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
