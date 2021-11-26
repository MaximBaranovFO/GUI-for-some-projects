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
 * Adiwl
 * <ul>
 * <li>Automated
 * <li>data
 * <li>indexing
 * <li>mechanic
 * <li>Rule
 * <ul>
 * Contain methods for provided access for created objects
 * @author wladimirowichbiaran
 */
public class ZPIAdimRule {
    /**
     * ZPIAdibProcessCommand
     */
    private ZPIAdibProcessCommand commandProcess;
    private Boolean isSetZPIAdibProcessCommand;
    /**
     * ZPIAdifControlFlag
     */
    private ZPIAdifControlFlag controlFlag;
    private Boolean isSetZPIAdifControlFlag;
    /**
     * ZPIAdilRule
     */
    private ZPIAdilRule loggerRule;
    private Boolean isSetZPIAdilRule;
    
    public ZPIAdimRule(){
        setFalseZPIAdilRule();
        setFalseZPIAdibProcessCommand();
        setFalseZPIAdifControlFlag();
    }
    /**
     * ZPIAdilRule
     * @return 
     * @throws IllegalArgumentException if not set
     */
    protected ZPIAdilRule getZPIAdilRule(){
        if( !this.isZPIAdilRule() ){
            throw new IllegalArgumentException(ZPIAdilRule.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIAdimRule.class.getCanonicalName());
        }
        return this.loggerRule;
    }
    /**
     * 
     * @param loggerZPIAdilRuleOuter 
     */
    protected void setZPIAdilRule(final ZPIAdilRule loggerZPIAdilRuleOuter){
        if( loggerZPIAdilRuleOuter != null){
            this.loggerRule = (ZPIAdilRule) loggerZPIAdilRuleOuter;
            setTrueZPIAdilRule();
        } else {
            throw new NullPointerException(ZPIAdilRule.class.getCanonicalName() 
                    + " object for set in " 
                    + ZPIAdimRule.class.getCanonicalName() 
                    + " is null");
        }
    }
    /**
     * Set in field <code>true</code>
     */
    protected void setTrueZPIAdilRule(){
        this.isSetZPIAdilRule = Boolean.TRUE;
    }
    /**
     * Set in field <code>false</code>
     */
    protected void setFalseZPIAdilRule(){
        this.isSetZPIAdilRule = Boolean.FALSE;
    }
    /**
     * 
     * @return true if ZPIAdilRule object set
     */
    protected Boolean isZPIAdilRule(){
        if( this.isSetZPIAdilRule ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIAdibProcessCommand
     * @return 
     * @throws IllegalArgumentException if not set
     */
    protected ZPIAdibProcessCommand getZPIAdibProcessCommand(){
        if( !this.isZPIAdibProcessCommand() ){
            throw new IllegalArgumentException(ZPIAdibProcessCommand.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIAdimRule.class.getCanonicalName());
        }
        return this.commandProcess;
    }
    /**
     * 
     * @param loggerZPIAdibProcessCommandOuter 
     */
    protected void setZPIAdibProcessCommand(final ZPIAdibProcessCommand loggerZPIAdibProcessCommandOuter){
        if( loggerZPIAdibProcessCommandOuter != null){
            this.commandProcess = (ZPIAdibProcessCommand) loggerZPIAdibProcessCommandOuter;
            setTrueZPIAdibProcessCommand();
        } else {
            throw new NullPointerException(ZPIAdibProcessCommand.class.getCanonicalName() 
                    + " object for set in " 
                    + ZPIAdimRule.class.getCanonicalName() 
                    + " is null");
        }
    }
    /**
     * Set in field <code>true</code>
     */
    protected void setTrueZPIAdibProcessCommand(){
        this.isSetZPIAdibProcessCommand = Boolean.TRUE;
    }
    /**
     * Set in field <code>false</code>
     */
    protected void setFalseZPIAdibProcessCommand(){
        this.isSetZPIAdibProcessCommand = Boolean.FALSE;
    }
    /**
     * 
     * @return true if ZPIAdibProcessCommand object set
     */
    protected Boolean isZPIAdibProcessCommand(){
        if( this.isSetZPIAdibProcessCommand ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * ZPIAdifControlFlag
     * @return 
     * @throws IllegalArgumentException if not set
     */
    protected ZPIAdifControlFlag getZPIAdifControlFlag(){
        if( !this.isZPIAdifControlFlag() ){
            throw new IllegalArgumentException(ZPIAdifControlFlag.class.getCanonicalName() 
                    + " object not set in " 
                    + ZPIAdimRule.class.getCanonicalName());
        }
        return this.controlFlag;
    }
    /**
     * 
     * @param loggerZPIAdifControlFlagOuter 
     */
    protected void setZPIAdifControlFlag(final ZPIAdifControlFlag controlZPIAdifControlFlagOuter){
        if( controlZPIAdifControlFlagOuter != null){
            this.controlFlag = (ZPIAdifControlFlag) controlZPIAdifControlFlagOuter;
            setTrueZPIAdifControlFlag();
        } else {
            throw new NullPointerException(ZPIAdifControlFlag.class.getCanonicalName() 
                    + " object for set in " 
                    + ZPIAdimRule.class.getCanonicalName() 
                    + " is null");
        }
    }
    /**
     * Set in field <code>true</code>
     */
    protected void setTrueZPIAdifControlFlag(){
        this.isSetZPIAdifControlFlag = Boolean.TRUE;
    }
    /**
     * Set in field <code>false</code>
     */
    protected void setFalseZPIAdifControlFlag(){
        this.isSetZPIAdifControlFlag = Boolean.FALSE;
    }
    /**
     * 
     * @return true if ZPIAdifControlFlag object set
     */
    protected Boolean isZPIAdifControlFlag(){
        if( this.isSetZPIAdifControlFlag ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
