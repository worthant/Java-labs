package gui.collections;
import client.Client;
import client.DataHolder;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import commandManager.*;
import exceptions.CommandsNotLoadedException;
import gui.LocalizationUtility;
import gui.UTF8Control;
import gui.create.CreateWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.*;
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
import requestLogic.requestSenders.AuthRequestSender;
import requestLogic.requestSenders.ShowRequestSender;
import responses.AuthResponse;
import responses.ShowResponse;
import serverLogic.ServerConnectionHandler;

import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class CollectionsWindowController {
    private static final Logger logger = LogManager.getLogger("lab8");

    private ResourceBundle currentBundle;

    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("en", "NZ"),
            new Locale("ru"),
            new Locale("hr"),
            new Locale("cs")
    );
    private int currentLocaleIndex = 0;

    private TreeSet<City> collection;
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
    private TableColumn<City, String> creationColumn;
    @FXML
    private TableColumn<City, Integer> areaColumn;
    @FXML
    private TableColumn<City, Integer> populationColumn;
    @FXML
    private TableColumn<City, Double> metersAboveSeaLevelColumn;
    @FXML
    private TableColumn<City, String> governmentColumn;
    @FXML
    private TableColumn<City, String> standardsColumn;
    @FXML
    private TableColumn<City, String> climateColumn;
    @FXML
    private TableColumn<City, String> governorColumn;

    @FXML
    private Text usernameText;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void initialize() {
        // handle locales
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
        updateUI();

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
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        coordXColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCoordinates().getX()).asObject());
        coordYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCoordinates().getY()).asObject());
        creationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getDate(cellData.getValue().getCreationDate())));
        areaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getArea()).asObject());
        populationColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPopulation()).asObject());
        metersAboveSeaLevelColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMetersAboveSeaLevel()).asObject());
        climateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClimate().toString()));
        governmentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGovernment().toString()));
        standardsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStandardOfLiving().toString()));
        governorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGovernor() != null ? cellData.getValue().getGovernor().getName() : ""));

        // Start the timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollection()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String getDate(Date date) {
        if (date == null) return "null";
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, currentBundle.getLocale());
        return formatter.format(date);
    }

    private void loadCollection() {
        Client client = Client.getInstance();
        ShowRequestSender rqSender = new ShowRequestSender();
        ShowResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("show", new ExternalBaseReceiverCaller()), new String[]{"show"}, ServerConnectionHandler.getCurrentConnection());
        setCollection(response.getCityTreeSet());
    }

    public void setCollection(TreeSet<City> collection) {
        this.collection = collection;
        if (collection != null) {
            for (City city : collection) {
                System.out.println(city.toString());
            }
            table.setItems(FXCollections.observableArrayList(collection));
            table.refresh();
        }
    }

    @FXML
    protected void onCreateButtonClick() {
        CreateWindow createWindow = new CreateWindow();
        createWindow.show();
    }

    @FXML
    protected void onGeoIconClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
        updateUI();
    }

    private void updateUI() {
//        exitButton.setText(locally.getKeyString("Exit"));
//        logoutButton.setText(locally.getKeyString("LogOut"));
//        helpButton.setText(locally.getKeyString("Help"));
//        infoButton.setText(locally.getKeyString("Info"));
//        addButton.setText(locally.getKeyString("Add"));
//        updateButton.setText(locally.getKeyString("Update"));
//        removeByIdButton.setText(locally.getKeyString("RemoveByID"));
//        clearButton.setText(locally.getKeyString("Clear"));
//        executeScriptButton.setText(locally.getKeyString("ExecuteScript"));
//        headButton.setText(locally.getKeyString("Head"));
//        addIfMaxButton.setText(locally.getKeyString("AddIfMax"));
//        addIfMinButton.setText(locally.getKeyString("AddIfMin"));
//        sumOfPriceButton.setText(locally.getKeyString("SumOfPrice"));
//        filterByPriceButton.setText(locally.getKeyString("FilterByPrice"));
//        filterContainsPartNumberButton.setText(locally.getKeyString("FilterContainsPartNumber"));
//
//        tableTab.setText(locally.getKeyString("TableTab"));
//        visualTab.setText(locally.getKeyString("VisualTab"));

//        nameColumn.setText(currentBundle.getString("name"));
//        creationColumn.setText(currentBundle.getString("creationDate"));
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
