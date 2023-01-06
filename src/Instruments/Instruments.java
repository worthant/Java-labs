package Instruments;

import PlanetObjects.PlanetObject;

/** This class really helps mining some Ore. */
public abstract class Instruments {
    private String type;
    public Instruments(String type) {
        System.out.println("Куётся инструмент " + type);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public String bang(PlanetObject surface) {
        return this.getType() + " cтучать о " + surface.getName();
    }
}

