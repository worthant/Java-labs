package gui.collections;

import Client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class CollectionsWindow {

    private Stage stage;
    private Scene scene;

    public CollectionsWindow() {
        stage = new Stage();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        // Username
        Text usernameText = new Text(Client.getInstance().getName());
        usernameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        usernameText.setFill(Color.web("#333333")); // replace HEX_VALUE with your green color hex code

        // Table
        TableView<Object> table = new TableView<>(); // Replace Object with your actual data type
        String[] headers = {"id", "name", "coord X", "coord Y", "creation", "area", "population", "government", "standards", "climate", "governor"};
        for (String header : headers) {
            TableColumn<Object, String> column = new TableColumn<>(header); // Replace Object with your actual data type
            column.setCellValueFactory(new PropertyValueFactory<>(header.toLowerCase()));
            table.getColumns().add(column);
        }

        // Dummy data
        ObservableList<Object> data = FXCollections.observableArrayList(
                new Object[] {1, "Vladimir", 56, 800, "990-06-01", 124, 349951, "DEMOCRACY", "VERY HIGH", "STEPPE", "ALEXEY"} // Replace Object with your actual data type
                // Add more dummy data here
        );
        table.setItems(data);

        // Buttons
        Button createButton = new Button("Create");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button visualizeButton = new Button("Visualize");
        Button commandsButton = new Button("Commands");

        // Action Handlers for buttons
        createButton.setOnAction(event -> {
            // Handle Create button
        });
        editButton.setOnAction(event -> {
            // Handle Edit button
        });
        deleteButton.setOnAction(event -> {
            // Handle Delete button
        });
        visualizeButton.setOnAction(event -> {
            // Handle Visualize button
        });
        commandsButton.setOnAction(event -> {
            // Handle Commands button
        });

        // Adding elements to the layout
        root.getChildren().addAll(usernameText, table, createButton, editButton, deleteButton, visualizeButton, commandsButton);

        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}

