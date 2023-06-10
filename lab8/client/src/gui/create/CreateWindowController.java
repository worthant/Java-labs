package gui.create;

import client.DataHolder;
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
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import responses.CommandStatusResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Controller class for the Create Window.
 * This class handles user interactions in the Create Window UI.
 */
public class CreateWindowController {
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

    private ValidationSupport validationSupport;

    @FXML
    public void initialize() {
        Arrays.stream(Climate.values()).forEach(value -> climateChoiceBox.getItems().add(value.name()));
        Arrays.stream(Government.values()).forEach(value -> governmentChoiceBox.getItems().add(value.name()));
        Arrays.stream(StandardOfLiving.values()).forEach(value -> standardsChoiceBox.getItems().add(value.name()));
    }


    /**
     * This method is invoked when the "Create" button is clicked.
     * It validates the input fields and, if valid, creates a new City object and sends it to the server.
     * If the fields are not valid, it shows an error message.
     */
    @FXML
    protected void onCreateButtonClick() {
        // Initialize the validation support
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.setValidationDecorator(new GraphicValidationDecoration());


        // Validators for each field
        validationSupport.registerValidator(nameField, Validator.createPredicateValidator(
                (String value) -> new NameValidator().validate(value),
                "Name is not valid. " + new NameValidator().getDescr()
        ));
        validationSupport.registerValidator(coordXField, Validator.createPredicateValidator(
                (String value) -> value.matches("\\d+") && new CoordinateXValidator().validate(Integer.valueOf(value)),
                "CoordX is not valid. " + new CoordinateXValidator().getDescr()
        ));
        validationSupport.registerValidator(coordYField, Validator.createPredicateValidator(
                (String value) -> value.matches("-?\\d+(\\.\\d+)?") && new CoordinateYValidator().validate(Double.valueOf(value)),
                "CoordY is not valid. " + new CoordinateYValidator().getDescr()
        ));
        validationSupport.registerValidator(areaField, Validator.createPredicateValidator(
                (String value) -> value.matches("\\d+") && new AreaValidator().validate(Integer.valueOf(value)),
                "Area is not valid. " + new AreaValidator().getDescr()
        ));
        validationSupport.registerValidator(populationField, Validator.createPredicateValidator(
                (String value) -> value.matches("\\d+") && new PopulationValidator().validate(Integer.valueOf(value)),
                "Population is not valid. " + new PopulationValidator().getDescr()
        ));
        validationSupport.registerValidator(metersAboveSeaLevelField, Validator.createPredicateValidator(
                (String value) -> value.matches("-?\\d+(\\.\\d+)?") && new MetersAboveSeaLevelValidator().validate(Double.valueOf(value)),
                "Meters Above Sea Level is not valid. " + new MetersAboveSeaLevelValidator().getDescr()
        ));
        validationSupport.registerValidator(climateChoiceBox, Validator.createPredicateValidator(
                (String value) -> Arrays.stream(Climate.values()).anyMatch((v) -> v.name().equals(value)),
                "Climate is not valid. It should be one of " + Arrays.toString(Climate.values())
        ));
        validationSupport.registerValidator(governmentChoiceBox, Validator.createPredicateValidator(
                (String value) -> Arrays.stream(Government.values()).anyMatch((v) -> v.name().equals(value)),
                "Government is not valid. It should be one of " + Arrays.toString(Government.values())
        ));
        validationSupport.registerValidator(standardsChoiceBox, Validator.createPredicateValidator(
                (String value) -> Arrays.stream(StandardOfLiving.values()).anyMatch((v) -> v.name().equals(value)),
                "Standard Of Living is not valid. It should be one of " + Arrays.toString(StandardOfLiving.values())
        ));


        // Check the validity of all the fields
        if (!validationSupport.isInvalid()) {
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

            // Validate created City object again
            models.validators.Validator<City> validator = new CityValidator();
            if (validator.validate(city)) {
                DataHolder.getInstance().setCityObject(city);
                try {
                    ExecutorService service = Executors.newSingleThreadExecutor();
                    Future<Boolean> future = service.submit(() -> {
                        try {
                            SingleCommandExecutor executor = new SingleCommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), CommandMode.GUIMode);
                            executor.executeCommand("add");
                            return true;
                        } catch (CommandsNotLoadedException e) {
                            Platform.runLater(() -> {
                                AlertUtility.errorAlert("Can't load commands from server. Please wait until the server will come back");
                            });
                            return false;
                        }
                    });

                    future.get();  // Wait until the command execution is completed

                    Platform.runLater(() -> {
                        CommandStatusResponse response = (CommandStatusResponse) DataHolder.getInstance().getBaseResponse();
                        if (response != null) {
                            AlertUtility.infoAlert(response.getResponse());
                        } else {
                            AlertUtility.errorAlert("idk why but you're object is not added, or server is just taking a nap");
                        }
                        ((Stage) nameField.getScene().getWindow()).close();
                    });

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            } else {
                displayValidationErrors();
            }
        } else {
            displayValidationErrors();
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

    private void displayValidationErrors() {
        validationSupport.getRegisteredControls().forEach(control -> {
            ValidationResult result = validationSupport.getValidationResult();
            if (result != null && !result.getErrors().isEmpty()) {
                String errorMessage = result.getErrors().toString();
                AlertUtility.errorAlert(errorMessage);
            }
        });
    }
}
