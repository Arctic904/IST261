package edu.psu.ist.model;

import java.util.ArrayList;

public class IncidentType {
    private ArrayList<Note> AssociatedNotes;
    private Severity Severity;

    public IncidentType(ArrayList<Note> associatedNotes, edu.psu.ist.model.Severity severity) {
        AssociatedNotes = associatedNotes;
        Severity = severity;
    }

    public ArrayList<Note> getAssociatedNotes() {
        return AssociatedNotes;
    }

    public void setAssociatedNotes(ArrayList<Note> associatedNotes) {
        AssociatedNotes = associatedNotes;
    }

    public edu.psu.ist.model.Severity getSeverity() {
        return Severity;
    }

    public void setSeverity(edu.psu.ist.model.Severity severity) {
        Severity = severity;
    }

    @Override
    public String toString() {
        return "IncidentType{" +
                "AssociatedNotes=" + AssociatedNotes +
                ", Severity=" + Severity +
                '}';
    }
}
