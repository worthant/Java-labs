package gui.visualization;

import client.Client;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import models.City;
import requestLogic.requestSenders.GetOwnershipRequestSender;
import responses.GetOwnershipResponse;
import serverLogic.ServerConnectionHandler;

import java.util.*;

public class VisualizationWindowController {
    @FXML
    private Canvas canvas;

    private Map<Long, String> ownershipMap; // Map of (city_id, client_name)
    private Map<String, Paint> colorMap = new HashMap<>();
    private TreeSet<City> cities;

    public VisualizationWindowController(TreeSet<City> cities) {
        this.cities = cities;
    }

    public void initialize() {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cityClicked(event.getX(), event.getY()));
        loadOwnershipMap();
        drawCities();
    }

    public void setCities(TreeSet<City> cities) {
        this.cities = cities;
    }

    private void loadOwnershipMap() {
        Client client = Client.getInstance();
        GetOwnershipRequestSender rqSender = new GetOwnershipRequestSender();
        GetOwnershipResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("get_ownership", new ExternalBaseReceiverCaller()), new String[]{"get_ownership"}, ServerConnectionHandler.getCurrentConnection());
        this.ownershipMap = response.getOwnershipMap();

        for (String user : new HashSet<>(ownershipMap.values())) {
            if (user.equals(Client.getInstance().getName())) {
                colorMap.put(user, Color.GREEN);
            } else {
                Color randomColor = generateRandomColor();
                colorMap.put(user, randomColor);
            }
        }
    }

    private Color generateRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b, 1.0f);
    }

    private void cityClicked(double x, double y) {
        // TODO: implement to show info of the clicked city in infoArea
    }

    private void drawCities() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (City city : cities) {
            String owner = ownershipMap.get(city.getId());
            Paint color = colorMap.getOrDefault(owner, Color.BLACK);
            double size = Math.log(city.getPopulation()); // or some other function to convert population to size

            // Convert coordinates to pixels. This depends on the range of your coordinates and the size of your Canvas.
            // The code below assumes your x and y are between -1000 and 1000.
            double canvasX = (double) (city.getCoordinates().getX() + 1000) / 2000 * canvas.getWidth();
            double canvasY = (city.getCoordinates().getY() + 1000) / 2000 * canvas.getHeight();

            gc.setFill(color);
            gc.fillOval(canvasX - size / 2, canvasY - size / 2, size, size);
        }
    }
}

