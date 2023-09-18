package psu.edu.ist.test;

import psu.edu.ist.model.*;

import java.util.ArrayList;
import java.util.Date;

public class TestHarness {
    public static void IncidentTest() {
        System.out.println("Incident Model Class Test.");
        User TestUser = new User("Bob", "bob@example.com", "8148549879", Access.USER);
        Incident TestIncident = new Incident("TestName", "Desc", TestUser, Severity.HIGH);
        IncidentType TestIncidentType = new IncidentType(new ArrayList<>(), Severity.HIGH);
        Incident TestIncident2 = new Incident("TestName2", "Desc2", TestUser, TestIncidentType);

        System.out.println("Expected: Desc2");
        System.out.println(TestIncident2.getDescription());
        System.out.println("Expected: TestName2");
        System.out.println(TestIncident2.getTitle());
        System.out.println("Expected: TestIncidentType");
        System.out.println(TestIncident2.getType());
        System.out.println("Expected: High");
        System.out.println(TestIncident2.getSeverity());
        System.out.println("Expected: User{ Bob }");
        System.out.println(TestIncident2.getCreatedBy());

        IncidentType TestType2 = new IncidentType(new ArrayList<>(), Severity.LOW);
        TestIncident2.setType(TestType2);
        System.out.println("Expected: TestType2");
        System.out.println(TestIncident2.getType());
        System.out.println("Expected: Low");
        System.out.println(TestIncident2.getSeverity());

        TestIncident2.setSeverity(Severity.MEDIUM);
        System.out.println("Expected: Medium");
        System.out.println(TestIncident2.getSeverity());

        TestIncident2.setTitle("A new title");
        System.out.println("Expected: A new title");
        System.out.println(TestIncident2.getTitle());

        TestIncident2.setDescription("A new test desc");
        System.out.println("Expected: A new test desc");
        System.out.println(TestIncident2.getDescription());

        User NewTestUser = new User("Ron", "ron@example.com", "8149549712", Access.ADMIN);
        TestIncident2.setCreatedBy(NewTestUser);
        System.out.println("Expected: User { Ron }");
        System.out.println(TestIncident2.getCreatedBy());

        System.out.println(TestIncident2);
    }

    public static void IncidentTypeTest() {
        System.out.println();
        System.out.println("Incident Type Model Class Test");
        IncidentType TestIncidentType = new IncidentType(new ArrayList<>(), Severity.HIGH);

        TestIncidentType.setSeverity(Severity.MEDIUM);
        System.out.println("Expected: Medium");
        System.out.println(TestIncidentType.getSeverity());

        ArrayList<Note> TestNotes = new ArrayList<>();
        User TestUser = new User("Bob", "bob@example.com", "8148549879", Access.USER);
        TestNotes.add(new Note("Test", TestUser, new Date(), "Test"));
        TestIncidentType.setAssociatedNotes(TestNotes);
        System.out.println("Expected: ArrList W/ Notes");
        System.out.println(TestIncidentType.getAssociatedNotes());

        System.out.println(TestIncidentType);
    }
}
