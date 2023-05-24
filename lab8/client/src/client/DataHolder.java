package client;

import models.City;
import responses.BaseResponse;

import java.util.TreeSet;

public class DataHolder {
    private TreeSet<City> collection;

    private String name;
    private String coordX;
    private String coordY;
    private String area;
    private String population;
    private String metersAboveSeaLevel;
    private String climate;
    private String government;
    private String standards;
    private String governor;
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BaseResponse getBaseResponse() {
        return response;
    }

    public void setBaseResponse(BaseResponse response) {
        this.response = response;
    }

    private BaseResponse response;

    private DataHolder() {}

    public void setCityObject(City city) {
        this.city = city;
    }

    public City getCityObject() {
        return city;
    }

    private static class Holder {
        private static final DataHolder INSTANCE = new DataHolder();
    }

    public static DataHolder getInstance() {
        return Holder.INSTANCE;
    }

    public TreeSet<City> getCollection() {
        return collection;
    }
    public void setCollection(TreeSet<City> collection) {
        this.collection = collection;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(String metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getGovernor() {
        return governor;
    }

    public void setGovernor(String governor) {
        this.governor = governor;
    }
}

