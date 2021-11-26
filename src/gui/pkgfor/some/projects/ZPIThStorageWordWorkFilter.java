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
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 ZPIThStorageWordLogicFilter.java:283
 ThStorageWordLogicWrite.java:380
 ThStorageWordCache.java:202
 * 
 * @author wladimirowichbiaran
 */
public class ZPIThStorageWordWorkFilter implements Runnable{
    private ZPIThStorageWordRule ruleStorageWordFilter;
    
    ZPIThStorageWordWorkFilter(final ZPIThStorageWordRule outerRuleStorageWordFilter){
        this.ruleStorageWordFilter = outerRuleStorageWordFilter;
    }
    
    @Override
    public void run(){
        System.out.println(ZPIThStorageWordWorkFilter.class.getCanonicalName() 
                + " run and say " 
                + this.ruleStorageWordFilter.toString());
        this.ruleStorageWordFilter.setTrueRunnedStorageWordWorkFilter();
        ThreadLocal<ZPIThStorageWordLogicFilter> logicStorageWordWorkFilter = new ThreadLocal<ZPIThStorageWordLogicFilter>();
        try{
            logicStorageWordWorkFilter.set(new ZPIThStorageWordLogicFilter());
            logicStorageWordWorkFilter.get().doFilterForIndexStorageWord(this.ruleStorageWordFilter);
        } finally {
            logicStorageWordWorkFilter.remove();
            logicStorageWordWorkFilter = null;
            this.ruleStorageWordFilter.setFalseRunnedStorageWordWorkFilter();
        }
    }
}
