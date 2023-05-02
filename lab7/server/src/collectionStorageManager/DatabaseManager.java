package collectionStorageManager;

import models.City;

import java.util.ArrayList;

public interface DatabaseManager {
    ArrayList<City> getCollectionFromDatabase();

    void writeCollectionToDatabase();
}
