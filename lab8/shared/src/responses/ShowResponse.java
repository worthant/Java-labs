package responses;

import models.City;

import java.util.TreeSet;

public class ShowResponse extends CommandStatusResponse {
    private TreeSet<City> response;

    public ShowResponse(String oldResponse, int statusCode, TreeSet<City> response) {
        super(oldResponse, statusCode);
        this.response = response;
    }

    public static ShowResponse of(String s, TreeSet<City> cityTreeSet) {
        return new ShowResponse(s, 0, cityTreeSet);
    }

    public TreeSet<City> getCityTreeSet() {
        return response;
    }
}

