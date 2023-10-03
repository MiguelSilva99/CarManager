package carManager3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CarInfo2 {
	private static final String FILENAME = "C:/Users/migue/Downloads/cardata2.txt"; // Define your file name
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Call the method to input car information
        CarInfo carInfo = inputCarInfo(scanner);

        // Display the entered car information with an ID
        System.out.println("Entered Car Information:");
        System.out.println("ID#" + carInfo.getId() + " - Brand: " + carInfo.getBrand());
        System.out.println("ID#" + carInfo.getId() + " - Model: " + carInfo.getModel());
        System.out.println("ID#" + carInfo.getId() + " - Seats: " + carInfo.getSeats());
        System.out.println("ID#" + carInfo.getId() + " - License Plate: " + carInfo.getLicensePlate());
        System.out.println("ID#" + carInfo.getId() + " - Engine Type: " + carInfo.getEngineType());
        System.out.println("ID#" + carInfo.getId() + " - Current Autonomy: " + carInfo.getCurrentAutonomy());
        
        saveCarInfoToFile(carInfo);
        // Close the scanner
        scanner.close();
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


//Method to save car information to a file
 // Method to save car information to a file
    public static void saveCarInfoToFile(CarInfo carInfo) {
        try (FileWriter writer = new FileWriter(FILENAME, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            // Create a formatted entry with the ID
            String formattedEntry = "ID#" + String.format("%02d", carInfo.getId()) + " - Brand: " + carInfo.getBrand() +
                                    "; Model: " + carInfo.getModel() + "; Seats: " + carInfo.getSeats() +
                                    "; License Plate: " + carInfo.getLicensePlate() + "; EngineType: " +
                                    carInfo.getEngineType() + "; Current Autonomy: " + carInfo.getCurrentAutonomy() + "Km;";

            // Write the formatted entry to the file
            bufferedWriter.write(formattedEntry);
            bufferedWriter.newLine();

            System.out.println("Car information saved to cardata2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
