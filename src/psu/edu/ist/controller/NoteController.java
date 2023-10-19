package psu.edu.ist.controller;

import psu.edu.ist.model.Incident;
import psu.edu.ist.model.Note;
import psu.edu.ist.model.Severity;
import psu.edu.ist.model.User;
import psu.edu.ist.view.NotesList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

public class NoteController {
    private NotesList view;
    private Incident parentIncident;
    private int idx;

    public NoteController() {
        User bob = new User("Bob", "bob@gmail.com", "1234567890");
        User john = new User("John", "bob@gmail.com", "1234567890");
        User rob = new User("Rob", "bob@gmail.com", "1234567890");
        ArrayList<User> users = new ArrayList<>();
        users.add(bob);
        users.add(john);
        users.add(rob);
        Incident incident = new Incident("An Incident", "A test incident", bob, Severity.MEDIUM);
        incident.addNote("First Note", new Date(), bob, "The first note");
        incident.addNote("Update", new Date(), john, "An update to the first note");
        incident.addNote("Final", new Date(), rob, "Fixed issue");
        this.parentIncident = incident;
        this.view = new NotesList(this);
        this.idx = 0;
        this.view.setUserSelection(users);
        this.view.setNote(this.parentIncident.getNotes().get(idx));
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
    }

    private void DeleteBtnPressed(ActionEvent event) {
        if(this.parentIncident.getNotes().size() == 0){
            JOptionPane.showMessageDialog(this.view, "No entry to delete");
            return;
        }
        int oldIdx = idx;
        idx = 0;
        this.parentIncident.getNotes().remove(oldIdx);
        if (this.parentIncident.getNotes().size() == 0) {
            view.setNote("", new Date(), this.view.getUsers().get(0), "");
            return;
        }
        this.view.setNote(this.parentIncident.getNotes().get(0));
    }

    private void NextBtnPressed(ActionEvent event) {
        int save = JOptionPane.showConfirmDialog(this.view, "Would you like to save the current note?");
        if (save == 1) {
            this.idx++;
            if (idx >= this.parentIncident.getNotes().size()) {
                idx = this.parentIncident.getNotes().size();
                view.setNote("", new Date(), this.view.getUsers().get(0), "");
                return;
            }
            Note newNote = this.parentIncident.getNotes().get(idx);
            view.setNote(newNote.getTitle(), newNote.getCreationDate(), newNote.getCreatedBy(), newNote.getContent());
        } else if(save == 2){
            return;
        } else {
            Note updated = view.saveNote();
            this.parentIncident.getNotes().set(idx, updated);
            this.idx++;
            if (idx >= this.parentIncident.getNotes().size()) {
                idx = this.parentIncident.getNotes().size();
                view.setNote("", new Date(), this.view.getUsers().get(0), "");
                return;
            }
            Note selected = this.parentIncident.getNotes().get(idx);
            view.setNote(selected);
        }
    }

    private void SaveBtnPressed(ActionEvent event) {
        Note updated = view.saveNote();
        if (idx >= this.parentIncident.getNotes().size()) {
            this.parentIncident.getNotes().add(updated);
            JOptionPane.showMessageDialog(this.view, "Saved");
            return;
        }
        this.parentIncident.getNotes().set(idx, updated);
        JOptionPane.showMessageDialog(this.view, "Saved");
    }

    private void PrevBtnPressed(ActionEvent event) {
        if (idx == 0) {
            JOptionPane.showMessageDialog(this.view, "You are already at the first entry");
            return;
        }
        if (idx >= this.parentIncident.getNotes().size()) {
            idx = this.parentIncident.getNotes().size();
        }
        idx--;
        int save = JOptionPane.showConfirmDialog(this.view, "Would you like to save the current note?");
        if (save == 1) {
            Note selected = this.parentIncident.getNotes().get(idx);
            view.setNote(selected);
        } else if(save == 2){
            idx++;
        } else {
            Note updated = view.saveNote();
            this.parentIncident.getNotes().set(idx + 1, updated);
            Note selected = this.parentIncident.getNotes().get(idx);
            view.setNote(selected);
        }
    }

    private void NewUserBtnPressed(ActionEvent event){
        String name = JOptionPane.showInputDialog("What is the user's name?");
        String email = JOptionPane.showInputDialog("What is the user's email?");
        String phone = JOptionPane.showInputDialog("What is the user's phone number?");
        User created = new User(name, email, phone);
        ArrayList<User> users = this.view.getUsers();
        users.add(created);
        this.view.setUserSelection(users);
    }
}
