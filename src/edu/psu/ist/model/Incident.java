package edu.psu.ist.model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Incident implements Serializable {
    private String Title;
    private String Description;
    private User CreatedBy;
    private Severity Severity;
    private IncidentType Type;
    private ArrayList<Note> Notes;

    public Incident(String title, String description, User createdBy, Severity severity) {
        Title = title;
        Description = description;
        CreatedBy = createdBy;
        Severity = severity;
        Notes = new ArrayList<>();
    }
    public Incident(String title, String description, User createdBy, IncidentType type) {
        Title = title;
        Description = description;
        CreatedBy = createdBy;
        Type = type;
        Severity = type.getSeverity();
        Notes = new ArrayList<>();
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public User getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(User createdBy) {
        CreatedBy = createdBy;
    }

    public Severity getSeverity() {
        return Severity;
    }

    public void setSeverity(Severity severity) {
        Severity = severity;
    }

    public IncidentType getType() {
        return Type;
    }

    public void setType(IncidentType type) {
        Type = type;
        Severity = type.getSeverity();
    }

    public ArrayList<Note> getNotes() {
        return Notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        Notes = notes;
    }

    public void addNote(Note note){
        this.Notes.add(note);
    }

    public void addNote(String title, Date created, User user, String content){
        this.Notes.add(new Note(title, user, created, content));
    }

    @Override
    public String toString() {
        return "Incident{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", CreatedBy=" + CreatedBy +
                ", Severity=" + Severity +
                ", Type=" + Type +
                ", Notes=" + Notes +
                '}';
    }

    public static Incident CreateIncidentDialogue() {
        String title = JOptionPane.showInputDialog("What is the title of the incident?");
        while (title.isEmpty()) {
            title = JOptionPane.showInputDialog("What is the title of the incident? (Cannot be empty)");
        }
        String description = JOptionPane.showInputDialog("What is the description for the incident?");
        while (description.isEmpty()) {
            description = JOptionPane.showInputDialog("What is the description for the incident? (Cannot be empty)");
        }
        return new Incident(title, description, null, edu.psu.ist.model.Severity.MEDIUM);
    }
}

