package gui;

import Client.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class LoginWindow {

    private Stage stage;
    private Scene scene;

    public LoginWindow() {
        stage = new Stage();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Geolocation Icon
        Image image = new Image("C:\\Users\\Boris\\Itmo\\Java_labs\\lab8\\docs\\img.png");
        ImageView geoIcon = new ImageView(image);
        geoIcon.setFitHeight(50);
        geoIcon.setFitWidth(50);
        geoIcon.setOnMouseClicked(event -> {
            // Here goes the logic for changing the locale
        });

        // Welcome Text
        Text welcomeText = new Text("Welcome back");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text credentialsText = new Text("Please enter your credentials");
        credentialsText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Username and Password Fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");

        // Sign In Button
        Button signInButton = new Button("Sign In");
        signInButton.setTextFill(Color.GREEN);
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

        // Adding elements to the layout
        root.getChildren().addAll(geoIcon, welcomeText, credentialsText, usernameField, passwordField, signInButton);

        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}

