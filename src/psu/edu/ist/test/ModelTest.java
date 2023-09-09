package psu.edu.ist.test;

import psu.edu.ist.model.*;

import java.util.ArrayList;

public class ModelTest {
    public static void main() {
        User Admin = new User("Admin", "adminTest@example.com", "18237481823", Access.ADMIN);

        Incident Hack = new Incident("Mainframe Hack", "Loss of access to main system", Admin, Severity.CRITICAL);

        ArrayList<Incident> Incidents = new ArrayList<>();
        Incidents.add(Hack);
        Category InProgress = new Category("In Progress", Incidents, 5, "Active Branch");

        ArrayList<User> MainUsers = new ArrayList<>();
        MainUsers.add(Admin);
        ArrayList<Category> Categories = new ArrayList<>();
        Categories.add(InProgress);
        Board Main = new Board("Main", MainUsers, Categories);

        System.out.println(Main);

    }
}
