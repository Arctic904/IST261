package edu.psu.ist.model;

import java.util.ArrayList;

public class Admin extends User{

    public Admin(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    public ArrayList<Note> getAllNotes(Board board){
        ArrayList<Note> boardNotes = new ArrayList<>();
        board.getCategories().stream().map(
                category -> category.getIncidents().stream().map(
                        incident -> incident.getNotes().stream().map(
                                note -> {
                                    boardNotes.add(note);
                                    return null;
                                }
                        )
                )
        );
        return boardNotes;
    }
}
