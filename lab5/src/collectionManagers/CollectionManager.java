package collectionManagers;

import collection.City.*;
import user.UserManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class CollectionManager {
    private static TreeSet<City> cityTreeSet;
    private static String pathToDataFile;
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

    public void writeCollection() {
        CSVManager csvManager = new CSVManager();
        csvManager.write(pathToDataFile, cityTreeSet);
    }
    public static void setCityCollection(TreeSet<City> cityTreeSet) {
        CollectionManager.cityTreeSet = cityTreeSet;
    }

    public static TreeSet<City> getCityCollection() {
        return cityTreeSet;
    }

    public static void setPathToDataFile(String pathToDataFile){
        CollectionManager.pathToDataFile = pathToDataFile;
    }

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
