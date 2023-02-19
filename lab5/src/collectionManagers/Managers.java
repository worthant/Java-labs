package collectionManagers;

import collection.City;

import java.io.IOException;
import java.util.TreeSet;

public interface Managers {
    TreeSet<City> readFromFile(String pathToDataFile) throws IOException;
}
