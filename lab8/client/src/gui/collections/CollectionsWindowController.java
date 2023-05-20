package gui.collections;
import client.Client;
import client.DataHolder;
import commandManager.*;
import exceptions.CommandsNotLoadedException;
import gui.create.CreateWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.City;
import models.Climate;
import models.Government;
import models.StandardOfLiving;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.TreeSet;

public class CollectionsWindowController {
    private static final Logger logger = LogManager.getLogger("lab6");
    @FXML
    private TableView<City> table;
    @FXML
    private TableColumn<City, Long> idColumn;
    @FXML
    private TableColumn<City, String> nameColumn;
    @FXML
    private TableColumn<City, Integer> coordXColumn;
    @FXML
    private TableColumn<City, Double> coordYColumn;
    @FXML
    private TableColumn<City, Date> creationColumn;
    @FXML
    private TableColumn<City, Integer> areaColumn;
    @FXML
    private TableColumn<City, Integer> populationColumn;
    @FXML
    private TableColumn<City, Government> governmentColumn;
    @FXML
    private TableColumn<City, StandardOfLiving> standardsColumn;
    @FXML
    private TableColumn<City, Climate> climateColumn;
    @FXML
    private TableColumn<City, String> governorColumn;

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

        // Setup cellValueFactories
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        coordXColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getX()));
        coordYColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getY()));
        creationColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        governmentColumn.setCellValueFactory(new PropertyValueFactory<>("government"));
        standardsColumn.setCellValueFactory(new PropertyValueFactory<>("standardOfLiving"));
        climateColumn.setCellValueFactory(new PropertyValueFactory<>("climate"));
        governorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGovernor() != null ? cellData.getValue().getGovernor().getName() : ""));

        // Start the timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollection()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



    private void loadCollection() {
        try {
            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), CommandMode.GUIMode);
            executor.executeCommand("show");
//            TreeSet<City> collection = DataHolder.getInstance().getCollection();

            // fill TableView columns with collection
            ObservableList<City> observableList = FXCollections.observableArrayList(DataHolder.getInstance().getCollection());
            table.setItems(observableList);

//            for (City obj2: collection) {
//                System.out.println(obj2.toString());
//            }
//            for (City obj: observableList) {
//                System.out.println(obj.toString());
//            }
        } catch (CommandsNotLoadedException e) {
            logger.info("something wrong while loading collection" + e.getMessage());
        }
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
