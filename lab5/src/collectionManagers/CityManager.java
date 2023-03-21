package collectionManagers;

import collection.City.*;
import collection.comparators.CityComparator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * CollectionManager is a class for working with a TreeSet collection of City objects.
 * It provides methods for loading and writing the collection to a .csv file,
 * as well as creating a new City object based on user input.
 */
public class CityManager implements CollectionManager<TreeSet<City>, City> {
    private static CityManager singletonPattern;
    private TreeSet<City> cityTreeSet;
    private static String pathToDataFile;

    public static CityManager getInstance() {
        if (singletonPattern == null)
            singletonPattern = new CityManager();
        return singletonPattern;
    }

    /**
     * Loads the TreeSet collection from a .csv file using the environment variable with the specified key.
     *
     * @param envKey the key of the environment variable containing the path to the .csv file
     */
    public void loadCollection(String envKey) {
        String pathToDataFile = System.getenv(envKey);
        CityManager.setPathToDataFile(pathToDataFile);
        if (pathToDataFile == null) {
            System.out.println("Переменной окружения lab5 не существует!");
        } else if (pathToDataFile.trim().split(" ").length != 1) {
            System.out.println("Некорректно задана переменная окружения lab5! " +
                    "\nЗадайте переменную окружения с именем \"lab5\", поместив туда полный путь к .csv файлу.");
        } else {
            try {
                CSVManager csvManager = new CSVManager();
                ArrayList<String> parsedCSVFile = csvManager.readFromFile(pathToDataFile);
                TreeSet<City> cities = new TreeSet<>(new CityComparator());
                CSVParser csvParser = CSVParser.parse(String.join("\n", parsedCSVFile), CSVFormat.DEFAULT.withHeader("id", "name", "x", "y", "creationDate", "area", "population", "metersAboveSeaLevel", "climate", "government", "standardOfLiving", "governor"));
                for (CSVRecord fields : csvParser) {
                    long id = IdManager.generateId();
                    String name = fields.get("name");
                    Integer x = Integer.valueOf(fields.get("x"));
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
                    Human governor = new Human(fields.get("governor"));
                    cities.add(new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor));
                }
                CityManager.getInstance().setCollection(cities);

            } catch (IOException | IllegalArgumentException e) {
                throw new IllegalArgumentException("CSV format violation: " + e.getMessage());
            }
        }
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
            int i = 1;
            for (City city : CityManager.getInstance().getCollection()) {
                records.add(city.getId() + "," + city.getName() + "," + city.getCoordinates().getX() + ","
                        + city.getCoordinates().getY() + "," + city.getCreationDate() + "," + city.getArea() + ","
                        + city.getPopulation() + "," + city.getMetersAboveSeaLevel() + "," + city.getClimate() + ","
                        + city.getGovernment() + "," + city.getStandardOfLiving() + "," + city.getGovernor());

                i++;
            }

            CSVManager csvManager = new CSVManager();
            csvManager.write(pathToDataFile, header, records);
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("CSV format violation: " + e.getMessage());
        }
    }


    @Override
    public void addElementToCollection(City value) {
        if (cityTreeSet != null)
            cityTreeSet.add(value);
        else {
            TreeSet<City> cities = new TreeSet<>(new CityComparator());
            cities.add(value);
            CityManager.getInstance().setCollection(cities);
        }
    }

    /**
     * Returns first element of collection.
     *
     * @return First element of collection. If collection is empty, returns new object.
     */
    @Override
    public City getFirstOrNew() {
        if (cityTreeSet.iterator().hasNext())
            return cityTreeSet.iterator().next();
        else
            return new City();
    }

    @Override
    public void clearCollection() {
        cityTreeSet.clear();
    }

    /**
     * Sets the collection of cities.
     *
     * @param cityTreeSet the TreeSet collection of City objects to set
     */
    @Override
    public void setCollection(TreeSet<City> cityTreeSet) {
        this.cityTreeSet = cityTreeSet;
    }

    /**
     * Gets the collection of cities.
     *
     * @return the TreeSet collection of City objects
     */
    public TreeSet<City> getCollection() {
        return cityTreeSet;
    }

    /**
     * Sets the path to the .csv file.
     *
     * @param pathToDataFile the path to the .csv file to set
     */
    public static void setPathToDataFile(String pathToDataFile) {
        CityManager.pathToDataFile = pathToDataFile;
    }

}
