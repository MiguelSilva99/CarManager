package carManagerV10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DriverInfoFileReader {
	private static final String FILENAME2 = "C:/Users/migue/Downloads/driverinfo.txt";
    public static DriverInfo readDriverInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME2))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0];
                    String contact = data[1];
                    String licenseNumber = data[2];
                    
                    return new DriverInfo(name, contact, licenseNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if there's an issue or no data in the file
    }
}