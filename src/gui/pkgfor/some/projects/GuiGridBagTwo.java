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

import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
//import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingWorker;
/**
 *
 * @author Администратор
 */
public class GuiGridBagTwo {
    private int gridx, gridy;
    private GridBagConstraints constraints;
    public GridBagConstraints get(){
        return constraints;
    }
    public GuiGridBagTwo nextCell(){
        constraints = new GridBagConstraints();
        constraints.gridx = gridx++;
        constraints.gridy = gridy;
        return this;
    }
    public GuiGridBagTwo nextRow(){
        gridy++;
        gridx = 0;
        
        constraints.gridx = 0;
        constraints.gridy = gridy;
        return this;
    }
    public GuiGridBagTwo span(){
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        return this;
    }
    public GuiGridBagTwo fillHorizontally(){
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return this;
    }
    public GuiGridBagTwo gap(int size){
        constraints.insets.right = size;
        return this;
    }
    public GuiGridBagTwo spanY(){
        constraints.gridheight = GridBagConstraints.REMAINDER;
        return this;
    }
    public GuiGridBagTwo fillBoth(){
        constraints.fill = GridBagConstraints.BOTH;
        return this;
    }
    public GuiGridBagTwo alignLeft(){
        constraints.anchor = GridBagConstraints.LINE_START;
        return this;
    }
    public GuiGridBagTwo alignRight(){
        constraints.anchor = GridBagConstraints.LINE_END;
        return this;
    }
    public GuiGridBagTwo setInsets(int left, int top, int right, int bottom){
        Insets i = new Insets(top, left, bottom, right);
        constraints.insets = i;
        return this;
    }
    public void insertEmptyRow(Container c, int height){
        Component comp = Box.createVerticalStrut(height);
        nextCell().nextRow().fillHorizontally().span();
        c.add(comp, get());
    }
    public void fillHorizontally(Container c){
        Component comp = Box.createGlue();
        nextCell().nextRow().fillBoth().setInsets(gridx, gridx + 1, gridx, gridx + 3);
        //nextCell().nextRow().fillBoth().span().spanY().setWeights(1.0f, 1.0f);
        c.add(comp, get());
        nextRow();
    }
}
