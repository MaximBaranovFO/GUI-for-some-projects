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
public class ZPIThWordWorkRouter implements Runnable{
    private ZPIThWordRule ruleWordRouter;
    
    ZPIThWordWorkRouter(final ZPIThWordRule outerRuleWordRouter){
        this.ruleWordRouter = outerRuleWordRouter;
    }
    
    @Override
    public void run(){
        System.out.println(ZPIThWordWorkRouter.class.getCanonicalName() 
                + " run and say " 
                + this.ruleWordRouter.toString());
        this.ruleWordRouter.setTrueRunnedWordWorkRouter();
        ThreadLocal<ZPIThWordLogicRouter> logicWordWorkRouter = new ThreadLocal<ZPIThWordLogicRouter>();
        try{
            logicWordWorkRouter.set(new ZPIThWordLogicRouter());
            logicWordWorkRouter.get().doRouterForIndexWord(this.ruleWordRouter);
        } finally {
            logicWordWorkRouter.remove();
            logicWordWorkRouter = null;
            this.ruleWordRouter.setFalseRunnedWordWorkRouter();
        }
    }
    
}
