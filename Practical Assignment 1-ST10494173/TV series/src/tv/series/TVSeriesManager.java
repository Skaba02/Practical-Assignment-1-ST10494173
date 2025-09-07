/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tv.series;

/**
 *
 * @author PABALELO
 */
import java.util.ArrayList;

public class TVSeriesManager {
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();

    // Add new series if ID is unique
    public void addSeries(SeriesModel series) {
        if (findById(series.getSeriesId()) != null) {
            System.out.println("Series ID already exists. Cannot add duplicate.");
            return;
        }
        seriesList.add(series);
        System.out.println("Series added successfully.");
    }

    // Search series by ID or name (case-insensitive)
    public void searchSeries(String keyword) {
        boolean found = false;
        keyword = keyword.toLowerCase();
        for (SeriesModel s : seriesList) {
            if (s.getSeriesId().equalsIgnoreCase(keyword) || s.getSeriesName().toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No series found matching: " + keyword);
        }
    }

    // Update series age restriction by ID
    public void updateSeriesAge(String id, String newAgeStr) {
        SeriesModel s = findById(id);
        if (s == null) {
            System.out.println("Series ID not found.");
            return;
        }
        try {
            int newAge = Integer.parseInt(newAgeStr);
            if (newAge < 2 || newAge > 18) {
                System.out.println("Age must be between 2 and 18.");
                return;
            }
            s.setSeriesAge(newAge);
            System.out.println("Age restriction updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input.");
        }
    }

    // Delete series by ID
    public void deleteSeries(String id) {
        SeriesModel s = findById(id);
        if (s == null) {
            System.out.println("Series ID not found.");
            return;
        }
        seriesList.remove(s);
        System.out.println("Series deleted.");
    }

    // Print all series
    public void printReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series to display.");
            return;
        }
        System.out.println("---- Series Report ----");
        for (SeriesModel s : seriesList) {
            System.out.println(s);
        }
    }

    // Helper to find series by ID
    private SeriesModel findById(String id) {
        for (SeriesModel s : seriesList) {
            if (s.getSeriesId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
