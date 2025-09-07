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
import java.util.Scanner;
import java.io.*;

public class Series {
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private final String FILE_NAME = "series_data.txt";

    // Constructor - load data from file when program starts
    public Series() {
        loadFromFile();
    }

    // Helper: Get valid positive integer
    private int getValidPositiveInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(input.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Helper: Get unique Series ID
    private String getUniqueSeriesId() {
        String id;
        while (true) {
            System.out.print("Enter Series ID: ");
            id = input.nextLine();
            boolean exists = false;

            for (SeriesModel s : seriesList) {
                if (s.getSeriesId().equalsIgnoreCase(id)) {
                    exists = true;
                    System.out.println("Error: Series ID already exists. Please enter a different ID.");
                    break;
                }
            }

            if (!exists) {
                return id;
            }
        }
    }

    // Save data to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (SeriesModel s : seriesList) {
                writer.write(s.getSeriesId() + "," +
                             s.getSeriesName() + "," +
                             s.getSeriesAge() + "," +
                             s.getSeriesNumberOfEpisodes());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No file yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    int episodes = Integer.parseInt(parts[3]);
                    seriesList.add(new SeriesModel(id, name, age));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // 1. CaptureSeries
    public void CaptureSeries() {
        String id = getUniqueSeriesId();

        System.out.print("Enter Series Name: ");
        String name = input.nextLine();

        int age = getValidPositiveInt("Enter Age Restriction: ");
        int episodes = getValidPositiveInt("Enter Number of Episodes: ");

        SeriesModel newSeries = new SeriesModel(id, name, age);
        seriesList.add(newSeries);
        saveToFile(); // Save after adding
        System.out.println("Series added successfully.\n");
    }

    // 2. SearchSeries
    public void SearchSeries() {
        System.out.print("Enter Series ID to search: ");
        String id = input.nextLine();
        boolean found = false;

        for (SeriesModel s : seriesList) {
            if (s.getSeriesId().equalsIgnoreCase(id)) {
                System.out.println("Series Found: " + s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Series not found.\n");
        }
    }

    // 3. UpdatedSeries
    public void UpdatedSeries() {
        System.out.print("Enter Series ID to update: ");
        String id = input.nextLine();
        boolean found = false;

        for (SeriesModel s : seriesList) {
            if (s.getSeriesId().equalsIgnoreCase(id)) {
                System.out.print("Enter New Series Name: ");
                s.setSeriesName(input.nextLine());

                s.setSeriesAge(getValidPositiveInt("Enter New Age Restriction: "));
                s.setSeriesNumberOfEpisodes(getValidPositiveInt("Enter New Number of Episodes: "));

                saveToFile(); // Save after update
                System.out.println("Series updated successfully.\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Series not found.\n");
        }
    }

    // 4. DeleteSeries
    public void DeleteSeries() {
        System.out.print("Enter Series ID to delete: ");
        String id = input.nextLine();
        boolean removed = seriesList.removeIf(s -> s.getSeriesId().equalsIgnoreCase(id));

        if (removed) {
            saveToFile(); // Save after delete
            System.out.println("Series deleted successfully.\n");
        } else {
            System.out.println("Series not found.\n");
        }
    }

    // 5. SeriesReport
    public void SeriesReport() {
        System.out.println("---- Series Report ----");
        if (seriesList.isEmpty()) {
            System.out.println("No series found.\n");
        } else {
            for (SeriesModel s : seriesList) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    // 6. ExitSeriesApplication
    public void ExitSeriesApplication() {
        System.out.println("Saving data and exiting... Goodbye!");
        saveToFile();
        System.exit(0);
    }
}
