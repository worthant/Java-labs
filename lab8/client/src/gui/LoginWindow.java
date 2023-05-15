package gui;

import Client.Client;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import requestLogic.requestSenders.AuthRequestSender;
import responses.AuthResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Objects;

public class LoginWindow {

    private Stage stage;
    private Scene scene;

    public LoginWindow() {
        stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginWindow.fxml")));
            scene = new Scene(root);
            Button signInButton = (Button) loader.getNamespace().get("signInButton");
            signInButton.getStyleClass().add("button-style");
            TextField usernameField = (TextField) loader.getNamespace().get("usernameField");
            TextField passwordField = (TextField) loader.getNamespace().get("passwordField");
            ImageView geoIcon = (ImageView) loader.getNamespace().get("geoIcon");
            signInButton.setOnAction(event -> {
                // Get username and password from text fields
                String username = usernameField.getText().trim();
                char[] password = passwordField.getText().trim().toCharArray();

                // Authentication logic
                try {
                    AuthRequestSender rqSender = new AuthRequestSender();
                    AuthResponse response = rqSender.sendAuthData(username, password, ServerConnectionHandler.getCurrentConnection());

                    if (response.isAuth()) {
                        // If authentication is successful, close the login window and open the collections window
                        Client.getInstance(username, password);
                        stage.close();
                        CollectionsWindow collectionsWindow = new CollectionsWindow();
                        collectionsWindow.show();
                    } else {
                        // If authentication fails, show an error message
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("There is no user with this name, or password is incorrect");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            scene = new Scene(root, 500, 500);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }
}

