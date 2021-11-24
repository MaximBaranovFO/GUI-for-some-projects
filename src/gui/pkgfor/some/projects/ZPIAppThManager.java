/*
 * Copyright 2021 Администратор.
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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Администратор
 */
public class ZPIAppThManager {
    
    private ConcurrentSkipListMap<String,Runnable> currentWorkerList;
    private ArrayBlockingQueue<String> messagesQueueForLogging;
    private ZPIAppObjectsList outerObectsForApp;
    private ZPIThIndexRule thIndexRule;

    public ZPIAppThManager(ZPIAppObjectsList objectsForApp) {
        this.outerObectsForApp = objectsForApp;
        this.currentWorkerList = objectsForApp.getWorkerList();
        this.messagesQueueForLogging = objectsForApp.getLoggingQueue();
        
    }
    protected AppObjectsList getListOfObjects(){
        return this.outerObectsForApp;
    }
    protected void setIndexRule(final ThIndexRule outerThIndexRule){
        this.thIndexRule = outerThIndexRule;
    }
    protected ThIndexRule getIndexRule(){
        return this.thIndexRule;
    }
    protected static void createNewWorkerGroup(){
        ThreadGroup groupForThreads = new ThreadGroup("ncfvThGroup");
        Thread thForExecutions = new Thread(groupForThreads, "addThread");
    }
    
    /**
     * create threads with called class.static functions in his logic and algoritms...
     */    
}
