package Humans;

import CosmicObjects.*;
import Interfaces.Viewable;
import PlanetObjects.PlanetObject;


public abstract class Human {
    private String name;
    private int id;
    private CosmicObject cosmicLocation;
    private PlanetObject planetLocation;
    private boolean escapeFlag = false;

    public Human(String name, int id, CosmicObject cosmicLocation, PlanetObject planetLocation) {
        this.name = name;
        this.id = id;
        this.cosmicLocation = cosmicLocation;
        this.planetLocation = planetLocation;
    }

    public String getName() {
        return this.name;
    }
    public String getCosmicLocation() {
        return this.cosmicLocation.getName();
    }
    public String getPlanetLocation(){ return this.planetLocation.getName();}

    public void setCosmicLocation(CosmicObject cosmicLocation) {
        System.out.println(this.getName() + " переместился c " + this.cosmicLocation + " на " + cosmicLocation);
        this.cosmicLocation = cosmicLocation;
    }

    public void setPlanetLocation(PlanetObject planetLocation) {
        System.out.println(this.getName() + " переместился c " + this.planetLocation.getName() + " на " + planetLocation.getName());
        this.planetLocation = planetLocation;
        if (!escapeFlag) {
            System.out.println("Получено достижение 'Идеальный побег'");
            escapeFlag = true;
        }
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

    public String feelBad() {
        return this.getName() + " плохо себя чувствует";
    }

    public String feelGood() {
        return this.getName() + " хорошо себя чувствует";
    }

    public String say(String speech) {
        return this.getName() + " сказал: " + speech;
    }

    public String takeOff(String objectName) {
        return this.getName() + " снял с себя " + objectName;
    }

    public String putOn(String objectName) {
        return this.getName() + " надел на себя " + objectName;
    }

    public String stay(PlanetObject place) {
        return this.getName() + " остался в " + place.getName();
    }

    public String goOutside(PlanetObject place) {
        return this.getName() + " вышел из " + place.getName();
    }

    public String go(String place) {
        return this.getName() + " отправился в " + place;
    }

    @Override
    public int hashCode() {
        return 1000 * this.name.hashCode() - 7;
    }

    @Override
    public String toString() {
        return "Human: {"
                + "ASStronaut name = '" + this.getName() + '\''
                + " id = " + this.hashCode()
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
