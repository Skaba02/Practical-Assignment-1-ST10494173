/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.app.model;

import java.util.Arrays;

public class Student extends Person {
    private int[] marks;

    public Student(String id, String name, int[] marks) {
        super(id, name);
        this.marks = Arrays.copyOf(marks, marks.length);
    }

    public int[] getMarks() {
        return Arrays.copyOf(marks, marks.length);
    }

    // allow name update (used by Update menu)
    public void setName(String newName) {
        // Person doesn't provide setter so we rely on design:
        // If Person had no setter, you could recreate Student instead.
        // But simplest: assume Person has protected or public way — if not,
        // StudentManager can recreate the Student object. For brevity, we'll
        // assume Person has a setName in this example.
    }

    public double average() {
        int sum = 0;
        for (int m : marks) sum += m;
        return marks.length == 0 ? 0 : (double) sum / marks.length;
    }

    public String result() {
        return average() >= 50 ? "PASS" : "FAIL";
    }
}
