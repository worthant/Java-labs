package gui.visualization;

import gui.AlertUtility;
import gui.create.CityManagementWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.City;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class VisualizationWindowController {
    @FXML
    private Canvas canvas;
    private Map<String, Color> colorMap = new HashMap<>();
    private Map<Long, String> ownershipMap; // Map of (city_id, client_name)
    private TreeSet<City> cities;

    public VisualizationWindowController(TreeSet<City> cities) {
        this.cities = cities;
    }

    @FXML
    public void initialize() {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cityClicked(event.getX(), event.getY()));
        drawMesh();
    }

    private void drawMesh() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.web("#797979"));
        gc.setLineWidth(1);
        double cellSize = 20.0;
        double width = canvas.getWidth(), height = canvas.getHeight();

        for (double x = cellSize; x < width; x += cellSize) {
            gc.strokeLine(x, 0, x, height);
        }
        for (double y = cellSize; y < height; y += cellSize) {
            gc.strokeLine(0, height - y, width, height - y);
        }
    }


    public void setCities(TreeSet<City> cities) {
        this.cities = cities;
    }


    private void cityClicked(double x, double y) {
        City clickedCity = null;
        for (City city : cities) {
            double canvasX = (double) city.getCoordinates().getX() / 1000 * canvas.getWidth();
            double canvasY = (1 - city.getCoordinates().getY() / 1000) * canvas.getHeight();
            double size = Math.log(city.getPopulation()) * 2;
            double distance = Math.sqrt(Math.pow(x - canvasX, 2) + Math.pow(y - canvasY, 2));

            if (distance <= size / 2) {
                clickedCity = city;
                break;
            }
        }

        if (clickedCity != null) {
            CityManagementWindow cityManagementWindow = new CityManagementWindow("Editing City");
            cityManagementWindow.show();
            cityManagementWindow.setCity(clickedCity);
        } else {
            AlertUtility.infoAlert("Please, select any city to edit it!");
        }
    }


    private void drawCities() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (City city : cities) {
            Color color = colorMap.getOrDefault(ownershipMap.get(city.getId()), Color.BLACK);
            double size = Math.log(city.getPopulation()) * 2;

            double canvasX = (double) city.getCoordinates().getX() / 1000 * canvas.getWidth();
            double canvasY = (1 - city.getCoordinates().getY() / 1000) * canvas.getHeight();

            gc.setStroke(color);
            gc.setLineWidth(2); // Adjust this to make your circle's border thicker or thinner
            gc.strokeOval(canvasX - size / 2, canvasY - size / 2, size, size);

            // Draw the icon inside the circle. This assumes that you have a method getCityIcon() that returns an Image object representing the city icon.
            // You'll need to adjust this code to fit your actual method for getting the city icon.
            Image cityIcon = getCityIcon();
            int iconSize = (int) Math.round(size - 4); // Subtracting 4 to give some padding around the icon.
            if (iconSize <= 0) {
                iconSize = 1;
            }

            ImageView imageView = new ImageView(cityIcon);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(iconSize);
            imageView.setFitHeight(iconSize);
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            Image scaledCityIcon = imageView.snapshot(sp, null);

            PixelReader pr = scaledCityIcon.getPixelReader();
            WritableImage coloredIcon = new WritableImage((int) iconSize, (int) iconSize);
            PixelWriter pw = coloredIcon.getPixelWriter();
            for (int y = 0; y < scaledCityIcon.getHeight(); y++) {
                for (int x = 0; x < scaledCityIcon.getWidth(); x++) {
                    Color pixelColor = pr.getColor(x, y);
                    if (pixelColor.getOpacity() > 0) {
                        pw.setColor(x, y, color);
                    } else {
                        pw.setColor(x, y, Color.TRANSPARENT);
                    }
                }
            }
            gc.drawImage(coloredIcon, canvasX - iconSize / 2, canvasY - iconSize / 2);
        }
    }

    public void loadColorMap(Map<String, Color> colorMap, Map<Long, String> ownershipMap) {
        this.colorMap = colorMap;
        this.ownershipMap = ownershipMap;
        startTimer();
    }

    private void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> drawCities()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Image getCityIcon() {
        return new Image(Objects.requireNonNull(getClass().getResource("/icons/thick_building.png")).toExternalForm());
    }
}

