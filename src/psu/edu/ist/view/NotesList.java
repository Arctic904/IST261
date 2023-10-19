package psu.edu.ist.view;

import psu.edu.ist.controller.NoteController;
import psu.edu.ist.model.Incident;
import psu.edu.ist.model.Note;
import psu.edu.ist.model.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

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
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 600;
    private final NoteController controller;
    private ArrayList<User> users;
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

    public void setUserSelection(ArrayList<User> Users) {
        this.users = Users;
        userSelection.removeAllItems();
        Users.stream().forEach(user -> userSelection.addItem(user));
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public Note saveNote() {
        int year = Integer.parseInt(creationDateTextInput.getText().split("/")[2]) - 1900;
        int month = Integer.parseInt(creationDateTextInput.getText().split("/")[0]) - 1;
        int day = Integer.parseInt(creationDateTextInput.getText().split("/")[1]);
        Date created = new Date(year, month, day);
        return new Note(this.titleTextInput.getText(), (User) this.userSelection.getSelectedItem(), created, this.contentTextInput.getText());
    }

    public void setNote(String title, Date created, User user, String content){
        this.titleTextInput.setText(title);
        String dateString = (created.getMonth()+1) + "/" + created.getDay() + "/" + (created.getYear()+1900);
        this.creationDateTextInput.setText(dateString);
        if(users.contains(user)){
            userSelection.setSelectedItem(user);
        } else {
            ArrayList<User> updatedList = this.users;
            updatedList.add(user);
            setUserSelection(updatedList);
            userSelection.setSelectedItem(user);
        }
        this.contentTextInput.setText(content);
    }

    public void setNote(Note note){
        this.titleTextInput.setText(note.getTitle());
        String dateString = (note.getCreationDate().getMonth()+1) + "/" + note.getCreationDate().getDay() + "/" + (note.getCreationDate().getYear()+1900);
        this.creationDateTextInput.setText(dateString);
        if(users.contains(note.getCreatedBy())){
            userSelection.setSelectedItem(note.getCreatedBy());
        } else {
            ArrayList<User> updatedList = this.users;
            updatedList.add(note.getCreatedBy());
            setUserSelection(updatedList);
            userSelection.setSelectedItem(note.getCreatedBy());
        }
        this.contentTextInput.setText(note.getContent());
    }
}
