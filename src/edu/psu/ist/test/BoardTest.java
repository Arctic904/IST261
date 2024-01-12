package edu.psu.ist.test;

import edu.psu.ist.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    User bob = new User("Bob", "test@test.com", "8145264978");
    Board test = new Board("Test", new ArrayList<>(List.of(bob)), new ArrayList<Category>(List.of(new Category("Test", new ArrayList<>(List.of(new Incident("TestIncident", "This is a test", bob, Severity.MEDIUM))), 60, "This is another test"))));

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Test", test.getName());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        test.setName("Test2");
        assertEquals("Test2", test.getName());
    }

    @org.junit.jupiter.api.Test
    void getUsers() {
        assertEquals(new ArrayList<>(Collections.singletonList(bob)), test.getUsers());
    }

    @org.junit.jupiter.api.Test
    void setUsers() {
        ArrayList<User> newUsers = test.getUsers();
        newUsers.add(new User("Ron", "test2@test.com", "8161558465"));
        test.setUsers(newUsers);
        assertEquals(newUsers, test.getUsers());
    }

    @org.junit.jupiter.api.Test
    void getCategories() {
        Incident incident = new Incident("TestIncident", "This is a test", bob, Severity.MEDIUM);
        ArrayList<Incident> list = new ArrayList<>();
        list.add(incident);
        Category cat = new Category("Test", list, 60, "This is another test");
        ArrayList<Category> testCats = new ArrayList<Category>();
        testCats.add(cat);
        //you need to set the categories on the 'test' object
        assertEquals(testCats.toString(), test.getCategories().toString());
    }

    @org.junit.jupiter.api.Test
    void setCategories() {
        ArrayList<Category> cats = new ArrayList<Category>(List.of(new Category("Test", new ArrayList<>(List.of(new Incident("TestIncident", "This is a test", bob, Severity.MEDIUM))), 60, "This is another test")));
        Incident incident = new Incident("KTest2", "Incident", bob, Severity.MAINTENANCE);
        cats.add(new Category("In Progress", new ArrayList<>(List.of(incident)), 40, "Bobs Problems"));
        test.setCategories(cats);
        assertEquals(cats, test.getCategories());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertNotNull(test.toString());
    }
}