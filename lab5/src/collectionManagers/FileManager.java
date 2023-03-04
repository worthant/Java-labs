package collectionManagers;

import collection.City.City;

import java.io.IOException;
import java.util.TreeSet;

/**
 The {@code Managers} interface represents a collection manager that reads and writes objects from and to a file.
 It has two methods to read objects from a file and write objects to a file.
 */
public interface FileManager {

    /**
     Reads a TreeSet of Cities from a file located at the specified path.
     @param pathToDataFile the path to the file to be read
     @return a TreeSet of City objects read from the file
     @throws IOException if there was an error reading the file
     */
    TreeSet<City> readFromFile(String pathToDataFile) throws IOException;

    /**
     Writes a TreeSet of City objects to a file located at the specified path.
     @param pathToDataFile the path to the file to be written
     @param cities a TreeSet of City objects to be written to the file
     @throws IOException if there was an error writing to the file
     */
    void write(String pathToDataFile, TreeSet<City> cities) throws IOException;
}
