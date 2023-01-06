package CosmicObjects;

import Interfaces.Lightable;
import Interfaces.Reflectable;
import Interfaces.Sound;

public abstract class CosmicObject implements Lightable, Reflectable {
    private int brightness;
    private String name;
    private double radius;
    private double mass;
    private int id;
    private CosmicObjectType type;
    private String time;

    public CosmicObject(String name, CosmicObjectType type, int id){
        this.name = name;
        this.type = type;
        this.id = id;
        System.out.println("Генерация " + name);
    }

    public CosmicObject(String name, double radius, double mass, CosmicObjectType type, int id, int brightness, String time) {
        this.brightness = brightness;
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.type = type;
        this.id = id;
        this.time = time;
        System.out.println("Генерация " + type + " " + name);
    }

    public String getTime(){
        return this.time;
    }

    public String setTime(String time){
        return this.time = time;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String light(int light){return " ";}

    @Override
    public String reflect(int reflect){return " ";}

    public void getRadius(){
        System.out.println(this.radius);
    }

    public void getMass(){
        System.out.println(this.mass);
    }

    public void getId(){
        System.out.println(this.id);
    }

    public void getType(){
        System.out.println(this.type);
    }

    public String getBrightness(){
        if (this.brightness < 2) {
            return "темно";
        } else {
            return "светло";
        }
    }
    @Override
    public int hashCode() {
        return 1000 * this.name.hashCode() - 7;
    }

    @Override
    public String toString() {
        return "Cosmic: {"
                + "CosmicObject name = '" + this.getName() + '\''
                + " I di = " + this.hashCode()
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
        if (!(o instanceof CosmicObject)) {
            return false;
        }
        CosmicObject c = (CosmicObject) o;
        return (id == c.id);
    }
}

