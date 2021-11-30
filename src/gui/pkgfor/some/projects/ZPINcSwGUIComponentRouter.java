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

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwGUIComponentRouter {
    /**
     * 
     * @return 
     */
    protected static String pathMainFrame(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanel(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineStart(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SLINESTART.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEnd(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SLINEEND.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPane(){
        return pathMainFramePanelLineEnd()
            + ZPINcStrGUIComponent.SJTABBEDPANE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneStack(){
        return pathMainFramePanelLineEndTabbedPane()
            + ZPINcStrGUIComponent.SSTACK.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneWork(){
        return pathMainFramePanelLineEndTabbedPane()
            + ZPINcStrGUIComponent.SWORK.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneOutput(){
        return pathMainFramePanelLineEndTabbedPane()
            + ZPINcStrGUIComponent.SOUTPUT.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneStackScrollPane(){
        return pathMainFramePanelLineEndTabbedPaneStack()
                + ZPINcStrGUIComponent.SJSCROLLPANE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneWorkScrollPane(){
        return pathMainFramePanelLineEndTabbedPaneWork()
                + ZPINcStrGUIComponent.SJSCROLLPANE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneOutputScrollPane(){
        return pathMainFramePanelLineEndTabbedPaneOutput()
                + ZPINcStrGUIComponent.SJSCROLLPANE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneStackScrollPaneTreeShowStack(){
        return pathMainFramePanelLineEndTabbedPaneStackScrollPane()
                + ZPINcStrGUIComponent.SJTREE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneWorkScrollPaneTreeShowWork(){
        return pathMainFramePanelLineEndTabbedPaneWorkScrollPane()
                + ZPINcStrGUIComponent.SJTREE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelLineEndTabbedPaneOutputScrollPaneTreeShowOutput(){
        return pathMainFramePanelLineEndTabbedPaneOutputScrollPane()
                + ZPINcStrGUIComponent.SJTREE.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageStart(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SPAGESTART.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageStartTextFieldSearch(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SPAGESTART.getStr()
                + ZPINcStrGUIComponent.STEXTFIELD.getStr()
                + ZPINcStrGUIComponent.SSEARCH.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageStartButtonSearch(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SPAGESTART.getStr()
                + ZPINcStrGUIComponent.SJBUTTON.getStr()
                + ZPINcStrGUIComponent.SSEARCH.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageEnd(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SPAGEEND.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageEndButtonGetStack(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SPAGEEND.getStr()
                + ZPINcStrGUIComponent.SJBUTTON.getStr()
                + ZPINcStrGUIComponent.SSTACK.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageEndProgressBar(){
        String componentPath = ZPINcStrGUIComponent.SMAIN.getStr()
            + ZPINcStrGUIComponent.SJFRAME.getStr()
            + ZPINcStrGUIComponent.SJPANEL.getStr()
            + ZPINcStrGUIComponent.SPAGEEND.getStr()
            + ZPINcStrGUIComponent.SJPROGRESSBAR.getStr();
        return componentPath;
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelPageEndButton(){
        String componentPath = ZPINcStrGUIComponent.SMAIN.getStr()
            + ZPINcStrGUIComponent.SJFRAME.getStr()
            + ZPINcStrGUIComponent.SJPANEL.getStr()
            + ZPINcStrGUIComponent.SPAGEEND.getStr()
            + ZPINcStrGUIComponent.SJBUTTON.getStr();
        return componentPath;
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelCenter(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
                + ZPINcStrGUIComponent.SJFRAME.getStr()
                + ZPINcStrGUIComponent.SJPANEL.getStr()
                + ZPINcStrGUIComponent.SCENTER.getStr();
    }
    /**
     * 
     * @return 
     */
    protected static String pathMainFramePanelCenterTable(){
        return ZPINcStrGUIComponent.SMAIN.getStr()
            + ZPINcStrGUIComponent.SJFRAME.getStr()
            + ZPINcStrGUIComponent.SJPANEL.getStr()
            + ZPINcStrGUIComponent.SCENTER.getStr()
            + ZPINcStrGUIComponent.SJTABLE.getStr();
    }
}
