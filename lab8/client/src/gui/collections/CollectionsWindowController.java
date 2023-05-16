package gui.collections;
import client.Client;
import client.DataHolder;
import commandManager.*;
import gui.create.CreateWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.City;

public class CollectionsWindowController {
    @FXML
    private TableView<City> table;

    @FXML
    private TableColumn<City, String> nameColumn;

    @FXML
    private Text usernameText;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void initialize() {
        // init commands
        CommandLoaderUtility.initializeCommands();

        // init graphics stuff
        comboBox.getItems().addAll("id", "name", "coord X", "coord Y", "creation", "area", "population", "government", "standards", "climate", "governor");
        Font.loadFont(getClass().getResourceAsStream("/fonts/ZCOOLXiaoWei-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/YouSheBiaoTiHei Regular.ttf"), 14);

        // init username
        String currentUsername = Client.getInstance().getName();
        usernameText.setText(currentUsername);

        // Retrieve data from DataHolder
        DataHolder data = DataHolder.getInstance();
    }

//    /**
//     * method for loading commands from the old Main class
//     */
//    private void loadCommands() {
//        boolean commandsNotLoaded = true;
//        int waitingCount = 4000;
//        while (commandsNotLoaded) {
//            try {
//                SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), CommandMode.GUIMode);
//                commandsNotLoaded = false;
//
//                executor.executeCommand();
//            } catch (CommandsNotLoadedException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("We couldn't get commands from server last time, so now we'll try to do this again...");
//                alert.showAndWait();
//
//                AtomicInteger secondsRemained = new AtomicInteger(waitingCount / 1000 - 1);
//
//                javax.swing.Timer timer = new Timer(1000, (x) -> alert.setContentText("Re-attempt in " + secondsRemained.getAndDecrement() + " seconds. You may interrupt awaiting by hitting Enter."));
//                alert.showAndWait();
//
//                timer.start();
//
//                CountDownLatch latch = new CountDownLatch(1);
//
//                int finalWaitingCount = waitingCount;
//                Runnable wait = () -> {
//                    try {
//                        Thread.sleep(finalWaitingCount);
//                        latch.countDown();
//                    } catch (InterruptedException ex) {
//                        alert.setContentText("Continuing...");
//                        alert.showAndWait();
//                    }
//                };
//
//                Thread tWait = new Thread(wait);
//                //Thread tForceInt = new Thread(forceInterrupt);
//
//                tWait.start();
//                //tForceInt.start();
//
//                try {
//                    latch.await();
//                    timer.stop();
//                    tWait.interrupt();
//                } catch (InterruptedException ex) {
//                    alert.setContentText("Interrupted loading");
//                    alert.showAndWait();
//                }
//
//                waitingCount += 2000;
//            }
//        }
//    }

    @FXML
    protected void onCreateButtonClick() {
        CreateWindow createWindow = new CreateWindow();
        createWindow.show();
    }

    @FXML
    protected void onEditButtonClick() {}

    @FXML
    protected void onDeleteButtonClick() {}

    @FXML
    protected void onVisualizeButtonClick() {}

    @FXML
    protected void onCommandsButtonClick() {}
}
