package edu.psu.ist.view;

import edu.psu.ist.controller.TodoListController;

import javax.swing.*;

public class TodoListView extends JFrame{
    private JPanel todoListPanel;
    private JTable todoListTable;
    private JButton newBtn;
    private JButton detailsBtn;
    private JButton deleteBtn;
    private JButton doneBtn;
    private JScrollPane todoScrollPane;
    private final TodoListController controller;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 600;

    public TodoListView(TodoListController controller) {
        this.controller = controller;
        createListComponents();
    }

    public JPanel getTodoListPanel() {
        return todoListPanel;
    }

    public JTable getTodoListTable() {
        return todoListTable;
    }

    public JButton getNewBtn() {
        return newBtn;
    }

    public JButton getDetailsBtn() {
        return detailsBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JButton getDoneBtn() {
        return doneBtn;
    }

    public void createListComponents() {
        this.add(todoListPanel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        todoListTable.setModel(controller.getTodoTableModel());
        //if you want to show the scroll bar on the view
        todoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
