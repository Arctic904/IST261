import psu.edu.ist.model.Access;
import psu.edu.ist.model.User;
import psu.edu.ist.test.TestHarness;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        TestHarness.IncidentTest();
        TestHarness.IncidentTypeTest();
        try {
            TestHarness.NoteTest();
        } catch (IOException err) {
            System.out.println(err);
        }
    }


}