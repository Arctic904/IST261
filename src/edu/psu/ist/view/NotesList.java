package edu.psu.ist.view;

import edu.psu.ist.model.Incident;
import edu.psu.ist.controller.NoteController;
import edu.psu.ist.model.Note;
import edu.psu.ist.model.User;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class NotesList extends JFrame{
    private JTextField titleTextInput;
    private JButton prevBtn;
    private JButton nextBtn;
    private JTextField creationDateTextInput;
    private JTextField contentTextInput;
    private JPanel notesList;
    private JButton saveBtn;
    private JButton newUserBtn;
    private JComboBox<User> userSelection;
    private JButton deleteBtn;
    private JButton quitBtn;
    private JButton backBtn;
    private JButton todosBtn;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 600;
    private final NoteController controller;
    private List<User> users;
    public NotesList(NoteController controller){
        this.controller = controller;
        createComponents();
    }

    private void createComponents() {
        this.add(notesList);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Incident incident = this.controller.getParentIncident();
        this.setTitle("Notes for: " + incident.getTitle() + ". Severity: " + incident.getSeverity().toString());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JButton getPrevBtn() {
        return prevBtn;
    }

    public JButton getNextBtn() {
        return nextBtn;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getNewUserBtn() {
        return newUserBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JButton getTodosBtn() {
        return todosBtn;
    }

    public void setUserSelection(List<User> Users) {
        this.users = Users;
        userSelection.removeAllItems();
        Users.forEach(user -> userSelection.addItem(user));
    }

    public List<User> getUsers(){
        return this.users;
    }

    public String getDate() {
        return creationDateTextInput.getText();
    }

    public Note saveNote() {
        int year = Integer.parseInt(getDate().split("/")[2]) - 1900;
        int month = Integer.parseInt(getDate().split("/")[0]) - 1;
        int day = Integer.parseInt(getDate().split("/")[1]);
        Date created = new Date(year, month, day);
        return new Note(this.titleTextInput.getText(), (User) this.userSelection.getSelectedItem(), created, this.contentTextInput.getText());
    }

    public void setNote(String title, Date created, User user, String content){
        this.titleTextInput.setText(title);
        String dateString = (created.getMonth()+1) + "/" + created.getDate() + "/" + (created.getYear()+1900);
        this.creationDateTextInput.setText(dateString);
        if(users.contains(user)){
            userSelection.setSelectedItem(user);
        } else {
            List<User> updatedList = this.users;
            updatedList.add(user);
            setUserSelection(updatedList);
            userSelection.setSelectedItem(user);
        }
        this.contentTextInput.setText(content);
    }

    public void setNote(Note note){
        this.titleTextInput.setText(note.getTitle());
        String dateString = (note.getCreationDate().getMonth()+1) + "/" + note.getCreationDate().getDate() + "/" + (note.getCreationDate().getYear()+1900);
        this.creationDateTextInput.setText(dateString);
        if(users.contains(note.getCreatedBy())){
            userSelection.setSelectedItem(note.getCreatedBy());
        } else {
            List<User> updatedList = this.users;
            updatedList.add(note.getCreatedBy());
            setUserSelection(updatedList);
            userSelection.setSelectedItem(note.getCreatedBy());
        }
        this.contentTextInput.setText(note.getContent());
    }
}
