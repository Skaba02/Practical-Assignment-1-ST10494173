/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.app.model;

public class Person {
    // information hiding
    private String id;
    private String name;

    // constructor
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters (read-only)
    public String getId() { return id; }
    public String getName() { return name; }
}
