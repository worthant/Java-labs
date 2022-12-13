package Humans;

import CosmicObjects.*;
import Interfaces.Viewable;
import PlanetObjects.PlanetObject;


public abstract class Human {
    private String name;
    private int id;
    private CosmicObject cosmicLocation;
    private PlanetObject planetLocation;

    public Human(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
        this.name = name;
        this.id = id;
        this.cosmicLocation = cosmicLocation;
        this.planetLocation = planetLocation;
    }

    public String getName() {
        return this.name;
    }


    //TODO
    // make getCosmicLocation() + getPlanetLocation()
    // also, check the see() method and all the logic here


    public String getLocation() {
        return this.cosmicLocation.getViewPoint();
    }

    public void see(Viewable target, CosmicObject viewPoint) {
        System.out.println(this.name + " увидел " + target.getView(viewPoint));
    }

    public void read(String text) {
        System.out.println(this.name + " прочитал " + text);
    }

    public void write(String text) {
        System.out.println(this.name + " написал " + text);
    }

    public void draw(String figure) {
        System.out.println(this.name + " нарисовал " + figure);
    }

    @Override
    public int hashCode() {
        return 1000 * this.name.hashCode() - 7;
    }

    @Override
    public String toString() {
        return "Person: {"
                + "Parson name = '" + this.getName() + '\''
                + " Айдишник = " + this.hashCode()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Human)) {
            return false;
        }
        Human c = (Human) o;
        return (id == c.id);
    }
}
