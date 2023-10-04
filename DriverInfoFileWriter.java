package carManagerV10;

import java.io.FileWriter;
import java.io.IOException;

public class DriverInfoFileWriter {
	private static final String FILENAME2 = "C:/Users/migue/Downloads/driverinfo.txt";
    public static void saveDriverInfoToFile(DriverInfo driverInfo) {
        try (FileWriter writer = new FileWriter(FILENAME2, true)) {
            // Convert DriverInfo data to a CSV format and write it to the file
            String csvData = driverInfo.getName() + "," + driverInfo.getContact() + "," + driverInfo.getLicenseNumber() + "\n";
            writer.write(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}