package collectionManagers;

import collection.City.City;

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

        //return new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standartOfLiving, governor);
    }
}
