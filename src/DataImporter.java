import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {
    public static List<Run> importFromCSV(String filePath) throws Exception {
        List<Run> runs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length < 4) { // Check for at least the required fields
                    throw new IllegalArgumentException("Invalid format in line: " + line);
                }
                // Handle the date by removing the time part
                String date = data[1].trim();
                if (date.contains(" ")) {
                    date = date.split(" ")[0]; // Take only the date (e.g., 2025-04-01)
                }
                double distance = Double.parseDouble(data[2].trim());
                String timeStr = data[3].trim(); // Time in HH:MM:SS format
                double time = convertTimeToMinutes(timeStr); // Convert to minutes
                runs.add(new Run(distance, time, date));
            }
        } catch (Exception e) {
            throw new Exception("Error importing data: " + e.getMessage());
        }
        return runs;
    }

    private static double convertTimeToMinutes(String timeStr) {
        try {
            String[] parts = timeStr.split(":");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid time format: " + timeStr);
            }
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            return hours * 60 + minutes + seconds / 60.0; // Convert to minutes with decimals
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting time: " + timeStr, e);
        }
    }
}