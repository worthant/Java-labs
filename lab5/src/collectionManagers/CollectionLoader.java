package collectionManagers;

import collection.CollectionManager;

import java.io.IOException;

public class CollectionLoader {
    public void loadCollection(String envKey) {
        try {
            String pathToDataFile = System.getenv(envKey);

            if (pathToDataFile == null) {
                System.out.println("Переменной окружения lab5 не существует!");
            } else if (pathToDataFile.trim().split(" ").length != 1) {
                System.out.println("Некорректно задана переменная окружения lab5! " +
                        "\nЗадайте переменную окружения с именем \"lab5\", поместив туда полный путь к .csv файлу.");
            }

            CSVManager csvManager = new CSVManager();
            CollectionManager.setCollection(csvManager.readFromFile(pathToDataFile));
        } catch (IOException e) {
            System.out.println("Возникла ошибка ввода-вывода при работе программы!");
        }
    }
}
