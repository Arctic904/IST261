package psu.edu.ist.controller;

import psu.edu.ist.model.User;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceController {
    private List<User> users = new ArrayList<>();
    private final String fileName = "UsersFile.txt";
    private final String filePath = "src/psu/edu/ist/data/";

    public UserPersistenceController() {
        readUserFile();
        if(users.isEmpty()){
            users.add(User.CreateUserDialogue());
            writeUserFile();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        writeUserFile();
    }

    private void writeUserFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filePath + fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(users);
            out.close();
            System.out.println("successful in writing data to file");
        } catch (IOException e) {
            System.out.println("caught exception while writing to file: " + e.getMessage());
        }
    }

    private void readUserFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filePath + fileName);
            in = new ObjectInputStream(fis);
            users = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("caught exception while reading from file: " + e.getMessage());
        }
        System.out.println("successful in reading from file");
    }
}
