package carManagerV10;

public class DriverInfo {
    private String name;
    private String contact;
    private String licenseNumber;

    public DriverInfo(String name, String contact, String licenseNumber) {
        this.name = name;
        this.contact = contact;
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}