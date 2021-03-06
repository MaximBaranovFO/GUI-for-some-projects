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

/**
 *
 * @author Администратор
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class GuiTableTree {
    private static JFrame windowCreated;
    private static JFrame windowCreatedWithArray;
    private static Boolean isNotWindowCreatedExist = Boolean.TRUE;
    private static Boolean isNotWindowCreatedExistWithArray = Boolean.TRUE;
    public static void GuiTableTree() {
        Runnable r;
        r = new Runnable() {
            
            @Override
            public void run() {
                new GuiTableTree().createUI();
            }
        };

        EventQueue.invokeLater(r);
    }
    protected static JFrame getWindowCreated(){
        if( isNotWindowCreatedExist ){
            openAndShow();
        }
        return windowCreated;
    }
    protected static JFrame getWindowCreatedWithArray(){
        if( isNotWindowCreatedExistWithArray ){
            int[] openAndShowWithArray = openAndShowWithArray();
        }
        return windowCreatedWithArray;
    }
    protected static void openAndShow(){
        Runnable r;
        r = new Runnable() {
            
            @Override
            public void run() {
                new GuiTableTree().createUI();
            }
        };

        EventQueue.invokeLater(r);
    }
    protected static int[] openAndShowWithArray(){
        int[] array = new int[3];
        array[0] = 1;
        array[1] = 2;
        array[3] = 3;
        Runnable r = new Runnable() {

            @Override
            public void run() {
                new GuiTableTree().createUIWithNames();
            }
        };

        EventQueue.invokeLater(r);
        return array;
    }

    private void createUI() {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPnl.add(addBtn);
        buttonPnl.add(delBtn);

        final GuiTableModel tableModel = new GuiTableModel();
        JTable table = new JTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ListTableDO do1 = new ListTableDO();
                do1.setName("Student " + tableModel.getRowCount());
                do1.setAge(tableModel.getRowCount());
                tableModel.addRow(do1);
            }
        });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.deleteRow();
            }
        });

        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.add(buttonPnl, BorderLayout.SOUTH);

        frame.setTitle("JTable Example.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        windowCreated = frame;
        isNotWindowCreatedExist = Boolean.FALSE;
        GUIinterfaceNamesE.GuiFromWindowAttr.correctTitleOfGUIWindow(frame);
    }
    
private void createUIWithNames() {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPnl.add(addBtn);
        buttonPnl.add(delBtn);

        final GuiTableModel tableModel = new GuiTableModel();
        JTable table = new JTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ListTableDO do1 = new ListTableDO();
                do1.setName("Student " + tableModel.getRowCount());
                do1.setAge(tableModel.getRowCount());
                tableModel.addRow(do1);
            }
        });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.deleteRow();
            }
        });

        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.add(buttonPnl, BorderLayout.SOUTH);

        frame.setTitle(GUIinterfaceNamesFA.OldGUIReconstruction.getWindowName("addSomeWindow"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        windowCreatedWithArray = frame;
        isNotWindowCreatedExistWithArray = Boolean.FALSE;
        GUIinterfaceNamesE.GuiFromWindowAttr.correctTitleOfGUIWindow(frame);
    }
}
/*public class GuiTableTree {
    
}*/
