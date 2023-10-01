package psu.edu.ist.test;

import org.junit.jupiter.api.Test;
import psu.edu.ist.model.Access;
import psu.edu.ist.model.Note;
import psu.edu.ist.model.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    User TestUser = new User("Bob", "Test@example.com", "8149764345");
    Date InitialCreation = new Date();
    Note TestNote = new Note("Test", TestUser, InitialCreation, "Test Note Content");
    @Test
    void getTitle() {
        assertEquals("Test", TestNote.getTitle());
    }

    @Test
    void setTitle() {
        TestNote.setTitle("New Test Title");
        assertEquals("New Test Title", TestNote.getTitle());
    }

    @Test
    void getCreatedBy() {
        assertEquals(TestUser, TestNote.getCreatedBy());
    }

    @Test
    void setCreatedBy() {
        User NewTestUser = new User("Ron", "Ron@example.com", "8416489646");
        TestNote.setCreatedBy(NewTestUser);
        assertEquals(NewTestUser, TestNote.getCreatedBy());
    }

    @Test
    void getCreationDate() {
        assertEquals(InitialCreation, TestNote.getCreationDate());
    }

    @Test
    void setCreationDate() {
        Date TestDate = new Date(2023, 2, 16, 20, 22, 34);
        TestNote.setCreationDate(TestDate);
        assertEquals(TestDate, TestNote.getCreationDate());
    }

    @Test
    void getContent() {
        assertEquals("Test Note Content", TestNote.getContent());
    }

    @Test
    void setContent() {
        TestNote.setContent("New Content");
        assertEquals("New Content", TestNote.getContent());
    }

    @Test
    void testToString() {
        assertNotNull(TestNote.toString());
    }
}