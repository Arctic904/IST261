package edu.psu.ist.controller;

import edu.psu.ist.model.Incident;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IncidentPersistenceController {
    private List<Incident> incidents = new ArrayList<>();
    private String fileName = "incidentsFile.txt";
    private final String filePath = "src/edu/psu/ist/data/";

    public IncidentPersistenceController() {
        readIncidentFile();
        if(incidents.isEmpty()){
            incidents.add(Incident.CreateIncidentDialogue());
            writeIncidentFile();
        }
    }

    public void setIncidents(List<Incident> incidents){
        this.incidents = incidents;
        writeIncidentFile();
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    private void writeIncidentFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filePath + fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(incidents);
            out.close();
            System.out.println("successful in writing data to file");
        } catch (IOException e) {
            System.out.println("caught exception while writing to file: " + e.getMessage());
        }
    }

    private void readIncidentFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filePath + fileName);
            in = new ObjectInputStream(fis);
            incidents = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("caught exception while reading from file: " + e.getMessage());
        }
        System.out.println("successful in reading from file");
    }
}
