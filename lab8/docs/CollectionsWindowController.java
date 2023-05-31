@FXML
    protected void onVisualizeButtonClick() {
        VisualizationWindow visualizationWindow = new VisualizationWindow();
        visualizationWindow.show();
        // Start the timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollectionToVisualizationWindow(visualizationWindow)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadCollectionToVisualizationWindow(VisualizationWindow visualizationWindow) {
        for (City city : collection) {
            String color = "red";
            int size = (int) Math.log(city.getPopulation());
            double lng = convertCoordinate(city.getCoordinates().getX(), Integer.MIN_VALUE, Integer.MAX_VALUE, -180, 180);
            double lat = convertCoordinate(city.getCoordinates().getY(), Double.MIN_VALUE, Double.MAX_VALUE, -90, 90);

            visualizationWindow.addCity(lat, lng, color, size, city.getName());
        }
    }

    private double convertCoordinate(double value, double originalMin, double originalMax, double targetMin, double targetMax) {
        return (value - originalMin) / (originalMax - originalMin) * (targetMax - targetMin) + targetMin;
    }