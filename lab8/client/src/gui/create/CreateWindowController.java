package gui.create;

import client.DataHolder;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateWindowController {
    @FXML
    private TextField CoordYField;
    @FXML
    private TextField areaField;
    @FXML
    private TextField populationField;
    @FXML
    private TextField metersAboveSeaLevelField;
    @FXML
    private ComboBox climateComboBox;
    @FXML
    private ComboBox governmentComboBox;
    @FXML
    private ComboBox standardsComboBox;
    @FXML
    private TextField governorField;
    @FXML
    private TextField CoordXField;
    @FXML
    private TextField nameField;

    @FXML
    protected void onCreateButtonClick() {
        // pass all the data to data holder for buildObject()
        DataHolder data = DataHolder.getInstance();
        data.setName(nameField.getText());
        data.setCoordX(CoordXField.getText());
        data.setCoordY(CoordYField.getText());
        data.setArea(areaField.getText());
        data.setPopulation(populationField.getText());
        data.setMetersAboveSeaLevel(metersAboveSeaLevelField.getText());
        data.setClimate(climateComboBox.getValue().toString());
        data.setGovernment(governmentComboBox.getValue().toString());
        data.setStandards(standardsComboBox.getValue().toString());
        data.setGovernor(governorField.getText());

        // that's all, now we can close that window
        ((Stage) nameField.getScene().getWindow()).close();
    }

}
