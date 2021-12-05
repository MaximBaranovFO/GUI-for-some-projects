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
public class GUIsomenamesA {
    private final ZPIAppObjectsList workerList;
    GUIsomenamesA(){
        workerList = new ZPIAppObjectsList();
    }
    protected void workerListShow(){
        this.workerList.doLogger();
        ConcurrentSkipListMap<String, Runnable> workerList1 = this.workerList.getWorkerList();
        ArrayBlockingQueue<String> loggingQueue = this.workerList.getLoggingQueue();
        String prefixError = this.workerList.getPrefixError();
        String prefixInfo = this.workerList.getPrefixInfo();
        String prefixState = this.workerList.getPrefixState();
        String prefixWarning = this.workerList.getPrefixWarning();
        Runnable threadByKey = this.workerList.getThreadByKey(prefixError);
    }
}
