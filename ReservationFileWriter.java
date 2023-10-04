package carManagerV10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationFileWriter {
    private static final String RESERVATION_FILENAME = "C:/Users/migue/Downloads/reservations.txt";
 
    public static void saveReservationsToFile(ArrayList<Reservation> reservations) {
        FileWriter writer = null;
       
        
      
        // Use the reservationId as needed
        try {
            writer = new FileWriter(RESERVATION_FILENAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Reservation reservation : reservations) {
                String formattedEntry ="Reservation ID: " + reservation.getReservationId() + "\n" +
                					   "Customer Name: " + reservation.getCustomerName() + "\n" +
                                       "Car ID: " +  "\n" +
                                       "Pickup Date and Time: " + reservation.getPickupDateTime() + "\n" +
                                       "Drop-off Date and Time: " + reservation.getDropoffDateTime() + "\n" +
                                       "--------------------------------------\n";

                bufferedWriter.write(formattedEntry);
            }
            
            bufferedWriter.flush(); // Flush the data to the file
            System.out.println("Reservations saved to reservations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // Close the writer in a finally block
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}