package edu.psu.ist.model;

import java.util.ArrayList;

public class Category {
    private String Name;
    private ArrayList<Incident> Incidents;
    private Integer TimeLimit;
    private String Description;

    public Category(String name, ArrayList<Incident> incidents, Integer timeLimit, String description) {
        Name = name;
        Incidents = incidents;
        TimeLimit = timeLimit;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Incident> getIncidents() {
        return Incidents;
    }

    public void setIncidents(ArrayList<Incident> incidents) {
        Incidents = incidents;
    }

    public Integer getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        TimeLimit = timeLimit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Name='" + Name + '\'' +
                ", Incidents=" + Incidents +
                ", TimeLimit=" + TimeLimit +
                ", Description='" + Description + '\'' +
                '}';
    }
}
