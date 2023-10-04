package carManagerV10;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;



public class CarInfo2 {
    private static final String FILENAME = "C:/Users/migue/Downloads/cardata10.txt";
    private static ArrayList<String> carInfoList = new ArrayList<>();
    private static final String DRIVER_FILENAME = "C:/Users/migue/Downloads/driverinfo.txt";
    private static final String RESERVATION_FILENAME = "C:/Users/migue/Downloads/reservations.txt";
    private static int idCounter = 0; // Initialize the ID counter
    private static ArrayList<DriverInfo> driverInfoList = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadCarInfoFromFile();

        while (true) {
            System.out.println("1. Enter Car Information");
            System.out.println("2. Enter Driver Information"); // Add a new option
            System.out.println("3. Search for Car by Line Number");
            System.out.println("4. Display List of Car Information");
            System.out.println("5. Remove Car by Line Number");
            System.out.println("6. Exit");
            System.out.println("7. Create Reservation"); // Add a new option
            System.out.println("8. Display Reservations"); // Add a new option
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    CarInfo carInfo = inputCarInfo(scanner);
                    saveCarInfoToFile(carInfo);
                    break;
                    
                case 2:
                    DriverInfo driverInfo = inputDriverInfo(scanner); // Call the new method
                    DriverInfoFileWriter.saveDriverInfoToFile(driverInfo); // Save driver info
                    break;
                    
                case 3:
                    System.out.print("Enter the ID number to search for: ");
                    int searchLine = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    searchCarByLine(searchLine);
                    break;
                case 4:
                    System.out.println("List of Car Information:");
                    for (String carInfoItem : carInfoList) {
                        System.out.println(carInfoItem);
                    }
                    break;
                case 5:
                    System.out.print("Enter the line number to remove: ");
                    int removeLine = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    removeCarByLine(removeLine);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                case 7:
                    createReservation(scanner);
                    break;
                case 8:
                	 String reservationsString = reservationsToString();
                	 System.out.println(reservationsString);
                    
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
    
    public static void removeCarByLine(int lineToRemove) {
    	if (lineToRemove >= 1 && lineToRemove <= carInfoList.size()) {
            // Remove the car information from the ArrayList
            carInfoList.remove(lineToRemove - 1);
            
            // Renumber the IDs
            renumberCarIDs();
            
            // Update the file with the modified car information
            updateFileWithCarInfo();
            
            System.out.println("Car removed successfully.");
        } else {
            System.out.println("Line number " + lineToRemove + " not found.");
        }
    }
    
    
    public static void renumberCarIDs() {
        for (int i = 0; i < carInfoList.size(); i++) {
            String line = carInfoList.get(i);
            String newLine = "ID#" + (i + 1) + line.substring(line.indexOf(" - "));
            carInfoList.set(i, newLine);
        }
    }
    
    public static void updateFileWithCarInfo() {
        try (FileWriter writer = new FileWriter(FILENAME);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (String carInfoItem : carInfoList) {
                bufferedWriter.write(carInfoItem);
                bufferedWriter.newLine();
            }

            System.out.println("File updated with modified car information.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Method to create a reservation
    public static Reservation createReservation(Scanner scanner) {
        // Input customer information
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        // Input car ID or other information to select a car
        System.out.print("Enter Car ID for Reservation: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        LocalDateTime pickupDateTime;
        LocalDateTime dropoffDateTime;
        // Input pickup and drop-off date and time
        try {
            System.out.print("Enter Pickup Date and Time (yyyy-MM-dd HH:mm): ");
            pickupDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter Drop-off Date and Time (yyyy-MM-dd HH:mm): ");
            dropoffDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time format.");
            return null; // Return null to indicate that the reservation was not created
        }
        CarInfo selectedCar = null;
		Reservation reservation = new Reservation(customerName, selectedCar, pickupDateTime, dropoffDateTime);
        
        reservations.add(reservation);
        
        ReservationFileWriter.saveReservationsToFile(reservations);

        System.out.println("Reservation created successfully.");
        
        return reservation;

        // Find the car by ID
       // CarInfo selectedCar = findCarById(carId);

       /* if (selectedCar != null) {
            // Create a reservation
            Reservation reservation = new Reservation(customerName, selectedCar, pickupDateTime, dropoffDateTime);
            
            // Add the reservation to the list
            reservations.add(reservation);
            
            // Save the reservations to the file
            ReservationFileWriter.saveReservationsToFile(reservations);
            
            System.out.println("Reservation created successfully.");
            
            // Return the created reservation
            return reservation;
        } else {
            System.out.println("Car not found.");
            return null; // Return null to indicate that the reservation was not created
        }
        */
    }
    
 // Method to input driver information
    public static DriverInfo inputDriverInfo(Scanner scanner) {
        System.out.print("Enter Driver's Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Driver's: ");
        String licensePlate = scanner.nextLine();

        System.out.print("Enter Driver's Contact Information: ");
        String contact = scanner.nextLine();

        return new DriverInfo(name, contact, licensePlate);
    }
    
    public static void saveDriverInfoToFile(DriverInfo driverInfo) {
        try (FileWriter writer = new FileWriter(DRIVER_FILENAME, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            String formattedEntry = driverInfo.getName() + "," +
                                    driverInfo.getContact() + "," +
                                    driverInfo.getLicenseNumber() + "\n";
            
            driverInfoList.add(driverInfo); // Add to the ArrayList
            bufferedWriter.write(formattedEntry);
            
            System.out.println("Driver information saved to driverinfo.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ArrayList<Reservation> loadReservations() {
        return ReservationsFileReader.readReservationsFromFile();
    }
    // Helper method to parse a single reservation from a string
  /*  private static Reservation parseReservation(String reservationString) {
        String[] lines = reservationString.split("\n");
        
        if (lines.length < 5) {
            // Check if there are at least 5 lines for a complete reservation
            return null; // Return null to indicate a parsing error
        }

        int reservationId = Integer.parseInt(lines[0].substring("Reservation ID: ".length()));
        String customerName = lines[1].substring("Customer Name: ".length());
        
        // Add error handling for parsing carId, pickupDateTime, and dropoffDateTime
        int carId = tryParseInt(lines[2].substring("Car ID: ".length()));
        LocalDateTime pickupDateTime = tryParseDateTime(lines[3].substring("Pickup Date and Time: ".length()));
        LocalDateTime dropoffDateTime = tryParseDateTime(lines[4].substring("Drop-off Date and Time: ".length()));

        if (carId == -1 || pickupDateTime == null || dropoffDateTime == null) {
            return null; // Return null if any parsing errors occur
        }

        return new Reservation(reservationId, customerName, carId, pickupDateTime, dropoffDateTime);
    }

    // Helper method to attempt parsing an integer, returning -1 on failure
    private static int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
        }
    */
    

    // Helper method to attempt parsing a LocalDateTime, returning null on failure
   /* private static LocalDateTime tryParseDateTime(String str) {
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
  */
    public static void displayReservations() {
        System.out.println("List of Reservations:");

        ArrayList<Reservation> reservations = ReservationsFileReader.readReservationsFromFile();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Customer Name: " + reservation.getCustomerName());
                System.out.println("Car ID: " + reservation.getCar().getId());
                System.out.println("Pickup Date and Time: " + reservation.getPickupDateTime());
                System.out.println("Drop-off Date and Time: " + reservation.getDropoffDateTime());
                System.out.println("--------------------------------------");
            }
        }
    }
    
    public static String reservationsToString() {
        StringBuilder result = new StringBuilder();
        //result.append("List of Reservations:\n");

        ArrayList<Reservation> reservations = ReservationsFileReader.readReservationsFromFile();

        if (reservations.isEmpty()) {
          //  result.append("No reservations found.\n");
        } else {
            for (Reservation reservation : reservations) {
                result.append("Reservation ID: ").append(reservation.getReservationId()).append("\n");
                result.append("Customer Name: ").append(reservation.getCustomerName()).append("\n");
                result.append("Car ID: ").append(reservation.getCar().getId()).append("\n");
                result.append("Pickup Date and Time: ").append(reservation.getPickupDateTime()).append("\n");
                result.append("Drop-off Date and Time: ").append(reservation.getDropoffDateTime()).append("\n");
                result.append("--------------------------------------\n");
            }
        }

        return result.toString();
    }
   /* public static CarInfo findCarById(int carId) {
        for (String carInfoString : carInfoList) {
            // Extract car ID from the carInfoString
            int idStartIndex = carInfoString.indexOf("ID#");
            if (idStartIndex != -1) {
                int idEndIndex = carInfoString.indexOf(" ", idStartIndex + 3); // Find the first space after "ID#"
                if (idEndIndex == -1) {
                    idEndIndex = carInfoString.length(); // If no space is found, consider the end of the string
                }

                String carInfoIdStr = carInfoString.substring(idStartIndex + 3, idEndIndex);
                int carInfoId = Integer.parseInt(carInfoIdStr);

                if (carInfoId == carId) {
                    // Extract car information from the line and create a CarInfo object
                    String[] parts = carInfoString.split(" - ");
                    String brand = null;
                    String model = null;
                    int seats = -1; // Initialize to a default value
                    String licensePlate = null;
                    String engineType = null;
                    int currentAutonomy = -1; // Initialize to a default value

                    for (String part : parts) {
                        if (part.startsWith("Brand: ")) {
                            brand = part.substring("Brand: ".length());
                        } else if (part.startsWith("Model: ")) {
                            model = part.substring("Model: ".length());
                        } else if (part.startsWith("Seats: ")) {
                            seats = Integer.parseInt(part.substring("Seats: ".length()));
                        } else if (part.startsWith("License Plate: ")) {
                            licensePlate = part.substring("License Plate: ".length());
                        } else if (part.startsWith("Engine Type: ")) {
                            engineType = part.substring("Engine Type: ".length());
                        } else if (part.startsWith("Current Autonomy: ")) {
                            currentAutonomy = Integer.parseInt(part.substring("Current Autonomy: ".length()).split(" ")[0]);
                        }
                    }

                    // Check if all required information was found
                    if (brand != null || model != null || seats != -1 || licensePlate != null || engineType != null || currentAutonomy != -1) {
                        return new CarInfo(carId, brand, model, seats, licensePlate, engineType, currentAutonomy);
                    }
                }
            }
        }

        System.out.println("Car not found");
        // Return null if the car is not found
        return null;
    }
   */
    
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

            System.out.println("Car information saved to cardata10.txt");
            renumberCarIDs();
            updateFileWithCarInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}