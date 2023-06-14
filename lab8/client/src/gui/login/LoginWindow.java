package gui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginWindow {
    private Stage stage;

    public LoginWindow(Stage stage) {
        try {
            this.stage = stage;
            stage.setResizable(false);
            URL fxmlLocation = getClass().getResource("/login/loginWindow.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            this.stage.setScene(new Scene(root, 680, 457));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }
}



