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

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPINcSwGUITreeShowOutput {

    protected static JTree showOutput(ZPINcSwGUIComponentStatus lComp) {
        DefaultMutableTreeNode treeTop = 
                new DefaultMutableTreeNode("Output contained:");
        JTree treeNodes = new JTree(treeTop);
        String pathComponent = 
                ZPINcSwGUIComponentRouter.pathMainFramePanelLineEndTabbedPaneOutputScrollPaneTreeShowOutput();
        lComp.putComponents(pathComponent, treeNodes);
        return treeNodes;
    }
    
}
