package models.handlers;

import collectionStorageManager.CSVManager;
import models.*;
import models.comparators.CityComparator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Current implementation of CollectionsHandler for HashSet of Route.
 *
 * @author worthant
 * @since 1.0
 */
public class CityHandler implements CollectionHandler<TreeSet<City>, City> {

    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.manager");

    private CommandStatusResponse response;

    private static CityHandler singletonPattern;
    private final Date initDate;

    /**
     * TreeSet collection of City objects.
     */
    private TreeSet<City> cities;

    /**
     * Path to the data file for the collection.
     */
    private static String pathToDataFile;

    private CityHandler() {
        cities = new TreeSet<>(new CityComparator());
        initDate = java.sql.Date.valueOf(LocalDate.now());
    }

    /**
     * Gets singleton instance of CityManager.
     *
     * @return the singleton instance of CityHandler
     */
    public static CityHandler getInstance() {
        if (singletonPattern == null)
            singletonPattern = new CityHandler();
        return singletonPattern;
    }

    /**
     * Loads the TreeSet collection from a .csv file using the environment variable with the specified key.
     *
     * @param envKey the key of the environment variable containing the path to the .csv file
     */
    public void loadCollection(String envKey) {
        String pathToDataFile = System.getenv(envKey);
        CityHandler.setPathToDataFile(pathToDataFile);
        if (pathToDataFile == null) {
            System.out.println("Переменной окружения lab5 не существует!");
        } else if (pathToDataFile.trim().split(" ").length != 1) {
            System.out.println("Некорректно задана переменная окружения lab5! " +
                    "\nЗадайте переменную окружения с именем \"lab5\", поместив туда полный путь к .csv файлу.");
        } else {
            try {
                long count = 1;
                CSVManager csvManager = new CSVManager();
                CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
                ArrayList<String> parsedCSVFile = csvManager.readFromFile(pathToDataFile);
                CSVParser csvParser = CSVParser.parse(String.join("\n", parsedCSVFile), CSVFormat.DEFAULT.withHeader("id", "name", "x", "y", "creationDate", "area", "population", "metersAboveSeaLevel", "climate", "government", "standardOfLiving", "governor"));
                for (CSVRecord fields : csvParser.getRecords()) {
                    long id = Long.parseLong(fields.get("id"));
                    String name = fields.get("name");
                    Integer x = Integer.valueOf(fields.get("x"));
                    double y = Double.parseDouble(fields.get("y"));
                    Coordinates coordinates = new Coordinates(x, y);
                    Date creationDate = java.sql.Date.valueOf(LocalDate.now());
                    Integer area = Integer.valueOf(fields.get("area"));
                    int population = Integer.parseInt(fields.get("population"));
                    Double metersAboveSeaLevel = null;
                    if (fields.get("metersAboveSeaLevel") != null && !fields.get("metersAboveSeaLevel").isEmpty()) {
                        metersAboveSeaLevel = Double.valueOf(fields.get("metersAboveSeaLevel"));
                    }
                    Climate climate = Climate.valueOf(fields.get("climate"));
                    Government government = Government.valueOf(fields.get("government"));
                    StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(fields.get("standardOfLiving"));
                    Human governor = new Human(fields.get("governor"));

                    City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
                    collectionHandler.addElementToCollection(city);
                    response = CommandStatusResponse.ofString(count++ + "th Element loaded!\n  fields: " + fields + "\n size: " + cities.size());
                    logger.info(response.getResponse());
                }
                response = CommandStatusResponse.ofString("Parsed CSV: " + cities.size());
                logger.info(response.getResponse());
                CityHandler.getInstance().setCollection(collectionHandler.getCollection());

            } catch (IOException | IllegalArgumentException e) {
                throw new IllegalArgumentException("CSV format violation: " + e.getMessage());
            }
        }
    }

    public CommandStatusResponse getResponse() {
        return response;
    }

    /**
     * Writes TreeSet collection to .csv file
     */
    public void writeCollection() {
        try {
            // header of City collection
            String[] header = {"id", "name", "x", "y", "creationDate",
                    "area", "population", "metersAboveSeaLevel", "climate", "government",
                    "standardOfLiving", "governor"};

            List<String> records = new ArrayList<>();
            for (City city : cities) {
                records.add(city.getId() + "," + city.getName() + "," + city.getCoordinates().getX() + ","
                        + city.getCoordinates().getY() + "," + city.getCreationDate() + "," + city.getArea() + ","
                        + city.getPopulation() + "," + city.getMetersAboveSeaLevel() + "," + city.getClimate() + ","
                        + city.getGovernment() + "," + city.getStandardOfLiving() + "," + city.getGovernor());
            }

            CSVManager csvManager = new CSVManager();
            csvManager.write(pathToDataFile, header, records);
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("CSV format violation: " + e.getMessage());
        }
    }

    /**
     * Returns actual collection reference.
     *
     * @return Current collection
     */
    @Override
    public TreeSet<City> getCollection() {
        return cities;
    }

    /**
     * Overrides current collection by provided value.
     *
     * @param cities Collection
     */
    @Override
    public void setCollection(TreeSet<City> cities) {
        this.cities = cities;
    }

    /**
     * Adds element to collection.
     *
     * @param city Element to add
     */
    @Override
    public void addElementToCollection(City city) {
        if (cities != null){
            response = CommandStatusResponse.ofString("is element added? - " + cities.add(city));
            logger.info(response.getResponse());
        } else {
            TreeSet<City> cities = new TreeSet<>(new CityComparator());
            cities.add(city);
            CityHandler.getInstance().setCollection(cities);
        }
    }

    /**
     * Removes all elements from the cityTreeSet collection.
     */
    @Override
    public void clearCollection() {
        cities.clear();
    }


    /**
     * Returns first element of collection.
     *
     * @return First element of collection. If collection is empty, returns new object.
     */
    @Override
    public City getFirstOrNew() {
        if (cities.iterator().hasNext())
            return cities.iterator().next();
        else
            return new City();
    }

    @Override
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Sets the path to the .csv file.
     *
     * @param pathToDataFile the path to the .csv file to set
     */
    public static void setPathToDataFile(String pathToDataFile) {
        CityHandler.pathToDataFile = pathToDataFile;
    }


}
