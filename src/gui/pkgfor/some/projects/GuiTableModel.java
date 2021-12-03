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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import javax.swing.table.AbstractTableModel;

public class GuiTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    
    private JButton buttonDefault;

    private List<ListTableDO> data;
    private List<String> columnNames;

    public GuiTableModel() {
        data = getTableDataList();
        columnNames = getColumnNamesList();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Boolean.class;
        case 1: 
            return String.class;
        case 2: 
            return Integer.class;
        case 3:
            //return Boolean.class;
            return JButton.class;
        case 4:
            return Boolean.class;
        default:
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 ? true : false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
        case 0:
            return data.get(rowIndex).isSelect();
        case 1:
            return data.get(rowIndex).getName();
        case 2:
            return data.get(rowIndex).getAge();
        case 3:
            return data.get(rowIndex).isDo();
        case 4:
            return data.get(rowIndex).isWorkerShowInfo();
        default:
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
        case 0:
            data.get(rowIndex).setSelect((Boolean) aValue);
            break;
        case 1:
            data.get(rowIndex).setName(aValue != null ? aValue.toString() : null);
            break;
        case 2:
            data.get(rowIndex).setAge(aValue != null ? Integer.parseInt(aValue.toString()) : null);
            break;
        case 3:
            data.get(rowIndex).setDo((JButton) getDefaultButton(columnIndex));
            break;
        case 4:
            data.get(rowIndex).setWorkerShowInfo((JButton) getDefaultButton(columnIndex));
            break;
        default:
            break;
        }
    }

    public void addRow(ListTableDO do1) {
        data.add(do1);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void deleteRow() {
        for(int rowIndex = data.size() - 1; rowIndex >= 0; rowIndex--) {
            if(data.get(rowIndex).isSelect()) {
                data.remove(rowIndex);
            }
        }

        fireTableDataChanged();
    }

    private List<ListTableDO> getTableDataList() {

        List<ListTableDO> list = new ArrayList<ListTableDO>();

        ListTableDO do1 = null;
        for(int i = 0; i < 5; i++) {

            do1 = new ListTableDO();
            do1.setSelect(false);
            do1.setName("Student " + i);
            do1.setAge(i);
            do1.setDo(getDefaultButton(i));
            do1.setWorkerShowInfo(getDefaultButton(i));

            list.add(do1);
        }

        return list;
    }

    private List<String> getColumnNamesList() {
        List<String> names = new ArrayList<String>();

        names.add("Select");
        names.add("Name");
        names.add("Age");
        names.add("Worker Do");
        names.add("About Info");

        return names;
    }
    private JButton getDefaultButton(Integer idxForAddButton){
        JButton returnedButton = new JButton();
        returnedButton.setName("Do ".concat(String.valueOf(idxForAddButton)));
        
        return returnedButton;
    }
}
/*public class GuiTableModel {
    
}*/
