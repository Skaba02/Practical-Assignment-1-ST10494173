/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tv.series;

/**
 *
 * @author PABALELO
 */
import java.util.Scanner;

public class TVSeriesApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TVSeriesManager manager = new TVSeriesManager();

        // Start menu
        System.out.println("LATEST SERIES - 2025");
        System.out.println("********************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String startChoice = input.nextLine();

        if (!startChoice.equals("1")) {
            System.out.println("Exiting application...");
            return;
        }

        int option;
        do {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction.");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025.");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter your choice: ");
            option = getIntInput(input);

            switch (option) {
                case 1:
                    captureSeries(input, manager);
                    break;
                case 2:
                    System.out.print("Enter series ID or Name to search: ");
                    String keyword = input.nextLine();
                    manager.searchSeries(keyword);
                    break;
                case 3:
                    System.out.print("Enter series ID to update age: ");
                    String id = input.nextLine();
                    String age = getValidAge(input);
                    manager.updateSeriesAge(id, age);
                    break;
                case 4:
                    System.out.print("Enter series ID to delete: ");
                    String deleteId = input.nextLine();
                    manager.deleteSeries(deleteId);
                    break;
                case 5:
                    manager.printReport();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (option != 6);

        input.close();
    }

    // Capture new series details
    private static void captureSeries(Scanner input, TVSeriesManager manager) {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("********************************");
        System.out.print("Enter the series id: ");
        String id = input.nextLine();
        System.out.print("Enter the series name: ");
        String name = input.nextLine();
        String age = getValidAge(input);
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = input.nextLine();

        SeriesModel series = new SeriesModel(id, name, age, episodes);
        manager.addSeries(series);
    }

    // Get valid age restriction
    private static String getValidAge(Scanner input) {
        String age;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            age = input.nextLine();
            if (!isNumeric(age)) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.println("Please re-enter the series age >>");
                continue;
            }
            int ageNum = Integer.parseInt(age);
            if (ageNum < 2 || ageNum > 18) {
                System.out.println("Age must be between 2 and 18.");
            } else {
                break;
            }
        }
        return age;
    }

    // Check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Get integer input safely
    private static int getIntInput(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            input.nextLine();
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
}
