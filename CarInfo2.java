package carManagerArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import carManagerFromZero.CarInfo;

public class CarInfo2 {
    private static final String FILENAME = "C:/Users/migue/Downloads/cardata4.txt";
    private static ArrayList<String> carInfoList = new ArrayList<>();
    private static int idCounter = 0; // Initialize the ID counter

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadCarInfoFromFile();

        while (true) {
            System.out.println("1. Enter Car Information");
            System.out.println("2. Search for Car by Line Number");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    CarInfo carInfo = inputCarInfo(scanner);
                    saveCarInfoToFile(carInfo);
                    break;
                case 2:
                    System.out.print("Enter the line number to search for: ");
                    int searchLine = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    searchCarByLine(searchLine);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    // Method to input car information
    public static CarInfo inputCarInfo(Scanner scanner) {
        System.out.print("Enter an ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("License Plate: ");
        String licensePlate = scanner.nextLine();

        System.out.print("Engine Type: ");
        String engineType = scanner.nextLine();

        System.out.print("Current Autonomy: ");
        int currentAutonomy = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Create and return a CarInfo object
        return new CarInfo(id, brand, model, seats, licensePlate, engineType, currentAutonomy);
    }
    

    // ... Rest of your code

    // Method to load car information from the file into ArrayList
    public static void loadCarInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                carInfoList.add(line);
                if (line.startsWith("ID#")) {
                    // Extract the ID number from the line and update the ID counter
                    int id = Integer.parseInt(line.split("#")[1].split(" ")[0]);
                    if (id > idCounter) {
                        idCounter = id;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to search for car information by line number
    public static void searchCarByLine(int line) {
        if (line >= 1 && line <= carInfoList.size()) {
            System.out.println(carInfoList.get(line - 1));
        } else {
            System.out.println("Line number " + line + " not found.");
        }
    }

    // Method to save car information to the file and ArrayList
    public static void saveCarInfoToFile(CarInfo carInfo) {
        idCounter++; // Increment the ID counter
        try (FileWriter writer = new FileWriter(FILENAME, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            String formattedEntry = "ID#" + idCounter + " - Brand: " + carInfo.getBrand() +
                                    "; Model: " + carInfo.getModel() + "; Seats: " + carInfo.getSeats() +
                                    "; License Plate: " + carInfo.getLicensePlate() + "; EngineType: " +
                                    carInfo.getEngineType() + "; Current Autonomy: " + carInfo.getCurrentAutonomy() + "Km;";

            carInfoList.add(formattedEntry); // Add to the ArrayList
            bufferedWriter.write(formattedEntry);
            bufferedWriter.newLine();

            System.out.println("Car information saved to cardata4.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}