package main.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class for generating some place marks for demonstration
 * Copy system.out and paste to data.json
 */
public class GeoJSONGenerator {

    public static void main(String[] args) {
        int numberOfFeatures = 8000;
        for (int i = 0; i < numberOfFeatures; i++) {
            System.out.println(generateGeoJSONFeature(i).toString() + ",");
        }
    }

    private static JSONObject generateGeoJSONFeature(int id) {
        JSONObject feature = new JSONObject();

        feature.put("type", "Feature");
        feature.put("id", id);

        JSONObject geometry = new JSONObject();
        geometry.put("type", "Point");

        // Assuming random values for coordinates for the sake of example
        double lat = Math.random() * 180 - 90;  // values between -90 and 90 for latitude
        double lon = Math.random() * 360 - 180;  // values between -180 and 180 for longitude
        JSONArray coordinates = new JSONArray(new double[]{lat, lon});
        geometry.put("coordinates", coordinates);

        feature.put("geometry", geometry);

        return feature;
    }
}

