package collectionStorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 The {@code Managers} interface represents a collection manager that reads and writes objects from and to a file.
 It has two methods to read objects from a file and write objects to a file.
 */
public interface FileManager {

    /**
     * Reads a file at the specified path and returns an ArrayList containing each line of the file as a string.
     *
     * @param pathToDataFile The path to the file to read.
     * @return An ArrayList containing each line of the file as a string.
     * @throws IOException If there is an error reading the file.
     */
    ArrayList<String> readFromFile(String pathToDataFile) throws IOException;

    /**
     * Writes a TreeSet of City objects to a file located at the specified path.
     * @param pathToDataFile the path to the file to be written
     * @param header An array of strings representing the header row of the CSV file.
     * @param records An array of objects representing the records to write to the CSV file. Each record should be an array of objects corresponding to a row in the CSV file.
     * @throws IOException if there was an error writing to the file
     */
    void write(String pathToDataFile, String[] header, List<String> records) throws IOException;
}
