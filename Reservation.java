package carManagerV10;

import java.time.LocalDateTime;

public class Reservation {
    private static int nextReservationId = 1;
    private int reservationId;
    private String customerName;
    private CarInfo car;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;

    public Reservation(String customerName, CarInfo car, LocalDateTime pickupDateTime, LocalDateTime dropoffDateTime) {
        this.reservationId = nextReservationId++;
        this.customerName = customerName;
        this.car = car; // Store the CarInfo object when creating the reservation
        this.pickupDateTime = pickupDateTime;
        this.dropoffDateTime = dropoffDateTime;
    }

    // Additional constructor that accepts reservationId, customerName, carId, pickupDateTime, and dropoffDateTime
    public Reservation(int reservationId, String customerName, int carId, LocalDateTime pickupDateTime, LocalDateTime dropoffDateTime) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        //this.car = findCarById(carId); // Implement findCarById method
        this.pickupDateTime = pickupDateTime;
        this.dropoffDateTime = dropoffDateTime;
    }

    // Getter methods for accessing reservation attributes
    public int getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public CarInfo getCar() {
        return car;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public LocalDateTime getDropoffDateTime() {
        return dropoffDateTime;
    }


    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                "\nCustomer Name: " + customerName +
                "\nCar ID: " + car.getId() +
                "\nPickup Date and Time: " + pickupDateTime +
                "\nDrop-off Date and Time: " + dropoffDateTime;
    }
 // Implement the findCarById method to retrieve a CarInfo object based on carId
    private CarInfo findCarById(int carId) {
       
    	
    	return findCarById(carId);
   }
   
}