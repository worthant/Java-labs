package gui.create;

import client.Client;
import client.DataHolder;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import commandManager.CommandDescriptionHolder;
import commandManager.CommandMode;
import commandManager.SingleCommandExecutor;
import exceptions.CommandsNotLoadedException;
import gui.AlertUtility;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.utilities.Utilities;
import models.*;
import models.validators.*;
import requestLogic.requestSenders.GetOwnershipRequestSender;
import responses.CommandStatusResponse;
import responses.GetOwnershipResponse;
import serverLogic.ServerConnectionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for the Create Window.
 * This class handles user interactions in the Create Window UI.
 */
public class CityManagementWindowController {
    @FXML
    private Label actionLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField coordXField;
    @FXML
    private TextField coordYField;
    @FXML
    private TextField areaField;
    @FXML
    private TextField populationField;
    @FXML
    private TextField metersAboveSeaLevelField;
    @FXML
    private ChoiceBox<String> climateChoiceBox;
    @FXML
    private ChoiceBox<String> governmentChoiceBox;
    @FXML
    private ChoiceBox<String> standardsChoiceBox;
    @FXML
    private TextField governorField;

    private Map<String, Boolean> validationState;
    private String actionText;
    private Map<Long, String> ownershipMap; // Map of (city_id, client_name)

    @FXML
    public void initialize() {
        actionLabel.setText(actionText);
        Arrays.stream(Climate.values()).forEach(value -> climateChoiceBox.getItems().add(value.name()));
        Arrays.stream(Government.values()).forEach(value -> governmentChoiceBox.getItems().add(value.name()));
        Arrays.stream(StandardOfLiving.values()).forEach(value -> standardsChoiceBox.getItems().add(value.name()));
        loadOwnershipMap();
        checkOwnership();
        validationState = new HashMap<>();
        validation();
    }

