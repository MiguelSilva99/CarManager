package carManager3;

public class CarInfo {
    private int id;
    private String brand;
    private String model;
    private int seats;
    private String licensePlate;
    private String engineType;
    private int currentAutonomy;

    public CarInfo(int id, String brand, String model, int seats, String licensePlate, String engineType, int currentAutonomy) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        this.licensePlate = licensePlate;
        this.engineType = engineType;
        this.currentAutonomy = currentAutonomy;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getSeats() {
        return seats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getCurrentAutonomy() {
        return currentAutonomy;
    }
}