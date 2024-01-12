package edu.psu.ist.test;

import edu.psu.ist.model.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TestHarness {
    public static void IncidentTest() {
        System.out.println("Incident Model Class Test.");
        User TestUser = new User("Bob", "bob@example.com", "8148549879");
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

        User NewTestUser = new User("Ron", "ron@example.com", "8149549712");
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
        User TestUser = new User("Bob", "bob@example.com", "8148549879");
        TestNotes.add(new Note("Test", TestUser, new Date(), "Test"));
        TestIncidentType.setAssociatedNotes(TestNotes);
        System.out.println("Expected: ArrList W/ Notes");
        System.out.println(TestIncidentType.getAssociatedNotes());

        System.out.println(TestIncidentType);
    }

    public static void NoteTest() throws IOException {
        User Bob = new User("Bob", "example@example.com", "8145365461");
        System.out.println();
        ArrayList<Note> noteList = new ArrayList<>();
        Note testNote = new Note("Test 1", Bob, new Date(), "Test Note Text Only");
        FileNote testFileNote = new FileNote("Test 2", Bob, new Date(), "Test Note File");
        ImageNote testImageNote = new ImageNote("Test 3", Bob, new Date(), "Test Note Image");
        testFileNote.setFile(new File("src/myfile.txt"));
        testImageNote.setImage(ImageIO.read(new File("src/img.png")));

        noteList.add(testNote);
        noteList.add(testFileNote);
        noteList.add(testImageNote);

        noteList.forEach(note -> System.out.println(note.getContent()));
    }
}
