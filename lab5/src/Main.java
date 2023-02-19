import collectionManagers.CollectionManager;
import user.UserManager;

public class Main {
    public static void main(String[] args) {
        String envKey = "lab5";

        /** load collection using environment key */
        CollectionManager loader = new CollectionManager();
        loader.loadCollection(envKey);

        while (UserManager.isWorking()) {
            UserManager.requestCommand();
        }
    }
}