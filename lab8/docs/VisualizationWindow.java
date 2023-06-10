package gui.worldMap;

import com.sun.source.tree.Tree;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.City;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.TreeSet;

public class VisualizationWindow {
    private Stage stage;
    private WebEngine webEngine;
    private TreeSet<City> collection;

    public VisualizationWindow(TreeSet<City> collection) {
        this.collection = collection;

        stage = new Stage();
        WebView webView = new WebView();
        webEngine = webView.getEngine();
        URL url = getClass().getResource("/visualization/map.html");
        if (url == null) {
            throw new RuntimeException("Cannot find file map.html in html directory");
        }
        webEngine.load(url.toString());

        StackPane root = new StackPane();
        root.getChildren().add(webView);
        Scene scene = new Scene(root, 1500, 900);
        stage.setScene(scene);
        stage.show();

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                loadCities(collection);
            }
        });
    }

    public void loadCities(TreeSet<City> collection) {
        try {
            this.collection = collection;

            for (City city : collection) {
                double lng = convertCoordinate(city.getCoordinates().getX(), Integer.MIN_VALUE, Integer.MAX_VALUE, -180, 180);
                double lat = convertCoordinate(city.getCoordinates().getY(), Double.MIN_VALUE, Double.MAX_VALUE, -90, 90);
                webEngine.executeScript("addCity('" + city.getName() + "', " + lng + ", " + lat + ", 'some_owner');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double convertCoordinate(double value, double originalMin, double originalMax, double targetMin, double targetMax) {
        return (value - originalMin) / (originalMax - originalMin) * (targetMax - targetMin) + targetMin;
    }

    public void show() {
        stage.show();
    }
}
