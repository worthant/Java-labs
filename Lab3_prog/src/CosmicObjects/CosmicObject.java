package CosmicObjects;

public abstract class CosmicObject {
    private int btightness;
    private String name;
    private double radius;
    private double mass;
    private int id;
    private CosmicObjectType type;

    public CosmicObject(String name, double radius, double mass, CosmicObjectType type, int id, int brightness) {
        this.btightness = brightness;
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.type = type;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getMass(){
        return this.mass;
    }

    public int getId(){
        return this.id;
    }

    public CosmicObjectType getType(){
        return this.type;
    }

    public int getBrightness(){
        return this.btightness;
    }
}
