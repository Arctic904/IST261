package edu.psu.ist.controller;

import edu.psu.ist.model.Incident;
import edu.psu.ist.model.Note;
import edu.psu.ist.model.User;
import edu.psu.ist.view.NotesList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class NoteController {
    private NotesList view;
    private Incident parentIncident;
    private ListController listController;
    private int idx;

    public NoteController(ListController listController, int selectedRow) {
        this.parentIncident = listController.getParentIncident();
        this.view = new NotesList(this);
        this.idx = selectedRow;
        this.view.setUserSelection(listController.getUsersList());
        this.view.setNote(this.parentIncident.getNotes().get(idx));
        this.listController = listController;
        addActionListeners();
    }

    public Incident getParentIncident() {
        return parentIncident;
    }

    public void addActionListeners() {
        view.getPrevBtn().addActionListener(this::PrevBtnPressed);
        view.getSaveBtn().addActionListener(this::SaveBtnPressed);
        view.getNextBtn().addActionListener(this::NextBtnPressed);
        view.getNewUserBtn().addActionListener(this::NewUserBtnPressed);
        view.getDeleteBtn().addActionListener(this::DeleteBtnPressed);
        view.getQuitBtn().addActionListener(e -> System.exit(0));
        view.getBackBtn().addActionListener(this::BackBtnPressed);
        view.getTodosBtn().addActionListener(this::TodosHandler);
    }

    private void TodosHandler(ActionEvent actionEvent){
        this.view.dispose();
        TodoListController todos = new TodoListController();
        todos.setVisibile();
    }

    private void BackBtnPressed(ActionEvent actionEvent) {
        //dispose the detail view
        this.view.dispose();
        //show the list view - access through list controller
        listController.showListView();
    }

    private void DeleteBtnPressed(ActionEvent event) {
        if(this.parentIncident.getNotes().isEmpty()){
            JOptionPane.showMessageDialog(this.view, "No entry to delete");
            return;
        }
        int oldIdx = idx;
        idx = 0;
        this.parentIncident.getNotes().remove(oldIdx);
        if (this.parentIncident.getNotes().isEmpty()) {
            view.setNote("", new Date(), this.view.getUsers().get(0), "");
            return;
        }
        this.view.setNote(this.parentIncident.getNotes().get(0));
        this.listController.updatePersistence();
    }

    private void NextBtnPressed(ActionEvent event) {
        this.idx++;
        if (idx >= this.parentIncident.getNotes().size()) {
            idx = this.parentIncident.getNotes().size();
            view.setNote("", new Date(), this.view.getUsers().get(0), "");
            return;
        }
        Note newNote = this.parentIncident.getNotes().get(idx);
        view.setNote(newNote.getTitle(), newNote.getCreationDate(), newNote.getCreatedBy(), newNote.getContent());
    }

    private void SaveBtnPressed(ActionEvent event) {
        String date = view.getDate();
        if(!validateDate(date)){
            JOptionPane.showMessageDialog(this.view, "Invalid date");
            return;
        }
        Note updated = view.saveNote();
        if (idx >= this.parentIncident.getNotes().size()) {
            this.parentIncident.getNotes().add(updated);
            JOptionPane.showMessageDialog(this.view, "Saved");
            return;
        }
        this.parentIncident.getNotes().set(idx, updated);
        this.listController.updatePersistence();
        JOptionPane.showMessageDialog(this.view, "Saved");
    }

    private void PrevBtnPressed(ActionEvent event) {
        this.idx--;
        if (idx < 0) {
            idx = 0;
            JOptionPane.showMessageDialog(this.view, "Already at first note!");
            return;
        }
        Note newNote = this.parentIncident.getNotes().get(idx);
        view.setNote(newNote.getTitle(), newNote.getCreationDate(), newNote.getCreatedBy(), newNote.getContent());
    }

    private void NewUserBtnPressed(ActionEvent event){
        User created = User.CreateUserDialogue();
        List<User> users = this.view.getUsers();
        users.add(created);
        this.view.setUserSelection(users);
    }

    public void NewNote() {
        idx = this.parentIncident.getNotes().size();
        view.setNote("", new Date(), this.view.getUsers().get(0), "");
    }

    public void deleteNote(int selectedRow) {
        if(this.parentIncident.getNotes().isEmpty()){
            JOptionPane.showMessageDialog(this.view, "No entry to delete");
            return;
        }
        this.parentIncident.getNotes().remove(selectedRow);
        if (this.parentIncident.getNotes().isEmpty()) {
            view.setNote("", new Date(), this.view.getUsers().get(0), "");
        }
        this.view.dispose();
        this.listController.showListView();
    }

    public boolean validateDate(String date){
        int year;
        int month;
        int day;
        try {
            year = Integer.parseInt(date.split("/")[2]);
            month = Integer.parseInt(date.split("/")[0]) - 1;
            day = Integer.parseInt(date.split("/")[1]);
        } catch (NumberFormatException e){
            return false;
        }
        if (month > 11 || day > 31 || month < 0 || day < 1 || year < 1900){
            return false;
        }
        List<Integer> thirty = List.of(3, 5, 8, 10);
        if(thirty.contains(month) && day > 30){
            JOptionPane.showMessageDialog(this.view, "Invalid day");
            return false;
        }
        if(year % 4 == 0 && month == 1 && day > 29){
            JOptionPane.showMessageDialog(this.view, "Invalid day");
            return false;
        }
        if (year % 4 != 0 && month == 1 && day > 28) {
            JOptionPane.showMessageDialog(this.view, "Invalid day");
            return false;
        }
        return true;
    }
}
