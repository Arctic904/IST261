package edu.psu.ist.model;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class TodoTableModel extends AbstractTableModel {
    private String[] columnNames = {"Attached Note", "Priority", "Due Date", "Content"};
    private LinkedList<Todo> todoList;

    public TodoTableModel(LinkedList<Todo> newTodoList){
        todoList = newTodoList;
    }
    @Override
    public int getRowCount() {
        return todoList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> todoList.get(rowIndex).getAttachedNote();
            case 1 -> todoList.get(rowIndex).getPriority();
            case 2 -> todoList.get(rowIndex).getDueDate();
            case 3 -> todoList.get(rowIndex).getContent();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }


}
