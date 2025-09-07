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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Series series = new Series();

        while (true) {
            System.out.println("LATEST SERIES - 2025");
            System.out.println("************************");
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            String choice = scanner.nextLine();

            if (!choice.equals("1")) {
                System.out.println("Exiting...");
                break;
            }

            while (true) {
                System.out.println("\nPlease select one of the following menu items:");
                System.out.println("(1) Capture a new series.");
                System.out.println("(2) Search for a series.");
                System.out.println("(3) Update series age restriction.");
                System.out.println("(4) Delete a series.");
                System.out.println("(5) Print series report - 2025.");
                System.out.println("(6) Exit Application.");
                System.out.print("Your choice: ");

                String menuChoice = scanner.nextLine();

                switch (menuChoice) {
                    case "1" -> series.CaptureSeries();
                    case "2" -> series.SearchSeries();
                    case "3" -> series.UpdatedSeries();
                    case "4" -> series.DeleteSeries();
                    case "5" -> series.SeriesReport();
                    case "6" -> series.ExitSeriesApplication();
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}
