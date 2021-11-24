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

import java.util.UUID;

/**
 *
 * @author wladimirowichbiaran
 */
public class ThIndexManager extends Thread{
    
    ThIndexManager(){
        super(UUID.randomUUID().toString());
        //Thread.currentThread().setName(UUID.randomUUID().toString());
    }
    
    @Override
    public void run(){
        System.out.println("                                                                                    indexManager");
        ThIndexRule thIndexRule = new ThIndexRule();
        
        ThIndexDirList thIndexDirList = new ThIndexDirList(thIndexRule);
        thIndexRule.setThreadIndexDirList(thIndexDirList);
        
        thIndexDirList.start();
        try{
            thIndexDirList.join();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        //make word index
        ThIndexWord thIndexWord = new ThIndexWord(thIndexRule);
        thIndexWord.start();
    }
}
