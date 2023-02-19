package collectionManagers;

import collection.City.City;

import java.io.IOException;
import java.util.TreeSet;

public interface Managers {
    TreeSet<City> readFromFile(String pathToDataFile) throws IOException;
    void write(String pathToDataFile, TreeSet<City> cities) throws IOException;
}
