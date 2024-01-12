package edu.psu.ist.controller;

import edu.psu.ist.model.*;
import edu.psu.ist.view.NoteListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListController {
    private NoteListView listView;
    List<Note> notes;
    NoteTableModel noteTableModel;
    NoteController noteController;
    private Incident parentIncident;
    private List<User> usersList;
    private UserPersistenceController userPersistenceController = new UserPersistenceController();
    private IncidentPersistenceController incidentPersistenceController = new IncidentPersistenceController();

    public ListController() {
       createInitialElements();
        this.usersList = userPersistenceController.getUsers();
        List<Incident> incidents = incidentPersistenceController.getIncidents();
        this.parentIncident = incidents.get(0);
        this.notes = parentIncident.getNotes();
        this.noteTableModel = new NoteTableModel(notes);
        this.listView = new NoteListView(this);
        addActionListeners();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public NoteTableModel getNoteTableModel() {
        return noteTableModel;
    }

    private void addActionListeners() {
        listView.getNewBtn().addActionListener(this::NewBtnListener);
        listView.getDoneBtn().addActionListener(e -> {updatePersistence(); System.exit(0);});
        listView.getShowDetailsBtn().addActionListener(this::DetailsBtnListener);
        listView.getDeleteBtn().addActionListener(this::DeleteBtnListener);
    }

    private void DeleteBtnListener(ActionEvent actionEvent) {
        listView.dispose();
        int selectedRow = listView.getNoteListTable().getSelectedRow();
        System.out.println("selectedRow = " +selectedRow);
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this.listView, "Please select an element!");
            return;
        }
        this.noteController = new NoteController(this, selectedRow);
        this.noteController.deleteNote(selectedRow);
        updatePersistence();
    }

    private void DetailsBtnListener(ActionEvent actionEvent) {
        listView.dispose();
        //find the selected row
        int selectedRow = listView.getNoteListTable().getSelectedRow();
        System.out.println("selectedRow = " +selectedRow);
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this.listView, "Please select an element!");
            this.showListView();
            return;
        }
        //if no row is selected on the list, set it to show first element on the details view
        //show a detail view with the data for the selected element
        //pass the flow from list controller to details controller
        //do not instantiate the details view from this list controller
        this.noteController = new NoteController(this, selectedRow);
    }

    private void NewBtnListener(ActionEvent actionEvent) {
        listView.dispose();
        //if no row is selected on the list, set it to show first element on the details view
        //show a detail view with the data for the selected element
        //pass the flow from list controller to details controller
        //do not instantiate the details view from this list controller
        if(this.notes.isEmpty()){
            this.notes.add(new Note(null, null, new Date(), null));
        }
        this.noteController = new NoteController(this, this.notes.size()-1);
        this.noteController.NewNote();
    }

    public void updatePersistence() {
        List<Incident> tempIncidents = new ArrayList<>();
        tempIncidents.add(this.getParentIncident());
        incidentPersistenceController.setIncidents(tempIncidents);
        userPersistenceController.setUsers(this.getUsersList());
    }

    private void createInitialElements() {
        User bob = new User("Bob", "bob@gmail.com", "1234567890");
        User john = new User("John", "bob@gmail.com", "1234567890");
        User rob = new User("Rob", "bob@gmail.com", "1234567890");
        List<User> users = new ArrayList<>();
        users.add(bob);
        users.add(john);
        users.add(rob);
        this.usersList = users;
        Incident incident = new Incident("An Incident", "A test incident", bob, Severity.MEDIUM);
        incident.addNote("First Note", new Date(), bob, "The first note");
        incident.addNote("Update", new Date(), john, "An update to the first note");
        incident.addNote("Final", new Date(), rob, "Fixed issue");
        this.parentIncident = incident;
        this.notes = incident.getNotes();
    }

    public Incident getParentIncident() {
        return this.parentIncident;
    }

    public void showListView() {
        this.listView.setVisible(true);
        updatePersistence();
    }
}
