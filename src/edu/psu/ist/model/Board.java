package edu.psu.ist.model;

import java.util.ArrayList;

public class Board {
    private String Name;
    private ArrayList<User> Users;
    private ArrayList<Category> Categories;

    public Board(String name, ArrayList<User> users, ArrayList<Category> categories) {
        Name = name;
        Users = users;
        Categories = categories;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<User> users) {
        Users = users;
    }

    public ArrayList<Category> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        Categories = categories;
    }

    @Override
    public String toString() {
        return "Board{" +
                "Name='" + Name + '\'' +
                ", Users=" + Users +
                ", Categories=" + Categories +
                '}';
    }
}
