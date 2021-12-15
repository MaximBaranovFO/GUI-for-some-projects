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

import java.awt.Component;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.Serializable;
//import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;
//import javax.swing.*;
//import java.awt.Button;
//import java.awt.*;
//import java.swing.JFrame;
//import java.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.InputStream;
import java.io.IOException;

import java.net.URL;

import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
/**
 *
 * @author Администратор
 */
public class GuiGridBagOne extends JFrame implements Serializable{
    public static GuiGridBagOne windowClass;
    GuiGridBagOne(){
        super("guiGridBagOne");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        Component addSomeTxt = add(new JButton("Some txt"));
        Component addCancel = add(new JButton("Cancel"));
        Component addWokerDo = add(new JButton("Do worker"));
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
        textFieldConstraints.weightx = 1.0f;
        
        add(new JTextField(10), textFieldConstraints);
        setSize(400, 200);
        setVisible(true);
        windowClass = this;
        GUIinterfaceNamesE.GuiFromWindowAttr.correctTitleOfGUIWindow(this);
    }
    public static void runMainExec(){
        SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    new GuiGridBagOne();
                }
            }
        );
    }
    public static void setElementedParams(){
        GuiGridBagTwo helperParams = new GuiGridBagTwo();
        helperParams.nextCell().gap(5);
        windowClass.add(new JLabel("Name:"), helperParams.get());
        helperParams.nextCell().span();
        windowClass.add(new JTextField(20));
    }
    

}
