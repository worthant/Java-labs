package collectionManagers;

import collection.City.*;

import java.io.*;
import java.time.LocalDate;
import java.util.TreeSet;

import collection.comparators.CityComparator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class CSVManager implements Managers{
    @Override
    public TreeSet<City> readFromFile(String pathToDataFile) {
        TreeSet<City> cities = new TreeSet<>(new CityComparator());

        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToDataFile));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());) {

            for (CSVRecord fields : csvParser) {
                long id = Integer.parseInt(fields.get("id"));
                String name = fields.get("name");
                Integer x = Integer.parseInt(fields.get("x"));
                double y = Double.parseDouble(fields.get("y"));
                Coordinates coordinates = new Coordinates(x, y);
                java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
                Integer area = Integer.parseInt(fields.get("area"));
                int population = Integer.parseInt(fields.get("population"));
                Double metersAboveSeaLevel = null;
                if (fields.get("metersAboveSeaLevel") != null && !fields.get("metersAboveSeaLevel").isEmpty()) {
                    metersAboveSeaLevel = Double.parseDouble(fields.get("metersAboveSeaLevel"));
                }
                Climate climate = Climate.valueOf(fields.get("climate"));
                Government government = Government.valueOf(fields.get("government"));
                StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(fields.get("standardOfLiving"));

                /** TODO: create validators */
//                    if (!row[11].isEmpty()) {
//                        Human governor = new Human(row[11].trim());
//                    }
                Human governor = new Human(fields.get("governor"));
                cities.add(new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println("Error reading city data file: " + e.getMessage());
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }

        return cities;
    }

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