    private void loadOwnershipMap() {
        Client client = Client.getInstance();
        GetOwnershipRequestSender rqSender = new GetOwnershipRequestSender();
        GetOwnershipResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("get_ownership", new ExternalBaseReceiverCaller()), new String[]{"get_ownership"}, ServerConnectionHandler.getCurrentConnection());
        this.ownershipMap = response.getOwnershipMap();
    }

    private void checkOwnership() {

    }

    /**
     * This method is invoked when the "Create" button is clicked.
     * It validates the input fields and, if valid, creates a new City object and sends it to the server.
     * If the fields are not valid, it shows an error message.
     */
    @FXML
    protected void onCreateButtonClick() {
        if (validationState.values().stream().allMatch(valid -> valid) &&
                climateChoiceBox.getValue() != null &&
                governmentChoiceBox.getValue() != null &&
                standardsChoiceBox.getValue() != null) {

            long id = Utilities.generateId();
            String name = nameField.getText();
            Coordinates coordinates = new Coordinates(Integer.parseInt(coordXField.getText()), Double.parseDouble(coordYField.getText()));
            java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
            Integer area = Integer.valueOf(areaField.getText());
            int population = Integer.parseInt(populationField.getText());
            Double metersAboveSeaLevel = Double.valueOf(metersAboveSeaLevelField.getText());
            Climate climate = climateChoiceBox.getValue() != null ? Climate.valueOf(climateChoiceBox.getValue()) : null;
            Government government = governmentChoiceBox.getValue() != null ? Government.valueOf(governmentChoiceBox.getValue()) : null;
            StandardOfLiving standards = standardsChoiceBox.getValue() != null ? StandardOfLiving.valueOf(standardsChoiceBox.getValue()) : null;
            Human human = new Human(governorField.getText());

            City city = new City(id, name, coordinates, creationDate, area, population,
                    metersAboveSeaLevel, climate, government, standards, human);

            DataHolder.getInstance().setCityObject(city);

            try {
                SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.GUIMode);
                executor.executeCommand("add");
            } catch (CommandsNotLoadedException e) {
                AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
            }

            Platform.runLater(() -> {
                CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
                if (response != null) {
                    AlertUtility.infoAlert(response.getResponse());
                } else {
                    AlertUtility.errorAlert("idk why but you're object is not added, or server is just taking a nap");
                }
                ((Stage) nameField.getScene().getWindow()).close();
            });
        } else {
            AlertUtility.errorAlert("Please correct all errors and fill the enums before proceeding.");
        }
    }

    /**
     * This method is invoked when the "Cancel" button is clicked.
     * It closes the Create Window.
     */
    @FXML
    protected void onCancelButtonClick() {
        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void validation() {
        NameValidator nameValidator = new NameValidator();
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = nameValidator.validate(newValue);
            validationState.put("name", valid);
            updateValidationState(nameField, valid, "Name is not valid. " + nameValidator.getDescr());
        });

        CoordinateXValidator coordinateXValidator = new CoordinateXValidator();
        coordXField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                boolean valid = newValue.matches("\\d+") && coordinateXValidator.validate(Integer.valueOf(newValue));
                validationState.put("coordX", valid);
                updateValidationState(coordXField, valid, "CoordX is not valid. " + coordinateXValidator.getDescr());
            } catch (NumberFormatException e) {
                updateValidationState(coordXField, false, "CoordX is not valid. It should be a number.");
            }
        });

        CoordinateYValidator coordinateYValidator = new CoordinateYValidator();
        coordYField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                boolean valid = newValue.matches("-?\\d+(\\.\\d+)?") && coordinateYValidator.validate(Double.valueOf(newValue));
                validationState.put("coordY", valid);
                updateValidationState(coordYField, valid, "CoordY is not valid. " + coordinateYValidator.getDescr());
            } catch (NumberFormatException e) {
                updateValidationState(coordYField, false, "CoordY is not valid. It should be a number.");
            }
        });

        AreaValidator areaValidator = new AreaValidator();
        areaField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                boolean valid = newValue.matches("\\d+") && areaValidator.validate(Integer.valueOf(newValue));
                validationState.put("area", valid);
                updateValidationState(areaField, valid, "Area is not valid. " + areaValidator.getDescr());
            } catch (NumberFormatException e) {
                updateValidationState(areaField, false, "Area is not valid. It should be a number.");
            }
        });
        PopulationValidator populationValidator = new PopulationValidator();
        populationField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                boolean valid = newValue.matches("\\d+") && populationValidator.validate(Integer.valueOf(newValue));
                validationState.put("population", valid);
                updateValidationState(populationField, valid, "Population is not valid. " + populationValidator.getDescr());
            } catch (NumberFormatException e) {
                updateValidationState(populationField, false, "Population is not valid. It should be a number.");
            }
        });
        MetersAboveSeaLevelValidator metersAboveSeaLevelValidator = new MetersAboveSeaLevelValidator();
        metersAboveSeaLevelField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                boolean valid = newValue.matches("-?\\d+(\\.\\d+)?") && metersAboveSeaLevelValidator.validate(Double.valueOf(newValue));
                validationState.put("seaLevel", valid);
                updateValidationState(metersAboveSeaLevelField, valid, "Meters Above Sea Level is not valid. " + metersAboveSeaLevelValidator.getDescr());
            } catch (NumberFormatException e) {
                updateValidationState(metersAboveSeaLevelField, false, "Meters Above Sea Level is not valid. It should be a number.");
            }
        });

        climateChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && Arrays.stream(Climate.values()).map(Enum::name).toList().contains(newValue);
            validationState.put("climate", valid);
            updateValidationState(climateChoiceBox, valid, "Climate is not valid. It should be one of " + Arrays.toString(Climate.values()));
        });

        governmentChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && Arrays.stream(Government.values()).map(Enum::name).toList().contains(newValue);
            validationState.put("government", valid);
            updateValidationState(governmentChoiceBox, valid, "Government is not valid. It should be one of " + Arrays.toString(Government.values()));
        });

        standardsChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && Arrays.stream(StandardOfLiving.values()).map(Enum::name).toList().contains(newValue);
            validationState.put("standards", valid);
            updateValidationState(standardsChoiceBox, valid, "Standards is not valid. It should be one of " + Arrays.toString(StandardOfLiving.values()));
        });

        governorField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = nameValidator.validate(newValue);
            validationState.put("governor", valid);
            updateValidationState(governorField, valid, "Name is not valid. " + nameValidator.getDescr());
        });
    }

    private void updateValidationState(TextField field, boolean valid, String message) {
        if (valid) {
            field.setStyle("-fx-background-color: #00ff00;"); //green color
            field.setTooltip(null);
        } else {
            field.setStyle("-fx-background-color: #ff0000;"); //red color
            field.setTooltip(new Tooltip(message));
        }
    }

    private void updateValidationState(ChoiceBox<String> choiceBox, boolean valid, String message) {
        if (valid) {
            choiceBox.setStyle("-fx-background-color: #00ff00;"); //green color
            choiceBox.setTooltip(null);
        } else {
            choiceBox.setStyle("-fx-background-color: #ff0000;"); //red color
            choiceBox.setTooltip(new Tooltip(message));
        }
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }
}
