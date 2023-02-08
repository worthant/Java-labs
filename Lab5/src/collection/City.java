package collection;

import java.util.Date;

public class City implements Comparable<City> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private Double metersAboveSeaLevel;
    private Climate climate; //Поле может быть null
    private Government government; //Поле не может быть null
    private StandartOfLiving standardOfLiving; //Поле не может быть null
    private Human governor; //Поле может быть null

    public City(int id, String name, Coordinates coordinates, Date creationDate, int area, Integer population, Double metersAboveSeaLevel, Climate climate, Government government, StandartOfLiving standardOfLiving, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.climate = climate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public StandartOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandartOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    @Override
    public int compareTo(City o) {
        int result = this.name.compareTo(o.name);
        if (result == 0)
            result = this.area - o.area;
        if (result == 0)
            result = this.coordinates.compareTo(o.coordinates);
        if (result == 0)
            result = this.population.compareTo(o.population);
        if (result == 0)
            result = this.creationDate.compareTo(o.creationDate);
        if (result == 0)
            result = this.standardOfLiving.compareTo(o.standardOfLiving);
        if (result == 0)
            result = this.government.compareTo(o.government);
        if (result == 0) {
            if (climate != null)
                result = this.climate.compareTo(o.climate);
            else if (o.climate != null)
                result = -o.climate.compareTo(this.climate);
        }
        if (result == 0) {
            if (governor != null)
                result = this.governor.compareTo(o.governor);
            else if (o.governor != null)
                result = -o.governor.compareTo(this.governor);
        }
        return result;
    }

    @Override
    public String toString() {
        String result;
        result = "City: " +
                "id = " + id +
                ", name = '" + name + "'" +
                ", coordinates = " + coordinates.toString() +
                ", creationDate = " + creationDate.toString() +
                ", area = " + area +
                ", population = " + population +
                ", metersAboveSeaLevel = " + metersAboveSeaLevel +
                ", government = " + government +
                ", standardOfLiving = " + standardOfLiving;

        if (climate == null || governor == null) {
            result += ", climate = null" + ", governor = null";
        } else {
            result += ", climate = " + climate + ", governor = " + governor;
        }
        return result;
    }
}
