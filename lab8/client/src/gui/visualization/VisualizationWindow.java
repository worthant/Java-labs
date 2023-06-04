package gui.visualization;

import client.Client;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.City;
import requestLogic.requestSenders.GetOwnershipRequestSender;
import responses.GetOwnershipResponse;
import serverLogic.ServerConnectionHandler;

import java.net.URL;
import java.util.*;

public class VisualizationWindow {
    private Stage stage;
    private WebEngine webEngine;
    private TreeSet<City> collection;

    private Map<Long, String> ownershipMap;
    private Map<String, String> userColorMap = new HashMap<>(); // Stores user-color pairs

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
            loadOwnershipMap();

            Random random = new Random();

            for (City city : collection) {
                double lng = random.nextDouble() * (179.9 - (-179.9)) + (-179.9);
                double lat = random.nextDouble() * (89.9 - (-89.9)) + (-89.9);

                String color = getIconName(city.getId());
                webEngine.executeScript("addCity('" + city.getName() + "', " + lng + ", " + lat + ", '" + ownershipMap.get(city.getId()) + "', '" + color + "');");
                System.out.println(city.getName() + ": lng - " + lng + ", lat - " + lat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private double convertCoordinate(double value, double originalMin, double originalMax, double targetMin, double targetMax) {
        double normalizedValue = (value - originalMin) / (originalMax - originalMin);
        double clampedValue = Math.max(Math.min(normalizedValue, 1.0), 0.0);
        return targetMin + (clampedValue * (targetMax - targetMin));
    }


    private String getIconName(Long city_id) {
        String user = ownershipMap.get(city_id);
        return userColorMap.getOrDefault(user, null);
    }

    /**
     * This method loads ownershipMap from the server in Map<Integer, String>.
     * (city_id, client_name)
     * And then, generates colors for each user -> ownershipMap
     * <p>
     * Note, that our client is always "green"
     */
    private void loadOwnershipMap() {
        Client client = Client.getInstance();
        GetOwnershipRequestSender rqSender = new GetOwnershipRequestSender();
        GetOwnershipResponse response = rqSender.sendCommand(client.getName(), client.getPasswd(),
                new CommandDescription("get_ownership", new ExternalBaseReceiverCaller()), new String[]{"get_ownership"}, ServerConnectionHandler.getCurrentConnection());
        this.ownershipMap = response.getOwnershipMap();

        for (String user : new HashSet<>(ownershipMap.values())) {
            if (user.equals(Client.getInstance().getName())) {
                userColorMap.put(user, "main.png");
            } else {
                String iconName = generateRandomIconName();
                userColorMap.put(user, iconName);
            }
        }
    }

    private String generateRandomIconName() {
        // This depends on how you named your icons.
        // In this example we assume you have icons named 'icon_#.svg', where # is a number between 1 and 100.
        int iconNumber = new Random().nextInt(100) + 1;
        return "icon_" + iconNumber + ".svg";
    }


    public void show() {
        stage.show();
    }
}
