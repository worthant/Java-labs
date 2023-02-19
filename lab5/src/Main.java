import collection.CollectionManager;
import collectionManagers.CollectionLoader;

public class Main {
    public static void main(String[] args) {
        String envKey = "lab5";

        /** load collection using environment key */
        CollectionLoader loader = new CollectionLoader();
        loader.loadCollection(envKey);
    }
}