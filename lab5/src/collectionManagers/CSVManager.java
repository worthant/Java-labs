package collectionManagers;

import collection.City.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import collection.comparators.CityComparator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * The CSVManager class provides methods for working with files in .csv format.
 * The class implements the Managers interface.
 * @see FileManager
 */
public class CSVManager implements FileManager {
    /**
     * Reads data from a file in .csv format and returns a TreeSet of City objects.
     * @param pathToDataFile the path to the file containing the city data.
     * @return a TreeSet of City objects containing the city data read from the file.
     */
//    rewrite this method
//1) use InputStreamReader
//2) validate lines, so that they are correct
//3) handle all the exceptions csvParser throw
//4) return array list of strings
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
            throw new IllegalArgumentException("Invalid CSV file format: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Writes the provided TreeSet of City objects to a file in .csv format.
     * @param pathToDataFile the path to the file to write the city data to.
     * @param cities the TreeSet of City objects to write to the file.
     */
    @Override
    public void write(String pathToDataFile, TreeSet<City> cities) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathToDataFile));
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                    .withHeader("id", "name", "x", "y", "creationDate",
                            "area", "population", "metersAboveSeaLevel", "climate", "government",
                            "standardOfLiving", "governor"));

            for (City city : cities) {
                csvPrinter.printRecord(city.getId(), city.getName(), city.getCoordinates().getX(),
                        city.getCoordinates().getY(), city.getCreationDate(), city.getArea(),
                        city.getPopulation(), city.getMetersAboveSeaLevel(), city.getClimate(),
                        city.getGovernment(), city.getStandardOfLiving(), city.getGovernor());
            }

            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

