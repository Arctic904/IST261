package edu.psu.ist.view;

import edu.psu.ist.model.Incident;
import edu.psu.ist.controller.ListController;

import javax.swing.*;

public class NoteListView extends JFrame{
    private JButton newBtn;
    private JButton showDetailsBtn;
    private JButton doneBtn;
    private JTable noteListTable;
    private JPanel pnlRoot;
    private JScrollPane scrollPane;
    private JButton deleteBtn;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 600;
    private final ListController controller;
    public NoteListView(ListController controller) {
        this.controller = controller;
        createListComponents();
    }

    public JButton getNewBtn() {
        return newBtn;
    }

    public JButton getShowDetailsBtn() {
        return showDetailsBtn;
    }

    public JButton getDoneBtn() {
        return doneBtn;
    }

    public JTable getNoteListTable() {
        return noteListTable;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public void createListComponents() {
        this.add(pnlRoot);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Incident incident = this.controller.getParentIncident();
        this.setTitle("Note List for: " + incident.getTitle() + ". Severity: " + incident.getSeverity().toString());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        noteListTable.setModel(controller.getNoteTableModel());
        //if you want to show the scroll bar on the view
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
