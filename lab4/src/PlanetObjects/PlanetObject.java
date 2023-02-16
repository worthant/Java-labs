package PlanetObjects;

import Colors.Color;
import CosmicObjects.CosmicObject;

public abstract class PlanetObject {
    private String name;
    private int id;
    private Color color;
    public PlanetObject(String name, int id, Color color){
        this.name = name;
        this.id = id;
        this.color = color;
        System.out.println("Формирование планетного объекта " + name);
    }
    public String getName(){
        return this.name;
    }
    public int getId(){return this.id;}

    public String getView() {
        return this.getName() + " виднеется вдали";
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return 1337 * this.name.hashCode() + 94;
    }

    @Override
    public String toString() {
        return "Object: {"
                + "Местонахождение = '" + this.getName() + '\''
                + " Координаты = " + this.hashCode()
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
        if (!(o instanceof PlanetObject)) {
            return false;
        }
        PlanetObject planetObject = (PlanetObject) o;
        return (id == planetObject.id);
    }

}
