package PlanetObjects;

public class Rocket extends PlanetObject{
    private int weight = 300000;
    public Rocket(String name) {
        super(name);
    }

    public int getWeight(){
        return this.weight;
    }

    //TODO
    // add getView() and make it work properly
}
