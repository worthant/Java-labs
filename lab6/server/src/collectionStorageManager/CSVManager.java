package collectionStorageManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The CSVManager class provides methods for working with files in .csv format.
 * The class implements the Managers interface.
 *
 * @see FileManager
 */
public class CSVManager implements FileManager {

    /**
     * Reads a CSV file at the specified path and returns an ArrayList containing each row of the file as a comma-separated string.
     *
     * @param pathToDataFile The path to the CSV file to read.
     * @return An ArrayList containing each row of the CSV file as a comma-separated string.
     * @throws IllegalArgumentException If there was an error reading the CSV file, or if the file format was invalid.
     */
    @Override
    public ArrayList<String> readFromFile(String pathToDataFile) {
        ArrayList<String> lines = new ArrayList<>();

        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToDataFile));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreSurroundingSpaces())) {
            for (CSVRecord record : csvParser) {
                StringBuilder sb = new StringBuilder();
                for (String field : record) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(field.trim());
                }
                lines.add(sb.toString());
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("CSV format violation: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Writes a collection of records to a CSV file at the specified path, using the given header and records array.
     *
     * @param pathToDataFile The path to the CSV file to write to.
     * @param header         An array of strings representing the header row of the CSV file.
     * @param records        An array of objects representing the records to write to the CSV file. Each record should be an array of objects corresponding to a row in the CSV file.
     * @throws IOException If there was an error writing to the CSV file.
     */
    @Override
    public void write(String pathToDataFile, String[] header, List<String> records) throws IOException {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathToDataFile));
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                    .withHeader("id", "name", "x", "y", "creationDate",
                            "area", "population", "metersAboveSeaLevel", "climate", "government",
                            "standardOfLiving", "governor"));

            for (String record: records) {
                csvPrinter.printRecord((Object[]) record.split(","));
            }

            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

