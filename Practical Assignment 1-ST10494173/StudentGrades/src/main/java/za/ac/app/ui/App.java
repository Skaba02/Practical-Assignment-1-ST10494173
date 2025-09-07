/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.app.ui;

import za.ac.app.model.Student;
import za.ac.app.service.StudentManager;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static final String DATA_FILE = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager(100);

        // load saved data
        manager.loadFromFile(DATA_FILE);
        System.out.println("Loaded saved students (if any).");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addStudentFlow(scanner, manager); break;
                case "2":
                    System.out.print("Enter student ID to search: ");
                    Student s = manager.searchById(scanner.nextLine().trim());
                    if (s == null) System.out.println("Student not found.");
                    else printStudent(s);
                    break;
                case "3":
                    System.out.print("Enter student ID to update name: ");
                    String uid = scanner.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.println(manager.updateName(uid, newName) ? "Updated." : "Not found.");
                    break;
                case "4":
                    System.out.print("Enter student ID to delete: ");
                    System.out.println(manager.delete(scanner.nextLine().trim()) ? "Deleted." : "Not found.");
                    break;
                case "5":
                    manager.sortByAverageDesc();
                    System.out.println(manager.buildReport());
                    break;
                case "6":
                    // save before exit
                    manager.saveToFile(DATA_FILE);
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Choose 1-6.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== STUDENT MENU ===");
        System.out.println("1. Add student");
        System.out.println("2. Search student by ID");
        System.out.println("3. Update student name");
        System.out.println("4. Delete student");
        System.out.println("5. Report (sorted by average)");
        System.out.println("6. Exit");
        System.out.print("Choose an option (1-6): ");
    }

    private static void addStudentFlow(Scanner scanner, StudentManager manager) {
        if (manager.isFull()) {
            System.out.println("Manager is full.");
            return;
        }
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        if (manager.searchById(id) != null) {
            System.out.println("ID already exists. Aborting.");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter marks separated by spaces (e.g. 65 70 80): ");
        String[] parts = scanner.nextLine().trim().split("\\s+");
        ArrayList<Integer> markList = new ArrayList<>();
        try {
            for (String p : parts) {
                if (p.isEmpty()) continue;
                int m = Integer.parseInt(p);
                if (m < 0 || m > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    return;
                }
                markList.add(m);
            }
            if (markList.isEmpty()) {
                System.out.println("No marks entered.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }
        int[] marks = markList.stream().mapToInt(Integer::intValue).toArray();
        boolean added = manager.add(new Student(id, name, marks));
        System.out.println(added ? "Student added." : "Could not add.");
    }

    private static void printStudent(Student s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.print("Marks: ");
        int[] ms = s.getMarks();
        for (int i = 0; i < ms.length; i++) {
            System.out.print(ms[i] + (i < ms.length - 1 ? ", " : ""));
        }
        System.out.println();
        System.out.printf("Average: %.1f\n", s.average());
        System.out.println("Result: " + s.result());
    }
}
