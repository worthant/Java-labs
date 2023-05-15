package gui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginWindow {

    private Stage stage;
    private Scene scene;

    public LoginWindow() {
        try {
            stage = new Stage();
            URL fxmlLocation = getClass().getResource("/login/loginWindow.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            scene = new Scene(root, 680, 457);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }
}


