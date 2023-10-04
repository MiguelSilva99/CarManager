package carManagerV10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationsFileReader {
    private static final String RESERVATION_FILENAME = "C:/Users/migue/Downloads/reservations.txt";

    public static ArrayList<Reservation> readReservationsFromFile() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        

        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATION_FILENAME))) {
            String line;
            Reservation reservation = null;
            System.out.println("List of Reservations:");
            System.out.println();
            while ((line = reader.readLine()) != null) {
            	
                System.out.println(" " + line); // Debugging statement
                

                if (line.startsWith("Reservation ID:")) {
                	 
                    if (reservation != null) {
                        reservations.add(reservation);
                    }
                    reservation = parseReservation(line);
                } else if (line.equals("--------------------------------------")) {
                    reservation = null;
                }
            }

            if (reservation != null) {
                reservations.add(reservation);
            }
        } catch (IOException e) {
        	 System.err.println("Error reading the reservations file: " + e.getMessage());
            e.printStackTrace();
        }

        return reservations;
        
    }
    
   


    private static Reservation parseReservation(String reservationString) {
        String[] lines = reservationString.split("\n");
        if (lines.length < 5) {
        	   //System.out.println("null");
            // Check if there are at least 5 lines in the reservationString
            return null; // Return null to indicate a parsing error
        }

        int reservationId = Integer.parseInt(lines[0].substring("Reservation ID: ".length()));
        String customerName = lines[1].substring("Customer Name: ".length());
        int carId = Integer.parseInt(lines[2].substring("Car ID: ".length()));
        LocalDateTime pickupDateTime = LocalDateTime.parse(lines[3].substring("Pickup Date and Time: ".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dropoffDateTime = LocalDateTime.parse(lines[4].substring("Drop-off Date and Time: ".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new Reservation(reservationId, customerName, carId, pickupDateTime, dropoffDateTime);
    }
    
    
    
}