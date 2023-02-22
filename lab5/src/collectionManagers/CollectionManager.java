package collectionManagers;

import collection.City.*;
import user.UserManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * CollectionManager is a class for working with a TreeSet collection of City objects.
 * It provides methods for loading and writing the collection to a .csv file,
 * as well as creating a new City object based on user input.
 */
public class CollectionManager {
    private static TreeSet<City> cityTreeSet;
    private static String pathToDataFile;

    /**
     * Loads the TreeSet collection from a .csv file using the environment variable with the specified key.
     * @param envKey the key of the environment variable containing the path to the .csv file
     */
    public void loadCollection(String envKey) {
        String pathToDataFile = System.getenv(envKey);
        CollectionManager.setPathToDataFile(pathToDataFile);
        if (pathToDataFile == null) {
            System.out.println("Переменной окружения lab5 не существует!");
        } else if (pathToDataFile.trim().split(" ").length != 1) {
            System.out.println("Некорректно задана переменная окружения lab5! " +
                    "\nЗадайте переменную окружения с именем \"lab5\", поместив туда полный путь к .csv файлу.");
        }

        CSVManager csvManager = new CSVManager();
        CollectionManager.setCityCollection(csvManager.readFromFile(pathToDataFile));
    }

    /**
     * Writes TreeSet collection to .csv file
     */
    public void writeCollection() {
        CSVManager csvManager = new CSVManager();
        csvManager.write(pathToDataFile, cityTreeSet);
    }

    /**
     * Sets the collection of cities.
     * @param cityTreeSet the TreeSet collection of City objects to set
     */
    public static void setCityCollection(TreeSet<City> cityTreeSet) {
        CollectionManager.cityTreeSet = cityTreeSet;
    }

    /**
     * Gets the collection of cities.
     * @return the TreeSet collection of City objects
     */
    public static TreeSet<City> getCityCollection() {
        return cityTreeSet;
    }

    /**
     * Sets the path to the .csv file.
     * @param pathToDataFile the path to the .csv file to set
     */
    public static void setPathToDataFile(String pathToDataFile){
        CollectionManager.pathToDataFile = pathToDataFile;
    }

    /**
     * Creates new City object based on user input
     * @return City object created
     */
    public static City getNewCity() {
        ArrayList<Object> parameters = UserManager.createNewCityByUser();

        long id = (Long) parameters.get(0);
        String name = (String) parameters.get(1);
        Coordinates coordinates = (Coordinates) parameters.get(2);
        java.util.Date creationDate = (java.util.Date) parameters.get(3);
        Integer area = (Integer) parameters.get(4);
        int population = (Integer) parameters.get(5);
        Double metersAboveSeaLevel = (Double) parameters.get(6);
        Climate climate = (Climate) parameters.get(7);
        Government government = (Government) parameters.get(8);
        StandardOfLiving standardOfLiving = (StandardOfLiving) parameters.get(9);
        Human governor = (Human) parameters.get(10);

        return new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
    }
}
