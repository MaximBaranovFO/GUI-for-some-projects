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

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppObjectsBusHelper {
    protected static ArrayList<String> cleanBusArrayBlockingToArrayString(ArrayBlockingQueue<String> listForLogStrs){
        String outSizeInputed = "--------size bus in AppObjectsBusHelper.cleanBusArrayBlockingToArrayString " 
                + listForLogStrs.size();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outSizeInputed, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_BUS_HELPER_VIEW_SIZE_DATA_FOR_CLEAN_OUT);
        ArrayList<String> forRecord = new ArrayList<String>();
        String poll;
        do{
            poll = listForLogStrs.poll();
            if( poll != null ){
                forRecord.add(poll);
            }
        }while( !listForLogStrs.isEmpty() );
        String outSizeOutputed = "--------size AppObjectsBusHelper.cleanBusArrayBlockingToArrayString array for write " + forRecord.size();
        ZPINcAppHelper.outToConsoleIfDevAndParamTrue(outSizeOutputed, ZPIAppConstants.LOG_LEVEL_IS_DEV_TO_CONS_HTML_BUS_HELPER_VIEW_SIZE_DATA_FOR_CLEAN_OUTPUT);
        return forRecord;
    }
    protected static ArrayBlockingQueue<String> cleanBusForRunnables(ArrayBlockingQueue<String> listForLogStrs){
        System.out.println("--------size bus in AppObjectsBusHelper.cleanBusForRunnables " + listForLogStrs.size());
        ArrayBlockingQueue<String> forRecord = new ArrayBlockingQueue<String>(listForLogStrs.size() + 100);
        String poll;
        do{
            poll = listForLogStrs.poll();
            if( poll != null ){
                forRecord.add(poll);
            }
        }while( !listForLogStrs.isEmpty() );
        System.out.println("--------size AppObjectsBusHelper.cleanBusForRunnables array for write " + forRecord.size());
        return forRecord;
    }
    protected static ArrayBlockingQueue<String> cleanBusFromArray(ArrayList<String> listForLogStrs){
        ArrayBlockingQueue<String> forRecord = new ArrayBlockingQueue<String>(listForLogStrs.size() 
                + ZPIAppConstants.LOG_HTML_CLEAN_FROM_BUS_ABOVE_QUEUE_SIZE);
        for(String elToReturn : listForLogStrs){
            forRecord.add(new String(elToReturn));
        }
        return forRecord;
    }
}
