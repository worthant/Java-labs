package gui.create;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CreateWindow {

    private Stage stage;
    private Scene scene;

    public CreateWindow() {
        try {
            stage = new Stage();
            URL fxmlLocation = getClass().getResource("/create/createWindow.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            scene = new Scene(root, 300, 500);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }
}
