package collectionStorageManager;

import models.City;

import java.util.ArrayList;
import java.util.Map;

public interface DatabaseManager {
    ArrayList<City> getCollectionFromDatabase();

    void writeCollectionToDatabase();

    Map<Long, String> getOwnerShipMap();
}
