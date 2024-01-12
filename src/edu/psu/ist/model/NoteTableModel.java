package edu.psu.ist.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NoteTableModel extends AbstractTableModel {
    private String[] columnNames = {"Title", "Created By", "Creation Date", "Content"};
    private List<Note> noteList;

    public NoteTableModel(List<Note> newNoteList){
        noteList = newNoteList;
    }
    @Override
    public int getRowCount() {
        return noteList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> noteList.get(rowIndex).getTitle();
            case 1 -> noteList.get(rowIndex).getCreatedBy();
            case 2 -> noteList.get(rowIndex).getCreationDate();
            case 3 -> noteList.get(rowIndex).getContent();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }


}
