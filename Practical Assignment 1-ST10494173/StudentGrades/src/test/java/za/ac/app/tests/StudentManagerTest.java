/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.app.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.app.model.Student;
import za.ac.app.service.StudentManager;

public class StudentManagerTest {

    @Test
    void average_isCalculatedCorrectly() {
        Student s = new Student("S100", "Test", new int[]{50, 60, 70});
        assertEquals(60.0, s.average(), 0.0001);
        assertEquals("PASS", s.result());
    }

    @Test
    void canAddAndDeleteStudent() {
        StudentManager m = new StudentManager(2);
        Student s1 = new Student("S1", "A", new int[]{40, 40, 40});
        assertTrue(m.add(s1));
        assertNotNull(m.searchById("S1"));
        assertTrue(m.delete("S1"));
        assertNull(m.searchById("S1"));
    }
}
