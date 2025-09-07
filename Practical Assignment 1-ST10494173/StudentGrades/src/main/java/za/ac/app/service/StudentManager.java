/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.app.service;

import za.ac.app.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final Student[] students;
    private int count = 0;

    public StudentManager(int capacity) {
        students = new Student[capacity];
    }

    public boolean add(Student s) {
        if (count >= students.length) return false;
        // prevent duplicate IDs
        if (searchById(s.getId()) != null) return false;
        students[count++] = s;
        return true;
    }

    public Student searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) return students[i];
        }
        return null;
    }

    public boolean updateName(String id, String newName) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                Student old = students[i];
                Student updated = new Student(old.getId(), newName, old.getMarks());
                students[i] = updated;
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) students[j] = students[j + 1];
                students[--count] = null;
                return true;
            }
        }
        return false;
    }

    // bubble sort (by average descending)
    public void sortByAverageDesc() {
        if (count <= 1) return;
        for (int pass = 0; pass < count - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < count - 1 - pass; i++) {
                if (students[i].average() < students[i + 1].average()) {
                    Student tmp = students[i];
                    students[i] = students[i + 1];
                    students[i + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public String buildReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== STUDENT REPORT ===\n");
        sb.append(String.format("%-6s %-15s %-8s %-5s\n", "ID", "Name", "Average", "Res"));
        sb.append("-----------------------------------------\n");
        for (int i = 0; i < count; i++) {
            Student s = students[i];
            sb.append(String.format("%-6s %-15s %-8.1f %-5s\n",
                    s.getId(), s.getName(), s.average(), s.result()));
        }
        sb.append("-----------------------------------------\n");
        int passes = 0;
        for (int i = 0; i < count; i++) if (students[i].average() >= 50) passes++;
        sb.append("Total: ").append(count)
          .append(" | Pass: ").append(passes)
          .append(" | Fail: ").append(count - passes).append("\n");
        return sb.toString();
    }

    public boolean isFull() {
        return count >= students.length;
    }

    // ===== File save/load =====

    public void saveToFile(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < count; i++) {
                Student s = students[i];
                out.print(s.getId() + ";" + s.getName() + ";");
                int[] marks = s.getMarks();
                for (int j = 0; j < marks.length; j++) {
                    out.print(marks[j]);
                    if (j < marks.length - 1) out.print(",");
                }
                out.println();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 3) continue;
                String id = parts[0];
                String name = parts[1];
                String[] markStrings = parts[2].split(",");
                List<Integer> markList = new ArrayList<>();
                for (String ms : markStrings) {
                    markList.add(Integer.parseInt(ms));
                }
                int[] marks = markList.stream().mapToInt(Integer::intValue).toArray();
                add(new Student(id, name, marks));
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    
}
