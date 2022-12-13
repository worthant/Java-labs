package PlanetObjects;

public class SpaceSuit extends PlanetObject{
    private int weight = 130;
    public SpaceSuit(String name) {
        super(name);
    }

    public int getWeight() {
        return weight;
    }
}
