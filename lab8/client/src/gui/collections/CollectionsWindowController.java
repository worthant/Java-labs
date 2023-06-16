package gui.collections;

import client.Client;
import client.DataHolder;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import commandManager.CommandDescriptionHolder;
import commandManager.CommandLoaderUtility;
import commandManager.CommandMode;
import commandManager.SingleCommandExecutor;
import exceptions.CommandsNotLoadedException;
import gui.AlertUtility;
import gui.UTF8Control;
import gui.create.CityManagementWindow;
import gui.login.LoginWindow;
import gui.music.MusicWindow;
import gui.visualization.VisualizationWindow;
import gui.worldMap.WorldMapWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.City;
import models.Climate;
import models.Government;
import models.StandardOfLiving;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.GetOwnershipRequestSender;
import requestLogic.requestSenders.ShowRequestSender;
import responses.CommandStatusResponse;
import responses.GetOwnershipResponse;
import responses.ShowResponse;
import serverLogic.ServerConnectionHandler;

import java.io.File;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsWindowController {
    private static final Logger logger = LogManager.getLogger("lab8");
    @FXML
    private Button sumOfMetersAboveSeaLevelButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button executeScriptButton;
    @FXML
    private Button removeById;
    @FXML
    private TextField removeByIdTextField;
    @FXML
    private Button exitButton;
    @FXML
    private Button addIfMinAreaButton;
    @FXML
    private Button commandsHistoryButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button browseButton;

    @FXML
    private ToolBar commandsToolBar;
    @FXML
    private Button commandsButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button createButton;
    @FXML
    private Button visualizeButton;
    @FXML
    private Button worldMapButton;
    @FXML
    private Label filterByLabel;
    @FXML
    private Label citiesCountLabel;
    @FXML
    private Label citiesLabel;
    private ResourceBundle currentBundle;
    private Stage stage;

    private FileChooser fileChooser;
    private String scriptPath;
    private Map<Long, String> ownershipMap; // Map of (city_id, client_name)
    private Map<String, Color> clientColorMap = new HashMap<>();
    private Client client;
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
    private TextField filterByText;

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
        commandsToolBar.setVisible(false);

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

        // Setup preferable width for columns (In the end it's about the user experience ^_^)
        idColumn.setPrefWidth(30);
        governorColumn.setPrefWidth(120);
        creationColumn.setPrefWidth(135);
        climateColumn.setPrefWidth(60);
        coordXColumn.setPrefWidth(55);
        coordYColumn.setPrefWidth(55);
        standardsColumn.setPrefWidth(70);
        nameColumn.setPrefWidth(120);
        metersAboveSeaLevelColumn.setPrefWidth(70);

        // Initialize the FileChooser for the Execute script button
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Script File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Script Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Set the initial directory
        // for laptop:
         fileChooser.setInitialDirectory(new File("C:\\Users\\Admin\\Itmo\\Java_labs\\lab8\\client\\src\\main\\resources\\scripts"));
        // for pc:
        //fileChooser.setInitialDirectory(new File("C:\\Users\\Boris\\Itmo\\Java_labs\\lab8\\client\\src\\main\\resources\\scripts"));

        // setup Client
        client = Client.getInstance();

        // setup ownership logic
        loadOwnershipMap();
        table.setRowFactory(tv -> new TableRow<City>() {
            @Override
            public void updateItem(City city, boolean empty) {
                super.updateItem(city, empty);
                if (city == null) {
                    setStyle("");
                } else {
                    Color color = clientColorMap.get(ownershipMap.get(city.getId()));
                    String rgb = String.format("#%02X%02X%02X",
                            (int)(color.getRed() * 255),
                            (int)(color.getGreen() * 255),
                            (int)(color.getBlue() * 255));
                    setStyle("-fx-border-color: " + rgb + ";");
                }
            }
        });

        // listener for filtering using Stream API
        filterByText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                String selectedValue = comboBox.getSelectionModel().getSelectedItem();
                filterCities(selectedValue, newValue);
            } else {
                // If the TextField is empty, show all cities
                table.setItems(FXCollections.observableArrayList(collection));
            }
        });

        // Start the timeline for loading collection to TableView
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollection()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Loads ownership map from the server
     */
    private void loadOwnershipMap() {
        client = Client.getInstance();
        GetOwnershipRequestSender rqSender = new GetOwnershipRequestSender();
        GetOwnershipResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("get_ownership", new ExternalBaseReceiverCaller()), new String[]{"get_ownership"}, ServerConnectionHandler.getCurrentConnection());
        this.ownershipMap = response.getOwnershipMap();

        for (String clientName : new HashSet<>(ownershipMap.values())) {
            if (clientName.equals(Client.getInstance().getName())) {
                clientColorMap.put(clientName, Color.GREEN);
            } else {
                if (!clientColorMap.containsKey(clientName)) {
                    Color randomColor = Color.color(Math.random(), Math.random(), Math.random());
                    clientColorMap.put(clientName, randomColor);
                }
            }
        }
    }

    /**
     * If you have different time format - checkout LocalizationUtility for more methods
     *
     * @param date - creation date of my cities
     * @return date in correct format, that will be more interesting to see, rather than 2023-12-11
     */
    private String getDate(Date date) {
        if (date == null) return "null";
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, currentBundle.getLocale());
        return formatter.format(date);
    }

    /**
     * Load collection from server
     */
    private void loadCollection() {
        loadOwnershipMap();
        ShowRequestSender rqSender = new ShowRequestSender();
        ShowResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("show", new ExternalBaseReceiverCaller()), new String[]{"show"}, ServerConnectionHandler.getCurrentConnection());
        setCollection(response.getCityTreeSet());
        loadOwnershipMap();
    }

    /**
     * Set just loaded collection to this class
     * Update TableView with that collection
     * Update counter with collection.size()
     *
     * @param collection - TreeSet<City> collection of City objects
     */
    public void setCollection(TreeSet<City> collection) {
        this.collection = collection;
        if (collection != null) {
            for (City city : collection) {
                System.out.println(city.toString());
            }
            table.setItems(FXCollections.observableArrayList(collection));

//            always sort TableView by id (by default), if i want to bypass the TreeSet sorting
//            idColumn.setSortType(TableColumn.SortType.ASCENDING);
//            table.getSortOrder().add(idColumn);

            table.refresh();
            citiesCountLabel.setText(Integer.toString(collection.size()));
        }
    }

    /**
     * Update CollectionsWindow UI
     */
    private void updateUI() {
        // TableView
        idColumn.setText(currentBundle.getString("id"));
        nameColumn.setText(currentBundle.getString("name"));
        coordXColumn.setText(currentBundle.getString("coordX"));
        coordYColumn.setText(currentBundle.getString("coordY"));
        creationColumn.setText(currentBundle.getString("creation"));
        areaColumn.setText(currentBundle.getString("area"));
        populationColumn.setText(currentBundle.getString("population"));
        metersAboveSeaLevelColumn.setText(currentBundle.getString("metersAboveSeaLevel"));
        climateColumn.setText(currentBundle.getString("climate"));
        governmentColumn.setText(currentBundle.getString("government"));
        standardsColumn.setText(currentBundle.getString("standards"));
        governorColumn.setText(currentBundle.getString("governor"));
        // buttons & labels
        citiesLabel.setText(currentBundle.getString("citiesCountLabel"));
        filterByLabel.setText(currentBundle.getString("filterByLabel"));
        ObservableList<String> localizedItems = FXCollections.observableArrayList(
                currentBundle.getString("id"),
                currentBundle.getString("name"),
                currentBundle.getString("coordX"),
                currentBundle.getString("coordY"),
                currentBundle.getString("creation"),
                currentBundle.getString("area"),
                currentBundle.getString("population"),
                currentBundle.getString("metersAboveSeaLevel"),
                currentBundle.getString("climate"),
                currentBundle.getString("government"),
                currentBundle.getString("standards"),
                currentBundle.getString("governor")
        );
        comboBox.getItems().setAll(localizedItems);
        commandsButton.setText(currentBundle.getString("commandsButton"));
        deleteButton.setText(currentBundle.getString("deleteButton"));
        editButton.setText(currentBundle.getString("editButton"));
        createButton.setText(currentBundle.getString("createButton"));
        visualizeButton.setText(currentBundle.getString("visualizeButton"));
        worldMapButton.setText(currentBundle.getString("worldMapButton"));

        // let's update buttons in toolBox
        sumOfMetersAboveSeaLevelButton.setText(currentBundle.getString("sumOfMetersAboveSeaLevelButton"));
        clearButton.setText(currentBundle.getString("clearButton"));
        executeScriptButton.setText(currentBundle.getString("executeScriptButton"));
        browseButton.setText(currentBundle.getString("browseButton"));
        exitButton.setText(currentBundle.getString("exitButton"));
        helpButton.setText(currentBundle.getString("helpButton"));
        commandsHistoryButton.setText(currentBundle.getString("commandsHistoryButton"));
    }

    @FXML
    protected void onCreateButtonClick() {
        CityManagementWindow cityManagementWindow = new CityManagementWindow("Creating City");
        cityManagementWindow.show();
    }

    @FXML
    protected void onEditButtonClick() {
        City selectedCity = table.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            CityManagementWindow cityManagementWindow = new CityManagementWindow("Editing City");
            cityManagementWindow.show();
            cityManagementWindow.setCity(selectedCity);
        } else {
            AlertUtility.infoAlert("Please, select any city to edit it!)");
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        City selectedCity = table.getSelectionModel().getSelectedItem();

        if (selectedCity != null) {
            table.getItems().remove(selectedCity);
            try {
                SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
                executor.executeCommand("remove_by_id " + selectedCity.getId());
            } catch (CommandsNotLoadedException e) {
                AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
            }

            Platform.runLater(() -> {
                CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
                if (response != null) {
                    AlertUtility.infoAlert(response.getResponse());
                } else {
                    AlertUtility.errorAlert("something wrong with execute_script command. It's suddenly silent -_-");
                }
            });
        } else {
            AlertUtility.infoAlert("Please, select any city to delete it!)");
        }
    }

    @FXML
    protected void onVisualizeButtonClick() {
        VisualizationWindow visualizationWindow = new VisualizationWindow(collection);
        visualizationWindow.show();
        visualizationWindow.loadColorMap(clientColorMap, ownershipMap);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollectionToVisualizationWindow(visualizationWindow)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    protected void onGeoIconClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
        updateUI();
    }

    @FXML
    protected void onWorldMapButtonClick() {
        WorldMapWindow visualizationWindow = new WorldMapWindow(collection);
        visualizationWindow.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> loadCollectionToWorldMapWindow(visualizationWindow)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadCollectionToWorldMapWindow(WorldMapWindow worldMapWindow) {
        worldMapWindow.loadCities(collection);
    }

    private void loadCollectionToVisualizationWindow(VisualizationWindow visualizationWindow) {
        visualizationWindow.loadCollection(collection);
    }

    @FXML
    protected void onCommandsButtonClick() {
        commandsToolBar.setVisible(!commandsToolBar.isVisible());
        stage.setHeight(commandsToolBar.isVisible() ? 670 : 615);
    }

    @FXML
    protected void onCommandsHistoryButtonClick() {
        try {
            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
            executor.executeCommand("history");
        } catch (CommandsNotLoadedException e) {
            AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
        }

        Platform.runLater(() -> {
            CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
            if (response != null) {
                AlertUtility.infoAlert(response.getResponse());
            } else {
                AlertUtility.errorAlert("something wrong with execute_script command. It's suddenly silent -_-");
            }
        });
    }

    public void setLocale(int index) {
        this.currentLocaleIndex = index;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void onMusicIconClick() {
        MusicWindow musicWindow = new MusicWindow();
        musicWindow.show();
    }

    @FXML
    protected void onExecuteScriptButtonClick() {
        if (scriptPath != null && !scriptPath.isEmpty()) {
            try {
                SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
                executor.executeCommand("execute_script " + scriptPath);
            } catch (CommandsNotLoadedException e) {
                AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
            }

            Platform.runLater(() -> {
                CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
                if (response != null) {
                    AlertUtility.infoAlert(response.getResponse());
                } else {
                    AlertUtility.errorAlert("something wrong with execute_script command. It's suddenly silent -_-");
                }
            });
        }
    }

    @FXML
    protected void onSumOfMetersAboveSeaLevelButtonClick() {
        try {
            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
            executor.executeCommand("sum_of_meters_above_sea_level");
        } catch (CommandsNotLoadedException e) {
            AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
        }

        Platform.runLater(() -> {
            CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
            if (response != null) {
                AlertUtility.infoAlert(response.getResponse());
            } else {
                AlertUtility.errorAlert("idk why clearing the collection isn't done, maybe because there is no wage for that lab8");
            }
        });
    }

    @FXML
    protected void onExitButtonClick() {
        stage.close();
        LoginWindow loginWindow = new LoginWindow(new Stage());
        loginWindow.show();
    }

    @FXML
    protected void onHelpButtonClick() {
        try {
            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
            executor.executeCommand("help");
        } catch (CommandsNotLoadedException e) {
            AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
        }

        Platform.runLater(() -> {
            CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
            if (response != null) {
                AlertUtility.infoAlert(response.getResponse());
            } else {
                AlertUtility.errorAlert("idk why clearing the collection isn't done, maybe because there is no wage for that lab8");
            }
        });
    }
    @FXML
    protected void onBrowseButtonClick() {
        File selectedFile = fileChooser.showOpenDialog(executeScriptButton.getScene().getWindow());
        if (selectedFile != null) {
            scriptPath = selectedFile.getAbsolutePath();
            executeScriptButton.setText("Execute " + selectedFile.getName());
        }
    }
    @FXML
    protected void onClearButtonClick() {
        try {
            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
            executor.executeCommand("clear");
        } catch (CommandsNotLoadedException e) {
            AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
        }

        Platform.runLater(() -> {
            CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
            if (response != null) {
                AlertUtility.infoAlert(response.getResponse());
            } else {
                AlertUtility.errorAlert("idk why clearing the collection isn't done, maybe because there is no wage for that lab8");
            }
        });
    }

    private void filterCities(String property, String value) {
        Stream<City> cityStream = collection.stream();
        switch (property) {
            case "id":
                long id = Long.parseLong(value);
                cityStream = cityStream.filter(city -> city.getId() == id);
                break;
            case "creationDate":
                DateTimeFormatter formatter;
                switch (currentBundle.getLocale().toString()) {
                    case "en_NZ":
                        formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", currentBundle.getLocale()); // for English (NZ) locale
                        break;
                    case "hr":
                        formatter = DateTimeFormatter.ofPattern("EEEE, d. MMMM yyyy.", currentBundle.getLocale()); // for Croatian locale
                        break;
                    case "ru":
                        formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'г.'", currentBundle.getLocale()); // for Russian locale
                        break;
                    case "cs":
                        formatter = DateTimeFormatter.ofPattern("EEEE, d. MMMM yyyy", currentBundle.getLocale()); // for Czech locale
                        break;
                    default:
                        formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US); // default to US locale
                        break;
                }
                LocalDate date = LocalDate.parse(value, formatter);
                cityStream = cityStream.filter(city -> {
                    String cityCreationDateString = getDate(city.getCreationDate());
                    LocalDate cityCreationDate = LocalDate.parse(cityCreationDateString, formatter);
                    return cityCreationDate.equals(date);
                });
                break;
            case "population":
                int population = Integer.parseInt(value);
                cityStream = cityStream.filter(city -> city.getPopulation() == population);
                break;
            case "area":
                int area = Integer.parseInt(value);
                cityStream = cityStream.filter(city -> city.getArea() == area);
                break;
            case "metersAboveSeaLevel":
                Double metersAboveSeaLevel = Double.parseDouble(value);
                cityStream = cityStream.filter(city -> city.getMetersAboveSeaLevel().equals(metersAboveSeaLevel));
                break;
            case "name":
                cityStream = cityStream.filter(city -> city.getName().equals(value));
                break;
            case "climate":
                Climate climate = Climate.valueOf(value);
                cityStream = cityStream.filter(city -> city.getClimate().equals(climate));
                break;
            case "government":
                Government government = Government.valueOf(value);
                cityStream = cityStream.filter(city -> city.getGovernment().equals(government));
                break;
            case "standards":
                StandardOfLiving standards = StandardOfLiving.valueOf(value);
                cityStream = cityStream.filter(city -> city.getStandardOfLiving().equals(standards));
                break;
            case "governor":
                cityStream = cityStream.filter(city -> city.getGovernor().getName().equals(value));
                break;
            case "coord X":
                int coordX = Integer.parseInt(value);
                cityStream = cityStream.filter(city -> city.getCoordinates().getX() == coordX);
                break;
            case "coord Y":
                double coordY = Double.parseDouble(value);
                cityStream = cityStream.filter(city -> city.getCoordinates().getY() == coordY);
                break;
            default:
                break;
        }
        table.setItems(FXCollections.observableArrayList(cityStream.collect(Collectors.toList())));
    }


}
