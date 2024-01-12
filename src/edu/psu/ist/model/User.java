package edu.psu.ist.model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String Name;
    private String Email;
    private String Phone;

    public User(String name, String email, String phone) {
        Name = name;
        Email = email;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public ArrayList<Note> getNotes(Board board){
        ArrayList<Note> boardNotes = new ArrayList<>();
        board.getCategories().stream().map(
                category -> category.getIncidents().stream().map(
                        incident -> incident.getNotes().stream().map(
                                note -> {
                                    if(note.getCreatedBy() == this){
                                        boardNotes.add(note);
                                    }
                                    return null;
                                }
                        )
                )
        );
        return boardNotes;
    }

    public ArrayList<Note> getAllNotes(Board board) throws Error {
        throw new Error("Not Admin User");
    }
    @Override
    public String toString() {
        return getName() + ", " + getEmail() + " - " + getPhone();
    }

    public static User CreateUserDialogue() {
        String name = JOptionPane.showInputDialog("What is the user's name?");
        while (name.isEmpty()) {
            name = JOptionPane.showInputDialog("What is the user's name? (Cannot be empty)");
        }
        String email = JOptionPane.showInputDialog("What is the user's email?");
        while (email.isEmpty()) {
            email = JOptionPane.showInputDialog("What is the user's email? (Cannot be empty)");
        }
        String phone = JOptionPane.showInputDialog("What is the user's phone number?");
        while (phone.isEmpty()) {
            phone = JOptionPane.showInputDialog("What is the user's phone number? (Cannot be empty)");
        }
        return new User(name, email, phone);
    }
}


