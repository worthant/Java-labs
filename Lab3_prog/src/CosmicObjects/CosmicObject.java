package CosmicObjects;

public abstract class CosmicObject {
    protected int brightness;
    protected String name;
    protected double radius;
    protected double mass;
    protected String id;
    protected CosmicObjectType type;
    protected String time;

    public CosmicObject(String name, CosmicObjectType type){
        this.name = name;
        this.type = type;
    }

    public CosmicObject(String name, double radius, double mass, CosmicObjectType type, String id, int brightness, String time) {
        this.brightness = brightness;
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.type = type;
        this.id = id;
        this.time = time;
    }

    public String getViewPoint(){
        return this.name;
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
}
